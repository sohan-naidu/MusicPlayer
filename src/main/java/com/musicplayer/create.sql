create table if not exists songs
(
    id serial primary key,
    title varchar(255) not null,
    artist varchar(255)not null,
    duration varchar(255) not null,
    song bytea not null
);


create table authentication
(
    username varchar(255) primary key,
    pwd varchar(255) not null,
    firstname varchar(255) not null,
    lastname varchar(255) not null,
    email varchar(255) not null,
    phone varchar(255) not null
);