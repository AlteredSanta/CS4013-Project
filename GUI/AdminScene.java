package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
				OverDueButton = new Button("Over Due");
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
		Stage StageOverdue = new Stage();
		//setsup the window
		StageOverdue.initModality(Modality.APPLICATION_MODAL);
		StageOverdue.setTitle("Data");
		StageOverdue.setMinWidth(375);
		StageOverdue.setMinHeight(200);
		
		//creates layout
		GridPane gridOverdue = new GridPane();
		gridOverdue.setPadding(new Insets(10, 10, 10, 10));
		gridOverdue.setVgap(8);
		gridOverdue.setHgap(8);
		BorderPane BorderPaneOverdue = new BorderPane();
		BorderPaneOverdue.setCenter(gridOverdue);
		
		Label chooseYear, selectOnRoutine, EnterRkey, display;
		ChoiceBox<String> YYYY, YN;
		TextField EnteredKey;
		Button Exit, getOverdue;
		
		//=============ALL LABELS=============
		chooseYear = new Label("Choose the Year");
		GridPane.setConstraints(chooseYear, 0, 0);
		selectOnRoutine = new Label("Select properties based on routine key");
		GridPane.setConstraints(selectOnRoutine, 0, 1);
		EnterRkey = new Label("Enter Routine Key");
		GridPane.setConstraints(EnterRkey, 0, 2);
		display = new Label("display");
		GridPane.setConstraints(display, 0, 3);
		
		//=============ALL CHOICEBOXES=============
		//YEAR CHOICEBOX
		YYYY = new ChoiceBox<>();
		YYYY.setPrefSize(90, 20);
		YYYY.getItems().addAll("2020");
		GridPane.setConstraints(YYYY, 1, 0);
		
		//BOOLEAN YES/NO
		YN = new ChoiceBox<>();
		YN.setPrefSize(90, 20);
		YN.getItems().addAll("Y","N");
		GridPane.setConstraints(YN, 1, 1);
		
		//=============ALL TEXTFIELDS=============
		//ENTERED ROUTINE KEY
		EnteredKey = new TextField();
		EnteredKey.setPrefSize(90, 22);
		EnteredKey.setPromptText("e.g. V94/A56");
		GridPane.setConstraints(EnteredKey, 1, 2);
		
		//=============ALL BUTTONS=============
		//getOverdue button
		getOverdue = new Button("Get OverDue");
		getOverdue.setPrefSize(100, 22);
		GridPane.setConstraints(getOverdue, 2, 2);
		getOverdue.setOnAction(e-> {
			
		});
		
		//exit button
		Exit = new Button("Exit");
		Exit.setPrefSize(100, 22);
		GridPane.setConstraints(Exit, 2, 5);
		Exit.setOnAction(e-> {
			StageOverdue.close();
		});
		
		String check = YN.getValue();

		gridOverdue.getChildren().addAll(	chooseYear, selectOnRoutine, display, 
											YYYY, YN, Exit, getOverdue, EnterRkey, EnteredKey);
		//creates new scene
		Scene OverdueScene = new Scene(BorderPaneOverdue);
				
		//sets the scene and displays the scene
		StageOverdue.setScene(OverdueScene);
		StageOverdue.showAndWait();
		
	}
	
	private void Statistics() {
		Stage StageStats = new Stage();
		//setsup the window
		StageStats.initModality(Modality.APPLICATION_MODAL);
		StageStats.setTitle("Data");
		StageStats.setMinWidth(375);
		StageStats.setMinHeight(200);
		
		//creates layout
		GridPane DataInfo = new GridPane();
		DataInfo.setPadding(new Insets(10, 10, 10, 10));
		DataInfo.setVgap(8);
		DataInfo.setHgap(8);
		BorderPane BorderPaneStatistics = new BorderPane();
		BorderPaneStatistics.setCenter(DataInfo);	
				
		Label InputPC, TotalTax, AverageTax, NumberOfPropertyTax,  TotalTaxPaid, AverageTaxPaid, NumberOfPropertyTaxPaid;
		TextField EnteredText;
		Button Exit, GetTaxInfo;
		
		//=================ALL LABELS=================
		InputPC = new Label("Enter Post Code");
		GridPane.setConstraints(InputPC, 0, 0);
		TotalTax = new Label("Total Tax Paid:");
		GridPane.setConstraints(TotalTax, 0, 2);
		AverageTax = new Label("Average Tax Paid:");
		GridPane.setConstraints(AverageTax, 0, 3);
		NumberOfPropertyTax = new Label("Number of Property Tax Paid:");
		GridPane.setConstraints(NumberOfPropertyTax, 0, 4);
		
		//Labels used to show the data
		TotalTaxPaid = new Label("0000");
		GridPane.setConstraints(TotalTaxPaid, 1, 2);
		AverageTaxPaid = new Label("0000");
		GridPane.setConstraints(AverageTaxPaid, 1, 3);
		NumberOfPropertyTaxPaid = new Label("0000");
		GridPane.setConstraints(NumberOfPropertyTaxPaid, 1, 4);
		
		//=================TEXT FIELDS=================
		EnteredText = new TextField();
		EnteredText.setPromptText("E.g. A56 / V94");
		GridPane.setConstraints(EnteredText, 1, 0);
		
		//=================ALL THE BUTTONS=================
		//GETTAXINFO BUTTON
		GetTaxInfo =new Button("Get Tax Details");
		GetTaxInfo.setPrefSize(100, 22);
		GridPane.setConstraints(GetTaxInfo, 2, 0);
		GetTaxInfo.setOnAction(e-> {
			
		});
		//EXIT BUTTON
		Exit = new Button("Exit");
		Exit.setPrefSize(100, 22);
		GridPane.setConstraints(Exit, 2, 12);
		Exit.setOnAction(e-> {
			StageStats.close();
		});
		
		DataInfo.getChildren().addAll(	GetTaxInfo, InputPC, TotalTax, AverageTax, NumberOfPropertyTax,
										TotalTaxPaid, AverageTaxPaid, NumberOfPropertyTaxPaid,
										EnteredText, Exit);
		//creates new scene
		Scene StatsScene = new Scene(BorderPaneStatistics);
				
		//sets the scene and displays the scene
		StageStats.setScene(StatsScene);
		StageStats.showAndWait();
		
	}
	
	private void Changes() {
		Stage StageChanges = new Stage();
		//setsup the window
		StageChanges.initModality(Modality.APPLICATION_MODAL);
		StageChanges.setTitle("Data");
		StageChanges.setMinWidth(375);
		StageChanges.setMinHeight(200);
		
		//creates layout
		GridPane gridChanges = new GridPane();
		gridChanges.setPadding(new Insets(10, 10, 10, 10));
		gridChanges.setVgap(8);
		gridChanges.setHgap(8);
		BorderPane BorderPaneChanges = new BorderPane();
		BorderPaneChanges.setCenter(gridChanges);
		
		Label 	chooseOwner, chooseProp, FixedCost, MarketValue, LocationTax, PPRCharge, UnpaidTax, 
				TotalTax, FixedCostVal, MarketValueVal, LocationTaxVal, PPRChargeVal, UnpaidTaxVal, TotalTaxVal;
		ChoiceBox<String> OwnerChoice, PropChoice;
		Button Exit, getValues;
		
		//=============ALL LABELS=============
		chooseOwner = new Label("Choose Owner");
		GridPane.setConstraints(chooseOwner, 0, 0);
		chooseProp = new Label("Choose Property");
		GridPane.setConstraints(chooseProp, 0, 1);
		FixedCost = new Label("Fixed Cost:");
		GridPane.setConstraints(FixedCost, 0, 3);
		MarketValue = new Label("Market Cost:");
		GridPane.setConstraints(MarketValue, 0, 4);
		LocationTax = new Label("Location Tax:");
		GridPane.setConstraints(LocationTax, 0, 5);
		PPRCharge = new Label("PRP Charge:");
		GridPane.setConstraints(PPRCharge, 0, 6);
		UnpaidTax = new Label("Unpaid Tax penalty:");
		GridPane.setConstraints(UnpaidTax, 0, 7);
		TotalTax = new Label("Total Tax:");
		GridPane.setConstraints(TotalTax, 0, 8);
		
		//Labels that will be input values
		FixedCostVal = new Label("€ 0000");
		GridPane.setConstraints(FixedCostVal, 1, 3);
		MarketValueVal = new Label("€ 0000");
		GridPane.setConstraints(MarketValueVal, 1, 4);
		LocationTaxVal = new Label("€ 0000");
		GridPane.setConstraints(LocationTaxVal, 1, 5);
		PPRChargeVal = new Label("€ 0000");
		GridPane.setConstraints(PPRChargeVal, 1, 6);
		UnpaidTaxVal = new Label("€ 0000");
		GridPane.setConstraints(UnpaidTaxVal, 1, 7);
		TotalTaxVal = new Label("€ 0000");
		GridPane.setConstraints(TotalTaxVal, 1, 8);
		
		
		//=============ALL CHOICEBOXES=============
		OwnerChoice = new ChoiceBox<>();
		OwnerChoice.setPrefSize(100, 20);
		OwnerChoice.getItems().addAll("Patrick Hourigan", "Andriy Kyrychenko", "Hello World");
		GridPane.setConstraints(OwnerChoice, 1, 0);
		
		PropChoice = new ChoiceBox<>();
		PropChoice.setPrefSize(100, 20);
		PropChoice.getItems().addAll(	"14 Downtown Street, Limerick, Ireland, V94 V9V9", 
										"88 Canqot Street, Sligo, Ireland, Q12 Q1Q2");
		GridPane.setConstraints(PropChoice, 1, 1);
		
		//=============ALL BUTTONS=============
		//getValuse button
		getValues = new Button("Get Values");
		getValues.setPrefSize(100, 22);
		GridPane.setConstraints(getValues, 2, 2);
		getValues.setOnAction(e-> {
			
		});
		
		//exit button
		Exit = new Button("Exit");
		Exit.setPrefSize(100, 22);
		GridPane.setConstraints(Exit, 2, 9);
		Exit.setOnAction(e-> {
			StageChanges.close();
		});
		
		gridChanges.getChildren().addAll(	chooseOwner, chooseProp, FixedCost, MarketValue, LocationTax, PPRCharge, UnpaidTax, 
											TotalTax, FixedCostVal, MarketValueVal, LocationTaxVal, PPRChargeVal, UnpaidTaxVal,
											OwnerChoice, PropChoice, Exit, getValues, TotalTaxVal);
		
		//creates new scene
		Scene ChangesScene = new Scene(BorderPaneChanges);
				
		//sets the scene and displays the scene
		StageChanges.setScene(ChangesScene);
		StageChanges.showAndWait();
	}


}
	


























