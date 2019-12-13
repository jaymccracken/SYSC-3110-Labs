import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class AddressBookTest {

	private BuddyInfo bi = null;
	private BuddyInfo bi2 = null;
	private AddressBook list = null;
	private AddressBook list2 = null;
	@Before
	public void setUp() throws Exception {
		bi = new BuddyInfo ("J",20,"905");
		bi2 = new BuddyInfo ("K",15,"613");
		list = new AddressBook();
		list.addBuddy(bi);
		list2.addBuddy(bi2);
	}

	@Test
	public void testSize() {
		assertEquals("The list should contain",1, list.size());
	}

	@Test
	public void testClear() {
		list.clear();
		assertEquals("The list should contain","[]", list.getList().toString());
	}

	@Test
	public void testAddBuddy() {
		assertEquals("The list should contain","["+bi.toString()+"]", list.getList().toString());
	}

	@Test
	public void testRemoveBuddy() {
		list.addBuddy(bi2);
		list.removeBuddy(1);
		assertEquals("The list should contain","["+bi.toString()+"]", list.getList().toString());
	}
	
	@SuppressWarnings("resource")
	@Test
	public void testSave() throws IOException {
		try {
			FileOutputStream f = new FileOutputStream(new File("myObjects.txt"));
			ObjectOutputStream o = new ObjectOutputStream(f);

			o.writeObject(bi);
			
			o.close();
			f.close();
			
			FileInputStream fi = new FileInputStream(new File("myObjects.txt"));
			ObjectInputStream oi = new ObjectInputStream(fi);
			BuddyInfo buddy;
			buddy = (BuddyInfo) oi.readObject();
			
			assertEquals(buddy.toString(),"Name: J, Age: 20, PhoneNumber:  905");
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			}
	}
	
	@Test
	public void testExportXML() throws IOException {
		list2.exportToXML();
		BufferedReader br;
		br = new BufferedReader(new FileReader("xml.txt"));
		assertEquals(br.read(),60);
	}
	
	@Test
	public void testImportXML() throws Exception {
		String one = list2.toXML();
		list.ImportXML(new File("xml.txt"));
		String two = list.toXML();
		assertEquals(one,two);
	}


}
