package ui;


import crypto.CryptoUtils;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import models.*;

import network.Client;

import storage.HistoryManager;

import ui.components.MessageBubble;
import ui.components.UserCard;


public class ChatWindow {


    private String selectedUser = null;


    private VBox usersPane;

    private VBox messagesBox;

    private ScrollPane messagesPane;

    private Label chatTitle;



    public void show(Stage stage){


        String username =
                Session.getUsername();



        /*
         ==========================
             LEFT USER SIDEBAR
         ==========================
        */


        TextField search =
                new TextField();


        search.setPromptText(
                "Search users..."
        );


        search.setPrefHeight(35);



        usersPane =
                new VBox();


        usersPane.setSpacing(8);

        usersPane.setPadding(
                new Insets(10)
        );


        usersPane.setStyle(
                "-fx-background-color:#202225;"
        );



        ScrollPane usersScroll = new ScrollPane(usersPane);

        usersScroll.setStyle(
                    "-fx-background-color:#202225;" +
                    "-fx-background:#202225;"
            );

            usersPane.setStyle(
                    "-fx-background-color:#202225;"
            );


        usersScroll.setStyle(
                "-fx-background:#202225;" +
                "-fx-background-color:#202225;"
        );


        usersScroll.setFitToWidth(true);



        usersScroll.setFitToWidth(true);



        usersScroll.setPrefWidth(260);



        usersScroll.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );



        VBox leftSide =
                new VBox(
                        search,
                        usersScroll
                );


        VBox.setVgrow(
                usersScroll,
                Priority.ALWAYS
        );





        /*
         ==========================
              CHAT HEADER
         ==========================
        */


        chatTitle =
                new Label(
                        "Select a user"
                );


        chatTitle.setStyle(
                "-fx-font-size:20;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:white;"
        );



        Label online =
                new Label(
                        "Online"
                );


        online.setStyle(
                "-fx-text-fill:#43B581;"
        );



        VBox header =
                new VBox(
                        chatTitle,
                        online
                );


        header.setSpacing(5);


        header.setPadding(
                new Insets(15)
        );


        header.setStyle(
                "-fx-background-color:#2B2D31;"
        );





        /*
         ==========================
             MESSAGE AREA
         ==========================
        */


        messagesBox =
                new VBox();


        messagesBox.setSpacing(10);


        messagesBox.setPadding(
                new Insets(15)
        );



        messagesPane =
                new ScrollPane(
                        messagesBox
                );

                messagesPane.setStyle(
                            "-fx-background-color:#202225;" +
                            "-fx-background:#202225;"
                    );

                    messagesBox.setStyle(
                            "-fx-background-color:#202225;"
                    );



        messagesPane.setFitToWidth(true);



        messagesPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );



        messagesPane.setStyle(
                "-fx-background:#202225;"
        );





        /*
         ==========================
              INPUT AREA
         ==========================
        */


        TextField input =
                new TextField();
        input.setStyle(
            "-fx-background-color:#313338;" +
            "-fx-text-fill:white;" +
            "-fx-prompt-text-fill:#AAAAAA;"
        );


        input.setPromptText(
                "Type a message..."
        );



        HBox.setHgrow(
                input,
                Priority.ALWAYS
        );



        Button send =
                new Button(
                        "Send"
                );



        Button clearHistory =
                new Button(
                        "Clear History"
                );

        send.setStyle(
                        "-fx-background-color:#5865F2;" +
                        "-fx-text-fill:white;"
                );


                clearHistory.setStyle(
                        "-fx-background-color:#313338;" +
                        "-fx-text-fill:white;"
                );

        HBox bottom =
                new HBox(
                        10,
                        input,
                        send,
                        clearHistory
                );


        bottom.setPadding(
                new Insets(10)
        );





        BorderPane center =
                new BorderPane();



        center.setTop(
                header
        );


        center.setCenter(
                messagesPane
        );


        center.setBottom(
                bottom
        );





        BorderPane root =
                new BorderPane();



        root.setLeft(
                leftSide
        );


        root.setCenter(
                center
        );



        root.setStyle(
                "-fx-background-color:#202225;" +
                "-fx-control-inner-background:#202225;"
        );



        /*
         Search system
        */


        search.textProperty()
        .addListener(
                (obs,oldValue,newValue)->{


                    for(javafx.scene.Node node:
                            usersPane.getChildren()){


                        if(node instanceof UserCard card){


                            boolean visible =
                                    card.getUsername()
                                    .toLowerCase()
                                    .contains(
                                      newValue.toLowerCase()
                                    );


                            card.setVisible(
                                    visible
                            );


                            card.setManaged(
                                    visible
                            );

                        }

                    }

                }
        );
                /*
         ==========================
              CLIENT CONNECTION
         ==========================
        */


        Client client =
                new Client(

                        username,


                        packet -> {


                            Platform.runLater(() -> {



                                /*
                                 =====================
                                      USER LIST
                                 =====================
                                */


                                if(
                                    packet.getType()
                                    ==
                                    MessageType.USER_LIST
                                ){



                                    usersPane
                                    .getChildren()
                                    .clear();



                                    String[] users =
                                            packet.getMessage()
                                            .split(",");



                                    for(String user : users){



                                        if(
                                            user.equals(username)
                                        )
                                            continue;



                                        UserCard card =
                                                new UserCard(
                                                        user,
                                                        true
                                                );



                                        card.setOnMouseClicked(e -> {



                                            selectedUser =
                                                    user;



                                            chatTitle
                                            .setText(
                                                    user
                                            );



                                            messagesBox
                                            .getChildren()
                                            .clear();



                                            /*
                                             Future:
                                             Load history here
                                            */


                                        });



                                        usersPane
                                        .getChildren()
                                        .add(card);



                                    }



                                    return;

                                }







                                /*
                                 =====================
                                      TEXT MESSAGE
                                 =====================
                                */


                                if(
                                    packet.getType()
                                    ==
                                    MessageType.TEXT
                                ){



                                    String decrypted =
                                            CryptoUtils.decrypt(
                                                    packet.getMessage()
                                            );



                                    messagesBox
                                    .getChildren()
                                    .add(


                                        new MessageBubble(

                                                packet.getSender(),

                                                decrypted,

                                                "Now",

                                                false

                                        )

                                    );



                                    messagesPane
                                    .setVvalue(1.0);



                                    return;

                                }



                            });



                        }

                );







        /*
         ==========================
              SEND MESSAGE
         ==========================
        */


        send.setOnAction(e -> {



            if(selectedUser == null){


                return;

            }




            String text =
                    input.getText();



            if(text.isBlank())
                return;






            String encrypted =
                    CryptoUtils.encrypt(
                            text
                    );







            ChatPacket packet =
                    new ChatPacket(

                            username,

                            selectedUser,

                            encrypted,

                            MessageType.TEXT

                    );





            client.send(
                    packet
            );





            messagesBox
            .getChildren()
            .add(



                    new MessageBubble(

                            "You",

                            text,

                            "Now",

                            true

                    )



            );



            messagesPane
            .setVvalue(1.0);



            input.clear();



        });







        /*
         ==========================
              CLEAR HISTORY
         ==========================
        */


        clearHistory.setOnAction(e -> {



            if(selectedUser == null)
                return;




            HistoryManager.clearHistory(

                    username,

                    selectedUser

            );



            messagesBox
            .getChildren()
            .clear();



        });
                /*
         ==========================
              SHOW WINDOW
         ==========================
        */


        Scene scene =
                new Scene(
                        root,
                        1100,
                        700
                );

                var css =
        getClass()
        .getResource("dark.css");


if(css != null){

    scene.getStylesheets()
            .add(css.toExternalForm());

}
       

        stage.setScene(
                scene
        );



        stage.setTitle(
                "JavaFXEncryptor 2.0 - "
                +
                username
        );



        stage.show();



    }


}