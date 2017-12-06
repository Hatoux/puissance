package hybride;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;
import org.junit.Test;
import exceptions.BadArgumentException;
import patricia.PatriciaTrie;

public class Test3 {

	
	
	@Test
	public  void test() {

		ArrayList<String> l= new ArrayList<>();
		l.add("bonjour");
		l.add("bonsoir");
		l.add("bonne");
		l.add("bon");
		l.add("salut");
		l.add("sale");
		l.add("bonnenuit");
		try {
			Hybride abr = new Hybride();
			for(String s : l)
				abr.add(s);
			
			PatriciaTrie pt = abr.toPatriciaTrie();
			for(String s : l)
			if(!pt.recherche(s))
				fail("!!!!!!!!!!! PROBLEME 1!!!!!!!!!  "+s);
			System.out.println("42: ");
		} catch (BadArgumentException e) {
			e.printStackTrace();
		}			
	}
}