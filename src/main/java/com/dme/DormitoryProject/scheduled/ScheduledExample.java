package com.dme.DormitoryProject.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledExample {

    @Scheduled(cron = "0 42 09 * * ?")
    public void exapmle(){
        for (int i=1; i<=10; i++){
            System.out.println(i);
        }
    }

    @Scheduled(fixedRate = 30000)
    public void example1(){
        for (int i=11; i<=20; i++){
            System.out.println(i);
        }
    }

}
