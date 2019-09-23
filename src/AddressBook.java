import java.util.ArrayList;
import java.util.List;
public class AddressBook {
	private List<BuddyInfo> buddyInfo;
	
	//ArrayList<BuddyInfo> buddyInfo = new ArrayList<BuddyInfo>();
	
	public AddressBook() {
		this.buddyInfo = new ArrayList<BuddyInfo>();
	}
	public void addBuddy(BuddyInfo aBuddyInfo) {
		if(aBuddyInfo != null) {
			this.buddyInfo.add(aBuddyInfo);
		}
	}
	
	public BuddyInfo removeBuddy(int index) {
		if(index >=0 && index < buddyInfo.size()) {
			return this.buddyInfo.remove(index);
		}
		return null;
	}
	
	public static void main(String[] args) {
		BuddyInfo buddy = new BuddyInfo("Tom","Carleton","613");
		AddressBook addressBook = new AddressBook();
		addressBook.addBuddy(buddy);
		addressBook.removeBuddy(0);
	}
}
