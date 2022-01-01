import controller.ControllerMenu;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Customer;
import model.Product;
import model.Store;
import view.ViewMenu;

public class Program extends Application{
	ArrayList<Product> allProducts;
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {
		
		Store m = Store.getInstance();
		ViewMenu v = new ViewMenu(stage);
		ControllerMenu c = new ControllerMenu(m,v);
	
	}

}
