package de.wingaming.present.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class FileUtils {
	
	public static Map<String, Long> getAnimationInfo(File file) {
		Map<String, Long> result = new HashMap<>();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			
			while((line = reader.readLine()) != null) {
				if (line.contains(":")) {
					String[] data = line.split(":");
					
					String key = data[0];
					Long value;
					
					try {
						value = Long.parseLong(data[1].trim());
					} catch (NumberFormatException ex) {
						System.out.println("Invalid animation-info-line: \"" + line + "\"");
						continue;
					}
					
					result.put(key, value);
				}
			}
			
			reader.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return result;
	}
	
	@SuppressWarnings("resource")
	public static String[] getResourceListing(Class<?> clazz, String path) throws URISyntaxException, IOException {
		URL dirURL = clazz.getResource(path);
	      
	      if (dirURL != null && dirURL.getProtocol().equals("file")) {
	        /* A file path: easy enough */
	        return new File(dirURL.toURI()).list();
	      } 
	      
	      if (dirURL == null) {
	        /* 
	         * In case of a jar file, we can't actually find a directory.
	         * Have to assume the same jar as clazz.
	         */
	        String me = clazz.getName().replace(".", "/")+".class";
	        dirURL = clazz.getClassLoader().getResource(me);
	      }
	      
	      if (dirURL.getProtocol().equals("jar")) {
	        /* A JAR path */
	        String jarPath = dirURL.getPath().substring(5, dirURL.getPath().indexOf("!")); //strip out only the JAR file
	        JarFile jar = new JarFile(URLDecoder.decode(jarPath, "UTF-8"));
	        Enumeration<JarEntry> entries = jar.entries(); //gives ALL entries in jar
	        Set<String> result = new HashSet<String>(); //avoid duplicates in case it is a subdirectory
	        
	        while(entries.hasMoreElements()) {
	          String name = entries.nextElement().getName();
	          
	          if (name.startsWith(path)) { //filter according to the path
	            String entry = name.substring(path.length());

	            int checkSubdir = entry.indexOf("/");
	            if (checkSubdir >= 0) {
	              // if it is a subdirectory, we just return the directory name
	              entry = entry.substring(0, checkSubdir);
	            }
	            
	            result.add(entry);
	          }
	        }
	        
	        for (String entry : result) {
				System.out.println("Entry: " + entry);
			}
	        
	        return result.toArray(new String[result.size()]);
	      }
	      
	      throw new UnsupportedOperationException("Cannot list files for URL "+dirURL);
	  }
}