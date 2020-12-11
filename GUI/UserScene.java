package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class UserScene extends Application {
	Stage window = new Stage();
	
	private Button RegisterProperty, PayTax, View, LogOut;
	private Label Logo;
	private Scene UserScene;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(15);
		grid.setHgap(8);
		
		RegisterProperty = new Button("Register Property");
		RegisterProperty.setPrefSize(120, 25);
		GridPane.setConstraints(RegisterProperty, 0, 0);
		PayTax = new Button("Pay Tax");
		PayTax.setPrefSize(120, 25);
		GridPane.setConstraints(PayTax, 0, 1);
		View = new Button("View");
		View.setPrefSize(120, 25);
		GridPane.setConstraints(View, 0, 2);
		LogOut = new Button("Log Out");
		LogOut.setPrefSize(120, 25);
		GridPane.setConstraints(LogOut, 0, 3);
		
		
		
		grid.getChildren().addAll(RegisterProperty, PayTax, View, LogOut);
		grid.setAlignment(Pos.CENTER);
		
		
		
		
		
		
		
		
		//LOGO Text
		Label logo = new Label("IrishPropertyTax");
		logo.setTextFill(Color.BLUE);
		logo.setFont(new Font("Cascadia Code SemiBold", 45));
				
		//HBox for logo
		HBox hbox = new HBox();
		hbox.getChildren().add(logo);
		hbox.setAlignment(Pos.CENTER);
		

		BorderPane border = new BorderPane();
		border.setTop(hbox);
		border.setCenter(grid);
		
		UserScene = new Scene(border, 500, 450);
		UserScene.setFill(Color.LIGHTBLUE);
		window.setScene(UserScene);
		window.setTitle("IrishPropertyTax");
		window.show();
		

		
	}
	

}







































































