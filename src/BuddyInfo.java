import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class BuddyInfo implements Serializable{
	String name;
	int age;
	String phoneNumber;
	
	public BuddyInfo(String name, int age, String phoneNumber) {
		this.name = name;
		this.age = age;
		this.phoneNumber = phoneNumber;
	}

	BuddyInfo(BuddyInfo b){
		this(b.name,b.age,b.phoneNumber);
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public boolean isOver18() {
		if (this.age > 18) {
			return true;
		}
		return false;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public BuddyInfo getBuddy() {
		return this;
	}
	
	public String greeting() {
		return "Hey " + this.getName();
	}
	@Override
	public String toString() {
		return "Name: " + this.name + ", Age: " + this.age + ", PhoneNumber:  " + this.phoneNumber;
	}
	
	public static BuddyInfo Import(String s) {
		try {
		
			FileInputStream f = new FileInputStream(new File(s));
			ObjectInputStream o = new ObjectInputStream(f);

			BuddyInfo buddy = (BuddyInfo) o.readObject();
			o.close();
			f.close();
			return buddy;
//		 BufferedReader br;
//		 br = new BufferedReader(new FileReader(file));
//		 String line = br.readLine();
//		 while(line != null) {
//			      String[] details = line.split(",");
//			      String[] name = details[0].split(": ");
//			      String[] age = details[1].split(": ");
//			      String[] phoneNumber = details[2].split(": ");
//			      
//			      BuddyInfo newBuddy = new BuddyInfo(name[1],Integer.parseInt(age[1]),phoneNumber[1]);
//			      return newBuddy;
//			   }
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public String toXML() {
		String xml = "\t <BuddyInfo> \n"
				+ "\t\t <Name>" + this.name + "</Name>\n "
				+ "\t\t <Age>" + this.age + "</Age> \n"
				+ "\t\t <PhoneNumber>" + this.phoneNumber + "</PhoneNumber> \n"
				+ "\t </BuddyInfo> \n";
		return xml;
	}

	public static void main(String[] args) {
	}

	

}
