package cz.utb.webservices.dao;

import cz.utb.webservices.model.Touch;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakeTouchDataAccessService implements TouchDao {

    private static List<Touch> DB = new ArrayList<>();

    @Override
    public int insertTouch(UUID id, Touch touch) {
        DB.add(new Touch(id, touch.getX()));
        return 1;
    }

    @Override
    public List<Touch> selectAllTouches() {
        return DB;
    }

    @Override
    public Optional<Touch> selectTouchById(UUID id) {
        return DB.stream().filter(touch -> touch.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deleteTouchById(UUID id) {
        Optional<Touch> touchMaybe = selectTouchById(id);

        if (!touchMaybe.isPresent()) {
            return 0;
        }

        DB.remove(touchMaybe.get());
        return 1;

    }

    @Override
    public int updateTouchById(UUID id, Touch updateTouch) {
        return selectTouchById(id).map(touch -> {
            int indexOfTouchToUpdate = DB.indexOf(touch);
            if (indexOfTouchToUpdate >= 0) {
                DB.set(indexOfTouchToUpdate, new Touch(id, updateTouch.getX()));
                return 1;
            }
            return 0;
        }).orElse(0);
    }
}
