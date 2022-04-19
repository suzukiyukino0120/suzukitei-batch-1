package com.example;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;


public class ReservationCalender {
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	
	private Integer roomId;
	
	private Integer reservedRoom;
	
	private Integer reservationLimit;
	

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}


	public Integer getReservedRoom() {
		return reservedRoom;
	}

	public void setReservedRoom(Integer reservedRoom) {
		this.reservedRoom = reservedRoom;
	}


	public Integer getReservationLimit() {
		return reservationLimit;
	}

	public void setReservationLimit(Integer reservationLimit) {
		this.reservationLimit = reservationLimit;
	}
	
	public ReservationCalender clone(ReservationCalender reservationCalender) {
		ReservationCalender newReservationCalender = new ReservationCalender();
		newReservationCalender.setDate(reservationCalender.getDate());
		newReservationCalender.setReservationLimit(reservationCalender.getReservationLimit());
		newReservationCalender.setReservedRoom(reservationCalender.getReservedRoom());
		return newReservationCalender;
	}
	
	@Override
	public String toString() {
		return "ReservationCalender [date=" + date + ", roomId=" + roomId + ", reservedRoom=" + reservedRoom
				+ ", reservationLimit=" + reservationLimit + "]";
	}

}
