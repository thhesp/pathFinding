package de.ur.ts.algorithms;

import java.util.ArrayList;

import de.ur.ts.map.Field;
import de.ur.ts.map.Map;

public class Djkstra extends Algorithm {
	
	ArrayList<Field> openSet = new ArrayList<Field>();

	public Djkstra(Map map) {
		super(map);
		setCurrentField(start);
		currentField.setValue(0);
		openSet.add(currentField);
	}

	@Override
	protected void work() {
		if(openSet.isEmpty()){
			failed = true;
			return;
		}
		setCurrentField(searchOpenSet());
		openSet.remove(currentField);
		if(currentField.equals(goal)){
			finished = true;
			return;
		}
		refreshOpenSet();
	}
	
	private void refreshOpenSet() {
		ArrayList<Field> neighbors = currentField.getNeighbors();
		for(Field f : neighbors){
			if(!f.isEmpty() || openSet.contains(f)) continue;
			double dist = calculateDistance(start, currentField) + calculateDistance(currentField, f);
			if(dist < f.getValue()){
				f.setValue(dist);
				if(!f.hasPredecessor() || !currentField.getPredecessor().equals(f)){
					f.setPredecessor(currentField);
				}
				openSet.add(f);
			}
		}
	}

	private Field searchOpenSet(){
		Field best = null;
		double bestValue = 9999;
		for(Field f : openSet){
			double value = f.getValue();
			if(value < bestValue){
				bestValue = value;
				best = f;
			}
		}
		
		return best;
	}

	@Override
	protected void markPath() {
		reconstructPath(goal);
	}

}
