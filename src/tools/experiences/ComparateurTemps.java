package tools.experiences;

import java.util.ArrayList;

import exceptions.BadArgumentException;
import exceptions.WrongAccessException;
import hybride.Hybride;
import patricia.PatriciaTrie;
import tools.Tools;

public class ComparateurTemps {
	
	private Hybride h;
	private PatriciaTrie p;
	
	public ComparateurTemps(Hybride h, PatriciaTrie p){
		this.h = h;
		this.p = p;
	}
	
	public ComparateurTemps(){
		this(null, null);
	}
	
	public Resultat addShakespeare() throws WrongAccessException, BadArgumentException{
		
		long timeP, timeH;
		// recuperer la liste de tous les mots
		ArrayList<String> words = Tools.shakespeareWords();
		
		// ajout de tous les mots de la liste dans le PatriciaTrie
		timeP = System.currentTimeMillis(); 
		p = new PatriciaTrie();
		for(String s : words) p.addWord(s);
		timeP = System.currentTimeMillis() - timeP;

		// ajout de tous les mots de la liste dans le HybrideTrie
		timeH = System.currentTimeMillis(); 
		h = new Hybride();
		for(String s : words) h.add(s);
		timeH = System.currentTimeMillis() - timeH;
		
		return new Resultat(timeP, timeH);
	}
	
	public Resultat addOneWord(String w) throws WrongAccessException, BadArgumentException{

		long timeP, timeH;
		
		if(p == null || h == null)
			throw new WrongAccessException("les tries sont vides!");

		// ajout de tous les mots de la liste dans le PatriciaTrie
		timeP = System.currentTimeMillis(); 
		p.addWord(w);
		timeP = System.currentTimeMillis() - timeP;

		// ajout de tous les mots de la liste dans le HybrideTrie
		timeH = System.currentTimeMillis(); 
		h.add(w);
		timeH = System.currentTimeMillis() - timeH;

		return new Resultat(timeP, timeH);
	}

	
	public Resultat rechercheFicShakespeare(String ficName) throws WrongAccessException, BadArgumentException{
		long timeP, timeH;
		
		if(p == null || h == null)
			throw new WrongAccessException("les tries sont vides!");
					
		// recuperer la liste des mots du fichier ficName
		ArrayList<String> words = Tools.fic_to_listWord("./fichiers_test/Shakespeare/" + ficName);
		
		// recherche des mots de la liste dans le PatriciaTrie
		timeP = System.currentTimeMillis(); 
		for(String s : words) p.recherche(s);
		timeP = System.currentTimeMillis() - timeP;

		// recherche des  mots de la liste dans le HybrideTrie
		timeH = System.currentTimeMillis(); 
		for(String s : words) h.rechercher(s);
		timeH = System.currentTimeMillis() - timeH;

		return new Resultat(timeP, timeH);
	}
	
	
	public Resultat suppressionFicShakespeare(String ficName) throws WrongAccessException, BadArgumentException{
		long timeP, timeH;
		
		if(p == null || h == null)
			throw new WrongAccessException("les tries sont vides!");
					
		// recuperer la liste des mots du fichier ficName
		ArrayList<String> words = Tools.fic_to_listWord("./fichiers_test/Shakespeare/" + ficName);
		
		// recherche des mots de la liste dans le PatriciaTrie
		timeP = System.currentTimeMillis(); 
		for(String s : words) p.deleteWord(s);
		timeP = System.currentTimeMillis() - timeP;

		// recherche des  mots de la liste dans le HybrideTrie
		timeH = System.currentTimeMillis(); 
		for(String s : words) h.suppression(s);
		timeH = System.currentTimeMillis() - timeH;

		return new Resultat(timeP, timeH);
	}
	
	
	

	

}
