INSERT INTO ROLE VALUES (1, 'ADMIN');
INSERT INTO ROLE VALUES (2, 'USER');

INSERT INTO user (id, name, password, active) VALUES (1, 'admin', '$2a$10$HhytyB2kMtayY1kXiqUdzeGGcrtvynWxT/JVZjoTUXdPwD34W8GGS', 1);

INSERT INTO user_role (user_id, role_id) VALUES (1, 2);