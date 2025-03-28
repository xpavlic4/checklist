DROP TABLE vet_specialties IF EXISTS;
DROP TABLE vets IF EXISTS;
DROP TABLE specialties IF EXISTS;
DROP TABLE visits IF EXISTS;
DROP TABLE pets IF EXISTS;
DROP TABLE types IF EXISTS;
DROP TABLE owners IF EXISTS;
DROP TABLE cases IF EXISTS;
DROP TABLE arguments IF EXISTS;
DROP TABLE evaluations IF EXISTS;


CREATE TABLE vets (
  id         INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  first_name VARCHAR(30),
  last_name  VARCHAR(30)
);
CREATE INDEX vets_last_name ON vets (last_name);

CREATE TABLE specialties (
  id   INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  name VARCHAR(80)
);
CREATE INDEX specialties_name ON specialties (name);

CREATE TABLE vet_specialties (
  vet_id       INTEGER NOT NULL,
  specialty_id INTEGER NOT NULL
);
ALTER TABLE vet_specialties ADD CONSTRAINT fk_vet_specialties_vets FOREIGN KEY (vet_id) REFERENCES vets (id);
ALTER TABLE vet_specialties ADD CONSTRAINT fk_vet_specialties_specialties FOREIGN KEY (specialty_id) REFERENCES specialties (id);

CREATE TABLE types (
  id   INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  name VARCHAR(80)
);
CREATE INDEX types_name ON types (name);

CREATE TABLE owners (
  id         INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  first_name VARCHAR(30),
  last_name  VARCHAR_IGNORECASE(30),
  address    VARCHAR(255),
  city       VARCHAR(80),
  telephone  VARCHAR(20)
);
CREATE INDEX owners_last_name ON owners (last_name);

CREATE TABLE pets (
  id         INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  name       VARCHAR(255),
  premisa       VARCHAR(255),
  birth_date DATE,
  type_id    INTEGER,
  owner_id   INTEGER
);
ALTER TABLE pets ADD CONSTRAINT fk_pets_owners FOREIGN KEY (owner_id) REFERENCES owners (id);
ALTER TABLE pets ADD CONSTRAINT fk_pets_types FOREIGN KEY (type_id) REFERENCES types (id);
CREATE INDEX pets_name ON pets (name);

CREATE TABLE visits (
  id          INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  pet_id      INTEGER,
  visit_date  DATE,
  description VARCHAR(255),
  premise       VARCHAR(255) default '',
  predicate       VARCHAR(255) default ''
);
ALTER TABLE visits ADD CONSTRAINT fk_visits_pets FOREIGN KEY (pet_id) REFERENCES pets (id);
CREATE INDEX visits_pet_id ON visits (pet_id);

CREATE TABLE cases (
                      id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                      name       VARCHAR(255),
                      email VARCHAR_IGNORECASE(30)
);
CREATE INDEX cases_name ON cases (name);

CREATE TABLE arguments (
                    id         INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                    name       VARCHAR(255),
                    premise       VARCHAR(255),
                    predicate       VARCHAR(255),
                    warrant       VARCHAR(255),
                    ordering    INTEGER,
                    type_id    INTEGER,
                    case_id   INTEGER,
                    parent_id INTEGER
);
ALTER TABLE arguments ADD CONSTRAINT fk_arguments_case FOREIGN KEY (case_id) REFERENCES cases (id);
ALTER TABLE arguments ADD CONSTRAINT fk_arguments_argument FOREIGN KEY (parent_id) REFERENCES arguments (id);

CREATE TABLE evaluations (
                    id         INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                    verification_status VARCHAR(30),
                    intensity  VARCHAR(30),
                    argument_id INTEGER
);
ALTER TABLE evaluations ADD CONSTRAINT fk_evaluations_argument FOREIGN KEY (argument_id) REFERENCES arguments (id);
create table ARGUMENT_TYPES (
  ID INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  NAME VARCHAR(255)
);

CREATE SEQUENCE id_seq START WITH 100 INCREMENT BY 1;
