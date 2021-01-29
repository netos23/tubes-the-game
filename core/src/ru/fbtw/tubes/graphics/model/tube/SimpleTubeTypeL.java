package ru.fbtw.tubes.graphics.model.tube;

import ru.fbtw.tubes.core.tube.TubeEntity;
import ru.fbtw.tubes.math.graph.Direction;

public class SimpleTubeTypeL extends SimpleTube {
    public static final String TEXTURE_NAME = "tubes\\L-tube";

    public SimpleTubeTypeL() {
        super(TEXTURE_NAME);

    }

    @Override
    public void updateRotation(TubeEntity entity) {
        boolean[] dirs = new boolean[4];
        for (Direction dir : entity.getDirections()) {
            dirs[dir.ordinal()] = true;
        }
        if (dirs[2] && dirs[3]) {
            sprite.setRotation(0);
        }

        if (dirs[0] && dirs[3]) {
            sprite.setRotation(270);
        }
        if (dirs[1] && dirs[2]) {
            sprite.setRotation(90);
        }

        if (dirs[0] && dirs[1]) {
            sprite.setRotation(180);
        }
    }

}
