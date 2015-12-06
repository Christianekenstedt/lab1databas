/*SELECT Album.name, Artist.name, Album.releaseDate FROM Album, Album_Artist, Artist where Album.albumID = Album_Artist.album and Album_Artist.artist = Artist.artistID;*/


/*SELECT Album.name, Artist.name, Album.releaseDate FROM Album, Album_Artist, Artist where Album.albumID = Album_Artist.album and Album_Artist.artist = Artist.artistID;*/

SELECT Album.* FROM Album, Album_Artist, Artist WHERE Album.albumID = Album_Artist.album and Album_Artist.artist = Artist.artistID AND Artist.name = 'Pink Floyd';