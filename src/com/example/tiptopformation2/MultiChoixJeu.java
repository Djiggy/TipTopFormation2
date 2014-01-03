package com.example.tiptopformation2;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.tiptopformation2.R.id;

import Core.Jeu;
import Core.QuizzModel;
import Exercices.MultiChoix;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MultiChoixJeu extends Activity  {

		/*
		 * Note sur les levels
		 * Level 1 : 2 �lements + on affiche le nombre de bonnes r�ponses
		 * Level 2 : 4 �l�ments + on affiche le nombre de bonnes r�ponses
		 * Level 3 : 4 �l�ments + on affiche PAS le nombre de bonnes r�ponses
		 */
		
		
		//Donn�es du Mod�le 
		private MultiChoix  instanceDeLaQuestion;
		private int niveau;
		private int NbBonnesReponses; 
		int numeroQuestion;
		int nombreDeQuestion;
		private String question;
		private String[][] lesElements; 
		
		
		// Proposition1 & 2 sont les deux bonnes r�ponses.
		// Dans le cas o� il n'y en a qu'une, seul proposition1 est une bonne r�ponse
		private String proposition1, proposition2, proposition3, proposition4;
		private String image1,image2,image3,image4;
		private String correction = "Il fallait s�lectionner la bonne r�ponse !";

		// Variables correspondant aux �l�ments de l'XML
		private TextView questionPosee, nombreBonneReponse, correctionEcrite, numeroQuestionVue;
		private CheckBox reponse1, reponse2, reponse3, reponse4;
		private ImageView img1, img2, img3, img4;
		private Button submit;		
		
		// Cette liste permettra de distribuer al�atoirement les variables.
		List<String> list = new ArrayList<String>();
		
		private boolean boutonSuivantAfficher=false;
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_multi_choix_jeu);
			initialiserModel();
			initialiserVariables();
			gererComposants();
			melanger();
			
			
			submit.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					boolean isRight;

					// Une fois qu'il a cliqu� sur le bouton OK, on v�rifie s'il
					// n'a pas coch� une mauvaise r�ponse ou qu'il n'a pas oubli� de bonnes r�ponses
					isRight = verifierResultats();

					// En fonction de son r�sultat, on affiche une correction correspondante
					
					
						if (!boutonSuivantAfficher){
							if (!isRight) 
								Toast.makeText(MultiChoixJeu.this, "Mauvaise r�ponse !\n"+correction, Toast.LENGTH_SHORT).show();
							else 
								Toast.makeText(MultiChoixJeu.this, "Bonne r�ponse !\n"+correction, Toast.LENGTH_SHORT).show();
							
							submit.setText("Suivant");
							boutonSuivantAfficher = true;
						}
						else {
							Intent intent = new Intent(MultiChoixJeu.this, Quizz.class);
							startActivity(intent);
						}
						
						
					

					reponse1.setEnabled(false);
					reponse2.setEnabled(false);
					reponse3.setEnabled(false);
					reponse4.setEnabled(false);
				}
			});
			
		}

		private void initialiserModel() {
			// TODO Auto-generated method stub
			/*
			 * On r�cup�re l'instance de notre question (le mod�le de celle-ci)
			 * On r�cup�re donc toutes les infos de la question :
			 * - la question
			 * - les �l�ments (bonnes r�ponses et mauvaises)
			 * - le num�ro de la question
			 * - le nombre de question
			 * - la correction
			 */
			instanceDeLaQuestion = (MultiChoix) Jeu.getInstance().getQuizz().getQuestionInstance();
			niveau = Jeu.getInstance().getQuizz().getLevelChoisi();
			NbBonnesReponses = instanceDeLaQuestion.getNbBonnesReponses();
			numeroQuestion = instanceDeLaQuestion.getNumeroDeLaQuestion();
			nombreDeQuestion = QuizzModel.getNbquestionparquizz();
			question = instanceDeLaQuestion.getQuestion();
			lesElements = new String[5][2];
			lesElements = instanceDeLaQuestion.getLesElements();
			correction = instanceDeLaQuestion.getPhraseCorrection();
			
			
			
			//on remplace les �l�ments par les vrais du model
			proposition1 = lesElements[0][0];//la r�ponse texte
			image1 =  lesElements[0][1];//l'image de la r�ponse
			proposition2 = lesElements[1][0];
			image1 =  lesElements[1][1];
			proposition3 = lesElements[2][0];
			image1 =  lesElements[2][1];
			proposition4 = lesElements[3][0];
			image1 =  lesElements[3][1];
			
			
			
			
			
		}

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.home, menu);
			return true;
		}

		
		
		public void initialiserVariables(){
			questionPosee = (TextView) findViewById(id.Question);
			nombreBonneReponse = (TextView) findViewById(id.NombreBonneReponse);
			reponse1 = (CheckBox) findViewById(id.checkBox1);
			reponse2 = (CheckBox) findViewById(id.checkBox2);
			reponse3 = (CheckBox) findViewById(id.checkBox3);
			reponse4 = (CheckBox) findViewById(id.checkBox4);
			img1 = (ImageView) findViewById(id.imageView1);
			img2 = (ImageView) findViewById(id.ImageView01);
			img3 = (ImageView) findViewById(id.ImageView02);
			img4 = (ImageView) findViewById(id.ImageView03);
			correctionEcrite = (TextView) findViewById(id.correction);
			submit = (Button) findViewById(id.Valider);
			numeroQuestionVue = (TextView) findViewById(id.numeroQuestion);
		
			
		}

		public void gererComposants(){
			// Il ne faut pas afficher la correction
			correctionEcrite.setText("");

			// Ajout des propositions dans la liste, globales quel que soit le niveau
			list.add(proposition1);
			list.add(proposition2);
			
			if (niveau == 1){
				// on d�sactive deux checkbox, deux images & le texte "nombre de bonne r�ponse"
				reponse3.setVisibility(-1);
				reponse4.setVisibility(-1);
				img3.setVisibility(-1);
				img4.setVisibility(-1);
				nombreBonneReponse.setVisibility(-1);
			}
			else if (niveau == 2){
				// Affichage : "Nombre de bonne r�ponse"
				if (NbBonnesReponses > 1)
					nombreBonneReponse.setText("Bonnes r�ponses : "+NbBonnesReponses);
				else 
					nombreBonneReponse.setText("Bonne r�ponse : "+NbBonnesReponses);
				
				// On ajoute les deux propositions dans le cas o� il y a 4 propositions
				list.add(proposition3);
				list.add(proposition4);
			}
			else {
				// on d�sactive le texte "nombre de bonne r�ponse"
				nombreBonneReponse.setVisibility(-1);
				
				// On ajoute les deux propositions dans le cas o� il y a 4 propositions
				list.add(proposition3);
				list.add(proposition4);
			}

			questionPosee.setText(question);
			numeroQuestionVue.setText(numeroQuestion+"/"+nombreDeQuestion);
		}

		public void melanger(){

			if (niveau == 1){

				// M�lange via la m�thode shuffle
				Collections.shuffle(list);
				// Attribution des valeurs
				reponse1.setText(list.get(0));
				reponse2.setText(list.get(1));
			}
			else {

				Collections.shuffle(list);
				reponse1.setText(list.get(0));
				reponse2.setText(list.get(1));
				reponse3.setText(list.get(2));
				reponse4.setText(list.get(3));
			}
		}

		public boolean verifierResultats(){
			if (niveau == 1){
				// on v�rifie si reponse1 est �gal � la proposition 2 ( fausse )
				// mais aussi si elle est coch�
				if ((reponse1.getText().equals(proposition2)) && (reponse1.isChecked()))
					return false;
				
				// On v�rifie si rien est coch�
				else if ( (! reponse1.isChecked()) && (! reponse2.isChecked()))
					return false;
			}
			else {
				if (NbBonnesReponses == 1){
					if(getNbChecked() != 1)
						return false;
					else {
						// on v�rifie si l'une des r�ponses est fausse et si elle est s�lectionn� 
						if ((! reponse1.getText().equals(proposition1)) && (reponse1.isChecked()))
							return false;
						if ((! reponse2.getText().equals(proposition1)) && (reponse1.isChecked()))
							return false;
						if ((! reponse3.getText().equals(proposition1)) && (reponse1.isChecked()))
							return false;
						if ((! reponse4.getText().equals(proposition1)) && (reponse1.isChecked()))
							return false;
					}
				}
				
				else { // dans le cas o� 2 bonnes r�ponses sont coch�s
					if(getNbChecked() != 2)
						return false;
					
					if (((! reponse1.getText().equals(proposition1))||(! reponse1.getText().equals(proposition2))) && (reponse1.isChecked()))
						return false;
					if (((! reponse2.getText().equals(proposition1))||(! reponse1.getText().equals(proposition2))) && (reponse1.isChecked()))
						return false;
					if (((! reponse3.getText().equals(proposition1))||(! reponse1.getText().equals(proposition2))) && (reponse1.isChecked()))
						return false;
					if (((! reponse4.getText().equals(proposition1))||(! reponse1.getText().equals(proposition2))) && (reponse1.isChecked()))
						return false;
				}
			}
			
			// Si aucune erreur n'est trouv�, on renvoit vrai.
			return true;
		}
		
		// fonction qui retourne le nombre de checkbox coch�
		public int getNbChecked(){
			int value = 0;
			
			if (reponse1.isChecked())
				value++;
			if (reponse2.isChecked())
				value++;
			if (reponse3.isChecked())
				value++;
			if (reponse4.isChecked())
				value++;
			return value;
		}

}
