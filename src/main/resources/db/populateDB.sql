DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (date_time, description, calories, user_id)
VALUES (to_timestamp('30.01.2020 10:00', 'DD.MM.YYYY HH24:MI')::timestamp without time zone, 'Завтрак', 500, 100000),
       (to_timestamp('30.01.2020 13:00', 'DD.MM.YYYY HH24:MI')::timestamp without time zone, 'Обед', 1000, 100000),
       (to_timestamp('30.01.2020 20:00', 'DD.MM.YYYY HH24:MI')::timestamp without time zone, 'Ужин', 500, 100000),
       (to_timestamp('31.01.2020 00:00', 'DD.MM.YYYY HH24:MI')::timestamp without time zone, 'Еда на граничное значение', 100, 100001),
       (to_timestamp('31.01.2020 10:00', 'DD.MM.YYYY HH24:MI')::timestamp without time zone, 'Завтрак', 1000, 100001),
       (to_timestamp('31.01.2020 13:00', 'DD.MM.YYYY HH24:MI')::timestamp without time zone, 'Обед', 500, 100001),
       (to_timestamp('31.01.2020 20:00', 'DD.MM.YYYY HH24:MI')::timestamp without time zone, 'Ужин', 410, 100001);