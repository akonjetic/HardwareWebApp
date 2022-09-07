package hr.tvz.konjetic.hardwareapp.jobs;

import hr.tvz.konjetic.hardwareapp.model.Hardware;
import hr.tvz.konjetic.hardwareapp.model.HardwareRepository;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Set;


public class HardwareJob extends QuartzJobBean {

    private Logger log = LoggerFactory.getLogger(HardwareJob.class);
    private final HardwareRepository hardwareRepository;

    public  HardwareJob(HardwareRepository hardwareRepository) {
        this.hardwareRepository = hardwareRepository;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        final Set<Hardware> hardwares = hardwareRepository.findAll();

        if(!hardwares.isEmpty()){
            log.info("Ovo su trenutno dostupni hardveri");
            log.info("------------------------------");
            hardwares.stream().filter(i -> i.getAvailableNum() > 0).forEach(
                    hardware -> log.info(hardware.getName() + " - " + hardware.getAvailableNum())
            );
            log.info("------------------------------");
        } else{
            log.info("Trenutno nema dostupnih hardvera...");
        }
    }
}
