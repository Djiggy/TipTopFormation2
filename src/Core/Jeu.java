package Core;

import android.content.Context;


/*
 * Jeu est un Singleton. Cela �vite d'utiliser des intents. Moins de code. Plus simple.
 */
public class Jeu  {
	
	private Context contexte;
	private static Jeu instance;
	private User user;
	private QuizzModel quizz;
	
	
	public QuizzModel getQuizz() {
		return quizz;
	}

	private Jeu (){
		user  = new User();
		
	}
	
	public static Jeu getInstance() {
	        if (null == instance) { // Premier appel
	            instance = new Jeu();
	        }
	        return instance;
	}
	 
	
	public User getUser() {
		return user;
	}
	

	public int getLevelByTheme (THEMES theme){
		return user.getLevelByTheme(theme);
	}

	public void initialiserLeQuizz() {
		// TODO Auto-generated method stub
		quizz = new QuizzModel();
		
	}

	public void recommencer(){
		/*
		 * Pour recommencer une partie 
		 * Il faut sauvegarder le th�me et le level choisi
		 * Mettre quizz � null et recr�er un quizz avec le th�me et le level
		 */
		
		//sauvegarde du th�me et du level
		THEMES theme = quizz.getTheme();
		int level = quizz.getLevelChoisi();
		
		quizz = null;
		quizz = new QuizzModel(theme, level);
		
	}
	
	/*
	 * Cette m�thode pour r�cup�rer les ressources (fichiers
	 * csv). En effet, si une classe n'est pas une activit�,
	 * elle ne peut pas acceder au ressource. L'astuce consiste
	 * donc � passer en argument le contexte d'une activit� android
	 * � une classe java.
	 */
	public void initialiserContexteAndroid(Context context) {
		
		this.contexte = context;
		
	}

	public Context getContexte() {
		return contexte;
	}
	
	


}
