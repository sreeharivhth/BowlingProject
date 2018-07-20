package one.bow;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;


public class MainManagerTest {

	private MainManager mainManagerObj;
	private static final int STRIKE = 10;
	private static final int SPARE = 201;
	private static final int STRIKE_BUDDY = 401;
	private LinkedList<int[][]> inputArrayValues;
	private LinkedList<int[][]> outputArrayValues;
	
	@Before
	public void init() {
		mainManagerObj=new MainManager();
		inputArrayValues=new LinkedList<>();
		inputArrayValues.add(new int[][]{{ STRIKE,STRIKE_BUDDY }, { 7,2 }, { STRIKE,STRIKE_BUDDY  }, { 6,3 },
							{ 8,SPARE }, { 7,0 }, { 7,SPARE }, { 9,0  },{ 9,0 }, { 9 , SPARE ,9}});
		
		inputArrayValues.add(new int[][]{ { STRIKE,STRIKE_BUDDY }, { 8,1 }, { 5,4 }, { STRIKE,STRIKE_BUDDY },
									{ 8,1 }, { 7,2 }, { 6,3 }, { 7,2  },{ 8,SPARE }, { STRIKE, STRIKE ,9} });
		
		inputArrayValues.add(new int[][] {  { STRIKE,STRIKE_BUDDY  }, { 9,0 }, { 7,SPARE }, { STRIKE,STRIKE_BUDDY  },
						{ STRIKE,STRIKE_BUDDY  }, { 7, 0 }, { 9, SPARE }, { 8 , SPARE },{ STRIKE,STRIKE_BUDDY  }, { 9 , SPARE , 1} });
		
		inputArrayValues.add(new int[][]{ { STRIKE,STRIKE_BUDDY }, { 7,2 }, { STRIKE,STRIKE_BUDDY  }, { 6,3 },
							{ 8,SPARE }, { 7,0 }, { 7,SPARE }, { 9,0  },{ 9,0 }, { 9 , SPARE ,9}});
		
		inputArrayValues.add(new int[][] { { STRIKE,STRIKE_BUDDY }, { 8,1 }, { 5,4 }, { STRIKE,STRIKE_BUDDY },
							{ 8,1 }, { 7,2 }, { 6,3 }, { 7,2  },{ 8,SPARE }, { STRIKE, STRIKE ,9} });
		
		inputArrayValues.add(new int[][] { { 1,1 }, { 1,1 }, { 1,1 }, { 1,1 },{ 1,1 }, { 1,1 }, { 1,1 }, { 1,1 },
								{ 1,1 }, { 1,1, 0 } });
		
		inputArrayValues.add(new int[][] { { STRIKE,STRIKE_BUDDY }, { STRIKE,STRIKE_BUDDY }, { STRIKE,STRIKE_BUDDY }, { STRIKE,STRIKE_BUDDY },
							{ STRIKE,STRIKE_BUDDY }, { STRIKE,STRIKE_BUDDY }, { STRIKE,STRIKE_BUDDY }, { STRIKE,STRIKE_BUDDY },
							{ STRIKE,STRIKE_BUDDY }, { STRIKE, STRIKE, STRIKE } });
		
		inputArrayValues.add(new int[][] { { 8,0 }, { 6,0 }, { STRIKE,STRIKE_BUDDY }, { STRIKE,STRIKE_BUDDY },{ STRIKE,STRIKE_BUDDY }, { 8,0 }, 
								{STRIKE,STRIKE_BUDDY}, { 6,1 },{ STRIKE,STRIKE_BUDDY }, { STRIKE,7,2} });
		
		inputArrayValues.add(new int[][] { { STRIKE,STRIKE_BUDDY }, { 8,1 }, { 5,4 }, { STRIKE,STRIKE_BUDDY },{ 8 , 1 }, { 7, 2 }, 
											{6 , 3 }, { 7, 2 },{ 8,SPARE }, { STRIKE,STRIKE,9} });
		
	 
		outputArrayValues=new LinkedList<>();
		
		outputArrayValues.add(new int[][]{{19},{28},{47},{56},{73},{80},{99},{108},{117},{136}});
		outputArrayValues.add(new int[][]{{19},{28},{37},{56},{65},{74},{83},{92},{112},{141}});
		outputArrayValues.add(new int[][]{{19},{28},{48},{75},{92},{99},{117},{137},{157},{168}});
		outputArrayValues.add(new int[][]{{19},{28},{47},{56},{73},{80},{99},{108},{117},{136}});
		outputArrayValues.add(new int[][]{{19},{28},{37},{56},{65},{74},{83},{92},{112},{141}});
		outputArrayValues.add(new int[][]{{2},{4},{6},{8},{10},{12},{14},{16},{18},{20}});
		outputArrayValues.add(new int[][]{{30},{60},{90},{120},{150},{180},{210},{240},{270},{300}});
		outputArrayValues.add(new int[][]{{8},{14},{44},{72},{90},{98},{115},{122},{149},{168}});
		outputArrayValues.add(new int[][]{{19},{28},{37},{56},{65},{74},{83},{92},{112},{141}});
		
	}
	
	@Test
	public void testCalculateScores() {
		
		for(int i=0;i<inputArrayValues.size();i++){
			assertArrayEquals(outputArrayValues.get(i),mainManagerObj.calculateScores(inputArrayValues.get(i)));
		}
	}


}
