package genetic_MazeProblem;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class MazeModel {
	
	public static final short W = 1; 
	public static final short F = 3;
	public static final short G = 6;
	public static final short K = 7;
	public static final short S = 8;
	public static final short D = 9; 
	public static final int MAZESIZE = 20;
	
	private int X;
	private int Y;
	private int step;
	private boolean hasGems = false;
	private boolean foundTrap = false;
	private boolean passTrap = false;
	private boolean success = false;
	
	private int CHECKPOINTS = 0;
	private static short[][] MAP =	
	{{W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W},
	{W,0,F,D,F,0,F,0,F,K,W,0,0,0,0,0,W,F,F,W},
	{W,0,F,0,W,0,F,0,W,0,W,0,W,W,W,0,W,F,F,W},
	{W,0,F,6,F,0,F,0,F,0,W,0,0,0,W,0,W,0,0,W},
	{W,0,W,0,0,0,0,0,0,0,W,0,W,0,W,0,0,W,0,W},
	{W,0,W,W,W,W,5,W,W,W,W,0,W,0,W,0,0,W,0,W},
	{W,0,0,W,0,0,0,0,0,0,0,0,W,0,W,W,0,W,0,W},
	{W,W,0,0,0,F,F,0,F,F,0,0,W,W,W,F,0,F,0,W},
	{W,0,0,W,0,F,F,0,F,F,0,0,0,0,0,0,0,0,0,W},
	{W,W,W,W,W,W,W,0,W,W,0,0,W,W,0,W,W,W,W,W},
	{W,0,0,0,0,0,0,0,W,W,0,W,W,0,0,0,0,0,0,W},
	{W,W,5,W,W,W,W,W,0,0,0,0,0,W,W,W,0,W,0,W},
	{W,0,0,0,0,0,0,W,0,0,0,0,0,W,0,W,0,W,0,W},
	{W,0,W,0,W,W,0,W,0,0,0,0,0,W,0,W,0,W,0,W},
	{W,0,W,0,W,0,0,W,W,W,W,W,W,W,0,W,0,0,0,W},
	{W,0,F,0,W,0,W,W,W,F,W,W,F,W,0,W,W,W,W,W},
	{W,0,F,0,W,0,W,0,0,0,0,0,5,0,0,0,0,0,0,W},
	{W,0,F,0,0,5,W,W,W,5,W,0,W,0,W,W,W,W,0,W},
	{W,0,0,0,W,0,0,0,0,0,W,0,W,0,0,0,0,0,S,W},
	{W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W}};
//	private static short[][] MAP 
//	= {{W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W},
//	   {W,0,0,D,0,0,0,0,0,K,W,0,0,0,0,0,W,0,0,W},
//	   {W,0,0,0,W,0,0,0,W,0,W,0,W,W,W,0,W,0,0,W},
//	   {W,0,0,6,0,0,W,0,W,0,W,0,0,0,W,0,W,0,0,W},
//	   {W,0,W,0,0,0,0,0,0,0,W,0,W,0,W,0,0,W,0,W},
//	   {W,0,W,W,W,W,5,W,W,W,W,0,W,0,W,0,0,W,0,W},
//	   {W,0,0,W,0,0,0,0,0,0,0,0,W,0,W,W,0,W,0,W},
//	   {W,W,0,0,0,0,0,0,0,0,0,0,W,W,W,0,0,0,0,W},
//	   {W,0,0,W,0,0,F,0,F,F,0,0,0,0,0,0,0,0,0,W},
//	   {W,W,W,W,W,W,W,0,W,W,0,0,W,W,0,W,W,W,W,W},
//	   {W,0,0,0,0,0,0,0,W,W,0,W,W,0,0,0,0,0,0,W},
//	   {W,W,5,W,W,W,W,W,0,0,0,0,0,W,W,W,0,W,0,W},
//	   {W,0,0,0,0,0,0,W,0,0,0,0,0,W,0,W,0,W,0,W},
//	   {W,0,W,0,W,W,0,W,0,0,0,0,0,W,0,W,0,W,0,W},
//	   {W,0,W,0,W,0,0,W,W,W,W,W,W,W,0,W,0,0,0,W},
//	   {W,0,F,0,W,0,W,W,W,F,W,W,0,W,0,W,W,W,W,W},
//	   {W,0,0,0,W,0,W,0,0,0,0,0,5,0,0,0,0,0,0,W},
//	   {W,0,0,0,0,5,W,W,W,5,W,0,W,0,W,W,W,W,0,W},
//	   {W,0,0,0,W,0,0,0,0,0,W,0,W,0,0,0,0,0,S,W},
//	   {W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W}};
	
	private static Position[][] TRACE;
	
	public int getX() {
		return this.X;
	}
	public int getY() {
		return this.Y;
	}
	public boolean foundTrap() {
		return foundTrap;
	}
	public boolean passTrap() {
		return passTrap;
	}
	public boolean hasGems() {
		return hasGems;
	}
	public final short[][] getMap() {
		return MAP;
	}
	public final Position[][] getTrace() {
		return TRACE;
	}
	public final int getStep() {
		return step;
	}
	
	public MazeModel() {
		this.X = 18;
		this.Y = 18;
		this.step = 0;
		TRACE = new Position[MAZESIZE][MAZESIZE];
		for(int i=0;i<MAZESIZE;i++) {
			for(int j=0;j<MAZESIZE;j++) {
				if(MAP[i][j]==W||MAP[i][j]==F)
					TRACE[i][j] = new Position(i, j, false);
				else {
					TRACE[i][j] = new Position(i, j, true);
				}
			}
		}
		maze_traverse_bfs(1,3,D);
		maze_traverse_bfs(1,9,K);
		maze_traverse_bfs(3,3,G);
	}
	
	private int maze_traverse_bfs(int sx, int sy, int mark) {
		int pathLength = 0;
		Queue<Position> q = new LinkedList<>();
		ArrayList<Position> adjP = new ArrayList<>();
		if(mark==6)	TRACE[sx][sy].path_cur_trap = TRACE[sx][sy];//6 indicate trap 7: gems 9 exit we need gems to pass trap
		if(mark==7)	TRACE[sx][sy].path_cur_gems = TRACE[sx][sy];
		if(mark==9)	TRACE[sx][sy].path_cur_end = TRACE[sx][sy];
		q.add(TRACE[sx][sy]);
		while(!q.isEmpty()) {
			Position lastP = q.poll();
			adjP = get_neighbor_position(lastP, mark);
			for(Position tempp: adjP) {
				if(mark==6) {
					tempp.path_cur_trap = lastP;
					tempp.distance_cur_trap = lastP.distance_cur_trap+1;
				} else if(mark==7) {
					tempp.path_cur_gems = lastP;
					tempp.distance_cur_gems = lastP.distance_cur_gems+1;
				} else if(mark==9) {
					tempp.path_cur_end = lastP;
					tempp.distance_cur_end = lastP.distance_cur_end+1;
				}
				q.add(tempp);
			}
		}
		return pathLength;
	}
	
	private ArrayList<Position> get_neighbor_position(Position p, int mark) {
		ArrayList<Position> positions = new ArrayList<>();
		if(mark == 6) {
			if(TRACE[p.x+1][p.y].walkable && TRACE[p.x+1][p.y].path_cur_trap == null) {
				positions.add(TRACE[p.x+1][p.y]);
			}
			if(TRACE[p.x-1][p.y].walkable && TRACE[p.x-1][p.y].path_cur_trap == null) {
				positions.add(TRACE[p.x-1][p.y]);
			}
			if(TRACE[p.x][p.y+1].walkable && TRACE[p.x][p.y+1].path_cur_trap == null) {
				positions.add(TRACE[p.x][p.y+1]);
			}
			if(TRACE[p.x][p.y-1].walkable && TRACE[p.x][p.y-1].path_cur_trap == null) {
				positions.add(TRACE[p.x][p.y-1]);
			}
		} else if(mark == 7) {
			if(TRACE[p.x+1][p.y].walkable && TRACE[p.x+1][p.y].path_cur_gems == null) {
				positions.add(TRACE[p.x+1][p.y]);
			}
			if(TRACE[p.x-1][p.y].walkable && TRACE[p.x-1][p.y].path_cur_gems == null) {
				positions.add(TRACE[p.x-1][p.y]);
			}
			if(TRACE[p.x][p.y+1].walkable && TRACE[p.x][p.y+1].path_cur_gems == null) {
				positions.add(TRACE[p.x][p.y+1]);
			}
			if(TRACE[p.x][p.y-1].walkable && TRACE[p.x][p.y-1].path_cur_gems == null) {
				positions.add(TRACE[p.x][p.y-1]);
			}
		} else if(mark == 9) {
			if(TRACE[p.x+1][p.y].walkable && TRACE[p.x+1][p.y].path_cur_end == null) {
				positions.add(TRACE[p.x+1][p.y]);
			}
			if(TRACE[p.x-1][p.y].walkable && TRACE[p.x-1][p.y].path_cur_end == null) {
				positions.add(TRACE[p.x-1][p.y]);
			}
			if(TRACE[p.x][p.y+1].walkable && TRACE[p.x][p.y+1].path_cur_end == null) {
				positions.add(TRACE[p.x][p.y+1]);
			}
			if(TRACE[p.x][p.y-1].walkable && TRACE[p.x][p.y-1].path_cur_end == null) {
				positions.add(TRACE[p.x][p.y-1]);
			}
		}
		return positions;
	}
	
	/**
	 * @param x
	 * @param y
	 * @return status
	 * return status: 0 alive; -1 died; 1 find gate without key; 2 find key; F complete
	 */
	public int getStatus(int dx, int dy) {
		if(this.X+dx <0 || this.X+dx>=MAZESIZE || this.Y+dy>=MAZESIZE || this.Y+dy<0) {
			return -1;
		}
		if(moveToNext(this.X+dx,this.Y+dy)) {
			this.step++;
			this.X += dx;
			this.Y += dy;
			if(MAP[this.X][this.Y]==0) return 0;
			if(MAP[this.X][this.Y]==F) {
				this.X -= dx;
				this.Y -= dy;
				return -1;
			}
			if(MAP[this.X][this.Y]==5) {
				this.CHECKPOINTS++;
				return 0;
			}
			if(MAP[this.X][this.Y]==6) {
				if(this.hasGems) {
					this.foundTrap = true;
					this.passTrap = true;
					return 0;
				} else {
					this.X -= dx;
					this.Y -= dy;
					foundTrap = true;
					return 1;
				}
			}
			if(MAP[this.X][this.Y]==7)  {
				this.hasGems = true;
				return 2;
			}
			if(MAP[this.X][this.Y]==9) {
				this.success = true;
				return 3; 
			}
		} else {
			return 0;
		}
		
		return 0;
	}
	
	
	private boolean moveToNext(int x, int y) {
		
		switch (MAP[x][y]) {
			case 0: case F: case 5: case 9: case 7: case 6:
				return true;
			case W:
				return false;
		}
		return false;
	}
	
	public double get_fitness_status() {
		double score = 0.00;
		score += this.foundTrap ? 1000 : 500*(1/(double)(TRACE[this.X][this.Y].distance_cur_trap+1));
		if(this.foundTrap) {
			score += this.hasGems ? 1000 : 500*(1/(double)(TRACE[this.X][this.Y].distance_cur_gems+1));
		}
		if(this.hasGems) {
			score += this.passTrap ? 1000 : 500*(1/(double)(TRACE[this.X][this.Y].distance_cur_trap+1));
		}
		if(this.passTrap) {
			score += this.success ? 1000 : 500*(1/(double)(TRACE[this.X][this.Y].distance_cur_end+1));
		}
		return score;
	}
}
