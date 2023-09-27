CREATE TABLE locations(
    id serial primary key,
    user_login varchar(100) references users (login),
    name varchar(100) not null,
    country varchar(50) NOT NULL,
    state varchar(50),
    latitude decimal not null,
    longitude decimal not null
);