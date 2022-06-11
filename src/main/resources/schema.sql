DROP TABLE app_users;
DROP TABLE app_roles;
DROP TABLE app_user_roles;
CREATE TABLE app_users (
  id BIGINT NOT NULL AUTO_INCREMENT,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  phone VARCHAR(255) NOT NULL,
  enabled BOOLEAN NOT NULL,
  primary key (id)
  );
CREATE TABLE app_roles (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  primary key (id)
  );
CREATE TABLE app_user_roles (
    user_id BIGINT NOT NULL,
    role_id INT NOT NULL,
    primary key (user_id, role_id),
    foreign key (user_id) references app_users(id),
    foreign key (role_id) references app_roles(id)
    );
INSERT INTO app_roles (name) VALUES ('ROLE_USER');
INSERT INTO app_roles (name) VALUES ('ROLE_ADMIN');
