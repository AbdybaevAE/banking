DROP TABLE IF EXISTS TRANSACTIONS CASCADE;
CREATE TABLE TRANSACTIONS
(
    TRANSACTION_ID BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    USER_ID BIGINT NOT NULL
);