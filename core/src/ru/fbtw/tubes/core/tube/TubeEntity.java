package ru.fbtw.tubes.core.tube;



import ru.fbtw.tubes.math.graph.Direction;
import ru.fbtw.tubes.math.graph.RectangleGraphEntity;

import java.util.Arrays;


public class TubeEntity extends RectangleGraphEntity {

	private Direction[] directions;

	TubeEntity(int x, int y, Direction[] directions) {
		super(x, y);
		this.directions = directions;
	}

	public void rotateDirections(){
		for (int i = 0; i < directions.length; i++) {
			if (directions[i] != null) {
				directions[i] = directions[i].rotate();
			}
		}
	}

	public void addDirection(Direction direction){
		int newLen = directions.length + 1;
		directions = Arrays.copyOf(directions,newLen);
		directions[newLen - 1] = direction;
	}

	public boolean inverseEquals(Direction other){
		for (Direction dir: directions) {
			if(dir.inversion() == other){
				return true;
			}
		}

		return false;
	}

	public Direction[] getDirections() {
		return directions;
	}

}
