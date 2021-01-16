package sample;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.net.MalformedURLException;

public class Main extends Application {
    MenuBar menu;
    Menu fileMenu;
    MenuItem openItem;
    FileChooser mediaChooser;


Player mediaPlayer;

    @Override
    public void start(Stage primaryStage) {
        try {

            BorderPane root = new BorderPane();
            Scene scene = new Scene(root, 400, 400);


            menu = new MenuBar();
            fileMenu = new Menu("file");
            menu.getMenus().add(fileMenu);

            openItem = new MenuItem("open");
            fileMenu.getItems().add(openItem);

            mediaChooser=new FileChooser();


            openItem.setOnAction(e -> {

                try {
                    File mediaFile = mediaChooser.showOpenDialog(primaryStage);
                    if (mediaPlayer!=null)
                    {
                        mediaPlayer.player.dispose();
                    }
                    mediaPlayer=new Player(mediaFile.toURI().toURL().toExternalForm());
                    mediaPlayer.view.setFitWidth(scene.getWidth());
                    root.setCenter(mediaPlayer);
                } catch (MalformedURLException malformedURLException) {
                    malformedURLException.printStackTrace();
                }
            });

            root.setTop(menu);
            primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> {
                if(mediaPlayer!=null)
                    mediaPlayer.view.setFitWidth(scene.getWidth());});
            //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            primaryStage.setTitle("Hello World");
            primaryStage.setScene(scene);
            primaryStage.show();



        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
