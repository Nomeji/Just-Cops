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
	public void monter(){
		if(cptEtages<nbEtages*TAILLETAGE){
			if(monte){
				localisation.y++;
				cptEtages++;
				if(cptEtages==nbEtages*TAILLETAGE-1){
					monte=false;
				}
			}
		}
		if(cptEtages>=0){
			if(!monte){
				localisation.y--;
				cptEtages--;
				if(cptEtages==0){
					monte=true;
				}
			}
		}
	}
	//L'ascenseur descend
	public void descendre(){
		if(cptEtages!=0){
			localisation.y--;
		}
		else monter();
	}
	//Méthode pour affichage du personnage
	public void draw(SpriteBatch batch){
		batch.draw(texture, localisation.x,localisation.y);
	}
}
