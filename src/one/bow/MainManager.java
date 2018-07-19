package one.bow;

import java.util.ArrayList;

public class MainManager {

	private static final int STRIKE = 10;
	private static final int SPARE = -10;
	private static final int STRIKE_BUDDY = -4;

	// int[][] input = new
	// int[][]{{1,2},{2,1},{3,1},{4,1},{5,1},{6,1},{7,1},{8,1},{9,1},{1,2,2}};//success

	/*int[][] input = new int[][] { { STRIKE,STRIKE_BUDDY }, { STRIKE,STRIKE_BUDDY }, { STRIKE,STRIKE_BUDDY }, { STRIKE,STRIKE_BUDDY },
								{ STRIKE,STRIKE_BUDDY }, { STRIKE,STRIKE_BUDDY }, { STRIKE,STRIKE_BUDDY }, { STRIKE,STRIKE_BUDDY },
								{ STRIKE,STRIKE_BUDDY }, { STRIKE, STRIKE, STRIKE } };//success
*/	 
	/*int[][] input = new int[][] { { 1,1 }, { 1,1 }, { 1,1 }, { 1,1 },
		{ 1,1 }, { 1,1 }, { 1,1 }, { 1,1 },
		{ 1,1 }, { 1,1, 0 } };//success 20
*/		
	
	/*int[][] input = new int[][] { { 1,SPARE }, { 1,1 }, { STRIKE,STRIKE_BUDDY }, { 1,1 },
		{ 1,1 }, { STRIKE,STRIKE_BUDDY }, { STRIKE,STRIKE_BUDDY }, { 1,1 },
		{ 1,1 }, { 1,1, 0 } };//Success 68
*/	 	
	/*int[][] input = new int[][] { { 1,4 }, { 4,5 }, { 6,SPARE }, { 5,SPARE },
		{ STRIKE,STRIKE_BUDDY }, { 0,1 }, { 7,SPARE }, { 6,SPARE },
		{ STRIKE,STRIKE_BUDDY }, { 2,SPARE,6 } };//Success 133 
*/	 
	/*int[][] input = new int[][] { { STRIKE,STRIKE_BUDDY }, { 9,0 }, { 7,SPARE }, { STRIKE,STRIKE_BUDDY },
		{ STRIKE,STRIKE_BUDDY }, { 7,0 }, { 9,SPARE }, { 8,SPARE },
		{ STRIKE,STRIKE_BUDDY }, { 9,SPARE,1} };//Success 168 as final score
	*/
	
	/*int[][] input = new int[][] { { 1,0 }, { 2,0 }, { 3,0 }, { 4,0 },
		{ 5 , 0  }, { 6,0 }, {	7,0 }, { 8,0 },
		{ 9,0 }, { STRIKE,5,4} };//WORKS ==64
	 */
	/*int[][] input = new int[][] { { 1,0 }, { 2,0 }, { 3,0 }, { 4,0 },
		{ 5 , 0  }, { 6,0 }, {	7,0 }, { 8,0 },
		{ 1,SPARE }, { STRIKE,1,1} };// WORKING 68
*/	
	/*int[][] input = new int[][] { { 8,0 }, { 6,0 }, { STRIKE,STRIKE_BUDDY }, { STRIKE,STRIKE_BUDDY },
		{ STRIKE,STRIKE_BUDDY }, { 8,0 }, {STRIKE,STRIKE_BUDDY}, { 6,1 },
		{ STRIKE,STRIKE_BUDDY }, { STRIKE,7,2} };//Correct 168
		*/
	/*int[][] input = new int[][] { { STRIKE,STRIKE_BUDDY }, { 8,1 }, { 5,4 }, { STRIKE,STRIKE_BUDDY },
		{ 8 , 1 }, { 7, 2 }, {	6 , 3 }, { 7, 2 },
		{ 8,SPARE }, { STRIKE,STRIKE,9} };//Correct 141
	 */
	
	int[][] input = new int[][] { { STRIKE,STRIKE_BUDDY }, { 7,2 }, { STRIKE,STRIKE_BUDDY }, { 6 , 3 },
		{ 8 , SPARE }, { 7, 0 }, {	7 , SPARE }, { 9 , 0 },
		{ 9 , 0 }, { 9 ,SPARE,9} };//Correct 136
	 
	
	/*int[][] input = new int[][] { { 9,SPARE }, { 7,SPARE }, { 9,SPARE }, { STRIKE,STRIKE_BUDDY  },
		{ 8 , 1 }, { 9, SPARE }, {	STRIKE,STRIKE_BUDDY }, { 7 , SPARE },
		{ 7 , SPARE }, { STRIKE , 5 , 4} };//Success 180
		*/
	/*int[][] input = new int[][] { { STRIKE,STRIKE_BUDDY  }, { 9,0 }, { 7,SPARE }, { STRIKE,STRIKE_BUDDY  },
		{ STRIKE,STRIKE_BUDDY  }, { 7, 0 }, { 9, SPARE }, { 8 , SPARE },
		{ STRIKE,STRIKE_BUDDY  }, { 9 , SPARE , 1} };//Got 177, game got 168
	 */
	
	/*int[][] input = new int[][] { { 1,0 }, { 1,0 }, { 1,0 }, { 1,0  },
		{ 1,0  }, { 1,0  }, { 1,0  }, { 1,0  },
		{ 1,0  }, { 1, 0 , 1} }; //WORKS
		*/
	/*int[][] input = new int[][] { { 1,0 }, { STRIKE,STRIKE_BUDDY }, { 1,0 }, { 1,0  },
		{ STRIKE,STRIKE_BUDDY }, { 1,0  }, { 1,SPARE  }, { 1,0  },
		{ 1,0  }, { 1, 0 , 0} }; WORKS*/
	
	/*int[][] input = new int[][] { { 1,SPARE }, { 4,SPARE }, { 2,0 }, { STRIKE,STRIKE_BUDDY  },
		{ 5,SPARE }, { 8,0  }, { 8,SPARE  }, { 3,4  },
		{ STRIKE,STRIKE_BUDDY  }, { STRIKE, STRIKE , STRIKE} };//correct 154
		*/
	
	/*int[][] input = new int[][] { { 3,SPARE }, { STRIKE,STRIKE_BUDDY }, { STRIKE,STRIKE_BUDDY }, { 7,1 },
		{ 0,3 }, { STRIKE,STRIKE_BUDDY }, { 9,SPARE  }, { 4,5  },
		{ STRIKE,STRIKE_BUDDY  }, { 3, SPARE , 1} }; //correct 150
	*/
	
	/*int[][] input = new int[][] { { STRIKE,STRIKE_BUDDY }, { 8,1 }, { 5,4 }, { STRIKE,STRIKE_BUDDY },
		{ 8,1 }, { 7,2 }, { 6,3 }, { 7,2  },
		{ 8,SPARE }, { STRIKE, STRIKE ,9} };//correct 141
	 */
	
	/*int[][] input = new int[][] { { STRIKE,STRIKE_BUDDY }, { 7,2 }, { STRIKE,STRIKE_BUDDY  }, { 6,3 },
		{ 8,SPARE }, { 7,0 }, { 7,SPARE }, { 9,0  },
		{ 9,0 }, { 9 , SPARE ,9} };//Correct 136
	*/
	int[][] output = new int[input.length][1];
	private ArrayList<WaitingObject> strikeWaitingList = new ArrayList<>();
	private boolean isExceptionCaused=false;
	private boolean shouldWaitForSpare = false;
	private int spareWaitIndex = -1;
	private int spareScoreLast = -1;
	
	
	public static void main(String[] args) {
		new MainManager().calculateScores();
	}
	
	public boolean calculateScores() {
		
		int columsToConsider = 0;
		
		//Outer array traversal (row)
		for (int row = 0; row < input.length; row++) {
			
			int localSum = 0;
			
			if (row != 9) {
				columsToConsider = 2;
			} else {
				columsToConsider = 3;
				WaitingObject waitingNinthObject  = createWaitObject(row,columsToConsider,0,0);
				strikeWaitingList.add(waitingNinthObject);
			}

			//Inner array traversal (columns)
			for (int col = 0; col < columsToConsider; col++) {

				int currentVal = input[row][col];
				System.out.println(currentVal);

				if (currentVal == STRIKE_BUDDY) {
					continue;
				}

				iterateWaitingList(row, currentVal);

				if (shouldWaitForSpare) {
					localSum = spareCaseUpdate(currentVal);
				}
				
				//If current row is not the last one, then proceed
				if(row!=9)
				{
					localSum = checkBowlCases(columsToConsider, row, localSum, currentVal);
					
					updateCheckPoint(columsToConsider, row, localSum, col);
				}
			}//End of inner array iteration
		}
		//End of outer array iteration
		
		printOutput();
	
		return isExceptionCaused;
	}

	/**
	 * @param columsToConsider
	 * @param row
	 * @param localSum
	 * @param currentVal
	 * @return
	 */
	private int checkBowlCases(int columsToConsider, int row, int localSum, int currentVal) {
		int strikeScoreLast;
		switch (currentVal) {

		case SPARE:

			//For SPARE, keep last score as 10 and update waiting index
			shouldWaitForSpare = true;
			spareScoreLast = 10;
			spareWaitIndex = row;

			break;

		case STRIKE:

			strikeScoreLast = 10;
			//Iterate list and check if object is already available
			//If then update the score
			if (!strikeWaitingList.isEmpty()) {
					boolean isValueAvailable = false;
					for (int i = 0; i < strikeWaitingList.size(); i++) {
						WaitingObject waitingObject = strikeWaitingList.get(i);
						if (waitingObject.inputIndex == row) {
							isValueAvailable = true;
							waitingObject.liveScore = waitingObject.liveScore + strikeScoreLast;// update
																								// STRIKE
						}
					}
					// Iterated over array, element with index not
					// available. So create new @WaitingObject and add
					// to list
					if (!isValueAvailable) {
						WaitingObject waitingObject = createWaitObject(row,columsToConsider,10,10);
						strikeWaitingList.add(waitingObject);
					}
				} 
				else {
					//List is empty , create object and add to list
					WaitingObject waitingObject = createWaitObject(row,columsToConsider,10,10);
					strikeWaitingList.add(waitingObject);
				}
			break;

		default:
			//If not coming under both cases, just add the current value to local sum
			localSum = localSum + currentVal;
			break;
		}
		return localSum;
	}

	/**
	 * @param currentVal
	 * @return
	 */
	private int spareCaseUpdate(int currentVal) {
		int localSum;
		//updating SPARE score
		spareScoreLast = spareScoreLast + currentVal;
		shouldWaitForSpare = false;
		// put value to last index of output
		int previousIndex= spareWaitIndex-1>0?spareWaitIndex-1:0;
		output[spareWaitIndex][0] = spareScoreLast+ output[previousIndex][0];
		
		// reset the SPARE indexes
		spareScoreLast = -1;
		spareWaitIndex = -1;
		localSum = 0;
		return localSum;
	}

	/**
	 * @param columsToConsider
	 * @param row
	 * @param localSum
	 * @param col
	 */
	private void updateCheckPoint(int columsToConsider, int row, int localSum, int col) {
		int checkPoint = columsToConsider - 1;
		if (col == checkPoint) {
			
			if (spareScoreLast != -1 && (!shouldWaitForSpare)) {
				//Update SPARE's score to last index + SPARE last score
				int previousIndex= row-1 >0 ?row-1:0;
				output[row][0] = spareScoreLast+output[previousIndex][0];
				spareWaitIndex = row;
			} else if (shouldWaitForSpare) {
				// don't add sum, keep values in waiting list only
			} else {
				//Update local score 
				int previousIndex= row-1 >0 ?row-1:0;
				output[row][0] = localSum+output[previousIndex][0];
			}
		}
	}

	/**
	 * @param row
	 * @param currentVal
	 */
	private void iterateWaitingList(int row, int currentVal) {
		// Iterate over @strikeWaitingList
		if (!strikeWaitingList.isEmpty()) {
			for (int i = 0; i < strikeWaitingList.size(); i++) {
				WaitingObject waitingObject = strikeWaitingList.get(i);
				int localRow = row;
				//To consider if waiting list index is having last element
				//To consider if waiting list index is having object of row-1 th and row-2 th object 
				
				boolean condiTwo = waitingObject.inputIndex == localRow-1;
				boolean condiThree = waitingObject.inputIndex == localRow-2;
				boolean condiFour = localRow == (input.length - 1);
				boolean condiOne = waitingObject.iterationCount != 0 && waitingObject.iterationCount != -1;
				
				if (condiOne && (condiTwo||condiThree||condiFour)) {
					//Check if object's iteration count is completed
						int presentValLocal = currentVal;
						if (currentVal == SPARE) {
							//For SPARE, reduce last score and add current value 
							presentValLocal = 10;
							waitingObject.liveScore = waitingObject.liveScore + presentValLocal - waitingObject.lastAddedValue;
							waitingObject.lastAddedValue = presentValLocal;
						}else{
							//If not, then add current value to last score
							waitingObject.liveScore = waitingObject.liveScore + presentValLocal;
							waitingObject.lastAddedValue = presentValLocal;
						}
						
						waitingObject.iterationCount = --waitingObject.iterationCount;// update
																						// iteration
																						// count
						if (waitingObject.iterationCount == 0) {
							updateWaitingListObj(waitingObject);									
						}
				}
			}
		}
	}

	/**
	 * @param waitingObject
	 */
	private void updateWaitingListObj(WaitingObject waitingObject) {
		// update the score if iteration is finished to last index
		int previousIndex= waitingObject.inputIndex-1>0?waitingObject.inputIndex-1:0;
		output[waitingObject.inputIndex][0] = waitingObject.liveScore + output[previousIndex][0];	
		waitingObject.iterationCount = -1;
	}
	
	/**
	 * Constructor
	 */
	public MainManager() {}
	
	/**
	 * Create Waiting object , which is used to track strikes and last index scores
	 * @param inputIndex
	 * @param iterationCount
	 * @param liveScore
	 * @param lastAdded
	 * @return
	 */
	private WaitingObject createWaitObject(int inputIndex,int iterationCount,int liveScore,int lastAdded){
		WaitingObject waitingNinthObject = new WaitingObject();
		waitingNinthObject.inputIndex=inputIndex;
		waitingNinthObject.iterationCount=iterationCount;
		waitingNinthObject.liveScore=liveScore;
		waitingNinthObject.lastAddedValue=lastAdded;
		return waitingNinthObject;
	}

	/**
	 * Object for holding waiting data in bowling 
	 * @author Sreehari.KV
	 *
	 */
	private class WaitingObject {

		private int inputIndex;
		private int liveScore;
		private int iterationCount;
		private int lastAddedValue;

	}

	private void printOutput() {

		System.out.println("==================OUTPUT==================");

		for (int row = 0; row < input.length; row++)
			for (int col = 0; col < 1; col++)
				System.out.println(output[row][col]);
	}
}
