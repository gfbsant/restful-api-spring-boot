CREATE SEQUENCE tb_user_seq;

CREATE TABLE IF NOT EXISTS tb_user (
    id bigint NOT NULL DEFAULT NEXTVAL ('tb_user_seq'),
    user_name varchar(255) DEFAULT NULL,
    full_name varchar(255) DEFAULT NULL,
    password varchar(255) DEFAULT NULL,
    account_non_expired boolean DEFAULT TRUE,
    account_non_locked boolean DEFAULT TRUE,
    credentials_non_expired boolean DEFAULT TRUE,
    enabled boolean DEFAULT TRUE,
    PRIMARY KEY (id),
    CONSTRAINT uk_user_name UNIQUE  (user_name)
);      