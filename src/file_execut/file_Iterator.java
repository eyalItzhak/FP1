package file_execut;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import model.Product;

public class file_Iterator implements Iterator<Product> {

	private final static String nameFile = "Product.txt";

	private RandomAccessFile iteratorRaf;
	private int totalSize;
	private int startPos;
	private int myCurrentPos;
	private int lastReadSize;
	private int lastPos;

	public file_Iterator(int TotalSizeOfFile) throws IOException {
		this.iteratorRaf = new RandomAccessFile(nameFile, "rw");
		this.myCurrentPos = 4;
		iteratorRaf.seek(myCurrentPos);
		totalSize = TotalSizeOfFile;
	}

	@Override
	public boolean hasNext() {
		if (this.myCurrentPos < this.totalSize) {
			return true;
		}
		return false;
	}

	public Product next() {
		if (this.hasNext() == false) {
			return null;
		} else {

			try {
				lastReadSize = iteratorRaf.readInt();
				lastPos = myCurrentPos;
				myCurrentPos = myCurrentPos + 4;
				Product p = readPoductFromPosInFile();
				myCurrentPos = myCurrentPos + p.sizeOfProduct();
				return p;

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return null;
	}

	public void removeAll() {

		try {
			iteratorRaf.seek(0);
			iteratorRaf.writeInt(0);
			iteratorRaf.setLength(4);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void remove() {

		try {
			Product temp;
			iteratorRaf.seek(this.lastPos);
			int myDelatSizeInTheEndFromFile = this.lastReadSize + 4; // +4 for the int for the size of last object
			iteratorRaf.seek(this.myCurrentPos);

			int witrePos = this.lastPos;

			while (hasNext()) {

				iteratorRaf.seek(this.myCurrentPos);

				temp = this.next();
				iteratorRaf.seek(witrePos);
				iteratorRaf.writeInt(lastReadSize); // write new size
				temp.save(iteratorRaf);
				witrePos = witrePos + lastReadSize + 4;
			}
			this.totalSize = this.totalSize - myDelatSizeInTheEndFromFile;
			iteratorRaf.setLength(totalSize);

			iteratorRaf.seek(0);
			int tempObjectCounter = iteratorRaf.readInt();
			tempObjectCounter = tempObjectCounter - 1;
			iteratorRaf.seek(0);
			iteratorRaf.writeInt(tempObjectCounter);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private Product readPoductFromPosInFile() throws IOException {
		Product p = new Product();
		p.load(iteratorRaf);
		return p;
	}

	public RandomAccessFile getIteratorRaf() {
		return this.iteratorRaf;
	}

	public void setIteratorRaf(RandomAccessFile iteratorRaf) {
		this.iteratorRaf = iteratorRaf;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public int getStartPos() {
		return startPos;
	}

	public void setStartPos(int startPos) {
		this.startPos = startPos;
	}

	public int getMyCurrentPos() {
		return myCurrentPos;
	}

	public void setMyCurrentPos(int myCurrentPos) {
		this.myCurrentPos = myCurrentPos;
	}

	public int getMyReadSize() {
		return lastReadSize;
	}

	public void setMyReadSize(int myReadSize) {
		lastReadSize = myReadSize;
	}

	public static String getNamefile() {
		return nameFile;
	}

}
