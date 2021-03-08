
delete from user_roles;
delete from users;
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

Drop USER 'tomcat'@'localhost';

CREATE USER 'tomcat'@'localhost' IDENTIFIED BY 'tomcat';
GRANT SELECT ON testschema.* TO 'tomcat'@'localhost';