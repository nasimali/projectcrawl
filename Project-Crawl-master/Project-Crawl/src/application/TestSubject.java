package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class TestSubject extends Application {
	@Override
	public void start(Stage primaryStage) {
	
			// Used for testing with different aspects in the BoardData
			// Testing values and circumstances used all described
		
			BoardData bd = new BoardData();
			
			// Initial Board how many seeds 
			// 4 seeds in each house = Total 12 -> GOOD !
			
			// Random Player Start -> GOOD !
			//System.out.println(bd.getStartPlayer());
			
			// Sowing Method Testing --> 11 is the Last House -> GOOD !
			// Works after Having 12 Seeds in a house ! Skips his own house -> GOOD !
			/*bd.sowSeeds(6);
			bd.sowSeeds(7);
			bd.sowSeeds(8);
			bd.sowSeeds(9);
			bd.sowSeeds(10);
			bd.sowSeeds(11);
			bd.sowSeeds(1);
			bd.sowSeeds(2);
			bd.sowSeeds(3);
			bd.sowSeeds(4);
			bd.sowSeeds(5);
			bd.sowSeeds(0);*/
			
			
			
			// Use This on Main to see Results based on Constructor Testing
			//bd.sowSeeds(5);
			//System.out.println("PLAYER SCORE 1 SHOULD BE 7 -> " + bd.getPlayerScore(1));
			//System.out.println("House Seeds -> " + 10 + " HAS -> " + bd.getHouseSeeds(10) + " <-- ");
			//System.out.println("House Seeds -> " + 11 + " HAS -> " + bd.getHouseSeeds(11) + " <-- ");
			//System.out.println("House Seeds -> " + 8 + " HAS -> " + bd.getHouseSeeds(8) + " <-- ");
			
			// Simple Capture -- Test on Board Data Constructor -> GOOD !
			//playerTurn = 1;
			//boardHouses[9] = 1;
			// Player 1 receives 2 Seeds
			
			// Double Capture -- Test on Board Data Constructor -> GOOD !
			//playerTurn = 1;
			//boardHouses[8] = 1;
			//boardHouses[9] = 1;
			// Player 1 receives 4 Seeds
			
			// Triple Capture with 2 and 3 -- Test on Board Data Constructor -> GOOD !
			//playerTurn = 1;
			//boardHouses[7] = 2;
			//boardHouses[8] = 1;
			//boardHouses[9] = 1;
			// Player 1 receives 7 Seeds 
			
			// Capture at the End, ending on the opponent houses -- Test on Board Data Constructor -> GOOD !
			//playerTurn = 1;
			//boardHouses[5] = 6;
			//boardHouses[10] = 1;
			//boardHouses[11] = 1;
			// Player 1 receives 4 Seeds 
			
			// Capture at the End with 2 seeds on a Non Consecutive Sequence -- Test on Board Data Constructor -> GOOD !
			//playerTurn = 1;
			//boardHouses[5] = 6;
			//boardHouses[8] = 1;
			//boardHouses[10] = 1;
			//boardHouses[11] = 1;
			// Player 1 receives 4 Seeds
			
			
			// Capture Everything Else from Opponent Constraint, Every House of Opponent should have 2 or 3 Seeds -> GOOD !
			//playerTurn = 1;
			//boardHouses[5] = 6;
			//boardHouses[6] = 2;
			//boardHouses[7] = 1;
			//boardHouses[8] = 2;
			//boardHouses[9] = 1;
			//boardHouses[10] = 1;
			//boardHouses[11] = 2;
			// Player 1 receives 0 Seeds
			
			// Capture Everything Else from Opponent Constraint, Test It by finishing on the other Side of the Board -> GOOD !
			//playerTurn = 1;
			//boardHouses[5] = 6;
			//boardHouses[6] = 2;
			//boardHouses[7] = 1;
			//boardHouses[8] = 2;
			//boardHouses[9] = 1;
			//boardHouses[10] = 1;
			//boardHouses[11] = 2;
			// Player 1 receives 0 Seeds
			
			// Capture Nothing since it stops on the opponents Board -> GOOD !
			//playerTurn = 1;
			//boardHouses[5] = 7;
			
			//boardHouses[6] = 2;
			//boardHouses[7] = 1;
			//boardHouses[8] = 2;
			//boardHouses[9] = 1;
			//boardHouses[10] = 1;
			//boardHouses[11] = 2;
			//boardHouses[0] = 2;
			// Player 1 still receives 0 Seeds
			
			// Do All the Tests above for Player 2 and Check it's results -> GOOD !
			
			/*
			bd.sowSeeds(11);
			
			System.out.println("PLAYER SCORE 2 SHOULD BE X -> " + bd.getPlayerScore(1));
			
			System.out.println("\n");
			System.out.println("House Seeds -> " + 0 + " HAS -> " + bd.getHouseSeeds(0) + " <-- ");
			System.out.println("House Seeds -> " + 1 + " HAS -> " + bd.getHouseSeeds(1) + " <-- ");
			System.out.println("House Seeds -> " + 2 + " HAS -> " + bd.getHouseSeeds(2) + " <-- ");
			System.out.println("House Seeds -> " + 3 + " HAS -> " + bd.getHouseSeeds(3) + " <-- ");
			System.out.println("House Seeds -> " + 4 + " HAS -> " + bd.getHouseSeeds(4) + " <-- ");
			System.out.println("House Seeds -> " + 5 + " HAS -> " + bd.getHouseSeeds(5) + " <-- ");
			
			System.out.println("XXXXXXXXXXXXXXXXXXXX");
			System.out.println("House Seeds -> " + 6 + " HAS -> " + bd.getHouseSeeds(6) + " <-- ");
			/*System.out.println("House Seeds -> " + 7 + " HAS -> " + bd.getHouseSeeds(7) + " <-- ");
			System.out.println("House Seeds -> " + 8 + " HAS -> " + bd.getHouseSeeds(8) + " <-- ");
			System.out.println("House Seeds -> " + 9 + " HAS -> " + bd.getHouseSeeds(9) + " <-- ");
			System.out.println("House Seeds -> " + 10 + " HAS -> " + bd.getHouseSeeds(10) + " <-- ");
			System.out.println("House Seeds -> " + 11 + " HAS -> " + bd.getHouseSeeds(11) + " <-- ");
			*/
			
			
			
			
			/*bd.sowSeeds(11);
			
			System.out.println("PLAYER SCORE 1 SHOULD BE 0 -> " + bd.getPlayerScore(2));
			
			System.out.println("House Seeds -> " + 6 + " HAS -> " + bd.getHouseSeeds(0) + " <-- ");
			System.out.println("House Seeds -> " + 7 + " HAS -> " + bd.getHouseSeeds(1) + " <-- ");
			System.out.println("House Seeds -> " + 8 + " HAS -> " + bd.getHouseSeeds(2) + " <-- ");
			System.out.println("House Seeds -> " + 9 + " HAS -> " + bd.getHouseSeeds(3) + " <-- ");
			System.out.println("House Seeds -> " + 10 + " HAS -> " + bd.getHouseSeeds(4) + " <-- ");
			System.out.println("House Seeds -> " + 11 + " HAS -> " + bd.getHouseSeeds(5) + " <-- ");
			System.out.println("House Seeds -> " + 0 + " HAS -> " + bd.getHouseSeeds(11) + " <-- ");
			System.out.println("House Seeds -> " + 5 + " HAS -> " + bd.getHouseSeeds(10) + " <-- ");
			*/
			
			
			
			
			System.exit(0);
			
			
			
			
			
			
			
			
			// Get House Seeds for debugging
			//for (int i = 0; i < 12; i++) {
			//	System.out.println("House Seeds -> " + i + " HAS -> " + bd.getHouseSeeds(i) + " <-- ");
			//}
		
	}
	/*
	public static void main(String[] args) {
		launch(args);
	}*/
}
