USE spring_security_cooperate;
INSERT INTO users(id, password, user_name) VALUES (1, '$2a$12$V5sBty4mvHQ503JjOmBiE.b37Ddi8M3rxIDiPt6YNURbQ0ztXyONu', 'longtq');
INSERT INTO roles(id, name) VALUES (1, 'USER');
INSERT INTO user_role(user_id, role_id) VALUES (1,1);
// default-password: password
