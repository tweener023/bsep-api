
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

--projects for engineer
insert into projects ( project_name,project_description, user_id, is_deleted, project_start, project_end) values ('BSEP', 'Projekat iz predmeta BSEP na cetvrtoj godini racunarstva', '2','false','2016-02-01', '2020-02-01');
insert into projects ( project_name,project_description, user_id, is_deleted, project_start, project_end) values ('ISA', 'Projekat iz predmeta ISA na cetvrtoj godini racunarstva','2','false','2016-02-01', '2020-02-01');
insert into projects ( project_name,project_description, user_id, is_deleted, project_start, project_end) values ('PSW', 'Projekat iz predmeta PSW na cetvrtoj godini racunarstva','2','false','2016-02-01','2020-02-01');

insert into permissions (can_create, can_read, can_update, can_delete, user_id) values ('true','true','true','true', 1);
insert into permissions (can_create, can_read, can_update, can_delete, user_id) values ('true','true','true','true', 2);
insert into permissions (can_create, can_read, can_update, can_delete, user_id) values ('true','true','true','true', 3);


insert into permissions (can_create, can_read, can_update, can_delete, user_id) values ('false','true','false','false', 4);
insert into permissions (can_create, can_read, can_update, can_delete, user_id) values ('false','true','false','false', 5);
insert into permissions (can_create, can_read, can_update, can_delete, user_id) values ('false','true','false','false', 6);
insert into permissions (can_create, can_read, can_update, can_delete, user_id) values ('false','true','false','false', 7);

-- Insert Fender guitar data
 insert into guitars (manufacturer_of_guitar, model_of_guitar,price, year_of_production, state_of_guitar, type_of_guitar, type_of_magnets, tuners, type_of_wood, description) values ('Fender', 'Stratocaster', 89999,2020, 'KAO_NOVO', 'ELEKTRICNA', 'HSS', 'Standard Tuners', 'Maple', 'A versatile electric guitar.');

-- Insert Gibson guitar data
insert into guitars (manufacturer_of_guitar, model_of_guitar,price, year_of_production, state_of_guitar, type_of_guitar, type_of_magnets, tuners, type_of_wood, description) values ('Gibson', 'Les Paul',234999, 1995, 'DOBRO', 'ELEKTRICNA', 'SINGL_KOIL', 'Grover Rotomatics', 'Mahogany', 'Classic rock electric guitar.');

-- Insert Taylor guitar data
insert into guitars (manufacturer_of_guitar, model_of_guitar, price,year_of_production, state_of_guitar, type_of_guitar, type_of_magnets, tuners, type_of_wood, description) values ('Taylor', '214ce', 145000,2018, 'NOVO', 'AKUSTICNA', 'PIEZO', 'Taylor Chrome', 'Spruce', 'High-quality acoustic guitar.');

-- Insert Ibanez guitar data
insert into guitars (manufacturer_of_guitar, model_of_guitar, price,year_of_production, state_of_guitar, type_of_guitar, type_of_magnets, tuners, type_of_wood, description) values ('Ibanez', 'RG550',34999, 2015, 'KAO_NOVO', 'ELEKTRICNA', 'HSS', 'Ibanez Tuners', 'Mahogany', 'Versatile electric guitar with a sleek design.');

-- Insert Martin guitar data
insert into guitars (manufacturer_of_guitar, model_of_guitar, price,year_of_production, state_of_guitar, type_of_guitar, type_of_magnets, tuners, type_of_wood, description) values ('Martin', 'D-28',349000, 2022, 'NOVO', 'AKUSTICNA', 'PIEZO', 'Martin Deluxe', 'Rosewood', 'High-quality acoustic guitar with rich tones.');

-- Insert PRS guitar data
insert into guitars (manufacturer_of_guitar, model_of_guitar,price, year_of_production, state_of_guitar, type_of_guitar, type_of_magnets, tuners, type_of_wood, description) values ('PRS', 'Custom 24', 123000,2019, 'KAO_NOVO', 'ELEKTRICNA', 'HAMBAKERI', 'PRS Phase III', 'Maple', 'Premium electric guitar with a unique design.');

-- Insert Epiphone guitar data
insert into guitars (manufacturer_of_guitar, model_of_guitar, price,year_of_production, state_of_guitar, type_of_guitar, type_of_magnets, tuners, type_of_wood, description) values ('Epiphone', 'Les Paul Standard', 23000,2021, 'KAO_NOVO', 'ELEKTRICNA', 'HAMBAKERI', 'Epiphone Deluxe', 'Mahogany', 'Affordable electric guitar with classic tones.');

insert into image_table (url_path, guitar_id) values ('https://horder-bucket.s3.eu-north-1.amazonaws.com/top-images/fender_stratocaster.jpg', 1);
insert into image_table (url_path, guitar_id) values ('https://horder-bucket.s3.eu-north-1.amazonaws.com/top-images/gibson_les_paul.jpg', 2);
insert into image_table (url_path, guitar_id) values ('https://horder-bucket.s3.eu-north-1.amazonaws.com/top-images/taylor_214ce.jpg', 3);
insert into image_table (url_path, guitar_id) values ('https://horder-bucket.s3.eu-north-1.amazonaws.com/top-images/ibanez_rg550.jpg', 4);
insert into image_table (url_path, guitar_id) values ('https://horder-bucket.s3.eu-north-1.amazonaws.com/top-images/martin_d28.jpg', 5);
insert into image_table (url_path, guitar_id) values ('https://horder-bucket.s3.eu-north-1.amazonaws.com/top-images/custom24.jpg', 6);
insert into image_table (url_path, guitar_id) values ('https://horder-bucket.s3.eu-north-1.amazonaws.com/top-images/epiphone_les_paul_standard.jpg', 7);

--fender strat see more
insert into all_image_table (url_path, guitar_id) values ('https://horder-bucket.s3.eu-north-1.amazonaws.com/images/fender-see-more-1.jpg', 1);
insert into all_image_table (url_path, guitar_id) values ('https://horder-bucket.s3.eu-north-1.amazonaws.com/images/fender-see-more-2.jpg', 1);
insert into all_image_table (url_path, guitar_id) values ('https://horder-bucket.s3.eu-north-1.amazonaws.com/images/fender-see-more-3.jpg', 1);
insert into all_image_table (url_path, guitar_id) values ('https://horder-bucket.s3.eu-north-1.amazonaws.com/images/fender-see-more-4.jpg', 1);
insert into all_image_table (url_path, guitar_id) values ('https://horder-bucket.s3.eu-north-1.amazonaws.com/images/fender-see-more-5.jpg', 1);
insert into all_image_table (url_path, guitar_id) values ('https://horder-bucket.s3.eu-north-1.amazonaws.com/images/fender-see-more-6.jpg', 1);
insert into all_image_table (url_path, guitar_id) values ('https://horder-bucket.s3.eu-north-1.amazonaws.com/images/fender-see-more-7.jpg', 1);

--gibson lp see more
insert into all_image_table (url_path, guitar_id) values ('https://horder-bucket.s3.eu-north-1.amazonaws.com/images/gibson-see-more-1.jpg', 2);
insert into all_image_table (url_path, guitar_id) values ('https://horder-bucket.s3.eu-north-1.amazonaws.com/images/gibson-see-more-2.jpg', 2);
insert into all_image_table (url_path, guitar_id) values ('https://horder-bucket.s3.eu-north-1.amazonaws.com/images/gibson-see-more-3.jpg', 2);

insert into all_image_table (url_path, guitar_id) values ('https://horder-bucket.s3.eu-north-1.amazonaws.com/images/taylor-see-more-1.jpg', 3);
insert into all_image_table (url_path, guitar_id) values ('https://horder-bucket.s3.eu-north-1.amazonaws.com/images/taylor-see-more-2.jpg', 3);
insert into all_image_table (url_path, guitar_id) values ('https://horder-bucket.s3.eu-north-1.amazonaws.com/images/taylor-see-more-3.jpg', 3);
insert into all_image_table (url_path, guitar_id) values ('https://horder-bucket.s3.eu-north-1.amazonaws.com/images/taylor-see-more-4.jpg', 3);
insert into all_image_table (url_path, guitar_id) values ('https://horder-bucket.s3.eu-north-1.amazonaws.com/images/taylor-see-more-5.jpg', 3);
insert into all_image_table (url_path, guitar_id) values ('https://horder-bucket.s3.eu-north-1.amazonaws.com/images/taylor-see-more-6.jpg', 3);

--ibanez see more
insert into all_image_table (url_path, guitar_id) values ('https://horder-bucket.s3.eu-north-1.amazonaws.com/images/ibanez-see-more-1.jpg', 4);
insert into all_image_table (url_path, guitar_id) values ('https://horder-bucket.s3.eu-north-1.amazonaws.com/images/ibanez-see-more-2.jpg', 4);
insert into all_image_table (url_path, guitar_id) values ('https://horder-bucket.s3.eu-north-1.amazonaws.com/images/ibanez-see-more-3.jpg', 4);
insert into all_image_table (url_path, guitar_id) values ('https://horder-bucket.s3.eu-north-1.amazonaws.com/images/ibanez-see-more-4.jpg', 4);

--martin see more
insert into all_image_table (url_path, guitar_id) values ('https://horder-bucket.s3.eu-north-1.amazonaws.com/images/martin-see-more-1.jpg', 5);
insert into all_image_table (url_path, guitar_id) values ('https://horder-bucket.s3.eu-north-1.amazonaws.com/images/martin-see-more-2.jpg', 5);
insert into all_image_table (url_path, guitar_id) values ('https://horder-bucket.s3.eu-north-1.amazonaws.com/images/martin-see-more-3.jpg', 5);
insert into all_image_table (url_path, guitar_id) values ('https://horder-bucket.s3.eu-north-1.amazonaws.com/images/martin-see-more-4.jpg', 5);
insert into all_image_table (url_path, guitar_id) values ('https://horder-bucket.s3.eu-north-1.amazonaws.com/images/martin-see-more-5.jpg', 5);
insert into all_image_table (url_path, guitar_id) values ('https://horder-bucket.s3.eu-north-1.amazonaws.com/images/martin-see-more-6.jpg', 5);
insert into all_image_table (url_path, guitar_id) values ('https://horder-bucket.s3.eu-north-1.amazonaws.com/images/martin-see-more-7.jpg', 5);

--prs see more
insert into all_image_table (url_path, guitar_id) values ('https://horder-bucket.s3.eu-north-1.amazonaws.com/images/prs-see-more-1.jpg', 6);
insert into all_image_table (url_path, guitar_id) values ('https://horder-bucket.s3.eu-north-1.amazonaws.com/images/prs-see-more-2.jpg', 6);
insert into all_image_table (url_path, guitar_id) values ('https://horder-bucket.s3.eu-north-1.amazonaws.com/images/prs-see-more-3.jpg', 6);
insert into all_image_table (url_path, guitar_id) values ('https://horder-bucket.s3.eu-north-1.amazonaws.com/images/prs-see-more-4.jpg', 6);


--epiphone lp see more
insert into all_image_table (url_path, guitar_id) values ('https://horder-bucket.s3.eu-north-1.amazonaws.com/images/epiphone-see-more-1.jpg', 7);
insert into all_image_table (url_path, guitar_id) values ('https://horder-bucket.s3.eu-north-1.amazonaws.com/images/epiphone-see-more-2.jpg', 7);
insert into all_image_table (url_path, guitar_id) values ('https://horder-bucket.s3.eu-north-1.amazonaws.com/images/epiphone-see-more-3.jpg', 7);
insert into all_image_table (url_path, guitar_id) values ('https://horder-bucket.s3.eu-north-1.amazonaws.com/images/epiphone-see-more-4.jpg', 7);
insert into all_image_table (url_path, guitar_id) values ('https://horder-bucket.s3.eu-north-1.amazonaws.com/images/epiphone-see-more-5.jpg', 7);
insert into all_image_table (url_path, guitar_id) values ('https://horder-bucket.s3.eu-north-1.amazonaws.com/images/epiphone-see-more-6.jpg', 7);
insert into all_image_table (url_path, guitar_id) values ('https://horder-bucket.s3.eu-north-1.amazonaws.com/images/epiphone-see-more-7.jpg', 7);

insert into blogs (title, blog_content) values ('first paragraph', 'Guitars, ubiquitous in the world of music, are enchanting instruments that resonate with emotions and tell stories through their strings. Their versatility spans various genres, from the timeless elegance of acoustic tones to the electrifying energy of rock solos. Crafted with meticulous precision, guitars are not merely musical tools but intricate works of art. The choice of tonewoods, body design, and fretwork contribute to each guitars unique voice. As an evolving canvas for playing techniques, guitars invite musicians to explore bending, sliding, and hammer-ons, creating an endless spectrum of musical expressions.');
insert into blogs (title, blog_content) values ('second paragraph', 'Guitars, ubiquitous in the world of music, are enchanting instruments that resonate with emotions and tell stories through their strings. Their versatility spans various genres, from the timeless elegance of acoustic tones to the electrifying energy of rock solos. Crafted with meticulous precision, guitars are not merely musical tools but intricate works of art. The choice of tonewoods, body design, and fretwork contribute to each guitars unique voice. As an evolving canvas for playing techniques, guitars invite musicians to explore bending, sliding, and hammer-ons, creating an endless spectrum of musical expressions.');
insert into blogs (title, blog_content) values ('third paragraph', 'Guitars, ubiquitous in the world of music, are enchanting instruments that resonate with emotions and tell stories through their strings. Their versatility spans various genres, from the timeless elegance of acoustic tones to the electrifying energy of rock solos. Crafted with meticulous precision, guitars are not merely musical tools but intricate works of art. The choice of tonewoods, body design, and fretwork contribute to each guitars unique voice. As an evolving canvas for playing techniques, guitars invite musicians to explore bending, sliding, and hammer-ons, creating an endless spectrum of musical expressions.');
insert into blogs (title, blog_content) values ('fourth paragraph', 'Guitars, ubiquitous in the world of music, are enchanting instruments that resonate with emotions and tell stories through their strings. Their versatility spans various genres, from the timeless elegance of acoustic tones to the electrifying energy of rock solos. Crafted with meticulous precision, guitars are not merely musical tools but intricate works of art. The choice of tonewoods, body design, and fretwork contribute to each guitars unique voice. As an evolving canvas for playing techniques, guitars invite musicians to explore bending, sliding, and hammer-ons, creating an endless spectrum of musical expressions.');
