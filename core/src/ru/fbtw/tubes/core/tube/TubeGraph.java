package ru.fbtw.tubes.core.tube;

import java.util.ArrayList;
import java.util.LinkedHashSet;

import ru.fbtw.tubes.math.graph.Direction;
import ru.fbtw.tubes.math.graph.Graph;
import ru.fbtw.tubes.math.graph.RectangleGraph;
import ru.fbtw.tubes.math.graph.RectangleGraphEntity;
import ru.fbtw.tubes.utils.Arrays;
import ru.fbtw.tubes.utils.Vector2;


public class TubeGraph implements Graph<RectangleGraphEntity> {
    private TubeEntity[][] nodes;
    private RectangleGraph graph;
    private TubeEntity origin, destination;

    public TubeGraph(RectangleGraph parent) {
        RectangleGraphEntity[][] tmpMatrix = initNodes(parent);

        setAllNeighbours(tmpMatrix);

        graph = new RectangleGraph(nodes);
    }

    public TubeGraph(RectangleGraph parent,
                     Vector2<Integer> originPos, Vector2<Integer> destinationPos) {
        RectangleGraphEntity[][] tmpMatrix = initNodes(parent);

        setWayPoint(WayPoint.ORIGIN, originPos);
        setWayPoint(WayPoint.DESTINATION, destinationPos);
        setAllNeighbours(tmpMatrix);

        graph = new RectangleGraph(nodes);

    }

    private void setAllNeighbours(RectangleGraphEntity[][] tmpMatrix) {
        for (int i = 0; i < tmpMatrix.length; i++) {
            for (int j = 0; j < tmpMatrix[0].length; j++) {
                setNeighbours(nodes[i][j]);
            }
        }
    }

    private RectangleGraphEntity[][] initNodes(RectangleGraph parent) {
        nodes = new TubeEntity[parent.getHeight()][parent.getWidth()];
        RectangleGraphEntity[][] tmpMatrix = parent.adjacencyMatrix();

        for (int i = 0; i < tmpMatrix.length; i++) {
            for (int j = 0; j < tmpMatrix[0].length; j++) {
                RectangleGraphEntity entity = tmpMatrix[i][j];

                nodes[i][j] = new TubeEntity(j, i, getDirections(entity));
            }
        }
        return tmpMatrix;
    }

    /**
     * !!!!WARNING!!!!
     * if the origin was initialized method don`t remove UP direction
     * from direction list, but add UP to the direction list of new origin
     */

    public void setWayPoint(WayPoint waypoint, Vector2<Integer> position) {
        TubeEntity target = nodes[position.getY()][position.getX()];

        if (waypoint == WayPoint.ORIGIN) {
            if (Arrays.indexOf(target.getDirections(), Direction.UP) == -1) {
                target.addDirection(Direction.UP);
                origin = target;
            }
        } else {
			if (Arrays.indexOf(target.getDirections(), Direction.BOTTOM) == -1) {
				target.addDirection(Direction.BOTTOM);
				destination = target;
			}
        }
    }

    private void setNeighbours(TubeEntity entity) {
        try {
            for (Direction dir : entity.getDirections()) {
                TubeEntity neighbour = getNeighbourByDirection(entity, dir);

                if (neighbour != null && neighbour.inverseEquals(dir)) {
                    entity.addNeighbour(neighbour, dir);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private TubeEntity getNeighbourByDirection(TubeEntity entity, Direction dir) {
        switch (dir) {
            case UP:
                if (entity.getY() > 0) {
                    return nodes[entity.getY() - 1][entity.getX()];
                }
                break;
            case BOTTOM:
                if (entity.getY() < nodes.length - 1) {
                    return nodes[entity.getY() + 1][entity.getX()];
                }
                break;
            case LEFT:
                if (entity.getX() > 0) {
                    return nodes[entity.getY()][entity.getX() - 1];
                }
                break;
            case RIGHT:
                if (entity.getX() < nodes[0].length - 1) {
                    return nodes[entity.getY()][entity.getX() + 1];
                }
                break;
        }
        return null;
    }


    private Direction[] getDirections(RectangleGraphEntity entity) {
        RectangleGraphEntity[] neighbours = entity.getNeighbors();
        ArrayList<Direction> directions = new ArrayList<>(4);

        for (int i = 0; i < neighbours.length; i++) {
            if (neighbours[i] != null) {
                directions.add(Direction.values()[i]);
            }
        }

        if (directions.size() == 1) {
            Direction direction = directions.get(0);
            directions.add(direction.inversion());
        }

        return directions.toArray(new Direction[0]);
    }

    public void rotate(int i, int j) {
        TubeEntity rotationTube = nodes[i][j];

        rotationTube.clear();
        rotationTube.rotateDirections();

        setNeighbours(rotationTube);
    }

	/*public void swap(TubeEntity a, TubeEntity b){

	}*/

    @Override
    public GraphEntity[][] adjacencyMatrix() {
        return graph.adjacencyMatrix();
    }

    @Override
    public void connect(RectangleGraphEntity a, RectangleGraphEntity b) {
        graph.connect(a, b);
    }

    public boolean validateOrigin() {
        return Arrays.indexOf(origin.getDirections(), Direction.UP) > -1;
    }

    public boolean validateDestination() {
        return Arrays.indexOf(origin.getDirections(), Direction.BOTTOM) > -1;
    }

    @Override
    public boolean deepSearch(RectangleGraphEntity origin, RectangleGraphEntity destination, LinkedHashSet<RectangleGraphEntity> track) {
        return graph.deepSearch(origin, destination, track);
    }

    @Override
    public boolean deepSearch(RectangleGraphEntity origin, RectangleGraphEntity destination) {
        return graph.deepSearch(origin, destination);
    }

    public boolean deepSearch() throws Exception {
        if (origin == null || destination == null) {
            throw new Exception("Origin or destination did not set to the current graph");
        }
        return deepSearch(origin, destination);
    }


    public enum WayPoint {
        ORIGIN,
        DESTINATION
    }
}
