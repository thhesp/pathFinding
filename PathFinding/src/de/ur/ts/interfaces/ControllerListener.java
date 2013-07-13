package de.ur.ts.interfaces;

import de.ur.ts.map.Map;

public interface ControllerListener {

	public void onMapCreated(Map map);
	
	public void onMapChange();
	
	public void onDeactivateValues();
	public void onActivateValues();
	
	public void onSaveImage(long systemTime, String algorithmName);
}
