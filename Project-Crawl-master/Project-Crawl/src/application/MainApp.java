/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author nasimali
 */
public class MainApp extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
    	
        Stage owarPage;
        Parent parent;
        owarPage = new Stage();
        owarPage.initStyle(StageStyle.TRANSPARENT);
        FXMLLoader loader = new FXMLLoader (getClass().getResource("OwariTitle.fxml"));
        parent = loader.load();
        OwariController controller = loader.getController();
        owarPage.setTitle("Owari Main Menu");
        owarPage.setScene(new Scene(parent, Color.TRANSPARENT));
        owarPage.show();
        
    }
     
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

