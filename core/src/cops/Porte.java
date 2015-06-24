package cops;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Porte {
	//Attribut
	Vector2 localisation;
	Vector2 taille;
	Texture texture;
	//Booléen quand utilisé
	boolean utiliser=false;
	//Constructeur
	public Porte(Vector2 localisation){
		//Entré de la localisation
		this.localisation=new Vector2();
		this.localisation=localisation;
		//la taille dépend de son image
		taille=new Vector2();
		texture=new Texture("porte.png");
		taille.x=texture.getWidth();
		taille.y=texture.getHeight();
	}
	//Affichage
	public void draw(SpriteBatch batch){
		batch.draw(texture, localisation.x,localisation.y);
	}
	//Getteur de position
	public Vector2 getLocalisation(){
		return(localisation);
	}
	//Getteur de taille
	public Vector2 getTaille(){
		return(taille);
	}
	public int use(Personnage perso){
		return(2);
	}
}
