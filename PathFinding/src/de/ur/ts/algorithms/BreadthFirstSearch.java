package de.ur.ts.algorithms;

import java.util.ArrayList;
import java.util.Iterator;

import de.ur.ts.map.Field;
import de.ur.ts.map.Map;

public class BreadthFirstSearch extends Algorithm {
	
	ArrayList<Field> que = new ArrayList<Field>();
	
	public BreadthFirstSearch(Map map){
		super(map);
		setCurrentField(start);
	}

	@Override
	protected void work() {
		if(currentField.equals(goal)){
			finished = true;
			return;
		}
		
		ArrayList<Field> neighbors = currentField.getNeighbors();
		Iterator<Field> it = neighbors.iterator();
		while(it.hasNext()){
			Field f = ((Field) it.next());
			if(!f.isInUse() && f.isEmpty() && !que.contains(f)){
				f.setPredecessor(currentField);
				que.add(f);
			}
		}
		
		if(que.size() == 0 && !currentField.equals(start)){
			failed = true;
			return;
		}
		
		setCurrentField(que.get(0));
		que.remove(0);
	}

	@Override
	protected void markPath() {
		reconstructPath(goal);
	}
}
