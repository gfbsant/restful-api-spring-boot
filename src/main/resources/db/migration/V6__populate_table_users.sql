INSERT INTO tb_user (id, user_name, full_name, password, account_non_expired, account_non_locked, credentials_non_expired, enabled) VALUES
	(1, 'leandro', 'Leandro Costa', '1e3cdeeaaaeeda173ff6d002e7cb5e3f91ebc354dcff52156c9eaba1793a3a5e5bee306c11099e22', TRUE, TRUE, TRUE, TRUE),
	(2, 'flavio', 'Flavio Costa', '75ec349c1b0ef4ee7b249d0b83ae4861853f3aa77bce8c4b15f28cd43c6424ab4f29df431831bb0d', TRUE, TRUE, TRUE, TRUE);
	
ALTER SEQUENCE tb_user_seq RESTART WITH 3;