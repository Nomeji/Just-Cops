package cops.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import cops.JustCops;
import cops.Personnage;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.width=JustCops.LARGEUR;
		config.height=JustCops.HAUTEUR;
		
		new LwjglApplication(new JustCops(),config);
	}
}
