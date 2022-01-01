package view;

import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Product;

public class ViewNewProduct {
	private ArrayList<Product> allProducts;
	private TextField CatalogNumTf;
	private TextField nameTf;
	private TextField costPrTf;
	private TextField priceTf;
	private TextField customerNameTf;
	private TextField customerPhoneTf;
	private RadioButton customerAproved;
	private RadioButton customerDisAproved;
	private ToggleGroup tg1;
	private Button saveBtn;
	private Button redoBtn;

	private Stage theStage;

	private Button bt;

	public ViewNewProduct(Stage stage, ArrayList<Product> allProducts) {

		this.allProducts = allProducts;
		this.theStage = stage;
		tg1 = new ToggleGroup();

		Text txt0 = new Text("New Product");
		txt0.prefWidth(60);
		txt0.prefHeight(60);
		txt0.setFont(Font.font("Engravers MT", 25));

		CatalogNumTf = new TextField();
		CatalogNumTf.setPromptText("Catalog Number");
		CatalogNumTf.setMaxSize(170, 10);

		nameTf = new TextField();
		nameTf.setPromptText("Product Name");
		nameTf.setMaxSize(170, 10);

		costPrTf = new TextField();
		costPrTf.setPromptText("Cost Price");
		costPrTf.setMaxSize(170, 10);

		priceTf = new TextField();
		priceTf.setPromptText("Selling Price");
		priceTf.setMaxSize(170, 10);

		customerNameTf = new TextField();
		customerNameTf.setPromptText("Customer Name");
		customerNameTf.setMaxSize(170, 10);

		customerPhoneTf = new TextField();
		customerPhoneTf.setPromptText("Customer Phone Number");
		customerPhoneTf.setMaxSize(170, 10);

		Text txt = new Text("Is the customer aproves to receive messages?");
		txt.prefHeight(30);
		txt.prefWidth(30);

		customerAproved = new RadioButton("Aprove");
		customerAproved.setToggleGroup(tg1);
		customerDisAproved = new RadioButton("Not Aprove");
		customerDisAproved.setToggleGroup(tg1);

		saveBtn = new Button("Save");
		saveBtn.setPrefSize(170, 10);

		redoBtn = new Button("Redo");
		redoBtn.setPrefSize(170, 10);
		redoBtn.setDisable(true);

		HBox hb1 = new HBox(19);
		hb1.getChildren().addAll(customerAproved, customerDisAproved);
		hb1.setAlignment(Pos.CENTER);

		VBox vb1 = new VBox(10);
		vb1.getChildren().add(txt0);
		vb1.getChildren().addAll(CatalogNumTf, nameTf, costPrTf, priceTf, customerNameTf, customerPhoneTf);
		vb1.getChildren().add(txt);
		vb1.getChildren().add(hb1);
		vb1.getChildren().add(saveBtn);
		vb1.getChildren().add(redoBtn);
		vb1.setAlignment(Pos.CENTER);

		Scene scene = new Scene(vb1, 500, 500);
		stage.setScene(scene);
		stage.show();
	}

	public ToggleGroup getTg1() {
		return tg1;
	}

	public ArrayList<Product> getAllProducts() {
		return allProducts;
	}

	public void setAllProducts(ArrayList<Product> allProducts) {
		this.allProducts = allProducts;
	}

	public TextField getCatalogNumTf() {
		return CatalogNumTf;
	}

	public void setCatalogNumTf(TextField catalogNumTf) {
		CatalogNumTf = catalogNumTf;
	}

	public TextField getNameTf() {
		return nameTf;
	}

	public void setNameTf(TextField nameTf) {
		this.nameTf = nameTf;
	}

	public TextField getCostPrTf() {
		return costPrTf;
	}

	public void setCostPrTf(TextField costPrTf) {
		this.costPrTf = costPrTf;
	}

	public TextField getPriceTf() {
		return priceTf;
	}

	public void setPriceTf(TextField priceTf) {
		this.priceTf = priceTf;
	}

	public TextField getCustomerNameTf() {
		return customerNameTf;
	}

	public void setCustomerNameTf(TextField customerNameTf) {
		this.customerNameTf = customerNameTf;
	}

	public TextField getCustomerPhoneTf() {
		return customerPhoneTf;
	}

	public void setCustomerPhoneTf(TextField customerPhoneTf) {
		this.customerPhoneTf = customerPhoneTf;
	}

	public RadioButton getCustomerAproved() {
		return customerAproved;
	}

	public void setCustomerAproved(RadioButton customerAproved) {
		this.customerAproved = customerAproved;
	}

	public RadioButton getCustomerDisAproved() {
		return customerDisAproved;
	}

	public void setCustomerDisAproved(RadioButton customerDisAproved) {
		this.customerDisAproved = customerDisAproved;
	}

	public Button getSave() {
		return saveBtn;
	}

	public void setSave(Button save) {
		this.saveBtn = save;
	}

	public Button getBt() {
		return bt;
	}

	public void setBt(Button bt) {
		this.bt = bt;
	}

	public Stage getStage() {
		return theStage;
	}


	public Button getRedo() {
		return redoBtn;
	}

	public void addEventHandlerToSaveBtn(EventHandler<ActionEvent> eventToSaveBtn) {
		saveBtn.setOnAction(eventToSaveBtn);
	}

	public void addEventHandlerRedoBtn(EventHandler<ActionEvent> eventToRedoBtn) {
		redoBtn.setOnAction(eventToRedoBtn);
	}

	public void addTextFormatterToCN(TextFormatter<String> textFormatter) {
		CatalogNumTf.setTextFormatter(textFormatter);
		
	}

}
