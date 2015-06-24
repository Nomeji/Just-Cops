package cops;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
	//Mouvement
	private boolean enMouvement=true;
	private boolean droite=true;
	//Constructeur
	Ennemie(int posX,int posY){
		localisation=new Vector2(posX,posY);
		mouvement=new Vector2();
		taille=new Vector2();
		ennemie=new Texture("BanditNoMoveRight.png");
		taille.x=ennemie.getWidth();
		taille.y=ennemie.getHeight();
	}
	//Affichage des Ennemies
	public void draw(SpriteBatch batch){
		batch.draw(ennemie, localisation.x,localisation.y);
	}
	//Mouvement des ennemies
	public void mouvement(ArrayList<Tile> tiles){
		//D�placement vers la droite
		if(droite){
			//On parcours la totalit� des tiles
			for(int i=0;i<tiles.size();i++){
				//On v�rifie celui qui est situ� apr�s lui
				if((localisation.x+taille.x+3>tiles.get(i).getLocalisation().x)&&(localisation.x+taille.x<=tiles.get(i).getLocalisation().x)&&(localisation.y==tiles.get(i).getLocalisation().y)){
					//Si il est passable alors le mouvement est incr�ment�
					if(tiles.get(i).getNum()!=1){
						localisation.x++;
					}
					//Sinon on change de sens
					else{
						droite=false;
					}
				}
			}
		}
		//D�placement vers la gauche
		else{
			//On parcourt la totalit� des tiles
			for(int i=0;i<tiles.size();i++){
				//On v�rifie que ce qu'il y a d�ri�re n'est pas un bloc plein
				if((localisation.x>=tiles.get(i).getLocalisation().x)&&(localisation.x-3<tiles.get(i).getLocalisation().x)&&(localisation.y==tiles.get(i).getLocalisation().y)){
					if(tiles.get(i).getNum()!=1){
						localisation.x--;
					}
					//Sinon on change de sens
					else{
						droite=true;
					}
				}
			}
		}
	}
}