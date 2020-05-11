package Auth;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private static Connection connection;
    private static Statement stmt;

    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:users.db");
            stmt = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addUser(String login, String pass, String nick) {
        try {
            String query = "INSERT INTO main (login, password, nickname) VALUES (?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, login);
            ps.setInt(2, pass.hashCode());
            ps.setString(3, nick);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteUser(String login) {
        try {
            String sql = "DELETE FROM main WHERE login=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, login);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A user was deleted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<String> selectUsers() {
        List<String> logins = new ArrayList<>();
        try {
            String sql = "SELECT * FROM main";

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()){
                String login = result.getString("login");
                logins.add(login);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logins;
    }

    public static void updateUser(String login, String nickName, String password) {
        try {
            String sql = "UPDATE Users SET nickName=?, password=? WHERE login=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nickName);
            statement.setInt(2, password.hashCode());
            statement.setString(3, login);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing user was updated successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getNickByLoginAndPass(String login, String pass) {
        try {
            ResultSet rs = stmt.executeQuery("SELECT nickname, password FROM main WHERE login = '" + login + "'");
            int myHash = pass.hashCode();
            if (rs.next()) {
                String nick = rs.getString(1);
                int dbHash = rs.getInt(2);
                if (myHash == dbHash) {
                    return nick;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void addLog(String login, String event) {
        try {
            String query = "INSERT INTO log (login, date, event) VALUES ((SELECT login FROM main WHERE login =?), ?, ?);";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, login);
            ps.setString(2, getCurrentDate());
            ps.setString(3, event);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String getCurrentDate(){
        return java.time.Clock.systemUTC().instant().toString();
    }
}
