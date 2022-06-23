import java.sql.*;

public class Main {
    public static final String DB_NAME = "testJava.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\arda\\OneDrive\\Masaüstü\\Software Project\\DBMS_Projects\\testDB\\" + DB_NAME;

    public static final String TABLE_CONTACTS = "contacts";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";

    public static void main(String[] args) {

        try {
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();

            statement.execute("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS + " " +
                            "(" + COLUMN_NAME + " TEXT, "
                                + COLUMN_PHONE + " INTEGER, "
                                + COLUMN_EMAIL + " TEXT)");

            insertContact(statement,"Arda",5414374277L,"arderd@outlook.com");

            insertContact(statement,"ekin",5427896577L,"ekin@outlook.com");

            insertContact(statement,"uygar",5439876534L,"uygrgun@outlook.com");

           insertContact(statement,"hilal",5414389766L,"hilal@outlook.com");

            statement.execute("UPDATE contacts SET name = 'nazlı', email = 'nazlı@outlook.com' " +
                                  "WHERE name = 'uygar'");
            statement.execute("DELETE FROM contacts WHERE name = 'Arda'");

//            statement.execute("SELECT * FROM contacts");
//            ResultSet results = statement.getResultSet();

            ResultSet results = statement.executeQuery("SELECT * FROM contacts");
            while (results.next()) {
                System.out.println(results.getString("name") + " " +
                        results.getString("phone") + " " +
                        results.getString("email"));
            }

            results.close();
            statement.close();
            conn.close();
        }
        catch (SQLException e) {
            System.out.println("Something went wrong " + e.getMessage());
            e.printStackTrace();
        }

    }

    public static void insertContact(Statement statement, String name, long phone, String email) throws SQLException {
        statement.execute("INSERT INTO " + TABLE_CONTACTS +
                "(" + COLUMN_NAME + "," + COLUMN_PHONE + "," + COLUMN_EMAIL + ") " +
                "VALUES('" + name + "'" + "," + phone + "," + "'" + email + "')");
    }
}