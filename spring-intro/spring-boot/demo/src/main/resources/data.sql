insert into perm_app (id, name) values (1, 'create-user');
insert into perm_app (id, name) values (2, 'create-user');
insert into perm_app (id, name) values (3, 'create-user');
insert into perm_app (id, name) values (4, 'create-user');
insert into perm_app (id, name) values (5, 'create-user');

insert into role_app (id, name) values (1, 'admin');

insert into role_permission (role_id, perm_id) values (1,1);
insert into role_permission (role_id, perm_id) values (1,2);
insert into role_permission (role_id, perm_id) values (1,3);
insert into role_permission (role_id, perm_id) values (1,5);

insert into user_app (id, name, username, password) values(1,'Alejandro', 'amunoz', 'password');

insert into user_role (user_id, role_id) values (1,1);

