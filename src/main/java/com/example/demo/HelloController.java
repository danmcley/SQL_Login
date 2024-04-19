package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class HelloController {
    public VBox pnLogin;
    public ColorPicker cpPicker;
    public TextField usernameField;
    public PasswordField passwordField;
    public Label Error;
    public VBox pnLogOut;
    public TextField registerUsernameField;
    public PasswordField registerPasswordField;
    public TextField registerConfirm;
    public Label register;
    @FXML
    protected void onLoginClick() throws IOException {
        AnchorPane p1 = (AnchorPane) pnLogin.getParent();
        Parent scene = FXMLLoader.load(getClass().getResource("home-view.fxml"));
        p1.getScene().getStylesheets().clear();
        p1.getScene().getStylesheets().add(getClass().getResource("user.css").toExternalForm());
        p1.getChildren().clear();
        p1.getChildren().add(scene);
    }
    @FXML
    protected void onSignupClick() throws IOException {
        AnchorPane p1 = (AnchorPane) pnLogin.getParent();
        Parent scene = FXMLLoader.load(getClass().getResource("sign_up.fxml"));
        p1.getScene().getStylesheets().clear();
        p1.getScene().getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        p1.getChildren().clear();
        p1.getChildren().add(scene);
    }
    @FXML
    protected void onLogOutClick() throws IOException {
        AnchorPane p2 = (AnchorPane) pnLogOut.getParent();
        Parent scene = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        p2.getScene().getStylesheets().clear();
        p2.getScene().getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        p2.getChildren().clear();
        p2.getChildren().add(scene);
    }
    @FXML
    protected void onRegister() throws IOException {
        if(registerPasswordField.getText().equals(registerConfirm.getText())){
            try(Connection c = MySQLConnection.getConnection();
                PreparedStatement statement = c.prepareStatement(
                        "INSERT INTO users (name, password) VALUES(?,?)"
                )){
                String name = registerUsernameField.getText();
                String password = registerPasswordField.getText();
                statement.setString(1, name);
                statement.setString(2, password);
                int rows = statement.executeUpdate();
                System.out.println("Rows inserted: " + rows);
                onLoginClick();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            register.setText("Password doesn't match");
        }
    }

}