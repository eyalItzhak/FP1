package Command;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import model.Customer;
import model.Product;
import model.Store;

public class DeleteAllProdCommand implements Command {

	private Store theModel;
	
	public DeleteAllProdCommand(Store theModel) {
		this.theModel = theModel;
	}
	
	@Override
	public void execute() {
		theModel.removeAllProduct();
	}

}
