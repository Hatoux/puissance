package tools.experiences;

import hybride.Hybride;
import patricia.PatriciaTrie;

public class ComparateurHauteur {

	public static Resultat compare(Hybride h, PatriciaTrie p){
		return new Resultat(h.hauteur(),p.hauteur());
	}
	
}
