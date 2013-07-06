package de.ur.ts.algorithms;

import de.ur.ts.map.Map;

public class AlgorithmFactory {
	
	private static String[] algorithms = {"DepthFirst",
			"BreadthFirst",
			"A-Star",
			"Djkstra"};
	
	private static String[] extra = {"DiagonalDepthFirst",
		"CompleteDepthFirst",
		"DiagonalBreadthFirst",
		"CompleteBreathFirst"};
	
	public static Algorithm getInstance(String name, Map map){
		System.out.println(name);
		if(name == algorithms[0]){
			return new DepthFirstSearch(map);
		}else if(name == algorithms[1]){
			return new BreadthFirstSearch(map);
		}else if(name == algorithms[2]){
			return new AStar(map);
		}else if(name == algorithms[3]){
			return new Djkstra(map);
		}
		
		return null;
	}
	
	public static String[] getAlgorithmList(){
		return algorithms;
	}

}
