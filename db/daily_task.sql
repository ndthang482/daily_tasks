CREATE TABLE daily_task (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            date DATE NOT NULL,
                            user_id BIGINT NOT NULL,
                            points INT NOT NULL,
                            CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES user(id),
                            INDEX idx_user_id (user_id)
);