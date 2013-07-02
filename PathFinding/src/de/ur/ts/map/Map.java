package de.ur.ts.map;

import org.json.JSONObject;

public class Map {
	
	private static final int DEFAULT_VALUE = -1;
	
	private int rows, cols;
	private Field[][] map;
	
	private int startCol, startRow;
	private int goalCol, goalRow;
	
	public Map(int cols, int rows){
		this.rows = rows;
		this.cols = cols;
		
		createMap();
		resetStart();
		resetGoal();
	}
	
	public boolean hasStart(){
		return  (startRow != DEFAULT_VALUE && startCol != DEFAULT_VALUE);
	}
	
	public boolean hasGoal(){
		return (goalRow != DEFAULT_VALUE && goalCol != DEFAULT_VALUE);
	}
	
	public Field getStart(){
		return map[startRow][startCol];
	}
	
	public Field getGoal(){
		return map[goalRow][goalCol];
	}
	
	public int getWidth(){
		return cols;
	}
	
	public int getHeight(){
		return rows;
	}
	
	public boolean setStart(int x, int y){
		if(map[x][y].isEmpty() && (x != goalCol || y != goalRow)){
			startCol = x;
			startRow = y;
			
			return true;
		}
		
		return false;
	}
	
	public boolean setGoal(int x, int y){
		if(map[x][y].isEmpty() && (x != startCol || y != startRow)){
			goalCol = x;
			goalRow = y;
			
			return true;
		}
		
		return false;
	}
	
	public int getStartX(){
		return startCol;
	}
	
	public int getStartY(){
		return startRow;
	}
	
	public int getGoalX(){
		return goalCol;
	}
	
	public int getGoalY(){
		return goalRow;
	}
	
	public boolean isStart(int x, int y){
		if(x != DEFAULT_VALUE && x == startCol && y == startRow) return true;
		return false;
	}
	
	public boolean isGoal(int x, int y){
		if(x != DEFAULT_VALUE && x == goalCol && y == goalRow) return true;
		return false;
	}
	
	private void createMap(){
		map = new Field[rows][cols];
		
		for(int rowIndex = 0; rowIndex < rows; rowIndex++){
			for(int colIndex = 0; colIndex < cols; colIndex++){
				map[rowIndex][colIndex] = new Field();
			}
		}
		
		connectFields();
	}

	private void connectFields() {
		for(int rowIndex = 0; rowIndex < rows; rowIndex++){
			for(int colIndex = 0; colIndex < cols; colIndex++){
				boolean top = (rowIndex > 0);
				boolean bottom = (rowIndex < (rows-1));
				boolean left = (colIndex > 0);
				boolean right = (colIndex < (cols-1));
				
				//connect Neighbors
				if(top){
					//top neighbor
					map[rowIndex][colIndex].addNeighbor(map[rowIndex-1][colIndex]);
					
				}
				if(bottom){
					//bottom neighbor
					map[rowIndex][colIndex].addNeighbor(map[rowIndex+1][colIndex]);
				}
				if(left){
					//left neighbor
					map[rowIndex][colIndex].addNeighbor(map[rowIndex][colIndex-1]);
				}
				if(right){
					//right neighbor
					map[rowIndex][colIndex].addNeighbor(map[rowIndex][colIndex+1]);
				}
				
				//connect diag Neighbors
				if(top && left){
					//top left neighbor
					map[rowIndex][colIndex].addDiagNeighbor(map[rowIndex-1][colIndex-1]);
					
				}
				if(top && right){
					//top right neighbor
					map[rowIndex][colIndex].addDiagNeighbor(map[rowIndex-1][colIndex+1]);
				}
				if(bottom & left){
					//bottom left neighbor
					map[rowIndex][colIndex].addDiagNeighbor(map[rowIndex+1][colIndex-1]);
				}
				if(bottom &right){
					//bottom right neighbor
					map[rowIndex][colIndex].addDiagNeighbor(map[rowIndex+1][colIndex+1]);
				}
			}
		}
	}
	
	public Field[][] getFields(){
		return map;
	}
	
	public Field getField(int colIndex, int rowIndex){
		return map[rowIndex][colIndex];
	}
	
	public void resetFields(){
		for(int rowIndex = 0; rowIndex < rows; rowIndex++){
			for(int colIndex = 0; colIndex < cols; colIndex++){
				map[rowIndex][colIndex].reset();
			}
		}
	}
	
	public void resetStart(){
		startCol = startRow = DEFAULT_VALUE;
	}
	
	public void resetGoal(){
		goalCol = goalRow = DEFAULT_VALUE;
	}
	
	public void reset(){
		resetFields();
		resetStart();
		resetGoal();
	}

	public void resetAlgorithmData() {
		for(int rowIndex = 0; rowIndex < rows; rowIndex++){
			for(int colIndex = 0; colIndex < cols; colIndex++){
				map[rowIndex][colIndex].resetAlgorithmData();
			}
		}
	}
	
	public JSONObject toJSON(){
		JSONObject obj = new JSONObject();
		obj.put("Columns", cols);
		obj.put("Rows", rows);
		obj.put("StartCol", startCol);
		obj.put("StartRow", startRow);
		obj.put("GoalCol" , goalCol);
		obj.put("GoalRow", goalRow);
		
		for(int rowIndex = 0; rowIndex < rows; rowIndex++){
			for(int colIndex = 0; colIndex < cols; colIndex++){
				if(!map[rowIndex][colIndex].isEmpty()){
					obj.put(rowIndex + ":" + colIndex, true);
				}
			}
		}
		return obj;
	}

}
