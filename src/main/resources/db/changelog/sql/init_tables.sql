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
    role VARCHAR(50) CHECK (role IN ('ADMIN', 'EMPLOYER', 'APPLICANT'))
);

INSERT INTO role (id, role)
VALUES (1, 'ADMIN'),
       (2, 'EMPLOYER'),
       (3, 'APPLICANT');

CREATE TABLE user_role
(
    user_id BIGINT REFERENCES app_user (id),
    role_id INTEGER REFERENCES role (id),
    PRIMARY KEY (user_id, role_id)
);
