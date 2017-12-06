package patricia;

public class TestCode {

	public static void a(StringBuilder s) {
		s.append("a");
	}
	public static void b(StringBuilder s) {
		s.append("b");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringBuilder s = new StringBuilder("aaaa");
//		a(s);
//		b(s);
		StringBuilder s1 = s.deleteCharAt(s.length()-1);
		b(s);
		b(s1);
		System.out.println(s);
		System.out.println(s1);
	
	}

}
