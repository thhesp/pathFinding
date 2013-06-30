package de.ur.ts.algorithms;

import java.util.ArrayList;

import de.ur.ts.interfaces.AlgorithmListener;
import de.ur.ts.map.Field;
import de.ur.ts.map.Map;

public abstract class Algorithm extends Thread{
	
	private static final int DEFAULT_SLEEP_MS = 100;
	
	private AlgorithmListener listener;
	private int sleepMs = DEFAULT_SLEEP_MS;
	
	protected boolean paused = false;
	protected boolean finished = false;

	
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
		while(!finished){
			while(!paused){
				try {
					sleep(sleepMs);
					work();
					notifyRefresh();
					if(finished) break;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		System.out.println("Finished!");
	}
	
	public void faster(){
		sleepMs  -= 10;
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
		finished = true;
	}
	
	protected void useField(Field f){
		if(!f.isInUse()){
			f.toggleInUse();
		}
	}
	
	protected void stepBack(){
		System.out.println("Step Back");
		if(history.isEmpty()){
			finished = true;
		}else{
			history.remove(history.size()-1);
			if(history.isEmpty()){
				finished = true;
			}else{
				setCurrentField(history.get(history.size()-1));
			}
		}
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

	protected abstract void work();

}
