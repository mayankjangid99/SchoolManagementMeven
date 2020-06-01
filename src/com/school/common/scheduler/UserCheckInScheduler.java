package com.school.common.scheduler;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.school.common.facade.FacadeServicesManager;

@Service
@EnableScheduling
public class UserCheckInScheduler 
{
	@Autowired
	private FacadeServicesManager facadeServicesManager;
	
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
    }*/

    @Scheduled(cron = "${checkOut.scheduler.cronTask.cron}")
    public void checkOut(){
        System.out.println(new Date() + " This runs in a cron schedule");
        //facadeServicesManager.getUserCheckInServices().checkOut();
    }
}
