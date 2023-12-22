CREATE SEQUENCE tb_books_seq;

CREATE TABLE IF NOT EXISTS tb_books (
  id bigint NOT NULL DEFAULT NEXTVAL ('tb_books_seq'),
  author varchar(120),
  launch_date timestamp(6) NOT NULL,
  price decimal(65,2) NOT NULL,
  title varchar(120),         
  PRIMARY KEY (id)      
);      