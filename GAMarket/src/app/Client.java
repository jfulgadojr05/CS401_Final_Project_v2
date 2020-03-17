package sample.GAMarket.src.app;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


import javafx.scene.control.TextField;

public class Client extends Application {
    private Stage window;

    private String username;
    private String password;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        //client gui
        window = primaryStage;
        window.setTitle("GAMarket");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        Button store = new Button("Store");
        GridPane.setConstraints(store, 0, 0);


        Button collection = new Button("Collection");
        GridPane.setConstraints(collection, 1, 0);

        Button userProfile = new Button("Profile");
        GridPane.setConstraints(userProfile, 2, 0);

        Button chat = new Button("Chat"); //possibly friends list

        GridPane.setConstraints(chat, 3, 0);

        grid.getChildren().addAll(store, collection, userProfile, chat);

        Scene scene = new Scene(grid, 300, 200);
        scene.getStylesheets().add("app/clientGUI.css");
        window.setScene(scene);
        window.show();

    }

    void goToStore(){

    };

}
