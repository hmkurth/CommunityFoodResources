
delete from user_roles;
delete from users;
delete from contact_details;
delete from location;
delete from food_resources;
delete from resource_type;
delete from resource_owners;

INSERT INTO users VALUES (1,'Joe','Coyne','j','1','beast@mail');

INSERT INTO user_roles VALUES (1,'j','admin','1');
INSERT  INTO  resource_owners VALUES (0, 'private or not available', null, null);
INSERT  INTO  resource_owners VALUES (8888, 'default place holder', null, null);
INSERT INTO  location VALUES(9999, 'default place holder', 'somewhere', 'Madison', 'WI', '53704', 'n/a', 'this is a placeholder location', null, null, null);

INSERT INTO resource_type VALUES (1, 'food pantry', null);
INSERT INTO resource_type VALUES (2, 'free little pantry', null);
INSERT INTO resource_type VALUES (3, 'community_fridge', null);
INSERT INTO resource_type VALUES (4, 'meal', null);
INSERT INTO resource_type VALUES (5, 'government resource', null);
INSERT INTO resource_type VALUES (6, 'community aid and support groups', null);
INSERT INTO resource_type VALUES (7, 'other', null);

INSERT INTO  contact_details VALUES(8888, 'default', 'default', null, null,null);

INSERT INTO food_resources (type_id, resource_name,  description, documentation_needed,service_area, website, day_of_week_offered, delivery_offered )
VALUES
(  1, 'Allied Food Pantry ', 'food pantry: We give out pre-packed bags from the outside of the Boys and Girls Club',  'Recipients do not need to sign up in advance but must bring identification for each family member.', 'Anyone who lives in the 53711, 53713 or 53719 ZIP code', 'https://alliedfoodpantry.wixsite.com/allied-food-pantry/who-we-serve', ' Wed 10:00-12:00, and Wed 6:00-7:30 pm', false);
