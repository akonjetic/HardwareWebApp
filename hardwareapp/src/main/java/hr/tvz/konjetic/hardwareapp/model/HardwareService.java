package hr.tvz.konjetic.hardwareapp.model;

import java.util.List;
import java.util.Optional;

public interface HardwareService {

    List<HardwareDTO> findAll();

    Optional<HardwareDTO> findByCode(String code);

    Optional<HardwareDTO> save(HardwareCommand hardwareCommand);

    Optional<HardwareDTO> update(String code, HardwareCommand updatedHardwareCommand);

    void deleteByCode(String code);

    Optional<List<HardwareDTO>> findByLimit(Integer up, Integer down);
}
