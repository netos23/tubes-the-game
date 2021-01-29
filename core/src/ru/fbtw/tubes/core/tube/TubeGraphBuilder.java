package ru.fbtw.tubes.core.tube;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;

import ru.fbtw.tubes.math.graph.RectangleGraph;
import ru.fbtw.tubes.math.graph.RectangleGraphEntity;
import ru.fbtw.tubes.utils.Vector2;

public class TubeGraphBuilder {
    private final Vector2<Integer> DEFAULT_ORIGIN = new Vector2<>(0, 0);

    private RectangleGraph baseGraph;
    private RectangleGraphEntity[][] matrix;
    private Vector2<Integer> origin, destination;
    private int w, h;


    private HashSet<RectangleGraphEntity> checked;
    private LinkedList<RectangleGraphEntity> nodesList;
    private boolean shuffle;

    public TubeGraphBuilder(int w, int h) {
        this.w = w;
        this.h = h;

        baseGraph = new RectangleGraph(w, h);
        matrix = baseGraph.adjacencyMatrix();

    }


    private void generate() {
        if (origin == null) {
            setDefaultOrigin();
        }

        Random random = new Random();
        Stack<RectangleGraphEntity> stack = new Stack<>();
        checked = new HashSet<>();
        nodesList = baseGraph.entitesList();
        RectangleGraphEntity current = matrix[origin.getY()][origin.getX()];


        check(current);

        int length = w * h;
        while (checked.size() != length) {
            ArrayList<RectangleGraphEntity> neighbours = getUncheckedNeighbours(current, checked);


            if (neighbours.size() > 0) {
                stack.push(current);

                int index = random.nextInt(neighbours.size());
                RectangleGraphEntity next = neighbours.get(index);

                baseGraph.connect(current, next);

                current = next;
                check(current);
            } else if (!stack.empty()) {
                current = stack.pop();
            } else {
                int index = random.nextInt(nodesList.size());
                current = nodesList.get(index);
            }
        }

    }

    private void check(RectangleGraphEntity entity) {
        nodesList.remove(entity);
        checked.add(entity);
    }

    private ArrayList<RectangleGraphEntity> getUncheckedNeighbours(
            RectangleGraphEntity target,
            HashSet<RectangleGraphEntity> checked) {

        int i = target.getY(), j = target.getX();
        ArrayList<RectangleGraphEntity> result = new ArrayList<>();
        RectangleGraphEntity tmp;


        if (i > 0) {
            tmp = matrix[i - 1][j];

            if (!checked.contains(tmp)) {
                result.add(tmp);
            }
        }

        if (i < h - 1) {
            tmp = matrix[i + 1][j];

            if (!checked.contains(tmp)) {
                result.add(tmp);
            }
        }

        if (j > 0) {
            tmp = matrix[i][j - 1];

            if (!checked.contains(tmp)) {
                result.add(tmp);
            }
        }

        if (j < w - 1) {
            tmp = matrix[i][j + 1];

            if (!checked.contains(tmp)) {
                result.add(tmp);
            }
        }
        return result;
    }

    public TubeGraphBuilder setOrigin(int x, int y) {
        this.origin = new Vector2<>(x, y);
        return this;
    }

    public TubeGraphBuilder setDestination(int x, int y) {
        this.destination = new Vector2<>(x, y);
        return this;
    }

    public TubeGraphBuilder setDefaultOrigin() {
        this.origin = DEFAULT_ORIGIN;
        return this;
    }

    public TubeGraphBuilder setShuffle(boolean shuffle) {
        this.shuffle = shuffle;
        return this;
    }

    private void shuffle(TubeGraph graph) {
        TubeEntity[][] entities = (TubeEntity[][]) graph.adjacencyMatrix();
        Random random = new Random();
        for (int r = 0, entitiesLength = entities.length; r < entitiesLength; r++) {
            for (int c = 0, rowLength = entities[0].length; c < rowLength; c++) {
                if (random.nextInt(1001) < 900) {
                    int chance = random.nextInt(3001);
                    int rotCount;
                    if (chance < 1000) {
                        rotCount = 1;
                    } else if (chance < 2000) {
                        rotCount = 2;
                    } else {
                        rotCount = 3;
                    }
                    for (int rotation = 0; rotation < rotCount; rotation++) {
                        graph.rotate(r, c);
                    }
                }
            }
        }
    }

    public TubeGraph build() {
        generate();

        if (destination == null) {
            Random random = new Random();
            int index = random.nextInt(w);
            destination = new Vector2<>(index, h - 1);
        }

        TubeGraph tubeGraph = new TubeGraph(baseGraph, origin, destination);

        if (shuffle) {
            shuffle(tubeGraph);
        }

        return tubeGraph;
    }
}
