CREATE TABLE users (
  username varchar(50) NOT NULL,
  password varchar(100) NOT NULL,
  enabled tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
);

CREATE TABLE authorities (
  username varchar(64) NOT NULL,
  authority varchar(64) NOT NULL,
  CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users (username)
);

INSERT INTO users (username,password,enabled) VALUES
	 ('test','{bcrypt}$2a$10$ImeqoAbgCx3HdADX9Or08eTBqAM6SMnEEn8K/6a0bsZiGg.VzYxBu',1);
INSERT INTO authorities (username,authority) VALUES
	 ('test','ADMIN');