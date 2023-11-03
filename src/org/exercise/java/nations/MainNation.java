package org.exercise.java.nations;

import java.sql.*;
import java.util.Scanner;

public class MainNation {
    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);

        // parameters for the connection to the database
        String url = "jdbc:mysql://localhost:3306/db_nations";
        String user = "root";
        String password = "root";


        // try to open a connection
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("what country you want to search?");
            String userCountry = userInput.nextLine();

            String query =
                    "select c.country_id, c.name, r.name, c2.name "
                            + "from countries c "
                            + "join regions r on r.region_id = c.region_id "
                            + "join continents c2 on c2.continent_id = r.continent_id "
                            + "where lower(c.name) like ? "
                            + "order by c.name; ";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                // insert scanner variables into the query
                preparedStatement.setString(1, "%" + userCountry + "%");


                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    while (resultSet.next()) {
                        String countryID = resultSet.getString("c.country_id");
                        String countryName = resultSet.getString("c.name");
                        String regionName = resultSet.getString("r.name");
                        String continentName = resultSet.getString("c2.name");

                        System.out.println(countryID + " - " + countryName + " REGION: " + regionName + " CONTINENT: " + continentName);
                    }

                } catch (SQLException e) {
                    System.out.println("Unable to execute query");
                    e.printStackTrace();
                }

            } catch (SQLException e) {
                System.out.println("Unable to prepare statement");
                e.printStackTrace();
            }


        } catch (SQLException e) {
            System.out.println("Unable to open connection");
            e.printStackTrace();
        }


        userInput.close();
    }
}
