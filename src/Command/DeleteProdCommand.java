package Command;

import model.Product;
import model.Store;

public class DeleteProdCommand implements Command{
	
	private Store theModel;
	private Product product;
	
	public DeleteProdCommand(Store theModel,Product product) {
		this.theModel = theModel;
		this.product = product;
	}
	@Override
	public void execute() {
		theModel.removeProduct(product);
		
	}

}
