CREATE TABLE status (
    id SERIAL PRIMARY KEY,
    status VARCHAR(50) NOT NULL
);

INSERT INTO status (status) VALUES
('INVITE'),
('DECLINE'),
('WAITING'),
('REMOVE'),
('ARCHIVE');

CREATE TABLE permission (
    id SERIAL PRIMARY KEY,
    permission VARCHAR(50) NOT NULL
);

INSERT INTO permission (permission) VALUES
('PRIMARY'),
('HR');

CREATE TABLE reply (
    id BIGSERIAL PRIMARY KEY,
    applicant_id INTEGER NOT NULL,
    vacancy_id INTEGER NOT NULL,
    status_id INTEGER NOT NULL,
    FOREIGN KEY (applicant_id) REFERENCES app_user (id),
    FOREIGN KEY (vacancy_id) REFERENCES vacancy (id),
    FOREIGN KEY (status_id) REFERENCES status (id)
);

CREATE TABLE employer_manage (
    id BIGSERIAL PRIMARY KEY,
    app_user_id INTEGER NOT NULL,
    employer_id INTEGER NOT NULL,
    permission_id INTEGER NOT NULL,
    FOREIGN KEY (app_user_id) REFERENCES app_user (id),
    FOREIGN KEY (employer_id) REFERENCES employer (id),
    FOREIGN KEY (permission_id) REFERENCES permission (id)
);