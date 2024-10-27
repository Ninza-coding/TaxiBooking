package com.taxi.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taxi.dao.BookingFormCrud;
import com.taxi.model.BookingForm;

@Service
public class BookingFormServiceImpl implements BookingFormService {

	private BookingFormCrud bookingFormCrud;

	@Autowired
	public void setBookingFormCrud(BookingFormCrud bookingFormCrud) {
		this.bookingFormCrud = bookingFormCrud;
	}

	@Override
	public BookingForm saveBookingFormService(BookingForm bookingForm) {

		return bookingFormCrud.save(bookingForm);
	}

	@Override
	public List<BookingForm> readAllBookingsService() {
		return bookingFormCrud.findAll();
		
	}

	@Override
	public void deleteAllBookingsService(int id) {
		bookingFormCrud.deleteById(id);
		
	}

}
