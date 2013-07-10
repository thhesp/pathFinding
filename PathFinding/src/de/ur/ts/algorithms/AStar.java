package de.ur.ts.algorithms;

import java.util.ArrayList;

import de.ur.ts.map.Field;
import de.ur.ts.map.Map;

public class AStar extends Algorithm{
	
	ArrayList<Field> closedSet = new ArrayList<Field>();
	ArrayList<Field> openSet = new ArrayList<Field>();

	public AStar(Map map) {
		super(map);
		setCurrentField(start);
		openSet.add(start);
	}

	@Override
	protected void work() {
		if(openSet.isEmpty()){
			failed = true;
			return;
		}
		setCurrentField(searchOpenSet());
		if(currentField.equals(goal)){
			finished = true;
			return;
		}
		openSet.remove(currentField);
		closedSet.add(currentField);
		refreshOpenSet();
	}

	private void refreshOpenSet() {
		ArrayList<Field> neighbors = currentField.getNeighbors();
		for(Field f : neighbors){
			if(!f.isEmpty()) continue;
			double tentativeGScore = calculateGScore(currentField) + calculateDistance(currentField, f);
			double neighborGScore = calculateGScore(f);
			if(closedSet.contains(f) && tentativeGScore >= neighborGScore) continue;
			
			if(!closedSet.contains(f) || tentativeGScore < neighborGScore){
				f.setPredecessor(currentField);
				setFScore(f);
				if(!openSet.contains(f)){
					openSet.add(f);
				}
			}
		}
		
	}

	private Field searchOpenSet(){
		Field best = null;
		double bestValue = 9999;
		for(Field f : openSet){
			double value = calculateFScore(f);
			if(value < bestValue){
				bestValue = value;
				best = f;
			}
		}
		
		return best;
	}
	
	private double calculateFScore(Field f){
		return calculateGScore(f) + calculateDistance(f,goal);
	}
	
	private double calculateGScore(Field f){
		return calculateDistance(f, start);
	}
	
	private void setFScore(Field f){
		f.setValue(calculateFScore(f));
	}

	@Override
	protected void markPath() {
		reconstructPath(goal);
	}

}
