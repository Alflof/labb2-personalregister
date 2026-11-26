-- Skapa databas och användare
CREATE database personalregister;
CREATE USER 'lab2_user'@'localhost' IDENTIFIED BY '<lösenord>';
GRANT CREATE, SELECT, INSERT, UPDATE, DELETE, REFERENCES ON personalregister.* TO 'lab2_user'@'localhost';
FLUSH PRIVILEGES;

-- Verifiera användaren
SELECT CURRENT_USER();

-- Skapa alla tabeller med attribut, nycklar, och regler
CREATE TABLE department
(
    department_id   INT AUTO_INCREMENT PRIMARY KEY,
    department_name VARCHAR(30) NOT NULL,
    city            VARCHAR(30) NOT NULL
);

CREATE TABLE employee
(
    employee_id   INT AUTO_INCREMENT PRIMARY KEY,
    first_name    VARCHAR(30)  NOT NULL,
    last_name     VARCHAR(30)  NOT NULL,
    email         VARCHAR(100) NOT NULL UNIQUE,
    start_date    DATE         NOT NULL,
    department_id INT          NOT NULL,
    FOREIGN KEY (department_id) REFERENCES department (department_id)
);

CREATE TABLE keycard
(
    keycard_id  INT AUTO_INCREMENT PRIMARY KEY,
    card_number INT  NOT NULL UNIQUE,
    issue_date  DATE NOT NULL,
    expiry_date DATE NOT NULL,
    employee_id INT  NOT NULL UNIQUE,
    FOREIGN KEY (employee_id) REFERENCES employee (employee_id)
);

CREATE TABLE skill
(
    skill_id          INT AUTO_INCREMENT PRIMARY KEY,
    skill_name        VARCHAR(30) UNIQUE NOT NULL,
    skill_description VARCHAR(200)       NOT NULL
);

CREATE TABLE employee_skill
(
    employee_id INT NOT NULL,
    skill_id    INT NOT NULL,
    PRIMARY KEY (employee_id, skill_id),
    FOREIGN KEY (employee_id) REFERENCES employee (employee_id),
    FOREIGN KEY (skill_id) REFERENCES skill (skill_id)
);

-- Skapa testdata i alla tabeller
INSERT INTO department (department_name, city)
VALUES ('Human Resources', 'Lund'),
       ('Level Design', 'Stockholm'),
       ('Economy', 'Skellefteå'),
       ('Ballistics', 'Uddevalla'),
       ('Accounts', 'London');

INSERT INTO employee (first_name, last_name, email, start_date, department_id)
VALUES ('Angelica', 'Borg', 'anbo@email.se', '2011-04-03', 1),
       ('Anders', 'Cameron', 'anca@email.se', '2011-07-02', 1),
       ('Ahmed', 'Beir', 'ahbe@email.se', '2013-07-02', 1),
       ('Amina', 'Hassan', 'amha@email.se', '2022-03-14', 2),
       ('Mateo', 'Silva', 'masi@email.se', '2023-08-01', 3),
       ('Mei-Ling', 'Chen', 'mech@email.se', '2018-02-03', 4);

INSERT INTO keycard (card_number, issue_date, expiry_date, employee_id)
VALUES (123, '2023-04-06', '2028-04-06', 1),
       (456, '2024-06-08', '2029-06-08', 2),
       (789, '2024-09-02', '2029-09-02', 5);

INSERT INTO skill (skill_name, skill_description)
VALUES ('SQL', 'Hantering av relationsdatabaser'),
       ('Java', 'Erfarenhet av programmeringsspråket'),
       ('Elgitarr', 'Kan spela Stairway to Heaven'),
       ('Matlagning', 'Fixa lunch till kontoret'),
       ('Nivådesign', 'Skapa utmaningar och bra användarupplevelse'),
       ('Budgetering', 'Ta hand om pengar');

INSERT INTO employee_skill (employee_id, skill_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 1),
       (3, 4),
       (4, 5),
       (5, 5);

-- G1
SELECT first_name, last_name, department_name
FROM employee
         INNER JOIN department ON employee.department_id = department.department_id;

-- G2
SELECT department_name, first_name, last_name
FROM department
         LEFT JOIN employee ON department.department_id = employee.department_id;

-- VG1
SELECT first_name, last_name, department_name, card_number
FROM employee
         INNER JOIN department ON employee.department_id = department.department_id
         LEFT JOIN keycard ON employee.employee_id = keycard.employee_id;

-- VG2
SELECT department_name, first_name, last_name, card_number
FROM department
         LEFT JOIN employee ON department.department_id = employee.department_id
         LEFT JOIN keycard ON employee.employee_id = keycard.employee_id;

-- VG3
-- En anställds samlade uppgifter
SELECT employee.employee_id,
       first_name,
       last_name,
       email,
       start_date,
       employee.department_id,
       department_name,
       city,
       keycard_id,
       card_number,
       issue_date,
       expiry_date,
       skill_name,
       skill_description
FROM employee
         LEFT JOIN department ON employee.department_id = department.department_id
         LEFT JOIN keycard ON employee.employee_id = keycard.employee_id
         LEFT JOIN employee_skill ON employee.employee_id = employee_skill.employee_id
         LEFT JOIN skill ON skill.skill_id = employee_skill.skill_id
WHERE employee.employee_id = 1;

-- Anställd utan passerkort
SELECT employee.employee_id,
       first_name,
       last_name,
       email,
       start_date,
       employee.department_id,
       department_name,
       city,
       keycard_id,
       card_number,
       issue_date,
       expiry_date,
       skill_name,
       skill_description
FROM employee
         LEFT JOIN department ON employee.department_id = department.department_id
         LEFT JOIN keycard ON employee.employee_id = keycard.employee_id
         LEFT JOIN employee_skill ON employee.employee_id = employee_skill.employee_id
         LEFT JOIN skill ON skill.skill_id = employee_skill.skill_id
WHERE employee.employee_id = 3;

-- Anställd utan kompetenser
SELECT employee.employee_id,
       first_name,
       last_name,
       email,
       start_date,
       employee.department_id,
       department_name,
       city,
       keycard_id,
       card_number,
       issue_date,
       expiry_date,
       skill_name,
       skill_description
FROM employee
         LEFT JOIN department ON employee.department_id = department.department_id
         LEFT JOIN keycard ON employee.employee_id = keycard.employee_id
         LEFT JOIN employee_skill ON employee.employee_id = employee_skill.employee_id
         LEFT JOIN skill ON skill.skill_id = employee_skill.skill_id
WHERE employee.employee_id = 6;

-- VG4
-- INNER
SELECT department_name, first_name, last_name
FROM department
         INNER JOIN employee ON department.department_id = employee.department_id;
-- LEFT
SELECT department_name, first_name, last_name
FROM department
         LEFT JOIN employee ON department.department_id = employee.department_id;