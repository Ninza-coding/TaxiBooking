package com.taxi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taxi.model.ContactFrom;

@Repository
public interface ContactFromCrud extends JpaRepository<ContactFrom, Integer> {

	@Override
	public <S extends ContactFrom> S save(S entity);
	
	@Override
	public List<ContactFrom> findAll();
	
	@Override
	public void deleteById(Integer id);
}

