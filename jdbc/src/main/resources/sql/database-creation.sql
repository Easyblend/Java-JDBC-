CREATE TABLE genre (
  idgenre INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  PRIMARY KEY (idgenre));
  
CREATE TABLE movie (
  idmovie INT NOT NULL AUTO_INCREMENT,
  title VARCHAR(100) NOT NULL,
  release_date DATETIME NULL,
  genre_id INT NOT NULL,
  duration INT NULL,
  director VARCHAR(100) NOT NULL,
  summary MEDIUMTEXT NULL,
  PRIMARY KEY (idmovie),
  INDEX genre_fk_idx (genre_id ASC),
  CONSTRAINT genre_fk FOREIGN KEY (genre_id) REFERENCES genre (idgenre));
