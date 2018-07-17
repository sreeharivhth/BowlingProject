package one.bow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class MainManager {

	//ArrayList<HashMap<Integer, Integer>> strikePointsWaitList = new ArrayList<HashMap<Integer, Integer>>();
	
	ArrayList<WaitingObject> strikeWaitingList = new ArrayList<WaitingObject>();
	
	int spare=20;
	int strike=10;
	int waitStrike=-3;
	int strikeBuddy=-4;
	
	boolean shouldWaitForNextOne=false;
	int spareWaitIndex=-1;
	int spareScoreLast=-1;
	
	//boolean shouldWaitForNextTwo=false;
	
	int strikeWaitIndex=-1;
	int strikeScoreLast=-1;
	
			
	int[][] input = new int[][]{{1,2},{strike,strikeBuddy},{strike,strikeBuddy},{6,1}};
	int [][]output=new int[input.length][1];
	int localSum=-1;
	
	public MainManager() {
						
		//WORKS FOR CONTINUOUS STRIKES. DIDINT CHECKED WITH MIX OF STRIKES AND SPARES
		for (int row = 0; row < input.length; row ++){
			localSum=0;
			for (int col = 0; col < 2; col++){
					
					int currentVal = input[row][col];
        			System.out.println (currentVal);
        				
        				if(currentVal == strikeBuddy){
        					continue;
        				}
        				
        				//Iterate over @strikeWaitingList
        				if(strikeWaitingList.size()>0){
                			
                			for (int i = 0; i < strikeWaitingList.size(); i++) {
								WaitingObject waitingObject = strikeWaitingList.get(i);
								//Check if waiting object's inputIndex is  
								int localRow = row;
								if((waitingObject.inputIndex == (localRow-1)) || (waitingObject.inputIndex == (localRow-2))){
									if(waitingObject.iterationCount!=0 && waitingObject.iterationCount!=-1){
										
										waitingObject.liveScore = waitingObject.liveScore+currentVal;
										waitingObject.iterationCount = --waitingObject.iterationCount;//update iteration count 
										if(waitingObject.iterationCount==0){
											output[waitingObject.inputIndex][0]=waitingObject.liveScore;//update the score
											waitingObject.iterationCount=-1;
											//strikeWaitingList.remove(i);
										}
									}
								}
							}
        			}
        			
		        	if(shouldWaitForNextOne){
                		spareScoreLast = spareScoreLast+currentVal;
                		shouldWaitForNextOne=false;
                		//put value to last index
                		output[spareWaitIndex][0]= spareScoreLast;
                		//reset the indexes
                		spareScoreLast=-1;
                		spareWaitIndex=-1;   
                		localSum=0;
                	}
                	if(currentVal==spare){
	                		shouldWaitForNextOne=true;
	                		spareScoreLast=10;
	                		spareWaitIndex=row;
	                }
                	else if(currentVal ==strike){
                			/*shouldWaitForNextTwo=true;*/
                			waitStrike=2;
                			strikeScoreLast=10;
                			strikeWaitIndex=row;
                				
                			if(strikeWaitingList.size()>0){
                			boolean isValueAvailable=false;
                			for (int i = 0; i < strikeWaitingList.size(); i++) {
								WaitingObject waitingObject = strikeWaitingList.get(i);
								if(waitingObject.inputIndex==row){
									isValueAvailable=true;
									waitingObject.liveScore = waitingObject.liveScore+strikeScoreLast;//will this update ?
								}
							}
                				//Iterated over array, element with index not available. So create new and add to list
	                			if(!isValueAvailable){
	                				WaitingObject waitingObject =new WaitingObject();
	                				waitingObject.inputIndex=row;
	                				waitingObject.iterationCount=2;
	                				waitingObject.liveScore=10;
	                				strikeWaitingList.add(waitingObject);
	                			}
                			}else{
                				WaitingObject waitingObject =new WaitingObject();
                				waitingObject.inputIndex=row;
                				waitingObject.iterationCount=2;
                				waitingObject.liveScore=10;
                				strikeWaitingList.add(waitingObject);
                			}
                	}
                	else{
	                	localSum=localSum+currentVal;
	                }
                	if(col==1){
                		
                		if(spareScoreLast!=-1 && (false==shouldWaitForNextOne)){
                			output[row][0]=spareScoreLast;
                    		localSum=0;	
                    		spareWaitIndex = row;
                		}else if(shouldWaitForNextOne){
                			//don't add sum, keep values in waiting list only
                			continue;  			
                		}
                		else{
                			output[row][0]=localSum;
                			localSum=0;	
                		}
                	}
		}
		
		/*
		 * WORKS FOR INTERMEDIARY STRIKES.
		 * WORKS FOR STRIKES , SPARES COMBINATION AS WELL
		 * NOT WORKING FOR CONTINUOUS STRIKES.
		 * for (int row = 0; row < input.length; row ++){
			localSum=0;
			for (int col = 0; col < 2; col++){
					
					int currentVal = input[row][col];
        			System.out.println (currentVal);
        			if(shouldWaitForNextTwo){
        				
        				if(currentVal == strikeBuddy){
        					continue;
        				}
        				--waitStrike;//1
        				if(waitStrike==0){
        					shouldWaitForNextTwo=false;
        					strikeScoreLast=strikeScoreLast+currentVal;
        					output[strikeWaitIndex][0]=strikeScoreLast;
        					
        					int previousValue = isValueAvailable(strikeWaitIndex);
        					previousValue = previousValue+currentVal;
        					HashMap<Integer, Integer> hTable= new HashMap<Integer, Integer>();
                			hTable.put(strikeWaitIndex, previousValue);
                			updateExistingValue(strikeWaitIndex,hTable);
                			output[strikeWaitIndex][0]=previousValue;
                			
                			//reset strike manipulators
                			
        					strikeWaitIndex=-1;
        					strikeScoreLast=-1;
        					waitStrike=-1;
        					
        				}else{
        					strikeScoreLast=strikeScoreLast+currentVal;
        					int previousValue = isValueAvailable(strikeWaitIndex);
        					previousValue = previousValue+currentVal;
        					HashMap<Integer, Integer> hTable= new HashMap<Integer, Integer>();
                			hTable.put(strikeWaitIndex, previousValue);
                			
                			updateExistingValue(strikeWaitIndex,hTable);
        				}
        			}
        			
		        	if(shouldWaitForNextOne){
                		spareScoreLast = spareScoreLast+currentVal;
                		shouldWaitForNextOne=false;
                		//put value to last index
                		output[spareWaitIndex][0]= spareScoreLast;
                		//reset the indexes
                		spareScoreLast=-1;
                		spareWaitIndex=-1;   
                		localSum=0;
                	}
                	if(currentVal==spare){
	                		shouldWaitForNextOne=true;
	                		spareScoreLast=10;
	                		spareWaitIndex=row;
	                }
                	else if(currentVal ==strike){
                			shouldWaitForNextTwo=true;
                			waitStrike=2;
                			strikeScoreLast=10;
                			strikeWaitIndex=row;
                			                			
                			HashMap<Integer, Integer> hTable= new HashMap<Integer, Integer>();
                			hTable.put(strikeWaitIndex, strikeScoreLast);
                			strikePointsWaitList.add(hTable);
                			
                	}
                	else{
	                	localSum=localSum+currentVal;
	                }
                	if(col==1){
                		if(shouldWaitForNextTwo){
                			continue;
                		}
                		else if(spareScoreLast!=-1 && (false==shouldWaitForNextOne)){
                			output[row][0]=spareScoreLast;
                    		localSum=0;	
                    		spareWaitIndex = row;
                		}else if(shouldWaitForNextOne){
                			//don't add sum, keep values in waiting list only
                			continue;  			
                		}
                		else{
                			output[row][0]=localSum;
                			localSum=0;	
                		}
                	}
            }
		}*/
		
		//Print the saved elements in output array
		
	}
		printOutput();
	}
	
private WaitingObject isValueAvailable(int indexToSearch){
		
		WaitingObject valueFound=null;
		for (int i = 0; i < strikeWaitingList.size(); i++) {
			WaitingObject waitingObject = strikeWaitingList.get(i);
			if(waitingObject.inputIndex==indexToSearch){
				valueFound=waitingObject;
				break;
			}
		}		
		return valueFound;
	}
	
	/*private void updateExistingValue(int index,HashMap<Integer, Integer> newValue) {
		
		for (int i = 0; i < strikeWaitingList.size(); i++) {
			HashMap<Integer, Integer> hashMap = strikePointsWaitList.get(i);
			for (Map.Entry<Integer, Integer> entry : hashMap.entrySet())
			{
			    System.out.println(entry.getKey() + " || " + entry.getValue());
			    if(entry.getKey()==index){
			    	strikePointsWaitList.set(i, newValue);
			    	break;
			    }else{
			    	continue;
			    }
			}
		}
	}*/
	
	/*private int isValueAvailable(int indexToSearch){
		
		int valueFound=-1;
		for (int i = 0; i < strikePointsWaitList.size(); i++) {
			HashMap<Integer, Integer> hashMap = strikePointsWaitList.get(i);
			for (Map.Entry<Integer, Integer> entry : hashMap.entrySet())
			{
			    System.out.println(entry.getKey() + "||" + entry.getValue());
			    if(entry.getKey()==indexToSearch){
			    	valueFound = entry.getValue();
			    	break;
			    }else{
			    	continue;
			    }
			}
		}		
		return valueFound;
	}
	
	private void updateExistingValue(int index,HashMap<Integer, Integer> newValue) {
		
		for (int i = 0; i < strikePointsWaitList.size(); i++) {
			HashMap<Integer, Integer> hashMap = strikePointsWaitList.get(i);
			for (Map.Entry<Integer, Integer> entry : hashMap.entrySet())
			{
			    System.out.println(entry.getKey() + " || " + entry.getValue());
			    if(entry.getKey()==index){
			    	strikePointsWaitList.set(i, newValue);
			    	break;
			    }else{
			    	continue;
			    }
			}
		}
	}*/
	
	private class WaitingObject {
		
		public int inputIndex;
		public int liveScore;
		public int iterationCount;
		
	}

	public static void main(String[] args) {

		new MainManager();
		
		
		/*
		//INTRODUCED STRIKE BUDDY. WORKS FOR NON CONTINUOUS STRIKES
		int spare=-1;
		int strike=-2;
		
		boolean shouldWaitForNextOne=false;
		int spareWaitIndex=-1;
		int spareScoreLast=-1;
		
		boolean shouldWaitForNextTwo=false;
		int strikeWaitIndex=-1;
		int strikeScoreLast=-1;
		int waitStrike=-1;
		int strikeBuddy=-1;
				
		int[][] input = new int[][]{{1,2},{strike,strikeBuddy},{2,0},{strike,strikeBuddy},{4,4}};
		int [][]output=new int[input.length][1];
		int localSum=-1;
		
		for (int row = 0; row < input.length; row ++){
			localSum=0;
			for (int col = 0; col < 2; col++){
				
					int currentVal = input[row][col];
        			System.out.println (currentVal);
        			if(shouldWaitForNextTwo){
        				
        				if(currentVal == strikeBuddy){
        					continue;
        				}
        				--waitStrike;//1
        				if(waitStrike==0){
        					shouldWaitForNextTwo=false;
        					strikeScoreLast=strikeScoreLast+currentVal;
        					output[strikeWaitIndex][0]=strikeScoreLast;
        					//reset strike manipulators
        					strikeWaitIndex=-1;
        					strikeScoreLast=-1;
        					waitStrike=-1;
        				}else{
        					strikeScoreLast=strikeScoreLast+currentVal;
        				}
        			}
        			
		        	if(shouldWaitForNextOne){
                		spareScoreLast = spareScoreLast+currentVal;
                		shouldWaitForNextOne=false;
                		//put value to last index
                		output[spareWaitIndex][0]= spareScoreLast;
                		//reset the indexes
                		spareScoreLast=-1;
                		spareWaitIndex=-1;   
                		localSum=0;
                	}
                	if(currentVal==spare){
	                		shouldWaitForNextOne=true;
	                		spareScoreLast=10;
	                		spareWaitIndex=row;
	                }
                	else if(currentVal ==strike){
                			shouldWaitForNextTwo=true;
                			waitStrike=2;
                			strikeScoreLast=10;
                			strikeWaitIndex=row;
                	}
                	else{
	                	localSum=localSum+currentVal;
	                }
                	if(col==1){
                		if(shouldWaitForNextTwo){
                			continue;
                		}
                		else if(spareScoreLast!=-1 && (false==shouldWaitForNextOne)){
                			output[row][0]=spareScoreLast;
                    		localSum=0;	
                    		spareWaitIndex = row;
                		}else if(shouldWaitForNextOne){
                			//don't add sum, keep values in waiting list only
                			continue;  			
                		}
                		else{
                			output[row][0]=localSum;
                			localSum=0;	
                		}
                	}
            }
		}*/
		
		
		
		/*
		//ONLY STRIKES IN ALL SCENARIOS WORKING, LOOPING STRIKES NOT CHECKED.
		int spare=-1;
		int strike=-2;
		
		boolean shouldWaitForNextOne=false;
		int spareWaitIndex=-1;
		int spareScoreLast=-1;
		
		boolean shouldWaitForNextTwo=false;
		int strikeWaitIndex=-1;
		int strikeScoreLast=-1;
		int waitStrike=-1;
				
		int[][] input = new int[][]{{1,2},{1,strike},{3,4},{strike,0},{4,4}};
		int [][]output=new int[input.length][1];
		int localSum=-1;
		
		for (int row = 0; row < input.length; row ++){
			localSum=0;
			for (int col = 0; col < 2; col++){
				
					int currentVal = input[row][col];
        			System.out.println (currentVal);
        			if(shouldWaitForNextTwo){
        				--waitStrike;//1
        				if(waitStrike==0){
        					shouldWaitForNextTwo=false;
        					strikeScoreLast=strikeScoreLast+currentVal;
        					output[strikeWaitIndex][0]=strikeScoreLast;
        					//reset strike manipulators
        					strikeWaitIndex=-1;
        					strikeScoreLast=-1;
        					waitStrike=-1;
        				}else{
        					strikeScoreLast=strikeScoreLast+currentVal;
        				}
        			}
        			
		        	if(shouldWaitForNextOne){
                		spareScoreLast = spareScoreLast+currentVal;
                		shouldWaitForNextOne=false;
                		//put value to last index
                		output[spareWaitIndex][0]= spareScoreLast;
                		//reset the indexes
                		spareScoreLast=-1;
                		spareWaitIndex=-1;   
                		localSum=0;
                	}
                	if(currentVal==spare){
	                		shouldWaitForNextOne=true;
	                		spareScoreLast=10;
	                		spareWaitIndex=row;
	                }
                	else if(currentVal ==strike){
                			shouldWaitForNextTwo=true;
                			waitStrike=2;
                			strikeScoreLast=10;
                			strikeWaitIndex=row;
                	}
                	else{
	                	localSum=localSum+currentVal;
	                }
                	if(col==1){
                		if(shouldWaitForNextTwo){
                			continue;
                		}
                		else if(spareScoreLast!=-1 && (false==shouldWaitForNextOne)){
                			output[row][0]=spareScoreLast;
                    		localSum=0;	
                    		spareWaitIndex = row;
                		}else if(shouldWaitForNextOne){
                			//don't add sum, keep values in waiting list only
                			continue;  			
                		}
                		else{
                			output[row][0]=localSum;
                			localSum=0;	
                		}
                	}
            }
		}*/
		
		//THIRD FLOW SUCCESS
		//ONLY SPARES WORKING FINE. LOOPING SPARES AND STRIKE NEED TO CHECK
		/*int spare=-1;
		int strike=-2;
		int total_score = 0;
		boolean shouldWaitForNextOne=false;
		boolean shouldWaitForNextTwo=false;
		int spareWaitIndex=-1;
		int spareScoreLast=-1;
		int[][] input = new int[][]{{1,2},{1,spare},{3,2},{1,spare},{4,4}};
		int [][]output=new int[input.length][1];
		int localSum=0;
		for (int row = 0; row < input.length; row ++){
				for (int col = 0; col < 2; col++){
					int currentVal = input[row][col];
        			System.out.println (currentVal);
		        	if(shouldWaitForNextOne){
                		spareScoreLast = spareScoreLast+currentVal;
                		shouldWaitForNextOne=false;
                		//put value to last index
                		output[spareWaitIndex][0]= spareScoreLast;
                		//reset the indexes
                		spareScoreLast=-1;
                		spareWaitIndex=-1;   
                		localSum=0;
                	}
                	if(currentVal==spare){
	                		shouldWaitForNextOne=true;
	                		spareScoreLast=10;
	                		spareWaitIndex=row;
	                }else{
	                	localSum=localSum+currentVal;
	                }
                	if(col==1){
                		if(spareScoreLast!=-1 && (false==shouldWaitForNextOne)){
                			output[row][0]=spareScoreLast;
                    		localSum=0;	
                    		spareWaitIndex = row;
                		}else if(shouldWaitForNextOne){
                			//don't add sum, keep values in waiting list only
                			continue;                			
                		}
                		else{
                			output[row][0]=localSum;
                			localSum=0;	
                		}
                	}
            }
		}*/
		
		
		
		
		/*
		//SECOND FLOW SUCCESS
		SPARE IN SINGLE FRAME. NOT EXCEEDING FRAME 
		int[][] input = new int[][]{{1,2},{spare,2},{1,2},{spare,2},{1,2}};
		int [][]output=new int[input.length][1];
		ArrayList<Integer> waitingList=new ArrayList<>();
		int localSum=0;
		int localSumSpare=-1;
		
		for (int row = 0; row < input.length; row ++){
			
				for (int col = 0; col < 2; col++){
					int currentVal = input[row][col];
                	System.out.println (currentVal);
                	if(shouldWaitForNextOne){
                		localSumSpare=localSumSpare+currentVal;
                		shouldWaitForNextOne=false;
                	}
                	if(currentVal==spare){
	                		shouldWaitForNextOne=true;
	                		localSum=localSum+10;
	                		localSumSpare=10;
	                }else{
	                	localSum=localSum+currentVal;
	                }
                	if(col==1){
                		if(localSumSpare!=-1){
                			output[row][0]=localSum;
                    		localSum=0;	
                		}else{
                			output[row][0]=localSum;
                			localSum=0;	
                			localSumSpare=-1;	
                		}
                	}
            }
		}
		*/
		
		
		//FIRST FLOW TO FIND SUMS OF INDIVIDUAL FRAME
		//Happy flow
		/*for (int row = 0; row < 10; row ++){
			int localSum=0;
				for (int col = 0; col < 2; col++){
                {
                	System.out.println (input[row][col]);
                	localSum=localSum+input[row][col];
                	if(col==1){
                		output[row][0]=localSum;
                		localSum=0;
                	}
                }
                
            }
		}*/
		//call printOutput() to print result
		
	}
	private void printOutput(){
		System.out.println("==================OUTPUT==================");
			
		for (int row = 0; row < input.length; row ++)
			for (int col = 0; col < 1; col++)
				System.out.println (output[row][col]);
		}
}


