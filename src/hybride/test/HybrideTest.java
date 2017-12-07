package hybride.test;


import org.junit.Test;

import exceptions.BadArgumentException;
import hybride.HNode;
import hybride.Hybride;
import junit.framework.TestCase;

public class HybrideTest extends TestCase {
	
	private Hybride trie;
	
	public HybrideTest(String methodeName) {
		super(methodeName);
	}
	
	public void setUp(){
		trie = new Hybride();
		try{
			trie.add("bonjour");
			trie.add("bonsoir");
			trie.add("bonne");
			trie.add("bon");
			trie.add("sale");
			trie.add("salut");
			trie.add("bonnenuit");
		}catch(BadArgumentException e){
			fail("BadArgumentException levee dans setUp");
		}
	}
	
	@Test
	public void testTrie(){
		HNode tmp;
		
		assertTrue(trie.getNbMot() == 7);
		
		tmp = trie.getHd();
		assertTrue(tmp.getValue() == null);
		assertTrue(tmp.getPrefix().compareTo("b") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() != null);
		
		tmp = tmp.getSon();
		assertTrue(tmp.getValue() == null);
		assertTrue(tmp.getPrefix().compareTo("o") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);
		
		tmp = tmp.getSon();
		assertTrue(tmp.getValue() != null && tmp.getValue().intValue() == 3);
		assertTrue(tmp.getPrefix().compareTo("n") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(tmp.getValue() == null);
		assertTrue(tmp.getPrefix().compareTo("j") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() != null);
		
		tmp = tmp.getSon();
		assertTrue(tmp.getValue() == null);
		assertTrue(tmp.getPrefix().compareTo("o") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);
		
		tmp = tmp.getSon();
		assertTrue(tmp.getValue() == null);
		assertTrue(tmp.getPrefix().compareTo("u") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);
		
		tmp = tmp.getSon();
		assertTrue(tmp.getValue() != null && tmp.getValue().intValue() == 0);
		assertTrue(tmp.getPrefix().compareTo("r") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() == null);
		assertTrue(tmp.getNext() == null);
		
		tmp = trie.getHd().getSon().getSon().getSon().getNext();
		assertTrue(tmp.getValue() == null);
		assertTrue(tmp.getPrefix().compareTo("s") == 0);
		assertTrue(tmp.getPrevious() != null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getPrevious();
		assertTrue(tmp.getValue() == null);
		assertTrue(tmp.getPrefix().compareTo("n") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(tmp.getValue() != null && tmp.getValue().intValue() == 2);
		assertTrue(tmp.getPrefix().compareTo("e") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(tmp.getValue() == null);
		assertTrue(tmp.getPrefix().compareTo("n") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(tmp.getValue() == null);
		assertTrue(tmp.getPrefix().compareTo("u") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);
		
		tmp = tmp.getSon();
		assertTrue(tmp.getValue() == null);
		assertTrue(tmp.getPrefix().compareTo("i") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(tmp.getValue() != null && tmp.getValue().intValue() == 6);
		assertTrue(tmp.getPrefix().compareTo("t") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() == null);
		assertTrue(tmp.getNext() == null);
		
		tmp = trie.getHd().getSon().getSon().getSon().getNext().getSon();
		assertTrue(tmp.getValue() == null);
		assertTrue(tmp.getPrefix().compareTo("o") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(tmp.getValue() == null);
		assertTrue(tmp.getPrefix().compareTo("i") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(tmp.getValue() != null && tmp.getValue().intValue() == 1);
		assertTrue(tmp.getPrefix().compareTo("r") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() == null);
		assertTrue(tmp.getNext() == null);
		
		tmp = trie.getHd().getNext();
		assertTrue(tmp.getValue() == null);
		assertTrue(tmp.getPrefix().compareTo("s") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(tmp.getValue() == null);
		assertTrue(tmp.getPrefix().compareTo("a") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(tmp.getValue() == null);
		assertTrue(tmp.getPrefix().compareTo("l") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);
		
		tmp = tmp.getSon();
		assertTrue(tmp.getValue() != null && tmp.getValue().intValue() == 4);
		assertTrue(tmp.getPrefix().compareTo("e") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() == null);
		assertTrue(tmp.getNext() != null);
		
		tmp = tmp.getNext();
		assertTrue(tmp.getValue() == null);
		assertTrue(tmp.getPrefix().compareTo("u") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);
		
		tmp = tmp.getSon();
		assertTrue(tmp.getValue() != null && tmp.getValue().intValue() == 5);
		assertTrue(tmp.getPrefix().compareTo("t") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() == null);
		assertTrue(tmp.getNext() == null);
	}
	
	@Test
	public void testAdd(){
		/* TODO ajout du mot  */
		/* TODO ajout d un mot deja existant */
		
	}
	
	
	

}
