package hr.tvz.konjetic.hardwareapp.jobs;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import static org.quartz.CronScheduleBuilder.cronSchedule;

@Configuration
public class SchedulerConfig {

    private static final String HARDWARE_JOB_IDENTITY = "hardwareJob";
    private static final String HARDWARE_TRIGGER = "hardwareTrigger";

    @Bean
    public JobDetail hardwareJobDetail(){
        return JobBuilder.newJob(HardwareJob.class).withIdentity(HARDWARE_JOB_IDENTITY)
                .storeDurably().build();
    }

   /* @Bean
    public SimpleTrigger hardwareTrigger(){
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(10).repeatForever();



        return TriggerBuilder.newTrigger().forJob(hardwareJobDetail())
                .withIdentity(HARDWARE_TRIGGER).withSchedule(scheduleBuilder).build();
    }*/

    @Scheduled(cron = "0 0 ? * SAT,SUN")
    @Bean
    public Trigger hardwareJobNewTrigger() {
        return TriggerBuilder.newTrigger().forJob(hardwareJobDetail())
                .withIdentity("hardwareTrigger").build();
    }





}
