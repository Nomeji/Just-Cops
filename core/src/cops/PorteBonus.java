package cops;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class PorteBonus extends Porte{
	//Attribut
	private boolean utilisable=true;
	//Constructeur
	PorteBonus(Vector2 localisation){
		super(localisation);
		super.texture=new Texture("../core/assets/porteBonus.png");
	}
	//Utilisation de la porte
	public int use(Personnage perso){
		//Gestion de la localisation du personnage
		if((((perso.getLocalisation().x>=localisation.x && perso.getLocalisation().x<=localisation.x+taille.x) && (perso.getLocalisation().y>=localisation.y && perso.getLocalisation().y<=localisation.y+taille.y && !utiliser)))&& perso.getAction(0)){
			//Quand une porte est utilisé elle redevient une porte normale
			super.texture=new Texture("porte.png");
			utiliser=true;
			return(1);
		}
		return(-1);
	}
}
