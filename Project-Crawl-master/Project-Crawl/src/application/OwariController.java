package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author luke
 */
public class OwariController{

	
    //begin-------------------------//Title Methds, not linked with the modle-------------------------//
    @FXML
    private  AnchorPane ap;
    
    @FXML
    private  Label player;
    /**playEvent - This opens the stage with the owari board for the user to play against the computer
    *@param event the action event needed to trigger the method
    */
    @FXML
    public void playEvent(ActionEvent event){
        try {
        	Parent parent;
        	FXMLLoader loader = new FXMLLoader (getClass().getResource("OwariBoard.fxml"));            OwariBoardController controller = loader.getController();
        	parent = loader.load();
        	
        	OwariBoardController controller1 = loader.getController();
        	// Call the initAiController for AI Play
        	controller1.initAIController();
            
            Scene scene = new Scene(parent, Color.TRANSPARENT);
            Stage stage = new Stage();
            
            /*
             * Image is copyright free and usable 
             * link to Image : https://pixabay.com/p-32546/?no_redirect
             */
            stage.setTitle("Owari Board Game");
            stage.getIcons().add(new Image("application/gamesIcon.png"));
            
            
            stage.setScene(scene);
            stage.show();
            
            Stage currentStage = (Stage) ap.getScene().getWindow();
            currentStage.close();
        } catch (IOException ex) {
            System.out.println("IO Exception erorr");
        }
    }
    
    /**TwoPlayEvent - This opens the stage with the owari board for the user to play against another user
    *@param event the action event needed to trigger the method
    */
    @FXML
    public void twoPlayEvent(ActionEvent event){
      try {
    	  Parent parent;
      	FXMLLoader loader = new FXMLLoader (getClass().getResource("OwariBoard.fxml"));            OwariBoardController controller = loader.getController();
      	parent = loader.load();
      	
      	OwariBoardController controller1 = loader.getController();
      	controller1.initController();
          
          Scene scene = new Scene(parent, Color.TRANSPARENT);
          Stage stage = new Stage();
          
          /*
           * Image is copyright free and usable 
           * link to Image : https://pixabay.com/p-32546/?no_redirect
           */
          stage.setTitle("Owari Board Game");
          stage.getIcons().add(new Image("application/gamesIcon.png"));
          
          
          stage.setScene(scene);
          stage.show();
          
          Stage currentStage = (Stage) ap.getScene().getWindow();
          currentStage.close();
        } catch (IOException ex) {
            System.out.println("IO Exception erorr");
        }
    }
    
    /**helpEvent - This opens a help message which shows some simple instructions on how to play owari
    *@param event the action event needed to trigger the method
    */
    @FXML
    public void helpEvent(ActionEvent event){
        String text = "--Owari Rules and Regulations as shown by the Abapa Rules--\n" +
"\n" +
"-Each side starts with 4 seeds each in their 6 holes (12 holes all together)\n" +
"-Pick seeds from your side and distbute them one at a time anti-clockwise unti hand is empty (sowing)\n" +
"-if last seed distributed lands on the opponents side and makes opponents hole have 2/3 seeds, then you can take thoses seeds\n" +
"-if the hole before the hole being captured is on the opponents side and also has 2/3 seeds, you can capture thoses as well (WOMBO_COMBO)\n" +
"-a WOMBO_COMBO will not occur if the pit before the last pit was not 2/3\n" +
"-If you sow 12 seeds or more, you do not sow into the pit you picked the seeds from\n" +
"-if opponent has no seeds, player must make a move to give them seeds\n" +
"-Cannot capture opponents seed if they will be left with no seeds once capture is finished(GRAND_SLAM not allowd)\n" +
"-Winner is the one with 25 seeds or more captured";
        Alert help = new Alert(Alert.AlertType.INFORMATION, text);
        help.show();
    }
    
    /**closeEvent - This closes the application when called
    *@param event the action event needed to trigger the method
    */
    @FXML
    public void closeEvent(ActionEvent event){
      System.exit(0);
    }
    
    /**closeEvent - This minimises the application when called
    *@param event the action event needed to trigger the method
    */
    @FXML
    public void minimiseEvent(ActionEvent event){
       Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
       stage.setIconified(true);
    }
    
     @FXML
    public void turnSwitch(ActionEvent event){
       
        player.setText("?");
    }
    //-------------------------//^^Title Methds, not linked with the modle^^-------------------------end//
    
}
