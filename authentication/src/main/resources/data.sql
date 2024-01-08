
INSERT INTO role (role)
VALUES ('ROLE_USER'), ('ROLE_ADMIN');

INSERT INTO users (name,lastname,email,password,state)
VALUES ('Matias', 'Torrez', 'matias.torrez@davinci.edu.ar', '$2a$10$CCqTBmFQk8QE5Df5MhG7j.lMPq.IKIOfXxHoAjmcQPLS4BbCdat0e', 'REGISTRADO');

INSERT INTO user_roles (fk_role, fk_user)
VALUES (1,1);

