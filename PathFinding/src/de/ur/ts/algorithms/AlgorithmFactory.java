package de.ur.ts.algorithms;

import de.ur.ts.map.Map;

public class AlgorithmFactory {
	
	private static String[] algorithms = {"DepthFirst",
			"DiagonalDepthFirst",
			"CompleteDepthFirst",
			"BreadthFirst",
			"DiagonalBreadthFirst",
			"CompleteBreathFirst",
			"A-Star"};
	
	public static Algorithm getInstance(String name, Map map){
		if(name == algorithms[0]){
			return new DepthFirstSearch(map);
		}else if(name == algorithms[1]){
			return new DiagDepthFirstSearch(map);
		}else if(name == algorithms[2]){
			return new CompleteDepthFirstSearch(map);
		}else if(name == algorithms[3]){
			return new BreadthFirstSearch(map);
		}else if(name == algorithms[4]){
			return new DiagBreadthFirstSearch(map);
		}else if(name == algorithms[5]){
			return new CompleteBreadthFirstSearch(map);
		}else if(name == algorithms[6]){
			return new AStar(map);
		}
		
		return null;
	}
	
	public static String[] getAlgorithmList(){
		return algorithms;
	}

}
