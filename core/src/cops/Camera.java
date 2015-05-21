package cops;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class Camera {
	private OrthographicCamera cam;
	private Personnage sonPerso; // La caméra se centre sur un personnage
	
	Camera(Personnage perso){ // On centre la caméra sur un personnage saisi en argument.
		sonPerso = perso;
		// On défini le champ de la caméra
		cam = new OrthographicCamera(200,200);
		cam.position.set(sonPerso.getCenter(), 0);
		cam.update();
	}
	
	public void update(){
		cam.position.set(sonPerso.getCenter(),0); // On centre la caméra sur le perso
		cam.update();
	}
	
	public OrthographicCamera getCamera(){
		return(cam);
	}
}
