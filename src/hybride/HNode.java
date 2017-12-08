package hybride;


import java.util.ArrayList;

import patricia.NodeP;
import patricia.PatriciaTrie;


public class HNode{

	private Integer value;
	private String prefix;
	private HNode previous;
	private HNode next;
	private HNode son;

	public HNode(Integer v,  String pr, HNode p, HNode s, HNode n) {
		previous=p;
		next=n;
		value=v;
		son=s;
		prefix=pr;
	}


	/* -------------------- -------------------- -------------------- */ 
	/* -------------------- ---- primitives ---- -------------------- */ 


	public Integer getValue() {	return value; }
	public String getPrefix() { return prefix; }
	public HNode getPrevious() { return previous; }
	public HNode getNext() { return next; }
	public HNode getSon() { return son; }

	public void setSon(HNode n){ son = n; }
	public void setPrevious(HNode n){ previous = n; }
	public void setNext(HNode n){ next = n; }

	/* -------------------- -------------------- -------------------- */ 
	/* --------------------  fonctions avancees  -------------------- */

	/* TODO: pk cette methode rnevoie un boolean? */
	public boolean add(String s,int v) {

		if(prefix.codePointAt(0)>s.codePointAt(0)) {
			if(previous==null)
				previous=new HNode(null, s.charAt(0)+"", null, null, null);
			previous.add(s, v);
		}

		if(prefix.codePointAt(0)==s.codePointAt(0)) {
			if(s.length()==1) 
				if(value==null) {
					value=v;
					return true;
				}
				else 
					return false;
			if(son==null)
				son=new HNode(null, s.charAt(1)+"", null, null, null);
			son.add(s.substring(1), v);
		}

		if(prefix.codePointAt(0)<s.codePointAt(0)) {
			if(next==null)
				next=new HNode(null, s.charAt(0)+"", null, null, null);
			next.add(s, v);
		}

		return false;
	}


	public boolean rechercher(String s) {
		// TODO Auto-generated method stub

		if(prefix.codePointAt(0)>s.codePointAt(0)) {
			if(previous==null)
				return false;
			return previous.rechercher(s);
		}


		if(prefix.codePointAt(0)==s.codePointAt(0)) {
			if(s.length()==1)
				return value!=null;
			if(son==null)
				return false;
			return son.rechercher(s.substring(1));
		}

		if(prefix.codePointAt(0)<s.codePointAt(0)) {
			if(next==null)
				return false;
			return next.rechercher(s);
		}

		return false;
	}




	public int comptageMot() {
		int compteur = 0;

		if(value!=null)
			compteur++;

		if(previous!=null)
			compteur+=previous.comptageMot();

		if(son!=null)
			compteur+=son.comptageMot();

		if(next!=null)
			compteur+=next.comptageMot();

		return compteur;
	}




	public int prefixe(String s) {
		// TODO Auto-generated method stub
		if(s.length()==0)
			return 0;

		if(prefix.codePointAt(0)>s.codePointAt(0)) {
			if(previous==null)
				return 0;
			return previous.prefixe(s);
		}

		if(prefix.codePointAt(0)==s.codePointAt(0)) {
			if(son==null)
				return 1;
			return 1+son.prefixe(s.substring(1));
		}

		if(prefix.codePointAt(0)<s.codePointAt(0)) {
			if(next==null)
				return 0;
			return next.prefixe(s);
		}

		return 0;
	}


	public void conversion(NodeP p,StringBuilder s){

		if(previous!=null) {
			previous.conversion(p, new StringBuilder(s));
		}
		if(next!=null) {
			next.conversion(p, new StringBuilder(s));
		}

		if(value==null) {
			if(son.previous==null && son.next==null) {
				s.append(prefix);
				son.conversion(p, s);
			}
			else {
				s.append(prefix);
				NodeP n = new NodeP(s.toString());
				p.getTabFils()[s.charAt(0)]=n;
				p.incNbFils();
				NodeP[] tabFils=new NodeP[PatriciaTrie.TAILLE_ALPHABET];
				n.setTabFils(tabFils);
				son.conversion(n, new StringBuilder());
				if(son.next!=null) {
					son.next.conversion(n, new StringBuilder());
				}
				if(son.previous!=null) {
					son.previous.conversion(n, new StringBuilder());
				}
			}

		}
		else {
			if(son==null) {
				s.append(prefix);
				s.append(PatriciaTrie.EPSILON);
				p.getTabFils()[s.charAt(0)]=new NodeP(s.toString());
				p.incNbFils();
			}
			else {
				s.append(prefix);
				NodeP n = new NodeP(s.toString());
				p.getTabFils()[s.charAt(0)]=n;
				p.incNbFils();
				NodeP[] tabFils=new NodeP[PatriciaTrie.TAILLE_ALPHABET];
				n.setTabFils(tabFils);
				tabFils[PatriciaTrie.EPSILON.charAt(0)]=new NodeP(PatriciaTrie.EPSILON);
				n.incNbFils();
				son.conversion(n, new StringBuilder());
			}
		}
	}



	public void listeMot(ArrayList<String> res, String prefix){

		/* recuperation des mots present dans le fils gauche */
		if(previous != null) previous.listeMot(res, prefix);

		/* ajout du mot courant si c'est un mot */
		if(value != null) res.add(prefix + this.prefix);

		/* recuperation des mots present dans le fils du milieu */
		if(son != null)	son.listeMot(res, prefix + this.prefix);

		/* recuperation des mots present dans le fils droit */
		if(next != null) next.listeMot(res, prefix);
	}

	/**
	 * methode appele par la methode comptageNil() de la classe Hybride.
	 * @return int: le nombre de pointeur nul dans l'Hybride a partir de ce node
	 */
	public int comptageNil(){
		int compteur = 0;
		if(previous == null) compteur++;
		else compteur += previous.comptageNil();
		if(son == null) compteur++;
		else compteur += son.comptageNil();
		if(next == null) compteur++;
		else compteur += next.comptageNil();
		return compteur;
	}




	public void addSubtree(HNode n){
		if(n.getPrefix().charAt(0) > prefix.charAt(0)){
			if(next == null) next = n;
			else next.addSubtree(n);
		}else if(n.getPrefix().charAt(0) < prefix.charAt(0)){
			if(previous == null) previous = n;
			else previous.addSubtree(n);
		}
		/* le cas ou n.getPrefix().charAt(0) == prefix.charAt(0) n'est pas a traiter 
		 * car cela voudrait dire que l'hybride est mal construit */

	}



	// WARNING! cette fonction peut potentiellemnt desequilibrer l'arbre
	public boolean suppression(HNode father, String word){
		System.out.println("Dans le node " + prefix);
		if(word.codePointAt(0) == prefix.codePointAt(0)){
			System.out.println("word.codePointAt(0) == prefix.codePointAt(0)");
			if(word.length() > 1){ 
				System.out.println("word.length() > 1");
				if(son != null && son.suppression(this, word.substring(1)) ){ /* implique son = null */
					if(father.getSon() != null && father.getSon().equals(this)){
						if(value == null){ /* Dans ce cas, le Node courant doit etre supprimer */
							if(previous == null && next == null){ 
								/* Seul cas ou on renvoie true (dans if(word.length() > 1) */
								father.setSon(null);
								return true;
							}
							if(previous == null && next != null) father.setSon(next);
							else if(previous != null && next == null) father.setSon(previous);
							else{
								HNode tmp = next;
								next = null;		
								previous.addSubtree(tmp);
								father.setSon(previous);
							}
						}					
					}else if(father.getPrevious() != null && father.getPrevious().equals(this)){
						if(previous == null && next == null) father.setPrevious(null);
						else if(previous == null && next != null) father.setPrevious(next);
						else if(previous != null && next == null) father.setPrevious(previous);
						else{
							HNode tmp = next;
							next = null;		
							previous.addSubtree(tmp);
							father.setPrevious(previous);
						}
					}else if(father.getNext() != null && father.getNext().equals(this)){
						if(previous == null && next == null) father.setNext(null);
						else if(previous == null && next != null) father.setNext(next);
						else if(previous != null && next == null) father.setNext(previous);
						else{
							HNode tmp = next;
							next = null;		
							previous.addSubtree(tmp);
							father.setNext(previous);
						}
					}
				}
			}else{ /* word.length() == 1 */
				if(value != null){ /* mot a supprimer trouve */
					value = null;
					if(son == null){
						if(father.getSon() != null && father.getSon().equals(this)){
							if(previous == null && next == null){ 
								/* Seul cas ou on renvoie true (dans if(word.length() == 1)) */
								father.setSon(null);
								return true;
							}
							if(previous == null && next != null)
								father.setSon(next);
							else if(previous != null && next != null)
								father.setSon(previous);
							else{
								HNode tmp = next;
								next = null;		
								previous.addSubtree(tmp);
								father.setSon(previous);
							}
						}else if(father.getPrevious() != null && father.getPrevious().equals(this)){

							if(previous == null && next == null) 
								father.setPrevious(null);
							else if(previous == null && next != null)
								father.setPrevious(next);
							else if(previous != null && next != null)
								father.setPrevious(previous);
							else{
								HNode tmp = next;
								next = null;		
								previous.addSubtree(tmp);
								father.setPrevious(previous);
							}
						}else if(father.getNext() != null && father.getNext().equals(this)){

							if(previous == null && next == null) 
								father.setNext(null);
							else if(previous == null && next != null)
								father.setNext(next);
							else if(previous != null && next != null)
								father.setNext(previous);
							else{
								HNode tmp = next;
								next = null;		
								previous.addSubtree(tmp);
								father.setNext(previous);
							}
						}
					} /* sinon on fait rien */
				} /* le mot qu'on veut chercher est inexistant donc on fait rien */
			}
		}else if(word.codePointAt(0) > prefix.codePointAt(0)){ 
			if(next != null) next.suppression(this, word);
		}else 
			if(previous != null) previous.suppression(this, word);
		return false;
	}



	/* -------------------- -------------------- -------------------- */ 
	/* --------------------  ----- autres -----  -------------------- */


	public void showHNode(int h){
		System.out.println("");
		boolean ok = false;

		if(previous != null){
			for(int i=0; i<h; i++)System.out.print("   ");
			System.out.print(prefix + " ");
			System.out.print("(p)->");
			previous.showHNode(h+1);
			ok = true;
		}
		if(son != null){
			for(int i=0; i<h; i++)System.out.print("   ");
			System.out.print(prefix + " ");
			System.out.print("(s)->");
			son.showHNode(h+1);
			ok = true;
		}
		if(next != null){
			for(int i=0; i<h; i++)System.out.print("   ");
			System.out.print(prefix + " ");
			System.out.print("(n)->");
			next.showHNode(h+1);
			ok = true;
		}
		if(!ok){
			for(int i=0; i<h; i++)System.out.print("   ");
			System.out.print(prefix + " \n");
		}
	}


}









