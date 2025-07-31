DROP SCHEMA IF EXISTS icinema_seating;
CREATE SCHEMA icinema_seating;
USE icinema_seating;
create table seating(
	id integer not null auto_increment,
	seat_number integer,
	show_id integer,
	user_name varchar(255),
	primary key (id);
);