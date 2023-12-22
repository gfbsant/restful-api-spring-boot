INSERT INTO tb_permission (description) VALUES
	('ADMIN'),
	('MANAGER'),
	('COMMON_USER');
	
ALTER SEQUENCE tb_permission_seq RESTART WITH 4;