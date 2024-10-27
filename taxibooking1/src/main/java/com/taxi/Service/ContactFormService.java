package com.taxi.Service;

import java.util.List;

import com.taxi.model.ContactFrom;

public interface ContactFormService {

	public ContactFrom saveContactFromService(ContactFrom contactForm);
		
	public List<ContactFrom> readAllContacts();
	
	public void deleteContactsService(int id);
}
