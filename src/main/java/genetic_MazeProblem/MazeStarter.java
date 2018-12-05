package genetic_MazeProblem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import genetic_MazeProblem.Generation.Population;

public class MazeStarter {
	public static void main(String[] args) { 
		ExecutorService e = Executors.newFixedThreadPool(2);
		Generation d = new Generation();
		Population thread1 =  d.new Population(0,100);
		Population thread2 =  d.new Population(0,100);
		e.execute(thread1);
		e.execute(thread2);
		e.shutdown();
		e.shutdownNow();
	}
}
