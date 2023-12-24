package br.com.restful.security.jwt;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.restful.exceptions.InvalidJwtAuthenticationException;
import br.com.restful.vo.v1.security.TokenValueObject;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class JwtTokenProvider {

	@Value("${security.jwt.token.secret-key:secret}")
	private String secretKey = "secret";

	@Value("${security.jwt.token.expire-length:3600000}")
	private long validityInMillis = 3600000;

	@Autowired
	private UserDetailsService userDetailsService;

	Algorithm algorithm = null;

	final String BEARER_STR = "Bearer ";

	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
		algorithm = Algorithm.HMAC256(secretKey.getBytes());
	}

	public TokenValueObject createAccessToken(String username, List<String> roles) {
		Date now = new Date();
		Date validity = new Date(now.getTime() + validityInMillis);
		String accessToken = getAccessToken(username, roles, now, validity);
		String refreshToken = getRefreshToken(username, roles, now);
		return new TokenValueObject(
				username, true, now, validity, accessToken, refreshToken);
	}

	public TokenValueObject refreshToken(String refreshToken) {
		if (refreshToken.contains(BEARER_STR))
			refreshToken = refreshToken.substring(BEARER_STR.length());
				JWTVerifier verifier = JWT.require(algorithm).build();
		DecodedJWT decodedJWT = verifier.verify(refreshToken);	
		String username = decodedJWT.getSubject();
		List<String> roles = decodedJWT.getClaim("roles").asList(String.class);
		return createAccessToken(username, roles);
	}

	private String getRefreshToken(String username, List<String> roles, Date now) {
		Date validity = new Date(now.getTime() + validityInMillis * 3); // 3 hours
		String accessToken = JWT
				.create()
				.withClaim("roles", roles)
				.withSubject(username)
				.withIssuedAt(now)
				.withExpiresAt(validity)
				.sign(algorithm)
				.strip();
		return accessToken;
	}

	private String getAccessToken(String username, List<String> roles, Date now, Date validity) {
		// Expect something like: "http://localhost:4200"
		String issueUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
				.build().toUriString();
		String accessToken = JWT
				.create()
				.withClaim("roles", roles)
				.withSubject(username)
				.withIssuer(issueUrl)
				.withIssuedAt(now)
				.withExpiresAt(validity)
				.sign(algorithm)
				.strip();
		return accessToken;
	}

	public Authentication getAuthentication(String token) {
		DecodedJWT decodedJWT = decodedToken(token);
		UserDetails userDetails = userDetailsService.loadUserByUsername(decodedJWT.getSubject());
		return new UsernamePasswordAuthenticationToken(
				userDetails, "", userDetails.getAuthorities());
	}

	private DecodedJWT decodedToken(String token) {
		Algorithm alg = Algorithm.HMAC256(secretKey.getBytes());
		JWTVerifier verifier = JWT.require(alg).build();
		DecodedJWT decodedJWT = verifier.verify(token);
		return decodedJWT;
	}

	public String resolveToken(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");

		if (bearerToken != null && bearerToken.startsWith(BEARER_STR))
			return bearerToken.substring(BEARER_STR.length()); // (7)
		return null;
	}

	public boolean validateToken(String token) {
		DecodedJWT decodedJWT = decodedToken(token);
		try {
			if (decodedJWT.getExpiresAt().before(new Date())) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			throw new InvalidJwtAuthenticationException("Expired or invalid token");
		}
	}

}
