package com.taxi.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taxi.dao.ContactFromCrud;
import com.taxi.model.ContactFrom;

@Service
public class ContactFromServiceImpl implements ContactFormService {

	private ContactFromCrud contactFromCrud;

	@Autowired
	public void setContactFromCrud(ContactFromCrud contactFromCrud) {
		this.contactFromCrud = contactFromCrud;
	}

	@Override
	public ContactFrom saveContactFromService(ContactFrom contactFrom) {
		return contactFromCrud.save(contactFrom);
	}

	@Override
	public List<ContactFrom> readAllContacts() {
		
		return contactFromCrud.findAll();
	}

	@Override
	public void deleteContactsService(int id) {
		contactFromCrud.deleteById(id);
	}

}
