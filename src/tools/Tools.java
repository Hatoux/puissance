package tools;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import exceptions.BadArgumentException;
import exceptions.WrongAccessException;
import patricia.PatriciaTrie;

public class Tools {


	/**
	 * methode verifiant que le mot passer en parametre verifie les conditions necessaire pour
	 * integrer les PatriciasTrie et les HybrideTrie. Les conditions sont:
	 * _ le mot ne doit pas etre une chaines vide
	 * _ le mot ne doit pas contenir le charactere EPSILON
	 * @param function_name:String -> nom de la methode appelant cette methode
	 * @param w:String -> mota verifier
	 * @throws BadArgumentException -> exceptions souleve si 'w' ne respecte pas les conditions
	 */
	public static void checkWord(String function_name, String w) throws BadArgumentException {
		if(w.length() == 0)
			throw new BadArgumentException(function_name + ": w est une chaine vide");
		for(int i=0; i<w.length(); i++)
			if(w.charAt(i) == PatriciaTrie.EPSILON.charAt(0))
				throw new BadArgumentException(function_name + ": w contient le caractere EPSILON");
	}


	/**
	 * retourne la liste contenenant les mots present dans le fichier passe en parametre
	 * @param pathFic:String -> chemin vers le fichier
	 * @return ArrayList<String> -> contenant les mots present dans le fichier. La liste vide si
	 *                              un probleme s'est produit
	 */
	public static ArrayList<String> fic_to_listWord(String pathFic){

		File f;
		FileReader fd = null;
		String buffer ="";
		int i;
		ArrayList<String> res = new ArrayList<String>();

		try{
			if( !pathFic.endsWith(".txt") )
				throw new BadArgumentException("Tools.fic_to_listWord: Le fichier n'est pas au bon format"); 

			f = new File(pathFic);
			if(!f.exists()){
				throw new BadArgumentException("Tools.fic_to_listWord: Le fichier n'existe pas");
			}

			fd = new FileReader(f);

			/* remplissage de la liste */
			while((i = fd.read()) != -1){
				if(i == (int) ' ' || i == (int) '\n' 
						|| i == (int) '\t' || i == (int) '\f' || i == (int) '\r'){
					if(buffer.compareTo("") != 0){
						res.add(buffer);
						buffer = "";
					}
				}else buffer += (char) i; 
			}
		}catch(BadArgumentException e){
			e.getMessage();
			return null;
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if(fd != null) fd.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return res;
	}

	public static ArrayList<String> file_to_listWord(File f){

		FileReader fd = null;
		String buffer ="";
		int i;
		ArrayList<String> res = new ArrayList<String>();

		try{
			fd = new FileReader(f);

			/* remplissage de la liste */
			while((i = fd.read()) != -1){
				if(i == (int) ' ' || i == (int) '\n' 
						|| i == (int) '\t' || i == (int) '\f' || i == (int) '\r'){
					if(buffer.compareTo("") != 0){
						res.add(buffer);
						buffer = "";
					}
				}else buffer += (char) i; 
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if(fd != null) fd.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return res;
	}



	public static ArrayList<String> shakespeareFics() throws WrongAccessException{
		
		ArrayList<String> res = new ArrayList<String>();
		File shakespeareDir = new File("./fichiers_test/Shakespeare");

		if(!shakespeareDir.isDirectory())
			throw new WrongAccessException("shakespeareWords: le dossier Shakespeare n'a pas ete trouve");

		

		for(File f: shakespeareDir.listFiles())
			res.add(f.getName());

		return res;
	}



	public static ArrayList<String> shakespeareWords() throws WrongAccessException{

		ArrayList<String> res = new ArrayList<String>();
		File shakespeareDir = new File("./fichiers_test/Shakespeare");

		if(!shakespeareDir.isDirectory())
			throw new WrongAccessException("shakespeareWords: le dossier Shakespeare n'a pas ete trouve");

		for(File f: shakespeareDir.listFiles())
			res.addAll(file_to_listWord(f));

		
		return res;
	}



}
