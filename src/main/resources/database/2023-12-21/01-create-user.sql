--liquibase formatted sql
--changeset vholovetskyi:1

create table `users`
(
    chat_id       BIGINT       NOT NULL PRIMARY KEY,
    first_name    VARCHAR(255) NOT NULL,
    last_name     VARCHAR(255) NOT NULL,
    phone         VARCHAR(255),
    registered_at TIMESTAMP    NOT NULL,
)