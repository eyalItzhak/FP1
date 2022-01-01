package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Customer;
import model.Product;
import model.Store;

public class ViewProductByCN {
	
	private VBox Vb;
	private Product product;
	private Button removeProdBtn;
	private Stage theStage;


	public ViewProductByCN(Stage stage, Store theShop, String toFind) {

		this.product = theShop.findProdByCN(toFind);
		this.theStage = stage;
		
		if (product != null) {
			TableView tableView = new TableView();
			tableView.setMaxSize(590, 68);
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

			tableView.getColumns().add(column1);
			tableView.getColumns().add(column2);
			tableView.getColumns().add(column3);
			tableView.getColumns().add(column4);
			tableView.getColumns().add(column5);

			tableView.getItems().add(product);
			
			removeProdBtn = new Button("Remove");
			removeProdBtn.setPrefSize(100, 50);
			removeProdBtn.setFont(new Font(17));
			removeProdBtn.setAlignment(Pos.BOTTOM_RIGHT);
			removeProdBtn.setStyle("-fx-border-color: #ff0000; -fx-border-width: 5px;");

			Vb = new VBox(10);
			Vb.getChildren().addAll(tableView);
			Vb.getChildren().addAll(removeProdBtn);
			Vb.setAlignment(Pos.CENTER);

		} else {
			
			Alert a = new Alert(AlertType.INFORMATION);
			a.setHeaderText("Error");
			a.setContentText("Product Not Found");
			a.show();
		}

		Scene scene = new Scene(Vb, 600, 300);
		stage.setScene(scene);
		stage.show();
	}

	public void addEventHandlerToBtnToRemove(EventHandler<ActionEvent> event) {
		removeProdBtn.setOnAction(event);
	}

	public Product getP() {
		return product;
	}

	public void setP(Product p) {
		this.product = p;
	}

	public Stage getStage() {
		return theStage;
	}

}
