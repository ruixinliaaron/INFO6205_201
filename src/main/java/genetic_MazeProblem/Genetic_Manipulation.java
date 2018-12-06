package genetic_MazeProblem;
public class Genetic_Manipulation implements Comparable {

	private Gene[] sequence_gene;
	private int achievements = 0;
	private int state;
	private double score = 0;
	private int step = 0;
	private int maxStep;
	private boolean alive = true;
	private static final int COUNT_GENE = 200;
	private final double MUTATE_PROB = 0.005;//probability of mutate
	static MazeModel m = new MazeModel();

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public int getStep() {
		return step;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public Gene[] getGenearr() {
		return sequence_gene;
	}

	public void setGenearr(Gene[] genearr) {
		this.sequence_gene = genearr;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	public void setDiedStep(int x) {
		this.maxStep = x;
	}

	public Genetic_Manipulation() {
		sequence_gene = new Gene[COUNT_GENE];
		state = 0;
	}
	
	public Genetic_Manipulation(Genetic_Manipulation male, Genetic_Manipulation female) {
		crossOver(male, female);
	}

	public void crossOver(Genetic_Manipulation male, Genetic_Manipulation female) {
		
		sequence_gene = new Gene[COUNT_GENE];
		
		for (int i = 0; i < COUNT_GENE; i++) {
			if(i < male.maxStep) {
				if (male.sequence_gene[i].getGene() == female.sequence_gene[i].getGene()) {
					this.sequence_gene[i] = male.sequence_gene[i];
				} else {
					double flag = Math.random();
					if(flag < (male.getScore()/(male.getScore()+female.getScore())))
						this.sequence_gene[i] = male.sequence_gene[i];
					else
						this.sequence_gene[i] = female.sequence_gene[i];
				}
			} else {
				this.sequence_gene[i] = new Gene();
			}
		}
	}

	public void Mutate() {
		for (Gene g : this.sequence_gene) {
			double f = Math.random();
			if (f <= MUTATE_PROB) {
				double c = Math.random();
				if (c < 0.25)
					g.setGene(0);
				else if (c < 0.5)
					g.setGene(1);
				else if (c < 0.75)
					g.setGene(2);
				else
					g.setGene(3);
				break;
			}
		}
	}

	public void Move() {
		while (this.state >= 0 && this.step < COUNT_GENE && this.isAlive()) {
			switch (this.getGenearr()[step].getGene()) {
				case 0:
					this.state = m.getStatus(1, 0);
					break;
				case 1:
					this.state = m.getStatus(0, -1);
					break;
				case 2:
					this.state = m.getStatus(-1, 0);
					break;
				case 3:
					this.state = m.getStatus(0, 1);
					break;
			}
			this.step++;
			if(this.state == 0) {
				continue;
			}
			if (this.state == -1) {
				this.alive = false;
				for(int i = step; i < COUNT_GENE; i++) {
					this.sequence_gene[i] = new Gene();
				}
				this.maxStep = this.step-1 > 0 ? this.step-1 : 0;
				break;
			}
			if (this.state == 3) {
				break;
			}
		}
		this.setScore(m.get_fitness_status());
	}

	@Override
	public int compareTo(Object o) {
		Genetic_Manipulation that = (Genetic_Manipulation) o;
		if(this.getScore()>that.getScore()) return -1;
		else if(this.getScore() < that.getScore()) return 1;
		else return 0;
	}
}
