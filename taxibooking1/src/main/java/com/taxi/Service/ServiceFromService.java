package com.taxi.Service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.taxi.model.ServiceFromUpload;

public interface ServiceFromService {

	public ServiceFromUpload addUploadService(ServiceFromUpload serviceFromUpload, MultipartFile multipartFile) throws Exception;
	public List<ServiceFromUpload> readAllService();
}
