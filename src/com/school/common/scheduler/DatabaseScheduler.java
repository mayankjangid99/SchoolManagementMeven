package com.school.common.scheduler;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class DatabaseScheduler 
{
	/*@Scheduled(fixedDelayString = "${email.scheduler.fixedDelayTask.fixedDelay}")
    public void fixedDelayTask() throws InterruptedException {
        System.out.println(new Date() + " This runs in a fixed delay");
    }

    @Scheduled(fixedRateString = "${email.scheduler.fixedRateTask.fixedRate}")
    public void fixedRateTask() {
        System.out.println(new Date() + " This runs in a fixed rate");
    }

    @Scheduled(fixedRateString = "${email.scheduler.fixedRateWithInitialDelayTask.fixedRate}", initialDelayString = "${email.scheduler.fixedRateWithInitialDelayTask.initialDelay}")
    public void fixedRateWithInitialDelayTask(){
        System.out.println(new Date() + " This runs in a fixed delay with a initial delay");
    }

    @Scheduled(cron = "${email.scheduler.cronTask.cron}")
    public void cronTask(){
        System.out.println(new Date() + " This runs in a cron schedule");
    }*/
}
