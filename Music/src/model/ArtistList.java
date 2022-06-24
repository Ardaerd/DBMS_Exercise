package model;

public class ArtistList {
    private String artistName;
    private String albumName;
    private int songTrack;
    private String songTitle;

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public int getSongTrack() {
        return songTrack;
    }

    public void setSongTrack(int songTrack) {
        this.songTrack = songTrack;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }
}
