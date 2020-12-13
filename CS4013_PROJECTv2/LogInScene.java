package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

public class LogInScene {
	
	private Stage window = new Stage();
	private TextField login;
	private PasswordField password;
	private Label username, pass, status, logo;
	private Scene scene;
	private Button logIn, Register;
	private String path = new String("C:\\Users\\Andreu\\eclipse-workspace\\SceneBuilder\\src\\application\\login+password.csv");
	private File file = new File("C:\\Users\\Andreu\\eclipse-workspace\\SceneBuilder\\src\\application\\login+password.csv");
	private static String line;
	private String User;
	private String Pass;
	private String UserPass;
	private ArrayList<String> list = new ArrayList<String>();
	
	
	public LogInScene() {
		LogInScene();
	}

	public void LogInScene() {
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(8);
		
		//login text
		username = new Label("Username:");
		GridPane.setConstraints(username, 0, 0);
		
		//Login TEXT field
		login = new TextField();
		login.setPromptText("username");
		GridPane.setConstraints(login, 1, 0);

		//Password text
		pass = new Label("Password:");
		GridPane.setConstraints(pass, 0, 1);
		
		//Password Field
		password = new PasswordField();
		password.setPromptText("password");
		GridPane.setConstraints(password, 1, 1);
		
		// Log In button
		logIn = new Button();
		logIn.setText("Log In");
		GridPane.setConstraints(logIn, 1, 2);
		
		// Register button
		Register = new Button();
		Register.setText("Register");
		GridPane.setConstraints(Register, 0, 2);
		
		//Login outcome
		status = new Label();
		status.setText("");
		GridPane.setConstraints(status, 1, 4);
		
		//LOGO Text
		logo = new Label("IrishPropertyTax");
		logo.setTextFill(Color.BLUE);
		logo.setFont(new Font("Cascadia Code SemiBold", 45));
		
		//HBox for logo
		HBox hbox = new HBox();
		hbox.getChildren().add(logo);
		hbox.setAlignment(Pos.CENTER);
		
		//Login scene
		grid.getChildren().addAll(username, login, pass, password, logIn, status, Register);
		grid.setAlignment(Pos.CENTER);
		
		BorderPane bord = new BorderPane();
		bord.setCenter(grid);
		bord.setTop(hbox);
		
		scene = new Scene(bord, 500, 450, Color.LIGHTBLUE);
		window.setScene(scene);
		window.show();
		window.setTitle("IrishPropertyTax");
		
		Register.setOnAction(e ->{
		});
		
		logIn.setOnAction(e -> {
			UserPass = (login.getText().toString() + "," + password.getText().toString());
				if(UserPass.equals("user,user")) {
					new UserScene();
					window.close();
				} else if (UserPass.equals("admin,admin")) {
					new AdminScene();
					window.close();
				} else {
					status.setText("Login/Password is invalid");
					System.out.println(UserPass);
				} 
		});
	}	
}