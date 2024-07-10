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
    company_name VARCHAR(50),
    email        VARCHAR(50)
);

CREATE TABLE app_user
(
    id       BIGSERIAL PRIMARY KEY,
    login    VARCHAR(50),
    password VARCHAR(50)
);

CREATE TABLE role
(
    id   INTEGER PRIMARY KEY,
    role VARCHAR(50) CHECK (role IN ('ROLE_ADMIN', 'ROLE_EMPLOYER', 'ROLE_APPLICANT'))
);

INSERT INTO role (id, role)
VALUES (1, 'ROLE_ADMIN'),
       (2, 'ROLE_EMPLOYER'),
       (3, 'ROLE_APPLICANT');

CREATE TABLE user_role
(
    user_id BIGINT REFERENCES app_user (id),
    role_id INTEGER REFERENCES role (id),
    PRIMARY KEY (user_id, role_id)
);

CREATE TABLE vacancy
(
    id          BIGSERIAL PRIMARY KEY,
    title       VARCHAR(255) NOT NULL,
    description TEXT,
    salary      VARCHAR(50),
    work_mode    VARCHAR(50),
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
