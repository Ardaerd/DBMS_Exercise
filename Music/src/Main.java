import model.Datasource;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Datasource datasource = new Datasource();
        if (!datasource.open()) {
            System.out.println("Can't open datasource");
            return;
        }

        System.out.println("1- For None");
        System.out.println("2- For Ascending order");
        System.out.println("3- For Descending order");
        System.out.print("Enter: ");

        int sortOrder = scanner.nextInt();
        datasource.printArtists_Table(sortOrder);

        System.out.print("Enter the Artist's ID: ");
        int indexOfArtist = scanner.nextInt();
        System.out.println();

        System.out.println("1- For None");
        System.out.println("2- For Ascending order");
        System.out.println("3- For Descending order");
        System.out.print("Enter: ");

        sortOrder = scanner.nextInt();

        datasource.printAlbumsForArtist(indexOfArtist,sortOrder);

        int count = datasource.getCount(Datasource.TABLE_SONGS);
        System.out.println("Number of songs is: " + count);

        System.out.println("1- For None");
        System.out.println("2- For Ascending order");
        System.out.println("3- For Descending order");
        System.out.print("Enter: ");
        sortOrder = scanner.nextInt();

        datasource.printSongs_Table(sortOrder);
        System.out.print("Enter the Song's ID: ");
        int indexOfSong = scanner.nextInt();

        System.out.println("1- For None");
        System.out.println("2- For Ascending order");
        System.out.println("3- For Descending order");
        System.out.print("Enter: ");
        sortOrder = scanner.nextInt();

        datasource.printQueryArtistsForSong(indexOfSong,sortOrder);

        System.out.print("push to enter for continue: ");
        scanner.nextLine();
        String str = scanner.nextLine();

        datasource.createViewForSongArtists();

        System.out.println("Enter a song name: ");
        str = scanner.nextLine();
        datasource.printSongInfoView(str);

        datasource.close();
    }


}