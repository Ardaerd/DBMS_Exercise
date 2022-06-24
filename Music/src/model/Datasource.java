package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Datasource {
    // For connecting part
    public static final String DB_NAME = "music.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\arda\\OneDrive\\Masaüstü\\Software Project\\DBMS_Projects\\Music\\" + DB_NAME;

    // For albums table
    public static final String TABLE_ALBUMS = "albums";
    public static final String COLUMN_ALBUM_ID = "_id";
    public static final String COLUMN_ALBUM_NAME = "name";
    public static final String COLUMN_ALBUM_ARTIST = "artist";

    // For artists table
    public static final String TABLE_ARTISTS = "artists";
    public static final String COLUMN_ARTISTS_ID = "_id";
    public static final String COLUMN_ARTISTS_NAME = "name";

    // For songs table
    public static final String TABLE_SONGS = "songs";
    public static final String COLUMN_SONG_ID = "_id";
    public static final String COLUMN_SONG_TRACK = "track";
    public static final String COLUMN_SONG_TITLE = "title";
    public static final String COLUMN_SONG_ALBUM = "album";

    // For ordering data
    public static final int ORDER_BY_NONE = 1;
    public static final int ORDER_BY_ASC = 2;
    public static final int ORDER_BY_DESC =3;

    // SELECT albums.name
    // FROM albums INNER JOIN artists ON albums.artist = artists._id
    // WHERE artists.name = artistName;
    public static final String QUERY_ALBUMS_BY_ARTIST = "SELECT " + TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + " " +
                                                        "FROM " + TABLE_ALBUMS + " INNER JOIN " + TABLE_ARTISTS + " " +
                                                        "ON " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST + " = " + TABLE_ARTISTS + "." + COLUMN_ARTISTS_ID + " " +
                                                        "WHERE " + TABLE_ARTISTS + "." + COLUMN_ARTISTS_NAME + " = \"";
    // ORDER BY albums.name COLLATE NOCASE
    public static final String QUERY_ALBUMS_BY_ARTIST_SORT = " ORDER BY " + TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + " COLLATE NOCASE ";

    // SELECT artists.name, albums.name, songs.track
    // FROM songs
    // INNER JOIN albums ON songs.album = albums.id
    // INNER JOIN artists ON albums.artist = artists.id
    // WHERE songs.title = "";
    public static final String QUERY_ARTIST_FOR_SONG = "SELECT " + TABLE_ARTISTS + "." + COLUMN_ARTISTS_NAME + "," + TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + "," + TABLE_SONGS + "." + COLUMN_SONG_TRACK + " " +
            "FROM " + TABLE_SONGS + " INNER JOIN " + TABLE_ALBUMS + " ON " + TABLE_SONGS + "." + COLUMN_SONG_ALBUM + " = " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ID + " " +
            "INNER JOIN " + TABLE_ARTISTS + " ON " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST + " = " + TABLE_ARTISTS + "." + COLUMN_ARTISTS_ID + " " +
            "WHERE " + TABLE_SONGS + "." + COLUMN_SONG_TITLE + " = \"";

    public static final String QUERY_ARTIST_FOR_SONG_COLUMN = "SELECT " + TABLE_ARTISTS + "." + COLUMN_ARTISTS_NAME + "," + TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + "," + TABLE_SONGS + "." + COLUMN_SONG_TRACK + " " +
            "FROM " + TABLE_SONGS + " INNER JOIN " + TABLE_ALBUMS + " ON " + TABLE_SONGS + "." + COLUMN_SONG_ALBUM + " = " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ID + " " +
            "INNER JOIN " + TABLE_ARTISTS + " ON " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST + " = " + TABLE_ARTISTS + "." + COLUMN_ARTISTS_ID;

    // ORDER BY artists.name, albums.name COLLATE NOCASE
    public static final String QUERY_ARTIST_FOR_SONG_SORT = "ORDER BY " + TABLE_ARTISTS + "." + COLUMN_ARTISTS_NAME + "," + TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + " COLLATE NOCASE ";

    public static final String QUERY_SONGS = "SELECT * " +
                                            " FROM " + TABLE_SONGS;
    private Connection conn;

    // Connecting to the DB
    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database : " + e.getMessage());
            return false;
        }
    }

    // Closing the connection
    public void close() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close connection: " + e.getMessage());
        }
    }

    public List<Artists> queryArtists(int sortOrder) {

        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(TABLE_ARTISTS);

        if (sortOrder != ORDER_BY_NONE) {
            sb.append(" ORDER BY ");
            sb.append(COLUMN_ARTISTS_NAME);
            sb.append(" COLLATE NOCASE ");

            if (sortOrder == ORDER_BY_ASC)
                sb.append("DESC");
            else
                sb.append("ASC");
        }

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {

            List<Artists> artists = new ArrayList<>();

            while (results.next()) {
                Artists artist = new Artists();
                artist.setId(results.getInt(COLUMN_ARTISTS_ID));
                artist.setName(results.getString(COLUMN_ARTISTS_NAME));
                artists.add(artist);
            }

            return artists;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public void printArtists_Table(int sortOrder) {
        List<Artists> artists = queryArtists(sortOrder);

        if (artists == null)
            System.out.println("There is no artists");
        else {
            for (Artists artist : artists)
                System.out.println("ID: " + artist.getId() + " | Name: " + artist.getName());
        }
    }

    public List<Songs> querySongs(int sortOrder) {

        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(TABLE_SONGS);

        if (sortOrder != ORDER_BY_NONE) {
            sb.append(" ORDER BY ");
            sb.append(COLUMN_SONG_TITLE);
            sb.append(" COLLATE NOCASE ");

            if (sortOrder == ORDER_BY_ASC)
                sb.append("DESC");
            else
                sb.append("ASC");
        }

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {

            List<Songs> songs = new ArrayList<>();

            while (results.next()) {
                Songs song = new Songs();
                song.setId(results.getInt(COLUMN_SONG_ID));
                song.setTrack(results.getInt(COLUMN_SONG_TRACK));
                song.setTitle(results.getString(COLUMN_SONG_TITLE));
                song.setAlbum(results.getInt(COLUMN_SONG_ALBUM));
                songs.add(song);
            }

            return songs;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public void printSongs_Table(int sortOrder) {
        List<Songs> songs = querySongs(sortOrder);

        if (songs == null)
            System.out.println("There is no artists");
        else {
            for (Songs song : songs)
                System.out.println("ID: " + song.getId() + " | Track: " + song.getTrack() + " | Title: " + song.getTitle() + " | " + song.getAlbum());
        }

    }

    public List<String> queryAlbumsForArtist(String artistName,int sortOrder) {
        // SELECT albums.name
        // FROM albums INNER JOIN artists ON albums.artist = artists._id
        // WHERE artists.name = artistName;
        StringBuilder sb = new StringBuilder(QUERY_ALBUMS_BY_ARTIST);
        sb.append(artistName);
        sb.append("\"");

        if (sortOrder != ORDER_BY_NONE) {
            sb.append(QUERY_ALBUMS_BY_ARTIST_SORT);
            if (sortOrder == ORDER_BY_ASC)
                sb.append("DESC");
            else
                sb.append("ASC");
        }

        System.out.println("SQL statement: " + sb.toString());

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {

            List<String> albums = new ArrayList<>();

            while (results.next()) {
                albums.add(results.getString(1));
            }

            return albums;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public void printAlbumsForArtist(int indexOfArtist,int sortOrder) {
        List<Artists> artists = queryArtists(1);
        List<String> albums = queryAlbumsForArtist(artists.get(indexOfArtist-1).getName(),sortOrder);

        for (String album : albums)
            System.out.println("Artist: " + artists.get(indexOfArtist-1).getName() + " | Album Name: " + album);
    }

    public List<SongArtist> queryArtistsForSong(String songName, int sortOrder) {
        StringBuilder sb = new StringBuilder(QUERY_ARTIST_FOR_SONG);
        sb.append(songName);
        sb.append("\" ");

        if (sortOrder != ORDER_BY_NONE) {
            sb.append(QUERY_ARTIST_FOR_SONG_SORT);
            if (sortOrder == ORDER_BY_ASC)
                sb.append(" DESC");
            else
                sb.append(" ASC");
        }

        System.out.println("SQL statement: " + sb.toString());

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {

            List<SongArtist> songsArtist = new ArrayList<>();

            while (results.next()) {
                SongArtist songArtist = new SongArtist();
                songArtist.setArtistName(results.getString(1));
                songArtist.setAlbumsName(results.getString(2));
                songArtist.setSongsTrack(results.getInt(3));
                songsArtist.add(songArtist);
            }

            return songsArtist;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public void printQueryArtistsForSong(int songIndex, int sortOrder) {
        List<Songs> songs = querySongs(1);
        List<SongArtist> songArtists = queryArtistsForSong(songs.get(songIndex-1).getTitle(),sortOrder);

        for (SongArtist songArtist : songArtists)
            System.out.println("Artist: " + songArtist.getArtistName() + " | " + "Album: " + songArtist.getAlbumsName() + " | " + "Track: " + songArtist.getSongsTrack());
    }


    public void printColumns() {

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(QUERY_ARTIST_FOR_SONG_COLUMN)) {

            ResultSetMetaData meta = results.getMetaData();

            int numColumns = meta.getColumnCount();

            for (int i = 1; i <= numColumns; i++) {
                System.out.println(meta.getColumnName(i));
            }

        } catch (SQLException e) {
            System.out.println("Query is failed: " + e.getMessage());
        }

    }

}
