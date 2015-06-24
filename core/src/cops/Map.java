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
	//Taille de la fenetre
	public static final int LARGEUR=640;
	public static final int HAUTEUR=480;
	final static int TTILE=3;
	//Attribut
	private String nomLevel;
	//Taille de la map en abscisse et en ordonnï¿½
	private Vector2 taille;
	//Rendu de la map
	private Texture rendu;
	//Liste des Tiles
	private ArrayList<Tile> tiles;
	//Liste d'ascenseur
	private ArrayList<Ascenseur> ascenseurs;
	private int nbAscenseur;
	//Liste des portes et gestion des objectifs
	private ArrayList<Porte> portes;
	private int nbPortes;
	private int nbObjectifs;
	private int nbBonus;
	//Tile pouvant ï¿½tre traverser
	private int numTile;
	//Emplacement de victoire
	private Vector2 posWin;
	private int tailleWin;
	//Gestions des ennemies
	private int nbEnnemies;
	private ArrayList<Ennemie> ennemies;
	
	
	Map(String nomLevel) throws FileNotFoundException{
		taille=new Vector2();
		tiles=new ArrayList<Tile>();
		ascenseurs=new ArrayList<Ascenseur>();
		portes=new ArrayList<Porte>();
		ennemies=new ArrayList<Ennemie>();
		//Scanner de fichier
		Scanner fichier;
		//Fichier tile du niveau
		String fichierTiles;
		//On ouvre le fichier oï¿½ sont contenu les infos sur la map
		fichier=new Scanner(new File("../core/assets/"+nomLevel));
		//Premiï¿½re ligne la taille multipliï¿½ par les tiles
		taille.x=fichier.nextInt();
		taille.y=fichier.nextInt();
		fichier.nextLine();
		//On trouve le nom du fichier tile
		fichierTiles=fichier.nextLine();
		//On entre le fichier pour les tile dans le rendu
		rendu=new Texture("../core/assets/"+fichierTiles);
		//Boucles de crï¿½ation de la map
		for(int i=0;i<taille.y;i++){
			for(int j=0;j<taille.x;j++){
				//l'id du tile est traitï¿½
				int numTile=fichier.nextInt();
				//Crï¿½ation du tile
				tiles.add(new Tile(new Vector2(j*TTILE,HAUTEUR-i*TTILE-TTILE),new TextureRegion(rendu,numTile*TTILE,0,TTILE,TTILE),numTile));
			}
		}
		//Va prendre la valeur du nombre d'ascenseur
		nbAscenseur=fichier.nextInt();
		int abscisseA;
		int ordonneA;
		int nbEtagesA;
		//Boucle pour entrer les ascenseurs en fonction du fichier
		for(int i=0;i<nbAscenseur;i++){
			abscisseA=fichier.nextInt();
			ordonneA=fichier.nextInt();
			nbEtagesA=fichier.nextInt();
			ascenseurs.add(new Ascenseur(new Vector2(abscisseA,ordonneA),nbEtagesA));
		}
		//gestion des porte;
		nbPortes=fichier.nextInt();
		nbObjectifs=fichier.nextInt();
		nbBonus=fichier.nextInt();
		int abscisseP;
		int ordonneP;
		int typeP;
		//Boucle pour entrer les portes
		for(int i=0;i<nbPortes;i++){
			abscisseP=fichier.nextInt();
			ordonneP=fichier.nextInt();
			typeP=fichier.nextInt();
			if(typeP==2){
				portes.add(new PorteBonus(new Vector2(abscisseP,ordonneP)));
			}
			else if(typeP==1){
				portes.add(new PorteObjective(new Vector2(abscisseP,ordonneP)));
			}
			else{
				portes.add(new Porte(new Vector2(abscisseP,ordonneP)));
			}
		}
		//Initialisation de l'emplacement pour la victoire
		posWin=new Vector2();
		posWin.x=fichier.nextInt();
		posWin.y=fichier.nextInt();
		tailleWin=fichier.nextInt();
		nbEnnemies=fichier.nextInt();
		//Boucle crï¿½ant les ennemies sur des positions alï¿½atoires de porte
		for(int i=0;i<nbEnnemies;i++){
			//On fait un alï¿½atoire pour savoir sur quelle porte va apparaï¿½tre l'ennemie
			int random=(int)(Math.random()*(nbPortes-1));
			ennemies.add(new Ennemie((int)portes.get(random).getLocalisation().x,(int)portes.get(random).getLocalisation().y+1));
		}
	}
	public void draw(SpriteBatch batch){
		//Boucle pour lire les tiles
		for(int i=0;i<tiles.size();i++){
			tiles.get(i).afficher(batch);
		}
		//Boucle pour lire les ascenseurs
		for(int i=0;i<ascenseurs.size();i++){
			ascenseurs.get(i).draw(batch);
		}
		//Boucle pour lire les portes
		for(int i=0;i<portes.size();i++){
			portes.get(i).draw(batch);
		}
		//Boucle pour lire les ennemies
		for(int i=0;i<ennemies.size();i++){
			ennemies.get(i).draw(batch);
		}
	}
	public void monter(Personnage perso){
		for(int i=0;i<ascenseurs.size();i++){
			ascenseurs.get(i).monter(perso,i);
		}
	}
	//Utilisation des portes
	public void usePorte(Personnage perso){
		int rep;
		//Boucle pour lire les portes
		for(int i=0;i<portes.size();i++){
			rep=portes.get(i).use(perso);
			if(rep==0){
				nbObjectifs--;
				System.out.println("Porte restante :"+nbObjectifs);
			}
			else if(rep==1){
				nbBonus--;
			}
		}
		
	}
	public int getNbObjectifs() {
		return nbObjectifs;
	}
	//Collision entre la map et le personnage
	public void collision(Personnage perso){
		
		Vector2 localisationPerso=new Vector2(perso.getLocalisation());
		//On voit oï¿½ va le personnage
		localisationPerso.x+=perso.getMouvement().x;
		localisationPerso.y+=perso.getMouvement().y;
		//On rï¿½cupï¿½re la taille du personnage
		Vector2 taillePerso= new Vector2(perso.getTaille());
		//Compteur de tile
		int cptTile=0;
		//Boucle de parcour des tiles
		for(int i=0;i<taille.y;i++){
			for(int j=0;j<taille.x;j++){
				//On attrape la localisation des tiles dans la liste
				Vector2 localisationTile=new Vector2(tiles.get(cptTile).getLocalisation());
				//Test des diffï¿½rente collision
				//En abscisse
				if((localisationPerso.x<localisationTile.x+TTILE && localisationPerso.x>localisationTile.x-12)||(localisationPerso.x+taillePerso.x<localisationTile.x+TTILE && localisationPerso.x+taillePerso.x>localisationTile.x)){
					//En ordonnï¿½
					if((localisationTile.y<localisationPerso.y+taillePerso.y && localisationTile.y>localisationPerso.y)||(localisationTile.y+TTILE<localisationPerso.y+taillePerso.y && localisationTile.y+TTILE>localisationPerso.y)){
						if(tiles.get(cptTile).getNum()==1){
							perso.setMouvement(new Vector2(0,0));
						}
					}
				}
				cptTile++;
			}
		}
		}
	
	public Vector2 getTaille() { // Getteur de la taille de la map en vecteur 2
		return taille;
	}
	//Fin de la map
	public boolean win(Personnage perso){
		//Si le perso a pris tout les objectifs et est positionnï¿½ au bonne endroit alors retourne vrai
		if((nbObjectifs==0)&&(perso.getLocalisation().x>=posWin.x && perso.getLocalisation().x+perso.getTaille().x<=posWin.x+tailleWin)&&(perso.getLocalisation().y==posWin.y)){
			return(true);
		}
		else{
			return(false);
		}
	}
	//Gravitï¿½
	public void gravity(Personnage perso){
		perso.gravity(tiles);
	}
	//Procï¿½dure faisant bouger les ennemies
	public void ennemiesMouvement(){
		//Parcour de la liste d'ennemies
		for(int i=0;i<ennemies.size();i++){
			ennemies.get(i).mouvement(tiles);
		}
	}
	//Gestion des contacts entre les ennemies et le joueur
	public void contactEnnemie(Personnage perso){
		//Parcours de la totalité des ennemies
		for(int i=0 ;i<ennemies.size();i++){
			//Verification du contact droit
			if((perso.getLocalisation().x<=ennemies.get(i).getLocalisation().x+ennemies.get(i).getTaille().x) && (perso.getLocalisation().x+perso.getTaille().x>=ennemies.get(i).getLocalisation().x) && (perso.getLocalisation().y==ennemies.get(i).getLocalisation().y)){
				perso.setVie(perso.getVie()-1);
			}
			//Verification du contact gauche
			else if((perso.getLocalisation().x<=ennemies.get(i).getLocalisation().x+ennemies.get(i).getTaille().x) && (perso.getLocalisation().x>=ennemies.get(i).getLocalisation().x) && (perso.getLocalisation().y==ennemies.get(i).getLocalisation().y)){
				perso.setVie(perso.getVie()-1);
			}
		}
	}
}
