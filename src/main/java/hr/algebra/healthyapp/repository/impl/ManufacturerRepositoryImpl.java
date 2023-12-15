package hr.algebra.healthyapp.repository.impl;

import hr.algebra.healthyapp.model.Manufacturer;
import hr.algebra.healthyapp.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class ManufacturerRepositoryImpl implements ManufacturerRepository {

    @Value("${jdbc.manufacturer.updateManufacturer}")
    private String updateManufacturerSql;

    @Value("${jdbc.manufacturer.createManufacturer}")
    private String saveManufacturerSql;

    @Value("${jdbc.manufacturer.getManufacturer}")
    private String getManufacturerSql;

    @Value("${jdbc.manufacturer.getALLManufacturer}")
    private String getAllManufacturerSql;

    @Value("${jdbc.manufacturer.deleteManufacturer}")
    private String deleteManufacturerSql;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public ManufacturerRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public void saveManufacturer(Manufacturer manufacturer) {
        if (manufacturer.getId() == null) {
            namedParameterJdbcTemplate.execute(saveManufacturerSql,
                    createMap(manufacturer), PreparedStatement::executeUpdate);
        } else {
            namedParameterJdbcTemplate.execute(updateManufacturerSql,
                    createMap(manufacturer), PreparedStatement::executeUpdate);
        }
    }

    @Override
    public void deleteManufacturer(Long manufacturerId) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", manufacturerId);
        namedParameterJdbcTemplate.execute(deleteManufacturerSql, map, PreparedStatement::executeUpdate);
    }

    @Override
    public Optional<Manufacturer> getManufacturer(Long id) {
        SqlParameterSource param = new MapSqlParameterSource("id", id);
        Manufacturer manufacturer = namedParameterJdbcTemplate.queryForObject(getManufacturerSql,
                param,
                BeanPropertyRowMapper.newInstance(Manufacturer.class));
        return Optional.ofNullable(manufacturer);
    }

    @Override
    public List<Manufacturer> getAllManufacturer() {
        return namedParameterJdbcTemplate.query(getAllManufacturerSql,
                BeanPropertyRowMapper.newInstance(Manufacturer.class));
    }


    private Map<String, ?> createMap(Manufacturer manufacturer) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", manufacturer.getId());
        map.put("name", manufacturer.getName());
        map.put("address", manufacturer.getAddress());
        return map;
    }
}
