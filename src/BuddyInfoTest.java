import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


import org.junit.Before;
import org.junit.Test;


public class BuddyInfoTest {

	private BuddyInfo bi = null;
	private BuddyInfo bi1 = null;
	private BuddyInfo bi2 = null;
	private BuddyInfo bi3 = null;
	
	@Before
	public void setUp() throws Exception {
		bi = new BuddyInfo ("J",20,"905");
		bi2 = new BuddyInfo ("K",15,"613");bi3 = new BuddyInfo ("K",18,"613");
	}

	@Test
	public void testCopyConstructor() {
		bi3 = new BuddyInfo ("L",5,"905");
		bi1 = new BuddyInfo(bi3);
		assertEquals("They should be false", false, bi1.equals(bi3));
	}
	
	@Test
	public void testGetName() {
		bi.setName("jay");
		assertEquals("Name should be jay", "jay", bi.getName());
	}

	@Test
	public void testGetAge() {
		bi.setAge(21);
		assertEquals("age should be 21.", 21, bi.getAge());
	}

	@Test
	public void testIsOver18() {
		assertEquals("They should be over 18.", true, bi.isOver18());
		assertEquals("They should be over 18.", false, bi3.isOver18());
		assertEquals("they should be under 18", false, bi2.isOver18());
	}

	@Test
	public void testGetPhoneNumber() {
		bi.setPhoneNumber("123");
		assertEquals("their phone number should be 123", "123", bi.getPhoneNumber());
	}

	@Test
	public void testgreeting() {
		assertEquals("the greeting should be Hey K", "Hey K", bi2.greeting());
	}

}
