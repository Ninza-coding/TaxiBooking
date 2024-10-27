package com.taxi.Service;

import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taxi.dao.ServiceFormCrud;
import com.taxi.model.ServiceFromUpload;

import jakarta.transaction.Transactional;

@Service
public class ServiceFromServiceImpl implements ServiceFromService{

	private ServiceFormCrud serviceFormCrud;
	@Autowired
	public void setServiceFormCrud(ServiceFormCrud serviceFormCrud) {
		this.serviceFormCrud = serviceFormCrud;
	}
	
	@SuppressWarnings("resource")
	@Transactional(rollbackOn = Exception.class)
	@Override
	public ServiceFromUpload addUploadService(ServiceFromUpload serviceFromUpload, MultipartFile multipartFile)
			throws Exception {
		ServiceFromUpload save=null;
		try {
		save = serviceFormCrud.save(serviceFromUpload);
		if(save!=null) {
			String path="D:\\java\\TaxiBookingApp\\taxibooking1\\src\\main\\resources\\static\\myserviceimg\\"+multipartFile.getOriginalFilename();
			byte[] bytes = multipartFile.getBytes();
			FileOutputStream fos= new FileOutputStream(path);
			fos.write(bytes);
		
		}
		}catch(Exception e){
			save=null;
			
			throw e;
		}
		return save;
	}

	@Override
	public List<ServiceFromUpload> readAllService() {
		
		return serviceFormCrud.findAll();
	}
}
