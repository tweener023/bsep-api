insert into users (email, password, username) values ('admin@email.com', '$2a$10$iwMIUYsBRR4n5dHJi7ZCUeeeDYgBqrt8dyVCUHvbKju6fKdI.Q2UC', 'admin');
insert into users (email, password, username) values ('engineer@email.com', '$2a$10$iwMIUYsBRR4n5dHJi7ZCUeeeDYgBqrt8dyVCUHvbKju6fKdI.Q2UC', 'engineer');
insert into users (email, password, username) values ('user@email.com', '$2a$10$iwMIUYsBRR4n5dHJi7ZCUeeeDYgBqrt8dyVCUHvbKju6fKdI.Q2UC', 'user');


insert into roles ( name ) values ('ROLE_USER');
insert into roles ( name ) values ('ROLE_ENGINEER');
insert into roles ( name ) values ('ROLE_ADMIN');

insert into user_roles(user_id,role_id) values (1,3);
insert into user_roles(user_id,role_id) values (2,2);
insert into user_roles(user_id,role_id) values (3,1);