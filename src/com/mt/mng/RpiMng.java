package com.mt.mng;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RpiMng extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("GUI Starting .........");
		System.out.println("FXML RES: -->" + getClass().getResource("/gui/admin/Admin.fxml"));
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/admin/Admin.fxml"));
		Parent root = loader.load();

		
		Scene scene = new Scene(root);
		primaryStage.resizableProperty().setValue(Boolean.FALSE);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	
	
	
}
