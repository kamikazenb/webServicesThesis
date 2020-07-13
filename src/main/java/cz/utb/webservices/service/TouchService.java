package cz.utb.webservices.service;

import cz.utb.webservices.dao.TouchDao;
import cz.utb.webservices.model.Touch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TouchService {

    private final TouchDao touchDao;

    @Autowired
    public TouchService(@Qualifier("postgres") TouchDao touchDao) {
        this.touchDao = touchDao;
    }

    public int addTouch(Touch touch) {
        return touchDao.insertTouch(touch);
    }

    public List<Touch> getAllTouches() {
        return touchDao.selectAllTouches();
    }

    public Optional<Touch> getTouchById(UUID id) {
        return touchDao.selectTouchById(id);
    }

    public int deleteTouch(UUID id) {
        return touchDao.deleteTouchById(id);
    }

    public int updateTouch(UUID id, Touch newTouch) {
        return touchDao.updateTouchById(id, newTouch);
    }

}
