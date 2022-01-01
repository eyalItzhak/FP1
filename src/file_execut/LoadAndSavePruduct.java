package file_execut;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import model.Customer;
import model.Product;

public class LoadAndSavePruduct implements File_Function {

	private Map<String, Product> products;
	private final static String nameFile = "Product.txt";
	private Map<String, Customer> customerList;
	private int totalFileSize;

	public LoadAndSavePruduct() throws FileNotFoundException {
		this.totalFileSize = 0;
	}

	@Override
	public void saveAllObjects() {
		try {
			RandomAccessFile fileInfo = new RandomAccessFile(nameFile, "rw");
			int tempSize;
			totalFileSize = 4;
			fileInfo.writeInt(products.size()); // new
			for (Map.Entry<String, Product> entry : products.entrySet()) {
				tempSize = entry.getValue().sizeOfProduct();
				totalFileSize = totalFileSize + tempSize + 4;// +4 for the int the the start for the utf
				fileInfo.writeInt(tempSize);
				entry.getValue().save(fileInfo);
			}
			fileInfo.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("fail");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("fail");
			e.printStackTrace();
		}

	}

	@Override
	public void LoadAllObjects() {
		customerList = new LinkedHashMap<String, Customer>();
		products = new LinkedHashMap<String, Product>();
		totalFileSize = 4;
		int index = 0;
		try {
			RandomAccessFile fileInfo = new RandomAccessFile(nameFile, "rw");

			if (fileInfo.length() == 0) {
				return;
			}

			index = fileInfo.readInt();
			for (int i = 0; i < index; i++) {
				totalFileSize = totalFileSize + fileInfo.readInt() + 4; // for size of object-not relvant to this method
				Product p1 = new Product();
				p1.load(fileInfo);
				addNewCustomerToList(p1);
				this.products.put(p1.getMKTid(), p1);
			}

			fileInfo.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public Map<String, Customer> getCustomerList() {
		return this.customerList;
	}

	public void setCustomerList(Map<String, Customer> customerList) {
		this.customerList = customerList;
	}

	private void addNewCustomerToList(Product p1) {
		String KeyId = String.valueOf(p1.getTheCustomer().getMyId());
		if (this.customerList.containsKey(KeyId)) {
			p1.setTheCustomer(customerList.get(KeyId));
		} else {
			this.customerList.put(KeyId, p1.getTheCustomer());
		}
	}

	public Map<String, Product> getProducts() {
		return this.products;
	}

	public void setProducts(Map<String, Product> products) {
		this.products = products;
	}

	public int getTotalFileSize() {
		return totalFileSize;
	}

	public void setTotalFileSize(int totalFileSize) {
		this.totalFileSize = totalFileSize;
	}


}
