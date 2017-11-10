/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 *
 * @author nasimali
 */
public class OwariBoardController implements Initializable {
    
	// Model to Interact with the UI given
	BoardData model = new BoardData();
    
    @FXML
    Button buttonOne;
    
    @FXML
    Button buttonTwo;
    
    @FXML
    Button buttonThree;
    
    @FXML
    Button buttonFour;
    
    @FXML
    Button buttonFive;
    
    @FXML
    Button buttonSix;
    
    @FXML
    Button buttonSeven;
    
    @FXML
    Button buttonEight;
    
    @FXML
    Button buttonNine;
    
    @FXML
    Button buttonTen;
   
    @FXML
    Button buttonEleven;
    
    @FXML
    Button buttonTwelve;
    
    @FXML
    Button captureOneButton;
    
    @FXML
    Button captureTwoButton;
    
    @FXML
    Label playerTurn;
    
    @FXML 
    Label score;
    
    boolean firstTime = true;
    boolean playAI = false;
    
    /**
	 * Return Animation for purposes of reusing it
	 * 
	 * @param b  Button to be given to enable its animation functionalities  
	 * 
	 * @return      FadeTransition Animation - final animation of the button
	 */
    public FadeTransition getAnimObj (Button b) {
    	
    	FadeTransition ft = new FadeTransition(Duration.millis(200), b);
    	ft.setFromValue(1.0);
    	ft.setToValue(0.1);
    	ft.setAutoReverse(true);
    	ft.setFromValue(0.1);
    	ft.setToValue(1.0);
    	return ft;
    }
    
    /**
     * Generate random number in order for AI to Play
     * 
	 * @return      an integer containing a random number from 6 to 11
	 */
    public int getRandomPlay() {
    	Random random = new Random();
		int randomPlay = random.nextInt((11 - 6) + 6);
    	
    	return randomPlay;
    }
    
    
    /**
     * Disable all buttons in case of draw or victory by one of the players
   	 */
    public void disableButtons() {
    	// Button Array to get all buttons created before
    	Button[] bArray = new Button[12];
    	
    	bArray[0] = buttonSeven; 
    	bArray[1] = buttonEight;
    	bArray[2] = buttonNine;
    	bArray[3] = buttonTen;
    	bArray[4] = buttonEleven;
    	bArray[5] = buttonTwelve;
    	
    	bArray[6] = buttonSix;
    	bArray[7] = buttonFive;
    	bArray[8] = buttonFour;
    	bArray[9] = buttonThree;
    	bArray[10] = buttonTwo;
    	bArray[11] = buttonOne;
    	
    	for(int i = 0; i < 12; i++) {
    		bArray[i].setDisable(true);
    	}	
    }
    
    
    /**
   	 * Initializes Animation according to the limits given
   	 * 
   	 * @param originBurron  Button to be given to enable its animation functionalities  
   	 * @param buttonNumSeeds  Integer to correspond to one of the limits of the for loop in order to apply animation to correct buttons 
   	 */
    public void initAnimation(Button originButton, int buttonNumSeeds) {
    	
    	// Button Array to get all buttons created before
    	Button[] bArray = new Button[12];
    	
    	bArray[0] = buttonSeven; 
    	bArray[1] = buttonEight;
    	bArray[2] = buttonNine;
    	bArray[3] = buttonTen;
    	bArray[4] = buttonEleven;
    	bArray[5] = buttonTwelve;
    	
    	bArray[6] = buttonSix;
    	bArray[7] = buttonFive;
    	bArray[8] = buttonFour;
    	bArray[9] = buttonThree;
    	bArray[10] = buttonTwo;
    	bArray[11] = buttonOne;
    	
    	// Animation on Distributing Seeds
    	ParallelTransition seqT = new ParallelTransition();
    	
    	// Calculate the limit for the i value and then distribute seeds animation 
    	int limitFor = (buttonNumSeeds + model.getHouseSeeds(buttonNumSeeds));
    	for (int i = buttonNumSeeds; i <= limitFor; i++) {
    		if (i >= 11) {
    			limitFor = 0 + (model.getHouseSeeds(buttonNumSeeds) - i);
    		}
    		seqT.getChildren().add(this.getAnimObj(bArray[i]));	// Add Animations on Pararel Transition
    	}
    	
    	
    	// Priority is doing this thread
    	Thread giveSeedAnim = new Thread() {
			@Override
			public void run() {  // override the run() to specify the running behavior
				// Do whatever is inside with high priority and then call the Thread
				seqT.play();
            }
		};
			// Start Animation Once in here and only then do for the remaining values
            giveSeedAnim.start();
    }
    
    
    
    /**
   	 * Initializes all the objects inside the controller to have the data from the model
   	 * Update them all according to style and data
   	 */
    public void initController () {
    	 	
    	if(firstTime == true) {
    		int startPlayer = model.getStartPlayer();
    		if (startPlayer == 0) { // TODO: Fix This Part
    			playerTurn.setText("Player Turn: "+ 1);
    		} else if (startPlayer == 1) {
    			playerTurn.setText("Player Turn: "+ 2);
    		}	
    		firstTime = false;
    	} else {
    		model.setPlayerTurn();
    	}
    	
    	if (model.getPlayerTurn() == 0) {
    		int winner = model.checkWinner();
    		if (winner == 1) {
    			this.disableButtons(); 
    			score.setText("PLAYER 1 WON !");
    		} else if (winner == 3) {
    			this.disableButtons(); 
    			score.setText("! DRAW !");
    		} 
    		
    		score.setText("Score: " + model.getPlayerScore(0));
    	} else if (model.getPlayerTurn() == 1) {
    		int winner = model.checkWinner();
    		if (winner == 2) {
    			this.disableButtons(); 
    			score.setText("PLAYER 2 WON !");
    		} else if (winner == 3) {
    			this.disableButtons(); 
    			score.setText("! DRAW !");
    		}
    		
    		score.setText("Score: " + model.getPlayerScore(1));
    	}
    	
    	buttonOne.setText("" + model.getHouseSeeds(11) + "");
    	buttonTwo.setText("" + model.getHouseSeeds(10) + "");
    	buttonThree.setText("" + model.getHouseSeeds(9) + "");
    	buttonFour.setText("" + model.getHouseSeeds(8) + "");
    	buttonFive.setText("" + model.getHouseSeeds(7) + "");
    	buttonSix.setText("" + model.getHouseSeeds(6) + "");
    	
    	buttonSeven.setText("" + model.getHouseSeeds(0) + "");
    	buttonEight.setText("" + model.getHouseSeeds(1) + "");
    	buttonNine.setText("" + model.getHouseSeeds(2) + "");
    	buttonTen.setText("" + model.getHouseSeeds(3) + "");
    	buttonEleven.setText("" + model.getHouseSeeds(4) + "");
    	buttonTwelve.setText("" + model.getHouseSeeds(5) + "");
    
    	captureOneButton.setText("Player 1 Score: \n" + model.getPlayerScore(0));
    	captureTwoButton.setText("Player 2 Score: \n" + model.getPlayerScore(1));
    	
    	// Some Animations Over Here
    	if (model.getPlayerTurn() == 0) {
    		
    		// If is player 1 Turn then change color of his buttons back to normal 
    		buttonSeven.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #fff7ab;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
    		buttonEight.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #fff7ab;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
        	buttonNine.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #fff7ab;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
        	buttonTen.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #fff7ab;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
        	buttonEleven.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #fff7ab;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
        	buttonTwelve.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #fff7ab;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
    	
        	// Buttons for Player 2 to Another Color
        	buttonOne.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #6C7A89;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
        	buttonTwo.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #6C7A89;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
        	buttonThree.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #6C7A89;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
        	buttonFour.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #6C7A89;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
        	buttonFive.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #6C7A89;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
        	buttonSix.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #6C7A89;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
    		
        	captureOneButton.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #fff7ab;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
        	captureOneButton.setPrefSize(137.0, 126.0);
        	captureTwoButton.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #6C7A89;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
        	captureTwoButton.setPrefSize(137.0, 126.0);
    		
    	} else if (model.getPlayerTurn() == 1) {
    		
    		
    		buttonSeven.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #6C7A89;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
    		buttonEight.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #6C7A89;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
        	buttonNine.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #6C7A89;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
        	buttonTen.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #6C7A89;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
        	buttonEleven.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #6C7A89;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
        	buttonTwelve.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #6C7A89;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
    		

        	buttonOne.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #fff7ab;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
        	buttonTwo.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #fff7ab;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
        	buttonThree.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #fff7ab;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
        	buttonFour.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #fff7ab;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
        	buttonFive.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #fff7ab;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
        	buttonSix.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #fff7ab;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
    		
        	captureOneButton.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #6C7A89;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
        	captureOneButton.setPrefSize(137.0, 126.0);
        	captureTwoButton.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #fff7ab;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
        	captureTwoButton.setPrefSize(137.0, 126.0);
    	}
    }
    
    
    /**
   	 * Initializes all the objects inside the controller to have the data from the model
   	 * Update them all according to style and data
   	 * This is for use only with AI gameplay
   	 */
    public void initAIController () {
    	playAI = true;
    	
    	if(firstTime == true) {
    		int startPlayer = model.getPlayerTurn();
    		if (startPlayer == 0) { // TODO: Fix This Part
    			playerTurn.setText("Player Turn: "+ 1);
    		} else if (startPlayer == 1) {
    			playerTurn.setText("Player Turn: "+ 2);
    		}	
    		firstTime = false;
    	} else {
    		model.setPlayerTurn();
    	}
    	
    	if (model.getPlayerTurn() == 0) {
    		int winner = model.checkWinner();
    		if (winner == 1) {
    			this.disableButtons(); 
    			score.setText("PLAYER 1 WON !");
    		} else if (winner == 3) {
    			this.disableButtons(); 
    			score.setText("! DRAW !");
    		} 
    		
    		score.setText("Score: " + model.getPlayerScore(0));
    	} else if (model.getPlayerTurn() == 1) {
    		int winner = model.checkWinner();
    		if (winner == 2) {
    			this.disableButtons(); 
    			score.setText("PLAYER 2 WON !");
    		} else if (winner == 3) {
    			this.disableButtons(); 
    			score.setText("! DRAW !");
    		}
    		
    		score.setText("Score: " + model.getPlayerScore(1));
    	}
    	
    	buttonOne.setText("" + model.getHouseSeeds(11) + "");
    	buttonTwo.setText("" + model.getHouseSeeds(10) + "");
    	buttonThree.setText("" + model.getHouseSeeds(9) + "");
    	buttonFour.setText("" + model.getHouseSeeds(8) + "");
    	buttonFive.setText("" + model.getHouseSeeds(7) + "");
    	buttonSix.setText("" + model.getHouseSeeds(6) + "");
    	
    	buttonSeven.setText("" + model.getHouseSeeds(0) + "");
    	buttonEight.setText("" + model.getHouseSeeds(1) + "");
    	buttonNine.setText("" + model.getHouseSeeds(2) + "");
    	buttonTen.setText("" + model.getHouseSeeds(3) + "");
    	buttonEleven.setText("" + model.getHouseSeeds(4) + "");
    	buttonTwelve.setText("" + model.getHouseSeeds(5) + "");
    
    	captureOneButton.setText("Player 1 Score: \n" + model.getPlayerScore(0));
    	captureTwoButton.setText("Player 2 Score: \n" + model.getPlayerScore(1));
    	
    	// Some Animations Over Here
    	if (model.getPlayerTurn() == 0) {
    		
    		// If is player 1 Turn then change color of his buttons back to normal 
    		buttonSeven.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #fff7ab;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
    		buttonEight.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #fff7ab;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
        	buttonNine.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #fff7ab;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
        	buttonTen.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #fff7ab;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
        	buttonEleven.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #fff7ab;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
        	buttonTwelve.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #fff7ab;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
    	
        	// Buttons for Player 2 to Another Color
        	buttonOne.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #6C7A89;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
        	buttonTwo.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #6C7A89;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
        	buttonThree.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #6C7A89;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
        	buttonFour.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #6C7A89;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
        	buttonFive.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #6C7A89;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
        	buttonSix.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #6C7A89;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
    		
        	captureOneButton.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #fff7ab;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
        	captureOneButton.setPrefSize(137.0, 126.0);
        	captureTwoButton.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #6C7A89;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
        	captureTwoButton.setPrefSize(137.0, 126.0);
    		
    	} else if (model.getPlayerTurn() == 1) {
    		
    		
    		buttonSeven.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #6C7A89;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
    		buttonEight.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #6C7A89;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
        	buttonNine.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #6C7A89;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
        	buttonTen.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #6C7A89;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
        	buttonEleven.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #6C7A89;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
        	buttonTwelve.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #6C7A89;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
    		

        	buttonOne.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #fff7ab;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
        	buttonTwo.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #fff7ab;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
        	buttonThree.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #fff7ab;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
        	buttonFour.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #fff7ab;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
        	buttonFive.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #fff7ab;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
        	buttonSix.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #fff7ab;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
    		
        	captureOneButton.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #6C7A89;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
        	captureOneButton.setPrefSize(137.0, 126.0);
        	captureTwoButton.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: #fff7ab;-fx-border-color: black; -fx-border-radius: 50 50 50 50;");
        	captureTwoButton.setPrefSize(137.0, 126.0);
    	}
    }
    
    
    
    
    
    @FXML 
    public void buttonOneEvent (ActionEvent a){
        if (playerTurn.getText().equals("Player Turn: "+2) && model.getHouseSeeds(11) > 0 && playAI == false){
        	
        	// If Player 2 Pressed the Button
        	this.initAnimation(buttonSeven, 11);
        	
            model.sowSeeds(11);
            this.initController();
             	
             	
             playerTurn.setText("Player Turn: "+ 1);
        } 
    }
    
    @FXML 
    public void buttonTwoEvent (ActionEvent a){
    	if (playerTurn.getText().equals("Player Turn: "+2) && model.getHouseSeeds(10) > 0 && playAI == false){
        	
        	// If Player 2 Pressed the Button
    		this.initAnimation(buttonSeven, 10);
    		
            model.sowSeeds(10);
            this.initController();
             	
             	
             playerTurn.setText("Player Turn: "+ 1);
        } 
    }
    
    @FXML 
    public void buttonThreeEvent (ActionEvent a){
    	if (playerTurn.getText().equals("Player Turn: "+2) && model.getHouseSeeds(9) > 0 && playAI == false){
        	
        	// If Player 2 Pressed the Button
    		this.initAnimation(buttonSeven, 9);
    		
            model.sowSeeds(9);
            this.initController();
             	
             	
             playerTurn.setText("Player Turn: "+ 1);
        } 
    }
    
    @FXML 
    public void buttonFourEvent (ActionEvent a){
    	if (playerTurn.getText().equals("Player Turn: "+2) && model.getHouseSeeds(8) > 0 && playAI == false){
        	
        	// If Player 2 Pressed the Button
    		this.initAnimation(buttonSeven, 8);
    		
            model.sowSeeds(8);
            this.initController();
             	
             	
             playerTurn.setText("Player Turn: "+ 1);
        } 
    }
    
    @FXML 
    public void buttonFiveEvent (ActionEvent a){
    	if (playerTurn.getText().equals("Player Turn: "+2) && model.getHouseSeeds(7) > 0 && playAI == false){
        	
        	// If Player 2 Pressed the Button
    		this.initAnimation(buttonSeven, 7);
    		
            model.sowSeeds(7);
            this.initController();
             	
             	
            playerTurn.setText("Player Turn: "+ 1);
        } 
    }
    
    @FXML 
    public void buttonSixEvent (ActionEvent a){
    	if (playerTurn.getText().equals("Player Turn: "+2) && model.getHouseSeeds(6) > 0 && playAI == false){
        	// If Player 2 Pressed the Button	
            
    		this.initAnimation(buttonSeven, 6);
    		
    		model.sowSeeds(6);
            this.initController();
             	
             	
             playerTurn.setText("Player Turn: "+ 1);
        } 
    }
    
    @FXML 
    public void buttonSevenEvent (ActionEvent a){
        if (playerTurn.getText().equals("Player Turn: "+1) && model.getHouseSeeds(0) > 0){
        	// If Player 1 Pressed the Button
    
        
			this.initAnimation(buttonSeven, 0);
			
        	model.sowSeeds(0);
        	this.initController();
        	
        	playerTurn.setText("Player Turn: "+ 2);
        	
        	// Enable AI Play
        	if (playAI == true) {
        		
        		int temp = this.getRandomPlay();
        		// AI Has to Press the Button
        		
        		
        		this.initAnimation(buttonSeven, temp);
        		
                model.sowSeeds(temp);
                this.initController();
                 	
                 	
                playerTurn.setText("Player Turn: "+ 1);
        	}
        	// End AI Play
        	
        } 
    }
    
    @FXML 
    public void buttonEightEvent (ActionEvent a){
    	 if (playerTurn.getText().equals("Player Turn: "+1) && model.getHouseSeeds(1) > 0){
         	// If Player 1 Pressed the Button
         	
    		this.initAnimation(buttonSeven, 1);
    		 
         	model.sowSeeds(1);
         	this.initController();
         	
         	
         	playerTurn.setText("Player Turn: "+ 2);
         	
         	// Enable AI Play
        	if (playAI == true) {
        		int temp = this.getRandomPlay();
        		// AI Has to Press the Button
        		
        		
        		this.initAnimation(buttonSeven, temp);
        		
                model.sowSeeds(temp);
                this.initController();
                 	
                 	
                playerTurn.setText("Player Turn: "+ 1);
        	}
        	// End AI Play
         } 
    }
    
    @FXML 
    public void buttonNineEvent (ActionEvent a){
    	 if (playerTurn.getText().equals("Player Turn: "+1) && model.getHouseSeeds(2) > 0){
         	// If Player 1 Pressed the Button
         	
    		this.initAnimation(buttonSeven, 2);
    		 
         	model.sowSeeds(2);
         	this.initController();
         	
         	
         	playerTurn.setText("Player Turn: "+ 2);
         	
         	// Enable AI Play
        	if (playAI == true) {
        		int temp = this.getRandomPlay();
        		// AI Has to Press the Button
        		
        		
        		this.initAnimation(buttonSeven, temp);
        		
                model.sowSeeds(temp);
                this.initController();
                 	
                 	
                playerTurn.setText("Player Turn: "+ 1);
        	}
        	// End AI Play
         } 
    }
    
    @FXML 
    public void buttonTenEvent (ActionEvent a){
    	 if (playerTurn.getText().equals("Player Turn: "+1) && model.getHouseSeeds(3) > 0){
         	// If Player 1 Pressed the Button
         	
    		this.initAnimation(buttonSeven, 3);
    		 
         	model.sowSeeds(3);
         	this.initController();
         	
         	
         	playerTurn.setText("Player Turn: "+ 2);
         	
         	// Enable AI Play
        	if (playAI == true) {
        		int temp = this.getRandomPlay();
        		// AI Has to Press the Button
        		
        		
        		this.initAnimation(buttonSeven, temp);
        		
                model.sowSeeds(temp);
                this.initController();
                 	
                 	
                playerTurn.setText("Player Turn: "+ 1);
        	}
        	// End AI Play
         } 
    }
    
    @FXML 
    public void buttonElevenEvent (ActionEvent a){
    	 if (playerTurn.getText().equals("Player Turn: "+1) && model.getHouseSeeds(4) > 0){
         	// If Player 1 Pressed the Button
         	
    		this.initAnimation(buttonSeven, 4); 
    		 
         	model.sowSeeds(4);
         	this.initController();
         	
         	
         	playerTurn.setText("Player Turn: "+ 2);
         	
         	// Enable AI Play
        	if (playAI == true) {
        		int temp = this.getRandomPlay();
        		// AI Has to Press the Button
        		
        		
        		this.initAnimation(buttonSeven, temp);
        		
                model.sowSeeds(temp);
                this.initController();
                 	
                 	
                playerTurn.setText("Player Turn: "+ 1);
        	}
        	// End AI Play
         } 
    }
    
    @FXML 
    public void buttonTwelveEvent (ActionEvent a){
    	 if (playerTurn.getText().equals("Player Turn: "+1) && model.getHouseSeeds(5) > 0){
         	// If Player 1 Pressed the Button
         	
    		this.initAnimation(buttonSeven, 5); 
    		 
         	model.sowSeeds(5);
         	this.initController();
         	
         	
         	playerTurn.setText("Player Turn: "+ 2);
         	
         	// Enable AI Play
        	if (playAI == true) {
        		int temp = this.getRandomPlay();
        		// AI Has to Press the Button
        		
        		
        		this.initAnimation(buttonSeven, temp);
        		
                model.sowSeeds(temp);
                this.initController();
                 	
                 	
                playerTurn.setText("Player Turn: "+ 1);
        	}
        	// End AI Play
         } 
    }
    
    @FXML 
    public void captureOneEvent (ActionEvent a){
        
    }
    
    @FXML 
    public void captureTwoEvent (ActionEvent a){
       
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
