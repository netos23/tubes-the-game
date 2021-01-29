package ru.fbtw.tubes.graphics.model.tube;

import ru.fbtw.tubes.core.tube.TubeEntity;
import ru.fbtw.tubes.math.graph.Direction;

public class SimpleTubeTypeT extends SimpleTube {
    public static final String TEXTURE_NAME = "tubes\\T-tube";

    public SimpleTubeTypeT() {
        super(TEXTURE_NAME);
    }

    @Override
    public void updateRotation(TubeEntity entity) {
        int rotation = 6;
        for(Direction direction : entity.getDirections()){
            rotation-=direction.ordinal();
        }

        switch (rotation){
            case 0:
                if(sprite.getRotation() != 0){
                    sprite.setRotation(0);
                }
                break;
            case 1:
                if(sprite.getRotation() != 270){
                    sprite.setRotation(270);
                }
                break;
            case 2:
                if(sprite.getRotation() != 180){
                    sprite.setRotation(180);
                }
                break;
            case 3:
                if(sprite.getRotation() != 90){
                    sprite.setRotation(90);
                }
                break;
        }
    }
}
