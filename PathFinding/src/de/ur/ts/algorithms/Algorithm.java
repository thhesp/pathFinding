package de.ur.ts.algorithms;

import java.util.ArrayList;

import de.ur.ts.interfaces.AlgorithmListener;
import de.ur.ts.map.Field;
import de.ur.ts.map.Map;

public abstract class Algorithm extends Thread{
	
	private static final int DEFAULT_SLEEP_MS = 100;
	
	private AlgorithmListener listener;
	private int sleepMs = DEFAULT_SLEEP_MS;
	private int steps = 0;
	
	protected boolean paused = false;
	protected boolean finished = false;
	protected boolean failed = false;

	
	protected Map map;
	protected Field start, goal;
	protected ArrayList<Field> history = new ArrayList<Field>();
	protected Field currentField = null;
	
	public Algorithm(Map map){
		this.map = map;
		start = map.getStart();
		goal = map.getGoal();
		setCurrentField(start);
	}
	
	public void run(){
		while(!finished && !failed){
			while(!paused){
				try {
					sleep(sleepMs);
					work();
					steps++;
					notifyRefresh();
					if(finished || failed) break;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		markPath();
		notifyFinished();
		notifyRefresh();
		if(finished){
			System.out.println("Finished!");
			System.out.println("Needed Steps: " + steps);
		}else if(failed){
			System.out.println("Failed");
			System.out.println("No path found");
		}
	}

	public void faster(){
		if(sleepMs > 0){
			sleepMs  -= 10;
		} 	
	}
	
	public void slower(){
		sleepMs += 10;
	}
	
	public void resetSleep(){
		sleepMs = DEFAULT_SLEEP_MS;
	}
	
	public boolean isRunning(){
		return !finished;
	}
	
	public void setListener(AlgorithmListener listener){
		this.listener = listener;
	}
	
	public boolean isPaused(){
		return paused;
	}
	
	public void pauseAlgorithm(){
		if(paused == false) paused = true;
	}
	
	public void resumeAlgorithm(){
		if(paused = true) paused = false;
	}
	
	public void stopAlgorithm(){
		failed = true;
	}
	
	protected void useField(Field f){
		if(!f.isInUse()){
			f.toggleInUse();
		}
	}
	
	protected void stepBack(){
		if(history.isEmpty()){
			failed = true;
		}else{
			history.remove(history.size()-1);
			if(history.isEmpty()){
				failed = true;
			}else{
				setCurrentField(history.get(history.size()-1));
			}
		}
	}
	
	protected double calculateDistance(Field f1, Field f2){
		int[] pos1 = map.getFieldPosition(f1);
		int[] pos2 =  map.getFieldPosition(f2);
		int xVektor = pos1[0] - pos2[0];
		int yVektor = pos1[1] - pos2[1];
		if(xVektor == 0 && yVektor == 0) return 0;
		return Math.sqrt((xVektor*xVektor) + (yVektor*yVektor));
	}
	

	protected void setCurrentField(Field f){
		if(currentField != null){
			currentField.deactivate();
		}
		currentField = f;
		useField(currentField);
		currentField.activate();
		notifyRefresh();
		if(!history.contains(currentField)){
			history.add(currentField);
		}
	}
	
	protected void notifyRefresh(){
		if(listener != null){
			listener.refresh();
		}
	}
	
	protected void reconstructPath(Field f) {
		f.setPath(true);
		if(f.hasPredecessor()){
			reconstructPath(f.getPredecessor());
		}
	}
	
	private void notifyFinished() {
		if(listener != null){
			listener.finished();
		}
	}

	protected abstract void work();
	protected abstract void markPath();

}
