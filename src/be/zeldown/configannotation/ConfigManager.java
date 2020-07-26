package be.zeldown.configannotation;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

public class ConfigManager {

  private static File file;
	
  public static void init(String path, String f) {
	  file = new File(path, f + ".json");
	  if(!file.getParentFile().exists()) {
		  file.mkdir();
	  }
	  if(!file.exists()) {
		  try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	  }
  }
  
  public static void write(String key, Object obj) {
	  Gson gson = new GsonBuilder().create();
	  try {
		  JsonObject jsonObject = gson.fromJson(new JsonReader(new FileReader(file)), JsonObject.class);
		  if(jsonObject == null) {
			  jsonObject = new JsonObject();
		  }
		  jsonObject.add(key, gson.toJsonTree(obj));
		  String s = gson.toJson(jsonObject);
		  
		  FileWriter writer = new FileWriter(file);
		  writer.write(s);
		  writer.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
  }
    
  public static Object read(String key, Object defaultValue, Class<?> type) {
	  Gson gson = new GsonBuilder().create();
	  try {
		  
		  JsonObject jsonObject = gson.fromJson(new JsonReader(new FileReader(file)), JsonObject.class);
		  if(jsonObject == null) {
			  jsonObject = new JsonObject();
		  }
		  if(jsonObject.has(key)) {
			  JsonElement e = jsonObject.get(key);
			  if(type == int.class) {
				  return e.getAsInt();
			  }
			  if(type == float.class) {
				  return e.getAsFloat();
			  }
			  return gson.fromJson(e, Object.class);
		  }
	} catch (Exception e) {
		e.printStackTrace();
	}
	  return defaultValue;
  }
}
