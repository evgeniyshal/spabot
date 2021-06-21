INSERT INTO roles (id, authority)
SELECT '1', 'ROLE_ADMIN'
WHERE NOT EXISTS (SELECT * FROM roles WHERE authority='ROLE_ADMIN');

INSERT INTO roles (id, authority)
SELECT '2', 'ROLE_USER'
WHERE NOT EXISTS (SELECT * FROM roles WHERE authority='ROLE_USER');