package application;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

import engine.Labyrinth;
import engine.MapLoader;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.image.Image; // matthias
import javafx.scene.image.ImageView; // matthias
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;

/*@formatter:off */
public class _03_1_SpielFeld extends Canvas implements EventHandler<KeyEvent>
{
	public static MapLoader mapLoader;
	public static FileInputStream fileInputStream;
	public static String fileName;
	public static int[][] field;
	public static Labyrinth level;
	public static Image mon1,mon2,mon3,mon4,mon5;
	public static boolean map2 = false;
	//public _03_1_SpielFeld(int x, int y){paint();}
	public _03_1_SpielFeld() throws FileNotFoundException
	{
		map2 = true;   // für Map 1 hier auskommentieren
		if(map2)
		{

			mon1 = new Image(getClass().getResourceAsStream("goblin.png"));
			mon2 = new Image(getClass().getResourceAsStream("goblin2.png"));
			mon3 = new Image(getClass().getResourceAsStream("orc.png"));
			mon4 = new Image(getClass().getResourceAsStream("skelmage.png"));
			mon5 = new Image(getClass().getResourceAsStream("skelmage2.png"));
			fileName = "Maps\\Map2.txt"; // Textfile einen String zuweisen
		}
		else
		{
			fileName = "Maps\\Map1.txt"; // Textfile einen String zuweisen
		}
		mapLoader = new MapLoader();
		fileInputStream = new FileInputStream(fileName);
		field =	mapLoader.readTextFileToArray(fileInputStream);
		level = new Labyrinth(field, playCo);
		setWidth(900);
		setHeight(700);
		setStyle("-fx-background-color: white;");
		paint();
		this.setOnKeyPressed(this);
		System.out.println("constructed!\n");
	}

	/*   Deaktiviert, da nun per Textfile Map erzeugen möglich ist

	int[][] field = new int[][]{
	//   0   1   2   3   4   5   6	 7   8	 9  10  11  12  13  14  15  16  17  18  19
	  { 99, 99, 00, 99, 00, 00, 00, 99, 99, 00, 00, 00, 99, 00, 00, 00, 99, 99, 99, 99},  // 0
	  { 00, 99, 00, 99, 00, 99, 00, 00, 99, 00, 00, 00, 00, 00, 00, 99, 00, 00, 00, 99},  // 1
	  { 00, 99, 00, 99, 00, 00, 00, 99, 99, 99, 99, 00, 00, 00, 00, 99, 00, 00, 00, 00},  // 2
	  { 99, 00, 99, 99, 00, 99, 00, 99, 00, 00, 99, 00, 00, 00, 00, 99, 99, 00, 00, 99},  // 3
	  { 00, 00, 00, 99, 00, 99, 00, 99, 00, 00, 00, 00, 99, 00, 00, 00, 99, 99, 00, 99},  // 4
	  { 00, 99, 00, 00, 00, 99, 00, 00, 00, 00, 00, 00, 99, 00, 99, 00, 00, 00, 00, 00},  // 5
	  { 00, 00, 00, 99, 00, 99, 00, 99, 00, 00, 00, 99, 99, 00, 99, 99, 00, 00, 00, 99},  // 6
	  { 99, 99, 99, 99, 00, 99, 99, 99, 99, 99, 00, 00, 00, 00, 00, 99, 00, 00, 00, 99},  // 7
	  { 00, 00, 00, 99, 00, 00, 99, 00, 99, 00, 00, 00, 00, 00, 00, 99, 00, 99, 00, 00},  // 8
	  { 00, 99, 00, 00, 00, 99, 99, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 99, 99, 99},  // 9
	  { 00, 00, 00, 99, 00, 99, 00, 99, 00, 99, 00, 00, 99, 00, 00, 99, 00, 00, 00, 00},  // 10
	  { 99, 99, 99, 99, 00, 00, 00, 99, 00, 00, 00, 00, 99, 99, 00, 99, 00, 00, 00, 00},  // 11
	  { 00, 00, 00, 99, 00, 99, 00, 00, 00, 00, 00, 00, 99, 00, 00, 00, 00, 99, 00, 99},  // 12
	  { 00, 99, 00, 00, 00, 99, 00, 00, 00, 00, 00, 00, 00, 00, 00, 99, 99, 99, 00, 99},  // 13
	  { 00, 99, 00, 00, 00, 99, 00, 99, 00, 99, 00, 00, 00, 00, 00, 99, 00, 00, 00, 99}   // 14
	};

*/
	int[] playCo = {4,4};  // Player Coordinates, Y,X

	public void moveUP() {
		if ( (playCo[0] > 0) && (level.laby[playCo[0]-1][playCo[1]] == 0) )
		{
			playCo[0]--;
			this.paint();
		}
	}

	public void moveDN() {
		if ( (playCo[0] < level.ydim) && (level.laby[playCo[0]+1][playCo[1]] == 0) ) {
			playCo[0]++;
			this.paint();
		}
	}

	public void moveLE() {
		if ( (playCo[1] > 0) && (level.laby[playCo[0]][playCo[1]-1] == 0) ) {
			playCo[1]--;
			this.paint();
		}
	}

	public void moveRI() {
		if ( (playCo[1] < level.xdim) && (level.laby[playCo[0]][playCo[1]+1] == 0) ) {
			playCo[1]++;
			this.paint();
		}
	}

	int[] end   = {7,8}; // for navi...
	int[] exit = new int[2];
	int[][] LOSmask;
	char[] solution;

//	Labyrinth level = new Labyrinth(field, playCo);

// Loading images:
  // objects
	Image dwarf = new Image(getClass().getResourceAsStream("dwarf3.png"));
	Image stairs = new Image(getClass().getResourceAsStream("stairs.png"));
	Image lisaMon = new Image(getClass().getResourceAsStream("lisaMon.png"));

  // tiles
	Image floor = new Image(getClass().getResourceAsStream("floor.png"));
	Image floorDark = new Image(getClass().getResourceAsStream("floorDark.png"));
	Image wall = new Image(getClass().getResourceAsStream("exwall.png"));
	Image wallDark = new Image(getClass().getResourceAsStream("exwallDark.png"));


	@Override
	public void handle(KeyEvent event) {
		//text.setText("A Key");
		System.out.println("lol");
	}
	public void paint()	{
		GraphicsContext gc = getGraphicsContext2D();
		paintWorld(gc);
	}

	public void paintWorld(GraphicsContext gc)
	{
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
		//level.displayLOS();

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

        if(map2)
        {
        	exit[0] = 12; exit[1] = 4;
        	gc.drawImage(stairs,	exit[0]*40+20+exit[0], 	exit[1]*40+20+exit[1],  40,40);
        }
        else
        {
        	// floor elements
        	exit[0] = 8; exit[1] = 9;
        	//  if (LOSmask[exit[1]][exit[0]] != 0)
        	gc.drawImage(stairs,	exit[0]*40+20+exit[0], 	exit[1]*40+20+exit[1],  40,40);
        	//  x:             x*40(koord) + 20(rand) + koordKorrektur // drawsize  // y (...)
        }

        // Player
        int x = playCo[1];        int y = playCo[0];
        gc.drawImage(dwarf, 	x*40+15+x,	y*40+15+y,    50,50);

        // Monster
        gc.drawImage(lisaMon, 	11*40+20+8, 8*40+20+8,	40,40);
        if(map2)
        {
        	gc.drawImage(lisaMon, 	9*40+20+8, 3*40+20+8,	40,40);
        	gc.drawImage(lisaMon, 	9*40+20+8, 4*40+20+8,	40,40);
        	gc.drawImage(lisaMon, 	9*40+20+8, 5*40+20+8,	40,40);
        	gc.drawImage(lisaMon, 	8*40+20+8, 3*40+20+8,	40,40);
        	gc.drawImage(lisaMon, 	8*40+20+8, 4*40+20+8,	40,40);
        	gc.drawImage(lisaMon, 	8*40+20+8, 5*40+20+8,	40,40);
        	gc.drawImage(lisaMon, 	3*40+20+8, 9*40+20+8,	40,40);
        	gc.drawImage(lisaMon, 	4*40+20+8, 9*40+20+8,	40,40);
        	gc.drawImage(lisaMon, 	5*40+20+8, 9*40+20+8,	40,40);
        	gc.drawImage(lisaMon, 	3*40+20+8, 10*40+20+8,	40,40);
        	gc.drawImage(lisaMon, 	4*40+20+8, 10*40+20+8,	40,40);
        	gc.drawImage(lisaMon, 	5*40+20+8, 10*40+20+8,	40,40);
        	gc.drawImage(mon1, 	2*40+20+8, 3*40+20+8,	40,40);
        	gc.drawImage(mon1, 	5*40+20+8, 4*40+20+8,	40,40);
        	gc.drawImage(mon1, 	5*40+20+8, 5*40+20+8,	40,40);
        	gc.drawImage(mon1, 	6*40+20+8, 4*40+20+8,	40,40);
        	gc.drawImage(mon3, 	3*40+20+8, 3*40+20+8,	40,40);
        	gc.drawImage(mon2, 	5*40+20+8, 6*40+20+8,	40,40);
        	gc.drawImage(mon3, 	2*40+20+8, 6*40+20+8,	40,40);
        	gc.drawImage(mon3, 	3*40+20+8, 5*40+20+8,	40,40);
        	gc.drawImage(mon4, 	2*40+20+8, 5*40+20+8,	40,40);
        	gc.drawImage(mon5, 	5*40+20+8, 2*40+20+8,	40,40);

        }

        // walls
        for (int i = 0; i<20;i++)
        {
            for (int j = 0; j<15;j++)
            {
            	if ( (level.laby[j][i] != 0) && (LOSmask[j][i] != 0) )
            	{
            		gc.drawImage(wall, i*40+10+i, j*40+10+j,50,50);
            	}
            	else if ( level.laby[j][i] != 0) //&& (LOSmask[j][i] == 1) )
            	{
            		gc.drawImage(wallDark, i*40+10+i, j*40+10+j,50,50);
            	}
            }
        }
        // light Bubble & the inner darkness of the dwarven condition
        Image view = new Image(getClass().getResourceAsStream("test2.png"));
		gc.drawImage(view, 	x*40+15+x-900,	y*40+15+y-700);
	}
}
