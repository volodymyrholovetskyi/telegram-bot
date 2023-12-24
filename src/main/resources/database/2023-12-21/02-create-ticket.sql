--liquibase formatted sql
--changeset vholovetskyi:2

create table `ticket`
(
    ticket_id BIGINT NOT NULL PRIMARY KEY AUTO INCREMENT,
    user_id BIGINT NOT NULL,
    date_of_visit TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES (chat_id)
    ON DELETE CASCADE
)