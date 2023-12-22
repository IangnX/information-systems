-- CREACIÓN DE USUARIOS

INSERT INTO "user" (username, name, password, role) VALUES ('rodriguez', 'luis márquez', '$2a$10$ywh1O2EwghHmFIMGeHgsx.9lMw5IXpg4jafeFS.Oi6nFv0181gHli', 'CUSTOMER');
INSERT INTO "user" (username, name, password, role) VALUES ('ricardo', 'fulano pérez', '$2a$10$V29z7/qC9wpHfzRMxGOHye5RMAxCid2/MzJalk0dsiA3zZ9CJfub.', 'EMPLOYEE');
INSERT INTO "user" (username, name, password, role) VALUES ('xiangn', 'mengano hernández', '$2a$10$TMbMuEZ8utU5iq8MOoxpmOc6QWQuYuwgx1xJF8lSMNkKP3hIrwYFG', 'ADMINISTRATOR');


-- CREACIÓN DE PRODUCTOS
INSERT INTO product (name, price) VALUES ('Smartphone', 500.00);
INSERT INTO product (name, price) VALUES ('Auriculares Bluetooth', 50.00);
INSERT INTO product (name, price) VALUES ('Tablet', 300.00);

INSERT INTO product (name, price) VALUES ('Camiseta', 25.00);
INSERT INTO product (name, price) VALUES ('Pantalones', 35.00);
INSERT INTO product (name, price) VALUES ('Zapatos', 45.00);

INSERT INTO product (name, price) VALUES ('Balón de Fútbol', 20.00);
INSERT INTO product (name, price) VALUES ('Raqueta de Tenis', 80.00);

INSERT INTO product (name, price) VALUES ('Aspiradora', 120.00);
INSERT INTO product (name, price) VALUES ('Licuadora', 50.00);
