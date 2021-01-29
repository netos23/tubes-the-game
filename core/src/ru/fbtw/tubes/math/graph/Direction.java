package ru.fbtw.tubes.math.graph;


public enum Direction {
	UP,
	RIGHT,
	BOTTOM,
	LEFT;

	public Direction rotate() {
		int i = ordinal() + 1;
		int max = values().length;

		i = (i == max) ? 0 : i;

		return values()[i];
	}

	public Direction inversion(){
		switch (this){
			case UP:
				return BOTTOM;
			case BOTTOM:
				return UP;
			case LEFT:
				return RIGHT;
			case RIGHT:
				return LEFT;
		}
		return null;
	}
}
