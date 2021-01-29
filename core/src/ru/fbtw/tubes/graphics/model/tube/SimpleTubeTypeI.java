package ru.fbtw.tubes.graphics.model.tube;

import ru.fbtw.tubes.core.tube.TubeEntity;
import ru.fbtw.tubes.math.graph.Direction;

public class SimpleTubeTypeI extends SimpleTube {
    public static final String TEXTURE_NAME = "tubes\\I-tube";

    public SimpleTubeTypeI() {
        super(TEXTURE_NAME);
    }

    @Override
    public void updateRotation(TubeEntity entity) {
        if(entity.getDirections()[0].equals(Direction.UP)
                || entity.getDirections()[0].equals(Direction.BOTTOM)){
            if (sprite.getRotation() != 90){
                sprite.setRotation(90);
            }
        }else{
            if(sprite.getRotation() != 0) {
                sprite.setRotation(0);
            }
        }
    }
}
