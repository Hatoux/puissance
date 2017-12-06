package patricia;


import exceptions.BadArgumentException;

public class Test {

	

	
	public static void main(String[] args) {

		String s1 = "bonjour";
		String s2 = "bonsoir";
		String s3 = "bonne";
		String s4 = "bon";
		String s5 = "salut";
		String s6 = "sale";
		String s7 = "bonnenuit";

		try {
			PatriciaTrie abr1,abr2,abrFusion;
			abr1 = new PatriciaTrie();
			abr2 = new PatriciaTrie();
			
			abr1.addWord(s1);
			abr1.addWord(s2);
			abr1.addWord(s3);
			abr1.addWord(s4);
			abr2.addWord(s5);
			abr2.addWord(s6);
			abr2.addWord(s7);
			
			abr1.showTrie();
			abr2.showTrie();
			
			
			if(	abr1.recherche(s1) &&
					abr1.recherche(s2) &&
					abr1.recherche(s3) &&
					abr1.recherche(s4) &&
					abr2.recherche(s5) &&
					abr2.recherche(s6) &&
					abr2.recherche(s7))
				System.out.println("TOUS LES MOTS DE L'ARBRE SONT TROUVABLE");
			else
				System.out.println("!!!!!!!!!!! PROBLEME !!!!!!!!!");
			
			
			if(	!abr1.recherche("bo") &&
					!abr1.recherche("bonjourno") &&
					!abr1.recherche("bonok") &&
					!abr1.recherche("bonjeu") &&
					!abr2.recherche("oiiii") &&
					!abr2.recherche("saluu") &&
					!abr2.recherche("cestok") 
					)
				System.out.println("tout a l'air ok");
			else
				System.out.println("!!!!!!!!!!! PROBLEME !!!!!!!!!");
				
			System.out.println("abr1");
			System.out.println( "abr.prefixe(bonjour) = " + abr1.prefixe("bonjour") );
			System.out.println( "abr.prefixe(bon) = " + abr1.prefixe("bon") );
			System.out.println("il y a " + abr1.comptageMot() + " mots dans le Patricia trie");
			System.out.println("hauteur="+abr1.hauteur());
			System.out.println("nbNil="+abr1.comptageNil());
			System.out.println("moyH="+abr1.profondeurMoyenne());
			System.out.println("suppression de: "+s4);
			abr1.deleteWord(s4);
			System.out.println("il y a " + abr1.comptageMot() + " mots dans le Patricia trie");
			abr1.showTrie();
			System.out.println();
			System.out.println("abr2");
			System.out.println( "abr.prefixe(sal) = " + abr2.prefixe("sal") );
			System.out.println( "abr.prefixe(bom) = " + abr2.prefixe("bom") );
			System.out.println("il y a " + abr2.comptageMot() + " mots dans le Patricia trie");
			System.out.println("hauteur="+abr2.hauteur());
			System.out.println("nbNil="+abr2.comptageNil());
			System.out.println("moyH="+abr2.profondeurMoyenne());
			System.out.println("suppression de: "+s7);
			abr2.deleteWord(s7);
			System.out.println("il y a " + abr2.comptageMot() + " mots dans le Patricia trie");
			abr2.showTrie();
			System.out.println();
			System.out.println("abrFusion");
			abrFusion=abr1.fusion(abr2);
			abrFusion.showTrie();
			System.out.println("il y a " + abrFusion.comptageMot() + " mots dans le Patricia trie");
			System.out.println( "abr.prefixe(bon) = " + abrFusion.prefixe("bon") );
			System.out.println( "abr.prefixe(bom) = " + abrFusion.prefixe("bom") );
			System.out.println("hauteur="+abrFusion.hauteur());
			System.out.println("nbNil="+abrFusion.comptageNil());
			System.out.println("moyH="+abrFusion.profondeurMoyenne());
			System.out.println("suppression de: "+s1);
			abrFusion.deleteWord(s1);
			System.out.println("il y a " + abrFusion.comptageMot() + " mots dans le Patricia trie");
			abrFusion.showTrie();
			System.out.println("\nabr2");
			abr2.showTrie();
			System.out.println("\nabr1");
			abr1.showTrie();
		} catch (BadArgumentException e) {
			e.printStackTrace();
		}			

		
	}

}
