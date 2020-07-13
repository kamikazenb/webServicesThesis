package cz.utb.webservices.dao;

import cz.utb.webservices.model.Touch;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository("postgres")
public class TouchDataAccessService implements TouchDao {
    @Override
    public int insertTouch(UUID id, Touch touch) {
        return 0;
    }

    @Override
    public List<Touch> selectAllTouches() {
        List<Touch> result = new ArrayList<>();
        result.add(new Touch(UUID.randomUUID(), (float)0.66));
        return result;
    }

    @Override
    public Optional<Touch> selectTouchById(UUID id) {
        return Optional.empty();
    }

    @Override
    public int deleteTouchById(UUID id) {
        return 0;
    }

    @Override
    public int updateTouchById(UUID id, Touch touch) {
        return 0;
    }
}
