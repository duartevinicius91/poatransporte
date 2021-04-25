CREATE TABLE linha (
   id SERIAL,
   codigo VARCHAR(10) NOT NULL,
   nome VARCHAR(100) NOT NULL,
   CONSTRAINT pk_linha PRIMARY KEY (id)
);

CREATE TABLE itinerario (
    id SERIAL,
    lat NUMERIC(15, 12) NOT NULL,
    lng NUMERIC(15, 12) NOT NULL,
    id_linha INTEGER NOT NULL,
    CONSTRAINT pk_itinerario PRIMARY KEY (id),
    CONSTRAINT fk_itinerario_linha FOREIGN KEY (id_linha)
        REFERENCES linha (id)
);
