package com.aeg.ims.transfer.batch.job;


import com.aeg.ims.transfer.TransferApplication;
import org.junit.Test;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

@SpringApplicationConfiguration(classes = TransferApplication.class)
public class JobTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private Scheduler scheduler;

    @Test
    public void test() throws Exception {

        JobDetail jobDetail = JobBuilder.newJob(SampleJob.class)
                .storeDurably(true)
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .startNow()
                .build();

        scheduler.scheduleJob(jobDetail, trigger);

        Thread.sleep(5000);
    }
}
