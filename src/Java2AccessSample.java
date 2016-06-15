/*
 * Made by Bascoder 2016.
 */

import java.sql.*;
import java.util.Locale;

/**
 * Created by Bas for project java2access.
 *
 * @author bascoder
 * @since 15-6-2016
 */
public class Java2AccessSample {

    private static final String JDBC_URL = "jdbc:ucanaccess://"
            + "D:/data/Dropbox/Development/access/northwind2013.accdb";

    public static void main(String[] args) {
        System.out.println("Start connection");
        try (final Connection con = DriverManager.getConnection(JDBC_URL, "StevenT", null)) {
            System.out.println("Connected successfully");

            printTables(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void printTables(Connection connection) {
        // query alle employees
        final String sql = "SELECT * FROM Employees";

        try (final Statement stmt = connection.createStatement();
             final ResultSet resultSet = stmt.executeQuery(sql)) {

            System.out.println("ID\tEmail\tFull Name\tLogin");
            // iterate & print met tabs
            while (resultSet.next()) {
                // luie manier column ordinal
                int i = 1;
                System.out.printf(Locale.getDefault(),
                        "%s\t%s\t%s\t%s",
                        resultSet.getString(i++), resultSet.getString(i++),
                        resultSet.getString(i++), resultSet.getString(i));
                System.out.print('\n');
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
