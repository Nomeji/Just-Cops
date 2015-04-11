package cops;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Map {
	final static int TTILE=3;
	//Attribut
	private String nomLevel;
	//Taille de la map en abscisse et en ordonné
	private Vector2 taille;
	//Rendu de la map
	private Texture rendu;
	//Liste des Tiles
	private ArrayList<Tile> tiles;
	//Tile pouvant être traverser
	private int numTile;
	
	Map(String nomLevel) throws FileNotFoundException{
		taille=new Vector2();
		tiles=new ArrayList<Tile>();
		//Scanner de fichier
		Scanner fichier;
		//Fichier tile du niveau
		String fichierTiles;
		//On ouvre le fichier où sont contenu les infos sur la map
		fichier=new Scanner(new File("../core/assets/"+nomLevel));
		//Première ligne la taille multiplié par les tiles
		taille.x=fichier.nextInt();
		taille.y=fichier.nextInt();
		fichier.nextLine();
		//On trouve le nom du fichier tile
		fichierTiles=fichier.nextLine();
		//On entre le fichier pour les tile dans le rendu
		rendu=new Texture("../core/assets/"+fichierTiles);
		//Boucles de création de la map
		for(int i=0;i<taille.y;i++){
			for(int j=0;j<taille.x;j++){
				//l'id du tile est traité
				int numTile=fichier.nextInt();
				//Création du tile
				tiles.add(new Tile(new Vector2(j*TTILE,i*TTILE),new TextureRegion(rendu,numTile*TTILE,0,TTILE,TTILE),numTile));
			}
		}
	}
	public void draw(SpriteBatch batch){
		//Boucle pour lire les tiles
		for(int i=0;i<tiles.size();i++){
			tiles.get(i).afficher(batch);
		}
	}
	//Collision entre la map et le personnage
	public void collision(Personnage perso){
		
		Vector2 localisationPerso=new Vector2(perso.getLocalisation());
		//On voit où va le personnage
		localisationPerso.x+=perso.getMouvement().x;
		localisationPerso.y+=perso.getMouvement().y;
		//On récupère la taille du personnage
		Vector2 taillePerso= new Vector2(perso.getTaille());
		//Compteur de tile
		int cptTile=0;
		//Boucle de parcour des tiles
		for(int i=0;i<taille.y;i++){
			for(int j=0;j<taille.x;j++){
				//On attrape la localisation des tiles dans la liste
				Vector2 localisationTile=new Vector2(tiles.get(cptTile).getLocalisation());
				//Test des différente collision
				//En abscisse
				if((localisationTile.x<localisationPerso.x+taillePerso.x && localisationTile.x>localisationPerso.x)||(localisationTile.x+TTILE<localisationPerso.x+taillePerso.x && localisationTile.x+TTILE>localisationPerso.x)){
					//En ordonné
					if((localisationTile.y<localisationPerso.y+taillePerso.y && localisationTile.y>localisationPerso.y)||(localisationTile.y+TTILE<localisationPerso.y+taillePerso.y && localisationTile.y+TTILE>localisationPerso.y)){
						if(tiles.get(cptTile).getNum()==0){
							perso.setMouvement(new Vector2(0,0));
						}
					}
				}
				cptTile++;
			}
		}
	}
	
}
