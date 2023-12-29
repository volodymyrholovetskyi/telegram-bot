--liquibase formatted sql
--changeset vholovetskyi:2

create sequence if not exists ticket_seq increment by 50;

CREATE TABLE ticket
(
    ticket_id     SERIAL    NOT NULL,
    user_id       INTEGER    NOT NULL,
    status        VARCHAR(255) NOT NULL,
    date_of_visit TIMESTAMP NOT NULL,
    PRIMARY KEY (ticket_id),
    CONSTRAINT fk_ticket_product
        FOREIGN KEY (user_id)
            REFERENCES users (chat_id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)