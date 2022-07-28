  CREATE TABLE IF NOT EXISTS users
(
    id             BIGINT             NOT NULL     GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 100000 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    name           VARCHAR(50)        NOT NULL,
    surname        VARCHAR(50)        NOT NULL,
    CONSTRAINT     users_pkey         PRIMARY KEY (id),

);


    CREATE TABLE IF NOT EXISTS products
(
    id                  BIGINT             NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 100000 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    price               NUMERIC(15,2)      NOT NULL,
    name                VARCHAR(50)        NULL,
    user_id             BIGINT             NOT NULL,
    CONSTRAINT          products_pk        PRIMARY KEY (id)
    CONSTRAINT          products_id_fkey   FOREIGN KEY (user_id) REFERENCES users (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE CASCADE

);