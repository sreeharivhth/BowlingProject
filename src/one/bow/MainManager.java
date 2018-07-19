package one.bow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.swing.text.StyledEditorKit;

public class MainManager {

	final int strike = 10;
	final int spare = -10;
	final int strikeBuddy = -4;

	// int[][] input = new
	// int[][]{{1,2},{2,1},{3,1},{4,1},{5,1},{6,1},{7,1},{8,1},{9,1},{1,2,2}};//success

	/*int[][] input = new int[][] { { strike,strikeBuddy }, { strike,strikeBuddy }, { strike,strikeBuddy }, { strike,strikeBuddy },
								{ strike,strikeBuddy }, { strike,strikeBuddy }, { strike,strikeBuddy }, { strike,strikeBuddy },
								{ strike,strikeBuddy }, { strike, strike, strike } };//success
*/	 
	
	/*int[][] input = new int[][] { { 1,1 }, { 1,1 }, { 1,1 }, { 1,1 },
		{ 1,1 }, { 1,1 }, { 1,1 }, { 1,1 },
		{ 1,1 }, { 1,1, 0 } };//success 20
*/		
	
	/*int[][] input = new int[][] { { 1,spare }, { 1,1 }, { strike,strikeBuddy }, { 1,1 },
		{ 1,1 }, { strike,strikeBuddy }, { strike,strikeBuddy }, { 1,1 },
		{ 1,1 }, { 1,1, 0 } };//Success 68
	 */	
	/*int[][] input = new int[][] { { 1,4 }, { 4,5 }, { 6,spare }, { 5,spare },
		{ strike,strikeBuddy }, { 0,1 }, { 7,spare }, { 6,spare },
		{ strike,strikeBuddy }, { 2,spare,6 } };//Success 133 
*/	 
	int[][] input = new int[][] { { strike,strikeBuddy }, { 9,0 }, { 7,spare }, { strike,strikeBuddy },
		{ strike,strikeBuddy }, { 7,0 }, { 9,spare }, { 8,spare },
		{ strike,strikeBuddy }, { 9,spare,1} };//Success 168 as final score
	
	
	/*int[][] input = new int[][] { { 1,0 }, { 2,0 }, { 3,0 }, { 4,0 },
		{ 5 , 0  }, { 6,0 }, {	7,0 }, { 8,0 },
		{ 9,0 }, { strike,5,4} };//WORKS
	 */
	/*int[][] input = new int[][] { { 1,0 }, { 2,0 }, { 3,0 }, { 4,0 },
		{ 5 , 0  }, { 6,0 }, {	7,0 }, { 8,0 },
		{ 1,spare }, { strike,1,1} };// WORKING
*/	
	/*int[][] input = new int[][] { { 8,0 }, { 6,0 }, { strike,strikeBuddy }, { strike,strikeBuddy },
		{ strike,strikeBuddy }, { 8,0 }, {strike,strikeBuddy}, { 6,1 },
		{ strike,strikeBuddy }, { strike,7,2} };//Correct 168
*/		
	/*int[][] input = new int[][] { { strike,strikeBuddy }, { 8,1 }, { 5,4 }, { strike,strikeBuddy },
		{ 8 , 1 }, { 7, 2 }, {	6 , 3 }, { 7, 2 },
		{ 8,spare }, { strike,strike,9} };//Correct 141
	 */
	
	/*int[][] input = new int[][] { { strike,strikeBuddy }, { 7,2 }, { strike,strikeBuddy }, { 6 , 3 },
		{ 8 , spare }, { 7, 0 }, {	7 , spare }, { 9 , 0 },
		{ 9 , 0 }, { 9 ,spare,9} };//Correct 136
*/	 
	
	/*int[][] input = new int[][] { { 9,spare }, { 7,spare }, { 9,spare }, { strike,strikeBuddy  },
		{ 8 , 1 }, { 9, spare }, {	strike,strikeBuddy }, { 7 , spare },
		{ 7 , spare }, { strike , 5 , 4} };//Success 180
*/		
	/*int[][] input = new int[][] { { strike,strikeBuddy  }, { 9,0 }, { 7,spare }, { strike,strikeBuddy  },
		{ strike,strikeBuddy  }, { 7, 0 }, { 9, spare }, { 8 , spare },
		{ strike,strikeBuddy  }, { 9 , spare , 1} };//Got 177, game got 168
*/	 
	
	/*int[][] input = new int[][] { { 1,0 }, { 1,0 }, { 1,0 }, { 1,0  },
		{ 1,0  }, { 1,0  }, { 1,0  }, { 1,0  },
		{ 1,0  }, { 1, 0 , 1} }; WORKS*/
		
	/*int[][] input = new int[][] { { 1,0 }, { strike,strikeBuddy }, { 1,0 }, { 1,0  },
		{ strike,strikeBuddy }, { 1,0  }, { 1,spare  }, { 1,0  },
		{ 1,0  }, { 1, 0 , 0} }; WORKS*/
	
	/*int[][] input = new int[][] { { 1,spare }, { 4,spare }, { 2,0 }, { strike,strikeBuddy  },
		{ 5,spare }, { 8,0  }, { 8,spare  }, { 3,4  },
		{ strike,strikeBuddy  }, { strike, strike , strike} };//correct 154
*/		
	
	/*int[][] input = new int[][] { { 3,spare }, { strike,strikeBuddy }, { strike,strikeBuddy }, { 7,1 },
		{ 0,3 }, { strike,strikeBuddy }, { 9,spare  }, { 4,5  },
		{ strike,strikeBuddy  }, { 3, spare , 1} }; //correct 150*/
	
	
	/*int[][] input = new int[][] { { strike,strikeBuddy }, { 8,1 }, { 5,4 }, { strike,strikeBuddy },
		{ 8,1 }, { 7,2 }, { 6,3 }, { 7,2  },
		{ 8,spare }, { strike, strike ,9} };//correct 141
*/	 
	
	/*int[][] input = new int[][] { { strike,strikeBuddy }, { 7,2 }, { strike,strikeBuddy  }, { 6,3 },
		{ 8,spare }, { 7,0 }, { 7,spare }, { 9,0  },
		{ 9,0 }, { 9 , spare ,9} };//Correct 136
*/	
	int[][] output = new int[input.length][1];

	ArrayList<WaitingObject> strikeWaitingList = new ArrayList<WaitingObject>();

	
	public static void main(String[] args) {
		new MainManager().calculateScores();
	}
	
	public boolean calculateScores() {
		
		boolean isExceptionCaused=false;
		boolean shouldWaitForSpare = false;
		int spareWaitIndex = -1;
		int spareScoreLast = -1;
		int strikeScoreLast = -1;
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

				if (currentVal == strikeBuddy) {
					continue;
				}

				// Iterate over @strikeWaitingList
				if (strikeWaitingList.size() > 0 ) {
					for (int i = 0; i < strikeWaitingList.size(); i++) {
						WaitingObject waitingObject = strikeWaitingList.get(i);

						int localRow = row;
						//To consider if waiting list index is having last element
						//To consider if waiting list index is having object of row-1 th and row-2 th object 
						if ((localRow == (input.length - 1))
								||
								(waitingObject.inputIndex == (localRow - 1))
										|| (waitingObject.inputIndex == (localRow - 2))) {
							//Check if object's iteration count is completed
							if (waitingObject.iterationCount != 0 && waitingObject.iterationCount != -1) {
								
								int presentValLocal = currentVal;
								if (currentVal == spare) {
									//For spare, reduce last score and add current value 
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
									// update the score if iteration is finished to last index
									int previousIndex= waitingObject.inputIndex-1>0?waitingObject.inputIndex-1:0;
									output[waitingObject.inputIndex][0] = waitingObject.liveScore + output[previousIndex][0];	
									waitingObject.iterationCount = -1;									
								}
							}
						}
					}
				}

				if (shouldWaitForSpare) {
					//updating spare score
					spareScoreLast = spareScoreLast + currentVal;
					shouldWaitForSpare = false;
					// put value to last index of output
					int previousIndex= spareWaitIndex-1>0?spareWaitIndex-1:0;
					output[spareWaitIndex][0] = spareScoreLast+ output[previousIndex][0];
					
					// reset the spare indexes
					spareScoreLast = -1;
					spareWaitIndex = -1;
					localSum = 0;
				}
				
				//If current row is not the last one, then proceed
				if(row!=9)
				{
					switch (currentVal) {

					case spare:

						//For spare, keep last score as 10 and update waiting index
						shouldWaitForSpare = true;
						spareScoreLast = 10;
						spareWaitIndex = row;

						break;

					case strike:

						strikeScoreLast = 10;
						//Iterate list and check if object is already available
						//If then update the score
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
					
					int checkPoint = columsToConsider - 1;
					if (col == checkPoint) {
						
						if (spareScoreLast != -1 && (false == shouldWaitForSpare)) {
							//Update spare's score to last index + spare last score
							int previousIndex= row-1 >0 ?row-1:0;
							output[row][0] = spareScoreLast+output[previousIndex][0];
							localSum = 0;
							spareWaitIndex = row;
						} else if (shouldWaitForSpare) {
							// don't add sum, keep values in waiting list only
							continue;
						} else {
							//Update local score 
							int previousIndex= row-1 >0 ?row-1:0;
							output[row][0] = localSum+output[previousIndex][0];
							localSum = 0;	
						}
					}
				}
			}//End of inner array iteration
		}
		//End of outer array iteration
		
		printOutput();
	
		return isExceptionCaused;
	}
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

		public int inputIndex;
		public int liveScore;
		public int iterationCount;
		public int lastAddedValue;

	}

	private void printOutput() {

		System.out.println("==================OUTPUT==================");

		for (int row = 0; row < input.length; row++)
			for (int col = 0; col < 1; col++)
				System.out.println(output[row][col]);
	}
}
