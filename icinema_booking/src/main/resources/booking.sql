create schema icinema_booking;
use icinema_booking;
create table booking(
	id INT AUTO_INCREMENT PRIMARY KEY,
	username VARCHAR(200) NOT NULL,
	password VARCHAR(200) NOT NULL
);