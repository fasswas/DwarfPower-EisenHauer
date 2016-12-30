package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import javax.swing.text.Position;

import javafx.scene.image.Image;
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
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
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
@SuppressWarnings({"restriction"})
public class _03_3_Inventar_page3 extends Application
{
	public GridPane gridPane ; // komplexes Gitter / Raster mit exakter KomponentenAnordnung
	public Label birne, gift, schwein;
	public TextField txtbirne, txtgift, txtschwein;
	public Scene scene;
	public static Button load, back;

	@Override
	public void start(Stage primaryStage)
	{
		primaryStage.setTitle("Spielansicht");
		gridPane = new GridPane(); // komplexes Gitter / Raster mit exakter KomponentenAnordnung
		birne = new Label("Birne");
		txtbirne = new TextField(); txtbirne.setMaxWidth(150); txtbirne.editableProperty().set(false);
		gift= new Label("Gift");
		txtgift = new TextField(); txtgift.setMaxWidth(150); txtgift.editableProperty().set(false);
		schwein = new Label("Schwein");
		txtschwein = new TextField(); txtschwein.setMaxWidth(150); txtschwein.editableProperty().set(false);
//___________________ Layout-GridPane-Komponenten anbringen __________________
		ColumnConstraints spalte1 = new ColumnConstraints(15);
		ColumnConstraints spalte2 = new ColumnConstraints(70);
		RowConstraints zeile1 = new RowConstraints(1);
		RowConstraints zeile2 = new RowConstraints(50);
		RowConstraints zeile3 = new RowConstraints(50);
		RowConstraints zeile4 = new RowConstraints(50);

		gridPane.getColumnConstraints().addAll(spalte1, spalte2);
		gridPane.getRowConstraints().addAll(zeile1, zeile2, zeile3, zeile4);
/************************************************************************************************************************
 * Die einzelnen Elemente passgenau auf das GridPane legen:
 ***********************************************************************************************************************/
/*** Gitter anzeigen um Elemente auszurichten (auskommentieren nachdem Elemente ausgerichtet wurden!!): gridPane.setGridLinesVisible(true);   **/
/*** >>>>>>>>>>>>> **/
//				gridPane.setGridLinesVisible(true);
/*** <<<<<<<<<<<< **/
/***           Node child       	int columnIndex    int rowIndex       int colspan              int rowspan             */
/***        welches Element  	    welche Spalte      welche Zeile   wieviele Spalten lang    wieviele Zeilen lang        */
gridPane.add(	birne, 		                  1, 				2, 		           1, 			            1                   );
gridPane.add(	txtbirne, 					  2, 				2, 			       1, 						1					);
gridPane.add(	gift, 					      1, 				3, 				   1, 						1					);
gridPane.add(	txtgift, 					  2, 				3, 			       1, 						1					);
gridPane.add(	schwein, 				      1, 				4, 				   1, 						1					);
gridPane.add(	txtschwein, 				  2, 				4, 			       1, 						1					);

GridPane.setHalignment(birne, HPos.LEFT);
GridPane.setHalignment(gift, HPos.LEFT);
GridPane.setHalignment(schwein, HPos.LEFT);
/***  Stage Obejekt erstellen und anzeigen:   ***/
	scene = new Scene(gridPane, 900, 700);
	if(_00_Launcher_page1.style1Selected){scene.getStylesheets().add(getClass().getResource("Style 1.css").toExternalForm());}
	if(_00_Launcher_page1.style2Selected){scene.getStylesheets().add(getClass().getResource("Style 2.css").toExternalForm());}
	if(_00_Launcher_page1.style3Selected){scene.getStylesheets().add(getClass().getResource("Style 3.css").toExternalForm());}
		primaryStage.getEventDispatcher();
		primaryStage.setOnCloseRequest(e ->	{ System.exit(0);});
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
//		primaryStage.show();
	}
	public static void main(String[] args){launch(args);}
}
