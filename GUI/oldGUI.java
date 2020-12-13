
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainGUI extends Application {
	Stage window = new Stage();
	Scene scene;
	Button button;
	String manager = "manager";
	String pw = "manager";
	String user = "user";
	String pw2 = "user";
	
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(8);
		
		//login text
		Label username = new Label("Username:");
		GridPane.setConstraints(username, 0, 0);
		
		//Login field
		TextField login = new TextField();
		login.setPromptText("username");
		GridPane.setConstraints(login, 1, 0);

		//Password text
		Label pass = new Label("Password:");
		GridPane.setConstraints(pass, 0, 1);
		
		//Password Field
		PasswordField password = new PasswordField();
		password.setPromptText("password");
		GridPane.setConstraints(password, 1, 1);
		
		// Log In button
		Button logIn = new Button();
		logIn.setText("Log In");
		GridPane.setConstraints(logIn, 1, 2);
		
		//Login outcome
		Label status = new Label();
		status.setText("");
		GridPane.setConstraints(status, 1, 4);
		
		//LOGO Text
		Label logo = new Label("IrishPropertyTax");
		logo.setTextFill(Color.BLUE);
		logo.setFont(new Font("Cascadia Code SemiBold", 45));
		
		//HBox for logo
		HBox hbox = new HBox();
		hbox.getChildren().add(logo);
		hbox.setAlignment(Pos.CENTER);
		
		//Login scene
		grid.getChildren().addAll(username, login, pass, password, logIn, status);
		grid.setAlignment(Pos.CENTER);
		
		BorderPane bord = new BorderPane();
		bord.setCenter(grid);
		bord.setTop(hbox);
		
		Scene scene = new Scene(bord, 500, 450, Color.LIGHTBLUE);
		window.setScene(scene);
		window.show();
		window.setTitle("Tax Calculator");
		
		
		
		Button LogOut = new Button();
		
		
		logIn.setOnAction(e -> {
			String userNAME = login.getText().toString();
			String passWORD = password.getText().toString();

			if(userNAME.equals(manager) && passWORD.equals(pw)) {
				status.setText("Welcome Home Manager");
			} else if (userNAME.equals(user) && passWORD.equals(pw2)) {
				status.setText("Welcome Home user");
			} else {
				status.setText("Login/Password is invalid");
			} 
		}); 
		//LogOut action	
			LogOut.setOnAction(e ->{
				window.setScene(scene);
			});
		
		
	}
  }
