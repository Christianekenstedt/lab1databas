USE medialibrary;

CREATE TABLE Artist(
	artistID INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    nationality VARCHAR(25) NOT NULL,
    primary key(artistID)
);

CREATE TABLE Genre(
	genreID INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(25) NOT NULL,
    PRIMARY KEY (genreID)
);

CREATE TABLE Grade(
	gradeID INT NOT NULL,
    name VARCHAR(25) NOT NULL,
    PRIMARY KEY(gradeID)
);

CREATE TABLE T_User(
	userID INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50),
    PRIMARY KEY(userID)
);

CREATE TABLE Album(
	albumID INT NOT NULL AUTO_INCREMENT,
    name varchar(50) NOT NULL,
    releaseDate date NOT NULL,
    genre INT NOT NULL,
    grade INT NOT NULL,
    PRIMARY KEY(albumID),
    FOREIGN KEY(genre) REFERENCES Genre(genreID),
    FOREIGN KEY(grade) REFERENCES Grade(gradeID)
);

CREATE TABLE Album_Artist(
	album INT NOT NULL,
    artist INT NOT NULL,
    PRIMARY KEY(album, artist),
    FOREIGN KEY(album) REFERENCES Album(albumID),
    FOREIGN KEY(artist) REFERENCES Artist(artistID)
);

CREATE TABLE Review(
	reviewID INT NOT NULL AUTO_INCREMENT,
    text VARCHAR(100),
    album INT NOT NULL,
    user INT NOT NULL,
    PRIMARY KEY(reviewID),
    FOREIGN KEY(album) REFERENCES Album(albumID),
    FOREIGN KEY(user) REFERENCES T_User(userID)
);