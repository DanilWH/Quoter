INSERT INTO usr (id, username, password, active)
    VALUES (1, 'admin', '341021dan', true);

INSERT INTO user_role (user_id, roles)
    VALUES (1, 'USER'), (1, 'ADMIN');
