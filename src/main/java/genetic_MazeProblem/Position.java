package genetic_MazeProblem;

public class Position {
		public int x;
		public int y;
		public boolean walkable;
		public Position path_cur_trap;
		public Position path_cur_gems;
		public Position path_cur_end;
		public int distance_cur_trap;
		public int distance_cur_gems;
		public int distance_cur_end;
		Position(int x, int y, boolean walkable) {
			this.x = x;
			this.y = y;
			this.walkable = walkable;	
			this.path_cur_trap = null;		
			this.path_cur_gems = null;		
			this.path_cur_end = null;		
			this.distance_cur_trap = 0;
			this.distance_cur_gems = 0;
			this.distance_cur_end = 0;
	}
}
