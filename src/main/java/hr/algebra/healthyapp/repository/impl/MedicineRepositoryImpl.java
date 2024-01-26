package hr.algebra.healthyapp.repository.impl;

import hr.algebra.healthyapp.exception.EntityDoesNotExistsException;
import hr.algebra.healthyapp.model.Medicine;
import hr.algebra.healthyapp.repository.ManufacturerRepository;
import hr.algebra.healthyapp.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MedicineRepositoryImpl implements MedicineRepository {

    @Value("${jdbc.medicine.updateMedicine}")
    private String updateMedicineSql;

    @Value("${jdbc.medicine.createMedicine}")
    private String saveMedicineSql;

    @Value("${jdbc.medicine.getMedicine}")
    private String getMedicineSql;

    @Value("${jdbc.medicine.getALLMedicine}")
    private String getAllMedicineSql;

    @Value("${jdbc.medicine.deleteMedicine}")
    private String deleteMedicineSql;

    @Value("${jdbc.medicine.getMedicineByManufacturer}")
    private String getMedicineByManufacturerSql;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private ManufacturerRepository manufacturerRepository;

    @Autowired
    public MedicineRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate, ManufacturerRepository manufacturerRepository) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public Long saveMedicine(Medicine medicine) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameters = new MapSqlParameterSource( createMap(medicine));
        if (medicine.getId() == null) {
            namedParameterJdbcTemplate.update(saveMedicineSql, parameters, keyHolder, new String[]{"id"});
            return keyHolder.getKey().longValue();
        } else {
            namedParameterJdbcTemplate.update(updateMedicineSql, parameters, keyHolder, new String[]{"id"});
            return keyHolder.getKey().longValue();
        }
    }

    @Override
    public void batchUpdateMedicine(List<Medicine> medicines) {
        List<MapSqlParameterSource> parameters = medicines.stream().map(medicine -> new MapSqlParameterSource(createMap(medicine))).collect(Collectors.toList());
        namedParameterJdbcTemplate.batchUpdate(updateMedicineSql, parameters.toArray(new SqlParameterSource[parameters.size()]));
    }

    @Override
    public void deleteMedicine(Long medicineId) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", medicineId);

        getMedicine(medicineId).ifPresentOrElse(medicine -> {
            List<Medicine> medicineByManufacturer = getMedicineByManufacturer(medicine.getManufacturerId());
            if (medicineByManufacturer.size() == 1) {
                manufacturerRepository.deleteManufacturer(medicine.getId());
            }
            namedParameterJdbcTemplate.execute(deleteMedicineSql, map, PreparedStatement::executeUpdate);
        }, () -> {
            throw new EntityDoesNotExistsException("Medicine with id %s does not exists", medicineId.toString());
        });
    }

    @Override
    public Optional<Medicine> getMedicine(Long id) {
        SqlParameterSource param = new MapSqlParameterSource("id", id);
        Medicine medicine = namedParameterJdbcTemplate.queryForObject(getMedicineSql, param, BeanPropertyRowMapper.newInstance(Medicine.class));
        return Optional.ofNullable(medicine);
    }

    @Override
    public List<Medicine> getAllMedicine() {
        return namedParameterJdbcTemplate.query(getAllMedicineSql, BeanPropertyRowMapper.newInstance(Medicine.class));
    }

    @Override
    public List<Medicine> getMedicineByManufacturer(Long manufacturerId) {
        SqlParameterSource param = new MapSqlParameterSource("manufacturerId", manufacturerId);
        return namedParameterJdbcTemplate.query(getMedicineByManufacturerSql,param, BeanPropertyRowMapper.newInstance(Medicine.class));
    }

    private Map<String, ?> createMap(Medicine medicine) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", medicine.getId());
        map.put("name", medicine.getName());
        map.put("description", medicine.getDescription());
        map.put("manufacturerId", medicine.getManufacturerId());
        return map;
    }
}
