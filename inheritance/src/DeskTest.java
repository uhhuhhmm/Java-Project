
public class DeskTest {

	public static void main(String[] args) {
		Deck d = new Deck();
		Card c = d.pick(0);
		System.out.println(c.toString());
		d.shuffle();
		c = d.pick(0);
		System.out.println(c.toString());
		
		
		
		
	}

}
