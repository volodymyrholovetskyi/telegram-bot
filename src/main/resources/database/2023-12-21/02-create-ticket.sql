--liquibase formatted sql
--changeset vholovetskyi:2

CREATE TABLE `ticket`
(
    ticket_id     BIGINT    NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_id       BIGINT    NOT NULL,
    date_of_visit DATETIME NOT NULL,
    CONSTRAINT `fk_ticket_product`
        FOREIGN KEY (user_id) REFERENCES users (chat_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
)