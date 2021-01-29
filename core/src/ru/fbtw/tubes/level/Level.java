package ru.fbtw.tubes.level;

import ru.fbtw.tubes.core.tube.TubeGraph;
import ru.fbtw.tubes.core.tube.TubeGraphBuilder;
import ru.fbtw.tubes.graphics.model.tube.TubeModel;

public class Level {
    private int w,h;
    private TubeModel model;
    private TubeGraph graph;


    public Level(int w, int h) {
        this.w = w;
        this.h = h;

        graph = new TubeGraphBuilder(w,h)
                .setDefaultOrigin()
                .setDestination(w-1, h-1)
                .build();


    }
}
