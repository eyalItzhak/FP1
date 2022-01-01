package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import file_execut.LoadAndSavePruduct;
import momento.MementoOfModel;

public class Store {

	private ArrayList<Customer> customers;
	private Map<String, Product> products;
	private final static String nameFile = "info.txt";
	private String order;

	private MementoOfModel lastSave;
	private LoadAndSavePruduct file;

	private static Store instance;

	private Store() {
		try {
			file = new LoadAndSavePruduct();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		customers = new ArrayList<Customer>();
		products = new LinkedHashMap<String, Product>(); // defult list
		order = "";
	}

	// metod for singlton

	public static Store getInstance() {
		if (instance == null) {
			instance = new Store();
		}
		return instance;
	}

	// sent mesgToAllCustomer-observer
	public ArrayList<String> SentMesgToSubscribCustomer(String Msg) {
		ArrayList<String> subscrib = new ArrayList<String>();

		for (int i = 0; i < customers.size(); i++) {
			if (customers.get(i).getMarketing()) {
				subscrib.add(customers.get(i).ReplyToMsg(Msg));
			}
		}
		return subscrib;
	}

	public void addCustomer(Customer c1) {
		if (!customers.contains(c1)) {
			customers.add(c1);
			if (c1.getMarketing()) {
			}
		}

	}

	public void removeProduct(Product d) {
		boolean delatCustomer = true;
		Customer c = d.getTheCustomer();
		products.remove(d.getMKTid());
		for (Map.Entry<String, Product> entry : products.entrySet()) {
			if (entry.getValue().getTheCustomer().equals(c)) {
				delatCustomer = false;
			}
		}
		if (delatCustomer) {
			this.customers.remove(c);
		}
	}

	public void inserProduct(Product o1) {

		this.insertToCustomerListFromProduct(o1);
		products.put(o1.getMKTid(), o1);

	}

	public void printMap() {
		for (Map.Entry<String, Product> entry : products.entrySet()) {
			System.out.println("Key: " + entry.getKey() + " " + entry.getValue().getMKTid());
		}
	}

	public ArrayList<Product> getProductArry() { // temp?
		ArrayList<Product> ToReturn = new ArrayList<Product>();
		for (Map.Entry<String, Product> entry : products.entrySet()) {
			ToReturn.add(entry.getValue());
		}
		return ToReturn;
	}

	public Product findProdByCN(String cn) {
		Product temp = this.products.get(cn);
		return temp;
	}

	// **************sort*******************

	public void OrderBy(String Order) {
		this.order = Order;
		if (Order.equals("ascending")) {
			this.sortByByAlfphB();
		} else if (Order.equals("descending")) {
			this.sortByByBetaA();
		} else {
			this.sortByOrder();
		}
	}

	public void sortByByAlfphB() {
		byAlfphB cmp = new byAlfphB();
		Map ProductsTemp = new TreeMap<String, Product>(cmp);
		if (!products.isEmpty()) {
			ProductsTemp.putAll(products);

		}
		products = ProductsTemp;

	}

	public void sortByByBetaA() {
		byBetaAlfa cmp = new byBetaAlfa();
		Map ProductsTemp = new TreeMap<String, Product>(cmp);
		if (!products.isEmpty()) {
			ProductsTemp.putAll(products);

		}
		products = ProductsTemp;
	}

	public void sortByOrder() {
		Map ProductsTemp = new LinkedHashMap<String, Product>();
		if (!products.isEmpty()) {
			ProductsTemp.putAll(products);

		}
		products = ProductsTemp;
	}

	// *****************inside method**************************

	private int insertToCustomerListFromProduct(Product o1) {

		for (int i = 0; i < this.customers.size(); i++) {
			if (this.customers.get(i).ItsTheSame(o1.getTheCustomer())) {
				this.customers.get(i).setMarketing(o1.getTheCustomer().getMarketing()); // may do problem
				o1.setTheCustomer(this.customers.get(i));
				return 0;
			}
		}
		this.customers.add(o1.getTheCustomer());

		if (o1.getTheCustomer().getMarketing()) {
		}
		return 1;
	}

	private void updateCustomer() {
		for (Map.Entry<String, Product> entry : products.entrySet()) {
			if (false == (this.customers.contains(entry.getValue().getTheCustomer())))
				this.addCustomer(entry.getValue().getTheCustomer());
		}

	}

	// ***********save and load***********************************

	public void loadFile() {
		try {
			this.file = new LoadAndSavePruduct();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.file.LoadAllObjects();
		this.products = this.file.getProducts();
		Map<String, Customer> Customers1 = this.file.getCustomerList();
		for (Map.Entry<String, Customer> entry : Customers1.entrySet()) {
			this.customers.add(entry.getValue());
		}

	}

	public void SaveFile() {
		this.file.setProducts(this.products);
		file.saveAllObjects();
	}


	// *********************getter and
	// seter******************************************

	public ArrayList<Customer> getCustomers() {
		return this.customers;
	}

	public void setCustomers(ArrayList<Customer> customers) {
		this.customers = customers;
	}

	public Map<String, Product> getProducts() {
		return this.products;
	}

	public void setProducts(Map<String, Product> products) {
		this.products = products;
	}

	public static String getNamefile() {
		return nameFile;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public MementoOfModel getLastSave() {
		return lastSave;
	}

	public LoadAndSavePruduct getFile() {
		return file;
	}

	public void setFile(LoadAndSavePruduct file) {
		this.file = file;
	}
	//////////////////////////////////////////////////////// momentoFunction

	public MementoOfModel saveStateToMemento() {
		String FileName = nameFile;
		return new MementoOfModel(this);
	}

	public boolean MomentoIsExis() {
		if (lastSave == null) {
			return false;
		} else {
			return true;
		}
	}

	public void setLastSave(MementoOfModel lastSave) {
		this.lastSave = lastSave;
	}

	public void SetStateFromLastSave() {
		this.getStateFromMemento(this.lastSave);
		this.lastSave = null;
	}

	public void getStateFromMemento(MementoOfModel memento) {
		Store Temp = memento.getState();
		this.customers = Temp.getCustomers();
		this.order = Temp.getOrder();
		this.products = Temp.getProducts();
	}

////////////////////////////////////////////////////

	// ...
	public void removeAllProduct() {
		customers = new ArrayList<Customer>();
		products = new LinkedHashMap<String, Product>();
	}

}
