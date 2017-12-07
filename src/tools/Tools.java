package tools;

import exceptions.BadArgumentException;
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



}
