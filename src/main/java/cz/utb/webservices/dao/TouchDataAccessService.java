package cz.utb.webservices.dao;

import cz.utb.webservices.model.Touch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository("postgres")
public class TouchDataAccessService implements TouchDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TouchDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int insertTouch(Touch touch) {
        String sql = "" +
                "INSERT INTO touch " +
                "(touchType, " +
                "x, " +
                "y, " +
                "clientCreated, " +
                "serverReceived, " +
                "serverType_idserverType, " +
                "client_idclient) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                touch.getTouchType(),
                touch.getX(),
                touch.getY(),
                touch.getClientCreated(),
                touch.getServerReceived(),
                touch.getServerType_idserverType(),
                touch.getClient_idclient()
        );
    }

    @Override
    public List<Touch> selectAllTouches() {
        final String sql = "SELECT touchType," +
                "x, " +
                "y, " +
                "clientCreated," +
                "serverReceived" +
                " FROM touch";
        return jdbcTemplate.query(sql, mapTouchFomDb());
    }
    private RowMapper<Touch> mapTouchFomDb() {
        return (resultSet, i) -> {
            String touchType = resultSet.getString("touchType");
            float x = resultSet.getFloat("x");
            float y = resultSet.getFloat("x");
            String clientCreated = resultSet.getString("clientCreated");
            String serverReceive = resultSet.getString("serverReceived");
            return new Touch(
                    x,
                    y,
                    touchType,
                    clientCreated,
                    serverReceive,
                    0
            );
        };
    }


    /*@Override
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
    }*/
}
