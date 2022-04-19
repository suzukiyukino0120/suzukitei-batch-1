package com.example;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ReservationCalenderJobLancher {
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private Job reservationCalenderJob;
	
	private JobParameters jobParameters;
	
	@Scheduled(cron = "0 0 23 L * *", zone = "Asia/Tokyo")
	public void batch() throws Exception {
		jobParameters = reservationCalenderJob.getJobParametersIncrementer().getNext(jobParameters);
		jobLauncher.run(reservationCalenderJob, jobParameters);
	}

}
