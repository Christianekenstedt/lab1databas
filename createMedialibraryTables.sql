USE medialibrary;

CREATE TABLE Artist(
	artistId INT NOT NULL,
    name VARCHAR(50) NOT NULL,
    nationality VARCHAR(25) NOT NULL,
    primary key(artistId)
);

CREATE TABLE Director(
	directorId INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY(directorId)
);

CREATE TABLE Genre(
	genre VARCHAR(25) NOT NULL,
    PRIMARY KEY (genre)
);

CREATE TABLE T_User(
	persNr INT NOT NULL,
    name VARCHAR(50),
    PRIMARY KEY(persNr)
);


CREATE TABLE Album(
	albumId INT NOT NULL,
    name varchar(50) NOT NULL,
    noTracks INT NOT NULL,
    artist INT NOT NULL,
    releaseDate date NOT NULL,
    PRIMARY KEY(albumId),
    FOREIGN KEY(artist) REFERENCES Artist(artistId)
);

CREATE TABLE Movie(
	movieId INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(50) NOT NULL,
    genre VARCHAR(25) NOT NULL,
    length INT,
    director int NOT NULL,
    releaseDate DATE NOT NULL,
    PRIMARY KEY(movieId),
    foreign key(genre) REFERENCES Genre(genre),
    foreign key(director) REFERENCES Director(directorId)
);
