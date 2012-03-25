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


  
CREATE TABLE comments
(
  user_id integer,
  commentable_id integer,
  commentable_type character varying(50),
  content character varying(1024),
  id integer PRIMARY KEY AUTOINCREMENT
);