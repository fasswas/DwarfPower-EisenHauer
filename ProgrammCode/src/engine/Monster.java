package engine;

public class Monster {

	char[] path;
	int[] coords;
	int index = 0;
	
	public Monster(int[] coords) {
		this.coords = coords;
	}
	
	void setPath(char[] newPath) {
		this.path = newPath;
	}
	
	public int getYcoord() {
		return coords[0];
	}
	public int getXcoord() {
		return coords[1];
	}
	
	
	void move() {
		
		if (index > path.length-1) return;
		
		int direction = path[index];
		index++;
		
		if (direction == 'U')
			coords[0]--;
		if (direction == 'D')
			coords[0]++;
		if (direction == 'L')
			coords[1]--;
		if (direction == 'R')
			coords[1]++;
	}
	
}
