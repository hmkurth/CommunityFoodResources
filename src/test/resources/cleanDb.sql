
delete from user_roles;
delete from users;
delete from contact_details;
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

