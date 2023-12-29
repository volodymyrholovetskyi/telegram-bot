--liquibase formatted sql
--changeset vholovetskyi:1

CREATE TABLE users
(
    chat_id       INTEGER NOT NULL,
    first_name    VARCHAR(255) NOT NULL,
    last_name     VARCHAR(255) NOT NULL,
    phone_number  VARCHAR(255) NOT NULL,
    role          VARCHAR(255) NOT NULL,
    registered_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (chat_id)
)