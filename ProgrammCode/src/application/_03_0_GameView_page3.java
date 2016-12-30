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
import javafx.scene.control.SingleSelectionModel;
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
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
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
@SuppressWarnings("restriction")
public class _03_0_GameView_page3 extends Application
{
	public TabPane tabPane;
	public GridPane gridPane ; // komplexes Gitter / Raster mit exakter KomponentenAnordnung
	public static Scene scene;
	@Override
	public void start(Stage GameViewStage)
	{
		GameViewStage.setTitle("Spielwelt");
		tabPane = new TabPane();
// ALLE Tabs NICHT schließen können , falls fehlt, können Tabs mit X geschlossen, ABER NICHT mehr geöffnet werden!!
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
// hierdurch können die Tabs individuell selektiert werden (möchte man z.B., dass statt dem Tab 1 ds Tb 3 beim laden diese DREIFACH-TabMenüs angezeigt wird, so kann man dies nun explizit auswählen durch: )
		SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
		gridPane = new GridPane(); // komplexes Gitter / Raster mit exakter KomponentenAnordnung
		_03_1_SpielFeld leinwand = new _03_1_SpielFeld();
		scene = new Scene(tabPane, 900, 700);
/**********************************************************************
 * Tab / Reitermenü 1: SPIEL
 ************************************************************************/
		Tab tab1 = new Tab();
		tab1.setText("Spiel");
		tab1.setContent(leinwand);
/**********************************************************************
 * Tab / Reitermenü 2: Profil
************************************************************************/
		_03_2_Profil_page3 profilLayOut = new  _03_2_Profil_page3();
		Tab tab2 = new Tab();
		tab2.setText("Profil");
		profilLayOut.start(GameViewStage);
		tab2.setContent(profilLayOut.gridPane);
/**********************************************************************
 * Tab / Reitermenü 3: Inventar
 ************************************************************************/
		_03_3_Inventar_page3 inventarLayout = new  _03_3_Inventar_page3();
		Tab tab3 = new Tab();
		tab3.setText("Inventar");
		inventarLayout.start(GameViewStage);
		tab3.setContent(inventarLayout.gridPane);
/**********************************************************************
 * Tab / Reitermenü 4: Spiel-Optionen
************************************************************************/
		_03_4_Tab_SpielOptionen_page3 spielOptionen = new  _03_4_Tab_SpielOptionen_page3();
		Tab tab4 = new Tab(); tab4.setText("Spiel-Optionen");
		spielOptionen.start(GameViewStage);
		tab4.setContent(spielOptionen.gridPane);
		/**********************************************************************
		 * ACHTUNG: falls man das Design von der SpielOptionen-Seite aus geändert hat, wird sofort wieder zu dieser zurückgesprungen :-)
		 ************************************************************************/
		if(_03_4_Tab_SpielOptionen_page3.spielOptionSelection)
		{
			_03_4_Tab_SpielOptionen_page3.spielOptionSelection = false;
			selectionModel.select(tab4); //select by object
//			selectionModel.select(1); //select by index starting with 0
//			selectionModel.clearSelection(); //clear your selection
		}
//___________________ Layout-GridPane-Komponenten anbringen __________________
		gridPane.setPadding(new Insets(10));
		gridPane.setHgap(0);
		ColumnConstraints spalte1 = new ColumnConstraints(200);
		ColumnConstraints spalte2 = new ColumnConstraints(120);
		ColumnConstraints spalte3 = new ColumnConstraints(125);
		ColumnConstraints spalte4 = new ColumnConstraints(160);
		RowConstraints zeile1 = new RowConstraints(150);
		RowConstraints zeile2 = new RowConstraints(50);
		RowConstraints zeile3 = new RowConstraints(50);
		RowConstraints zeile4 = new RowConstraints(50);
		gridPane.getColumnConstraints().addAll(spalte1, spalte2, spalte3, spalte4);
		gridPane.getRowConstraints().addAll(zeile1, zeile2, zeile3, zeile4 );

		tabPane.getTabs().addAll(tab1, tab2, tab3, tab4);
		if(_00_Launcher_page1.style1Selected){scene.getStylesheets().add(getClass().getResource("Style 1.css").toExternalForm());}
		if(_00_Launcher_page1.style2Selected){scene.getStylesheets().add(getClass().getResource("Style 2.css").toExternalForm());}
		if(_00_Launcher_page1.style3Selected){scene.getStylesheets().add(getClass().getResource("Style 3.css").toExternalForm());}
/***  Stage Obejekt erstellen und anzeigen:   ***/
		GameViewStage.getEventDispatcher();
		GameViewStage.setOnCloseRequest(e ->	{ System.exit(0);});
		GameViewStage.setScene(scene);
		GameViewStage.setResizable(false);
		GameViewStage.show();
	}
	public static void main(String[] args){launch(args);}
}
