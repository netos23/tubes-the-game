package ru.fbtw.tubes.math.graph;

public class RectangleGraphEntity implements Graph.GraphEntity {
	protected final int neighborsCount = 4;

	protected RectangleGraphEntity[] neighbors;
	protected int x,y;

	public RectangleGraphEntity(int x,int y) {
		this.x = x;
		this.y = y;

		neighbors = new RectangleGraphEntity[neighborsCount];
	}



	@Override
	public RectangleGraphEntity[] getNeighbors() {
		return neighbors;
	}

	@Override
	public void addNeighbour(Graph.GraphEntity neighbour, Direction dir) throws Exception{
		if(!(dir instanceof Direction
				&& neighbour instanceof RectangleGraphEntity)) throw new Exception("Unsupported enum type");

		neighbors[dir.ordinal()] = (RectangleGraphEntity) neighbour;
	}

	@Override
	public void clear() {
		neighbors = null;
		neighbors = new RectangleGraphEntity[neighborsCount];
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
