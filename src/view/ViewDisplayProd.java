package view;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Customer;
import model.Product;

public class ViewDisplayProd {
	private TextField txtF1;
	private Button btnToRemoveProduct;
	private Stage theStage;
	
	public ViewDisplayProd(Stage stage,ArrayList<Product> allProducts) {

		this.theStage = stage;
		Text leftTitleTxt = new Text("All Products");
		leftTitleTxt.setFont(Font.font("Engravers MT",33));
		
		TableView tableView = new TableView();
		tableView.setMaxSize(660, 300);
	    TableColumn<Product, String> column1 = new TableColumn<>("Catalog Number");
	    column1.setCellValueFactory(new PropertyValueFactory<>("MKTid"));

	    TableColumn<Product, String> column2 = new TableColumn<>("Product Name");
	    column2.setCellValueFactory(new PropertyValueFactory<>("nameOfProduct"));
	    
	    TableColumn<Product, Integer> column3 = new TableColumn<>("Marketing Price");
	    column3.setCellValueFactory(new PropertyValueFactory<>("priceForCustomer"));

	    TableColumn<Product, Integer> column4 = new TableColumn<>("Cost Price");
	    column4.setCellValueFactory(new PropertyValueFactory<>("priceForShop"));
	    
	    TableColumn<Product, String> column5 = new TableColumn<>("Customer Name");
	    column5.setCellValueFactory(new PropertyValueFactory<>("customerName"));
	    
	    TableColumn<Product,Integer> column6 = new TableColumn<>("Profit");
	    column6.setCellValueFactory(new PropertyValueFactory<>("profit"));

	    tableView.getColumns().add(column1);
	    tableView.getColumns().add(column2);
	    tableView.getColumns().add(column3);
	    tableView.getColumns().add(column4);
	    tableView.getColumns().add(column5);
	    tableView.getColumns().add(column6);

	    tableView.getItems().addAll(allProducts);
	    
	    tableView.setPlaceholder(new Label("No products to display"));
		
	    txtF1 = new TextField();
	    txtF1.setPromptText("Catalog Number");
		txtF1.setMaxSize(300, 220);
	    
		VBox vb1 = new VBox(16);
		vb1.getChildren().add(leftTitleTxt);
		vb1.getChildren().addAll(tableView);
		vb1.getChildren().add(txtF1);
		
		btnToRemoveProduct = new Button("Remove\n     All");
		btnToRemoveProduct.setMaxSize(100, 100);
		btnToRemoveProduct.setFont(Font.font("Engravers MT",18));
		btnToRemoveProduct.setStyle("-fx-border-color: #ff0000; -fx-border-width: 5px;");
		
		VBox vb2 = new VBox();
		vb2.getChildren().add(btnToRemoveProduct);
		vb2.setAlignment(Pos.CENTER_LEFT);
		
		VBox vb3 = new VBox(300);
		vb3.getChildren().addAll(vb1,vb2);
		vb3.setAlignment(Pos.CENTER);
		vb3.setPadding(new Insets(0,0, 0, 80));
		
		Scene scene = new Scene(vb3,1000,1000);
		stage.setScene(scene);
		stage.show();
	}

	public TextField getTxtF1() {
		return txtF1;
	}
	public void addEventHandlerToTxtF(EventHandler<KeyEvent> event) {
		txtF1.setOnKeyPressed(event);
	}
	
	public void addEventHandlerToRemoveAllProduct(EventHandler<ActionEvent> event) {
		btnToRemoveProduct.setOnAction(event);
	}

	public Stage getStage() {
		return theStage;
	}
	
	
}
