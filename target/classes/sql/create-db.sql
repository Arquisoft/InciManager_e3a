--DROP TABLE TINCIDENCIAS IF EXISTS;

CREATE TABLE TINCIDENCIAS(
  id BIGINT PRIMARY KEY,
  nombre_incidencia VARCHAR(30),
  descripcion VARCHAR(200),
  latitud VARCHAR(30),
  longitud VARCHAR(30),
  estado VARCHAR(50),
  fecha_entrada DATE,
  fecha_caducidad DATE,
  id_agente BIGINT
);


CREATE TABLE TAGENTES(
  id BIGINT PRIMARY KEY,
  nombre VARCHAR(30),
  contrasena VARCHAR(30),
  latitud VARCHAR(30),
  longitud VARCHAR(30),
  email VARCHAR(50),
  fecha_entrada DATE,
  permiso_envio VARCHAR(10)
);

CREATE TABLE TCATEGORIAS(
  id BIGINT PRIMARY KEY,
  categoria VARCHAR(30),
  id_incidencia BIGINT
);

CREATE TABLE TPROPIEDADES(
  id BIGINT PRIMARY KEY,
  propiedad VARCHAR(30),
  id_incidencia BIGINT, 
  valor INTEGER
);

CREATE TABLE TUSUARIOS(
  id BIGINT PRIMARY KEY,
  nombre VARCHAR(30),
  apellidos VARCHAR(100),
  identificador VARCHAR(30),
  contrasena VARCHAR(30),
  perfil VARCHAR(30),
  email VARCHAR(50)
);