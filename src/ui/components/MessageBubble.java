package ui.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class MessageBubble extends HBox {

    public MessageBubble(
            String sender,
            String message,
            String time,
            boolean mine
    ) {

        VBox bubble = new VBox();

        bubble.setSpacing(4);

        bubble.setPadding(new Insets(10));

        bubble.setMaxWidth(420);

        Label senderLabel = new Label(sender);

        senderLabel.setStyle(
                "-fx-font-size:11;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:#AAAAAA;"
        );

        Label messageLabel = new Label(message);

        messageLabel.setWrapText(true);

        messageLabel.setStyle(
                "-fx-font-size:14;"
        );

        Label timeLabel = new Label(time);

        timeLabel.setStyle(
                "-fx-font-size:10;" +
                "-fx-text-fill:#888888;"
        );

        Region spacer = new Region();

        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox footer = new HBox(
                spacer,
                timeLabel
        );

        if (mine) {

            bubble.setBackground(
                    new Background(
                            new BackgroundFill(
                                    Color.web("#5865F2"),
                                    new CornerRadii(18),
                                    Insets.EMPTY
                            )
                    )
            );

            messageLabel.setTextFill(Color.WHITE);

            senderLabel.setTextFill(Color.LIGHTGRAY);

            setAlignment(Pos.CENTER_RIGHT);

        } else {

            bubble.setBackground(
                    new Background(
                            new BackgroundFill(
                                    Color.web("#313338"),
                                    new CornerRadii(18),
                                    Insets.EMPTY
                            )
                    )
            );

            messageLabel.setTextFill(Color.WHITE);

            senderLabel.setTextFill(Color.LIGHTGRAY);

            setAlignment(Pos.CENTER_LEFT);

        }

        bubble.getChildren().addAll(
                senderLabel,
                messageLabel,
                footer
        );

        setPadding(new Insets(6));

        getChildren().add(bubble);

    }

}