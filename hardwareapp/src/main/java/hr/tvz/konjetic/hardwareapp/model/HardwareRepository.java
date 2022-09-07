package hr.tvz.konjetic.hardwareapp.model;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface HardwareRepository {
    Set<Hardware> findAll();

    Optional<Hardware> findByCode(String code);

    Optional<Hardware> save(Hardware hardware);

    Optional<Hardware> update(String code, Hardware updatedHardware);

    void deleteByCode(String code);

    Optional<List<Hardware>> findByLimit(Integer up, Integer down);
}
