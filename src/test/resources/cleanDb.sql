
delete from user_roles;
delete from users;
delete from contact_details;
delete from location;
delete from food_resources;
INSERT INTO users VALUES (1,'Joe','Coyne','jcoyne','supersecret1','beast@mail'),
                         (2,'Fred','Hensen','fhensen','supersecret2','yeast@mail'),
                         (3,'Barney','Curry','bcurry','supersecret3','seast@mail'),
                         (4,'Karen','Mack','kmack','supersecret4','least@mail'),
                         (5,'Dianne','Klein','dklein','supersecret5','zeast@mail'),
                         (6,'tom','cat','tomcat','tomcat','QUeast@mail');
INSERT INTO user_roles VALUES (1,'jcoyne','regular','1'),
                              (2,'fhensen','all','2'),
                              (3,'bcurry','admin','3'),
                              (4,'kmack','regular','4'),
                              (5,'dklein','all','5'),
                              (6,'tomcat','administration','6');

INSERT INTO contact_details VALUES (1,'tim','shmidt','tsm@gone.com', '1233333333'),
                                   (2, 'Sue', 'Bell', 'SueB.com', '125878956'),
                                   (3,'Mike', 'Voit','MV@gone.com', '8005557777'),
                                   (4,'Caleb','Jones','cjn@gone.com', '1255333333'),
                                   (5,'Carrie','Tanner','ctan@gone.com', '1255333333'),
                                   (6,'Vera','Planer','vera@gobs.com', '1233333333');

INSERT INTO location (id, name_description, streetaddr_or_intersection, city, state, zip,  comments, resource_id)
VALUES
(1,'eastside near lansing','422 Lansing','Madison', 'WI', 53714,  'near front of property', 55);
INSERT INTO location (id, name_description, streetaddr_or_intersection, city, state, zip,  comments, resource_id) VALUES (2,'main building','522 Albert','Madison','WI', 53746,  'n/a', 65);
INSERT INTO location (id, name_description, streetaddr_or_intersection, city, state, zip,  comments, resource_id) VALUES (3, 'drop off','655 Main','Madison','WI', 53722,  'n/a', 75);
INSERT INTO location (id, name_description, streetaddr_or_intersection, city, state, zip,  comments, resource_id) VALUES (4,'River Food Pantry','1002 W. Wash','Madison', 'WI', 537478,  'pickup near front door', 85);
INSERT INTO location (id, name_description, streetaddr_or_intersection, city, state, zip,  comments, resource_id) VALUES (5,'Easter Seals','corner of Main and Fordem','Madison','WI', 53254,  'near back', 95);
INSERT INTO location (id, name_description, streetaddr_or_intersection, city, state, zip,  comments, resource_id) VALUES (6,'eastside near lansing','422 Lansing','Madison','WI', 53758,  'n/a', 105);




INSERT INTO food_resources (id, resource_name,  description,location_id, contact_id, documentation_needed, delivery_offered )
VALUES
(1,'free pantry on johnson ', 'free pantry','1', '3', 'social security number and id', '0');
INSERT INTO food_resources (id, resource_name,  description,location_id, contact_id, documentation_needed, delivery_offered )
VALUES
(2,'community meal ', 'saturday meal','2', '2', 'social security number and id', '1');
INSERT INTO food_resources (id, resource_name,  description,location_id, contact_id, documentation_needed, delivery_offered )
VALUES
(3,'free pantry on Gorham ', 'free pantry','2', '3', 'ssn', '1');
INSERT INTO food_resources (id, resource_name,  description,location_id, contact_id, documentation_needed, delivery_offered )
VALUES
(4,'SNAP Program', 'gov program','1', '3', 'social security number and id', '0');
INSERT INTO food_resources (id, resource_name,  description,location_id, contact_id, documentation_needed, delivery_offered )
VALUES
(5,'Christ Lutheran Church Pantry', 'food pantry','6', '5', 'social security number and id', '0');
INSERT INTO food_resources (id, resource_name,  description, contact_id, documentation_needed, delivery_offered )
VALUES
(6,'Dane County Neighbors Helping neighbors ', 'facebook page', '6', 'n/a', '0');


