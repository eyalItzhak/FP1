package model;

import java.io.BufferedOutputStream;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;

import file_execut.LoadAndSavePruduct;
import observer.observer;

public class Customer extends observer implements Serializable {

	private String name;
	private String phone;
	private boolean marketing;

	private static int Id = 0;
	private int MyId = 0;

	private String msg;
	public observer getMarketing;

	public Customer() {

		name = "";
		phone = "";
		marketing = true;
	}

	public Customer(String name, String phone, String isAproved) {
		this.MyId = Id;
		Id = Id + 1;
		this.msg = "";
		this.name = name;
		this.phone = phone;
		if (isAproved.equalsIgnoreCase("Aprove")) {
			this.marketing = true;
		} else {
			this.marketing = false;
		}

	}

	public boolean ItsTheSame(Customer c) {
		if (c.getName().equals(this.name)) {
			if (c.getPhone().equals(this.getPhone())) {
				return true;
			}
		}
		return false;

	}


	@Override
	public String ReplyToMsg(String Msg) {
		this.msg = Msg;
		return this.name;
	}

	public void save(DataOutput output) throws IOException {
		output.writeUTF(name);
		output.writeUTF(phone);
		output.writeBoolean(marketing);
		output.writeInt(MyId);
	}

	public int SizeOfCustomer() { // whith dataoutpot...
		return this.name.length() + 2 + this.phone.length() + 2 + 1 + 4; // 2,2 for wirte utf +1 for boolean+4 for int
	}

	public void load(RandomAccessFile fileInfo) throws IOException {
		this.name = fileInfo.readUTF();
		this.phone = fileInfo.readUTF();
		this.marketing = fileInfo.readBoolean();
		this.MyId = fileInfo.readInt();
		if (MyId > this.Id) {
			this.Id = MyId;
		}

	}

	// *************************************************getters and setters****************************
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isMarketing() {
		return this.marketing;
	}

	public void setMarketing(boolean marketing) {
		this.marketing = marketing;
	}

	public boolean getMarketing() {
		return this.marketing;
	}
	
	public observer getGetMarketing() {
		return getMarketing;
	}

	public void setGetMarketing(observer getMarketing) {
		this.getMarketing = getMarketing;
	}
	

	public int getMyId() {
		return MyId;
	}

	public void setMyId(int myId) {
		MyId = myId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
