package cops;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Ennemie {
	//Attribut
	//Position et mouvement
	private Vector2 localisation;
	private Vector2 mouvement;
	//Texture
	private Texture ennemie;
	//Taille
	private Vector2 taille;
	//Constructeur
	Ennemie(int posX,int posY){
		localisation=new Vector2(posX,posY);
		taille=new Vector2();
		ennemie=new Texture("BanditNoMoveRight.png");
		taille.x=ennemie.getWidth();
		taille.y=ennemie.getHeight();
	}
}
