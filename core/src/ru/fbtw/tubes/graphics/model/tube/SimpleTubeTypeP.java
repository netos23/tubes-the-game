package ru.fbtw.tubes.graphics.model.tube;

import ru.fbtw.tubes.core.tube.TubeEntity;

public class SimpleTubeTypeP extends SimpleTube {
    public static final String TEXTURE_NAME = "tubes\\+-tube";

    public SimpleTubeTypeP() {
        super(TEXTURE_NAME);
    }

    @Override
    public void updateRotation(TubeEntity entity) {

    }
}
