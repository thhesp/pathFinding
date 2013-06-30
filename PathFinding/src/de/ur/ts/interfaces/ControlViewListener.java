package de.ur.ts.interfaces;

public interface ControlViewListener {
	
	public void onStart(String algorithmName);
	public void onPause();
	public void onStop();
	public void onReset();
	public void onFaster();
	public void onSlower();
	public void onResetSpeed();

}
