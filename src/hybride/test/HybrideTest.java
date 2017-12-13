package hybride.test;


import java.util.ArrayList;

import org.junit.Test;

import exceptions.BadArgumentException;
import hybride.HNode;
import hybride.Hybride;
import junit.framework.TestCase;
import patricia.PatriciaTrie;

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

		tmp = trie.getHd();
		assertTrue(tmp.isWord() == false);
		assertTrue(tmp.getPrefix().compareTo("b") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() != null);

		tmp = tmp.getSon();
		assertTrue(tmp.isWord() == false);
		assertTrue(tmp.getPrefix().compareTo("o") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(tmp.isWord() == true);
		assertTrue(tmp.getPrefix().compareTo("n") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(tmp.isWord() == false);
		assertTrue(tmp.getPrefix().compareTo("j") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() != null);

		tmp = tmp.getSon();
		assertTrue(tmp.isWord() == false);
		assertTrue(tmp.getPrefix().compareTo("o") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(tmp.isWord() == false);
		assertTrue(tmp.getPrefix().compareTo("u") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(tmp.isWord() == true);
		assertTrue(tmp.getPrefix().compareTo("r") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() == null);
		assertTrue(tmp.getNext() == null);

		tmp = trie.getHd().getSon().getSon().getSon().getNext();
		assertTrue(tmp.isWord() == false);
		assertTrue(tmp.getPrefix().compareTo("s") == 0);
		assertTrue(tmp.getPrevious() != null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getPrevious();
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("n") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("e") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("n") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("u") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("i") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("t") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() == null);
		assertTrue(tmp.getNext() == null);

		tmp = trie.getHd().getSon().getSon().getSon().getNext().getSon();
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("o") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("i") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("r") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() == null);
		assertTrue(tmp.getNext() == null);

		tmp = trie.getHd().getNext();
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("s") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("a") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("l") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("e") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() == null);
		assertTrue(tmp.getNext() != null);

		tmp = tmp.getNext();
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("u") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("t") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() == null);
		assertTrue(tmp.getNext() == null);
	}

	@Test
	public void testAdd(){
		/* test les captures d'exceptions */
		try {
			trie.add("");
			fail("HybrideTest.testAdd: BadArgumentException non capture pour le mot vide");
		} catch (BadArgumentException e) {}

		try {
			trie.add("hello" + PatriciaTrie.EPSILON);
			fail("HybrideTest.testAdd: BadArgumentException non capture pour un mot contenant EPSILON" );
		} catch (BadArgumentException e) {}

		try {
			trie.add("hello" + PatriciaTrie.EPSILON + "toto");
			fail("HybrideTest.testAdd: BadArgumentException non capture pour un mot contenant EPSILON" );
		} catch (BadArgumentException e) {}

		try{
			HNode tmp = null;
			trie.add("bonjour!!!");

			tmp = trie.getHd();
			assertTrue("HybrideTest.testAdd --> echec lors du test 1", !tmp.isWord());
			assertTrue("HybrideTest.testAdd --> echec lors du test 1",
					tmp.getPrefix().compareTo("b") == 0);
			assertTrue("HybrideTest.testAdd --> echec lors du test 1",
					tmp.getPrevious() == null);
			assertTrue("HybrideTest.testAdd --> echec lors du test 1",
					tmp.getSon() != null);
			assertTrue("HybrideTest.testAdd --> echec lors du test 1",
					tmp.getNext() != null);

			tmp = tmp.getSon();
			assertTrue("HybrideTest.testAdd --> echec lors du test 2", !tmp.isWord());
			assertTrue("HybrideTest.testAdd --> echec lors du test 2",
					tmp.getPrefix().compareTo("o") == 0);
			assertTrue("HybrideTest.testAdd --> echec lors du test 2",
					tmp.getPrevious() == null);
			assertTrue("HybrideTest.testAdd --> echec lors du test 2",
					tmp.getSon() != null);
			assertTrue("HybrideTest.testAdd --> echec lors du test 2",
					tmp.getNext() == null);

			tmp = tmp.getSon();
			assertTrue("HybrideTest.testAdd --> echec lors du test 3", tmp.isWord());
			assertTrue("HybrideTest.testAdd --> echec lors du test 3",
					tmp.getPrefix().compareTo("n") == 0);
			assertTrue("HybrideTest.testAdd --> echec lors du test 3",
					tmp.getPrevious() == null);
			assertTrue("HybrideTest.testAdd --> echec lors du test 3",
					tmp.getSon() != null);
			assertTrue("HybrideTest.testAdd --> echec lors du test 3",
					tmp.getNext() == null);

			tmp = tmp.getSon();
			assertTrue("HybrideTest.testAdd --> echec lors du test 4", !tmp.isWord());
			assertTrue("HybrideTest.testAdd --> echec lors du test 4",
					tmp.getPrefix().compareTo("j") == 0);
			assertTrue("HybrideTest.testAdd --> echec lors du test 4",
					tmp.getPrevious() == null);
			assertTrue("HybrideTest.testAdd --> echec lors du test 4",
					tmp.getSon() != null);
			assertTrue("HybrideTest.testAdd --> echec lors du test 4",
					tmp.getNext() != null);

			tmp = tmp.getSon();
			assertTrue("HybrideTest.testAdd --> echec lors du test 5", !tmp.isWord());
			assertTrue("HybrideTest.testAdd --> echec lors du test 5",
					tmp.getPrefix().compareTo("o") == 0);
			assertTrue("HybrideTest.testAdd --> echec lors du test 5",
					tmp.getPrevious() == null);
			assertTrue("HybrideTest.testAdd --> echec lors du test 5",
					tmp.getSon() != null);
			assertTrue("HybrideTest.testAdd --> echec lors du test 5",
					tmp.getNext() == null);

			tmp = tmp.getSon();
			assertTrue("HybrideTest.testAdd --> echec lors du test 6", !tmp.isWord());
			assertTrue("HybrideTest.testAdd --> echec lors du test 6",
					tmp.getPrefix().compareTo("u") == 0);
			assertTrue("HybrideTest.testAdd --> echec lors du test 6",
					tmp.getPrevious() == null);
			assertTrue("HybrideTest.testAdd --> echec lors du test 6",
					tmp.getSon() != null);
			assertTrue("HybrideTest.testAdd --> echec lors du test 6",
					tmp.getNext() == null);

			tmp = tmp.getSon();
			assertTrue("HybrideTest.testAdd --> echec lors du test 7", tmp.isWord());
			assertTrue("HybrideTest.testAdd --> echec lors du test 7",
					tmp.getPrefix().compareTo("r") == 0);
			assertTrue("HybrideTest.testAdd --> echec lors du test 7",
					tmp.getPrevious() == null);
			assertTrue("HybrideTest.testAdd --> echec lors du test 7",
					tmp.getSon() != null);
			assertTrue("HybrideTest.testAdd --> echec lors du test 7",
					tmp.getNext() == null);

			tmp = tmp.getSon();
			assertTrue("HybrideTest.testAdd --> echec lors du test 8", !tmp.isWord());
			assertTrue("HybrideTest.testAdd --> echec lors du test 8",
					tmp.getPrefix().compareTo("!") == 0);
			assertTrue("HybrideTest.testAdd --> echec lors du test 8",
					tmp.getPrevious() == null);
			assertTrue("HybrideTest.testAdd --> echec lors du test 8",
					tmp.getSon() != null);
			assertTrue("HybrideTest.testAdd --> echec lors du test 8",
					tmp.getNext() == null);

			tmp = tmp.getSon();
			assertTrue("HybrideTest.testAdd --> echec lors du test 9", !tmp.isWord());
			assertTrue("HybrideTest.testAdd --> echec lors du test 9",
					tmp.getPrefix().compareTo("!") == 0);
			assertTrue("HybrideTest.testAdd --> echec lors du test 9",
					tmp.getPrevious() == null);
			assertTrue("HybrideTest.testAdd --> echec lors du test 9",
					tmp.getSon() != null);
			assertTrue("HybrideTest.testAdd --> echec lors du test 9",
					tmp.getNext() == null);

			tmp = tmp.getSon();
			assertTrue("HybrideTest.testAdd --> echec lors du test 10", tmp.isWord());
			assertTrue("HybrideTest.testAdd --> echec lors du test 10",
					tmp.getPrefix().compareTo("!") == 0);
			assertTrue("HybrideTest.testAdd --> echec lors du test 10",
					tmp.getPrevious() == null);
			assertTrue("HybrideTest.testAdd --> echec lors du test 10",
					tmp.getSon() == null);
			assertTrue("HybrideTest.testAdd --> echec lors du test 10",
					tmp.getNext() == null);

			trie.add("bonjour!!!");

			tmp = trie.getHd();
			assertTrue("HybrideTest.testAdd --> echec lors du test 11", !tmp.isWord());
			assertTrue("HybrideTest.testAdd --> echec lors du test 11",
					tmp.getPrefix().compareTo("b") == 0);
			assertTrue("HybrideTest.testAdd --> echec lors du test 11",
					tmp.getPrevious() == null);
			assertTrue("HybrideTest.testAdd --> echec lors du test 11",
					tmp.getSon() != null);
			assertTrue("HybrideTest.testAdd --> echec lors du test 11",
					tmp.getNext() != null);

			tmp = tmp.getSon();
			assertTrue("HybrideTest.testAdd --> echec lors du test 12", !tmp.isWord());
			assertTrue("HybrideTest.testAdd --> echec lors du test 12",
					tmp.getPrefix().compareTo("o") == 0);
			assertTrue("HybrideTest.testAdd --> echec lors du test 12",
					tmp.getPrevious() == null);
			assertTrue("HybrideTest.testAdd --> echec lors du test 12",
					tmp.getSon() != null);
			assertTrue("HybrideTest.testAdd --> echec lors du test 12",
					tmp.getNext() == null);

			tmp = tmp.getSon();
			assertTrue("HybrideTest.testAdd --> echec lors du test 13", tmp.isWord());
			assertTrue("HybrideTest.testAdd --> echec lors du test 13",
					tmp.getPrefix().compareTo("n") == 0);
			assertTrue("HybrideTest.testAdd --> echec lors du test 13",
					tmp.getPrevious() == null);
			assertTrue("HybrideTest.testAdd --> echec lors du test 13",
					tmp.getSon() != null);
			assertTrue("HybrideTest.testAdd --> echec lors du test 13",
					tmp.getNext() == null);

			tmp = tmp.getSon();
			assertTrue("HybrideTest.testAdd --> echec lors du test 14", !tmp.isWord());
			assertTrue("HybrideTest.testAdd --> echec lors du test 14",
					tmp.getPrefix().compareTo("j") == 0);
			assertTrue("HybrideTest.testAdd --> echec lors du test 14",
					tmp.getPrevious() == null);
			assertTrue("HybrideTest.testAdd --> echec lors du test 14",
					tmp.getSon() != null);
			assertTrue("HybrideTest.testAdd --> echec lors du test 14",
					tmp.getNext() != null);

			tmp = tmp.getSon();
			assertTrue("HybrideTest.testAdd --> echec lors du test 15", !tmp.isWord());
			assertTrue("HybrideTest.testAdd --> echec lors du test 15",
					tmp.getPrefix().compareTo("o") == 0);
			assertTrue("HybrideTest.testAdd --> echec lors du test 15",
					tmp.getPrevious() == null);
			assertTrue("HybrideTest.testAdd --> echec lors du test 15",
					tmp.getSon() != null);
			assertTrue("HybrideTest.testAdd --> echec lors du test 15",
					tmp.getNext() == null);

			tmp = tmp.getSon();
			assertTrue("HybrideTest.testAdd --> echec lors du test 16", !tmp.isWord());
			assertTrue("HybrideTest.testAdd --> echec lors du test 16",
					tmp.getPrefix().compareTo("u") == 0);
			assertTrue("HybrideTest.testAdd --> echec lors du test 16",
					tmp.getPrevious() == null);
			assertTrue("HybrideTest.testAdd --> echec lors du test 16",
					tmp.getSon() != null);
			assertTrue("HybrideTest.testAdd --> echec lors du test 16",
					tmp.getNext() == null);

			tmp = tmp.getSon();
			assertTrue("HybrideTest.testAdd --> echec lors du test 17", tmp.isWord());
			assertTrue("HybrideTest.testAdd --> echec lors du test 17",
					tmp.getPrefix().compareTo("r") == 0);
			assertTrue("HybrideTest.testAdd --> echec lors du test 17",
					tmp.getPrevious() == null);
			assertTrue("HybrideTest.testAdd --> echec lors du test 17",
					tmp.getSon() != null);
			assertTrue("HybrideTest.testAdd --> echec lors du test 17",
					tmp.getNext() == null);

			tmp = tmp.getSon();
			assertTrue("HybrideTest.testAdd --> echec lors du test 18", !tmp.isWord());
			assertTrue("HybrideTest.testAdd --> echec lors du test 18",
					tmp.getPrefix().compareTo("!") == 0);
			assertTrue("HybrideTest.testAdd --> echec lors du test 18",
					tmp.getPrevious() == null);
			assertTrue("HybrideTest.testAdd --> echec lors du test 18",
					tmp.getSon() != null);
			assertTrue("HybrideTest.testAdd --> echec lors du test 18",
					tmp.getNext() == null);

			tmp = tmp.getSon();
			assertTrue("HybrideTest.testAdd --> echec lors du test 19", !tmp.isWord());
			assertTrue("HybrideTest.testAdd --> echec lors du test 19",
					tmp.getPrefix().compareTo("!") == 0);
			assertTrue("HybrideTest.testAdd --> echec lors du test 19",
					tmp.getPrevious() == null);
			assertTrue("HybrideTest.testAdd --> echec lors du test 19",
					tmp.getSon() != null);
			assertTrue("HybrideTest.testAdd --> echec lors du test 19",
					tmp.getNext() == null);

			tmp = tmp.getSon();
			assertTrue("HybrideTest.testAdd --> echec lors du test 20", tmp.isWord());
			assertTrue("HybrideTest.testAdd --> echec lors du test 20",
					tmp.getPrefix().compareTo("!") == 0);
			assertTrue("HybrideTest.testAdd --> echec lors du test 20",
					tmp.getPrevious() == null);
			assertTrue("HybrideTest.testAdd --> echec lors du test 20",
					tmp.getSon() == null);
			assertTrue("HybrideTest.testAdd --> echec lors du test 20",
					tmp.getNext() == null);

		}catch(Exception e){
			fail("HybrideTest.testAdd --> " + e.getMessage());			
		}
	}

	@Test
	public void testRecherche(){

		/* test les captures d'exceptions */
		try {
			trie.rechercher("");
			fail("HybrideTest.testRechercher: BadArgumentException non capture pour le mot vide");
		} catch (BadArgumentException e) {}

		try {
			trie.rechercher("hello" + PatriciaTrie.EPSILON);
			fail("HybrideTest.testRechercher: BadArgumentException non capture pour un mot contenant EPSILON" );
		} catch (BadArgumentException e) {}

		try {
			trie.rechercher("hello" + PatriciaTrie.EPSILON + "toto");
			fail("HybrideTest.testRechercher: BadArgumentException non capture pour un mot contenant EPSILON" );
		} catch (BadArgumentException e) {}


		try{
			/* verifie que tous les mots sont trouvable */
			assertTrue("HybrideTest.testRechercher --> echec lors du test 1", trie.rechercher("bonjour"));
			assertTrue("HybrideTest.testRechercher --> echec lors du test 2", trie.rechercher("bonsoir"));
			assertTrue("HybrideTest.testRechercher --> echec lors du test 3", trie.rechercher("bonne"));
			assertTrue("HybrideTest.testRechercher --> echec lors du test 4", trie.rechercher("bon"));
			assertTrue("HybrideTest.testRechercher --> echec lors du test 5", trie.rechercher("sale"));
			assertTrue("HybrideTest.testRechercher --> echec lors du test 6", trie.rechercher("salut"));
			assertTrue("HybrideTest.testRechercher --> echec lors du test 7", trie.rechercher("bonnenuit"));

			/* verifie que la methode ne trouve pas des mots inexistant */
			assertFalse("HybrideTest.testRechercher --> echec lors du test 8", trie.rechercher("sal"));
			assertFalse("HybrideTest.testRechercher --> echec lors du test 9", trie.rechercher("bonjour!!"));
			assertFalse("HybrideTest.testRechercher --> echec lors du test 10", trie.rechercher("nice"));
			assertFalse("HybrideTest.testRechercher --> echec lors du test 11", trie.rechercher("bonok"));

		} catch (Exception e) {
			fail("HybrideTest.testRechercher -> " + e.getMessage());
		}
	}

	@Test
	public void testComptageMot(){
		assertTrue("HybrideTest.testComptageMot --> echec lors du test", trie.comptageMot() == 7);
	}

	@Test
	public void testComptageNil(){
		assertTrue("HybrideTest.testComptageNil --> echec lors du test", trie.comptageNil() == 47);
	}

	@Test
	public void testListeMot(){
		ArrayList<String> res = trie.listeMot();
		assertTrue("HybrideTest.testListeMot --> echec lors du test 1",
				res.get(0).compareTo("bon") == 0);
		assertTrue("HybrideTest.testListeMot --> echec lors du test 2",
				res.get(1).compareTo("bonjour") == 0);
		assertTrue("HybrideTest.testListeMot --> echec lors du test 3",
				res.get(2).compareTo("bonne") == 0);
		assertTrue("HybrideTest.testListeMot --> echec lors du test 4",
				res.get(3).compareTo("bonnenuit") == 0);
		assertTrue("HybrideTest.testListeMot --> echec lors du test 5",
				res.get(4).compareTo("bonsoir") == 0);
		assertTrue("HybrideTest.testListeMot --> echec lors du test 6",
				res.get(5).compareTo("sale") == 0);
		assertTrue("HybrideTest.testListeMot --> echec lors du test 7",
				res.get(6).compareTo("salut") == 0);
	}

	// TODO: IL FAUT CORRIGER CETTE FONCTION
	/*
	@Test
	public void testPrefixe(){
		// test les captures d'exceptions 
		try {
			trie.prefixe("");
			fail("HybrideTest.testPrefixe: BadArgumentException non capture pour le mot vide");
		} catch (BadArgumentException e) {}

		try {
			trie.prefixe("hello" + PatriciaTrie.EPSILON);
			fail("HybrideTest.testPrefixe: BadArgumentException non capture pour un mot contenant EPSILON" );
		} catch (BadArgumentException e) {}

		try {
			trie.prefixe("hello" + PatriciaTrie.EPSILON + "toto");
			fail("HybrideTest.testPrefixe: BadArgumentException non capture pour un mot contenant EPSILON" );
		} catch (BadArgumentException e) {}

		try{
			assertTrue("HybrideTest.testPrefixe --> echec lors du test 1", trie.prefixe("bonjour") == 1);
			assertTrue("HybrideTest.testPrefixe --> echec lors du test 2", trie.prefixe("bon") == 5);
			assertTrue("HybrideTest.testPrefixe --> echec lors du test 3", trie.prefixe("bonne") == 2);
			assertTrue("HybrideTest.testPrefixe --> echec lors du test 4", trie.prefixe("salu") == 1);
			assertTrue("HybrideTest.testPrefixe --> echec lors du test 5", trie.prefixe("bom") == 0);
		} catch (Exception e) {
			fail("HybrideTest.testPrefixe -> " + e.getMessage());
		}
	}
*/


	@Test
	public void testSuppression(){
		/* test les captures d'exceptions */
		try {
			trie.add("");
			fail("HybrideTest.testSuppression: BadArgumentException non capture pour le mot vide");
		} catch (BadArgumentException e) {}

		try {
			trie.add("hello" + PatriciaTrie.EPSILON);
			fail("HybrideTest.testSuppression: BadArgumentException non capture " 
					+ "pour un mot contenant EPSILON" );
		} catch (BadArgumentException e) {}

		try {
			trie.add("hello" + PatriciaTrie.EPSILON + "toto");
			fail("HybrideTest.testSuppression: BadArgumentException non capture "
					+ "pour un mot contenant EPSILON" );
		} catch (BadArgumentException e) {}

		try{
			HNode tmp = null;
			trie.add("bonyes");
			trie.suppression("bonsoir");

			tmp = trie.getHd().getSon().getSon().getSon().getNext();
			assertTrue("HybrideTest.testSuppression --> echec lors du test 1",!tmp.isWord());
			assertTrue("HybrideTest.testSuppression --> echec lors du test 1",
					tmp.getPrefix().compareTo("n") == 0);
			assertTrue("HybrideTest.testSuppression --> echec lors du test 1",
					tmp.getPrevious() == null);
			assertTrue("HybrideTest.testSuppression --> echec lors du test 1",
					tmp.getSon() != null);
			assertTrue("HybrideTest.testSuppression --> echec lors du test 1",
					tmp.getNext() != null);

			tmp = tmp.getNext();
			assertTrue("HybrideTest.testSuppression --> echec lors du test 2",!tmp.isWord());
			assertTrue("HybrideTest.testSuppression --> echec lors du test 2",
					tmp.getPrefix().compareTo("y") == 0);
			assertTrue("HybrideTest.testSuppression --> echec lors du test 2",
					tmp.getPrevious() == null);
			assertTrue("HybrideTest.testSuppression --> echec lors du test 2",
					tmp.getSon() != null);
			assertTrue("HybrideTest.testSuppression --> echec lors du test 2",
					tmp.getNext() == null);

			trie.suppression("bonnenuit");

			tmp = trie.getHd().getSon().getSon().getSon().getNext();
			assertTrue("HybrideTest.testSuppression --> echec lors du test 3",!tmp.isWord());
			assertTrue("HybrideTest.testSuppression --> echec lors du test 3",
					tmp.getPrefix().compareTo("n") == 0);
			assertTrue("HybrideTest.testSuppression --> echec lors du test 3",
					tmp.getPrevious() == null);
			assertTrue("HybrideTest.testSuppression --> echec lors du test 3",
					tmp.getSon() != null);
			assertTrue("HybrideTest.testSuppression --> echec lors du test 3",
					tmp.getNext() != null);

			tmp = tmp.getNext();
			assertTrue("HybrideTest.testSuppression --> echec lors du test 4",!tmp.isWord());
			assertTrue("HybrideTest.testSuppression --> echec lors du test 4",
					tmp.getPrefix().compareTo("y") == 0);
			assertTrue("HybrideTest.testSuppression --> echec lors du test 4",
					tmp.getPrevious() == null);
			assertTrue("HybrideTest.testSuppression --> echec lors du test 4",
					tmp.getSon() != null);
			assertTrue("HybrideTest.testSuppression --> echec lors du test 4",
					tmp.getNext() == null);

			trie.suppression("bonne");

			tmp = trie.getHd().getSon().getSon().getSon().getNext();
			assertTrue("HybrideTest.testSuppression --> echec lors du test 5",!tmp.isWord());
			assertTrue("HybrideTest.testSuppression --> echec lors du test 5",
					tmp.getPrefix().compareTo("y") == 0);
			assertTrue("HybrideTest.testSuppression --> echec lors du test 5",
					tmp.getPrevious() == null);
			assertTrue("HybrideTest.testSuppression --> echec lors du test 5",
					tmp.getSon() != null);
			assertTrue("HybrideTest.testSuppression --> echec lors du test 5",
					tmp.getNext() == null);

		}catch(Exception e){
			fail("HybrideTest.testSuppression --> " + e.getMessage());			
		}
	}

	@Test
	public void testCloneTrieH(){
		HNode tmp;
		Hybride c = trie.cloneTrieH();


		tmp = c.getHd();
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("b") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() != null);

		tmp = tmp.getSon();
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("o") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("n") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("j") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() != null);

		tmp = tmp.getSon();
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("o") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("u") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("r") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() == null);
		assertTrue(tmp.getNext() == null);

		tmp = c.getHd().getSon().getSon().getSon().getNext();
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("s") == 0);
		assertTrue(tmp.getPrevious() != null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getPrevious();
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("n") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("e") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("n") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("u") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("i") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("t") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() == null);
		assertTrue(tmp.getNext() == null);

		tmp = c.getHd().getSon().getSon().getSon().getNext().getSon();
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("o") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("i") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("r") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() == null);
		assertTrue(tmp.getNext() == null);

		tmp = c.getHd().getNext();
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("s") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("a") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("l") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("e") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() == null);
		assertTrue(tmp.getNext() != null);

		tmp = tmp.getNext();
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("u") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("t") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() == null);
		assertTrue(tmp.getNext() == null);
	}

	@Test
	public void testFusion(){
		HNode tmp;
		Hybride h2 = new Hybride();
		try{
			h2.add("bonok");
			h2.add("bondac");
			h2.add("bonlol");
			h2.add("salade");
			h2.add("sa");
			h2.add("si");
		}catch(BadArgumentException e){
			fail("BadArgumentException levee dans testFusion");
		}


		Hybride f = trie.fusion(h2);

		tmp = f.getHd();
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("b") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() != null);

		tmp = tmp.getSon();
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("o") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("n") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("j") == 0);
		assertTrue(tmp.getPrevious() != null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() != null);
		
		tmp = tmp.getPrevious();
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("d") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("a") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("c") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() == null);
		assertTrue(tmp.getNext() == null);

		
		
		tmp = f.getHd().getSon().getSon().getSon().getSon();
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("o") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("u") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("r") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() == null);
		assertTrue(tmp.getNext() == null);

		tmp = f.getHd().getSon().getSon().getSon().getNext();
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("s") == 0);
		assertTrue(tmp.getPrevious() != null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getPrevious();
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("n") == 0);
		assertTrue(tmp.getPrevious() != null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() != null);

		tmp = tmp.getPrevious();
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("l") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);
		
		tmp = tmp.getSon();
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("o") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("l") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() == null);
		assertTrue(tmp.getNext() == null);
		
		tmp = f.getHd().getSon().getSon().getSon().getNext().getPrevious().getSon();
		assertTrue(tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("e") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("n") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("u") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("i") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("t") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() == null);
		assertTrue(tmp.getNext() == null);

		
		tmp = f.getHd().getSon().getSon().getSon().getNext().getPrevious().getNext();
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("o") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);
		
		tmp = tmp.getSon();
		assertTrue(tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("k") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() == null);
		assertTrue(tmp.getNext() == null);
		
		
		
		tmp = f.getHd().getSon().getSon().getSon().getNext().getSon();
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("o") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("i") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("r") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() == null);
		assertTrue(tmp.getNext() == null);

		tmp = f.getHd().getNext();
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("s") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("a") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() != null);

		tmp = tmp.getSon();
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("l") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("e") == 0);
		assertTrue(tmp.getPrevious() != null);
		assertTrue(tmp.getSon() == null);
		assertTrue(tmp.getNext() != null);
		
		tmp = tmp.getPrevious();
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("a") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);
		
		tmp = tmp.getSon();
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("d") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);
		
		tmp = tmp.getSon();
		assertTrue(tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("e") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() == null);
		assertTrue(tmp.getNext() == null);

		tmp = f.getHd().getNext().getSon().getSon().getSon().getNext();
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("u") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("t") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() == null);
		assertTrue(tmp.getNext() == null);
		
		tmp = f.getHd().getNext().getSon().getNext();
		assertTrue(tmp.isWord());
		assertTrue(tmp.getPrefix().compareTo("i") == 0);
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() == null);
		assertTrue(tmp.getNext() == null);

	}

}
