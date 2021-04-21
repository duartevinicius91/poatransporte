CREATE TABLE linha (
   id INTEGER,
   codigo VARCHAR(10) NOT NULL,
   nome VARCHAR(100) NOT NULL,
   CONSTRAINT pk_linha PRIMARY KEY (id)
);

CREATE TABLE itinerario (
    id INTEGER,
    lat NUMERIC(15, 12) NOT NULL,
    lng NUMERIC(15, 12) NOT NULL,
    idlinha INTEGER,
    CONSTRAINT pk_itinerario PRIMARY KEY (id),
    CONSTRAINT fk_itinerario_linha FOREIGN KEY (idlinha)
        REFERENCES linha (id)
);
