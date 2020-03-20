package gui.admin;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.mt.mng.Editor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class AdminController implements Initializable {
	

    @FXML
    private Button btnPotvrd;

    @FXML
    private TextArea tstLog;

    @FXML
    private TextField txtSSID;

    @FXML
    private TextField txtPSWD;

    @FXML
    private Button btnSaveWifi;

    @FXML
    void potvrdenie(ActionEvent event) {

    }

    @FXML
    void saveWifiSettings(ActionEvent event) {

    	System.out.println("New SSID: " + txtSSID.getText());
    	System.out.println("New Password: " +txtPSWD.getText());
    	
    	tstLog.appendText("New SSID: " + txtSSID.getText()+"\n");
       	tstLog.appendText("New Password: " +txtPSWD.getText()+"\n");
       	
       	Editor editor = new Editor();
       	
       
       	try {
       		System.out.println("OLD: " + editor.getWifiSettings());
       		System.out.println("NEW: " +editor.setWifi(txtSSID.getText(), txtPSWD.getText()));
       		
       		txtPSWD.clear();
       		txtSSID.clear();
       		
       	 	tstLog.appendText(editor.rebootWifi()+"\n");
       		
			
		} catch (IOException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("Error save data!");

			alert.showAndWait();
			e.printStackTrace();
		}
       	
    }


		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			
		}


}
