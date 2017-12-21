package presentation;

import java.util.Scanner;

import exceptions.BadArgumentException;
import hybride.Hybride;
import patricia.PatriciaTrie;

public class Presentation {

	public static void main(String[] args) {
		boolean stayStructure = true;
		String buff = "";
		Scanner sc = new Scanner(System.in);
		
		//TODO Nom Prenom
		System.out.println("Bienvenue dans le projet d'ALGAV de HEBIRI Hatem et RASCAR Joseph");
		System.out.println("");
		System.out.println("Appuyez sur la touche entree pour continuer");
		buff = sc.nextLine();
		buff = "";
		
		afficheSeparation();
		
		
		while(stayStructure){
			System.out.println("Quel genre de structure voulez vous manipuler?");
			System.out.println("1: Hybride Trie");
			System.out.println("2: Patricia Trie");
			System.out.println("3: Retour au menu principale");

			buff = sc.nextLine();
			switch(buff){
			case "1" :
				buff = "";
				afficheSeparation();
				stayStructure = manipuleHybride(sc);
				break;
			case "2" :
				buff = "";
				afficheSeparation();
				stayStructure = manipulePatricia(sc);
				break;
			case "3" :
				buff = "";
				afficheSeparation();
				stayStructure = false;
				break;
			case "end" :
				buff = "";
				afficheSeparation();
				System.out.println("Merci ...");
				return; 
			default:
				buff = "";
				System.out.println("Requete non reconnu");
				afficheSeparation();
				break;
			}
		}
	}

	
	private static boolean manipuleHybride(Scanner sc){
		Hybride trie = new Hybride();
		String buff = "";
		int forPrefix;
		boolean firstStep = true;
		
		while(firstStep){
			System.out.println("Un HybrideTrie vient d'etre cree pour vos manipulations");
			System.out.print("Quel mot voulez vous inserer dans le trie: ");
			buff = sc.nextLine();
			try {
				trie.add(buff);
				firstStep = false;
			} catch (BadArgumentException e) {
				System.out.println("Requete impossible");
				System.out.println("Motif: " + e.getMessage());
			}
			afficheSeparation();
		}
		
		while(true){
			System.out.println("Quel est votre prochaine manipulation?");
			System.out.println("1: Ajouter un mot");
			System.out.println("2: Supprimer un mot");
			System.out.println("3: Rechercher un mot");
			System.out.println("4: Rechercher les mot commencant par un prefixe");
			System.out.println("5: Retour au menu precedent");

			buff = sc.nextLine();
			switch(buff){

			case "1" :
				System.out.print("Quel mot voulez vous inserer dans le trie: ");
				buff = sc.nextLine();
				afficheBlanc();
				try {
					trie.add(buff);
					bilanHybride(trie);
				} catch (BadArgumentException e) {
					System.out.println("Requete impossible");
					System.out.println("Motif: " + e.getMessage());
				}
				buff = "";
				afficheSeparation();
				break;

			case "2" :
				System.out.print("Quel mot voulez vous supprimer du trie: ");
				buff = sc.nextLine();
				afficheBlanc();
				try {
					trie.suppression(buff);
					bilanHybride(trie);
				} catch (BadArgumentException e) {
					System.out.println("Requete impossible");
					System.out.println("Motif: " + e.getMessage());
				}
				buff = "";
				afficheSeparation();
				break;

			case "3" :
				System.out.print("Quel mot voulez vous rechercher: ");
				buff = sc.nextLine();
				afficheBlanc();
				try {
					if(trie.rechercher(buff))
						System.out.println("Le mot " + buff + "est present dans le trie");
					else System.out.println("Le mot " + buff + " n'est pas present dans le trie");
				} catch (BadArgumentException e) {
					System.out.println("Requete impossible");
					System.out.println("Motif: " + e.getMessage());
				}
				buff = "";
				afficheSeparation();
				break;

			case "4" :
				System.out.print("Quel est le prefixe des mots que vous rechechez: ");
				buff = sc.nextLine();
				afficheBlanc();
				try {
					forPrefix = trie.prefixe(buff);
					if(forPrefix == 0) 
						System.out.println("Aucun mot commence par " + buff + " dans le trie");
					else if(forPrefix == 1) 
						System.out.println("Il y a 1 mot commence par " + buff + " dans le trie");
					else System.out.println("Il y a "+ forPrefix + " mots commencent par " + buff + " dans le trie");
					System.out.println("liste des mots:");
				} catch (BadArgumentException e) {
					System.out.println("Requete impossible");
					System.out.println("Motif: " + e.getMessage());
				}
				buff = "";
				afficheSeparation();
				break;

			case "5" :
				buff = "";
				afficheSeparation();
				return true;

			case "end" :
				afficheSeparation();
				return false; 

			default:
				buff = "";
				System.out.println("Requete non reconnu");
				afficheSeparation();
				break;
			}
		}		
	}

	private static boolean manipulePatricia(Scanner sc){
		PatriciaTrie trie = new PatriciaTrie();
		String buff = "";
		int forPrefixe;
		boolean firstStep = true;
		
		while(firstStep){
			System.out.println("Un PatriciaTrie vient d'etre cree pour vos manipulations");
			System.out.print("Quel mot voulez vous inserer dans le trie: ");
			buff = sc.nextLine();
			try {
				trie.addWord(buff);
				firstStep = false;
			} catch (BadArgumentException e) {
				System.out.println("Requete impossible");
				System.out.println("Motif: " + e.getMessage());
			}
			afficheSeparation();
		}
		
		while(true){
			System.out.println("Quel est votre prochaine manipulation?");
			System.out.println("1: Ajouter un mot");
			System.out.println("2: Supprimer un mot");
			System.out.println("3: Rechercher un mot");
			System.out.println("4: Rechercher les mot commencant par un prefixe");
			System.out.println("5: Retour au menu precedent");

			buff = sc.nextLine();
			switch(buff){

			case "1" :
				System.out.print("Quel mot voulez vous inserer dans le trie: ");
				buff = sc.nextLine();
				afficheBlanc();
				try {
					trie.addWord(buff);
					bilanPatricia(trie);
				} catch (BadArgumentException e) {
					System.out.println("Requete impossible");
					System.out.println("Motif: " + e.getMessage());
				}
				buff = "";
				afficheSeparation();
				break;

			case "2" :
				System.out.print("Quel mot voulez vous supprimer du trie: ");
				buff = sc.nextLine();
				afficheBlanc();
				try {
					trie.deleteWord(buff);
					bilanPatricia(trie);
				} catch (BadArgumentException e) {
					System.out.println("Requete impossible");
					System.out.println("Motif: " + e.getMessage());
				}
				buff = "";
				afficheSeparation();
				break;

			case "3" :
				System.out.print("Quel mot voulez vous rechercher: ");
				buff = sc.nextLine();
				afficheBlanc();
				try {
					if(trie.recherche(buff))
						System.out.println("Le mot " + buff + "est present dans le trie");
					else System.out.println("Le mot " + buff + " n'est pas present dans le trie");
				} catch (BadArgumentException e) {
					System.out.println("Requete impossible");
					System.out.println("Motif: " + e.getMessage());
				}
				buff = "";
				afficheSeparation();
				break;

			case "4" :
				System.out.print("Quel est le prefixe des mots que vous rechechez: ");
				buff = sc.nextLine();
				afficheBlanc();
				try {
					forPrefixe = trie.prefixe(buff);
					if(forPrefixe == 0) 
						System.out.println("Aucun mot commence par " + buff + " dans le trie");
					else if(forPrefixe == 1)
						System.out.println("Il y a 1 mot qui commence par " + buff + " dans le trie");
					else System.out.println("Il y a " + forPrefixe + " mots qui commencent par " + buff + " dans le trie");
				} catch (BadArgumentException e) {
					System.out.println("Requete impossible");
					System.out.println("Motif: " + e.getMessage());
				}
				buff = "";
				afficheSeparation();
				break;

			case "5" :
				buff = "";
				afficheSeparation();
				return true;

			case "end" :
				buff = "";
				afficheSeparation();
				return false; 

			default:
				buff = "";
				System.out.println("Requete non reconnu ...");
				afficheSeparation();
				break;
			}
		}		

	}


	private static void bilanHybride(Hybride trie){
		// TODO
		//Tools.imageHybride(trie);
		trie.showHybride();

		System.out.println("liste des mots:");
		for(String s: trie.listeMot()) System.out.print(s + "  ");
		System.out.println("");
		System.out.println("Nombre de mots: " + trie.comptageMot());
		System.out.println("Nombre de nils: " + trie.comptageNil());
		System.out.println("Hauteur       : " + trie.hauteur());
	}
	
	private static void bilanPatricia(PatriciaTrie trie){
		// TODO
		//Tools.imagePatricia(trie);
		trie.showTrie();

		System.out.println("liste des mots:");
		for(String s: trie.listeMot()) System.out.print(s + "  ");
		System.out.println("");
		System.out.println("Nombre de mots: " + trie.comptageMot());
		System.out.println("Nombre de nils: " + trie.comptageNil());
		System.out.println("Hauteur       : " + trie.hauteur());
	}
	
	private static void afficheBlanc(){
		System.out.println("");
		System.out.println("");
	}
	
	private static void afficheSeparation(){
		System.out.println("");
		System.out.println("");
		System.out.println("------------------------------------------------------------------------------");
		System.out.println("------------------------------------------------------------------------------");
		System.out.println("");
		System.out.println("");
	}
}
