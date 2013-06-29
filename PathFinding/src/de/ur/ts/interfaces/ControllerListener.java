package de.ur.ts.interfaces;

import de.ur.ts.map.Map;

public interface ControllerListener {

	public void onMapCreated(Map map);
	
	public void onMapChange();
}
