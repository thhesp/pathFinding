package de.ur.ts.algorithms.extras;

import java.util.ArrayList;
import java.util.Iterator;

import de.ur.ts.algorithms.Algorithm;
import de.ur.ts.map.Field;
import de.ur.ts.map.Map;

public class DiagDepthFirstSearch extends Algorithm {
	
	public DiagDepthFirstSearch(Map map) {
		super(map);
		setCurrentField(start);
	}

	@Override
	protected void work() {
		if(currentField.equals(goal)){
			finished = true;
			return;
		}
		boolean fieldFound = false;
		ArrayList<Field> neighbors = currentField.getDiagNeighbors();
		if(neighbors.isEmpty()){
			stepBack();
			return;
		}
		Iterator<Field> it = neighbors.iterator();
		while(it.hasNext()){
			Field f = ((Field) it.next());
			if(!f.isInUse() && f.isEmpty()){
				f.setPredecessor(currentField);
				setCurrentField(f);
				fieldFound = true;
				return;
			}
		}
		
		if(!fieldFound){
			stepBack();
		}
	}

	@Override
	protected void markPath() {
		// TODO Auto-generated method stub
		
	}



}
