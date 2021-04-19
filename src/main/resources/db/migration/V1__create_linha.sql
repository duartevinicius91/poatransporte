CREATE TABLE linha (
  id  SERIAL CONSTRAINT pk_linha PRIMARY KEY,
  codigo VARCHAR(10) UNIQUE NOT NULL,
  nome VARCHAR(50) NOT NULL
);

CREATE TABLE itinerario (
   id SERIAL CONSTRAINT pk_itinerario PRIMARY KEY,
   lat NUMERIC(15, 12) NOT NULL,
   lng NUMERIC(15, 12) NOT NULL,
   idlinha INTEGER,
   CONSTRAINT FOREIGN KEY fk_itinerario_linha
       REFERENCES linha (id)
);
