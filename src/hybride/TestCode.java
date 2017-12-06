package hybride;


import exceptions.BadArgumentException;

public class TestCode {

	

	
	public static void main(String[] args) {

		String s1 = "bonjour";
		String s2 = "bonsoir";
		String s3 = "bonne";
		String s4 = "bon";
		String s5 = "salut";
		String s6 = "sale";
		String s7 = "bonnenuit";

		try {
			Hybride abr = new Hybride();
			abr.add(s1);
			abr.add(s2);
			abr.add(s3);
			abr.add(s4);
			abr.add(s5);
			abr.add(s6);
			abr.add(s7);
			
			//abr.showTrie();
			
			if(	abr.rechercher(s1) &&
					abr.rechercher(s2) &&
					abr.rechercher(s3) &&
					abr.rechercher(s4) &&
					abr.rechercher(s5) &&
					abr.rechercher(s6) &&
					abr.rechercher(s7))
				System.out.println("TOUS LES MOTS DE L'ARBRE SONT TROUVABLE");
			else
				System.out.println("!!!!!!!!!!! PROBLEME 1!!!!!!!!!");
			
			
			if(	!abr.rechercher("bo") &&
					!abr.rechercher("bonjourno") &&
					!abr.rechercher("bonok") &&
					!abr.rechercher("bonjeu") &&
					!abr.rechercher("oiiii") &&
					!abr.rechercher("saluu") &&
					!abr.rechercher("cestok") 
					)
				System.out.println("tout a l'air ok");
			else
				System.out.println("!!!!!!!!!!! PROBLEME 2!!!!!!!!!");
				
			
			System.out.println( "abr.prefixe(c) = " + abr.prefixe("c") );
			System.out.println( "abr.prefixe(bonjour) = " + abr.prefixe("bonjour") );
			System.out.println( "abr.prefixe(bon) = " + abr.prefixe("bon") );
			System.out.println( "abr.prefixe(bam) = " + abr.prefixe("bam") );
			System.out.println( "abr.prefixe(bonjourno) = " + abr.prefixe("c") );
			System.out.println();
			System.out.println("il y a " + abr.comptageMot() + " mots dans le Hybride trie");
//			System.out.println(abr);
//			System.out.println("hauteur="+abr.hauteur());
//			System.out.println("nbNil="+abr.comptageNil());
//			System.out.println("moyH="+abr.moyenneHauteur());
//			System.out.println();
//			System.out.println("suppression de: "+s7);
//			abr.deleteWord(s7);
//			System.out.println("il y a " + abr.comptageMot() + " mots dans le Hybride trie");
//			System.out.println(abr);
//			System.out.println("hauteur="+abr.hauteur());
//			System.out.println("nbNil="+abr.comptageNil());
//			System.out.println("moyH="+abr.moyenneHauteur());
//			System.out.println();
//			System.out.println("suppression de: "+s4);
//			abr.deleteWord(s4);
//			System.out.println("il y a " + abr.comptageMot() + " mots dans le Hybride trie");
//			System.out.println(abr);
//			System.out.println("hauteur="+abr.hauteur());
//			System.out.println("nbNil="+abr.comptageNil());
//			System.out.println("moyH="+abr.moyenneHauteur());
//			System.out.println();
//			System.out.println(abr);
		} catch (BadArgumentException e) {
			e.printStackTrace();
		}			

		
	}

}
