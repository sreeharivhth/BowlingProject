package one.bow;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MainManagerTest {

	//https://opensourceforu.com/2015/08/unit-testing-in-java-using-the-junit-framework/
	
	
	MainManager mainManagerObj;
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
		
		mainManagerObj.input = inputArray;
		assertFalse(mainManagerObj.calculateScores());		
		
		outputArray = mainManagerObj.output;
		int[][]outputCheckArray=new int[][]{{19}, {28}, {39}, {41}, {50}, {58}, {66}, {71}, {80}, {116}};
		
		//[[19], [28], [39], [41], [50], [58], [66], [71], [80], [116]]
		assertArrayEquals(outputCheckArray, outputArray);
		
	}

	/*@Test
	public void testMainManager() {
		fail("Not yet implemented");
	}*/

}
