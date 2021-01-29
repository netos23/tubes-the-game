package ru.fbtw.tubes.math.graph;

import java.util.LinkedHashSet;

public interface Graph<T extends Graph.GraphEntity> {

	GraphEntity[][] adjacencyMatrix();

	void connect(T a, T b);

	boolean deepSearch(T origin, T destination, LinkedHashSet<T> track);
	boolean deepSearch(T origin, T destination);

	interface GraphEntity{
		GraphEntity[] getNeighbors();

		void addNeighbour(GraphEntity neighbour, Direction dir) throws Exception;

		void clear();
	}
}
