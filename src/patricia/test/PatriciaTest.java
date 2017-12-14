package patricia.test;

import org.junit.Test;

import exceptions.BadArgumentException;
import hybride.HNode;
import hybride.Hybride;
import junit.framework.TestCase;
import patricia.NodeP;
import patricia.PatriciaTrie;

public class PatriciaTest extends TestCase {

	private PatriciaTrie trie;

	public PatriciaTest(String methodeName) {
		super(methodeName);
	}

	public void setUp(){
		trie = new PatriciaTrie();
		try{
			trie.addWord("bonjour");
			trie.addWord("bonsoir");
			trie.addWord("bonne");
			trie.addWord("bon");
			trie.addWord("sale");
			trie.addWord("salut");
			trie.addWord("bonnenuit");
		}catch(BadArgumentException e){
			fail("BadArgumentException levee dans setUp");
		}
	}

	@Test
	public void testTrie(){
		NodeP tmp;
		assertTrue(trie.getNbFils() == 2);
		try {
			tmp = trie.getTabFils()['b'];

			assertTrue(tmp.getPrefix().compareTo("bon") == 0);
			assertTrue(tmp.getNbFils() == 4);

			assertTrue(tmp.getUnFils(PatriciaTrie.EPSILON).getPrefix().compareTo(PatriciaTrie.EPSILON) == 0);
			assertTrue(tmp.getUnFils(PatriciaTrie.EPSILON).getNbFils() == 0);

			assertTrue(tmp.getUnFils("j").getPrefix().compareTo("jour" + PatriciaTrie.EPSILON) == 0);
			assertTrue(tmp.getUnFils("j").getNbFils() == 0);

			assertTrue(tmp.getUnFils("s").getPrefix().compareTo("soir" + PatriciaTrie.EPSILON) == 0);
			assertTrue(tmp.getUnFils("s").getNbFils() == 0);

			tmp = tmp.getUnFils("n");
			assertTrue(tmp.getPrefix().compareTo("ne") == 0);
			assertTrue(tmp.getNbFils() == 2);

			assertTrue(tmp.getUnFils(PatriciaTrie.EPSILON).getPrefix().compareTo(PatriciaTrie.EPSILON) == 0);
			assertTrue(tmp.getUnFils(PatriciaTrie.EPSILON).getNbFils() == 0);

			assertTrue(tmp.getUnFils("n").getPrefix().compareTo("nuit" + PatriciaTrie.EPSILON) == 0);
			assertTrue(tmp.getUnFils("n").getNbFils() == 0);

			tmp = trie.iemefils('s');
			assertTrue(tmp.getPrefix().compareTo("sal") == 0);
			assertTrue(tmp.getNbFils() == 2);

			assertTrue(tmp.getUnFils("e").getPrefix().compareTo("e" + PatriciaTrie.EPSILON) == 0);
			assertTrue(tmp.getUnFils("e").getNbFils() == 0);

			assertTrue(tmp.getUnFils("u").getPrefix().compareTo("ut" + PatriciaTrie.EPSILON) == 0);
			assertTrue(tmp.getUnFils("u").getNbFils() == 0);

		}catch(Exception e){
			fail("testTrie");
		}


	}

	@Test
	public void testAddWord(){
		NodeP tmp = null;

		/* test les captures d'exceptions */
		try {
			trie.addWord("");
			fail("PatriciaTest.testAddWord: BadArgumentException non capture pour le mot vide");
		} catch (BadArgumentException e) {}

		try {
			trie.addWord("hello" + PatriciaTrie.EPSILON);
			fail("PatriciaTest.testAddWord: BadArgumentException non capture pour un mot contenant EPSILON" );
		} catch (BadArgumentException e) {}

		try {
			trie.addWord("hello" + PatriciaTrie.EPSILON + "toto");
			fail("PatriciaTest.testAddWord: BadArgumentException non capture pour un mot contenant EPSILON" );
		} catch (BadArgumentException e) {}

		try {
			trie.addWord("bonn!!");

			tmp = trie.iemefils('b').getUnFils("n");
			assertTrue("PatriciaTest.addWord --> echec lors du test 1",
					tmp.getPrefix().compareTo("n") == 0);
			assertTrue("PatriciaTest.addWord --> echec lors du test 2",
					tmp.getNbFils() == 2);

			assertTrue("PatriciaTest.addWord --> echec lors du test 3",
					tmp.getUnFils("!").getPrefix().compareTo("!!" + PatriciaTrie.EPSILON) == 0);
			assertTrue("PatriciaTest.addWord --> echec lors du test 4",
					tmp.getUnFils("!").getNbFils() == 0);

			tmp = tmp.getUnFils("e");
			assertTrue("PatriciaTest.addWord --> echec lors du test 5",
					tmp.getPrefix().compareTo("e") == 0);
			assertTrue("PatriciaTest.addWord --> echec lors du test 6",
					tmp.getNbFils() == 2);

			assertTrue("PatriciaTest.addWord --> echec lors du test 7",
					tmp.getUnFils(PatriciaTrie.EPSILON).getPrefix().compareTo(PatriciaTrie.EPSILON) == 0);
			assertTrue("PatriciaTest.addWord --> echec lors du test 8",
					tmp.getUnFils(PatriciaTrie.EPSILON).getNbFils() == 0);

			assertTrue("PatriciaTest.addWord --> echec lors du test 9",
					tmp.getUnFils("n").getPrefix().compareTo("nuit" + PatriciaTrie.EPSILON) == 0);
			assertTrue("PatriciaTest.addWord --> echec lors du test 10",
					tmp.getUnFils("n").getNbFils() == 0);

		} catch (Exception e) {
			fail("PatriciaTest.testAddWord --> " + e.getMessage());
		}
		
		/* TODO ajout d un mot deja existant */
	}

	@Test
	public void testRecherche(){

		/* test les captures d'exceptions */
		try {
			trie.recherche("");
			fail("PatriciaTest.testRecherche: BadArgumentException non capture pour le mot vide");
		} catch (BadArgumentException e) {}

		try {
			trie.recherche("hello" + PatriciaTrie.EPSILON);
			fail("PatriciaTest.testRecherche: BadArgumentException non capture pour un mot contenant EPSILON" );
		} catch (BadArgumentException e) {}

		try {
			trie.recherche("hello" + PatriciaTrie.EPSILON + "toto");
			fail("PatriciaTest.testRecherche: BadArgumentException non capture pour un mot contenant EPSILON" );
		} catch (BadArgumentException e) {}

		
		try{
			/* verifie que tous les mots sont trouvable */
			assertTrue("PatriciaTest.recherche --> echec lors du test 1", trie.recherche("bonjour"));
			assertTrue("PatriciaTest.recherche --> echec lors du test 2", trie.recherche("bonsoir"));
			assertTrue("PatriciaTest.recherche --> echec lors du test 3", trie.recherche("bonne"));
			assertTrue("PatriciaTest.recherche --> echec lors du test 4", trie.recherche("bon"));
			assertTrue("PatriciaTest.recherche --> echec lors du test 5", trie.recherche("sale"));
			assertTrue("PatriciaTest.recherche --> echec lors du test 6", trie.recherche("salut"));
			assertTrue("PatriciaTest.recherche --> echec lors du test 7", trie.recherche("bonnenuit"));
			
			/* verifie que la methode ne trouve pas des mots inexistant */
			assertFalse("PatriciaTest.recherche --> echec lors du test 8", trie.recherche("sal"));
			assertFalse("PatriciaTest.recherche --> echec lors du test 9", trie.recherche("bonjour!!"));
			assertFalse("PatriciaTest.recherche --> echec lors du test 10", trie.recherche("nice"));
			assertFalse("PatriciaTest.recherche --> echec lors du test 11", trie.recherche("bonok"));
			
		} catch (Exception e) {
			fail("PatriciaTest.testRecherche -> " + e.getMessage());
		}
	}
		
	@Test
	public void testComptageMot(){
		assertTrue("PatriciaTest.comptageMot --> echec lors du test", trie.comptageMot() == 7);
	}
	
	@Test
	public void testComptageNil(){
		assertTrue("PatriciaTest.comptageNil --> echec lors du test", trie.comptageNil() == 502);
	}
	
	@Test
	public void testPrefixe(){
		/* test les captures d'exceptions */
		try {
			trie.prefixe("");
			fail("PatriciaTest.testPrefixe: BadArgumentException non capture pour le mot vide");
		} catch (BadArgumentException e) {}

		try {
			trie.prefixe("hello" + PatriciaTrie.EPSILON);
			fail("PatriciaTest.testPrefixe: BadArgumentException non capture pour un mot contenant EPSILON" );
		} catch (BadArgumentException e) {}

		try {
			trie.prefixe("hello" + PatriciaTrie.EPSILON + "toto");
			fail("PatriciaTest.testPrefixe: BadArgumentException non capture pour un mot contenant EPSILON" );
		} catch (BadArgumentException e) {}
		
		try{
			assertTrue("PatriciaTest.prefixe --> echec lors du test 1", trie.prefixe("bonjour") == 1);
			assertTrue("PatriciaTest.prefixe --> echec lors du test 2", trie.prefixe("bon") == 5);
			assertTrue("PatriciaTest.prefixe --> echec lors du test 3", trie.prefixe("bonne") == 2);
			assertTrue("PatriciaTest.prefixe --> echec lors du test 4", trie.prefixe("salu") == 1);
			assertTrue("PatriciaTest.prefixe --> echec lors du test 5", trie.prefixe("bom") == 0);
		} catch (Exception e) {
			fail("PatriciaTest.testPrefixe -> " + e.getMessage());
		}
	}
	
	
	@Test
	public void testToHybride(){
		HNode tmp = null;
		Hybride h = trie.toHybride();
		
		tmp = h.getHd();
		assertTrue(tmp.getPrefix().charAt(0) == 's');
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrevious() != null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);
		
		tmp = tmp.getPrevious();
		assertTrue(tmp.getPrefix().charAt(0) == 'b');
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);
		
		tmp = tmp.getSon();
		assertTrue(tmp.getPrefix().charAt(0) == 'o');
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);
		
		tmp = tmp.getSon();
		assertTrue(tmp.getPrefix().charAt(0) == 'n');
		assertTrue(tmp.isWord());
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);
		
		tmp = tmp.getSon();
		assertTrue(tmp.getPrefix().charAt(0) == 'n');
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrevious() != null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() != null);
		
		tmp = tmp.getPrevious();
		assertTrue(tmp.getPrefix().charAt(0) == 'j');
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(tmp.getPrefix().charAt(0) == 'o');
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);
		
		tmp = tmp.getSon();
		assertTrue(tmp.getPrefix().charAt(0) == 'u');
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(tmp.getPrefix().charAt(0) == 'r');
		assertTrue(tmp.isWord());
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() == null);
		assertTrue(tmp.getNext() == null);
		
		tmp = h.getHd().getPrevious().getSon().getSon().getSon().getSon();
		assertTrue(tmp.getPrefix().charAt(0) == 'e');
		assertTrue(tmp.isWord());
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(tmp.getPrefix().charAt(0) == 'n');
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);
		
		tmp = tmp.getSon();
		assertTrue(tmp.getPrefix().charAt(0) == 'u');
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(tmp.getPrefix().charAt(0) == 'i');
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(tmp.getPrefix().charAt(0) == 't');
		assertTrue(tmp.isWord());
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() == null);
		assertTrue(tmp.getNext() == null);

		tmp = h.getHd().getPrevious().getSon().getSon().getSon().getNext();
		assertTrue(tmp.getPrefix().charAt(0) == 's');
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);
		
		tmp = tmp.getSon();
		assertTrue(tmp.getPrefix().charAt(0) == 'o');
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);
		
		tmp = tmp.getSon();
		assertTrue(tmp.getPrefix().charAt(0) == 'i');
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(tmp.getPrefix().charAt(0) == 'r');
		assertTrue(tmp.isWord());
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() == null);
		assertTrue(tmp.getNext() == null);

		tmp = h.getHd().getSon();
		assertTrue(tmp.getPrefix().charAt(0) == 'a');
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getSon();
		assertTrue(tmp.getPrefix().charAt(0) == 'l');
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp =tmp.getSon();
		assertTrue(tmp.getPrefix().charAt(0) == 'u');
		assertTrue(!tmp.isWord());
		assertTrue(tmp.getPrevious() != null);
		assertTrue(tmp.getSon() != null);
		assertTrue(tmp.getNext() == null);

		tmp = tmp.getPrevious();
		assertTrue(tmp.getPrefix().charAt(0) == 'e');
		assertTrue(tmp.isWord());
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() == null);
		assertTrue(tmp.getNext() == null);

		tmp = h.getHd().getSon().getSon().getSon().getSon();
		assertTrue(tmp.getPrefix().charAt(0) == 't');
		assertTrue(tmp.isWord());
		assertTrue(tmp.getPrevious() == null);
		assertTrue(tmp.getSon() == null);
		assertTrue(tmp.getNext() == null);
		
	}
	
	/* TODO: test de autres methodes */
	
	/* TODO: test des primitives */


}
