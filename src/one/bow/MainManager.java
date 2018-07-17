package one.bow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class MainManager {

	int strike=10;
	int spare=-10;
	int strikeBuddy=-4;
	
	int[][] input = new int[][]{{1,2},{3,spare},{1,spare},{strike,strikeBuddy},{strike,strikeBuddy},{1,2}};
	int[][]output=new int[input.length][1];
	
	ArrayList<WaitingObject> strikeWaitingList = new ArrayList<WaitingObject>();
	
	boolean shouldWaitForSpare=false;
	
	int spareWaitIndex=-1;
	int spareScoreLast=-1;
	
	int strikeWaitIndex=-1;
	int strikeScoreLast=-1;
	
	int localSum=-1;
	
	public static void main(String[] args) {
		new MainManager();
	}
	
	public MainManager() {
						
		//WORKS FOR CONTINUOUS STRIKES,SPARES AS WELL. CORNER CASE YET TO FINISH. 
		
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
										int presentValLocal=currentVal;
										if(currentVal==spare){
											presentValLocal=10;
										}
										waitingObject.liveScore = waitingObject.liveScore+presentValLocal;
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
        			
		        	if(shouldWaitForSpare){
                		spareScoreLast = spareScoreLast+currentVal;
                		shouldWaitForSpare=false;
                		//put value to last index
                		output[spareWaitIndex][0]= spareScoreLast;
                		//reset the indexes
                		spareScoreLast=-1;
                		spareWaitIndex=-1;   
                		localSum=0;
                	}
                	if(currentVal==spare){
	                		shouldWaitForSpare=true;
	                		spareScoreLast=10;
	                		spareWaitIndex=row;
	                }
                	else if(currentVal ==strike){
                			
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
                		if(spareScoreLast!=-1 && (false==shouldWaitForSpare)){
                			output[row][0]=spareScoreLast;
                    		localSum=0;	
                    		spareWaitIndex = row;
                		}else if(shouldWaitForSpare){
                			//don't add sum, keep values in waiting list only
                			continue;  			
                		}
                		else{
                			output[row][0]=localSum;
                			localSum=0;	
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
	
	private void printOutput(){
		
		System.out.println("==================OUTPUT==================");
			
		for (int row = 0; row < input.length; row ++)
			for (int col = 0; col < 1; col++)
				System.out.println (output[row][col]);
		}
}


