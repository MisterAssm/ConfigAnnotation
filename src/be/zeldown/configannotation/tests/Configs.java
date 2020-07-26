package be.zeldown.configannotation.tests;

import be.zeldown.configannotation.Config;
import be.zeldown.configannotation.ConfigFile;

@ConfigFile(path = "be/zeldown/config", file = "config")
public class Configs {

  @Config(key = "cps") public static boolean cps;
  @Config(key = "color") public static int color = -1;
  @Config(key = "version") public static int version = 2;
  @Config(key = "user") public static User user = new User("bob", 43);

}
