package cops;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Ascenseur {
	//Taille d'un étage
	final static int TAILLETAGE=33;
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
	Ascenseur(Vector2 localisation,int nbEtages){
		this.localisation=new Vector2();
		this.localisation=localisation;
		texture=new Texture("../core/assets/ascenseur.png");
		taille=new Vector2();
		taille.x=texture.getHeight();
		taille.y=texture.getWidth();
		this.nbEtages=nbEtages;
	}
	//L'ascenseur monte
	public void monter(Personnage perso){
		//L'ascenseur monte
		if(cptEtages<=nbEtages*TAILLETAGE+1){
			if(monte){
				localisation.y++;
				cptEtages++;
				if(cptEtages==nbEtages*TAILLETAGE){
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
		//On vérifie l'abscisse dans le cas où il monte
		if((perso.getLocalisation().x>=localisation.x && perso.getLocalisation().x<=localisation.x+taille.x)&&(perso.getLocalisation().x+perso.getTaille().x>=localisation.x && perso.getLocalisation().x+perso.getTaille().x<=localisation.x+taille.x)&& monte){
			//Puis l'ordonné
			if(perso.getLocalisation().y>=localisation.y && perso.getLocalisation().y<=localisation.y+1){
				perso.getLocalisation().y++;
			}
		}
		//On vérifie l'abscisse dans la cas où l'ascenseur descend
		if((perso.getLocalisation().x>localisation.x && perso.getLocalisation().x<localisation.x+taille.x)&&(perso.getLocalisation().x+perso.getTaille().x>=localisation.x && perso.getLocalisation().x+perso.getTaille().x<=localisation.x+taille.x)&& !monte){
			//Puis l'ordonné
			if(perso.getLocalisation().y>=localisation.y && perso.getLocalisation().y<=localisation.y+2){
				perso.getLocalisation().y--;
			}
		}
	}
	//Méthode pour affichage du personnage
	public void draw(SpriteBatch batch){
		batch.draw(texture, localisation.x,localisation.y);
	}
}
