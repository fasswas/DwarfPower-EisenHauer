package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import javax.swing.text.Position;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Animation.Status;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;

/*@formatter:off */
public class _00_Launcher_page1 extends Application
{
	public GridPane gridPane ; // komplexes Gitter / Raster mit exakter KomponentenAnordnung
	public Label spielname;
	public Text  changeDesign;
	public static Scene scene;
	public static Button back, start, load;
	public static RadioButton style1, style2, style3;
	public  static boolean style1Selected = true;
	public  static boolean style2Selected = false;
	public  static boolean style3Selected = false;
	public  static boolean firstStart = true;
	@SuppressWarnings("restriction")
	@Override
	public void start(Stage primaryStage)
	{
		primaryStage.setTitle("Willkommen");
		gridPane = new GridPane(); // komplexes Gitter / Raster mit exakter KomponentenAnordnung
		// ORIGINAL-Größe:
		scene = new Scene(gridPane, 900, 700);
//________________ Komponenten-Initialisierung
		spielname = new Label("Dwarf Simulator 2000");
		spielname.setFont(Font.font("TimesRoman", FontWeight.BOLD, 40));
		changeDesign = new Text("Möchtest du das Design ändern:");
		changeDesign.setFont(Font.font("Tahoma", FontWeight.LIGHT, 15));
		start = new Button("Neues Spiel beginnen");
		load = new Button("Alten Spielstand laden");
		final ToggleGroup stylesGroup = new ToggleGroup();
		style1 = new RadioButton("Style 1");
		style1.setToggleGroup(stylesGroup);
		if(firstStart)
		{
			style1.setSelected(true);
			firstStart = false;
		}
		style2 = new RadioButton("Style 2");
		style2.setToggleGroup(stylesGroup);

		style3 = new RadioButton("Style 3");
		style3.setToggleGroup(stylesGroup);

////___________________ Layout-GridPane-Komponenten anbringen __________________
		gridPane.setPadding(new Insets(10));
		gridPane.setHgap(0);
		ColumnConstraints spalte1 = new ColumnConstraints(185);
		ColumnConstraints spalte2 = new ColumnConstraints(25);
		ColumnConstraints spalte3 = new ColumnConstraints(110);
		ColumnConstraints spalte4 = new ColumnConstraints(160);
		ColumnConstraints spalte5 = new ColumnConstraints(160);
		RowConstraints zeile1 = new RowConstraints(220);
		RowConstraints zeile2 = new RowConstraints(50);
		RowConstraints zeile3 = new RowConstraints(50);
		RowConstraints zeile4 = new RowConstraints(50);
		RowConstraints zeile5 = new RowConstraints(50);
		RowConstraints zeile6 = new RowConstraints(80);
		gridPane.getColumnConstraints().addAll(spalte1, spalte2, spalte3, spalte4, spalte5);
		gridPane.getRowConstraints().addAll(zeile1, zeile2, zeile3, zeile4, zeile5, zeile6 );
/************************************************************************************************************************
 * Die einzelnen Elemente passgenau auf das GridPane legen:
 ***********************************************************************************************************************/
/*** Gitter anzeigen um Elemente auszurichten (auskommentieren nachdem Elemente ausgerichtet wurden!!): gridPane.setGridLinesVisible(true);   **/
/*** >>>>>>>>>>>>> **/
//		gridPane.setGridLinesVisible(true);
/*** <<<<<<<<<<<< **/
/***           Node child       	int columnIndex    int rowIndex       int colspan              int rowspan             */
/***        welches Element  	    welche Spalte      welche Zeile   wieviele Spalten lang    wieviele Zeilen lang        */

gridPane.add(	spielname, 	  			  2, 				0, 				   4, 						1					);
gridPane.add(	start, 	  				  3, 				1, 				   2, 						1					);
gridPane.add(	load, 					  3, 				2, 				   2, 						1					);
gridPane.add(	changeDesign, 	  		  3, 				4, 				   4, 						1					);
gridPane.add(	style1, 		          1, 				5, 		           2, 			            1                   );
gridPane.add(	style2, 				  3, 				5, 				   1, 						1					);
gridPane.add(	style3, 				  4, 				5, 				   1, 						1					);

	GridPane.setHalignment(spielname, HPos.LEFT);
	GridPane.setHalignment(start, HPos.LEFT);
//	GridPane.setHalignment(style1, HPos.CENTER);
	GridPane.setHalignment(style2, HPos.CENTER);
	GridPane.setHalignment(style3, HPos.RIGHT);
	GridPane.setValignment(changeDesign, VPos.BOTTOM);
//______________________ Button-Activation: _______________________________
	/*** java 8 NEUE ActionEvent-Funktionen YEAH!!!!!!    ****/

		start.setOnAction(e -> {
			_02_WelcomeNewWorld_page2 newGame_page2 = new _02_WelcomeNewWorld_page2();
			newGame_page2.start(primaryStage);
		});
		load.setOnAction(e -> {
			_01_0_LoadSaveGame_page2 loadPage2 = new _01_0_LoadSaveGame_page2();
			loadPage2.start(primaryStage);
		});
		style1.setOnAction(e -> {
				style1Selected = true;
				style2Selected = false;
				style3Selected = false;
				style1.setSelected(true);
				start(primaryStage);
		});
		style2.setOnAction(e -> {
			style2Selected = true;
			style1Selected = false;
			style3Selected = false;
			style2.setSelected(true);
			start(primaryStage);
		});
		style3.setOnAction(e -> {
			style1Selected = false;
			style2Selected = false;
	        style3Selected = true;
	        style3.setSelected(true);
	        start(primaryStage);
	    });
				if(style1Selected)
				{
					style1.setSelected(true);
					scene.getStylesheets().add(getClass().getResource("Style 1.css").toExternalForm());
				}
				if(style2Selected)
				{
					style1Selected = false;
					style2.setSelected(true);
					scene.getStylesheets().add(getClass().getResource("Style 2.css").toExternalForm());
				}
				if(style3Selected)
				{
					style3.setSelected(true);
					scene.getStylesheets().add(getClass().getResource("Style 3.css").toExternalForm());
				}
/***  Stage Obejekt erstellen und anzeigen:   ***/
		primaryStage.getEventDispatcher();
		primaryStage.setOnCloseRequest(e ->	{ System.exit(0);});
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	public static void main(String[] args){launch(args);}
}