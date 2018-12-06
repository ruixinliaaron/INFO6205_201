package genetic_MazeProblem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

public class Generation {
	public static final int MAXGENERATION = 10000;
	public static final int FIRST_GENERATION = 1000;
	private static Genetic_Manipulation bestOfGeneration;
	static PriorityBlockingQueue<Genetic_Manipulation> pq = new PriorityBlockingQueue<>();
	public static int GENERATION=0;
	public volatile double hightestScore = 0.00;//better than sychronize and explicit lock

	public class Population implements Runnable{
		int lo;
		int hi;
		public Population(int i, int j) {
			lo=i;
			hi=j;
		}
		@Override
		public void run() {
			while(lo<hi) {
				Genetic_Manipulation genetic = new Genetic_Manipulation();
				Gene[] genes = new Gene[200];
				for(int j=0;j<genetic.getGenearr().length;j++) {
					genes[j] = new Gene();
				}
				genetic.setGenearr(genes);
				genetic.Mutate();
				genetic.Move();
				pq.add(genetic);
				lo++;
			}
			GENERATION += 1;

		do {
			bestOfGeneration = pq.peek();

			if(bestOfGeneration!=null) {
				if(hightestScore < bestOfGeneration.getScore()) {
					hightestScore = bestOfGeneration.getScore();
				}
				System.out.print("Current Thread: "+Thread.currentThread().getId());
				System.out.printf(" Generation %2d| Position: %2d,%2d|Status:%3d|Score:%7.2f|FoundTrap:%b|hasGems:%b|passTrap:%b\n",
						GENERATION, bestOfGeneration.m.getX(), bestOfGeneration.m.getY(), bestOfGeneration.getState(), bestOfGeneration.getScore(), 
						bestOfGeneration.m.foundTrap(), bestOfGeneration.m.hasGems(), bestOfGeneration.m.passTrap());
				doNextGen();	
			}else
				break;
		}while(bestOfGeneration.getState()!=3);
	}
}
	
	public static void doNextGen() {
		ArrayList<Genetic_Manipulation> parents = new ArrayList<>();
		Genetic_Manipulation first = pq.peek();
		for(int i=0;i<5;i++) {
			try {
				parents.add(pq.poll());
			}catch(Exception e) {
				break;
			}
			
		}
		//pq.clear();
		pq.add(first);
		for(Genetic_Manipulation f:parents) {
			for(Genetic_Manipulation m:parents) {
				 for(int j=0;j<4;j++) {
					 try {
						 Genetic_Manipulation generic = new Genetic_Manipulation(f, m);
							generic.Mutate();
							generic.Move();
							pq.add(generic);
					 }catch(Exception e) {
							break;
						}
					
				 }
			}
		}
		GENERATION+=1;
		
	}
	

}
