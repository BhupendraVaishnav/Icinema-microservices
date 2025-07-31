-- Step 1: Create Database

create SCHEMA moviesdb;
use moviesdb;
CREATE TABLE Movies (
	id INT AUTO_INCREMENT PRIMARY KEY,
	movie_name VARCHAR(255),
	movie_genre VARCHAR(50),
	movie_language VARCHAR(50),
	release_date DATE,
	rating DECIMAL(3,1),
	censor_rating VARCHAR(10),
	movie_description TEXT,
	view_count INT,
	cover_image VARCHAR(255),
	is_screening BOOLEAN
);

INSERT INTO Movies(movie_name, movie_genre, movie_language, release_date, rating, censor_rating, movie_description, view_count, cover_image, is_screening)
VALUES
('Stellar Voyage','SCIFI','English', '2025-06-01', 8.5, 'PG-13', 'Embark on ann interrgalactic journey that explores humanitys resilience',0,'http://localhost:9100/utilities/1/thumbnail',TRUE);