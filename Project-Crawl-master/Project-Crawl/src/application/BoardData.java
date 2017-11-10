package application;

import java.util.Random;

/**
* This Class provides all the data needed in order to play the game Oware
* It contains different kinds of method to retrieve data and to manipulate it in order to
* respect and fulfill the rules of the game.
*
* @version 1.0
* @since   2016-11-8
*/
public class BoardData {
	
	private int[] boardHouses = new int[12]; // Array Representing the 12 houses of the board
	private int[] playerScores = new int[2]; // Array Representing the extra 2 houses to save both player scores
	private int playerTurn; // Defines who is going to play now

	
	public BoardData() {
		//Initialize  boardHouses array to put 4 seeds inside each house initially
		for (int i=0; i<12; i++){
			boardHouses[i] = 4;
		}
		
		// Initialize Both Player Scores to 0
		playerScores[0] = 0;
		playerScores[1] = 0;
		
		boardHouses[0] = 1;
		boardHouses[1] = 1;
		
		
	}
	
	/**
	 * Decide who plays first trough random number
	 * @return      playerTurn - corresponds to the current player turn
	 */
	public int getStartPlayer() {
		Random random = new Random();
		int randomPlayer = random.nextInt(100);
		// 50/50 percent chance for each player = Based on probability to get chosen
		if (randomPlayer < 50){
			playerTurn = 0; // Player 1 Turn
			return playerTurn;
		} else {
			playerTurn = 1; // Player 2 Turn
			return playerTurn;
		}
	}
	
	/**
	 * Method for player moves to distribute seeds around the houses
	 *
	 * @param  housePos  an integer containing the position of the house we pressed
	 */
	public void sowSeeds(int housePos) {
		
		if (getHouseSeeds(housePos) > 0) {
			
			int j = housePos; // Control house positions
			// While current boardHouse has seeds
			while(boardHouses[housePos] > 0) {
				
				// In case we get to the same house again after adding 12 seeds to all remaining houses
				if (j == housePos) {
					boardHouses[j++]++;
					boardHouses[housePos]--;
				} else {
					// Pass trough final house
					if(j > 11) {
						j = 0; // Goes Back to beginning
					} else {
						boardHouses[j++]++; // Increase House Position and increment its seeds 
						boardHouses[housePos]--; // Decrement Seeds from original house
					}
				}	
			}
			/// Debugging Purposes ///
			//System.out.println("J Value after finished is --> " + j);
			//System.out.println("housePos Value after --> " + (j - 1));
			/// Debugging Purposes ///
			
			// After sowing check for captured seeds
			captureSeed(playerTurn, (j - 1));
		} else {
			//Means its Equal to 0 so No Sowing or any Action is Allowed
			// TODO: Maybe Display Message Box Over Here warning the User about this Action
		}
	}
	
	
	/**
	 *  Private void capture seed - Capture Seeds of the Opponent
	 *  Will be called inside sowing automatically when it detects a capture 
	 *
	 * @param  currentPlayer  an integer containing the current player information
	 * @param  lastHousePos   an integer containing the last position of the last house who got the sow
	 */
	private void captureSeed(int currentPlayer, int lastHousePos) {
		// Capture depends on the current player (Player 1 -> Check housePos from 5 to 11 | Player 2 -> Check housePos from 0 to 5)
		
		int[] prePlayerScore = new int[12]; // Integer Array to Store the Player's Score before Adding/Removing to the proper variable
		int houseEnd = 0, houseStart = 0, i = 0; // Loop Control for checking houses
		
		// Houses to check for capture depend on current player and the lastHousePosition
		if (currentPlayer == 0 && lastHousePos < 11) {
			houseStart = 5;
			houseEnd = lastHousePos;
		} else if (currentPlayer == 1 && lastHousePos < 6) {
			houseStart = 0;
			houseEnd = lastHousePos;
		} 
		System.out.println("HOUSE END -> " + houseEnd);
		// Check if Last House that received seeds has either 2 or 3 Seeds and More Conditions about the Players Turn
		if ((getHouseSeeds(houseEnd) == 2 || getHouseSeeds(houseEnd) == 3) && ((currentPlayer == 0 && (houseEnd > 5 && houseEnd <= 11)) || (currentPlayer == 1 && (houseEnd < 6 && houseEnd >= 0)))){
			
			// Add seeds to correct prePlayerScore Array
			prePlayerScore[houseEnd] = boardHouses[houseEnd]; 
			// Take all the seeds from the house
			boardHouses[houseEnd] -= boardHouses[houseEnd]; 
			// Decrement houseEnd for effect of Loop
			houseEnd--;
			
			//Check the boardHouses before for more capture, based on set up information above
			for (i = houseEnd; i > houseStart; i--){
				
				/// Debugging Purposes ///
				//System.out.println("Value of I for testing capture -> " + i);
				/// Debugging Purposes ///
				
				// If we get consecutive houses before last one with either 2 or 3 
				if (getHouseSeeds(i) == 2 || getHouseSeeds(i) == 3){
					prePlayerScore[i] = boardHouses[i];
					boardHouses[i] -= boardHouses[i]; 
				} else {
					// Consecutive 2 or 3 just stopped so no more capture allowed - Get out of the loop
					break;
				}
			}
			
			// Condition if we are able to capture all of the Opponents Seeds
			// Means If the i is on the final House of either player 1 or player 2
			if (i == (houseStart + 1) && currentPlayer == 0) { // CHECK THIS VALUE AFTERWARDS
				
				/// Debugging Purposes ///
				//System.out.println("House End -> " + (houseEnd + 1));
				//System.out.println("I Value -> " + i);
				/// Debugging Purposes ///
				
				// Add 1 more to house End to make the loop go to every Place
				houseEnd += 1;
				// For Loop to distribute all the Seeds around the Opponent and Take out his Added Score he could have
				for (int j = i; j <= houseEnd; j++) { 
					boardHouses[j] += prePlayerScore[j]; // Houses come back with All The Seeds Again
					prePlayerScore[j] = 0; // Final Score Set to 0
				}
				
			} else if (i == (houseStart) && currentPlayer == 1) { // CHECK THIS VALUE AFTERWARDS
				
				/// Debugging Purposes ///
				//System.out.println("House End -> " + (houseEnd + 1));
				//System.out.println("I Value -> " + i);
				/// Debugging Purposes ///
				
				// Add 1 more to house End to make the loop go to every Place
				houseEnd += 1;
				// For Loop to distribute all the Seeds around the Opponent and Take out his Added Score he could have
				for (int j = i; j <= houseEnd; j++) { 
					boardHouses[j] += prePlayerScore[j]; // Houses come back with All The Seeds Again
					prePlayerScore[j] = 0; // Final Score Set to 0
				}
			}
		
		// Don't check anymore 	
		} else { 
			// Stop method = Nothing to capture
			return;
		}
		
		// Add Final Modified Score to the PlayerScore
		// Add seeds to correct playerScore
		for (int j = 0; j < 12; j++) {
			// Add all the score saved on the Array to the Player
			playerScores[playerTurn] += prePlayerScore[j];
		} 
	}
	
	
	/**
	 *  Checks for Winner based on his current score 
	 *  Can also declare a draw 
	 *  
	 * @return an integer containing information about who won or if it is a draw
	 */
	public int checkWinner(){
		if (playerScores[0] >= 25){
			return 1; // Player 1 Won
		}
		else if (playerScores[1] >= 25){
			return 2; // Player 2 Won
		}
		else if (playerScores[0] == 24 && playerScores[1] == 24){
			return 3; // Draw Obtained
		}
		return 0;
	}
	
	/**
	 *  Changes the turn of the current player to the other 
	 *  
	 * @return an integer containing information about who is the player that is going to play now
	 */
	public int setPlayerTurn() {
		// If Current Player is Player 1 that means Player 2 will play next
		if (playerTurn == 0) {
			playerTurn = 1;
		// Else means its player 1 turn
		} else {
			playerTurn = 0;
		}
		return playerTurn;
	}
	
	/**
	 *  Gets the current Player Turn
	 *  
	 * @return an integer containing information about who is current player playing
	 */
	public int getPlayerTurn() {
		return playerTurn;
	}
	
	/**
	 *  Gets the number of seeds in the desired house
	 *  
	 * @param house integer containing the house position
	 *  
	 * @return an integer containing the number of seeds inside the given house
	 */
	public int getHouseSeeds(int house){
		return boardHouses[house];
	}
	
	/**
	 *  Gets the current score of the desired player
	 *  
	 * @param player integer indicating which player we want to get information from
	 *  
	 * @return an integer containing the current score of the given player
	 */
	public int getPlayerScore(int player) {
		return playerScores[player];
	}

}
