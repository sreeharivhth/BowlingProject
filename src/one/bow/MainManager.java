package one.bow;

import java.util.ArrayList;
import java.util.Arrays;
/**
 * Main class for calculating individual frame in bowling and total score 
 * @author Sreehari.KV
 *
 */
public class MainManager {

	private static final int STRIKE = 10;
	private static final int SPARE = 201;
	private static final int STRIKE_BUDDY = 401;

	// int[][] mInput = new
	// int[][]{{1,2},{2,1},{3,1},{4,1},{5,1},{6,1},{7,1},{8,1},{9,1},{1,2,2}};//success

	
	/*int[][] mInput = new int[][] { { 1,SPARE }, { 1,1 }, { STRIKE,STRIKE_BUDDY }, { 1,1 },
		{ 1,1 }, { STRIKE,STRIKE_BUDDY }, { STRIKE,STRIKE_BUDDY }, { 1,1 },
		{ 1,1 }, { 1,1, 0 } };//Success 68
*/	 	
	/*int[][] mInput = new int[][] { { 1,4 }, { 4,5 }, { 6,SPARE }, { 5,SPARE },
		{ STRIKE,STRIKE_BUDDY }, { 0,1 }, { 7,SPARE }, { 6,SPARE },
		{ STRIKE,STRIKE_BUDDY }, { 2,SPARE,6 } };//Success 133 
*/	 
	/*int[][] mInput = new int[][] { { STRIKE,STRIKE_BUDDY }, { 9,0 }, { 7,SPARE }, { STRIKE,STRIKE_BUDDY },
		{ STRIKE,STRIKE_BUDDY }, { 7,0 }, { 9,SPARE }, { 8,SPARE },
		{ STRIKE,STRIKE_BUDDY }, { 9,SPARE,1} };//Success 168 as final score
	*/
	
	/*int[][] mInput = new int[][] { { 1,0 }, { 2,0 }, { 3,0 }, { 4,0 },
		{ 5 , 0  }, { 6,0 }, {	7,0 }, { 8,0 },
		{ 9,0 }, { STRIKE,5,4} };//WORKS ==64
	 */
	/*int[][] mInput = new int[][] { { 1,0 }, { 2,0 }, { 3,0 }, { 4,0 },
		{ 5 , 0  }, { 6,0 }, {	7,0 }, { 8,0 },
		{ 1,SPARE }, { STRIKE,1,1} };// WORKING 68
*/	
	
	
	/*int[][] mInput = new int[][] { { STRIKE,STRIKE_BUDDY }, { 7,2 }, { STRIKE,STRIKE_BUDDY }, { 6 , 3 },
		{ 8 , SPARE }, { 7, 0 }, {	7 , SPARE }, { 9 , 0 },
		{ 9 , 0 }, { 9 ,SPARE,9} };//Correct 136
	 */
	
	/*int[][] mInput = new int[][] { { 9,SPARE }, { 7,SPARE }, { 9,SPARE }, { STRIKE,STRIKE_BUDDY  },
		{ 8 , 1 }, { 9, SPARE }, {	STRIKE,STRIKE_BUDDY }, { 7 , SPARE },
		{ 7 , SPARE }, { STRIKE , 5 , 4} };//Success 180
		*/
	
	/*int[][] mInput = new int[][] { { 1,0 }, { 1,0 }, { 1,0 }, { 1,0  },
		{ 1,0  }, { 1,0  }, { 1,0  }, { 1,0  },
		{ 1,0  }, { 1, 0 , 1} }; //WORKS
*/		
	/*int[][] mInput = new int[][] { { 1,0 }, { STRIKE,STRIKE_BUDDY }, { 1,0 }, { 1,0  },
		{ STRIKE,STRIKE_BUDDY }, { 1,0  }, { 1,SPARE  }, { 1,0  },
		{ 1,0  }, { 1, 0 , 0} }; WORKS*/
	
	/*int[][] mInput = new int[][] { { 1,SPARE }, { 4,SPARE }, { 2,0 }, { STRIKE,STRIKE_BUDDY  },
		{ 5,SPARE }, { 8,0  }, { 8,SPARE  }, { 3,4  },
		{ STRIKE,STRIKE_BUDDY  }, { STRIKE, STRIKE , STRIKE} };//correct 154
		*/
	
	int[][] mInput = new int[][] { { 3,SPARE }, { STRIKE,STRIKE_BUDDY }, { STRIKE,STRIKE_BUDDY }, { 7,1 },
		{ 0,3 }, { STRIKE,STRIKE_BUDDY }, { 9,SPARE  }, { 4,5  },
		{ STRIKE,STRIKE_BUDDY  }, { 3, SPARE , 1} }; //correct 150
	
	int[][] mOutput;
	private ArrayList<WaitingObject> mWaitingList;
	private boolean isExceptionCaused;
	private boolean shouldWaitForSpare;
	private int mSpareWaitIndex;
	private int mSpareScoreLast;	
	
	public static void main(String[] args) {
		new MainManager().calculateScores(null);
	}
	
	/**
	 * Calculation of bowling scores
	 * @return
	 */
	public int[][] calculateScores(int[][] mInput) {
		shouldWaitForSpare = false;
		isExceptionCaused=false;
		mSpareWaitIndex = -1;
		mSpareScoreLast = -1;
		mWaitingList = new ArrayList<>();
		if(null==mInput)
			mInput=this.mInput;
		
		mOutput = new int[mInput.length][1];
		
		try {
			int columsToConsider = 2;		
			//Outer array traversal (row)
			for (int row = 0; row < mInput.length; row++) {
				int localSum = 0;
				if (row == 9) {
					columsToConsider = 3;
					WaitingObject waitingNinthObject  = createWaitObject(row,columsToConsider,0,0);
					mWaitingList.add(waitingNinthObject);
				} 
				//Inner array traversal (columns)
				for (int col = 0; col < columsToConsider; col++) {
					int currentVal = mInput[row][col];
					if(!isEntryValid(currentVal)){
						throw new Exception("Invalid Entry ("+currentVal + ") ");
					}
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
						localSum = checkSpecialCases(columsToConsider, row, localSum, currentVal);
						updateCheckPoint(columsToConsider, row, localSum, col);
					}
				}//End of inner array iteration
			}
			//End of outer array iteration
			
			printOutput();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	
		return mOutput;
	}
	
	private boolean isEntryValid(int val){
		boolean isValid=true;
		if(val<0){
			isValid=false;
			return isValid;
		}else if(val>10&& val!=SPARE && val!=STRIKE_BUDDY){
			isValid=false;
			return isValid;
		}
		return isValid;
	}

	/**
	 * Cross check special cases
	 * @param columsToConsider
	 * @param row
	 * @param localSum
	 * @param currentVal
	 * @return
	 */
	private int checkSpecialCases(int columsToConsider, int row, int localSum, int currentVal) {
		int strikeScoreLast;
		switch (currentVal) {

		case SPARE:

			//For SPARE, keep last score as 10 and update waiting index
			shouldWaitForSpare = true;
			mSpareScoreLast = 10;
			mSpareWaitIndex = row;

			break;

		case STRIKE:

			strikeScoreLast = 10;
			//Iterate list and check if object is already available
			//If then update the score
			if (!mWaitingList.isEmpty()) {
					boolean isValueAvailable = false;
					for (int i = 0; i < mWaitingList.size(); i++) {
						WaitingObject waitingObject = mWaitingList.get(i);
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
						mWaitingList.add(waitingObject);
					}
				} 
				else {
					//List is empty , create object and add to list
					WaitingObject waitingObject = createWaitObject(row,columsToConsider,10,10);
					mWaitingList.add(waitingObject);
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
	 * Update score of spare condition
	 * @param currentVal
	 * @return
	 */
	private int spareCaseUpdate(int currentVal) {
		int localSum;
		//updating SPARE score
		mSpareScoreLast = mSpareScoreLast + currentVal;
		shouldWaitForSpare = false;
		// put value to last index of output
		int previousIndex= mSpareWaitIndex-1>0?mSpareWaitIndex-1:0;
		mOutput[mSpareWaitIndex][0] = mSpareScoreLast+ mOutput[previousIndex][0];
		
		// reset the SPARE indexes
		mSpareScoreLast = -1;
		mSpareWaitIndex = -1;
		localSum = 0;
		return localSum;
	}

	/**
	 * End of column except last index
	 * @param columsToConsider
	 * @param row
	 * @param localSum
	 * @param col
	 */
	private void updateCheckPoint(int columsToConsider, int row, int localSum, int col) {
		int checkPoint = columsToConsider - 1;
		if (col == checkPoint) {
			if (mSpareScoreLast != -1 && (!shouldWaitForSpare)) {
				//Update SPARE's score to last index + SPARE last score
				int previousIndex= row-1 >0 ?row-1:0;
				mOutput[row][0] = mSpareScoreLast+mOutput[previousIndex][0];
				mSpareWaitIndex = row;
			} else if (shouldWaitForSpare) {
				// don't add sum, keep values in waiting list only
			} else {
				//Update local score 
				int previousIndex= row-1 >0 ?row-1:0;
				mOutput[row][0] = localSum+mOutput[previousIndex][0];
			}
		}
	}

	/**
	 * Running through waiting list 
	 * @param row
	 * @param currentVal
	 */
	private void iterateWaitingList(int row, int currentVal) {
		// Iterate over @strikeWaitingList
			for (int i = 0; i < mWaitingList.size(); i++) {
				
				WaitingObject waitingObject = mWaitingList.get(i);
				int localRow = row;
				//To consider if waiting list index is having last element
				//To consider if waiting list index is having object of row-1 th or row-2 th object 
				
				boolean condiOne = waitingObject.iterationCount > 0;
				boolean condiTwo = waitingObject.inputIndex == localRow-1;
				boolean condiThree = waitingObject.inputIndex == localRow-2;
				boolean condiLastColmn = localRow == (mInput.length - 1);
				
				if (condiOne && (condiTwo||condiThree||condiLastColmn)) {
					//Check if object's iteration count is completed
						int presentValLocal = currentVal;
						if (currentVal == SPARE) {
							//For SPARE, reduce last score and add current value 
							waitingObject.liveScore = waitingObject.liveScore + STRIKE - waitingObject.lastAddedValue;
						}else{
							//If not, then add current value to last score
							waitingObject.liveScore = waitingObject.liveScore + presentValLocal;
						}
						waitingObject.lastAddedValue = presentValLocal;
						waitingObject.iterationCount = --waitingObject.iterationCount;// update
																						// iteration
																						// count
						if (waitingObject.iterationCount == 0) {
							updateWaitingListObj(waitingObject);									
						}
				}
			}
	}

	/**
	 * Setting score of last index with waiting object
	 * @param waitingObject
	 */
	private void updateWaitingListObj(WaitingObject waitingObject) {
		// update the score if iteration is finished to last index
		int previousIndex= waitingObject.inputIndex-1>0?waitingObject.inputIndex-1:0;
		mOutput[waitingObject.inputIndex][0] = waitingObject.liveScore + mOutput[previousIndex][0];	
		waitingObject.iterationCount = -1;
	}
	
	
	
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

		for (int row = 0; row < mInput.length; row++)
			for (int col = 0; col < 1; col++)
				System.out.print(mOutput[row][col]+",");
		
		System.out.println(" ");
	}
}
