package cops;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

public class Camera {
	private OrthographicCamera cam;
	private Personnage sonPerso; // La caméra se centre sur un personnage
	private Map saMap; // La marge de déplacement de la caméra se base sur la taille de la map 
	private static Vector2 borderMin;
	private static Vector2 borderMax;
	
	Camera(Personnage perso, Map m){ // On centre la caméra sur un personnage saisi en argument.
		sonPerso = perso;
		saMap = m;
		borderMin = new Vector2(80f,420f);
		// On défini le champ de la caméra
		cam = new OrthographicCamera(200,200);
		cam.position.set(borderMin, 0);
		cam.update();
	}
	
	public void update(){
		System.out.println(saMap.getTaille().y);
		if (sonPerso.getCenter().x > borderMin.x){ // Actualisation de la caméra en x
			cam.position.set(sonPerso.getCenter().x,cam.position.y,0); // On centre la caméra sur le perso en x, uniquement en x si il est suffisement loin du bord de la map
			cam.update();
		
		}
		if (sonPerso.getCenter().y > borderMin.y){ // Actualisation de la caméra en y
			if(!sonPerso.getInMouv()){ // On vérifie que le perso n'est pas en saut.
				cam.position.set(cam.position.x,sonPerso.getCenter().y,0); // On centre la caméra sur le perso en y, uniquement en y si il n'est pas en saut et qu'il est suffisement loin du bas de la map
				cam.update();
			}
		}
	}
	
	public OrthographicCamera getCamera(){
		return(cam);
	}
}
