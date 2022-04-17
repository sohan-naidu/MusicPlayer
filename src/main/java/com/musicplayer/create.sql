create table if not exists songs
(
    id serial primary key,
    title varchar(255) not null,
    artist varchar(255)not null,
    duration varchar(255) not null,
    song bytea not null
);
