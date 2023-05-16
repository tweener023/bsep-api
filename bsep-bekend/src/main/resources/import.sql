
--activated profiles
insert into users (email, password, username, first_name, last_name,address, city, country, phone_number, title, is_approved) values ('admin@email.com', '$2a$10$iwMIUYsBRR4n5dHJi7ZCUeeeDYgBqrt8dyVCUHvbKju6fKdI.Q2UC', 'admin', 'mile', 'milic', 'Milentija Popovica 4', 'Novi Sad', 'Srbija', '0611759488', 'dipl. inzenjer elektrotehnike i racunarstva', 'true');
insert into users (email, password, username, first_name, last_name,address, city, country, phone_number, title, is_approved) values ('engineer@email.com', '$2a$10$iwMIUYsBRR4n5dHJi7ZCUeeeDYgBqrt8dyVCUHvbKju6fKdI.Q2UC', 'engineer', 'pera', 'peric', 'Milentija Popovica 4', 'Novi Sad', 'Srbija', '0611759488', 'dipl. inzenjer elektrotehnike i racunarstva', 'true');
insert into users (email, password, username, first_name, last_name,address, city, country, phone_number, title, is_approved) values ('user@email.com', '$2a$10$iwMIUYsBRR4n5dHJi7ZCUeeeDYgBqrt8dyVCUHvbKju6fKdI.Q2UC', 'user', 'zika', 'zikic', 'Milentija Popovica 4', 'Novi Sad', 'Srbija', '0611759488', 'dipl. inzenjer elektrotehnike i racunarstva', 'true');

--unactivated profiles
insert into users (email, password, username, first_name, last_name,address, city, country, phone_number, title, is_approved) values ('user2@email.com', '$2a$10$iwMIUYsBRR4n5dHJi7ZCUeeeDYgBqrt8dyVCUHvbKju6fKdI.Q2UC', 'user2', 'zivorad', 'zikicev', 'Milentija Popovica 4', 'Novi Sad', 'Srbija', '0611759488', 'dipl. inzenjer ', 'false');
insert into users (email, password, username, first_name, last_name,address, city, country, phone_number, title, is_approved) values ('user3@email.com', '$2a$10$iwMIUYsBRR4n5dHJi7ZCUeeeDYgBqrt8dyVCUHvbKju6fKdI.Q2UC', 'user3', 'milorad', 'jovanov', 'Milentija Popovica 4', 'Novi Sad', 'Srbija', '0611759488', 'dipl. inzenjer ', 'false');
insert into users (email, password, username, first_name, last_name,address, city, country, phone_number, title, is_approved) values ('user4@email.com', '$2a$10$iwMIUYsBRR4n5dHJi7ZCUeeeDYgBqrt8dyVCUHvbKju6fKdI.Q2UC', 'user4', 'tihomir', 'milivojev', 'Milentija Popovica 4', 'Novi Sad', 'Srbija', '0611759488', 'dipl. inzenjer ', 'false');
insert into users (email, password, username, first_name, last_name,address, city, country, phone_number, title, is_approved) values ('user5@email.com', '$2a$10$iwMIUYsBRR4n5dHJi7ZCUeeeDYgBqrt8dyVCUHvbKju6fKdI.Q2UC', 'user5', 'stevo', 'kajtez', 'Milentija Popovica 4', 'Novi Sad', 'Srbija', '0611759488', 'dipl. inzenjer ', 'false');

insert into roles ( name ) values ('ROLE_USER');
insert into roles ( name ) values ('ROLE_ENGINEER');
insert into roles ( name ) values ('ROLE_ADMIN');

--activated profiles roles
insert into user_roles(user_id,role_id) values (1,3);
insert into user_roles(user_id,role_id) values (2,2);
insert into user_roles(user_id,role_id) values (3,1);

--unactivated profiles roles
insert into user_roles(user_id,role_id) values (4,1);
insert into user_roles(user_id,role_id) values (5,1);
insert into user_roles(user_id,role_id) values (6,1);
insert into user_roles(user_id,role_id) values (7,1);

--skills for engineer
insert into skills ( skill_name,skill_level, user_id, is_deleted) values ('React Native', 'Beginner', '2','false');
insert into skills ( skill_name,skill_level, user_id, is_deleted) values ('Spring Security', 'Expert','2','false');
insert into skills ( skill_name,skill_level, user_id, is_deleted) values ('C#', 'Advanced','2','false');


