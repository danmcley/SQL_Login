package com.example.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertData {
    public static void main(String[] args) {
        try(Connection connection = MySQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO users (name, email) VALUES (?,?)")) {
            String name = "John Doe";
            String email = "john@example.com";

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);

            int rowInserted = preparedStatement.executeUpdate();
            if(rowInserted > 0){
                System.out.println("Data inserted successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } ;
    }
}
