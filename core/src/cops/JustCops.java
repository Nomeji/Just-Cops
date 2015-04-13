package cops;

import java.io.FileNotFoundException;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class JustCops extends ApplicationAdapter {
	//Taille de la fenetre
	public static final int LARGEUR=640;
	public static final int HAUTEUR=480;
	//Joueur
	public static final int JOUEURMAX=2;
	
	//Attribut de la partie
	private SpriteBatch batch;
	private Personnage perso;
	private Ascenseur ascenseur;
	private Map map;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		//Creation des personnages
		if(Personnage.nbJoueurs<=JOUEURMAX){
			perso=new Personnage(new Vector2(3,3));
		}
		try {
			map=new Map("level1.txt");
		} catch (FileNotFoundException e) {
			System.out.println("Erreur, la map n'existe pas");
		}
		ascenseur=new Ascenseur(new Vector2(3,3),1);
		
	}
	@Override
	public void render () {
		//La fenetre
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//Mise à jour des evenements
		perso.updateEvenement();
		//Deplacement et action
		perso.update();
		//Gestion de la collision avec la map
		map.collision(perso);
		//Deplacement du personnage
		perso.deplacement();
		//Les ascenseur bouge
		ascenseur.monter();
		//Affichage
		batch.begin();
		map.draw(batch);
		ascenseur.draw(batch);
		perso.draw(batch);
		batch.end();
	}
}
