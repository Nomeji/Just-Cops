package cops;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class Evenement {
	//Entrées des touches
	static final int DROITE=0;
	static final int GAUCHE=1;
	//Attribut
	private boolean[] deplacement;
	private boolean[] action;
	//Constructeur
	Evenement(){
		deplacement= new boolean[2];
		action= new boolean[3];
	}
	//Mise à jour des evenements
	public void update(){
		//Mise à jour des Déplacements
		deplacement[Evenement.DROITE]=Gdx.input.isKeyPressed(Keys.RIGHT);
		deplacement[Evenement.GAUCHE]=Gdx.input.isKeyPressed(Keys.LEFT);
		//Mise à jour des Actions
		action[0]=Gdx.input.isKeyPressed(Keys.UP);
	}
	//Test du déplacement
	public boolean getDeplacement(int id){
		return(deplacement[id]);
	}
	//Test de l'action
	public boolean getAction(int id){
		return(action[id]);
	}
}
