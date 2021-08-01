DROP TABLE IF EXISTS card;

CREATE TABLE card(
id BIGINT AUTO_INCREMENT  PRIMARY KEY,
card_holder VARCHAR(200) NOT NULL,
card_number VARCHAR(21) NOT NULL,
exp_date TIMESTAMP NOT NULL,
account_id BIGINT,
status INT DEFAULT NULL);


INSERT INTO card (card_holder, card_number, exp_date, account_id, status ) VALUES
  ('KARLIS L', '111111111111111111111',current_date + 365, 1, 1),
  ('JANIS S', '222222222222222222222',current_date + 320, 2, 1),
  ('AIJA S', '333333333333333333333',current_date + 170, 2, 1),
  ('MAIJA G', '999999999999999999999',current_date + 170, null, 1);






