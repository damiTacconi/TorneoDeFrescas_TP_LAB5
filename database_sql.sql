CREATE DATABASE torneodefrescas;

CREATE TABLE resultados(

id_resultado INT AUTO_INCREMENT NOT NULL,
nombre_ganador VARCHAR(50) NOT NULL,
puntos_cerveza INT NOT NULL,

CONSTRAINT pk_id_resultado PRIMARY KEY (id_resultado)
);
