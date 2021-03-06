USE medialibrary;


INSERT INTO Genre(name) VALUES('Rock'),('Reggae'),('Dance'),('House'),('RnB'),('Pop');
INSERT INTO Grade(gradeID, name) VALUES(1,'Bad'),(2,'Meh'),(3,'Okey'),(4,'Good'), (5,'Very Good');

INSERT INTO Artist(name, nationality) VALUES('Jack Black', 'USA');
INSERT INTO Artist(name, nationality) VALUES('Pink Floyd', 'USA');
INSERT INTO Artist(name, nationality) VALUES('The Beatles', 'UK');
INSERT INTO Artist(name, nationality) VALUES('Led Zeppelin', 'UK');
INSERT INTO Artist(name, nationality) VALUES('Michael Jackson', 'USA');
INSERT INTO Artist(name, nationality) VALUES('Swedish House Mafia', 'SWE');

INSERT INTO Album(name, releaseDate, genre, grade) VALUES('Dark Side of the Moon', DATE('1973-00-00'), 1, 5);
INSERT INTO Album(name, releaseDate, genre, grade) VALUES('Sgt. Peppers Lonely Hearts Club Band', DATE('1967-06-01'), 1, 5);
INSERT INTO Album(name, releaseDate, genre, grade) VALUES('Led Zeppelin IV', DATE('1967-06-01'), 1, 5);
INSERT INTO Album(name, releaseDate, genre, grade) VALUES('The Pick of Destiny', DATE('2006-11-14'), 1, 5);
INSERT INTO Album(name, releaseDate, genre, grade) VALUES('Thriller', DATE('1982-01-01'), 1, 5);
INSERT INTO Album(name, releaseDate, genre, grade) VALUES('Greyhound', DATE('2012-03-12'), 4, 5);

INSERT INTO Album_Artist(album,artist) VALUES (1,2);
INSERT INTO Album_Artist(album,artist) VALUES (2,3);
INSERT INTO Album_Artist(album,artist) VALUES (3,4);
INSERT INTO Album_Artist(album,artist) VALUES (4,1);
INSERT INTO Album_Artist(album,artist) VALUES (5,5);
INSERT INTO Album_Artist(album,artist) VALUES (6,6);

/*ADD USERS*/
INSERT INTO T_User(name) VALUES('Christian'),('Gustaf'),('Kalle'),('Lisa');

/*ADD REVIEW*/
INSERT INTO Review(text, album, user) VALUES('I like it, but not the best one', 2, 1);