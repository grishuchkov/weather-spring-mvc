CREATE TABLE locations(
    id serial primary key,
    name varchar(100) not null,
    user_login varchar(100) references users (login),
    latitude decimal not null,
    longitude decimal not null
);