CREATE SEQUENCE tb_permission_seq;

CREATE TABLE IF NOT EXISTS tb_permission (
  id bigint NOT NULL DEFAULT NEXTVAL ('tb_permission_seq'),
  description varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);