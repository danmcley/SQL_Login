package com.example.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateData {
    public static void main(String[] args) {
        try(Connection connection = MySQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE users SET name = ?, email = ? WHERE id = ? RETURNING *")) {

            String newName = "Dan Mcley";
            String newEmail = "danmcley@gmail.com";
            int userIdToUpdate = 1;

            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, newEmail);
            preparedStatement.setInt(3, userIdToUpdate);

            int rowInserted = preparedStatement.executeUpdate();
            if(rowInserted > 0){
                System.out.println("Data updated successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
