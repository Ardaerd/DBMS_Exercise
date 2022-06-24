package model;

public class SongArtist {
    private String artistName;
    private String albumsName;
    private int songsTrack;

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getAlbumsName() {
        return albumsName;
    }

    public void setAlbumsName(String albumsName) {
        this.albumsName = albumsName;
    }

    public int getSongsTrack() {
        return songsTrack;
    }

    public void setSongsTrack(int songsTrack) {
        this.songsTrack = songsTrack;
    }
}
