package tools.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import exceptions.BadArgumentException;
import exceptions.WrongAccessException;
import hybride.Hybride;
import patricia.PatriciaTrie;
import tools.Tools;
import tools.experiences.ComparateurTemps;
import tools.experiences.Resultat;

public class mainTest {
	
	
	/* test permettant de verifier que les mots du fichiers sont extrait quel que soit la facon
	 * dont le fichier est concue */
	public static void main1(String [] args){
		ArrayList<String> words1 = Tools.fic_to_listWord("./fichiers_test/exemple_de_base.txt");
		Set<String> l1 = new HashSet<>();
		for(String s : words1) l1.add(s);

		ArrayList<String> words2 = Tools.fic_to_listWord("./fichiers_test/phrase.txt");
		Set<String> l2 = new HashSet<>();
		for(String s : words2) l2.add(s);

		System.out.println("il y a " + l1.size() + " mots dans le set1");
		System.out.println("il y a " + l2.size() + " mots dans le set2");
	}

	/* verifie que nos structures recupere bien le bon nombre de mots */
	public static void main2(String [] args){
		try {
			/* pour recuperer le nombre de mots reel */
			System.out.println("recuperation des mots dans une liste");
			ArrayList<String> words = Tools.shakespeareWords();
			
			System.out.println("recuperation des mots de la liste dans un set");
			Set<String> l1 = new HashSet<>();
			for(String s : words) l1.add(s);
			
			System.out.println("recuperation des mots dans un PatriciaTrie");
			PatriciaTrie abrP = new PatriciaTrie();
			for(String s : words) abrP.addWord(s);

			System.out.println("recuperation des mots dans un HybrideTrie");
			Hybride abrH = new Hybride();
			for(String s : words) abrH.add(s);
			
			System.out.println("il y a " + l1.size() + " mots dans le set1");
			System.out.println("il y a " + abrP.comptageMot() + " mots dans le PatriciaTrie");
			System.out.println("il y a " + abrH.comptageMot() + " mots dans le HybrideTrie");
			
		} catch (WrongAccessException e) {
			e.printStackTrace();
		} catch (BadArgumentException e) {
			e.printStackTrace();
		}
	}

	public static void main(String [] args){
		try {
			ComparateurTemps ct = new ComparateurTemps();
			
			// Recuperation des fichiers du dossier Shakespeare
			System.out.println("Recuperation des fichiers du dossier Shakespeare");
			ArrayList<String> shakespeareFilesName = Tools.shakespeareFics();
			
			// Remplissages des structure avec tous les mots dans Shakespeare
			System.out.println("Remplissages des structure avec tous les mots dans Shakespeare");
			Resultat resAdd = ct.addShakespeare();
			System.out.println("temps de remplissage du patricia : " + resAdd.getResultPatricia() + "ms");
			System.out.println("temps de remplissage du hybride  : " + resAdd.getResultHybride() + "ms");
			
			// Recherche de l'ensemble des mots present dans le fichier
			System.out.println("Recherche de l'ensemble des mots present dans le fichier"
					+ shakespeareFilesName.get(0));
			Resultat resRecherche = ct.rechercheFicShakespeare(shakespeareFilesName.get(0));
			System.out.println("temps de recherche dans le patricia : " 
					+ resRecherche.getResultPatricia() + "ms");
			System.out.println("temps de recherche dans le hybride  : " 
					+ resRecherche.getResultHybride() + "ms");
			
			// Suppression de l'ensemble des mots present dans le fichier
			System.out.println("Suppression de l'ensemble des mots present dans le fichier"
					+ shakespeareFilesName.get(0));
			Resultat resSuppression = ct.suppressionFicShakespeare(shakespeareFilesName.get(0));
			System.out.println("temps de supression dans le patricia : " 
					+ resSuppression.getResultPatricia() + "ms");
			System.out.println("temps de supression dans le hybride  : " 
					+ resSuppression.getResultHybride() + "ms");
			
		} catch (WrongAccessException | BadArgumentException e) {
			e.printStackTrace();
		}
	}
}
