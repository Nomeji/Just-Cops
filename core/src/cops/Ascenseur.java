package cops;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Ascenseur {
	//Taille d'un étage
	private int tailleEtage;
	//Attribut
	private Vector2 localisation;
	private Vector2 taille;
	private Texture texture;
	private int nbEtages;
	//Compte les placements de l'ascenseur
	private int cptEtages;
	//Booleen pour monter ou descendre
	private boolean monte=true;
	//Constructeur
	Ascenseur(Vector2 localisation,int nbEtages,int tailleEtage){
		this.localisation=new Vector2();
		this.localisation=localisation;
		texture=new Texture("../core/assets/ascenseur.png");
		taille=new Vector2();
		taille.x=texture.getHeight();
		taille.y=texture.getWidth();
		this.nbEtages=nbEtages;
		this.tailleEtage=tailleEtage;
	}
	//L'ascenseur monte et le personnage aussi si il est dans un ascenseur
	public void monter(Personnage perso, int numAscenseur){
		//L'ascenseur monte
		if(cptEtages<=nbEtages*tailleEtage+1){
			if(monte){
				localisation.y++;
				cptEtages++;
				if(cptEtages==nbEtages*tailleEtage){
					monte=false;
				}
			}
		}
		//L'ascenseur descend
		if(cptEtages>=0){
			if(!monte){
				localisation.y--;
				cptEtages--;
				if(cptEtages==0){
					monte=true;
				}
			}
		}
		//Si le personnage entre dans un ascenseur alors le processus commence
		if(perso.getAscenseur()==-1){
			//On vérifie l'abscisse dans le cas où il monte
			if((perso.getLocalisation().x>=localisation.x && perso.getLocalisation().x<=localisation.x+taille.x)&&(perso.getLocalisation().x+perso.getTaille().x>=localisation.x && perso.getLocalisation().x+perso.getTaille().x<=localisation.x+taille.x)&& monte){
				//Puis l'ordonné
				if(perso.getLocalisation().y>=localisation.y &&	perso.getLocalisation().y+perso.getTaille().y<=localisation.y+taille.y){
					perso.getLocalisation().y++;
					perso.setAscenseur(numAscenseur);
				}
			}
			//On vérifie l'abscisse dans la cas où l'ascenseur descend
			if((perso.getLocalisation().x>localisation.x && perso.getLocalisation().x<localisation.x+taille.x)&&(perso.getLocalisation().x+perso.getTaille().x>=localisation.x && perso.getLocalisation().x+perso.getTaille().x<=localisation.x+taille.x)&& !monte){
				//Puis l'ordonné
				if(perso.getLocalisation().y>=localisation.y &&	perso.getLocalisation().y+perso.getTaille().y<=localisation.y+taille.y){
					perso.getLocalisation().y--;
					perso.setAscenseur(numAscenseur);
				}
			}
		}
		//Le processus continue si le personnage reste dans l'ascenseur
		//Vérification si il s'agit bien du même ascenseur
		if(perso.getAscenseur()!=-1 && numAscenseur==perso.getAscenseur()){
			//Quand il monte
			if((perso.getLocalisation().x>=localisation.x && perso.getLocalisation().x<=localisation.x+taille.x)&&(perso.getLocalisation().x+perso.getTaille().x>=localisation.x && perso.getLocalisation().x+perso.getTaille().x<=localisation.x+taille.x)&& monte){
				perso.getLocalisation().y++;
			}
			//Quand il descend
			else if((perso.getLocalisation().x>localisation.x && perso.getLocalisation().x<localisation.x+taille.x)&&(perso.getLocalisation().x+perso.getTaille().x>=localisation.x && perso.getLocalisation().x+perso.getTaille().x<=localisation.x+taille.x)&& !monte){
				perso.getLocalisation().y--;
			}
			//Quand il est en dehors de l'ascenseur
			else{
				perso.setAscenseur(-1);
			}
		}
	}
	//Méthode pour affichage du personnage
	public void draw(SpriteBatch batch){
		batch.draw(texture, localisation.x,localisation.y);
	}
}
