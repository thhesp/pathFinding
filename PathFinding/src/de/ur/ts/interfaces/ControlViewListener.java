package de.ur.ts.interfaces;

public interface ControlViewListener {
	
	public void onStart(String algorithmName);
	public void onPause();
	public void onStop();
	public void onFaster();
	public void onSlower();
	public void onResetSpeed();
	public void onCreateMap();
	public void onLoadMap();
	public void onSaveMap();
	public void onResetMap();
	public void onActivateValues();
	public void onDeactivateValues();

}
