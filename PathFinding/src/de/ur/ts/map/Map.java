package de.ur.ts.map;

public class Map {
	
	private static final int DEFAULT_VALUE = -1;
	
	private int rows, cols;
	private Field[][] map;
	
	private int startX, startY;
	private int goalX, goalY;
	
	public Map(int cols, int rows){
		this.rows = rows;
		this.cols = cols;
		
		createMap();
		resetStart();
		resetGoal();
	}
	
	public int getWidth(){
		return cols;
	}
	
	public int getHeight(){
		return rows;
	}
	
	public boolean setStart(int x, int y){
		if(map[x][y].isEmpty() && x != goalX && y != goalY){
			startX = x;
			startY = y;
			
			return true;
		}
		
		return false;
	}
	
	public boolean setGoal(int x, int y){
		if(map[x][y].isEmpty() && x != startX && y != startY){
			goalX = x;
			goalY = y;
			
			return true;
		}
		
		return false;
	}
	
	public int getStartX(){
		return startX;
	}
	
	public int getStartY(){
		return startY;
	}
	
	public int getGoalX(){
		return goalX;
	}
	
	public int getGoalY(){
		return goalY;
	}
	
	public boolean isStart(int x, int y){
		if(x != DEFAULT_VALUE && x == startX && y == startY) return true;
		return false;
	}
	
	public boolean isGoal(int x, int y){
		if(x != DEFAULT_VALUE && x == goalX && y == goalY) return true;
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
	
	public void resetMap(){
		for(int rowIndex = 0; rowIndex < rows; rowIndex++){
			for(int colIndex = 0; colIndex < cols; colIndex++){
				map[rowIndex][colIndex].reset();
			}
		}
	}
	
	public void resetStart(){
		startX = startY = DEFAULT_VALUE;
	}
	
	public void resetGoal(){
		goalX = goalY = DEFAULT_VALUE;
	}

}
