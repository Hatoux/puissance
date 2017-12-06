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
	
	// TODO regarder si constructeur necessaire
	public HNode(Integer v,  String pr, HNode p, HNode s, HNode n) {
		previous=p;
		next=n;
		value=v;
		son=s;
		prefix=pr;
	}

	
	public HNode getSon(){ return son; }	
	public void setSon(HNode n){ son = n; }

	public HNode getPrevious(){ return previous; }
	public void setPrevious(HNode n){ previous = n; }

	public HNode getNext(){ return next; }
	public void setNext(HNode n){ next = n; }

	public String getPrefix(){ return prefix; }

	public Integer getValue() { return value; }




//	public void add(String s,int v) {
//
//		if(prefix.charAt(0)>s.charAt(0)) {
//			if(previous==null) {
//				HNode n=new HNode(null, s.charAt(0)+"", null, null, this);
//				previous=n;
//				n.add(s, v);
//				return;
//			}
//			HNode tmp1 = this;
//			HNode tmp2 = previous;
//			while(tmp2!=null && tmp2.prefix.charAt(0)>s.charAt(0)) {
//				tmp1 = tmp2;
//				tmp2=tmp2.previous;
//
//			}
//			if(tmp2!=null && tmp2.prefix.charAt(0)<s.charAt(0)) {
//				HNode n=new HNode(null, s.charAt(0)+"", tmp2, null, tmp1);
//				tmp1.previous=n;
//				tmp2.next=n;
//				n.add(s, v);
//				return;
//			}
//			if(tmp2!=null && tmp2.prefix.charAt(0)==s.charAt(0)) {
//				tmp2.add(s, v);
//				return;
//			}
//			if(tmp2==null) {
//				HNode n=new HNode(null, s.charAt(0)+"", null, null, tmp1);
//				tmp1.previous=n;
//				n.add(s, v);
//				return;
//			}
//
//		}
//
//		if(prefix.charAt(0)==s.charAt(0)) {
//			if(s.length()==1) 
//				if(value==null) {
//					value=v;
//					return;
//				}
//				else 
//					return;
//			if(son==null)
//				son=new HNode(null, s.charAt(1)+"", null, null, null);
//			son.add(s.substring(1), v);
//			return;
//		}
//
//		if(prefix.charAt(0)<s.charAt(0)) {
//
//			if(next==null) {
//
//				HNode n=new HNode(null, s.charAt(0)+"", this, null, null);
//				next=n;
//				n.add(s, v);
//				return;
//			}
//			HNode tmp1 = this;
//			HNode tmp2 = next;
//			while(tmp2!=null && tmp2.prefix.charAt(0)<s.charAt(0)) {
//				tmp1 = tmp2;
//				tmp2=tmp2.next;
//
//			}
//
//			if(tmp2!=null && tmp2.prefix.charAt(0)>s.charAt(0)) {
//
//				HNode n=new HNode(null, s.charAt(0)+"", tmp1, null, tmp2);
//				tmp1.next=n;
//				tmp2.previous=n;
//				n.add(s, v);
//				return;
//			}
//			if(tmp2!=null && tmp2.prefix.charAt(0)==s.charAt(0)) {
//
//				tmp2.add(s, v);
//				return;
//			}
//			if(tmp2==null) {
//
//				HNode n=new HNode(null, s.charAt(0)+"", tmp1, null, null);
//				tmp1.next=n;
//				n.add(s, v);
//				return;
//			}
//
//		}
//	}
//
//
//
//
//
//
//
//
//	public boolean rechercher(String s) {
//
//		if(prefix.charAt(0)>s.charAt(0)) {
//
//			if(previous==null) 
//				return false;
//
//			HNode tmp2 = previous;
//			while(tmp2!=null && tmp2.prefix.charAt(0)>s.charAt(0)) 
//				tmp2=tmp2.previous;
//
//			if(tmp2!=null && tmp2.prefix.charAt(0)<s.charAt(0)) 
//				return false;
//
//			if(tmp2!=null && tmp2.prefix.charAt(0)==s.charAt(0)) 
//				return tmp2.rechercher(s);
//
//
//			if(tmp2==null) 
//				return false;
//
//		}
//
//		if(prefix.charAt(0)==s.charAt(0)) {
//			if(s.length()==1)
//				return value!=null;
//			if(son==null)
//				return false;
//			return son.rechercher(s.substring(1));
//		}
//
//		if(prefix.charAt(0)<s.charAt(0)) {
//			if(next==null) 
//				return false;
//
//			HNode tmp2 = next;
//			while(tmp2!=null && tmp2.prefix.charAt(0)<s.charAt(0)) 
//				tmp2=tmp2.next;
//
//			if(tmp2!=null && tmp2.prefix.charAt(0)>s.charAt(0)) 
//				return false;
//
//			if(tmp2!=null && tmp2.prefix.charAt(0)==s.charAt(0)) 
//				return tmp2.rechercher(s);
//
//			if(tmp2==null) 
//				return false;
//		}
//
//		return false;
//	}
//
//
//	public int comptageMotG() {
//		int compteur = 0;
//
//		if(value!=null)
//			compteur++;
//		if(previous!=null) {
//			compteur+=previous.comptageMotG();
//			
//		}
//
//		if(son!=null)
//			compteur+=son.comptageMot();
//
//		return compteur;
//		
//	}
//	
//	public int comptageMotD() {
//		int compteur = 0;
//
//		if(value!=null)
//			compteur++;
//		if(next!=null) {
//			compteur+=next.comptageMotD();
//			
//		}
//
//		if(son!=null)
//			compteur+=son.comptageMot();
//
//		return compteur;
//		
//	}
//
//	public int comptageMot() {
//		int compteur = 0;
//
//		if(value!=null)
//			compteur++;
//
//
//		if(previous!=null) {
//			compteur+=previous.comptageMotG();
//		}
//
//
//		
//
//		if(next!=null) {
//			compteur+=next.comptageMotD();
//		}
//		
//		
//		if(son!=null)
//			compteur+=son.comptageMot();
//
//		return compteur;
//
//	}
//
//
//
//
//	public int prefixe(String s) {
//		// TODO Auto-generated method stub
////		if(s.length()==1) {
////			if(value!=null)
////				return 1+comptageMot();
////			else
////				return comptageMot();
////		}
//
//
//		if(prefix.charAt(0)>s.charAt(0)) {
//
//			if(previous==null) 
//				return 0;
//
//			HNode tmp2 = previous;
//			while(tmp2!=null && tmp2.prefix.charAt(0)>s.charAt(0)) 
//				tmp2=tmp2.previous;
//
//			if(tmp2!=null && tmp2.prefix.charAt(0)<s.charAt(0)) 
//				return 0;
//
//			if(tmp2!=null && tmp2.prefix.charAt(0)==s.charAt(0)) 
//				return tmp2.prefixe(s);
//
//
//			if(tmp2==null) 
//				return 0;
//		}
//
//		if(prefix.charAt(0)==s.charAt(0)) {
//			if(s.length()==1) 
//				return comptageMot();
//			else
//				if(son==null)
//					return 0;
//				else
//					return son.prefixe(s.substring(1));
//
//		}
//
//		if(prefix.charAt(0)<s.charAt(0)) {
//
//			HNode tmp2 = next;
//			while(tmp2!=null && tmp2.prefix.charAt(0)<s.charAt(0)) 
//				tmp2=tmp2.next;
//
//			if(tmp2!=null && tmp2.prefix.charAt(0)>s.charAt(0)) 
//				return 0;
//
//			if(tmp2!=null && tmp2.prefix.charAt(0)==s.charAt(0)) 
//				return tmp2.prefixe(s);
//
//			if(tmp2==null) 
//				return 0;
//		}
//
//		return 0;
//	}

	public void add(String s,int v) {


		if(prefix.codePointAt(0)>s.codePointAt(0)) {
//			System.out.println("frere gauche:"+s);
			if(previous==null)
				previous=new HNode(null, s.charAt(0)+"", null, null, null);
			previous.add(s, v);
		}

		if(prefix.codePointAt(0)==s.codePointAt(0)) {
//			System.out.println("fils :"+s);
			if(s.length()==1) 
				if(value==null) {
					value=v;
					return;
				}
				else 
					return;
			if(son==null)
				son=new HNode(null, s.charAt(1)+"", null, null, null);
			son.add(s.substring(1), v);
		}

		if(prefix.codePointAt(0)<s.codePointAt(0)) {
//			System.out.println("frere droite :"+s);
			if(next==null)
				next=new HNode(null, s.charAt(0)+"", null, null, null);
			next.add(s, v);
		}

	}

//	public void add(String s,int v,int i) {
//
//
//		if(prefix.codePointAt(0)>s.codePointAt(i)) {
////			System.out.println("frere gauche:"+s);
//			if(previous==null)
//				previous=HTools.newHNode(null, s.charAt(i)+"", null, null, null);
//			previous.add(s, v,i);
//		}
//
//		if(prefix.codePointAt(0)==s.codePointAt(i)) {
////			System.out.println("fils :"+s);
//			if(s.length()-i==1) 
//				if(value==null) {
//					value=v;
//					return;
//				}
//				else 
//					return;
//			i++;
//			if(son==null)
//				son=HTools.newHNode(null, s.charAt(i)+"", null, null, null);
//			son.add(s, v, i);
//		}
//
//		if(prefix.codePointAt(0)<s.codePointAt(i)) {
////			System.out.println("frere droite :"+s);
//			if(next==null)
//				next=HTools.newHNode(null, s.charAt(i)+"", null, null, null);
//			next.add(s, v,i);
//		}
//
//	}






	
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




	public void parcoursLargeur( NodeP n) {
		NodeP[] tabFils=n.getTabFils();
		tabFils[prefix.charAt(0)]=parcoursProfondeur(new StringBuilder(""));
		n.incNbFils();
		HNode previous=this.previous;
		HNode next=this.next;
		while(next!=null || previous!=null) {
			if(next!=null) {
				tabFils[next.prefix.charAt(0)]=next.parcoursProfondeur(new StringBuilder(""));
				next=next.next;
				n.incNbFils();
			}
			if(previous!=null) {
				tabFils[previous.prefix.charAt(0)]=previous.parcoursProfondeur(new StringBuilder(""));
				previous=previous.previous;
				n.incNbFils();
			}
		}
	}

	public NodeP parcoursProfondeur(StringBuilder s) {

		s.append(prefix);
		if(son!=null) {
			if(son.getNext()!=null || son.getPrevious()!=null) {
				NodeP n=new NodeP(s.toString());
				NodeP[] tabFils=new NodeP[PatriciaTrie.TAILLE_ALPHABET];
				if(value!=null) {
					n.incNbFils();
					tabFils[PatriciaTrie.EPSILON.charAt(0)]=new NodeP(PatriciaTrie.EPSILON);
				}
				n.setTabFils(tabFils);
				son.parcoursLargeur(n);
				return n;
			}else {
				if(value!=null) {
					NodeP n=new NodeP(s.toString());
					NodeP[] tabFils=new NodeP[PatriciaTrie.TAILLE_ALPHABET];
					n.incNbFils();
					tabFils[PatriciaTrie.EPSILON.charAt(0)]=new NodeP(PatriciaTrie.EPSILON);
					n.setTabFils(tabFils);
					son.parcoursLargeur(n);
					return n;
				}
				return son.parcoursProfondeur(s);
			}
		}
		else {
			s.append(PatriciaTrie.EPSILON);
			return new NodeP(s.toString());
		}


	}
	
	
	
	
	
	
	
	
	/* ma partie */

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

