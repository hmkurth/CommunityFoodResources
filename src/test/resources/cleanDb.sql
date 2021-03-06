
delete from user_roles;
delete from users;
delete from contact_details;
delete from location;
delete from food_resources;
delete from resource_type;
delete from resource_owners;

INSERT INTO users VALUES (1,'Joe','Coyne','jcoyne','supersecret1','beast@mail'),
                         (2,'Fred','Hensen','fhensen','supersecret2','yeast@mail'),
                         (3,'Barney','Curry','bcurry','supersecret3','seast@mail'),
                         (4,'Karen','Mack','kmack','supersecret4','least@mail'),
                         (5,'Dianne','Klein','dklein','supersecret5','zeast@mail'),
                         (6,'tom','cat','tomcat','tomcat','QUeast@mail');
INSERT INTO user_roles VALUES (1,'jcoyne','user','1'),
                              (2,'fhensen','all','2'),
                              (3,'bcurry','admin','3'),
                              (4,'kmack','user','4'),
                              (5,'dklein','all','5'),
                              (6,'tomcat','administration','6');


INSERT INTO location (id, name_description, streetaddr_or_intersection, city, state, zip,  comments)
VALUES
(1,'eastside near lansing','422 Lansing','Madison', 'WI', 53714,  'near front of property');
INSERT INTO location (id, name_description, streetaddr_or_intersection, city, state, zip,  comments) VALUES (2,'main building','3426 hargrove','Madison','WI', 53714,  'n/a');
INSERT INTO location (id, name_description, streetaddr_or_intersection, city, state, zip,  comments) VALUES (3, 'drop off','655 Main','Madison','WI', 53722,  'n/a');
INSERT INTO location (id, name_description, streetaddr_or_intersection, city, state, zip,  comments) VALUES (4,'River Food Pantry','1002 W. Wash','Madison', 'WI', 537478,  'pickup near front door');
INSERT INTO location (id, name_description, streetaddr_or_intersection, city, state, zip,  comments) VALUES (5,'Easter Seals','corner of Main and Fordem','Madison','WI', 53254,  'near back');
INSERT INTO location (id, name_description, streetaddr_or_intersection, city, state, zip,  comments) VALUES (6,'eastside near lansing','422 Lansing','Madison','WI', 53758,  'n/a');



INSERT INTO resource_type VALUES (1, 'food pantry', null);
INSERT INTO resource_type VALUES (2, 'free little pantry', null);
INSERT INTO resource_type VALUES (3, 'meal', null);
INSERT INTO resource_type VALUES (4, 'government resource', null);
INSERT INTO resource_type VALUES (5, 'community aid and support groups', null);
INSERT INTO resource_type VALUES (6, 'other', null);

INSERT INTO food_resources (id, type_id, resource_name,  description,location_id, contact_id, documentation_needed, delivery_offered )
VALUES
(1, 2, 'free pantry on johnson ', 'free pantry','1', null, 'social security number and id', false);
INSERT INTO food_resources (id,  type_id,  resource_name,  description,location_id, contact_id, documentation_needed, delivery_offered )
VALUES
(2,5, 'community meal ', 'saturday meal','2', null, 'social security number and id', true);
INSERT INTO food_resources (id,  type_id,  resource_name,  description,location_id, contact_id, documentation_needed, delivery_offered )
VALUES
(3, 2, 'free pantry on Gorham ', 'free pantry','2', null, 'ssn', true);
INSERT INTO food_resources (id,  type_id, resource_name,  description,location_id, contact_id, documentation_needed, delivery_offered )
VALUES
(4,6,'SNAP Program', 'gov program','1', null, 'social security number and id', false);
INSERT INTO food_resources (id, type_id , resource_name,  description,location_id, contact_id, documentation_needed, delivery_offered )
VALUES
(5,5, 'Christ Lutheran Church Pantry', 'food pantry','6', null, 'social security number and id', false);
INSERT INTO food_resources (id,  type_id, resource_name,  description, contact_id, documentation_needed, delivery_offered )
VALUES
(6,4, 'Dane County Neighbors Helping neighbors ', 'facebook page', null, 'n/a', false);




INSERT INTO resource_owners VALUES (1, 'The Beacon', null, 'www.beacon.com' );
INSERT INTO resource_owners VALUES (2, 'Salvation Army', null, 'www.salvationArmy.com' );
INSERT INTO resource_owners VALUES (3, 'Paul Schmidt', null, 'www.paully.com' );
INSERT INTO resource_owners VALUES (4, 'Bethel Lutheran Church', null, 'www.christalmighty!.com' );
INSERT INTO resource_owners VALUES (5, 'State Of Wisconsin',null, 'www.SNAP.com' );
INSERT INTO resource_owners VALUES (6, 'Liza Beans', null, '@facebookSomething' );


INSERT INTO contact_details VALUES (1,'tim','shmidt','tsm@gone.com', '1233333333', 2),
                                   (2, 'Sue', 'Bell', 'SueB.com', '125878956',2),
                                   (3,'Mike', 'Voit','MV@gone.com', '8005557777', 6),
                                   (4,'Caleb','Jones','cjn@gone.com', '1255333333', 2),
                                   (5,'Carrie','Tanner','ctan@gone.com', '1255333333', 4),
                                   (6,'Vera','Planer','vera@gobs.com', '1233333333', 5);
