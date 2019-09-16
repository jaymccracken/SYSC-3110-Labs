import java.util.ArrayList;

public class AddressBook {

	ArrayList<BuddyInfo> buddyInfo = new ArrayList<BuddyInfo>();
	
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
	
	public static void main(String[] args) {
		BuddyInfo buddy = new BuddyInfo("Tom","Carleton","613");
		AddressBook addressBook = new AddressBook();
		addressBook.addBuddy(buddy);
		addressBook.removeBuddy(0);
	}
}
