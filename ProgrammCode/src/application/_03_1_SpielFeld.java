package application;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/*@formatter:off */
public class _03_1_SpielFeld extends Canvas
{
	public _03_1_SpielFeld(int x, int y){paint();}
	public _03_1_SpielFeld()
	{
//		Breite: 900 Höhe: 700
		setWidth(900);
		setHeight(700);
		// Hintergrundfarbe einstellen
		setStyle("-fx-background-color: white;");
		paint();
	}
	public void paint()
	{
		GraphicsContext gc = getGraphicsContext2D();
		paintWorld(gc);
	}
	public void paintWorld(GraphicsContext gc)
	{
//		gc.setStroke(Color.BLUE);
//		gc.setLineWidth(0.5);
//		gc.strokeLine(0, 0, 999999999, 999999999); // digonale Linie uebers ganze Canvas
//		gc.setStroke(Color.BLUE); // Linienfarbe
//		gc.setLineWidth(1.0); // Liniendicke in Punkt
//		gc.setStroke(Color.RED); // Linienfarbe f�r zweite Linie VORHER festgelegt
//		gc.setLineWidth(25.0); // Liniendicke der zweiten Linie Vorher bestimmt
//		gc.strokeLine(0, 50, 999, 50); // da Linie danach gezeichnet, �berschreibt die erste blaue!
//		gc.setStroke(Color.GREEN); // Linienfarbe f�r dritte Linie VORHER festgelegt
//		gc.setLineWidth(5.0); // Liniendicke der dritten Linie Vorher bestimmt
//		gc.strokeLine(500, 0, 200, 999); // da Linie danach gezeichnet, �berschreibt die andern beiden
		for(int i = 0; i < 1000; i++)
		{
			for(int j = 0; j < 700; j++)
			{
				gc.setStroke(Color.WHITE);
				gc.setLineWidth(0.3);
//				gc.strokeRect(i * 40 + 20 + i,     j * 40 + 20 + j,       40,       40);
				gc.strokeRect(i * 40  + i,     j * 40 + j,       40,       40);
			}
		}
//		gc.setStroke(Color.CHARTREUSE);
//		gc.setLineWidth(0.3);
//		gc.strokeRect(40 + 20, 20, 40, 40);

// links oben Ecke, erstes hellgrünes rechteck
		gc.setFill(Color.CHARTREUSE);
//		gc.fillRect(20, 20, 40, 40);
		gc.fillRect(0, 0, 40, 40);

		gc.setFill(Color.BLUEVIOLET);
//		gc.fillRect(60, 60, 40, 40);
		gc.fillRect(40, 40, 40, 40);

		gc.setFill(Color.BISQUE);
//		gc.fillRect(183, 183, 40, 40);
		gc.fillRect(163, 163, 40, 40);

	}
	public static void main(String[] args){}
}
