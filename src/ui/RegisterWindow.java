package ui;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import storage.UserManager;

public class RegisterWindow {

    public void show(Stage stage) {

        TextField username = new TextField();
        username.setPromptText("Username");

        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        Button registerBtn = new Button("Create Account");

        Label status = new Label();

        registerBtn.setOnAction(e -> {

            boolean ok = UserManager.register(
                    username.getText(),
                    password.getText()
            );

            if (ok) {
                status.setText("Account created!");
            } else {
                status.setText("User already exists!");
            }
        });

        VBox root = new VBox(
                10,
                username,
                password,
                registerBtn,
                status
        );

        stage.setScene(new Scene(root, 300, 200));
        stage.setTitle("Register");
        stage.show();
    }
}
