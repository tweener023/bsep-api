insert into users (email, password, username, first_name, last_name,address, city, country, phone_number, title, is_approved) values ('admin@email.com', '$2a$10$iwMIUYsBRR4n5dHJi7ZCUeeeDYgBqrt8dyVCUHvbKju6fKdI.Q2UC', 'admin', 'mile', 'milic', 'Milentija Popovica 4', 'Novi Sad', 'Srbija', '0611759488', 'dipl. inzenjer elektrotehnike i racunarstva', 'true');
insert into users (email, password, username, first_name, last_name,address, city, country, phone_number, title, is_approved) values ('engineer@email.com', '$2a$10$iwMIUYsBRR4n5dHJi7ZCUeeeDYgBqrt8dyVCUHvbKju6fKdI.Q2UC', 'engineer', 'pera', 'peric', 'Milentija Popovica 4', 'Novi Sad', 'Srbija', '0611759488', 'dipl. inzenjer elektrotehnike i racunarstva', 'true');
insert into users (email, password, username, first_name, last_name,address, city, country, phone_number, title, is_approved) values ('user@email.com', '$2a$10$iwMIUYsBRR4n5dHJi7ZCUeeeDYgBqrt8dyVCUHvbKju6fKdI.Q2UC', 'user', 'zika', 'zikic', 'Milentija Popovica 4', 'Novi Sad', 'Srbija', '0611759488', 'dipl. inzenjer elektrotehnike i racunarstva', 'true');

insert into users (email, password, username, first_name, last_name,address, city, country, phone_number, title, is_approved) values ('user2@email.com', '$2a$10$iwMIUYsBRR4n5dHJi7ZCUeeeDYgBqrt8dyVCUHvbKju6fKdI.Q2UC', 'user2', 'zivorad', 'zikicev', 'Milentija Popovica 4', 'Novi Sad', 'Srbija', '0611759488', 'dipl. inzenjer elektrotehnike i racunarstva', 'false');




insert into roles ( name ) values ('ROLE_USER');
insert into roles ( name ) values ('ROLE_ENGINEER');
insert into roles ( name ) values ('ROLE_ADMIN');

insert into user_roles(user_id,role_id) values (1,3);
insert into user_roles(user_id,role_id) values (2,2);
insert into user_roles(user_id,role_id) values (3,1);