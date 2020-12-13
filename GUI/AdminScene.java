package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AdminScene {
	private Stage window = new Stage();
	private Button DataButton, OverDueButton, StatisticsButton, ChangesButton;
	private Button LogOut;
	private Label Logo;
	private Scene AdminScene;
	
	public AdminScene(Stage stage) {
		AdminScene();
	}
	
	public void AdminScene() {
		//Set up the grid pane
				GridPane grid = new GridPane();
				grid.setPadding(new Insets(10, 10, 10, 10));
				grid.setVgap(15);
				grid.setHgap(8);
				
				//Setting UP BUTTONS
				//DataButton 
				DataButton = new Button("Data");
				DataButton.setPrefSize(120, 25);
				GridPane.setConstraints(DataButton, 0, 0);
				
				//OverDueButton
				OverDueButton = new Button("OverDue");
				OverDueButton.setPrefSize(120, 25);
				GridPane.setConstraints(OverDueButton, 0, 1);
				
				//StatisticsButton 
				StatisticsButton = new Button("Statistics");
				StatisticsButton.setPrefSize(120, 25);
				GridPane.setConstraints(StatisticsButton, 0, 2);
				
				//CHANGES BUTTON
				ChangesButton = new Button("Changes");
				ChangesButton.setPrefSize(120, 25);
				GridPane.setConstraints(ChangesButton, 0, 3);
				
				//LogOut button
				LogOut = new Button("Log Out");
				LogOut.setPrefSize(120, 25);
				GridPane.setConstraints(LogOut, 0, 5);
				
				
				//adding elements go GridPane
				grid.getChildren().addAll(DataButton, OverDueButton, StatisticsButton, ChangesButton, LogOut);
				grid.setAlignment(Pos.CENTER);
				
				
				//LOGO Text
				Logo = new Label("IrishPropertyTax");
				Logo.setTextFill(Color.BLUE);
				Logo.setFont(new Font("Cascadia Code SemiBold", 45));
						
				//HBox for logo
				HBox hbox = new HBox();
				hbox.getChildren().add(Logo);
				hbox.setAlignment(Pos.CENTER);
				
				//Creating Border Pane
				BorderPane border = new BorderPane();
				border.setTop(hbox);
				border.setCenter(grid);
				
				//Setting up scene and stage
				AdminScene = new Scene(border, 500, 450);
				AdminScene.setFill(Color.LIGHTBLUE);
				window.setScene(AdminScene);
				window.setTitle("IrishPropertyTax");
				window.show();
				
				//LogOut action	
				LogOut.setOnAction(e -> {
					new LogInScene(window);
				//	window.close();
				});
				
				//RegisterProperty button action
				DataButton.setOnAction(e -> {
					Data();
				});
				
				OverDueButton.setOnAction(e -> {
					OverDue();
				});
				
				StatisticsButton.setOnAction(e -> {
					Statistics();
				});
				
				ChangesButton.setOnAction(e-> {
					Changes();
				});
			}

	
	private void Data() {
		Stage StageData = new Stage();
		//setsup the window
		StageData.initModality(Modality.APPLICATION_MODAL);
		StageData.setTitle("Data");
		StageData.setMinWidth(375);
		StageData.setMinHeight(400);
		
		//creates layout
		GridPane DataInfo = new GridPane();
		DataInfo.setPadding(new Insets(10, 10, 10, 10));
		DataInfo.setVgap(8);
		DataInfo.setHgap(8);
		BorderPane BorderPaneData = new BorderPane();
		BorderPaneData.setCenter(DataInfo);	
		
		Label TextSelectOwner, TextViewSpecific, TextSelectProperty, TextOwnerSelect, TextViewSelect, TextPropertySelect,
			DueTax, OverdueTax, DueTaxAmount, TotalTax, OverdueTaxAmount, TotalTaxAmount;
		ChoiceBox<String> SelectOwner, ViewSpecific, SelectProperty;
		Button buttonSelectO, buttonSelectV, buttonSelectP, Exit;
		
		//============ALL LABELS==========
		TextSelectOwner = new Label("Select Owner");
		GridPane.setConstraints(TextSelectOwner, 0, 1);
		
		TextViewSpecific = new Label("View Specific");
		GridPane.setConstraints(TextViewSpecific, 0, 3);
		
		TextSelectProperty = new Label("Select Property");
		GridPane.setConstraints(TextSelectProperty, 0, 5);
		
		TextOwnerSelect = new Label("");
		GridPane.setConstraints(TextOwnerSelect, 1, 2);
		TextViewSelect = new Label("");
		GridPane.setConstraints(TextViewSelect, 1, 4);
		TextPropertySelect = new Label("");
		GridPane.setConstraints(TextPropertySelect, 1, 6);
		
		//TAX DISPLAY
		DueTax = new Label("Due Tax:");
		GridPane.setConstraints(DueTax, 0, 8);
		OverdueTax = new Label("Overdue Tax:");
		GridPane.setConstraints(OverdueTax, 0, 9);
		TotalTax = new Label("Total Tax:");
		GridPane.setConstraints(TotalTax, 0, 10);
		//LABELS THAT ARE USED TO INPUT VALUE
		DueTaxAmount = new Label("___________________________________");
		GridPane.setConstraints(DueTaxAmount, 1, 8);
		OverdueTaxAmount = new Label("___________________________________");
		GridPane.setConstraints(OverdueTaxAmount, 1, 9);
		TotalTaxAmount = new Label("___________________________________");
		GridPane.setConstraints(TotalTaxAmount, 1, 10);
		
		//============ALL CHOICEBOXES============
		SelectOwner = new ChoiceBox<>();
		SelectOwner.setPrefSize(175, 22);
		SelectOwner.getItems().addAll("Pappy Santa", "Maya Hee");
		GridPane.setConstraints(SelectOwner, 1, 1);
		ViewSpecific = new ChoiceBox<>();
		ViewSpecific.setPrefSize(175, 22);
		ViewSpecific.getItems().addAll("Property Data", "All Data");
		GridPane.setConstraints(ViewSpecific, 1, 3);
		SelectProperty = new ChoiceBox<>();
		SelectProperty.setPrefSize(175, 22);
		SelectProperty.getItems().addAll("A. 420 Richmond Court, Limerick, V94 K4K4", 
											"B. 14 Sexton Street, Limerick V92 Q1Q2");
		GridPane.setConstraints(SelectProperty, 1, 5);
		
		//============ALL BUTTONS============
		buttonSelectO = new Button("Select Owner");
		buttonSelectO.setPrefSize(100, 22);
		GridPane.setConstraints(buttonSelectO, 2, 1);
		buttonSelectO.setOnAction(e-> {
			TextOwnerSelect.setText("Owner Selected");
		});
		
		buttonSelectV = new Button("Select View");
		buttonSelectV.setPrefSize(100, 22);
		GridPane.setConstraints(buttonSelectV, 2, 3);
		buttonSelectV.setOnAction(e-> {
			TextViewSelect.setText("View Selected");
		});
		
		buttonSelectP = new Button("Select Property");
		buttonSelectP.setPrefSize(100, 22);
		GridPane.setConstraints(buttonSelectP, 2, 5);
		buttonSelectP.setOnAction(e-> {
			TextPropertySelect.setText("Property Selected");
		});
		
		Exit = new Button("Exit");
		Exit.setPrefSize(100, 22);
		GridPane.setConstraints(Exit, 2, 12);
		Exit.setOnAction(e-> {
			StageData.close();
		});
		
		
		DataInfo.getChildren().addAll(TextSelectOwner, TextViewSpecific, TextSelectProperty, SelectOwner, 
										ViewSpecific, SelectProperty, buttonSelectO, buttonSelectV, buttonSelectP,
										Exit, TextOwnerSelect, TextViewSelect, TextPropertySelect, DueTax, 
										OverdueTax, DueTaxAmount, TotalTax, OverdueTaxAmount, TotalTaxAmount);
		//creates new scene
		Scene scene = new Scene(BorderPaneData);
		
		//sets the scene and displays the scene
		StageData.setScene(scene);
		StageData.showAndWait();
		
	}
	
	private void OverDue() {
		//setsup the window
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("OverDue");
		window.setMinWidth(375);
		window.setMinHeight(400);
		
		//creates layout
		GridPane ViewInfo = new GridPane();
		ViewInfo.setPadding(new Insets(10, 10, 10, 10));
		ViewInfo.setVgap(8);
		ViewInfo.setHgap(8);
		BorderPane BorderPaneView = new BorderPane();
		BorderPaneView.setCenter(ViewInfo);	
				
		//creates new scene
		Scene scene = new Scene(BorderPaneView);
				
		//sets the scene and displays the scene
		window.setScene(scene);
		window.showAndWait();
		
	}
	
	private void Statistics() {
		//setsup the window
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Statistics");
		window.setMinWidth(375);
		window.setMinHeight(400);
		
		//creates layout
		GridPane ViewInfo = new GridPane();
		ViewInfo.setPadding(new Insets(10, 10, 10, 10));
		ViewInfo.setVgap(8);
		ViewInfo.setHgap(8);
		BorderPane BorderPaneView = new BorderPane();
		BorderPaneView.setCenter(ViewInfo);	
				
		//creates new scene
		Scene scene = new Scene(BorderPaneView);
				
		//sets the scene and displays the scene
		window.setScene(scene);
		window.showAndWait();
		
	}
	
	private void Changes() {
		//setsup the window
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Changes");
		window.setMinWidth(375);
		window.setMinHeight(400);
		
		//creates layout
		GridPane ViewInfo = new GridPane();
		ViewInfo.setPadding(new Insets(10, 10, 10, 10));
		ViewInfo.setVgap(8);
		ViewInfo.setHgap(8);
		BorderPane BorderPaneView = new BorderPane();
		BorderPaneView.setCenter(ViewInfo);	
				
		//creates new scene
		Scene scene = new Scene(BorderPaneView);
				
		//sets the scene and displays the scene
		window.setScene(scene);
		window.showAndWait();
	}


}
	


























