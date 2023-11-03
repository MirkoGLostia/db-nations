package org.exercise.java.nations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainNation {
    public static void main(String[] args) {

        // parameters for the connection to the database
        String url = "jdbc:mysql://localhost:3306/db_nations";
        String user = "root";
        String password = "root";

        // try to open a connection
        try (Connection connection = DriverManager.getConnection(url, user, password)) {

            String query =
                    "select c.name, c.country_id, r.name, c2.name "
                            + "from countries c "
                            + "join regions r on r.region_id = c.region_id "
                            + "join continents c2 on c2.continent_id = r.continent_id "
                            + "order by c.name ";


        } catch (SQLException e) {
            System.out.println("Unable to open connection");
            e.printStackTrace();
        }
    }
}
