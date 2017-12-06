/* 
 TODO: Pour rapport
 _ preciser pour addWord le fait qu'on verifie qu'il n'y a pas EPSILON à l'interieur du mot
 _ preciser pour addWord le cheat de rajouter EPSILON a la fin du mot

 */


package patricia;

import java.util.ArrayList;

import exceptions.BadArgumentException;

public class PatriciaTrie {

	public static int TAILLE_ALPHABET = 128;
	public static final String EPSILON = "_";



	private NodeP[] tabFils;
	private int nbFils;
	


	
	public PatriciaTrie(){
		tabFils = null;
		nbFils = 0;
	}

	public NodeP[] getTabFils() {
		return tabFils;
	}


	public void setTabFils(NodeP[] tabFils) {
		this.tabFils = tabFils;
	}


	public void setNbFils(int nbFils) {
		this.nbFils = nbFils;
	}

	public void incNbFils() {
		nbFils++;
	}
    
	public void initFils() {
		tabFils=new NodeP[TAILLE_ALPHABET];
	}
	
	public NodeP filsAIndex(int i) {
		return tabFils[i];
	}


	/* ---------- a ajouter ---------- */

	public int getNbFils(){ return nbFils; }

	/**
	 * ajoute une chaine de charactere dans le Patricia trie
	 * @param w:String -> mot a rajouter
	 * @throws BadArgumentException -> si w ne peut etre un mot du dictionnaire
	 */
	public void addWord(String w) throws BadArgumentException{
		/* verifier que le mot est correcte */
		for(int i=0; i<w.length(); i++){
			if(w.length() == 0)
				throw new BadArgumentException("PatriciaTrie.addWord: w est une chaine vide");
			else if(w.charAt(i) == EPSILON.charAt(0))
				throw new BadArgumentException("PatriciaTrie.addWord: w contient le caractere EPSILON");
		}

		/* ajout d'EPSILON a la fin du mot */
		String word = w.concat(EPSILON);
		//		System.out.println("ajout du mot: " + w); // TODO print

		if(tabFils == null){
			tabFils = new NodeP[ TAILLE_ALPHABET ];

			tabFils[ word.charAt(0) ] = new NodeP(word);
			nbFils=1;
		}else if(tabFils[ word.charAt(0) ] == null){
			tabFils[ word.charAt(0) ] = new NodeP(word);
			nbFils++;
		}else
			tabFils[ word.charAt(0) ].addWord(word);
	}

	/**
	 * determine si le mot est present dans le Patricia trie
	 * @param w:String -> mot a rechercher
	 * @return true si w est un mot du Patricia trie, false sinon
	 * @throws BadArgumentException -> si w ne peut etre un mot du dictionnaire
	 */
	public boolean recherche(String w) throws BadArgumentException{
		/* verifier que le mot est correcte */
		for(int i=0; i<w.length(); i++){
			if(w.length() == 0)
				throw new BadArgumentException("PatriciaTrie.addWord: w est une chaine vide");
			else if(w.charAt(i) == EPSILON.charAt(0))
				throw new BadArgumentException("PatriciaTrie.addWord: w contient le caractere EPSILON");
		}
		/* ajout d'EPSILON a la fin du mot */
		String word = w.concat(EPSILON);

		if(tabFils != null){
			if(tabFils[ word.charAt(0) ] != null) 
				return tabFils[ word.charAt(0) ].recherche(word);
			return false;
		}
		return false;
	}


	/**
	 * compte le nombre de mots dans le Patricia trie courant
	 * @return le nombre de mots
	 */
	public int comptageMot(){
		if(tabFils != null){
			int compteur = 0;
			for(NodeP n: tabFils)
				if(n != null)
					compteur += n.comptageMot();
			return compteur;
		}
		return 0;
	}


	public int prefixe(String w) throws BadArgumentException{
		/* verifier que le mot est correcte */
		for(int i=0; i<w.length(); i++){
			if(w.length() == 0)
				throw new BadArgumentException("PatriciaTrie.addWord: w est une chaine vide");
			else if(w.charAt(i) == EPSILON.charAt(0))
				throw new BadArgumentException("PatriciaTrie.addWord: w contient le caractere EPSILON");
		}

		if(tabFils != null){
			if(tabFils[ w.charAt(0) ] != null) 
				return tabFils[ w.charAt(0) ].prefixe(w);
		}
		return 0;
	}



	/**
	 * permet de visualiser chaque noeud du Patricia trie
	 */
	public void showTrie(){
		System.out.print("\"\"" );
		if(tabFils != null){
			System.out.println(" (" + nbFils + ")->");
			for(NodeP n: tabFils) 
				if(n != null) n.showNode(1);

		}
	}

	public void deleteWord(String w) throws BadArgumentException{
		/* verifier que le mot est correcte */
		for(int i=0; i<w.length(); i++){
			if(w.length() == 0)
				throw new BadArgumentException("PatriciaTrie.addWord: w est une chaine vide");
			else if(w.charAt(i) == EPSILON.charAt(0))
				throw new BadArgumentException("PatriciaTrie.addWord: w contient le caractere EPSILON");
		}

		/* ajout d'EPSILON a la fin du mot */
		String word = w.concat(EPSILON);
		boolean b=false;
		if(tabFils[ word.charAt(0) ] != null)
			b=tabFils[ word.charAt(0) ].deleteWord(word);
		if(b) {
			tabFils[ word.charAt(0) ]=null;
			nbFils--;
		}
	}

	public String toString() {
		ArrayList<String> lr=listeMot();
		String r="";
		for(String s : lr)
			r+=s+"\n";
		return r;
	}

	public ArrayList<String> listeMot(){
		ArrayList<String> lr=new ArrayList<>();
		if(tabFils!=null) {
			for(int i=0;i<tabFils.length;i++) {
				if(tabFils[i]!=null) {
					//				System.out.println(tabFils[i]);
					tabFils[i].toString(lr, "");
				}
			}
		}
		return lr;
	}

	public ArrayList<String> listeMot2(){
		ArrayList<String> lr=new ArrayList<>();
		if(tabFils!=null) {
			for(int i=0;i<tabFils.length;i++) {
				if(tabFils[i]!=null) {
										tabFils[i].toString2(lr, new StringBuilder(""));
				}
			}
		}
		return lr;
	}
	public int comptageNil(){
		int r = 0;
		if(tabFils!=null)
			for(int i=0;i<tabFils.length;i++)
				if(tabFils[i]!=null) 
					r+=tabFils[i].comptageNil();
				else
					r++;
		return r;
	}

	public int hauteur() {
		int r = 0;
		if(tabFils!=null)
			for(int i=0;i<tabFils.length;i++)
				if(tabFils[i]!=null)
					r=r<tabFils[i].hauteur()?tabFils[i].hauteur():r;
					return r;
	}

	public double profondeurMoyenne() {
		double r = 0;
		double j=0;
		if(tabFils!=null) 
			for(int i=0;i<tabFils.length;i++)
				if(tabFils[i]!=null) {
					r+=tabFils[i].hauteur();
					j++;
				}


		return r/j;
	}

	public PatriciaTrie clone() {
		PatriciaTrie result = new PatriciaTrie();
		if(tabFils!=null) {
			result.tabFils= new NodeP[TAILLE_ALPHABET];
			for(int i=0;i<TAILLE_ALPHABET;i++) 
				if(tabFils[i]!=null) {
					result.tabFils[i]=tabFils[i].clone();
					result.nbFils++;
				}
		}
		return result;
	}

	public PatriciaTrie fusion(PatriciaTrie p2){
		PatriciaTrie p1 =clone();
		p2=p2.clone();
		if(p1.tabFils!=null) {
			if(p2.tabFils!=null) {
				for(int i=0;i<TAILLE_ALPHABET;i++){
					if(p1.tabFils[i]!=null){
						if(p2.tabFils[i]!=null) {
							p1.tabFils[i].fusion(p2.tabFils[i]);
						}
					}
					else {
						if(p2.tabFils[i]!=null) {
							p1.tabFils[i]=p2.tabFils[i];
							p1.nbFils++;
						}
					}
				}
			}
		}
		else {
			p1.tabFils=p2.tabFils;
			p1.nbFils=p2.nbFils;
		}
		return p1;
	}

}
