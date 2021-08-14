DROP TABLE IF EXISTS account;

CREATE TABLE account (
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  account_number VARCHAR(21) NOT NULL,
  currency VARCHAR(3) NOT NULL,
  ballance DOUBLE NOT NULL,
  status INT DEFAULT NULL
);

INSERT INTO account (account_number, currency, ballance, status) VALUES
  ('test001', 'USD',999, 1);
