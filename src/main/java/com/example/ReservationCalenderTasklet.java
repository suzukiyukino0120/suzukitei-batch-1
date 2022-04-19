package com.example;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReservationCalenderTasklet implements Tasklet{

	@Autowired
	JdbcBatchItemWriter <ReservationCalender> writer;
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		
		ArrayList<ReservationCalender> list = new ArrayList<ReservationCalender>();
		Calendar cal= Calendar.getInstance();
        cal.add(Calendar.MONTH, 3);
        
        int daysCount=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        Date date = new Date();
        
        for(int i = 1; i <= daysCount; i++) {
        	cal.set(Calendar.DATE, i);
        	date = cal.getTime();
        	
        	ReservationCalender reservationCalender1 = new ReservationCalender();
        	
        	reservationCalender1.setDate(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        	reservationCalender1.setReservedRoom(0);
        	reservationCalender1.setReservationLimit(5);
        	
        	ReservationCalender reservationCalender2 = reservationCalender1.clone(reservationCalender1);        	
        	ReservationCalender reservationCalender3 = reservationCalender1.clone(reservationCalender1);        	
        	ReservationCalender reservationCalender4 = reservationCalender1.clone(reservationCalender1); 
        	
        	reservationCalender1.setRoomId(1);
        	reservationCalender2.setRoomId(2);
        	reservationCalender3.setRoomId(3);
        	reservationCalender4.setRoomId(4);
        	
        	list.add(reservationCalender1);
        	list.add(reservationCalender2);
        	list.add(reservationCalender3);
        	list.add(reservationCalender4);
        	
        }
		
		writer.write(list);
		
		return RepeatStatus.FINISHED;
	}
}
