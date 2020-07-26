package be.zeldown.configannotation.tests;

import be.zeldown.configannotation.ConfigInitier;
import be.zeldown.configannotation.Side;

public class Test {

	private static ConfigInitier configInitier = new ConfigInitier();
	
	public static void main(String[] args) {
		configInitier.init(Configs.class);
		configInitier.saveAll();
	}
	
}
