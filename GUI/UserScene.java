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

public class UserScene {
	private Stage window = new Stage();
	private Button RegisterProperty, PayTax, View, LogOut;
	private Label Logo;
	private Scene UserScene;
	
	public UserScene(Stage stage) {
		UserScene();
	}
	
	private void UserScene() {
		//Set up the grid pane
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(15);
		grid.setHgap(8);
		
		//Setting UP BUTTONS
		//Register Property button
		RegisterProperty = new Button("Register Property");
		RegisterProperty.setPrefSize(120, 25);
		GridPane.setConstraints(RegisterProperty, 0, 0);
		
		//PayTax button
		PayTax = new Button("Pay Tax");
		PayTax.setPrefSize(120, 25);
		GridPane.setConstraints(PayTax, 0, 1);
		
		//View button
		View = new Button("View");
		View.setPrefSize(120, 25);
		GridPane.setConstraints(View, 0, 2);
		
		//LogOut button
		LogOut = new Button("Log Out");
		LogOut.setPrefSize(120, 25);
		GridPane.setConstraints(LogOut, 0, 4);
		
		
		//adding elements go GridPane
		grid.getChildren().addAll(RegisterProperty, PayTax, View, LogOut);
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
		UserScene = new Scene(border, 500, 450);
		UserScene.setFill(Color.LIGHTBLUE);
		window.setScene(UserScene);
		window.setTitle("IrishPropertyTax");
		window.show();
		
		//LogOut action	
		LogOut.setOnAction(e -> {
			new LogInScene(window);
		//	window.close();
		});
		
		//RegisterProperty button action
		RegisterProperty.setOnAction(e -> {
			RegisterProperty();
		});
		
		PayTax.setOnAction(e -> {
			PayTax();
		});
		
		View.setOnAction(e -> {
			View();
		});
	}
	
	//Window that pops up after pressing "RegisterProperty" button
	public void RegisterProperty() {
		//Setting up the stage
		Stage RegisterProperty = new Stage();
		RegisterProperty.initModality(Modality.APPLICATION_MODAL);
		RegisterProperty.setTitle("Property Registration");
		RegisterProperty.setMinWidth(340);
		RegisterProperty.setMinHeight(310);
		
		//creates layout
		GridPane registerLayout = new GridPane();
		registerLayout.setPadding(new Insets(10, 10, 10, 10));
		registerLayout.setVgap(8);
		registerLayout.setHgap(8);
		BorderPane regmainLay = new BorderPane();
		regmainLay.setCenter(registerLayout);
		
		//ALL THE Labels 
		Label address, postcode, marketValue, location, principal, DofLastTaxPayment, status;
		address = new Label("Address");
		GridPane.setConstraints(address, 0, 0);
		postcode = new Label("Postcode");
		GridPane.setConstraints(postcode, 0, 1);
		marketValue = new Label("MarketValue");
		GridPane.setConstraints(marketValue, 0, 2);
		location = new Label("Location");
		GridPane.setConstraints(location, 0, 3);
		principal = new Label("Principal");
		GridPane.setConstraints(principal, 0, 4);
		DofLastTaxPayment = new Label("Date of last tax Payment");
		GridPane.setConstraints(DofLastTaxPayment, 0, 5);
		status = new Label("");
		GridPane.setConstraints(status, 0, 7);
		
		//ALL THE TEXTFIELDS
		//ADDRESS TEXTFIELD
		TextField Taddress, Tpostcode, TmarketValue, Tprincipal, TDofLastTaxPayment;
		Taddress = new TextField();
		Taddress.setPromptText("Address");
		GridPane.setConstraints(Taddress, 1, 0);
		//POSTCODE TEXTFIELD
		Tpostcode = new TextField();
		Tpostcode.setPromptText("Postcode e.g A65 F4E2");
		GridPane.setConstraints(Tpostcode, 1, 1);
		//MARKETVALUE TEXTFIELD
		TmarketValue = new TextField();
		TmarketValue.setPromptText("Market Value");
		GridPane.setConstraints(TmarketValue, 1, 2);
		//LOCATION TEXTFIELD
		ChoiceBox<String> Tlocation = new ChoiceBox<>();
		Tlocation.setPrefSize(150, 20);
		Tlocation.getItems().addAll("City", "Large Town", "Small Town", "Village", "Country Side");
		GridPane.setConstraints(Tlocation, 1, 3);
		//PRINCIPAL TEXTFIELD
		Tprincipal = new TextField();
		Tprincipal.setPromptText("Y/N e.g. Y - Yes");
		GridPane.setConstraints(Tprincipal, 1, 4);
		//LAST TAX PAYMENT TEXT FIELD
		TDofLastTaxPayment = new TextField();
		TDofLastTaxPayment.setPromptText("YYYY-MM-DD");
		GridPane.setConstraints(TDofLastTaxPayment, 1, 5);

		//creates needed buttons
		Button closebutton = new Button("Exit");
		closebutton.setPrefSize(150, 22);
		GridPane.setConstraints(closebutton, 1, 6);
		closebutton.setOnAction(e -> RegisterProperty.close());
		
		Button addproperty = new Button("Add Property");
		addproperty.setPrefSize(150, 22);
		GridPane.setConstraints(addproperty, 0, 6);
		addproperty.setOnAction(e-> {
			status.setText("Successfully Added");
			System.out.println("property successfully added");
		});

		registerLayout.getChildren().addAll(address, postcode, marketValue, location, principal, DofLastTaxPayment,
											Taddress, Tpostcode, TmarketValue, Tlocation, Tprincipal, TDofLastTaxPayment,
											closebutton, addproperty, status);
		
		//creates new scene
		Scene RegisterPropertyscene = new Scene(regmainLay);
		
		//sets the scene and displays the scene
		RegisterProperty.setScene(RegisterPropertyscene);
		RegisterProperty.showAndWait();
	}
	
	//Window that pops up after pressing "Pay Tax" button
	public void PayTax() {
		//Setting up the stage
		Stage PayTaxes = new Stage();
		PayTaxes.initModality(Modality.APPLICATION_MODAL);
		PayTaxes.setTitle("Pay Tax");
		PayTaxes.setMinWidth(400);
		PayTaxes.setMinHeight(300);
				
		//creates layout
		GridPane Paytaxes = new GridPane();
		Paytaxes.setPadding(new Insets(10, 10, 10, 10));
		Paytaxes.setVgap(8);
		Paytaxes.setHgap(8);
		BorderPane BorderPaneMain = new BorderPane();
		BorderPaneMain.setCenter(Paytaxes);		
			
		//Labels, ChoiceBoxes, Buttons
		Label 	checkLabel, selectProp, selectPropStatus, selectTax, selectTaxStatus, DueTax, OverdueTax, TotalTax, DueTaxAmount,
				OverdueTaxAmount, TotalTaxAmount;
		ChoiceBox<String> ListOfProperty, ListOfTaxes;
		Button SelectProperty, GetTaxInfo, SelectTax, PayTax, Exit;
		
		//===========ALL THE LABELS===============
		
		//Check Label
		checkLabel = new Label(""); // IF statements will check if there is property
		GridPane.setConstraints(checkLabel, 0, 0);
		
		//SelectProperty label + selectPropertyStatus
		selectProp = new Label("Select the Property:");
		GridPane.setConstraints(selectProp, 0, 1);
		selectPropStatus = new Label("");
		GridPane.setConstraints(selectPropStatus, 1, 2);
		
		//SelectTax label + SelectTaxStatus
		selectTax = new Label("Select the Tax:");
		GridPane.setConstraints(selectTax, 0, 6);
		selectTaxStatus = new Label("");
		GridPane.setConstraints(selectTaxStatus, 1, 7);
		
		//=============Due OverDue and Total TAXES=============
		DueTax = new Label("Due Tax:");
		GridPane.setConstraints(DueTax, 0, 3);
		OverdueTax = new Label("Overdue Tax:");
		GridPane.setConstraints(OverdueTax, 0, 4);
		TotalTax = new Label("Total Tax:");
		GridPane.setConstraints(TotalTax, 0, 5);
		
		//============DueTaxAmount, OverdueTaxAmount, TotalTaxAmount==================
		DueTaxAmount = new Label("00000000"); // WILL NEED PROPER INPUT
		GridPane.setConstraints(DueTaxAmount, 1, 3);
		OverdueTaxAmount = new Label("00000000"); // WILL NEED PROPER INPUT
		GridPane.setConstraints(OverdueTaxAmount, 1, 4);
		TotalTaxAmount = new Label("00000000"); // WILL NEED PROPER INPUT
		GridPane.setConstraints(TotalTaxAmount, 1, 5);
		
		//============CHOICE BOXES: LIST_OF_PROPERTY + LIST_OF_TAXES======================		
		ListOfProperty = new ChoiceBox<>();
		ListOfProperty.setPrefSize(175, 22);
		ListOfProperty.getItems().addAll("A. 420 Richmond Court, Limerick V94 K4K8", "B. 10 O'Connell Street, Limerick V92 A8W2");
		GridPane.setConstraints(ListOfProperty, 1, 1);
		ListOfTaxes = new ChoiceBox<>();
		ListOfTaxes.setPrefSize(175, 22);
		ListOfTaxes.getItems().addAll("A. Due Tax", "B. Overdue Tax","C. Total Tax");
		GridPane.setConstraints(ListOfTaxes, 1, 6);
		
		//============ALL THE BUTTONS===============
		//SELECT PROPERTY BUTTON
		SelectProperty = new Button("Select Property");
		SelectProperty.setPrefSize(100, 22);
		GridPane.setConstraints(SelectProperty, 2, 1);
		
		//GET TAX INFO BUTTON
		GetTaxInfo = new Button("Get Tax Info");
		GetTaxInfo.setPrefSize(100, 22);
		GridPane.setConstraints(GetTaxInfo, 2, 2);
		
		//SELECT TAX BUTTON
		SelectTax = new Button("Select Tax");
		SelectTax.setPrefSize(100, 22);
		GridPane.setConstraints(SelectTax, 2, 6);
		
		//Pay TAX BUTTON
		PayTax = new Button("Pay Tax");
		PayTax.setPrefSize(100, 22);
		GridPane.setConstraints(PayTax, 2, 7);
		
		// EXIT BUTTON
		Exit = new Button("Exit");
		Exit.setPrefSize(100, 22);
		GridPane.setConstraints(Exit, 2, 10);
		
		//=================BUTTON ACTIONS=====================
		//EXIT
		Exit.setOnAction(e -> PayTaxes.close());
		
		//SELECTPROPERTY
		SelectProperty.setOnAction(e-> {
			String proptype = ListOfProperty.getValue();
			selectPropStatus.setText("Property " + proptype.charAt(0) + ". Selected");
		});
		
		SelectTax.setOnAction(e -> {
			String taxtype = ListOfTaxes.getValue();
			selectTaxStatus.setText("'" + taxtype + "' is Selected");
		});
		
		
		//ADDING ALL ELEMENTS ON THE PANE
		Paytaxes.getChildren().addAll(	checkLabel, selectProp, ListOfProperty, SelectProperty, DueTax, OverdueTax,
										TotalTax, DueTaxAmount, OverdueTaxAmount, TotalTaxAmount, GetTaxInfo, 
										ListOfTaxes, selectTax, SelectTax, PayTax, Exit, selectPropStatus, selectTaxStatus);
		
		//creates new scene
		Scene PayTaxScene = new Scene(BorderPaneMain);
		
		//sets the scene and displays the scene
		PayTaxes.setScene(PayTaxScene);
		PayTaxes.showAndWait();
	}
	
	//Window that pops up after pressing "View" button
	public void View() {
		Stage View = new Stage();
		View.initModality(Modality.APPLICATION_MODAL);
		View.setTitle("View Property");
		View.setMinWidth(375);
		View.setMinHeight(400);
		
		//creates layout
		GridPane ViewInfo = new GridPane();
		ViewInfo.setPadding(new Insets(10, 10, 10, 10));
		ViewInfo.setVgap(8);
		ViewInfo.setHgap(8);
		BorderPane BorderPaneView = new BorderPane();
		BorderPaneView.setCenter(ViewInfo);		
					
		//Labels, ChoiceBoxes, Buttons
		Label viewOption, existsStatus, viewOptionStatus, selectProperty, selectPropertyStatus, 
				address, postcode, marketValue, location, principal, DofLastTaxPayment, addressVal,
				postcodeVal, marketValueVal, locationVal, principalVal, DofLastTaxPaymentVal,
				DueTax, OverdueTax, DueTaxAmount, TotalTax, OverdueTaxAmount, TotalTaxAmount;
		ChoiceBox<String> ViewChoice, PropertyList;
		Button selectView, SelectProperty, Exit, GetInfo; 
		
		//============ALL LABELS============
		//existsStatus LABEL
		existsStatus = new Label("");
		GridPane.setConstraints(existsStatus, 0, 0);
		
		viewOption = new Label("View Option:");
		GridPane.setConstraints(viewOption, 0, 1);
		viewOptionStatus = new Label("");
		GridPane.setConstraints(viewOptionStatus, 1, 2);
		selectProperty = new Label("Select Property:");
		GridPane.setConstraints(selectProperty, 0, 3);
		selectPropertyStatus = new Label("");
		GridPane.setConstraints(selectPropertyStatus, 1, 4);
		
		//LABELS OF THE HEADINGS
		address = new Label("Address:");
		GridPane.setConstraints(address, 0, 5);
		postcode = new Label("Postcode:");
		GridPane.setConstraints(postcode, 0, 6);
		marketValue = new Label("Market Value:");
		GridPane.setConstraints(marketValue, 0, 7);
		location = new Label("Location:");
		GridPane.setConstraints(location, 0, 8);
		principal = new Label("Principal:");
		GridPane.setConstraints(principal, 0, 9);
		DofLastTaxPayment = new Label("Last Tax Payment:");
		GridPane.setConstraints(DofLastTaxPayment, 0, 10);
		
		//LABELS THAT ARE USED TO INPUT VALUE/NAME
		addressVal = new Label("___________________________________");		// ADDRESS
		GridPane.setConstraints(addressVal, 1, 5);
		postcodeVal = new Label("___________________________________");		//POSTCODE
		GridPane.setConstraints(postcodeVal, 1, 6);
		marketValueVal = new Label("___________________________________");	//MARKETVALUE
		GridPane.setConstraints(marketValueVal, 1, 7);
		locationVal = new Label("___________________________________");		//LOCATION
		GridPane.setConstraints(locationVal, 1, 8);
		principalVal = new Label("___________________________________");		//PRINCIPAL
		GridPane.setConstraints(principalVal, 1, 9);
		DofLastTaxPaymentVal = new Label("___________________________________");	//DATE OF LAST TAX PAYMENT
		GridPane.setConstraints(DofLastTaxPaymentVal, 1, 10);
		
		//TAX LABELS
		DueTax = new Label("Due Tax:");
		GridPane.setConstraints(DueTax, 0, 11);
		OverdueTax = new Label("Overdue Tax:");
		GridPane.setConstraints(OverdueTax, 0, 12);
		TotalTax = new Label("Total Tax:");
		GridPane.setConstraints(TotalTax, 0, 13);
		//LABELS THAT ARE USED TO INPUT VALUE
		DueTaxAmount = new Label("___________________________________");
		GridPane.setConstraints(DueTaxAmount, 1, 11);
		OverdueTaxAmount = new Label("___________________________________");
		GridPane.setConstraints(OverdueTaxAmount, 1, 12);
		TotalTaxAmount = new Label("___________________________________");
		GridPane.setConstraints(TotalTaxAmount, 1, 13);
		
		//=============CHOICE BOXES=============
		//VIEWCHOICE CHOICEBOX
		ViewChoice = new ChoiceBox<>();
		ViewChoice.setPrefSize(175, 22);
		ViewChoice.getItems().addAll("A. By Date","B. ALL");
		GridPane.setConstraints(ViewChoice, 1, 1);
		
		//PROPERTYLIST CHOICEBOX
		PropertyList = new ChoiceBox<>();
		PropertyList.setPrefSize(175, 22);
		PropertyList.getItems().addAll("A. 420 Richmond Court, Limerick, V94 K4K4", 
										"B. 14 Sexton Street, Limerick V92 Q1Q2");
		GridPane.setConstraints(PropertyList, 1, 3);
		
		//============ALL BUTTONS==============
		selectView = new Button("Select View");
		selectView.setPrefSize(100, 22);
		GridPane.setConstraints(selectView, 2, 1);
		selectView.setOnAction(e -> {
			String opt = ViewChoice.getValue();
			viewOptionStatus.setText(" Option '" + opt.charAt(0) + ".' Selected");
		});
		
		SelectProperty = new Button("Select Property");
		SelectProperty.setPrefSize(100, 22);
		GridPane.setConstraints(SelectProperty, 2, 3);
		SelectProperty.setOnAction(e-> {
			String prop = PropertyList.getValue();
			selectPropertyStatus.setText(" Property '" + prop.charAt(0) + ".'  Selected");
		});
		
		GetInfo = new Button("Get Details");
		GetInfo.setPrefSize(100, 22);
		GridPane.setConstraints(GetInfo, 2, 4);
		
		Exit = new Button("Exit");
		Exit.setPrefSize(100, 22);
		GridPane.setConstraints(Exit, 2, 14);
		Exit.setOnAction(e -> View.close());
		
		ViewInfo.getChildren().addAll(viewOption, existsStatus, viewOptionStatus, selectProperty, selectPropertyStatus,
										address, postcode, marketValue, location, principal, DofLastTaxPayment, ViewChoice,
										PropertyList, selectView, SelectProperty, Exit, addressVal,
										postcodeVal, marketValueVal, locationVal, principalVal, DofLastTaxPaymentVal,
										DueTax, OverdueTax, DueTaxAmount, TotalTax, OverdueTaxAmount, TotalTaxAmount, GetInfo);
		//creates new scene
		Scene RegisterPropertyscene = new Scene(BorderPaneView);
		
		//sets the scene and displays the scene
		View.setScene(RegisterPropertyscene);
		View.showAndWait();
	}
}




















