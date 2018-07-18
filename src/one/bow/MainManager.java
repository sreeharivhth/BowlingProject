package one.bow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class MainManager {

	final int strike = 10;
	final int spare = -10;
	final int strikeBuddy = -4;

	// included elements with 3rd index
	// int[][] input = new
	// int[][]{{1,2},{2,1},{3,1},{4,1},{5,1},{6,1},{7,1},{8,1},{9,1},{1,2,2}};//success

	/*final int[][] input = new int[][] { { strike,strikeBuddy }, { strike,strikeBuddy }, { strike,strikeBuddy }, { strike,strikeBuddy },
								{ strike,strikeBuddy }, { strike,strikeBuddy }, { strike,strikeBuddy }, { strike,strikeBuddy },
								{ strike,strikeBuddy }, { strike, strike, strike } };//success
	 */
	
	/*final int[][] input = new int[][] { { 1,1 }, { 1,1 }, { 1,1 }, { 1,1 },
		{ 1,1 }, { 1,1 }, { 1,1 }, { 1,1 },
		{ 1,1 }, { 1,1, 0 } };//success
	 */
	
	/*final int[][] input = new int[][] { { 1,spare }, { 1,1 }, { strike,strikeBuddy }, { 1,1 },
		{ 1,1 }, { strike,strikeBuddy }, { strike,strikeBuddy }, { 1,1 },
		{ 1,1 }, { 1,1, 0 } };//works
	 */
	/*final int[][] input = new int[][] { { 1,4 }, { 4,5 }, { 6,spare }, { 5,spare },
		{ strike,strikeBuddy }, { 0,1 }, { 7,spare }, { 6,spare },
		{ strike,strikeBuddy }, { 2,spare,6 } };//ERROR==> Shows 133 as final score
	 */
	/*final int[][] input = new int[][] { { strike,strikeBuddy }, { 9,0 }, { 7,spare }, { strike,strikeBuddy },
		{ strike,strikeBuddy }, { 7,0 }, { 9,spare }, { 8,spare },
		{ strike,strikeBuddy }, { 9,spare,1} };//ERROR>- shows 168 as final score
	 */
	
	final int[][] input = new int[][] { { strike,strikeBuddy }, { 9,0 }, { 7,spare }, { 1,1 },
		{ 9 , 0  }, { 8,0 }, {8,0 }, { 5,0 },
		{ 9,0 }, { 9,spare,6} };//ERROR >= Shows final score as 96, were as I got 105
		
	int[][] output = new int[input.length][1];

	ArrayList<WaitingObject> strikeWaitingList = new ArrayList<WaitingObject>();

	

	public static void main(String[] args) {
		new MainManager();
	}

	public MainManager() {

		// WORKS FOR CONTINUOUS STRIKES,SPARES AS WELL. CORNER CASE NEED TO POLISH
		// UT PENDING
		
		boolean shouldWaitForSpare = false;
		int spareWaitIndex = -1;
		int spareScoreLast = -1;
		int strikeScoreLast = -1;
		int columsToConsider = 0;
		int localSum = -1;
		
		//Outer array traversal
		for (int row = 0; row < input.length; row++) {
			localSum = 0;
			if (row != 9) {
				columsToConsider = 2;
			} else {
				columsToConsider = 3;
			}

			//Inner array traversal
			for (int col = 0; col < columsToConsider; col++) {

				int currentVal = input[row][col];
				System.out.println(currentVal);

				if (currentVal == strikeBuddy) {
					continue;
				}

				// Iterate over @strikeWaitingList
				if (strikeWaitingList.size() > 0) {
					for (int i = 0; i < strikeWaitingList.size(); i++) {
						WaitingObject waitingObject = strikeWaitingList.get(i);
						// Check if waiting object's inputIndex is
						int localRow = row;
						if ((localRow == (input.length - 1))
								|| /* This is for special case scenario */
								((waitingObject.inputIndex == (localRow - 1))
										|| (waitingObject.inputIndex == (localRow - 2)))) {
							if (waitingObject.iterationCount != 0 && waitingObject.iterationCount != -1) {
								int presentValLocal = currentVal;
								if (currentVal == spare) {
									presentValLocal = 10;
								}
								waitingObject.liveScore = waitingObject.liveScore + presentValLocal;
								waitingObject.iterationCount = --waitingObject.iterationCount;// update
																								// iteration
																								// count
								if (waitingObject.iterationCount == 0) {
									// update the score
									
									int previousIndex= waitingObject.inputIndex-1>0?waitingObject.inputIndex-1:0;
									output[waitingObject.inputIndex][0] = waitingObject.liveScore + output[previousIndex][0];	
									
									waitingObject.iterationCount = -1;
								}
							}
						}
					}
				}

				if (shouldWaitForSpare) {
					spareScoreLast = spareScoreLast + currentVal;
					shouldWaitForSpare = false;
					// put value to last index
					int previousIndex= spareWaitIndex-1>0?spareWaitIndex-1:0;
					output[spareWaitIndex][0] = spareScoreLast+ output[previousIndex][0];
					
					// reset the spare indexes
					spareScoreLast = -1;
					spareWaitIndex = -1;
					localSum = 0;
				}
				/* This is for special case scenario */
				if (row == 9) {
					if(currentVal!=-1){
						int localCurrent= currentVal;
						if(currentVal==spare || currentVal==strike){
							localCurrent=10;
						}
						localSum = localSum + localCurrent;	
					}
					
				} else {
					switch (currentVal) {

					case spare:

						shouldWaitForSpare = true;
						spareScoreLast = 10;
						spareWaitIndex = row;

						break;

					case strike:

						strikeScoreLast = 10;
						
						if (strikeWaitingList.size() > 0) {
							boolean isValueAvailable = false;
							for (int i = 0; i < strikeWaitingList.size(); i++) {
								WaitingObject waitingObject = strikeWaitingList.get(i);
								if (waitingObject.inputIndex == row) {
									isValueAvailable = true;
									waitingObject.liveScore = waitingObject.liveScore + strikeScoreLast;// update
																										// strike
								}
							}
							// Iterated over array, element with index not
							// available. So create new @WaitingObject and add
							// to list
							if (!isValueAvailable) {
								WaitingObject waitingObject = new WaitingObject();
								waitingObject.inputIndex = row;
								waitingObject.iterationCount = 2;
								waitingObject.liveScore = 10;
								strikeWaitingList.add(waitingObject);
							}
						} else {
							WaitingObject waitingObject = new WaitingObject();
							waitingObject.inputIndex = row;
							waitingObject.iterationCount = 2;
							waitingObject.liveScore = 10;
							strikeWaitingList.add(waitingObject);
						}
						break;

					default:
						localSum = localSum + currentVal;
						break;
					}
				}

				int checkPoint = columsToConsider - 1;

				if (col == checkPoint) {
					if (spareScoreLast != -1 && (false == shouldWaitForSpare)) {
						
						int previousIndex= row-1 >0 ?row-1:0;
						output[row][0] = spareScoreLast+output[previousIndex][0];
						
						localSum = 0;
						spareWaitIndex = row;
					} else if (shouldWaitForSpare) {
						// don't add sum, keep values in waiting list only
						continue;
					} else {
						int previousIndex= row-1 >0 ?row-1:0;
						output[row][0] = localSum+output[previousIndex][0];
												
						localSum = 0;
					}
				}
			}
		}
		printOutput();
	}

	private class WaitingObject {

		public int inputIndex;
		public int liveScore;
		public int iterationCount;

	}

	private void printOutput() {

		System.out.println("==================OUTPUT==================");

		for (int row = 0; row < input.length; row++)
			for (int col = 0; col < 1; col++)
				System.out.println(output[row][col]);
	}
}
