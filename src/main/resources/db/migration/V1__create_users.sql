CREATE TABLE users(
    login varchar(100) primary key,
    password varchar(100) not null,
    role varchar(100) not null
);