package ui;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import storage.UserManager;
import models.Session;

public class LoginWindow {

    public void show(Stage stage) {

        TextField username = new TextField();
        username.setPromptText("Username");

        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        Button loginBtn = new Button("Login");
        Button registerBtn = new Button("Register");

        Label status = new Label();

        loginBtn.setOnAction(e -> {

            if (UserManager.login(username.getText(), password.getText())) {

                Session.setUser(
                    new models.User(
                        username.getText(),
                        password.getText()
                    )
                );

                new ChatWindow().show(new Stage());
                stage.close();

            } else {
                status.setText("Invalid login");
            }
        });

        registerBtn.setOnAction(e -> {
            new RegisterWindow().show(new Stage());
        });

        VBox root = new VBox(10, username, password, loginBtn, registerBtn, status);

        stage.setScene(new Scene(root, 300, 200));
        stage.setTitle("Login");
        stage.show();
    }
}
