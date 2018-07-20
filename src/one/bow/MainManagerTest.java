package one.bow;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class MainManagerTest {

	private MainManager mainManagerObj;
	private static final int STRIKE = 10;
	private static final int SPARE = -10;
	private static final int STRIKE_BUDDY = -4;
	private int[][] mInput;
	private int[][] mOutput;
	
	@Parameters 
	public static Collection<Object[][]>  testCasesInput(){
		
		return Arrays.asList(new Object[][][][]{
			
			{
				{{ STRIKE,STRIKE_BUDDY }, { 7,2 }, { STRIKE,STRIKE_BUDDY  }, { 6,3 },
					{ 8,SPARE }, { 7,0 }, { 7,SPARE }, { 9,0  },
					{ 9,0 }, { 9 , SPARE ,9}}
				,{{19},{28},{47},{56},{73},{80},{99},{108},{117},{136}}
			},
			{
				{ { STRIKE,STRIKE_BUDDY }, { 8,1 }, { 5,4 }, { STRIKE,STRIKE_BUDDY },
					{ 8,1 }, { 7,2 }, { 6,3 }, { 7,2  },
					{ 8,SPARE }, { STRIKE, STRIKE ,9} }
				,{{19},{28},{37},{56},{65},{74},{83},{92},{112},{141}}
			}
			
			
		});
	}
	
	/*@Before
	public void init() {
		
	}*/
	
	public MainManagerTest(int[][] input,int[][] output) {
		System.out.println("MainManagerTest , input.length = "+input.length+" || output.length = "+output.length);
		mainManagerObj=new MainManager();
		this.mInput = input;
		this.mOutput = output;
	}
	
	@Test
	public void testCalculateScores() {
		
		System.out.println("testCalculateScores()");
		assertEquals(mOutput, mainManagerObj.calculateScores(mInput));
		
		System.out.println("test done");
		//assertArrayEquals(mOutput, mainManagerObj.calculateScores(mInput));
		
		/*assertThat(mainManagerObj.calculateScores(mInput), mOutput);*/
		/*int[][] inputArray = new int[][] { { 10,-4 }, { 9,0 }, { 7,-10 }, { 1,1 },
			{ 9 , 0  }, { 8,0 }, {8,0 }, { 5,0 },
			{ 9,0 }, { 10,10,6} };
			
		int[][] outputArray = new int[inputArray.length][1];*/
		
		/*mainManagerObj.input = inputArray;
		assertFalse(mainManagerObj.calculateScores());		
		
		outputArray = mainManagerObj.output;
		int[][]outputCheckArray=new int[][]{{19}, {28}, {39}, {41}, {50}, {58}, {66}, {71}, {80}, {116}};
		
		//[[19], [28], [39], [41], [50], [58], [66], [71], [80], [116]]
		assertArrayEquals(outputCheckArray, outputArray);*/
		
		
		/*int[][] input2 = new int[][] { { STRIKE,STRIKE_BUDDY }, { STRIKE,STRIKE_BUDDY }, { STRIKE,STRIKE_BUDDY }, { STRIKE,STRIKE_BUDDY },
			{ STRIKE,STRIKE_BUDDY }, { STRIKE,STRIKE_BUDDY }, { STRIKE,STRIKE_BUDDY }, { STRIKE,STRIKE_BUDDY },
			{ STRIKE,STRIKE_BUDDY }, { STRIKE, STRIKE, STRIKE } };//success
			
		int[][]output2= new int[][]{{30},{60},{90},{120},{150},{180},{210},{240},{270},{300}};*/
		
		
		/*assertArrayEquals(mainManagerObj.calculateScores(this.mInput));
		outputArray = mainManagerObj.mOutput;
		assertArrayEquals(output2, outputArray);*/
		
		/*int[][] input3 = new int[][] { { STRIKE,STRIKE_BUDDY }, { 9,0 }, { 7,SPARE }, { STRIKE,STRIKE_BUDDY },
			{ STRIKE,STRIKE_BUDDY }, { 7,0 }, { 9,SPARE }, { 8,SPARE },
			{ STRIKE,STRIKE_BUDDY }, { 9,SPARE,1} };
		int[][]output3= new int[][]{{19},{28},{48},{75},{92},{99},{117},{137},{157},{168}};
		
		mainManagerObj.mInput = input3;
		assertFalse(mainManagerObj.calculateScores());
		System.arraycopy(outputArray, 0, mainManagerObj.mOutput, 0, outputArray.length);
		assertArrayEquals(output3, outputArray);*/
		
	}

	/*@Test
	public void testMainManager() {
		fail("Not yet implemented");
	}*/

}
