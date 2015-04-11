package cops;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Tile {
	//Taille d'un tile
	public final static int TTILE=3;
	//Numero du tile
	private int num;
	//Taille du tile
	private Vector2 taille;
	//Localisation du tile
	private Vector2 localisation;
	//Gestion d'une tile area
	private TextureRegion tile;
	
	//Constructeur
	Tile(Vector2 localisation, TextureRegion tile,int num){
		taille=new Vector2();
		taille.x=TTILE;
		taille.y=TTILE;
		this.num=num;
		this.localisation=localisation;
		this.tile=tile;
	}
	//Affichage du tile
	public void afficher(SpriteBatch batch){
		batch.draw(tile,localisation.x,localisation.y);
	}
	//Getteur de position
	public Vector2 getLocalisation(){
		return(localisation);
	}
	public int getNum(){
		return(num);
	}
}
