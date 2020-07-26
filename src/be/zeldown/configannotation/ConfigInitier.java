package be.zeldown.configannotation;



import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.avaje.ebeaninternal.server.util.ClassPathSearch;
import com.avaje.ebeaninternal.server.util.ClassPathSearchFilter;
import com.avaje.ebeaninternal.server.util.ClassPathSearchMatcher;
import com.google.gson.Gson;

public class ConfigInitier {

  Class<?>[] classes;
	
  public void init(Class<?>... classes) {
	  init(Side.BOTH, classes);
  }
	
  public void init(Side side, Class<?>... classes) {
	this.classes = classes;
    loadAll(side);
  }
  
  public void loadAll() {
	  loadAll(Side.BOTH);
  }

  public void loadAll(Side side) {
    for (Class<?> c : scanElements()) {
    	ConfigFile configFile = (ConfigFile) c.getAnnotation(ConfigFile.class);
      for (Field field : c.getDeclaredFields()) {
        if (field.isAnnotationPresent(Config.class)) {
          Config annotation = field.getAnnotation(Config.class);
          if (annotation.side() == side || annotation.side() == Side.BOTH) {
        	  ConfigManager.init(configFile.path(), configFile.file());
        	  try {
				field.set(field.getClass(), ConfigManager.read(annotation.key(), field.get(field.getClass()), field.getType()));
			} catch (Exception e) {
				e.printStackTrace();
			}
            }
          }
        }
      }
    }

  public void saveAll() {
	  saveAll(Side.BOTH);
  }
  
  public void saveAll(Side side) {
    for (Class<?> c : scanElements()) {
    	ConfigFile configFile = (ConfigFile) c.getAnnotation(ConfigFile.class);
      for (Field field : c.getDeclaredFields()) {
        if (field.isAnnotationPresent(Config.class)) {
          Config annotation = field.getAnnotation(Config.class);
          if (annotation.side() == side || annotation.side() == Side.BOTH) {
        	    ConfigManager.init(configFile.path(), configFile.file());
        	    try {
        	    	ConfigManager.write(annotation.key(), field.get(field.getClass()));
				} catch (Exception e) {
					e.printStackTrace();
				}
          }
        }
      }
    }
  }

  public void save(Object obj, Side side) {
    for (Field field : obj.getClass().getDeclaredFields()) {
      if (field.isAnnotationPresent(Config.class)) {
        Config annotation = field.getAnnotation(Config.class);
        if (annotation.side() == side || annotation.side() == Side.BOTH) {
          
        }
      }
    }
  }

  public List<Class<?>> scanElements() {
	List<Class<?>> classes = new ArrayList<>();
    for (Class<?> c : this.classes) {
    	  if(c.isAnnotationPresent(ConfigFile.class)) {
    	        classes.add(c);
    	  }
      }
    return classes;
  }

}
