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

    public static final String TABLE_ARTIST_SONG_VIEW = "artist_list";
    // CREATE VIEW IF NOT EXISTS artist_list AS
    // SELECT artists.name, albums.name As album, songs.track, songs.title
    // FROM songs INNER JOIN albums ON songs.album = albums._id
    // INNER JOIN artists ON  albums.artist = artists._id
    //ORDER BY artists.name, albums.name, songs.track
    public static final String CREATE_ARTIST_FOR_SONG_VIEW = "CREATE VIEW IF NOT EXISTS " +
            TABLE_ARTIST_SONG_VIEW + " AS SELECT " + TABLE_ARTISTS + "." + COLUMN_ARTISTS_NAME + "," +
            TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + " AS " + COLUMN_SONG_ALBUM + "," +
            TABLE_SONGS + "." + COLUMN_SONG_TRACK + "," + TABLE_SONGS + "." + COLUMN_SONG_TITLE +
            " FROM " + TABLE_SONGS +
            " INNER JOIN " + TABLE_ALBUMS + " ON " + TABLE_SONGS + "." + COLUMN_SONG_ALBUM + " = " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ID +
            " INNER JOIN " + TABLE_ARTISTS + " ON " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST + " = " + TABLE_ARTISTS + "." + COLUMN_ARTISTS_ID +
            " ORDER BY " + TABLE_ARTISTS + "." + COLUMN_ARTISTS_NAME + "," +
            TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + "," + TABLE_SONGS + "." + COLUMN_SONG_TRACK;

    // SELECT name,album,track FROM artist_list WHERE title = 'title';
    public static final String QUERY_VIEW_SONG_INFO = "SELECT " + COLUMN_ARTISTS_NAME + "," +
            COLUMN_SONG_ALBUM + "," + COLUMN_SONG_TRACK + " FROM " + TABLE_ARTIST_SONG_VIEW +
            " WHERE " + COLUMN_SONG_TITLE + " = \"";

    // SELECT name, album, track FROM artist_list WHERE title = ?;
    public static final String QUERY_VIEW_SONG_INFO_PREP = "SELECT " + COLUMN_ARTISTS_NAME + "," +
            COLUMN_SONG_ALBUM + "," + COLUMN_SONG_TRACK + " FROM " + TABLE_ARTIST_SONG_VIEW +
            " WHERE " + COLUMN_SONG_TITLE + " = ?";

    public static final String QUERY_ARTIST = "SELECT " + COLUMN_ARTISTS_ID + " " +
            "FROM " + TABLE_ARTISTS + " WHERE " + COLUMN_ARTISTS_NAME + " = ?";

    public static final String QUERY_ALBUM = "SELECT " + COLUMN_ALBUM_ID + " " +
            "FROM " + TABLE_ALBUMS + " WHERE " + COLUMN_ALBUM_NAME + " = ?";

    public static final String INSERT_ARTIST = "INSERT INTO " + TABLE_ARTISTS +
            "(" + COLUMN_ARTISTS_NAME + ") VALUES(?)";

    public static final String INSERT_ALBUMS = "INSERT INTO " + TABLE_ALBUMS +
            "(" + COLUMN_ALBUM_NAME + "," + COLUMN_ALBUM_ARTIST + ") VALUES(?,?)";

    public static final String INSERT_SONGS = "INSERT INTO " + TABLE_SONGS +
            "(" + COLUMN_SONG_TRACK + "," + COLUMN_SONG_TITLE + "," + COLUMN_SONG_ALBUM + ") " +
            "VALUES(?,?,?)";

    private Connection conn;
    private PreparedStatement querySongInfoView;
    private PreparedStatement queryArtists;
    private PreparedStatement queryAlbums;

    private PreparedStatement insertIntoArtists;
    private PreparedStatement insertIntoAlbums;
    private PreparedStatement insertIntoSongs;


    // Connecting to the DB
    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            querySongInfoView = conn.prepareStatement(QUERY_VIEW_SONG_INFO_PREP);
            insertIntoArtists = conn.prepareStatement(INSERT_ARTIST,Statement.RETURN_GENERATED_KEYS);
            insertIntoAlbums = conn.prepareStatement(INSERT_ALBUMS,Statement.RETURN_GENERATED_KEYS);
            insertIntoSongs = conn.prepareStatement(INSERT_SONGS);
            queryArtists = conn.prepareStatement(QUERY_ARTIST);
            queryAlbums = conn.prepareStatement(QUERY_ALBUM);
            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database : " + e.getMessage());
            return false;
        }
    }

    // Closing the connection
    public void close() {
        try {
            if (querySongInfoView != null)
                querySongInfoView.close();

            if (insertIntoArtists != null)
                insertIntoArtists.close();

            if (insertIntoAlbums != null)
                insertIntoAlbums.close();

            if (insertIntoSongs != null)
                insertIntoSongs.close();

            if (queryArtists != null)
                queryArtists.close();

            if (queryAlbums != null)
                queryAlbums.close();

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close connection: " + e.getMessage());
        }
    }

    private int insertArtist(String name) throws SQLException {
        queryArtists.setString(1,name);
        ResultSet results = queryArtists.executeQuery();

        if (results.next())
            return results.getInt(1);

        else {
            // Insert the artist
            insertIntoArtists.setString(1,name);
            int affectedRows = insertIntoArtists.executeUpdate();

            if (affectedRows != 1)
                throw new SQLException("Couldn't insert artist!");

            ResultSet generateKeys = insertIntoArtists.getGeneratedKeys();

            if (generateKeys.next())
                return generateKeys.getInt(1);
            else
                throw new SQLException("Couldn't insert artist!");
        }
    }

    private int insertAlbum(String name, int artistId) throws SQLException {
        queryAlbums.setString(1,name);
        ResultSet results = queryAlbums.executeQuery();

        if (results.next())
            return results.getInt(1);

        else {
            // Insert the artist
            insertIntoAlbums.setString(1,name);
            insertIntoAlbums.setInt(2,artistId);
            int affectedRows = insertIntoAlbums.executeUpdate();

            if (affectedRows != 1)
                throw new SQLException("Couldn't insert album!");

            ResultSet generateKeys = insertIntoAlbums.getGeneratedKeys();

            if (generateKeys.next())
                return generateKeys.getInt(1);
            else
                throw new SQLException("Couldn't insert album!");
        }
    }

    public void insertSong(String title, String artist, String album, int track) {
        try {
            conn.setAutoCommit(false);

            int artistId = insertArtist(artist);
            int albumId = insertAlbum(album,artistId);

            insertIntoSongs.setInt(1,track);
            insertIntoSongs.setString(2,title);
            insertIntoSongs.setInt(3,albumId);

            int affectedRows = insertIntoSongs.executeUpdate();

            if (affectedRows == 1)
                conn.commit();
            else
                throw new SQLException("The song insert failed!");

        } catch (Exception e) {
            System.out.println("Insert song exception: " + e.getMessage());

            try {
                System.out.println("Performing rollback...");
                conn.rollback();
            } catch (SQLException ex) {
                System.out.println("Oh boy! Things are really bad! " + ex.getMessage());
            }
        } finally {
            try {
                System.out.println("Resetting default commit behavior!");
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Couldn't reset auto-commit!");
            }
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

    public int getCount(String table) {
        String sql = "SELECT COUNT(*) AS count, MIN(_id) AS min_id FROM " + table;

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sql)) {
            int count = results.getInt("count");
            int min = results.getInt("min_id");
            System.out.printf("Count: %d, Min: %d\n",count,min);

            return count;
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return -1;
        }
    }

    public Boolean createViewForSongArtists() {

        try (Statement statement = conn.createStatement()) {
            statement.execute(CREATE_ARTIST_FOR_SONG_VIEW);
            return true;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return false;
        }
    }

    public List<SongArtist> querySongInfoView(String title) {

        try {
            querySongInfoView.setString(1,title);
            ResultSet results = querySongInfoView.executeQuery();

            List<SongArtist> songArtists = new ArrayList<>();
            while (results.next()) {
                SongArtist songArtist = new SongArtist();
                songArtist.setArtistName(results.getString(1));
                songArtist.setAlbumsName(results.getString(2));
                songArtist.setSongsTrack(results.getInt(3));
                songArtists.add(songArtist);
            }

            return songArtists;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public void printSongInfoView(String song) {
        List<SongArtist> songArtists = querySongInfoView(song);

        if (songArtists.isEmpty()) {
            System.out.println("Song couldn't find.");
            return;
        }

        for (SongArtist songInfo : songArtists)
            System.out.println("|Artist: " + songInfo.getArtistName() + " |Album: " + songInfo.getAlbumsName() + " |Track: " + songInfo.getSongsTrack() + "|");
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
