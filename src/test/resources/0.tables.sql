CREATE TABLE users
(
  id integer PRIMARY KEY AUTOINCREMENT,
  email character varying(50),
  username character varying(50),
  password character varying(50)
);

CREATE TABLE hotels
(
  id integer PRIMARY KEY AUTOINCREMENT,
  description character varying(50),
  name character varying(50)
);