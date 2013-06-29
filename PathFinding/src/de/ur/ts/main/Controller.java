package de.ur.ts.main;

import java.util.ArrayList;
import java.util.Iterator;

import de.ur.ts.interfaces.ControlViewListener;
import de.ur.ts.interfaces.ControllerListener;
import de.ur.ts.interfaces.DialogListener;
import de.ur.ts.interfaces.MapViewListener;
import de.ur.ts.map.Field;
import de.ur.ts.map.Map;
import de.ur.ts.view.GUI;

public class Controller implements MapViewListener, ControlViewListener, DialogListener{
	
	private GUI gui;
	private Map map;
	
	private ArrayList<ControllerListener> listeners = new ArrayList<ControllerListener>();
	
	public Controller(){
		gui = new GUI(this);
	}
	
	public void addListener(ControllerListener listener){
		listeners.add(listener);
	}


	private void cycle(int x, int y) {
		Field f = map.getField(x,y);
		if(!f.isEmpty()){
			f.toggleEmpty();
			map.setStart(x, y);
		}else if(map.isStart(x, y)){
			map.resetStart();
			map.setGoal(x, y);
		}else if(map.isGoal(x, y)){
			map.resetGoal();
		}else{
			f.toggleEmpty();
		}
	}

	private void reset(int x, int y) {
		if(map.isGoal(x, y)){
			map.resetGoal();
		}

		if(map.isStart(x, y)){
			map.resetStart();
		}
		
		map.getField(x, y).reset();
	}


	@Override
	public void onLeftButton(int x, int y) {
		cycle(x,y);
		Iterator<ControllerListener> it = listeners.iterator();
		while(it.hasNext()){
			((ControllerListener) it.next()).onMapChange();
		}
	}


	@Override
	public void onRightButton(int x, int y) {
		reset(x,y);
		Iterator<ControllerListener> it = listeners.iterator();
		while(it.hasNext()){
			((ControllerListener) it.next()).onMapChange();
		}
	}


	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onAlgorithmChange() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void changedMapSize(int cols, int rows) {
		map = new Map(cols, rows);
		Iterator<ControllerListener> it = listeners.iterator();
		while(it.hasNext()){
			((ControllerListener) it.next()).onMapCreated(map);
		}
		
	}

	@Override
	public void onMiddleButton(int x, int y) {
		System.out.println("Field x: " + x + " Field y: " + y + "\n" + map.getField(x, y).toString());
	}
	
}
