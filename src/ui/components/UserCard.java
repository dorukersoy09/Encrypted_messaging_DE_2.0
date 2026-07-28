package ui.components;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class UserCard extends VBox {

    private final String username;

    public UserCard(
            String username,
            boolean online
    ){

        this.username=username;

        Label name=new Label(username);

        name.setStyle(
                "-fx-font-size:15;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:white;"
        );

        Label lastMessage =
                new Label("No messages yet");

        lastMessage.setStyle(
                "-fx-font-size:11;" +
                "-fx-text-fill:#BBBBBB;"
        );

        Label status =
                new Label(
                        online
                        ?
                        "🟢 Online"
                        :
                        "⚪ Offline"
                );

        status.setStyle(
                "-fx-font-size:10;"
        );

        getChildren().addAll(
                name,
                lastMessage,
                status
        );

        setSpacing(4);

        setPadding(new Insets(12));

        setStyle(
                "-fx-background-color:#2B2D31;" +
                "-fx-background-radius:10;"
        );

    }

    public String getUsername(){

        return username;

    }

}