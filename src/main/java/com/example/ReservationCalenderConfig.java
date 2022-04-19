package com.example;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class ReservationCalenderConfig {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public JdbcBatchItemWriter<ReservationCalender> writer(DataSource dataSource) {
	  return new JdbcBatchItemWriterBuilder<ReservationCalender>()
	    .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
	    .sql("INSERT INTO reservation_calender (date, room_id, reserved_room, reservation_limit) VALUES (:date, :roomId, :reservedRoom, :reservationLimit)")
	    .dataSource(dataSource)
	    .build();
	}
	
	@Bean
	public ReservationCalenderTasklet tasklet() {
		return new ReservationCalenderTasklet();
	}
	
	@Bean
	public Step reservationCalenderStep() {
		return stepBuilderFactory.get("reservationCalenderStep")
				.tasklet(tasklet())
				.build();
	}
	
	@Bean
	public Job reservationCalenderJob(Step reservationCalenderStep) {
		return jobBuilderFactory.get("reservationCalenderJob")
				.incrementer(new RunIdIncrementer())
				.flow(reservationCalenderStep)
				.end()
				.build();
	}
}
