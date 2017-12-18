package patricia;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

import exceptions.WrongAccessException;
import hybride.HNode;



public class NodeP {

	private String prefix;
	private NodeP[] tabFils;
	private int nbFils;
	private int id;
	private static int cpt=0;
	
	public NodeP(String prefix){
		this.prefix = prefix;
		tabFils = null;
		nbFils = 0;
		id=cpt++;
	}

	public NodeP(){
		this.prefix =null;
		tabFils = null;
		nbFils = 0;
		id=cpt++;
	}

	public NodeP(String prefix,NodeP[] t){
		this.prefix = prefix;
		tabFils = t;
		nbFils = 0;
		id=cpt++;
	}


	/* -------------------- -------------------- -------------------- */ 
	/* -------------------- ---- primitives ---- -------------------- */ 
	
	public int getId(){ return id; }
	
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



	
	public void deleteWord(String w){
		int i;
		int min = w.length()<prefix.length()? w.length() : prefix.length();
		for(i=0;i<min && prefix.charAt(i)==w.charAt(i);i++) {}
		String s1 = w.substring(0,i);
		if(s1.compareTo(prefix)==0) {
			String s2 = w.substring(i);
			if(tabFils[s2.charAt(0)]!=null) {
				if(tabFils[s2.charAt(0)].prefix.compareTo(s2)==0) {
					if(nbFils==2) {
						for(i=0;i<PatriciaTrie.TAILLE_ALPHABET;i++)
							if(tabFils[i]!=null && i!=s2.charAt(0))
								break;
						prefix+=tabFils[i].prefix;
						nbFils=tabFils[i].nbFils;
						tabFils=tabFils[i].tabFils;
					}
					else {
						tabFils[s2.charAt(0)]=null;
						nbFils--;
					}
				}
				else {
					tabFils[s2.charAt(0)].deleteWord(s2);
				}
			}
		}
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
	/* -------------------- ---- toHybride ----- -------------------- */



	/* legere optimisation : etant donne que les fichiers fournis contiendront en grande 
	 * majorité des lettres minuscules. Le parcourt du tableau de fils des PatriciaTrie 
	 * debutera à partir de tabFils['m']. */
	public static HNode horizontalTraduction(NodeP[] tabFils) throws WrongAccessException{
		HNode tmpH = null, rootLevel = null;

		/* parcourt de tabFils['m'] --> tabFils[PatriciaTrie.TAILLE_ALPHABET -1] */
		for(int i=(int) 'm'; i<PatriciaTrie.TAILLE_ALPHABET;i++)
			if(i != PatriciaTrie.EPSILON.codePointAt(0) && tabFils[i] != null){
				if(rootLevel == null){
					rootLevel = tabFils[i].verticalTraduction();
					tmpH = rootLevel;
				}else{
					tmpH.setNext( tabFils[i].verticalTraduction() );
					tmpH = tmpH.getNext();
				}
			}

		/* parcourt de tabFils['l'] --> tabFils[0] */
		if(rootLevel != null ) tmpH = rootLevel;
		for(int i=(int) 'l';i>=0;i--)
			if(i != PatriciaTrie.EPSILON.codePointAt(0) && tabFils[i] != null){
				if(rootLevel == null){
					rootLevel = tabFils[i].verticalTraduction();
					tmpH = rootLevel;
				}else{
					tmpH.setPrevious( tabFils[i].verticalTraduction() );
					tmpH = tmpH.getPrevious();
				}
			}
		return rootLevel;
	}


	public HNode verticalTraduction() throws WrongAccessException{
		HNode rootLevel = null, tmpH = null;
		int i;

		/* pour forcer horizontalTraduction() a ne pas appemer cette 
		 * methode sur le NodeP EPSILON */
		if(prefix.compareTo(PatriciaTrie.EPSILON) == 0)
			throw new WrongAccessException("PatriciaTrie.verticalTraduction -> " 
					+ "traitement du node EPSILON non traite par cette methode");
		for(i=0; i<prefix.length() - 2; i++){
			if(rootLevel == null){
				rootLevel = new HNode(false, prefix.charAt(i) + "");
				tmpH = rootLevel;
			}else{
				tmpH.setSon( new HNode(false, prefix.charAt(i) + "") );
				tmpH = tmpH.getSon();
			}
		}

		/* i == prefix.length()-2 pour la gestion du PatriciaTrie.EPSILON */
		if(prefix.charAt(i+1) == PatriciaTrie.EPSILON.charAt(0)){
			/* fin de la semi-recursion avec horizontalTraduction() */
			if(rootLevel == null) rootLevel = new HNode(true, prefix.charAt(i) + "");
			else tmpH.setSon( new HNode(true, prefix.charAt(i) + "") );
		}else{
			/* la semi-recursion avec horizontalTraduction() doit continuer */
			if(rootLevel == null){
				rootLevel = new HNode(false, prefix.charAt(i) + "");
				tmpH = rootLevel;
			}else{
				tmpH.setSon(new HNode(false, prefix.charAt(i) + ""));
				tmpH = tmpH.getSon();
			}

			if(tabFils[PatriciaTrie.EPSILON.codePointAt(0)] == null)
				tmpH.setSon(new HNode(false, prefix.charAt(i+1) + ""));
			else /* implique tabFils contient le NodeP EPSILON */
				tmpH.setSon(new HNode(true, prefix.charAt(i+1) + ""));
			tmpH = tmpH.getSon();

			tmpH.setSon( horizontalTraduction(tabFils));
		}
		return rootLevel;
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

	public void writeFile(BufferedWriter writer) throws IOException {
		
		if(tabFils!=null)
		for(int i=0;i<PatriciaTrie.TAILLE_ALPHABET;i++)
			if(tabFils[i]!=null) {
				writer.write("\""+id+" "+prefix+"\" -- \""+tabFils[i].id+" "+tabFils[i].prefix+"\";");
				writer.newLine();
				tabFils[i].writeFile(writer);
			}
}
}




