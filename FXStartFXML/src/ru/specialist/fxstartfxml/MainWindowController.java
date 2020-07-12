package ru.specialist.fxstartfxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
// import javafx.scene.paint.Paint;

public class MainWindowController {

    @FXML
    private Label labelHello;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtEmail;

    @FXML
    void onHelloButton(ActionEvent event) {
    	String userName = txtName.getText().trim();
    	String hello = (userName.isEmpty()) ? "������!" : String.format("������, %s!", userName);
    	labelHello.setText(hello);
    	
    }
    
    @FXML
    void onRegister(ActionEvent event) {
    	String err = "";
    	
    	if (txtName.getText().isEmpty()) err += "��� �� �������. ";
    	if (txtEmail.getText().isEmpty()) err += "Email �� ������. ";
    	
    	if (!err.isEmpty()) {
    		labelHello.setText(err);
    		//labelHello.setTextFill(Paint.valueOf("red"));
    		labelHello.setTextFill(Color.RED);
    	}
    	else {
    		labelHello.setText("�� ����������������");
    		labelHello.setTextFill(Color.BLACK);
    	}
    		
    		
    }
    @FXML
    void onAgeEdit(KeyEvent event) {
    	System.out.println(event.getCode());
    	// event.consume();
    }

}
