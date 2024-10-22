CREATE TABLE IF NOT EXISTS RUN(
    id int not null, 
    title varchar(255) not null,
    started_on timestamp not null,
    completed_on timestamp not null,
    miles int not null,
    location varchar(10) not null,
    primary key(id)
);