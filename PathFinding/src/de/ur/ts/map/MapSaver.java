package de.ur.ts.map;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MapSaver {
	
	private static final String TYPE = ".json";
	

	public static boolean saveMap(File directory, String filename, Map map) {
		String path = directory.toString() + "\\";
		System.out.println("Path: " + path);
		boolean noError = true;
		String input = map.toJSON().toString();
		
		File file = new File(path + filename + TYPE);
		try {
			file.createNewFile();
			FileWriter fstream = new FileWriter(file);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(input);
			// Close the output stream
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			noError = false;
		}

		return noError;
	}
	

}
