
public class MemberCallTest {

	public static void main(String[] args) {
		
		
		System.out.println(MemberCall.cv);
		MemberCall.stM();
		MemberCall.stM2();
		
		MemberCall mc = new MemberCall(); //생성자 우선
		mc.inM();
		mc.inM2();
		
		
		
		
	}

}
