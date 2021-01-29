package ru.fbtw.tubes.math.graph;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public class RectangleGraph implements Graph<RectangleGraphEntity> {
	private RectangleGraphEntity[][] nodes;

	public RectangleGraph(int w, int h) {
		nodes = new RectangleGraphEntity[h][w];

		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				nodes[i][j] = new RectangleGraphEntity(j, i);
			}
		}
	}

	public RectangleGraph(RectangleGraphEntity[][] adjacencyMatrix){
		this.nodes = adjacencyMatrix;
	}

	@Override
	public RectangleGraphEntity[][] adjacencyMatrix() {
		return nodes;
	}

	public LinkedList<RectangleGraphEntity> entitesList(){
		LinkedList<RectangleGraphEntity> entities = new LinkedList<>();
		for(RectangleGraphEntity[] node : nodes){
			Collections.addAll(entities,node);
		}

		return entities;
	}


	/*protected final void setAdjacencyMatrix(RectangleGraphEntity[][] adjacencyMatrix) {
		this.nodes = adjacencyMatrix;
	}*/


	@Override
	public void connect(RectangleGraphEntity a, RectangleGraphEntity b) {
		if(a.equals(b)) return;

		Direction direction = getDirection(a, b);

		try {
			a.addNeighbour(b, direction);
			b.addNeighbour(a, direction.inversion());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Direction getDirection(RectangleGraphEntity a, RectangleGraphEntity b) {

		if (a.getY() == b.getY()) {
			if (a.getX() > b.getX()) {
				return Direction.LEFT;
			} else {
				return Direction.RIGHT;
			}
		} else {
			if (a.getY() < b.getY()) {
				return Direction.BOTTOM;
			} else {
				return Direction.UP;
			}
		}
	}

	@Override
	public boolean deepSearch(RectangleGraphEntity origin, RectangleGraphEntity destination,
							  LinkedHashSet<RectangleGraphEntity> track) {
		LinkedList<RectangleGraphEntity> elementsStack = new LinkedList<>();
		track = new LinkedHashSet<>();

		elementsStack.push(origin);

		boolean isDestination = false;

		while (!(elementsStack.isEmpty() || isDestination)) {
			RectangleGraphEntity last = elementsStack.pop();
			isDestination = destination.equals(last);

			track.add(last);

			for (RectangleGraphEntity neighbour : last.getNeighbors()) {
				if (neighbour != null && !track.contains(neighbour)) {
					elementsStack.push(neighbour);
				}
			}
		}
		return isDestination;
	}

	@Override
	public boolean deepSearch(RectangleGraphEntity origin, RectangleGraphEntity destination) {
		return deepSearch(origin,destination,null);
	}


	public int getWidth(){
		return nodes[0].length;
	}

	public int getHeight(){
		return nodes.length;
	}

}
