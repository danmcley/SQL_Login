package com.example.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteData {
    public static void main(String[] args) {
        try(Connection connection = MySQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM users WHERE id = ? RETURNING *")) {

            int userIDToDelete = 1;

            preparedStatement.setInt(1, userIDToDelete);

            int rowInserted = preparedStatement.executeUpdate();
            if(rowInserted > 0){
                System.out.println("Data Deleted successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
