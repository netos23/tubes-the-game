package ru.fbtw.tubes.graphics.model.tube;

import ru.fbtw.tubes.core.tube.TubeEntity;
import ru.fbtw.tubes.graphics.Renderable;

public interface TubeModel extends Renderable {
    void updateRotation(TubeEntity entity);
}
