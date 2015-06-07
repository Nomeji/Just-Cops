package cops;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Personnage {
	//Pour le mode deux joueurs
	static final int JOUEURMAX=2;
	static final int NBTEXTURES=3;
	//Nombre de joueur stock�
	static int nbJoueurs=0;
	//Attribut
	//Vector2 g�re les d�placements d'une entit�
	private Vector2 localisation;
	private Vector2 mouvement;
	//Gestion des textures
	private Texture perso;
	private Texture sesTexturesDroites[];
	private Texture sesTexturesGauches[];
	// Animation
	private Animation animMarcheGauche;
	private Animation animMarcheDroite;
	//Gestion de la vie
	private int vie;
	//Gestion de la taille du personnage
	private Vector2 taille;
	//Evenement lier au personnage
	private Evenement event;
	//Compteur de temps pour le saut
	private int cptJump=0;
	//Compteur de texture
	private int cptText=0;
	//Bool�en pour le saut
	private boolean enCours=false;
	//Constructeur
	Personnage(Vector2 localisation){
		//Initialisation des positions
		this.localisation=new Vector2();
		mouvement = new Vector2();
		//Entr� de la texture
		if(nbJoueurs==0){
			perso=new Texture("PolicierMoveRight0.png");
		}
		//Initialisation de la taille du personnage
		taille=new Vector2();
		taille.x=perso.getHeight();
		taille.y=perso.getWidth();
		//Donne la localisation
		this.localisation=localisation;
		//Initialisation de la vie
		vie=3;
		//Cr�ation des evenements du personnage
		event= new Evenement();
		//Gestion des texture
		sesTexturesDroites=new Texture[NBTEXTURES];
		sesTexturesDroites[0]=new Texture("PolicierMoveRight0.png");
		sesTexturesDroites[1]=new Texture("PolicierMoveRight1.png");
		sesTexturesDroites[2]=new Texture("PolicierMoveRight2.png");
		sesTexturesGauches=new Texture[NBTEXTURES];
		sesTexturesGauches[0]=new Texture("PolicierMoveLeft0.png");
		sesTexturesGauches[1]=new Texture("PolicierMoveLeft1.png");
		sesTexturesGauches[2]=new Texture("PolicierMoveLeft2.png");
		//Animation
		//animMarcheGauche = new Animation(0.025f,sesTexturesGauches);
		//On augmente le nombre de joueur
		nbJoueurs++;
	}
	//M�thode pour affichage du personnage
	public void draw(SpriteBatch batch){
		batch.draw(perso, localisation.x,localisation.y);
	}
	//M�thode pour la perte de vie
	public void blesser(){
		vie--;
	}
	
	//actions et d�placements
	public void update(){
		//Deplacement vers la droite
		if(event.getDeplacement(Evenement.DROITE)){
			mouvement.x+=3;
			perso=sesTexturesDroites[cptText];
			cptText++;
			if(cptText>=NBTEXTURES){
				cptText=0;
			}
		}
		//Deplacement vers la gauche
		else if(event.getDeplacement(Evenement.GAUCHE)){
			mouvement.x-=3;
			perso=sesTexturesGauches[cptText];
			cptText++;
			if(cptText>=NBTEXTURES){
				cptText=0;
			}
		}
		//Saut
		if(event.getAction(0)|| enCours){
			//Initialisation de la s�paration du saut
			if(!enCours){
				enCours=true;
				cptJump=0;
			}
			//S�paration du saut
			else if(enCours && cptJump<=10){
				if(cptJump==0){
					mouvement.y+=2;
				}
				else if(cptJump==1){
					mouvement.y+=3;
				}
				else if(cptJump==3){
					mouvement.y+=4;
				}
				else if(cptJump==4){
					mouvement.y+=5;
				}
				else if(cptJump==5){
					mouvement.y+=6;
				}
				else if(cptJump==6){
					mouvement.y-=6;
				}
				else if(cptJump==7){
					mouvement.y-=5;
				}
				else if(cptJump==8){
					mouvement.y-=4;
				}
				else if(cptJump==9){
					mouvement.y-=3;
				}
				else if(cptJump==10){
					mouvement.y-=2;
				}
				cptJump++;
			}
			//Fin du saut
			else{
				enCours=false;
			}
		}
	}
	//Mise � jour des Evenement
	public void updateEvenement(){
		event.update();
	}
	//On s'occupe du deplacement
	public void deplacement(){
		localisation.x+=mouvement.x;
		localisation.y+=mouvement.y;
		mouvement.x=0;
		mouvement.y=0;
	}
	//Getteur de position
	public Vector2 getLocalisation(){
		return(localisation);
	}
	//Getteur de mouvement
	public Vector2 getMouvement(){
		return(mouvement);
	}
	//Getteur de taille
	public Vector2 getTaille(){
		return(taille);
	}
	//Setteur de mouvement
	public void setMouvement(Vector2 move){
		mouvement.x=move.x;
		mouvement.y=move.y;
	}
	//Getteur du centre du perso
	public Vector2 getCenter(){
		return(new Vector2(localisation.x+(taille.x/2),localisation.y+(taille.y/2)));
	}
	//Getteur de mouvement en cours
	public boolean getInMouv(){
		return(enCours);
	}
}
