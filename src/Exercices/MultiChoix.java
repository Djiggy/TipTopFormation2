package Exercices;

import Core.EXERCICES;
import android.util.Log;

public class MultiChoix extends QuestionReponse {
	
	private int NbBonnesReponses;
	private String[][] lesElements;

	/*
	 * Historique des ids pour ne pas avoir 2 fois la m�me question.
	 */
	protected static int nbQuestionDejaAjouter=0;
	protected static int[] tabQuestionHistorique;
	
	public MultiChoix() {
		super();
		typeExo = EXERCICES.MULTICHOIX;
		tabQuestionHistorique = new int[getJeu().getQuizz().getNbquestionparquizz()];
		lesElements = new String[5][5];
		remplirLesVariables();
		Log.w("MultiChoix", "Constructeur");
		
		
	}
	
	public int getNbBonnesReponses() {
		return NbBonnesReponses;
	}

	/*
	 *  - tab[0][0] = Element 0 : le texte ET tab[0][1] = Element 0 : l'image
	 *  - tab[1][0] = Element 1 : le texte ET tab[1][1] = Element 1 : l'image
	 *  ...
	 *  Cette m�thode permet donc d'ajouter autant d'info que l'in veut par �l�ment :
	 *  correction personnalis�, un deuxi�me images, une aide, une autre formulation du
	 *  texte...
	 */
	public String[][] getLesElements() {
		return lesElements;
	}
	
	public void remplirLesVariables (){
		Log.w("MultiChoix", "remplirLesVariables()");
		//String chaine[] = lireCSV(null);
		//id = Integer.parseInt(chaine[0]);
		//question= chaine[1];
		
		// lesElements = extraireTousElementsTableau(chaine, 2);
		
	
		 id = "1";
		 question= "Cocher les bonnes r�ponses";
		 lesElements[0][0] = "Bonne r�ponse 1";
		 lesElements[0][1] = "toxique";
		 lesElements[1][0] = "Bonne r�ponse 2";
		 lesElements[2][0] = "Mauvaise r�ponse 1";
		 lesElements[3][0] = "Mauvaise r�ponse 2";
		 NbBonnesReponses = 2;
		 phraseCorrection = "La correction de la question";
	}
	
	
	
}
