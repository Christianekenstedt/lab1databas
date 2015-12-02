USE medialibrary;


INSERT INTO Genre(name) VALUES('Rock'),('Reggae'),('Dance'),('House'),('RnB'),('Pop');
INSERT INTO Grade(gradeID, name) VALUES(1,'Bad'),(2,'Meh'),(3,'Okey'),(4,'Good'), (5,'Very Good');

INSERT INTO Artist(name, nationality) VALUES('Jack Black', 'USA');
INSERT INTO Artist(name, nationality) VALUES('Pink Floyd', 'USA');

INSERT INTO Album(name, releaseDate, genre, grade) VALUES('Dark Side of the Moon', DATE('1973-00-00'), 1, 5);

INSERT INTO Album_Artist(album,artist) VALUES (1,2);