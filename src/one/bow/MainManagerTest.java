package one.bow;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MainManagerTest {

	//https://opensourceforu.com/2015/08/unit-testing-in-java-using-the-junit-framework/
	
	
	MainManager mainManagerObj;
	private static final int STRIKE = 10;
	private static final int SPARE = -10;
	private static final int STRIKE_BUDDY = -4;
	
	@Before
	public void init() {
		mainManagerObj=new MainManager();
	}
	/*@Test
	public void testMain() {
		fail("Not yet implemented");
	}*/

	@Test
	public void testCalculateScores() {
		
		int[][] inputArray = new int[][] { { 10,-4 }, { 9,0 }, { 7,-10 }, { 1,1 },
			{ 9 , 0  }, { 8,0 }, {8,0 }, { 5,0 },
			{ 9,0 }, { 10,10,6} };
			
		int[][] outputArray = new int[inputArray.length][1];
		
		/*mainManagerObj.input = inputArray;
		assertFalse(mainManagerObj.calculateScores());		
		
		outputArray = mainManagerObj.output;
		int[][]outputCheckArray=new int[][]{{19}, {28}, {39}, {41}, {50}, {58}, {66}, {71}, {80}, {116}};
		
		//[[19], [28], [39], [41], [50], [58], [66], [71], [80], [116]]
		assertArrayEquals(outputCheckArray, outputArray);*/
		
		
		int[][] input2 = new int[][] { { STRIKE,STRIKE_BUDDY }, { STRIKE,STRIKE_BUDDY }, { STRIKE,STRIKE_BUDDY }, { STRIKE,STRIKE_BUDDY },
			{ STRIKE,STRIKE_BUDDY }, { STRIKE,STRIKE_BUDDY }, { STRIKE,STRIKE_BUDDY }, { STRIKE,STRIKE_BUDDY },
			{ STRIKE,STRIKE_BUDDY }, { STRIKE, STRIKE, STRIKE } };//success
			
		int[][]output2= new int[][]{{30},{60},{90},{120},{150},{180},{210},{240},{270},{300}};
		
		mainManagerObj.input = input2;
		assertFalse(mainManagerObj.calculateScores());
		outputArray = mainManagerObj.output;
		assertArrayEquals(output2, outputArray);
		
		int[][] input3 = new int[][] { { STRIKE,STRIKE_BUDDY }, { 9,0 }, { 7,SPARE }, { STRIKE,STRIKE_BUDDY },
			{ STRIKE,STRIKE_BUDDY }, { 7,0 }, { 9,SPARE }, { 8,SPARE },
			{ STRIKE,STRIKE_BUDDY }, { 9,SPARE,1} };
		int[][]output3= new int[][]{{19},{28},{48},{75},{92},{99},{117},{137},{157},{168}};
		
		mainManagerObj.input = input3;
		assertFalse(mainManagerObj.calculateScores());
		System.arraycopy(outputArray, 0, mainManagerObj.output, 0, outputArray.length);
		assertArrayEquals(output3, outputArray);
		
	}

	/*@Test
	public void testMainManager() {
		fail("Not yet implemented");
	}*/

}
