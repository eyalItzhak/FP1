package view;

import java.awt.Color;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ViewMenu {
	private Button btnNewProd;
	private Button btnDisplayAllProd;
	private Button btnSendMassege;
	private Button btnExit;
	private RadioButton ascending;
	private RadioButton descending;
	private RadioButton insert;
	private ToggleGroup tg1;
	private String orderedBy;
	
	private Stage theStage;
	
	public ViewMenu(Stage stage) {
		theStage=stage;
		Text title = new Text("All_In_One");
		title.setFont(Font.font("castellar", 55));
		
		btnNewProd = new Button("Add New\n Procuct");
		btnNewProd.setPrefSize(170, 100);
		btnNewProd.setFont(new Font(20));
		btnNewProd.setAlignment(Pos.CENTER);
		btnNewProd.setDisable(true);

		btnDisplayAllProd = new Button(" Display\nProducts");
		btnDisplayAllProd.setPrefSize(170, 100);
		btnDisplayAllProd.setFont(new Font(20));
		btnDisplayAllProd.setAlignment(Pos.CENTER);
		btnDisplayAllProd.setDisable(true);

		btnSendMassege = new Button("   Send\nMassege");
		btnSendMassege.setPrefSize(170, 100);
		btnSendMassege.setFont(new Font(20));
		btnSendMassege.setAlignment(Pos.CENTER);
		btnSendMassege.setDisable(true);

		btnExit = new Button("Exit");
		btnExit.setPrefSize(170, 100);
		btnExit.setFont(new Font(20));
		btnExit.setAlignment(Pos.CENTER);
		btnExit.setStyle("-fx-border-color: #ff0000; -fx-border-width: 5px;");

		ascending = new RadioButton("Ascending Order");
		ascending.setPrefSize(170, 100);
		ascending.setToggleGroup(tg1);

		descending = new RadioButton("Descending Order");
		descending.setPrefSize(170, 100);
		descending.setToggleGroup(tg1);

		insert = new RadioButton("Insert Order");
		insert.setPrefSize(170, 100);
		insert.setToggleGroup(tg1);

		ascending.setOnAction(e -> {

			orderedBy = "ascending";
			ascending.setDisable(true);
			descending.setDisable(true);
			insert.setDisable(true);

			btnNewProd.setDisable(false);
			btnDisplayAllProd.setDisable(false);
			btnSendMassege.setDisable(false);
		});

		descending.setOnAction(e -> {

			orderedBy = "descending";
			ascending.setDisable(true);
			descending.setDisable(true);
			insert.setDisable(true);

			btnNewProd.setDisable(false);
			btnDisplayAllProd.setDisable(false);
			btnSendMassege.setDisable(false);

		});

		insert.setOnAction(e -> {

			orderedBy = "insert";
			ascending.setDisable(true);
			descending.setDisable(true);
			insert.setDisable(true);

			btnNewProd.setDisable(false);
			btnDisplayAllProd.setDisable(false);
			btnSendMassege.setDisable(false);

		});
		
		
		
		

		HBox hb1 = new HBox();
		hb1.getChildren().addAll(title);
		hb1.setAlignment(Pos.CENTER);

		HBox hb2 = new HBox(17);
		hb2.getChildren().addAll(btnNewProd, btnDisplayAllProd/*, btnDeleteProd*/);
		hb2.setAlignment(Pos.CENTER);

		HBox hb3 = new HBox(27);
		hb3.getChildren().addAll(btnSendMassege/*, btnComffCost*/);
		hb3.setAlignment(Pos.CENTER);

		VBox vb1 = new VBox();
		vb1.getChildren().addAll(ascending, descending, insert);

		VBox vb2 = new VBox(10);
		vb2.getChildren().addAll(hb2, hb3);

		HBox hb4 = new HBox(130);
		hb4.getChildren().addAll(vb1, vb2);
		hb4.setAlignment(Pos.CENTER);

		HBox hb5 = new HBox();
		hb5.getChildren().addAll(btnExit);
		hb5.setAlignment(Pos.CENTER);

		VBox vb = new VBox(70);
		vb.getChildren().addAll(hb1, hb4, hb5);
		vb.setAlignment(Pos.CENTER);

		Scene scene = new Scene(vb, 1000, 1000);
		stage.setScene(scene);
		stage.show();
	}
	
		public String GetOrderedBy() {
		return this.orderedBy;
	}

	
	
	public  void closeStage() {
		this.theStage.close();
	}
	
	public void addEventHandlerToBtnNewProd(EventHandler<ActionEvent> event) {
		btnNewProd.setOnAction(event);
	}
	
	public void addEventHandlerToBtnDisplayAllProd(EventHandler<ActionEvent> event) {
		btnDisplayAllProd.setOnAction(event);
	}
	

	
	public void addEventHandlerToExit(EventHandler<ActionEvent> event) {
		btnExit.setOnAction(event);
	}
	
	public void addEventHandlerToBtnDisplayMessage(EventHandler<ActionEvent> event) {
		btnSendMassege.setOnAction(event);
	}
	
}
