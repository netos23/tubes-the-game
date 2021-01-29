package ru.fbtw.tubes.graphics.model.tube;

import ru.fbtw.tubes.core.tube.TubeEntity;
import ru.fbtw.tubes.math.graph.Direction;

public class TubeModelFactory {
    public TubeModel getTube(TubeEntity entity) {
        switch (getTubeType(entity)) {
            case 0:
                return getTypeI(entity);
            case 1:
                return getTypeL(entity);
            case 2:
                return getTypeT(entity);
            case 3:
                return getTypeP(entity);
            default:
                return null;
        }
    }

    private int getTubeType(TubeEntity entity) {
        if (entity.getDirections().length == 4) {
            return 3;
        } else if (entity.getDirections().length == 3) {
            return 2;
        } else {
            Direction[] directions = entity.getDirections();
            if (directions[0].inversion().equals(directions[1])) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    public TubeModel getTypeL(TubeEntity entity) {
        SimpleTubeTypeL model = new SimpleTubeTypeL();
        return updateRotation(model, entity);
    }

    public TubeModel getTypeP(TubeEntity entity) {
        SimpleTubeTypeP model = new SimpleTubeTypeP();
        return updateRotation(model, entity);
    }

    public TubeModel getTypeT(TubeEntity entity) {
        SimpleTubeTypeT model = new SimpleTubeTypeT();
        return updateRotation(model, entity);
    }

    public TubeModel getTypeI(TubeEntity entity) {
        SimpleTubeTypeI model = new SimpleTubeTypeI();
        return updateRotation(model, entity);
    }

    private TubeModel updateRotation(TubeModel model, TubeEntity entity) {
        model.updateRotation(entity);
        return model;
    }
}
