create table person (
	id bigserial primary key,
	car_id bigserial,
	name_person text,
	age int,
	availability_of_rights boolean,
	foreign key (car_id) references car (id)

);

create table car (
	id bigserial primary key,
	stamp_car text,
	model text,
	price bigserial
);