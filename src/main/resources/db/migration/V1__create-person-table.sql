CREATE SEQUENCE tb_person_seq;

CREATE TABLE tb_person (
    id bigint NOT NULL DEFAULT NEXTVAL ('tb_person_seq'),
	first_name varchar(255) NOT NULL,
	last_name varchar(255) NOT NULL,	
	address varchar(255) NOT NULL,
	gender varchar(255) NOT NULL,
	CONSTRAINT tb_person_pkey PRIMARY KEY (id)
);