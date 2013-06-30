package de.ur.ts.main;

import java.util.ArrayList;
import java.util.Iterator;

import de.ur.ts.algorithms.Algorithm;
import de.ur.ts.algorithms.Tiefensuche;
import de.ur.ts.interfaces.AlgorithmListener;
import de.ur.ts.interfaces.ControlViewListener;
import de.ur.ts.interfaces.ControllerListener;
import de.ur.ts.interfaces.DialogListener;
import de.ur.ts.interfaces.MapViewListener;
import de.ur.ts.map.Field;
import de.ur.ts.map.Map;
import de.ur.ts.view.GUI;

public class Controller implements MapViewListener, ControlViewListener,
		DialogListener, AlgorithmListener {

	private GUI gui;
	private Map map;
	private Algorithm algorithm = null;

	private ArrayList<ControllerListener> listeners = new ArrayList<ControllerListener>();

	private String[] algorithms = { "Breitensuche", "Tiefensuche" };

	public Controller() {
		gui = new GUI(this, algorithms);
	}

	public void addListener(ControllerListener listener) {
		listeners.add(listener);
	}

	private void cycle(int x, int y) {
		Field f = map.getField(x, y);
		if (!f.isEmpty()) {
			f.toggleEmpty();
			map.setGoal(x, y);
		} else if (map.isGoal(x, y)) {
			map.resetGoal();
			map.setStart(x, y);
		} else if (map.isStart(x, y)) {
			map.resetStart();
		} else {
			f.toggleEmpty();
		}
	}

	private void reset(int x, int y) {
		if (map.isGoal(x, y)) {
			map.resetGoal();
		}

		if (map.isStart(x, y)) {
			map.resetStart();
		}

		map.getField(x, y).reset();
	}

	@Override
	public void onLeftButton(int x, int y) {
		cycle(x, y);
		notifyMapChange();
	}

	@Override
	public void onRightButton(int x, int y) {
		reset(x, y);
		notifyMapChange();
	}

	private void notifyMapChange() {
		Iterator<ControllerListener> it = listeners.iterator();
		while (it.hasNext()) {
			((ControllerListener) it.next()).onMapChange();
		}
	}

	@Override
	public void onStart(String algorithmName) {
		System.out.println("Start");
		if (algorithm != null && algorithm.isPaused()) {
			algorithm.resumeAlgorithm();
		} else {
			if (map.hasStart() && map.hasGoal()) {
				if(algorithm != null && algorithm.isRunning()){
					System.out.println("There is still another running Algorithm!");
				}else{
					startAlgorithm(algorithmName);
				}
			}else{
				System.out.println("No Start/ Goal set!");
			}
		}
	}

	private void startAlgorithm(String algorithmName) {
		if (algorithmName == "Breitensuche") {

		} else if (algorithmName == "Tiefensuche") {
			algorithm = new Tiefensuche(map);

		}
		
		
		if(algorithm != null){
			algorithm.setListener(this);
			algorithm.start();
			System.out.println("Start Algorithm!");
		}
	}

	@Override
	public void onPause() {
		System.out.println("Pause");
		if (algorithm != null) {
			algorithm.pauseAlgorithm();
		}
	}

	@Override
	public void onStop() {
		System.out.println("Stop");
		if (algorithm != null) {
			algorithm.stopAlgorithm();
		}
	}

	@Override
	public void onReset() {
		map.reset();
		notifyMapChange();
	}

	@Override
	public void changedMapSize(int cols, int rows) {
		map = new Map(cols, rows);
		Iterator<ControllerListener> it = listeners.iterator();
		while (it.hasNext()) {
			((ControllerListener) it.next()).onMapCreated(map);
		}

	}

	@Override
	public void onMiddleButton(int x, int y) {
		System.out.println("Field x: " + x + " Field y: " + y + "\n"
				+ map.getField(x, y).asString());
	}

	@Override
	public void refresh() {
		notifyMapChange();
	}
	
	public void faster(){
		if(algorithm != null){
			algorithm.faster();
		}
	}
	
	public void slower(){
		if(algorithm != null){
			algorithm.slower();
		}
	}
	
	public void resetSleep(){
		if(algorithm != null){
			algorithm.resetSleep();
		}
	}

}
