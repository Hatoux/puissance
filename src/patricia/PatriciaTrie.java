/* 
 TODO: Pour rapport
 _ preciser pour addWord le fait qu'on verifie qu'il n'y a pas EPSILON à l'interieur du mot
 _ preciser pour addWord le cheat de rajouter EPSILON a la fin du mot

 */


package patricia;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import exceptions.BadArgumentException;
import exceptions.WrongAccessException;
import hybride.Hybride;
import tools.Tools;

public class PatriciaTrie {

	public static int TAILLE_ALPHABET = 128;
	public static final String EPSILON = "_";

	private NodeP[] tabFils;
	private int nbFils;

	public PatriciaTrie(){
		tabFils = null;
		nbFils = 0;
	}



	/* -------------------- -------------------- -------------------- */ 
	/* -------------------- ---- primitives ---- -------------------- */ 


	public NodeP[] getTabFils() { return tabFils; }
	public void setTabFils(NodeP[] tabFils) { this.tabFils = tabFils; }
	public void initFils() { tabFils=new NodeP[TAILLE_ALPHABET]; }
	public NodeP iemefils(int i) { return tabFils[i]; }

	public int getNbFils(){ return nbFils; }
	public void incNbFils() { nbFils++;	}
	public void setNbFils(int nbFils) {	this.nbFils = nbFils; }




	/* -------------------- -------------------- -------------------- */ 
	/* --------------------  fonctions avancees  -------------------- */


	/**
	 * ajoute une chaine de charactere dans le Patricia trie
	 * @param w:String -> mot a rajouter
	 * @throws BadArgumentException -> si w ne peut etre un mot du dictionnaire
	 */
	public void addWord(String w) throws BadArgumentException{
		/* verifier que le mot est correcte */
		Tools.checkWord("PatriciaTrie.addWord", w);

		/* ajout d'EPSILON a la fin du mot */
		String word = w.concat(EPSILON);

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


	public void deleteWord(String w) throws BadArgumentException{
		/* verifier que le mot est correcte */
		Tools.checkWord("PatriciaTrie.deleteWord", w);

		/* ajout d'EPSILON a la fin du mot */
		String word = w.concat(EPSILON);
		if(tabFils[ word.charAt(0) ] != null) {
			if(tabFils[ word.charAt(0)].getPrefix().compareTo(word)==0) {
				tabFils[ word.charAt(0)]=null;
				nbFils--;
			}
			else
				tabFils[ word.charAt(0)].deleteWord(word);
		}
	}
	
	
	/**
	 * determine si le mot est present dans le Patricia trie
	 * @param w:String -> mot a rechercher
	 * @return true si w est un mot du Patricia trie, false sinon
	 * @throws BadArgumentException -> si w ne peut etre un mot du dictionnaire
	 */
	public boolean recherche(String w) throws BadArgumentException{
		/* verifier que le mot est correcte */
		Tools.checkWord("PatriciaTrie.recherche", w);

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
	 * compte le nombre de mots dans le Patricia trie
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
		return 0;
	}

	/**
	 * compte le nombre de pointeur vers null dans le Patricia trie
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
	 * @throws BadArgumentException
	 */
	public int prefixe(String w) throws BadArgumentException{
		/* verifier que le mot est correcte */
		Tools.checkWord("PatriciaTrie.prefixe", w);

		if(tabFils != null){
			if(tabFils[ w.charAt(0) ] != null) 
				return tabFils[ w.charAt(0) ].prefixe(w);
		}
		return 0;
	}
	
	// TODO ya listeMot et listeMot2. 
	
	public ArrayList<String> listeMot(){
		ArrayList<String> lr=new ArrayList<>();
		if(tabFils!=null) {
			for(int i=0;i<tabFils.length;i++) {
				if(tabFils[i]!=null) {
					tabFils[i].toString(lr, "");
				}
			}
		}
		return lr;
	}



	/* -------------------- -------------------- -------------------- */ 
	/* -------------------- -------------------- -------------------- */ 






	public String toString() {
		ArrayList<String> lr=listeMot();
		String r="";
		for(String s : lr)
			r+=s+"\n";
		return r;
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

	public int hauteur() {
		int r = 0;
		if(tabFils!=null)
			for(int i=0;i<tabFils.length;i++)
				if(tabFils[i]!=null)
					r=r<tabFils[i].hauteur()?tabFils[i].hauteur():r;
					return r;
	}

	// TODO chelou ca et bug si j = 0
	public double profondeurMoyenneOld() {
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
	
	public double profondeurMoyenne(){
		ArrayList<Integer> l = new ArrayList<Integer>();
		int somme_hauteur = 0;
		if(tabFils == null) return 0;
		for(int i=0;i<tabFils.length;i++)
			if(tabFils[i]!=null) l.addAll(tabFils[i].hauteurMoy(1));
		if(l.size() == 0) return 0;
		return somme_hauteur / l.size();
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
		PatriciaTrie p1 = clone();
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


	/* -------------------- -------------------- -------------------- */ 
	/* -------------------- ---- toHybride ----- -------------------- */

	public Hybride toHybride(){
		Hybride res = new Hybride();
		if(tabFils != null) 
			try{
				res.setHd(NodeP.horizontalTraduction(tabFils));
			}catch(WrongAccessException e){
				e.getMessage();
				System.out.println("--- ECHEC DE LA CONVESION DU PATRICIA ---");
				System.out.println("MOTIF: la methode horizontalTraduction() appel la methode" 
						+ "verticalTraduction() sur le NodeP EPSILON");
			}
		return res;
	}


	/* -------------------- -------------------- -------------------- */ 
	/* -------------------- ------ autres ------ -------------------- */

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




	

	public void writeFile(String fs){
		BufferedWriter bw=null;
		FileWriter fw=null;
		try {		
			fw = new FileWriter(fs);
			bw = new BufferedWriter(fw);
			bw.write("graph G {");
			bw.newLine();
			bw.write("Racine;");
			bw.newLine();
			if(tabFils!=null)
			for(int i=0;i<TAILLE_ALPHABET;i++)
				if(tabFils[i]!=null) {
					bw.write("Racine -- \""+tabFils[i].getId()+" "+tabFils[i].getPrefix()+"\";");
					bw.newLine();
					tabFils[i].writeFile(bw);
				}
			bw.write("}");
		} catch (Exception ex){
			ex.printStackTrace();
		}
		finally {
			try {
				if(bw!=null)
					bw.close();
				if(fw!=null)
					fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}





