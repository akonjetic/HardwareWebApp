package hr.tvz.konjetic.hardwareapp.model;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HardwareServiceImpl implements HardwareService{

    private final HardwareRepository hardwareRepository;

    public HardwareServiceImpl(HardwareRepository hardwareRepository){
        this.hardwareRepository = hardwareRepository;
    }

    @Override
    public List<HardwareDTO> findAll() {
        return hardwareRepository.findAll().stream().map(this::mapHardwareToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<HardwareDTO> findByCode(String code) {
        //Hardware hardware = hardwareRepository.findByCode(code).orElse(null);
       /* if(hardware.getType().equals(Type.CPU) && hardware.availableNum < 10){
            return mapHardwareToDTONewPrice(hardware);
        }
        else{*/
            return hardwareRepository.findByCode(code).map(this::mapHardwareToDTO);
       // }
    }

    @Override
    public Optional<HardwareDTO> save(HardwareCommand hardwareCommand) {
        return hardwareRepository.save(mapCommandToHardware(hardwareCommand)).map(this::mapHardwareToDTO);
    }

    @Override
    public void deleteByCode(final String code) {
        hardwareRepository.deleteByCode(code);
    }

    @Override
    public Optional<List<HardwareDTO>> findByLimit(Integer up, Integer down) {
        return Optional.ofNullable(hardwareRepository.findByLimit(up, down).get().stream().map(this::mapHardwareToDTO).collect(Collectors.toList()));
    }


    @Override
    public Optional<HardwareDTO> update(final String code, final HardwareCommand updatedHardwareCommand) {
        return hardwareRepository.update(code, mapCommandToHardware(updatedHardwareCommand)).map(this::mapHardwareToDTO);
    }


    private HardwareDTO mapHardwareToDTO(final Hardware hardware){
        return new HardwareDTO(hardware.name, hardware.price, hardware.code);
    }

    private Hardware mapCommandToHardware(final HardwareCommand hardwareCommand) {
        return new Hardware(hardwareCommand.getName(), hardwareCommand.getCode(), hardwareCommand.getPrice(), hardwareCommand.getType(), hardwareCommand.getAvailableNum());
    }

   /* private HardwareDTO mapHardwareToDTONewPrice(final Hardware hardware){
        Double newPrice = hardware.getPrice()+(hardware.getPrice()*0.4);
        return new HardwareDTO(hardware.name, newPrice);
    }*/
}
