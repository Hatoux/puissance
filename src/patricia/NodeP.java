package patricia;

import java.util.ArrayList;

import exceptions.WrongAccessException;



public class NodeP {

	private String prefix;
	private NodeP[] tabFils;
	private int nbFils;

	public NodeP(String prefix){
		this.prefix = prefix;
		tabFils = null;
		nbFils = 0;
	}

	public NodeP(){
		this.prefix =null;
		tabFils = null;
		nbFils = 0;
	}

	public NodeP(String prefix,NodeP[] t){
		this.prefix = prefix;
		tabFils = t;
		nbFils = 0;
	}


	/* -------------------- -------------------- -------------------- */ 
	/* -------------------- ---- primitives ---- -------------------- */ 
	public String getPrefix(){ return prefix; }

	public void setPrefix(String newPrefix){ prefix = newPrefix; }

	public void initTabFils() {	tabFils=new NodeP[PatriciaTrie.TAILLE_ALPHABET]; }

	public NodeP[] getTabFils(){ return tabFils; }

	public void setTabFils(NodeP[] t){ tabFils = t; }

	public int getNbFils(){ return nbFils; }

	//TODO voir si cette fonction est necessaire
	public void setNbFils(int n){ nbFils = n; }

	public void incNbFils() { nbFils++; }

	// TODO necessaire?
	public void incNbFils(int i) { nbFils+=i; }

	public boolean isTabFilsNotNull(){ return tabFils != null; }

	//TODO voir si cette fonction est necessaire
	public boolean isUnFilsNotNull(String key) throws WrongAccessException{
		if(isTabFilsNotNull()){
			NodeP[] sons = getTabFils();
			return sons[ key.charAt(0) ] != null;
		}else throw new WrongAccessException("methode getUnFils : Acces a un fils inexistant!");
	}

	public NodeP getUnFils(String key) throws WrongAccessException{
		if(isTabFilsNotNull()){
			NodeP[] sons = getTabFils();
			return sons[ key.charAt(0) ];
		}else throw new WrongAccessException("methode getUnFils : Acces a un fils inexistant!");
	}

	/* -------------------- -------------------- -------------------- */ 
	/* --------------------  fonctions avancees  -------------------- */



	/**
	 * ajoute la chaine de charactere passe en parametre dant le Patricia trie a partir du noeud courant
	 * @param w:String -> chaine de character a rajouter
	 */
	public void addWord(String w){
		String prefixCommun = Ptools.getPrefixCommun(prefix, w);
		int taillePrefixCommun = prefixCommun.length();
		String restePrefix = prefix.substring(taillePrefixCommun);
		String resteMot = w.substring(taillePrefixCommun);

		if(tabFils == null){ // le node n'a pas de fils
			if(prefix.compareTo(w) != 0){ // prefix != w
				/* maj du node courant */
				tabFils = new NodeP[ PatriciaTrie.TAILLE_ALPHABET ];
				prefix = prefixCommun;
				nbFils = 2;
				/* creation + ajout des nodes fils */
				tabFils[ restePrefix.charAt(0) ] = new NodeP(restePrefix);
				tabFils[ resteMot.charAt(0) ] = new NodeP(resteMot);
			}
		}else{
			if(prefix.compareTo(prefixCommun) == 0){ // prefixCommun == prefix
				if(tabFils[ resteMot.charAt(0) ] == null){
					/* maj du node courant */
					nbFils++;
					/* creation + ajout du node fils */
					tabFils[ resteMot.charAt(0) ] = new NodeP(resteMot);
				}else 
					tabFils[ resteMot.charAt(0) ].addWord(resteMot);
			}else{ 
				NodeP[] tmpTab = tabFils; 
				int tmpNbFils = nbFils;
				/* maj du node courant */
				tabFils = new NodeP[ PatriciaTrie.TAILLE_ALPHABET ];
				prefix = prefixCommun;
				nbFils = 2;
				/* creation + ajout des nodes fils */
				tabFils[ restePrefix.charAt(0) ] = new NodeP(restePrefix);
				tabFils[ restePrefix.charAt(0) ].setTabFils(tmpTab);
				tabFils[ restePrefix.charAt(0) ].setNbFils(tmpNbFils);
				tabFils[ resteMot.charAt(0) ] = new NodeP(resteMot);
			}
		}
	}


	/**
	 * determine si une chaine de character est present dant le trie a partir du noeud courant
	 * @param w:String -> chaine rechercher a partir de ce noeud
	 * @return true si le chaine est trouve a partir de ce noeud, false sinon
	 */
	public boolean recherche(String w){
		if(tabFils != null){
			String prefixCommun = Ptools.getPrefixCommun(prefix, w);
			String resteMot = w.substring(prefixCommun.length());
			if(prefix.compareTo(prefixCommun) == 0){
				if(tabFils[ resteMot.charAt(0) ] != null)
					return tabFils[ resteMot.charAt(0) ].recherche(resteMot);
			}
			return false;			
		}else return prefix.compareTo(w) == 0;
	}

	/**
	 * compte le nombre de mots dans le Patricia trie courant
	 * @return int -> le nombre de mots
	 */
	public int comptageMot(){
		if(tabFils != null){
			int compteur = 0;
			for(NodeP n: tabFils)
				if(n != null)
					compteur += n.comptageMot();
			return compteur;
		}
		return 1;
	}

	/**
	 * compte le nombre de pointeur vers null dans le Patricia trie courant
	 * @return int -> le nombre de pointeur vers null
	 */
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



	/**
	 * methode qui donne le nombre de mots presents commencant par le prefixe passe en parametre
	 * @param w:String -> prefixe
	 * @return int -> le nombre de mots presents commencant par w
	 */
	public int prefixe(String w){
		String prefixCommun = Ptools.getPrefixCommun(prefix, w);
		String resteMot = w.substring(prefixCommun.length());
		if(tabFils != null){
			if(prefixCommun.compareTo(w) == 0)
				return comptageMot();
			else if(tabFils[ resteMot.charAt(0) ] != null)
				return tabFils[ resteMot.charAt(0) ].prefixe(resteMot);
		}else if(prefixCommun.compareTo(w) == 0) 
			return 1;
		return 0;
	}


	public boolean deleteWord(String w){
		if(prefix.compareTo(w)==0) {
			return true;
		}

		if(prefix.length()>w.length()) {
			return false;
		}

		int i;
		int min = w.length()<prefix.length()? w.length() : prefix.length();
		for(i=0;i<min && prefix.charAt(i)==w.charAt(i);i++) {} 	
		String s = w.substring(i);
		if(tabFils[s.charAt(0)]!=null) {//inutile normalement
			boolean b=tabFils[s.charAt(0)].deleteWord(s);
			if(b) {
				if(nbFils==2) {
					for(i=0;i<PatriciaTrie.TAILLE_ALPHABET;i++)
						if(tabFils[i]!=null && i!=s.charAt(0))
							break;
					prefix+=tabFils[i].prefix;
					tabFils=tabFils[i].tabFils;
					nbFils--;
					return false;
				}
				else {
					tabFils[s.charAt(0)]=null;
					nbFils--;
					return false;
				}
			}
			else {
				return false;
			}


		}
		else
			return false;

	}

	

	/* -------------------- -------------------- -------------------- */ 
	/* -------------------- -------------------- -------------------- */ 

	
	
	public void toString(ArrayList<String>ls, String s) {
		if(prefix.charAt(prefix.length()-1)==PatriciaTrie.EPSILON.charAt(0)) {
			if(prefix.length()>1)
				s=s+(prefix.substring(0,prefix.length()-1));
			ls.add(s);
		}
		else {
			s=s+prefix;
			if(tabFils!=null) {
				for(int i=0;i<tabFils.length;i++){
					if(tabFils[i]!=null)
						tabFils[i].toString(ls, s);
				}
			}
		}
	}

	public void toString2(ArrayList<String>ls, StringBuilder s) {
		if(prefix.charAt(prefix.length()-1)==PatriciaTrie.EPSILON.charAt(0)) {
			if(prefix.length()>1)
				s.append(prefix.substring(0,prefix.length()-1));
			ls.add(s.toString());
		}
		else {
			s.append(prefix);
			if(tabFils!=null) {
				for(int i=0;i<tabFils.length;i++){
					if(tabFils[i]!=null)
						tabFils[i].toString2(ls, new StringBuilder (s));
				}
			}
		}
	}
	
	

	public int hauteur() {
		int r = 0;
		if(tabFils!=null)
			for(int i=0;i<tabFils.length;i++)
				if(tabFils[i]!=null)
					r=r<tabFils[i].hauteur()?tabFils[i].hauteur():r;
					r++;
					return r;
	}

	private void fusionFils(NodeP n) {
		if(tabFils!=null) {
			if(n.tabFils!=null) {
				for(int i=0;i<PatriciaTrie.TAILLE_ALPHABET;i++) {
					if(tabFils[i]!=null) {
						if(n.tabFils[i]!=null) {
							tabFils[i].fusion(n.tabFils[i]);
						}
					}
					else {
						if(n.tabFils[i]!=null){
							tabFils[i]=n.tabFils[i];
							nbFils++;
						}
					}
				}
			}
		}
		else {
			if(n.tabFils!=null) {
				tabFils=n.tabFils;
				nbFils=n.nbFils;
			}
		}		
	}

	private void fusionFilsNoeud(NodeP n) {
		if(tabFils==null) {
			tabFils=new NodeP[PatriciaTrie.TAILLE_ALPHABET];
			tabFils[n.prefix.charAt(0)]=n;
			nbFils=1;
			return; 
		}

		if(tabFils[n.prefix.charAt(0)]!=null){
			tabFils[n.prefix.charAt(0)].fusion(n);
			return;
		}

		tabFils[n.prefix.charAt(0)]=n;
		nbFils++;
		return;

	}

	private void swapContent(NodeP n) {
		String tmpPrefix=prefix;
		int tmpNbFils=nbFils;
		NodeP[] tmpFils=tabFils;				
		prefix=n.prefix;
		nbFils=n.nbFils;
		tabFils=n.tabFils;
		n.prefix=tmpPrefix;
		n.nbFils=tmpNbFils;
		n.tabFils=tmpFils;
	}
	public void fusion(NodeP n){

		int i,min;
		min=n.prefix.length()<prefix.length()?n.prefix.length():prefix.length();
		for(i=0;i<min && prefix.charAt(i)==n.prefix.charAt(i);i++){}
		int compare=prefix.compareTo(n.prefix); 
		if(i==min) {
			if(compare==0) {
				fusionFils(n);
			}
			if(compare<0) {

				n.prefix=n.prefix.substring(min);
				fusionFilsNoeud(n);
			}
			if(compare>0) {
				swapContent(n);
				n.prefix=n.prefix.substring(min);
				fusionFilsNoeud(n);

			}
		}
		else {
			min=i;

			if(prefix.length()>n.prefix.length()) 
				swapContent(n);
			NodeP nf=new NodeP(prefix.substring(min));
			nf.nbFils=nbFils;
			nf.tabFils=tabFils;
			n.prefix=n.prefix.substring(min);
			prefix=prefix.substring(0,min);
			nbFils=2;
			tabFils= new NodeP[PatriciaTrie.TAILLE_ALPHABET];
			tabFils[n.prefix.charAt(0)]=n;
			tabFils[nf.prefix.charAt(0)]=nf;

		}
	}


	public NodeP clone() {
		NodeP result=new NodeP(prefix);
		result.nbFils=0;
		if(tabFils!=null) {
			result.tabFils=new NodeP[PatriciaTrie.TAILLE_ALPHABET];
			for(int i=0;i<PatriciaTrie.TAILLE_ALPHABET;i++) {
				if(tabFils[i]!=null) {
					result.tabFils[i]=tabFils[i].clone();
					result.nbFils++;
				}
			}
		}
		return result;
	}
	
	/* -------------------- -------------------- -------------------- */ 
	/* --------------------  ----- autres -----  -------------------- */


	/**
	 * affiche le Patricia trie a partir du noeud courant
	 * @param h:int -> entier permettant d'indenter l'affichage
	 */
	public void showNode(int h){
		for(int i=0; i<h; i++) System.out.print("     ");
		System.out.print(prefix);
		if(tabFils != null) {
			System.out.println(" (" + nbFils + ")->");
			for(NodeP n: tabFils) 
				if(n != null){
					n.showNode(h + 1);
					System.out.println();
				}
		}
	}



}




