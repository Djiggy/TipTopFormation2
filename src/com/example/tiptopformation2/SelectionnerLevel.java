package com.example.tiptopformation2;

import Core.Intents;
import Core.Quizz;
import Core.THEMES;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SelectionnerLevel extends Activity implements OnClickListener, Intents {

	//titre de l'activit�
	private static final String phrase ="S�lectionner le niveau pour le th�me ";
	
	//couleur de grisement
	private static final String colorGriserBouton="#FF7777";
	private static final String colorAfficherBouton="#83B913";
	
	private String theme;//th�me choisi
	private int levelUserTheme;//level du joueur sur ce th�me
	private int levelChoisiParUtilisateur=0;
	
	//Les variables de vues
	private Button facile;
	private Button moyen;
	private Button difficile;
	private Button jouer;
	
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selectionner_level);

		/*
		 * On r�cup�re le th�me et le niveau du joueur sur ce th�me
		 */
		TextView titre = (TextView) findViewById(R.id.textView1);
		 theme = "";// Au cas o� l'intent ne marche pas
		 Intent intent = getIntent();
		 if (intent != null) {
            theme =intent.getStringExtra(INTENT_THEME);
            levelUserTheme = intent.getIntExtra(INTENT_LEVEL, levelUserTheme);
        }
		
		titre.setText(phrase+theme);
		
		/*
		 * On initialise nos boutons de vue et on grise les 
		 * boutons qui sont interdits gr�ce � la fonction
		 *  ::griserLevelsInterdit(levelUser)
		 */
		facile = (Button) findViewById(R.id.facile);
		moyen = (Button) findViewById(R.id.moyen);
		difficile = (Button) findViewById(R.id.difficile);
		jouer = (Button) findViewById(R.id.jouer);
		griserLevelsInterdit();
		
		//Les listeners
		facile.setOnClickListener(this);
		moyen.setOnClickListener(this);
		difficile.setOnClickListener(this);
		jouer.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		
		
		if(v == facile) {
			levelChoisiParUtilisateur=1;
			
		}
		else if(v == moyen) {
			if (levelUserTheme>=2)//on grise le bouton moyen
				levelChoisiParUtilisateur=2;
			else levelChoisiParUtilisateur=-1;
			
		}
		else if(v == difficile) {
			if (levelUserTheme>=3)//on grise le bouton difficile
				levelChoisiParUtilisateur=3;
			else levelChoisiParUtilisateur=-1;
			
		}
		
		
		/*
		 * Si la personne n'a pas le niveau neccessaire on affiche un petit message
		 * Si le personne a le niveau requis on fait apparaitre un bouton "JOUER"
		 */
		if (levelChoisiParUtilisateur == -1)
			Toast.makeText(this, "Vous n'avez pas encore le niveau neccessaire" , Toast.LENGTH_LONG).show();
		
		if (levelChoisiParUtilisateur != -1 && levelChoisiParUtilisateur != 0)
			jouer.setVisibility(View.VISIBLE);
		
		//D�s qu'on clique sur "Jouer"
		if (v == jouer){
			Intent intent = new Intent(SelectionnerLevel.this, Quizz.class);
			intent.putExtra(INTENT_LEVEL_CHOISI, levelChoisiParUtilisateur);
			startActivity(intent);
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.selectionner_level, menu);
		return true;
	}

	
	
	private void griserLevelsInterdit (){
		
		facile.setBackgroundColor(Color.parseColor(colorAfficherBouton));
		moyen.setBackgroundColor(Color.parseColor(colorAfficherBouton));
		difficile.setBackgroundColor(Color.parseColor(colorAfficherBouton));
		if (levelUserTheme<3){//on grise le bouton difficile
			difficile.setBackgroundColor(Color.parseColor(colorGriserBouton));
		}
		if (levelUserTheme<2){//on grise le bouton moyen
			moyen.setBackgroundColor(Color.parseColor(colorGriserBouton));
		}
	}

	

}
