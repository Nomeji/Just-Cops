package cops;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class HUD {
	private OrthographicCamera cameraH;
	
	// Image et position d'une porte
	private Texture porteIco;
	private Vector2 positionPorte;
	
	// Affichage du nombre de porte
	private BitmapFont nbPorteB;
	private CharSequence strPorte;
	
	// Image et position d'un coeur
	private Texture coeurIco;
	private Vector2 positionCoeur;
	
	// Affichage du nombre de vie
	private BitmapFont nbCoeur;
	private CharSequence strCoeur;
	
	HUD(Map m,Personnage p){
		porteIco = new Texture("porte.png"); // On affiche une icone de porte
		positionPorte = new Vector2(10,400); 
		
		nbPorteB = new BitmapFont(); // On créer un bitmap qui affiche le nombre de porte restante
		strPorte = ""+m.getNbObjectifs();
		
		coeurIco = new Texture("coeurPlein.png");
		positionCoeur = new Vector2(43,410);
		
		nbCoeur = new BitmapFont(); // On créer un bitmap qui affiche le nombre de vie restante
		strCoeur = ""+p.getVie();
		
		cameraH = new OrthographicCamera(500,500); // On créer une caméra fixe
		cameraH.position.set(positionPorte,0);
		cameraH.update();
	}
	
	public CharSequence getStrPorte() {
		return strPorte;
	}

	public BitmapFont getNbPorteB() {
		return nbPorteB;
	}

	public BitmapFont getNbCoeur() {
		return nbCoeur;
	}

	public CharSequence getStrCoeur() {
		return strCoeur;
	}

	public void draw(SpriteBatch batch){
		batch.draw(porteIco, positionPorte.x,positionPorte.y);
		batch.draw(coeurIco, positionCoeur.x,positionCoeur.y);
	}
	
	public void update(Map m,Personnage p){
		strPorte = ""+m.getNbObjectifs();
		strCoeur = ""+p.getVie();
	}
}
