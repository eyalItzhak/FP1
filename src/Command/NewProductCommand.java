package Command;

import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.Region;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import model.Customer;
import model.Product;
import model.Store;
import view.ViewNewProduct;

public class NewProductCommand implements Command{
	
	private Store theModel;
	private ViewNewProduct theView;
	
	public NewProductCommand(Store theModel,ViewNewProduct theView) {
		this.theModel = theModel;
		this.theView = theView;
	}
	
	@Override
	public void execute() {
		theModel.setLastSave(theModel.saveStateToMemento());
		int priceOfCustomer;
		int priceOfShope;
		String ProductName;
		String mktId;
		String aprove; 
		
		try {
			
		if (theView.getCatalogNumTf().getText().equals("")) {
			theView.getRedo().setDisable(true);
			throw new Exception();
		}else {
			mktId=theView.getCatalogNumTf().getText();
		}
		if (theView.getPriceTf().getText().equals("")) {
			priceOfCustomer = 0;
		}else {
			priceOfCustomer=Integer.parseInt(theView.getPriceTf().getText());
		}
		
		if (theView.getCostPrTf().getText().equals("")) {
			priceOfShope = 0;
		}else {
			priceOfShope=Integer.parseInt(theView.getCostPrTf().getText())  ;
		}

		ProductName = theView.getNameTf().getText();
		String customerName=theView.getCustomerNameTf().getText();
		String Phone=theView.getCustomerPhoneTf().getText();
		
		if((customerName != null && !isAlpha(customerName)) || (Phone != null && !onlyDigits(Phone)) ||
				!((priceOfCustomer>=0)&&(priceOfShope>=0))) {
			theView.getRedo().setDisable(true);
			throw new Exception();
		}
		
		RadioButton rb = (RadioButton)theView.getTg1().getSelectedToggle();
		if (rb == null) {
			aprove = "Not Aprove";
		}else {
			aprove= rb.getText();
		}
		
		Customer c=new Customer(customerName, Phone, aprove);
		
		Product p=new Product(ProductName, mktId, priceOfCustomer, priceOfShope, c);
		theModel.inserProduct(p);
		theModel.SaveFile();
		
		Alert a = new Alert(AlertType.INFORMATION);
		a.setHeaderText("Confirmation");
		a.setContentText("Product Saved");
		a.show();
		
		theView.getRedo().setDisable(false);
		} 
		
		
		
		catch(Exception e) {
			Alert a = new Alert(AlertType.INFORMATION, "You entered a worng value,\nPlease check that Catalog Number is not empty.\n\n"
					+ "If you enterd the following details,\n"
					+ "kindly notice that:\n\n"
					+ "1) Phone number only digits\n"
					+ "2) Customer name only letters\n"
					+ "3) Price only digits and Non-negative number\n", ButtonType.OK);
			
			a.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
			a.show();
		}
	}
	
	public boolean isAlpha(String name) {
	    char[] chars = name.toCharArray();

	    for (char c : chars) {
	        if(!Character.isLetter(c)) {
	            return false;
	        }
	    }

	    return true;
	} 
	
	public static boolean onlyDigits(String str) 
    { 

        for (int i = 0; i < str.length(); i++) { 
  
            if (!(str.charAt(i) >= '0' && str.charAt(i) <= '9')) { 
                return false; 
            } 

        } 
        return true; 
    } 
}
