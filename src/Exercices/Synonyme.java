package Exercices;

public class Synonyme extends QuestionReponse{

	
	private static final String phraseEntete="Remettez les �l�ments dans l'ordre";//a adapter pour les th�mes ...
	private String texte;
	private String[] lesElements;
	
	public Synonyme() {
		super();
		
	
		//on prend le csv
		lesElements = lireCSV(getTabQuestionHistorique());
		
	}
	
	public void remplirLesVariables (int id, String texte, String[] lesElements ){
		
		
	}
	
	
	
	
}
