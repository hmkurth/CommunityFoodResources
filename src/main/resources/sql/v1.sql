create database CFRL;

create table if not exists contact_details
(
    id int auto_increment
        primary key,
    first_name varchar(255) null,
    last_name varchar(255) null,
    email varchar(100) null,
    phone varchar(17) null,

owner_id int null
);

create table if not exists location
(
    id int auto_increment
        primary key,
    name_description varchar(255) null,
    streetaddr_or_intersection varchar(255) null,
    city varchar(25) null,
    state varchar(2) null,
    zip varchar(10) null,
    bus_info varchar(500) null comment 'adjacent bustops or line info',
    comments varchar(500) null,
    lat float(10,6) null,
    lng float(10,6) null
)
    comment 'location data';

create table if not exists food_resources
(
    id int auto_increment
        primary key,
    resource_name varchar(255) not null,
    resource_owner int null comment 'foreign key to resource owner table',
    description varchar(500) not null,
    location_id int null,
    contact_id int null,
    comments varchar(500) null,
    service_area varchar(500) null comment 'the area serviced, who can use service',
    documentation_needed varchar(500) not null,
    website varchar(300) null,
    day_of_week_offered varchar(500) null,
    delivery_offered tinyint(1) not null,
    delivery_desc varchar(500) null,
    dietary_considerations varchar(500) null,
    type_id int null,
    is_verified tinyint(1) default 0 not null,
    constraint food_resources_contact_details_id_fk
        foreign key (contact_id) references contact_details (id)
            on update cascade on delete cascade,
    constraint food_resources_location_id_fk
        foreign key (location_id) references location (id)
            on update cascade on delete cascade
)
    comment 'individual services or resources, corresponding to resource to owners';

create table if not exists resource_owners
(
    id int auto_increment
        primary key,
    org_name varchar(255) not null,
    resource_id int null,
    website varchar(100) null,
    constraint resource_owners_food_resources_id_fk
        foreign key (resource_id) references food_resources (id)
            on update cascade on delete cascade
)
    comment 'entities that control food resources';

alter table contact_details
    add constraint contact_details_resource_owners_id_fk
        foreign key (owner_id) references resource_owners (id)
            on update cascade on delete cascade;

alter table food_resources
    add constraint food_resources_resource_owners_id_fk
        foreign key (resource_owner) references resource_owners (id)
            on update cascade on delete cascade;

create table if not exists resource_type
(
    id int auto_increment
        primary key,
    type_name varchar(50) not null,
    resource_id int null,
    constraint resource_type_food_resources_id_fk
        foreign key (resource_id) references food_resources (id)
            on update cascade on delete cascade
)
    comment 'to catagorize resources into groups';

alter table food_resources
    add constraint food_resources_resource_type_id_fk
        foreign key (type_id) references resource_type (id)
            on update cascade on delete cascade;

create table if not exists users
(
    id int auto_increment
        primary key,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    user_name varchar(255) not null,
    user_password varchar(255) not null,
    email varchar(255) not null
);

create table if not exists user_roles
(
    id int auto_increment
        primary key,
    user_name varchar(255) not null,
    role_name varchar(255) not null,
    users_id int not null,
    constraint user_roles_users_id_fk
        foreign key (users_id) references users (id)
            on update cascade on delete cascade
);


INSERT INTO resource_type VALUES (1, 'food pantry', null);
INSERT INTO resource_type VALUES (2, 'free little pantry', null);
INSERT INTO resource_type VALUES (3, 'community_fridge', null);
INSERT INTO resource_type VALUES (4, 'meal', null);
INSERT INTO resource_type VALUES (5, 'government resource', null);
INSERT INTO resource_type VALUES (6, 'community aid and support groups', null);
INSERT INTO resource_type VALUES (7, 'other', null);

CREATE USER 'tomcat'@'localhost' IDENTIFIED BY 'tomcat';
GRANT SELECT ON CFRL.* TO 'tomcat'@'localhost';

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
