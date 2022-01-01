package model;

import java.io.BufferedOutputStream;
import java.io.DataOutput;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;

public class Product  implements Serializable {

	private String nameOfProduct;
	private String MKTid;
	private int priceForCustomer;
	private int priceForShop;
	private Customer TheCustomer;
	private String customerName;
	private int profit;
	
	public Product(){ 
		this.nameOfProduct="";
		this.MKTid="";
		this.priceForCustomer=0;
		this.priceForShop=0;
		this.TheCustomer=new Customer(); 
	}
	
	public Product(String nameOfProduct,String MKTid,int priceForCustomer,int priceForShop,Customer TheCustomer){
		this.nameOfProduct=nameOfProduct;
		this.MKTid=MKTid;
		this.priceForCustomer=priceForCustomer;
		this.priceForShop=priceForShop;
		this.TheCustomer=TheCustomer;
		this.customerName = TheCustomer.getName();
		this.profit = (priceForCustomer-priceForShop);
	}
	
	
	public void save(DataOutput  output) throws IOException {

		output.writeUTF(nameOfProduct);
		output.writeUTF(MKTid);
		output.writeInt(priceForCustomer);
		output.writeInt(priceForShop);
		TheCustomer.save(output);
	}
	
	
	public int sizeOfProduct(){ //white data outPotSteamSize
		return this.nameOfProduct.length()+2+this.MKTid.length()+2+4+4+TheCustomer.SizeOfCustomer(); //size of 
	}
	
	public void load(RandomAccessFile fileInfo) throws IOException {
		this.nameOfProduct=fileInfo.readUTF();
		this.MKTid=fileInfo.readUTF();
		this.priceForCustomer=fileInfo.readInt();
		this.priceForShop=fileInfo.readInt();
		this.TheCustomer=new Customer();
        this.TheCustomer.load(fileInfo);
        this.customerName = TheCustomer.getName();
        this.profit = (priceForCustomer-priceForShop);
	}
	
	//********************************getters and setters*******************************
	public String getNameOfProduct() {
		return nameOfProduct;
	}
	public void setNameOfProduct(String nameOfProduct) {
		this.nameOfProduct = nameOfProduct;
	}
	public String lastPos() {
		return MKTid;
	}
	public void setMKTid(String mKTid) {
		MKTid = mKTid;
	}
	public int getPriceForCustomer() {
		return priceForCustomer;
	}
	public void setPriceForCustomer(int priceForCustomer) {
		this.priceForCustomer = priceForCustomer;
	}
	public int getPriceForShop() {
		return priceForShop;
	}
	public void setPriceForShop(int priceForShop) {
		this.priceForShop = priceForShop;
	}
	public Customer getTheCustomer() {
		return TheCustomer;
	}
	public void setTheCustomer(Customer theCustomer) {
		TheCustomer = theCustomer;
	}

	public String getMKTid() {
		return MKTid;
		
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getProfit() {
		return profit;
	}

	

	

	

	
	
	
}
