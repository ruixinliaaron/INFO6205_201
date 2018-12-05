package genetic_MazeProblem_Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import genetic_MazeProblem.Gene;
import genetic_MazeProblem.Genetic_Manipulation;
import genetic_MazeProblem.MazeModel;

@SuppressWarnings("ALL")
public class UnitTest {

	@Test
	public void Maze_Test1() throws Exception {
		MazeModel m1 = new MazeModel();
		assertEquals(9, m1.getMap()[1][3]);
		assertEquals(46, m1.getTrace()[18][18].distance_cur_gems);//mt1.getTrace()[18][18].disFromKey
		assertEquals(25, m1.getTrace()[3][18].distance_cur_trap);
	}
	
	@Test
	public void Maze_Test2() throws Exception {
		MazeModel m2 = new MazeModel();
		m2.getStatus(0, -1);
		m2.getStatus(0, -1);
		m2.getStatus(0, -1);
		m2.getStatus(0, -1);
		m2.getStatus(0, -1);
		assertEquals(18, m2.getX());
		assertEquals(13, m2.getY());
		m2.getStatus(-1, 0);
		m2.getStatus(-1, 0);
		m2.getStatus(0, -1);
		// move position test
		assertEquals(16, m2.getX());
		assertEquals(12, m2.getY());
	}
	
	@Test
	public void Maze_Test3() throws Exception {
		MazeModel m3 = new MazeModel();
		m3.getStatus(0, -1);
		m3.getStatus(0, -1);
		m3.getStatus(0, -1);
		m3.getStatus(0, -1);
		m3.getStatus(0, -1);
		assertEquals(18, m3.getX());
		assertEquals(13, m3.getY());
		m3.getStatus(-1, 0);
		m3.getStatus(-1, 0);
		m3.getStatus(0, -1);

		assertEquals(0, m3.getStatus(-1, 0));
		m3.getStatus(-1, 0); 
		assertEquals(15, m3.getX());
	}
	
	@Test
	public void Maze_Test4() throws Exception {
		Genetic_Manipulation genetic = new Genetic_Manipulation();
		assertNotNull(genetic);
		assertEquals(200, genetic.getGenearr().length);
	}
	
	@Test
	public void Maze_Test5() throws Exception {
		Genetic_Manipulation genetic1 = new Genetic_Manipulation();
		genetic1.setScore(1000.00);
		Genetic_Manipulation genetic2 = new Genetic_Manipulation();
		genetic2.setScore(888.88);
		assertEquals(-1, genetic1.compareTo(genetic2));
	}
	
	@Test
	public void Maze_Test6() throws Exception {
		Gene[] genes = new Gene[200];
		for (int i = 0; i < 150; i++) {
			genes[i] = new Gene(2);
		}
		Genetic_Manipulation genetic1 = new Genetic_Manipulation();
		genetic1.setGenearr(genes);
		genetic1.setDiedStep(150);
		Genetic_Manipulation genetic2 = new Genetic_Manipulation();
		genetic2.setDiedStep(150);
		genetic2.setGenearr(genes);
		
		assertEquals(2, genetic1.getGenearr()[20].getGene());
		assertEquals(2, genetic2.getGenearr()[20].getGene());
	}
	
	@Test
	public void Maze_Test7() throws Exception {
		Gene[] genes = new Gene[150];
		for (int i = 0; i < 150; i++) {
			genes[i] = new Gene(2);
		}
		Genetic_Manipulation genetic1 = new Genetic_Manipulation();
		genetic1.setGenearr(genes);
		genetic1.setDiedStep(150);
		Genetic_Manipulation genetic2 = new Genetic_Manipulation();
		genetic2.setDiedStep(150);
		genetic2.setGenearr(genes);
		
		assertEquals(2, genetic1.getGenearr()[20].getGene());
		assertEquals(2, genetic2.getGenearr()[20].getGene());
		Genetic_Manipulation offspring = new Genetic_Manipulation(genetic1, genetic2);
		assertEquals(2, offspring.getGenearr()[20].getGene());
	}
    
	@Test
	public void Maze_Test8() throws Exception {
		Gene gene = new Gene();
		assertEquals(2, gene.UP);
		assertEquals(3, gene.RIGHT);
	}
}
