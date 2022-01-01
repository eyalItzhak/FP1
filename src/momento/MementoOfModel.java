package momento;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import model.Customer;
import model.Product;
import model.Store;
import model.byAlfphB;
import model.byBetaAlfa;

public class MementoOfModel {

	private ArrayList<Customer> theCustomers;
	private Map<String, Product> theProducts;
	private boolean marktingStatos[]; // the customer is transfer by ref so the markting may be chang
	private String order;

	// get Store status and saveIt
	public MementoOfModel(Store beforSave) {
		this.order = beforSave.getOrder();
		this.OrderBy();
		this.theCustomers = new ArrayList<Customer>();
		marktingStatos = new boolean[beforSave.getCustomers().size()];
		for (int i = 0; i < beforSave.getCustomers().size(); i++) {
			this.theCustomers.add(beforSave.getCustomers().get(i));
			marktingStatos[i] = theCustomers.get(i).getMarketing();
		}
		if (beforSave.getProducts().isEmpty() != true)
			this.theProducts.putAll(beforSave.getProducts());
	}

	// get the moment back
	public Store getState() {
		Store theModel = Store.getInstance();
		theModel.setOrder(this.order);
		for (int i = 0; i < marktingStatos.length; i++) {
			theCustomers.get(i).setMarketing(marktingStatos[i]);
		}
		theModel.setCustomers(theCustomers);
		theModel.setProducts(theProducts);
		return theModel;
	}

	// inside metod -not really part of the dising pattern
	private void OrderBy() {
		if (order == null) {
			return;
		}
		if (order.equals("ascending")) {
			this.sortByByAlfphB();
		} else if (order.equals("descending")) {
			this.sortByByBetaA();
		} else {
			this.sortByOrder();
		}
	}

	private void sortByByAlfphB() {
		byAlfphB cmp = new byAlfphB();
		this.theProducts = new TreeMap<String, Product>(cmp);
	}

	private void sortByByBetaA() {
		byBetaAlfa cmp = new byBetaAlfa();
		this.theProducts = new TreeMap<String, Product>(cmp);
	}

	public void sortByOrder() {
		this.theProducts = new LinkedHashMap<String, Product>();
	}

}
