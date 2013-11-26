package Core;

import com.example.tiptopformation2.Quizz;
import com.example.tiptopformation2.SelectionnerLevel;

import android.content.Intent;
import android.content.res.Resources.Theme;
import android.os.Parcel;
import android.os.Parcelable;


/*
 * Jeu est un Singleton. Cela �vite d'utiliser des intents. Moins de code. Plus simple.
 */
public class Jeu  {
	
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
	
	


}
