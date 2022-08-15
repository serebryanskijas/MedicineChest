drop table if exists medicine_chest_medicine;
drop table if exists medicine_dosage_form;
drop table if exists category;
drop table if exists medicine_chest;
drop table if exists medicine;

CREATE TABLE category
(
    id   INTEGER NOT NULL AUTO_INCREMENT,
    name TEXT    NOT NULL
);

CREATE TABLE dosage_form
(
    id   INTEGER NOT NULL AUTO_INCREMENT,
    form TEXT    NULL
);

CREATE TABLE medicine_chest
(
    id   INTEGER NOT NULL AUTO_INCREMENT,
    name TEXT    NOT NULL
);

CREATE TABLE medicine
(
    id           INTEGER NOT NULL AUTO_INCREMENT,
    name         TEXT    NOT NULL,
    manufacturer TEXT    NULL,
    dosage       INTEGER NULL,
    description  TEXT    NULL,
    category_id  INTEGER NOT NULL
);

CREATE TABLE medicine_chest_medicine
(
    id                INTEGER NOT NULL AUTO_INCREMENT,
    count             INT     NOT NULL,
    expiration_date   DATE    NOT NULL,
    medicine_chest_id INTEGER NOT NULL,
    medicine_id       INTEGER NOT NULL
);

CREATE TABLE medicine_dosage_form
(
    medicine_id    INTEGER NOT NULL,
    dosage_form_id INTEGER null
);