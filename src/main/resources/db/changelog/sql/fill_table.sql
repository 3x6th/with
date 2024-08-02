--truncate table app_user cascade reply cascade employer_manage;
INSERT INTO app_user (login, email, password, email_verified, auth_provider, image_url, role_id) VALUES
('tech_innovations_work', 'tech_innovations_work@example.com', '$2a$10$2N8HiWnsO6SQtoewAsFxEuOzz1mfa4l5w8Nazk2eYh2gL63z.Qaze','false', 'local', '', '2'), --pass: tech_innovations_secret
('green_solutions_work', 'green_solutions_work@example.com', '$2a$10$02NDEwO2E8qbKuUnypblBOfOzolsdi.EI2eKIBVwLeTzNjxMSiNLK','false', 'local', '', '2'), --pass: green_solutions_secret
('health_plus_work', 'health_plus_work@example.com', '$2a$10$M0zlxPZUCI/rdWx8zOXsUu0FjDdiKoj542HDwrWMu8Q/bSyv/n5Fu','false', 'local', '', '2'), --pass: health_plus_secret
('gadget_galaxy_work', 'gadget_galaxy_work@example.com', '$2a$10$IZOhDFckKgObx0Gyg5evtubogG4KcXJu2DJKl9Ho7mlrca3tjelvK','false', 'local', '', '2'), --pass: gadget_galaxy_secret
('finance_first_work', 'finance_first_work@example.com', '$2a$10$fTfqTBtVaOxBhAVqM0vaROWoDlRR1hdF22.KBFpdMhEmq8.f6bzIK','false', 'local', '', '2'), --pass: finance_first_secret
('sanya_app', 'sanya_app@example.com', '$2a$10$0dAS44oKDMFFBilXZMKTsu7jMySfEXq3FOk7nmY8toqIZJpU3.ECu','false', 'local', '', '3'), --pass: sanya_secret
('victoria_app', 'victoria_app@example.com', '$2a$10$hIni9vyRqNQY7E.D6N7pF.X/.S3.yCmvBgxaSteYgzmQNGKQdHPdG','false', 'local', '', '3'); --pass: victoria_secret

insert into employer_manage (app_user_id, employer_id, permission_id) values
('1', '1', '1'), --tech_innovations_work -> tech_innovations company -> owner
('2', '2', '1'), --Health Plus -> Health Plus company -> owner
('3', '3', '1'), --Green Solutions -> Green Solutions company -> owner
('4', '4', '1'), --Gadget Galaxy -> Gadget Galaxy company -> owner
('5', '5', '1'); --Finance First -> Finance First company -> owner

insert into reply (applicant_id, vacancy_id, status_id) values
('6', '1', '3'), --Sanya -> Java Developer -> waiting
('7', '2', '3'); --victoria -> Backend Developer -> waiting

