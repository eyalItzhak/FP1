package view;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Product;

public class ViewMessage extends Thread {

	private TextField messTF;
	private Button sendBtn;
	VBox vb2;							// for the names to whom the messages will send to
	ArrayList<String> aprrovedNames;	// names of customer that approved receive messages

	public ViewMessage(Stage stage) {

		Text title = new Text("Messages");
		title.setFont(Font.font("Engravers MT", 30));
		
		sendBtn = new Button("Send");
		sendBtn.setPrefSize(170, 10);
		
		messTF = new TextField();
		messTF.setPromptText("Message");
		messTF.setPrefSize(220, 170);
		messTF.setAlignment(Pos.TOP_LEFT);

		HBox hb1 = new HBox();
		hb1.getChildren().add(title);
		hb1.setAlignment(Pos.TOP_CENTER);
		
		VBox vb1 = new VBox(12);
		vb1.getChildren().addAll(messTF,sendBtn);
		vb1.setAlignment(Pos.CENTER_LEFT);
		
		vb2 = new VBox(7);  // for the names to whom the messages will send to
		
		HBox hb2 = new HBox(120);
		hb2.getChildren().addAll(vb1,vb2);
		hb2.setPadding(new Insets(0,0,0,40));
		
		VBox vb3 = new VBox(90);
		vb3.getChildren().addAll(hb1,hb2);
		vb3.setPadding(new Insets(50,0,0,0));

		Scene scene = new Scene(vb3, 600, 600);
		stage.setScene(scene);
		stage.show();

	}

	private void addNameCustomerToPane(String TheName) {
		Label l = new Label(TheName.toString());

		Platform.runLater(() -> {
			vb2.getChildren().add(l);
		});
	}

	public void addEventHandlerToMess(EventHandler<ActionEvent> event) {
		sendBtn.setOnAction(event);
	}

	public TextField getMess() {
		return messTF;
	}

	public void setMess(TextField mess) {
		this.messTF = mess;
	}

	public Button getSend() {
		return sendBtn;
	}

	public void setSend(Button send) {
		this.sendBtn = send;
	}

	public VBox getVB2() {
		return vb2;
	}

	public void setVB2(VBox vb2) {
		this.vb2 = vb2;
	}

	@Override
	public void run() {
		
		for (int i = 0; i < aprrovedNames.size(); i++) {

			this.addNameCustomerToPane(aprrovedNames.get(i));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		this.addNameCustomerToPane("\n\nAll messages sent.");
	}

	public void RunListThreath(ArrayList<String> names2) {
		this.aprrovedNames = names2;
		
		if(aprrovedNames.size() == 0) {
			Alert a = new Alert(AlertType.INFORMATION);
			a.setHeaderText("Error");
			a.setContentText("No Customers That Approved To Receive Messages");
			a.show();
		}else {
			Text title2 = new Text("Message sent to:");
			title2.setFont(Font.font("Engravers MT", 17));
			
			vb2.getChildren().add(title2);
			
			this.start();

		}
	}

}
