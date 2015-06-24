package cops;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class AnimPerso {
	private Vector2 position;
	private Vector2 taille;
	private Texture spritPerso;
	private TextureRegion regionPerso;
	
	AnimPerso(Vector2 pos){
		position = pos;
		spritPerso = new Texture(Gdx.files.internal("policierAll.png"));
		regionPerso = new TextureRegion(spritPerso,10,6,12,26);
		taille = new Vector2(regionPerso.getRegionHeight(),regionPerso.getRegionWidth());
	}
}
