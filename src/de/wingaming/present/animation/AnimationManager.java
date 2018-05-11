package de.wingaming.present.animation;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.wingaming.present.Main;
import de.wingaming.present.utils.FileUtils;
import de.wingaming.present.utils.ImageUtils;
import javafx.scene.image.Image;

public class AnimationManager {

	public static final long DEFAULT_DELAY = 100;
	
	public static Animation getAnimation(String animationName) {
		Map<String, List<AnimationImage>> animationMap = new HashMap<>();
		
		InputStream animatiopaths = Main.class.getResourceAsStream("res/animations.path");
		
		if (animatiopaths != null) {
			try {
				String line = null;
				BufferedReader reader = new BufferedReader(new InputStreamReader(animatiopaths));
				
				while ((line = reader.readLine()) != null) {
					if (line.startsWith(animationName+":")) {
						String[] animations = line.split(":")[1].split(";");
						
						for (String animation : animations) {
							String[] data = animation.split(",");
							
							String name = data[0];
							int amount = Integer.parseInt(data[1]);
							
							List<AnimationImage> animgs = new ArrayList<>();
							for (int i = 0; i < amount; i++) {
								System.out.println(name + "->" + i);
								
								Image img = new Image(Main.class.getResourceAsStream("res/"+animationName+"/"+name+"/"+i+".png"));
								long delay = DEFAULT_DELAY;
								
								animgs.add(new AnimationImage(img, delay));
							}
							
							animationMap.put(animation, animgs);
						}
						
						return new Animation(animationMap);
					}
				}
				
				reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		File folder = new File("res/"+animationName);
		
		if (!folder.isDirectory()) return null;
		
		for (File file : folder.listFiles()) {
			if (!file.isDirectory()) continue;
			
			String inAnimationName = file.getName();
			List<AnimationImage> animation = loadAnimationFolder(animationName, file);
			
			animationMap.put(inAnimationName, animation);
		}
		
		return new Animation(animationMap);
	}
	
	private static List<AnimationImage> loadAnimationFolder(String animationame, File folder) {
		List<AnimationImage> result = new ArrayList<>();
		
		File info = new File(folder.getAbsolutePath() + "/info.animation");
		Map<String, Long> delayInfo;
		
		if (info.exists()) {
			delayInfo = FileUtils.getAnimationInfo(info);
		} else {
			delayInfo = new HashMap<>();
		}
		
		for (int i = 0; i < folder.listFiles().length; i++) {
			File image = new File(folder.getAbsolutePath()+"/"+i+".png");
			
			if (!image.exists()) {
				break;
			} else {
				long delay = delayInfo.containsKey(i+"") ? delayInfo.get(i+"") : DEFAULT_DELAY;
				
				result.add(new AnimationImage(ImageUtils.resample(new Image("file:res/"+animationame+"/"+folder.getName()+"/"+image.getName()), 3), delay));
			}
		}
		
		return result;
	}
}