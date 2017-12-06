package hybride;

import java.util.ArrayList;
import java.util.HashMap;

import exceptions.BadArgumentException;
import patricia.NodeP;
import patricia.PatriciaTrie;

public class Hybride {
	
	private HNode hd;

	private int nbMots;
	
	public Hybride() {
		hd=null;
		nbMots=0;
	}
	
	public void add(String s) throws BadArgumentException{
		if(s.length()==0)
			throw new BadArgumentException("Hybride.addWord: w est une chaine vide");
		if(hd==null)
			hd=new HNode(null, s.charAt(0)+"", null, null, null);
		hd.add(s.substring(0),nbMots);
		nbMots++;

	}
	
	public boolean rechercher(String w) {
		/* ajout d'EPSILON a la fin du mot */

		if(hd != null)
			return hd.rechercher(w);
		
		return false;
	}
	
	public int comptageMot(){
		int compteur = 0;
		
		if(hd != null)
			compteur += hd.comptageMot();
		
		return compteur;
	}
	
	public int prefixe(String w) throws BadArgumentException{
		/* verifier que le mot est correcte */
		if(hd != null){
			return hd.prefixe(w);
		}
		return 0;
	}
	
	public PatriciaTrie toPatriciaTrie() {
		PatriciaTrie n = new PatriciaTrie();
		if(hd!=null) {
			n.initFils();
			NodeP[] tabFils=n.getTabFils();
			tabFils[hd.getPrefix().charAt(0)]=hd.parcoursProfondeur(new StringBuilder(""));
			n.incNbFils();
			HNode previous=hd.getPrevious();
			HNode next=hd.getNext();
			while(next!=null || previous!=null) {
				if(next!=null) {
					tabFils[next.getPrefix().charAt(0)]=next.parcoursProfondeur(new StringBuilder(""));
					next=next.getNext();
					n.incNbFils();;
				}
				if(previous!=null) {
					tabFils[previous.getPrefix().charAt(0)]=previous.parcoursProfondeur(new StringBuilder(""));
					previous=previous.getPrevious();
					n.incNbFils();
				}
			}
		}
		return n;
	}
	

}



