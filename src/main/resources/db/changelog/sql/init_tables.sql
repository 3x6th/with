CREATE TABLE applicant
(
    id          BIGSERIAL PRIMARY KEY,
    first_name  VARCHAR(50),
    second_name VARCHAR(50),
    email       VARCHAR(50)
);

CREATE TABLE employer
(
    id           BIGSERIAL PRIMARY KEY,
    company_name VARCHAR(255) NOT NULL,
    website      VARCHAR(50),
    description  TEXT,
    location     VARCHAR(255),
    email        VARCHAR(50)
);

CREATE TABLE role
(
    id   INTEGER PRIMARY KEY,
    role VARCHAR(50) CHECK (role IN ('ROLE_ADMIN', 'ROLE_EMPLOYER', 'ROLE_APPLICANT', 'ROLE_DEFAULT'))
);

CREATE TABLE app_user
(
    id       BIGSERIAL PRIMARY KEY,
    login    VARCHAR(50),
    email    VARCHAR(50),
    password VARCHAR(255),
    email_verified BOOLEAN DEFAULT FALSE,
    auth_provider VARCHAR(15),
    image_url TEXT,
    role_id INTEGER REFERENCES role (id)
);

INSERT INTO role (id, role)
VALUES (1, 'ROLE_ADMIN'),
       (2, 'ROLE_EMPLOYER'),
       (3, 'ROLE_APPLICANT'),
       (4, 'ROLE_DEFAULT');

CREATE TABLE vacancy
(
    id          BIGSERIAL PRIMARY KEY,
    title       VARCHAR(255) NOT NULL,
    description TEXT,
    salary      VARCHAR(50),
    work_mode   VARCHAR(50),
    location    VARCHAR(255),
    employer_id BIGINT REFERENCES employer (id)
);

CREATE TABLE tag
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE vacancy_tag
(
    vacancy_id BIGINT REFERENCES vacancy (id),
    tag_id     BIGINT REFERENCES tag (id),
    PRIMARY KEY (vacancy_id, tag_id)
);


-- Insert employers
INSERT INTO employer (company_name, website, description, location, email)
VALUES
    ('Tech Innovations', 'http://techinnovations.com', 'Leading tech company specializing in innovative solutions.', 'San Francisco, CA', 'info@techinnovations.com'),
    ('Green Solutions', 'http://greensolutions.com', 'Dedicated to making the world a greener place through technology.', 'Portland, OR', 'contact@greensolutions.com'),
    ('Health Plus', 'http://healthplus.com', 'Focused on delivering quality healthcare solutions.', 'New York, NY', 'support@healthplus.com'),
    ('Gadget Galaxy', 'http://gadgetgalaxy.com', 'Your ultimate destination for gadgets and tech reviews.', 'Los Angeles, CA', 'hello@gadgetgalaxy.com'),
    ('Finance First', 'http://financefirst.com', 'Pioneering new ways to manage your personal finance.', 'Chicago, IL', 'service@financefirst.com');


-- Insert tags
INSERT INTO tag (name)
VALUES ('Java');
INSERT INTO tag (name)
VALUES ('Spring Boot');
INSERT INTO tag (name)
VALUES ('SQL');
INSERT INTO tag (name)
VALUES ('Microservices');

-- Insert vacancies
INSERT INTO vacancy (title, description, salary, work_mode, location, employer_id)
VALUES ('Java Developer', 'Develop and maintain Java applications', '2000', 'Remote', 'New York', 1);
INSERT INTO vacancy (title, description, salary, work_mode, location, employer_id)
VALUES ('Backend Developer', 'Work on backend services', '2500', 'On-site', 'San Francisco', 2);
INSERT INTO vacancy (title, description, salary, work_mode, location, employer_id)
VALUES ('Full Stack Developer', 'Develop full stack applications', '3000', 'Hybrid', 'Chicago', 3);
INSERT INTO vacancy (title, description, salary, work_mode, location, employer_id)
VALUES ('Frontend Developer', 'Develop and maintain frontend applications', '2200', 'Remote', 'New York', 1);
INSERT INTO vacancy (title, description, salary, work_mode, location, employer_id)
VALUES ('DevOps Engineer', 'Manage CI/CD pipelines and infrastructure', '2700', 'On-site', 'San Francisco', 2);
INSERT INTO vacancy (title, description, salary, work_mode, location, employer_id)
VALUES ('Data Scientist', 'Analyze and interpret complex data', '3200', 'Hybrid', 'Chicago', 3);

-- Insert vacancy_tag relationships
INSERT INTO vacancy_tag (vacancy_id, tag_id)
VALUES (1, 1); -- Java Developer - Java
INSERT INTO vacancy_tag (vacancy_id, tag_id)
VALUES (1, 2); -- Java Developer - Spring Boot
INSERT INTO vacancy_tag (vacancy_id, tag_id)
VALUES (2, 3); -- Backend Developer - SQL
INSERT INTO vacancy_tag (vacancy_id, tag_id)
VALUES (2, 4); -- Backend Developer - Microservices
INSERT INTO vacancy_tag (vacancy_id, tag_id)
VALUES (3, 1); -- Full Stack Developer - Java
INSERT INTO vacancy_tag (vacancy_id, tag_id)
VALUES (3, 2); -- Full Stack Developer - Spring Boot
INSERT INTO vacancy_tag (vacancy_id, tag_id)
VALUES (3, 3); -- Full Stack Developer - SQL
INSERT INTO vacancy_tag (vacancy_id, tag_id)
VALUES (4, 1); -- Frontend Developer - Java
INSERT INTO vacancy_tag (vacancy_id, tag_id)
VALUES (4, 2); -- Frontend Developer - Spring Boot
INSERT INTO vacancy_tag (vacancy_id, tag_id)
VALUES (5, 3); -- DevOps Engineer - SQL
INSERT INTO vacancy_tag (vacancy_id, tag_id)
VALUES (5, 4); -- DevOps Engineer - Microservices
INSERT INTO vacancy_tag (vacancy_id, tag_id)
VALUES (6, 1); -- Data Scientist - Java
INSERT INTO vacancy_tag (vacancy_id, tag_id)
VALUES (6, 2); -- Data Scientist - Spring Boot
INSERT INTO vacancy_tag (vacancy_id, tag_id)
VALUES (6, 3); -- Data Scientist - SQL