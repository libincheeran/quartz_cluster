package com;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;

public class MyJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			String instance = context.getScheduler().getSchedulerInstanceId();
			System.out.println("hello from quartz : "+instance);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

}
