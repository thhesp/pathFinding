package de.ur.ts.map;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.json.JSONObject;

public class MapLoader {

	private static final String ENCODING = "UTF-8";

	public static Map loadMap(File file) throws FileNotFoundException {
		StringBuilder text = new StringBuilder();
		String NL = System.getProperty("line.separator");
		Scanner scanner = new Scanner(file, ENCODING);
		try {
			boolean found = false;
			String temp = "";
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				text.append(line);
			}
		} finally {
			scanner.close();
		}

		JSONObject jsonFile = new JSONObject(text.toString());
		
		int rows = jsonFile.getInt("Rows");
		int cols = jsonFile.getInt("Columns");
		Map map = new Map(cols, rows);
		
		int startCol = jsonFile.getInt("StartCol");
		int startRow = jsonFile.getInt("StartRow");
		
		map.setStart(startCol, startRow);
		
		int goalCol = jsonFile.getInt("GoalCol");
		int goalRow = jsonFile.getInt("GoalRow");
		
		map.setGoal(goalCol, goalRow);
		
		for(int rowIndex = 0; rowIndex < rows; rowIndex++){
			for(int colIndex = 0; colIndex < cols; colIndex++){
				if(jsonFile.has(rowIndex+":"+colIndex)){
					map.getField(colIndex,rowIndex).toggleEmpty();
				}
			}
		}
		return map;
	}
}


