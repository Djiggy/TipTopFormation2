package Exercices;

import com.example.tiptopformation2.MultiChoixJeu;
import com.example.tiptopformation2.Quizz;

import android.content.Intent;
import android.util.Log;
import Core.EXERCICES;

public class MultiChoix extends QuestionReponse {
	
	

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
	
	public void remplirLesVariables (){
		Log.w("MultiChoix", "remplirLesVariables()");
		//String chaine[] = lireCSV(null);
		//id = Integer.parseInt(chaine[0]);
		//question= chaine[1];
		
		// lesElements = extraireTousElementsTableau(chaine, 2);
		
	
		 id = 1;
		 question= "Cocher les bonnes r�ponses";
		 lesElements[0][0] = "Bonne r�ponse";
		 lesElements[1][0] = "Bonne r�ponse";
		 lesElements[2][0] = "Mauvaise r�ponse";
		 lesElements[3][0] = "Mauvaise r�ponse";
		 NbBonnesReponses = 2;
	}
	
	
	
}
