package com.taxi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taxi.model.ServiceFromUpload;

@Repository
public interface ServiceFormCrud extends JpaRepository<ServiceFromUpload, Integer>  {

	@Override
	public <S extends ServiceFromUpload> S save(S entity);
}