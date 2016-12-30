package engine;


public class MonsterRandom extends Monster {

	boolean report = false;
	//int[] coords; // leave this to super class
	Labyrinth myLab; 
	
	public MonsterRandom(int[] coords, Labyrinth lab) {
		super(coords);
		this.myLab = lab;
	}
	
	char[] directions = {'U','D','L','R'};
	char dir;
	int[] lastMove = {0,0};
	
	int maxPatience = 3;
	int patience = maxPatience;  // how often to try new directions

	
	@Override
	void move() {
				while(true) { // try until valid move is found
					dir = directions[(int) (Math.random()*4)];
					if (report) System.out.println("i wanna go " + dir + " ^^");
					int yShift = 0; // where I want to go
					int xShift = 0; // maybe there's food?
					if (dir == 'U') yShift = -1;
					if (dir == 'D') yShift = 1;
					if (dir == 'R') xShift = 1;
					if (dir == 'L') xShift = -1;
					
					// that's where I'm coming from! There's no food there! Picking new direction...
					if (lastMove[0] == -yShift && lastMove[1] == -xShift && patience>0) {
						patience--;
						continue;
						}  
	
					if (okToGo(dir)) {
						coords[0]=coords[0]+yShift;
						coords[1]=coords[1]+xShift;
							lastMove[0] = yShift;
							lastMove[1] = xShift;
						if (report) System.out.println("I changed my coordinates! " + coords[0] + coords[1]);
						patience=maxPatience;
						break; // valid move found, done
					}
				} // while
	}

	boolean okToGo(char dir) {
		int yShift = 0; // where I want to go
		int xShift = 0; // maybe there's food?
		if (dir == 'U') yShift = -1;
		if (dir == 'D') yShift = 1;
		if (dir == 'R') xShift = 1;
		if (dir == 'L') xShift = -1;
		
		if (report) System.out.println("shift: " +yShift+xShift+ "\n"+ "coords: " + coords[0]+coords[1] );//+ myLab.getValue(coords, yShift, xShift));

		try {
			  if (myLab.getValue(coords, yShift, xShift) == 0) {
				  if (report)System.out.println("\n in bounds! -> looking at " +yShift+xShift+ " value there is " + myLab.getValue(coords, yShift, xShift));
				  return true;
			  } else return false;
			} catch (Exception e) {
				if (report)System.out.println("out of bounds");
				  return false;
				} // for out of bounds
	}
	
}
