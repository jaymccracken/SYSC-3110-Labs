import java.io.BufferedWriter;
import java.io.File;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.DefaultListModel;



public class AddressBook  extends DefaultHandler implements Serializable{

	private ArrayList<BuddyInfo> buddyInfo;
	
	public AddressBook () {
		this.buddyInfo = new ArrayList<BuddyInfo>();
	}
	
	public void addBuddy(BuddyInfo aBuddyInfo) {
		if(aBuddyInfo != null) {
			buddyInfo.add(aBuddyInfo);
		}
	}
	
	public void removeBuddy(int index) {
		if(index >=0 && index < buddyInfo.size()) {
			buddyInfo.remove(index);
		}
	}
	
	public ArrayList<BuddyInfo> getList() {
		return this.buddyInfo;
	}
	
	public BuddyInfo getBuddy(int index) {
		return buddyInfo.get(index).getBuddy();
	}
	
	public int size() {
			return this.buddyInfo.size();
		}
	
	public void clear() {
		this.buddyInfo.clear();
	}
	
	public void save(String FileName) {
		try {
		FileOutputStream f = new FileOutputStream(new File(FileName));
		ObjectOutputStream o = new ObjectOutputStream(f);
		for(int i=0; i< this.size() ;i++) {
			o.writeObject(this.getBuddy(i).toString());
		}
		o.close();
		f.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		}	
	}
	
	public String toXML() {
		String xml = "<AddressBook> \n";
		for(int i=0; i< this.size() ;i++) {
			xml += this.getBuddy(i).toXML();
		}
		xml += "</AddressBook> \n";
		return xml;
	}
	
	public void exportToXML() throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("xml.txt"));
	    writer.write(this.toXML());	     
	    writer.close();		
	}
	
	public void ImportXML(File f) throws Exception {
		 SAXParserFactory spf = SAXParserFactory.newInstance();
		 SAXParser s = spf.newSAXParser();
		 
		 
		 DefaultHandler dh = new DefaultHandler() {
			 String curr;
			 BuddyInfo b = null;
			 public void startElement(String u, String ln, String qName, Attributes a) {
				 System.out.println("START: " + qName);
				 curr = qName;
				 
			 }
			 public void endElement(String u, String ln, String qName) {
				 System.out.println("END: " + qName);
				 if (curr == "BuddyInfo") {
					 buddyInfo.add(b);
				 }
			 }
			 public void characters(char[] ch, int start, int length) {
				 System.out.println("CHARS: " + new String (ch,start,length));
				 if (curr == "BuddyInfo") {
					 b = new BuddyInfo(null,0,null);
				 }
				 if (curr == "Name") {
					 b.setName(new String (ch,start,length));
				 }
				 if (curr == "Age") {
					 b.setAge(20);
				 }
				 if (curr == "PhoneNumber") {
					 b.setPhoneNumber(new String (ch,start,length));
				 }
			 }	 
		 };
		 s.parse(f, dh);
		 
	}
	
	public static void main(String[] args) throws Exception {
		BuddyInfo buddy = new BuddyInfo("Tom",19,"613");
		AddressBook addressBook = new AddressBook();
		addressBook.addBuddy(buddy);
		addressBook.exportToXML();
		File f = new File("xml.txt");
		addressBook.ImportXML(f);
		System.out.println(addressBook.toXML());
	}
}
