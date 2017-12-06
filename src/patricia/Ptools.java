package patricia;

public class Ptools {
	
	public static String getPrefixCommun(String s1, String s2){
		int min = s1.length() < s2.length() ? s1.length() : s2.length();
		StringBuilder s = new StringBuilder();
		for(int i=0; i<min; i++) {
			if(s2.charAt(i) == s1.charAt(i)) 
				s.append(s2.charAt(i));
			else break;
		}
		return s.toString();
	}
	
	public static NodeP newNodeP(String s) {
		if(s=="")
			return null;
		else
			return new NodeP(s);
	}
}