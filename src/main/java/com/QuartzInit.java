package com;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

@WebServlet("/test")
public class QuartzInit extends HttpServlet {
	
	private SchedulerFactory schedFact = null;
	private Scheduler scheduler = null;

	@Override
	public void init() throws ServletException {
		try {
			System.out.println("*********************************************************************");
			System.out.println("*********************************************************************");
			System.out.println("*********************************************************************");
			System.out.println("*********************************************************************");
			System.out.println("*********************************************************************");
			System.out.println("*********************************************************************");
			System.out.println("*********************************************************************");
			
			schedFact = new org.quartz.impl.StdSchedulerFactory();
			scheduler = schedFact.getScheduler();
			scheduler.start();
			
			
			JobBuilder jobBuilder = JobBuilder.newJob(MyJob.class);
			JobDetail jobDetail = jobBuilder.build();
			
			
			// Trigger the job to run now, and then every 40 seconds
			Trigger trigger = TriggerBuilder.newTrigger()
			.withIdentity("myTrigger", "group1")
			.startNow()
			.withSchedule(SimpleScheduleBuilder.simpleSchedule()
	                .withRepeatCount(50)
	                .withIntervalInSeconds(30))		
			.build();
			
			scheduler.scheduleJob(jobDetail, trigger);
			
			System.out.println("*********************************************************************");
			System.out.println("*********************************************************************");
			System.out.println("*********************************************************************");
			System.out.println("*********************************************************************");
			System.out.println("*********************************************************************");
			System.out.println("INIT DONE");
			System.out.println("*********************************************************************");
			System.out.println("*********************************************************************");
			System.out.println("*********************************************************************");
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void destroy() {
		try {
			scheduler.shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main (String[] args ) throws ServletException, InterruptedException {
		
		QuartzInit q = new QuartzInit();
		q.init();
		Thread.sleep(50000);
		q.destroy();
	}
}
