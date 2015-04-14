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
	public void use(){
		super.texture=new Texture("porte.png");
	}
}
