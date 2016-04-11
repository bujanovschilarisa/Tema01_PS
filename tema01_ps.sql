CREATE DATABASE tema01_ps;
USE tema01_ps;

-- drop database tema01_ps;

CREATE TABLE IF NOT EXISTS show_event (
  id INT UNIQUE AUTO_INCREMENT PRIMARY KEY,
  name_show VARCHAR(45) UNIQUE NOT NULL,
  regie VARCHAR(45) NOT NULL,
  distributie VARCHAR(100) NOT NULL,
  data_show DATE NOT NULL,
  number_of_tickets INT NOT NULL,
  sters boolean NOT NULL
);

-- DELETE FROM show_event WHERE name_show = 'MEMO';

CREATE TABLE IF NOT EXISTS ticket_event (
  id INT UNIQUE AUTO_INCREMENT PRIMARY KEY,
  nume_show VARCHAR(50) NOT NULL,
  numar_rezervat INT NOT NULL,
  rand_rezervat INT NOT NULL,
  INDEX(nume_show),
  FOREIGN KEY (nume_show) REFERENCES show_event(name_show)
);

CREATE TABLE IF NOT EXISTS user (
  id INT UNIQUE AUTO_INCREMENT PRIMARY KEY,
  name_user VARCHAR(45) UNIQUE NOT NULL,
  password_user VARCHAR(100) NOT NULL,
  username VARCHAR(45) UNIQUE NOT NULL,
  rol VARCHAR(45) NOT NULL
);

-- UPDATE show_event SET regie = 'a' WHERE name_show = 'MEMO';

INSERT INTO show_event (name_show, regie, distributie, data_show, number_of_tickets, sters) 
VALUES
('MEMO', 'Mona Marian', 'Ea: Miriam Cuibus; El: Dragos Pop', '2000/07/10', 100, false),
('12 OAMENI FURIOSI', 'Tudor Lucanu', 'Juratul 1: Ovidiu Crisan; Juratul 2: Ruslan Barlea; Ofiterul: Doru Bodrea', '2015/02/11', 50, false),
('ATENTIE, CAD INGERII!', 'Medeea Iancu', 'one-woman show cu: Elena Ivanca', '2013/11/22', 500, false);

INSERT INTO ticket_event (nume_show, numar_rezervat, rand_rezervat) 
VALUES
('MEMO', 10, 5),
('MEMO', 10, 4),
('MEMO', 11, 5),
('12 OAMENI FURIOSI', 9, 10),
('12 OAMENI FURIOSI', 10, 4),
('ATENTIE, CAD INGERII!', 11, 5);

INSERT INTO user (name_user, password_user, username, rol) 
VALUES
('Bujanovschi Larisa', '6b1af9a521ff4a6c7942cea27193f001', 'bujanovschi_larisa','admin'),
('Boar Eleonora', '74da8112bef4f25601169ba63fca3244', 'boar_eleonora','employee'),
('Huet Monica', 'f854b924e1621ba254e4da6c59be8506', 'huet_monica','employee');