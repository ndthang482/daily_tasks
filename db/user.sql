CREATE TABLE user (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      username VARCHAR(255) NOT NULL,
                      password VARCHAR(255) NOT NULL,
                      fullname VARCHAR(255) NOT NULL,
                      email VARCHAR(255) NOT NULL,
                      points INT NOT NULL
);