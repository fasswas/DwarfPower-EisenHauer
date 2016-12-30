package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import engine.Labyrinth;
import engine.MapLoader;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;

/*@formatter:off */
public class _03_1_SpielFeld extends Canvas
{

	int[] playCo = {4,4};  // Player Coordinates, Y,X
	int[] exit = new int[2];

	int[][] LOSmask;
	char[] solution;

	public _03_1_SpielFeld() throws FileNotFoundException {
		setWidth(900);
		setHeight(700);
		setStyle("-fx-background-color: white;");
		exit[0]=8;exit[1]=9;
		paint();
		System.out.println("Dwarf On!!\n");
	}

	 boolean active = true;

	 @SuppressWarnings("resource")
	public int[][] loadMap(String fileName) throws FileNotFoundException {
		MapLoader mapLoader = new MapLoader();
		FileInputStream fileInputStream = new FileInputStream(fileName);
		return mapLoader.readTextFileToArray(fileInputStream);
	 }

	 String map1 = "Maps\\Map1.txt";
	 String map2 = "Maps\\Map2.txt";
	 int[][] field = loadMap(map1);
	 Labyrinth level = new Labyrinth(field, playCo);

	public void moveUP() {
		if ( (playCo[0] > 0) && (level.laby[playCo[0]-1][playCo[1]] == 0) )
		{			playCo[0]--;			this.paint();		}	}
	public void moveDN() {
		if ( (playCo[0] < level.ydim) && (level.laby[playCo[0]+1][playCo[1]] == 0) ) {
			playCo[0]++;			this.paint();		}	}
	public void moveLE() {
		if ( (playCo[1] > 0) && (level.laby[playCo[0]][playCo[1]-1] == 0) ) {
			playCo[1]--;			this.paint();		}	}
	public void moveRI() {
		if ( (playCo[1] < level.xdim) && (level.laby[playCo[0]][playCo[1]+1] == 0) ) {
			playCo[1]++;			this.paint();		}	}



// Loading images:
  // objects
	Image dwarf = 	new Image(getClass().getResourceAsStream("dwarf3.png"));
	Image stairs = 	new Image(getClass().getResourceAsStream("stairs.png"));
	Image lisaMon = new Image(getClass().getResourceAsStream("lisaMon.png"));
	Image goblin = 	new Image(getClass().getResourceAsStream("goblin2.png"));
	Image orc = 	new Image(getClass().getResourceAsStream("orc.png"));

  // tiles
	Image floor = 		new Image(getClass().getResourceAsStream("floor.png"));
	Image floorDark = 	new Image(getClass().getResourceAsStream("floorDark.png"));
	Image wall = 		new Image(getClass().getResourceAsStream("exwall2.png"));
	Image wallAlt = 	new Image(getClass().getResourceAsStream("exwallAlt.png"));

	Image wallDark = 	new Image(getClass().getResourceAsStream("exwallDark.png"));


	public void paint()	{
		GraphicsContext gc = getGraphicsContext2D();
		//paintWorld(gc);

		for(int i = 0; i < 20; i++)
		{
			for(int j = 0; j < 15; j++)
			{
				gc.setStroke(Color.BLACK);
				gc.setLineWidth(0.0);
				gc.strokeRect(i * 40 + 20 + i, j * 40 + 20 + j, 40, 40);
			}
		}
	// VISIBLE AREA
		LOSmask = level.returnLOS();

	// Draw Floor;
        for (int i = 0; i<20;i++)
        {
            for (int j = 0; j<15;j++)
            {
            	if ( (level.laby[j][i] == 0) && (LOSmask[j][i] != 0) )gc.drawImage(floor, i*40+20+i, j*40+20+j,40,40);
            	else if (level.laby[j][i] == 0)// && (LOSmask[j][i] == 1) )
           			gc.drawImage(floorDark, i*40+20+i, j*40+20+j,40,40);
            }
        }

      // floor elements
      //  if (LOSmask[exit[1]][exit[0]] != 0)
        	gc.drawImage(stairs,	exit[0]*40+20+exit[0], 	exit[1]*40+20+exit[1],  40,40);
   		         //  x:             x*40(koord) + 20(rand) + koordKorrektur // drawsize  // y (...)

        // Player
        int x = playCo[1];        int y = playCo[0];
        gc.drawImage(dwarf, 	x*40+15+x,	y*40+15+y,    50,50);

        // Monster
        gc.drawImage(lisaMon,  11*40+20+8, 8*40+20+8,	40,40);
        gc.drawImage(goblin, 	6*40+20+6, 4*40+20+4,	40,40);
        gc.drawImage(orc, 		4*40+20+4, 6*40+20+6,	35,35);


        // walls
        for (int i = 0; i<20;i++){
            for (int j = 0; j<15;j++){
            	if ( (level.laby[j][i] != 0) && (LOSmask[j][i] != 0) )
            		if ((j+i+i) % 7 == 0)
            			 gc.drawImage(wallAlt, i*40+10+i, j*40+10+j,51,52);
            		else gc.drawImage(wall, i*40+10+i, j*40+10+j,51,52);
            	else if ( level.laby[j][i] != 0) //&& (LOSmask[j][i] == 1) )
            		gc.drawImage(wallDark, i*40+10+i, j*40+10+j,51,52);
            }
        }
        // light Bubble & the inner darkness of the dwarven condition
        Image view = new Image(getClass().getResourceAsStream("fog.png"));
		gc.drawImage(view, 	x*40+15+x-900,	y*40+15+y-700);

	} // end paint()


} // end class



// for debug:
//int[][] field = new int[][]{
////   0   1   2   3   4   5   6	 7   8	 9  10  11  12  13  14  15  16  17  18  19
// { 99, 99, 00, 99, 00, 00, 00, 99, 99, 00, 00, 00, 99, 00, 00, 00, 99, 99, 99, 99},  // 0
// { 00, 99, 00, 99, 00, 99, 00, 00, 99, 00, 00, 00, 00, 00, 00, 99, 00, 00, 00, 99},  // 1
// { 00, 99, 00, 99, 00, 00, 00, 99, 99, 99, 99, 00, 00, 00, 00, 99, 00, 00, 00, 00},  // 2
// { 00, 00, 00, 00, 00, 00, 00, 99, 00, 00, 99, 00, 00, 00, 00, 99, 99, 00, 00, 99},  // 3
// { 99, 00, 99, 99, 00, 00, 00, 99, 00, 00, 00, 00, 99, 00, 00, 00, 99, 99, 00, 99},  // 4
// { 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 99, 00, 99, 00, 00, 00, 00, 00},  // 5
// { 00, 00, 00, 00, 00, 00, 00, 99, 00, 00, 00, 99, 99, 00, 99, 99, 00, 00, 00, 99},  // 6
// { 99, 99, 99, 00, 00, 00, 99, 99, 99, 99, 00, 00, 00, 00, 00, 99, 00, 00, 00, 99},  // 7
// { 00, 00, 99, 00, 00, 00, 99, 99, 99, 99, 99, 00, 00, 00, 00, 99, 00, 99, 00, 00},  // 8
// { 00, 99, 99, 99, 00, 99, 99, 99, 00, 00, 00, 00, 00, 00, 00, 00, 00, 99, 99, 99},  // 9
// { 00, 00, 00, 99, 00, 99, 00, 99, 99, 00, 00, 00, 99, 00, 00, 99, 00, 00, 00, 00},  // 10
// { 99, 99, 99, 99, 00, 00, 00, 99, 99, 00, 00, 00, 99, 99, 99, 99, 00, 00, 00, 00},  // 11
// { 00, 00, 00, 99, 00, 99, 00, 00, 00, 00, 00, 00, 99, 00, 00, 00, 00, 99, 00, 99},  // 12
// { 00, 99, 00, 00, 00, 99, 00, 00, 00, 00, 00, 00, 00, 00, 00, 99, 99, 99, 00, 99},  // 13
// { 00, 99, 00, 00, 00, 99, 00, 99, 00, 99, 00, 00, 00, 00, 00, 99, 00, 00, 00, 99}   // 14
//};