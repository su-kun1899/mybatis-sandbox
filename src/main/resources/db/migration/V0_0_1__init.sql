CREATE TABLE parent (
  id   CHAR(36) PRIMARY KEY,
  name VARCHAR(128) NOT NULL,
  UNIQUE (name)
);

CREATE TABLE child (
  id        CHAR(36) PRIMARY KEY,
  parent_id CHAR(36)     NOT NULL REFERENCES parent (id),
  name      VARCHAR(128) NOT NULL,
  UNIQUE (parent_id, name)
);
