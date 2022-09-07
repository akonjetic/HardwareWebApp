package hr.tvz.konjetic.hardwareapp.model;

import hr.tvz.konjetic.hardwareapp.enums.Type;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


@Primary
@Repository
public class JdbcHardwareRepository implements HardwareRepository, Serializable {

    private static final String SELECT_ALL =
            "SELECT code, product_name, price, product_type, available_num FROM hardware";

    private final JdbcTemplate jdbc;
    private final SimpleJdbcInsert inserter;

    public JdbcHardwareRepository(JdbcTemplate jdbc){
        this.jdbc=jdbc;
        this.inserter = new SimpleJdbcInsert(jdbc)
                .withTableName("hardware")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Set<Hardware> findAll() {return Set.copyOf(jdbc.query(SELECT_ALL, this::mapRowToHardware));
    }

    @Override
    public Optional<Hardware> findByCode(String code) {
        try{
            return Optional.ofNullable(
                    jdbc.queryForObject(SELECT_ALL + " WHERE code = ?", this::mapRowToHardware, code)
            );
        } catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }





    @Override
    public Optional<Hardware> save(Hardware hardware) {
        try {
            System.out.println(hardware.toString());
            //hardware.setCode(saveHardwareDetails(hardware));
            String id = saveHardwareDetails(hardware);
            return Optional.of(hardware);
        } catch (DuplicateKeyException e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<Hardware> update(String code, Hardware updatedHardware) {

        int executed = jdbc.update("UPDATE hardware set " +
                        "code = ?, " +
                        "product_name = ?, " +
                        "price = ?, " +
                        "product_type = ?, " +
                        "available_num = ? " +
                        " WHERE code = ?",
                updatedHardware.getCode(),
                updatedHardware.getName(),
                updatedHardware.getPrice(),
                updatedHardware.getType().toString(),
                updatedHardware.getAvailableNum(),
                updatedHardware.getCode()
        );

        if(executed > 0){
            return Optional.of(updatedHardware);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void deleteByCode(final String code) {
        jdbc.update("DELETE FROM hardware WHERE code = ?", code);
    }

    @Override
    public Optional<List<Hardware>> findByLimit(Integer up, Integer down) {
        try{
            return Optional.ofNullable(
                    jdbc.queryForObject(SELECT_ALL + " WHERE available_num BETWEEN ? AND ?", this::mapRowsToListHardware, down, up)
            );
        } catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }


    private Hardware mapRowToHardware(ResultSet rs, int rowNum) throws SQLException {
        return new Hardware(
                rs.getString("product_name"),
                rs.getString("code"),
                rs.getDouble("price"),
                Type.valueOf(rs.getString("product_type")),
                rs.getInt("available_num")
        );
    }

    private List<Hardware> mapRowsToListHardware(ResultSet rs, int rowNum) throws SQLException{
        List<Hardware> list = new ArrayList<>();
        while(rs.next()){
            list.add(new Hardware(
                    rs.getString("product_name"),
                    rs.getString("code"),
                    rs.getDouble("price"),
                    Type.valueOf(rs.getString("product_type")),
                    rs.getInt("available_num")
            )
            );

        }

        return list;
    }


    private String saveHardwareDetails(Hardware hardware){
        Map<String, Object> values = new HashMap<>();

        values.put("code",hardware.getCode());
        values.put("product_name",hardware.getName());
        values.put("product_type",hardware.getType());
        values.put("available_num",hardware.getAvailableNum());
        values.put("price",hardware.getPrice());

       // jdbc.execute("SET IDENTITY_INSERT hardware ON;");
        return String.valueOf(inserter.executeAndReturnKey(values));

    }

}


