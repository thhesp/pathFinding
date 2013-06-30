package de.ur.ts.map;

import java.util.ArrayList;

public class Field {
	
	private boolean inUse = false;
	private boolean active = false;
	private boolean empty = true;
	private boolean hasValue = false;
	private String algorithmValue = "";
	
	private ArrayList<Field> neighbors = new ArrayList<Field>();
	private ArrayList<Field> diagNeighbors = new ArrayList<Field>();
	
	public Field(){
		empty = true;
		hasValue = false;
	}
	
	public Field(boolean empty){
		this.empty = empty;
		hasValue = false;
	}
	
	public Field(String value){
		algorithmValue = value;
		hasValue = true;
		empty = true;
	}
	
	public Field(boolean empty, String value){
		algorithmValue = value;
		hasValue = true;
		this.empty = empty;
	}
	
	public boolean isActive(){
		return active;
	}
	
	public void activate(){
		active = true;
	}
	
	public void deactivate(){
		active = false;
	}
	
	public boolean isInUse(){
		return inUse;
	}
	
	public void toggleInUse(){
		inUse = !inUse;
	}
	
	public boolean isEmpty(){
		return empty;
	}
	
	public void setEmpty(boolean v){
		empty = v;
	}
	
	public void toggleEmpty(){
		empty = !empty;
	}
	
	public void setValue(String value){
		algorithmValue = value;
		hasValue = true;
	}
	
	public void removeValue(){
		algorithmValue = "";
		hasValue = false;
	}
	
	public boolean hasValue(){
		return hasValue;
	}
	
	public String getValue(){
		return algorithmValue;
	}
	
	public void setNeighbors(ArrayList<Field> list){
		neighbors = list;
	}
	
	public void setDiagNeighbors(ArrayList<Field> list){
		diagNeighbors = list;
	}
	
	public void addNeighbor(Field f){
		if(!neighbors.contains(f) && !diagNeighbors.contains(f)) neighbors.add(f);
	}
	
	public void addDiagNeighbor(Field f){
		if(!neighbors.contains(f) && !diagNeighbors.contains(f)) diagNeighbors.add(f);
	}
	
	public ArrayList<Field> getNeighbors(){
		return neighbors;
	}
	
	public ArrayList<Field> getDiagNeighbors(){
		return diagNeighbors;
	}
	
	public ArrayList<Field> getAllNeighbors(){
		ArrayList<Field> list = new ArrayList<Field>();
		list.addAll(neighbors);
		list.addAll(diagNeighbors);
		return list;
	}
	
	public boolean hasNeighbor(Field f){
		if(neighbors.contains(f)) return true;
		if(diagNeighbors.contains(f)) return true;
		
		return false;
	}
	
	public void reset(){
		active = false;
		inUse = false;
		empty = true;
		hasValue = false;
		algorithmValue = "";
	}
	
	public String asString(){
		String str = "";
		if(empty){
			str += "The Field is empty.\n";
		}else{
			str += "The Field is occupied.\n";
		}
		
		if(hasValue){
			str += "The current value is " + algorithmValue + " \n";
		}else{
			str += "The Field has no value. \n";
		}
		
		if(active){
			str += "The Field is active. \n";
		}else{
			str += "The Field is inactive. \n";
		}
		
		str += this;
		
		return str;
	}

	public void resetAlgorithmData() {
		active = false;
		inUse = false;
		hasValue = false;
		algorithmValue = "";
	}

}
