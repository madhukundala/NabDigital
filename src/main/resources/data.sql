DROP TABLE IF EXISTS PRODUCTS_TBL;

CREATE TABLE PRODUCTS_TBL (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  product_name VARCHAR(250) NOT NULL,
  product_value VARCHAR(250) NOT NULL,
  category VARCHAR(250) DEFAULT NULL
);


INSERT INTO PRODUCTS_TBL (product_name, product_value, category) VALUES
  ('TOMATO', '1.22', 'VEG'),
  ('ONION', '34.3', 'VEG'),
  ('CARROTS', '43.2', 'FRUITS'),
  ('APPLE', '432.2', 'FRUITS'),
  ('ORANGES', '143.2', 'FRUITS');