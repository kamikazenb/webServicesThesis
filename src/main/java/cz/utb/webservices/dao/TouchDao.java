package cz.utb.webservices.dao;

import cz.utb.webservices.model.Touch;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TouchDao {
    int insertTouch(UUID id, Touch touch);

    default int insertTouch(Touch touch){
        UUID id = UUID.randomUUID();
        return insertTouch(id, touch);
    }

    List<Touch> selectAllTouches();

    Optional<Touch> selectTouchById(UUID id);

    int deleteTouchById(UUID id);

    int updateTouchById(UUID id, Touch touch);
}
