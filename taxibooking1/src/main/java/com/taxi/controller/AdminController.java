package com.taxi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.taxi.Service.BookingFormService;
import com.taxi.Service.ContactFormService;
import com.taxi.Service.ServiceFromService;
import com.taxi.config.AdminConfigService;
import com.taxi.model.BookingForm;
import com.taxi.model.ContactFrom;
import com.taxi.model.ServiceFromUpload;

@Controller
@RequestMapping("admin")
public class AdminController {

	private ContactFormService contactFormService;
	@Autowired
	public void setContactFormService(ContactFormService contactFormService) {
		this.contactFormService = contactFormService;	
	}
	private BookingFormService bookingFormService;
	@Autowired
	public void setBookingFormService(BookingFormService bookingFormService) {
		this.bookingFormService = bookingFormService;
	}
	private AdminConfigService adminConfigService;
	@Autowired
	public void setAdminConfigService(AdminConfigService adminConfigService) {
		this.adminConfigService = adminConfigService;
	}
	private ServiceFromService serviceFromService;
	@Autowired
	public void setServiceFromService(ServiceFromService serviceFromService) {
		this.serviceFromService = serviceFromService;
	}

	@GetMapping("dashboard")
	public String adminDashboard() {
		return "admin/dashboard";
	}

	@GetMapping("readallcontacts")
	public String readAllContacts(Model model) {
		List<ContactFrom> readAllContacts = contactFormService.readAllContacts();
		model.addAttribute("allcontacts", readAllContacts);
		return "admin/readallcontacts";
	}

	@GetMapping("deletecontact/{id}")
	public String deleteContact(@PathVariable int id, RedirectAttributes redirectAttributes) {
		contactFormService.deleteContactsService(id);
		redirectAttributes.addFlashAttribute("message", "Contact Deleted Successfully");
		return "redirect:/admin/readallcontacts";
	}	
	
	@GetMapping("readAllBookings")
	public String readAllBookings(Model model) {
		List<BookingForm> readAllBookings = bookingFormService.readAllBookingsService();		
		model.addAttribute("allBookings", readAllBookings);
		return "admin/readallbookings";
	}
	
	@GetMapping("deleteBooking/{id}")
	public String deleteBooking(@PathVariable int id, RedirectAttributes redirectAttributes) {
		bookingFormService.deleteAllBookingsService(id);
		redirectAttributes.addFlashAttribute("message", "Booking Deleted Successfully");
		return "redirect:/admin/readAllBookings";
	}
	
	@GetMapping("changeCredentials")
	public String changeCredentialsView() {
		return"admin/changecredentials";
	}
	@PostMapping("changeCredentials")
	public String changeCredentials(@RequestParam("oldusername") String oldusername,
									@RequestParam("oldpassword") String oldpassword,
									@RequestParam("newusername") String newusername,
									@RequestParam("newpassword") String newpassword, 
									RedirectAttributes redirectAttributes) {
		
		String result = adminConfigService.checkAdminCredentials(oldusername, oldpassword);
		if(result.equals("SUCCESS")) {
		result = adminConfigService.updateAdminCredentialsService(newusername, newpassword, oldusername);
		redirectAttributes.addFlashAttribute("message",result);
		}else {
			redirectAttributes.addFlashAttribute("message",result);
		}
		
		return "redirect:/admin/dashboard";
	}
	
	@GetMapping("addServices")
	public String addServiceView(){
		return "admin/addservices";	
	}
	
	@InitBinder
	public void stopBinding(WebDataBinder webDataBinder) {
		webDataBinder.setDisallowedFields("image");
	}
	
	@PostMapping("addServices")
	public String addService(@ModelAttribute ServiceFromUpload serviceFormUpload,
			@RequestParam("image") MultipartFile multipartFile, RedirectAttributes redirectAttributes){
		String originalFilename = multipartFile.getOriginalFilename();
		serviceFormUpload.setImage(originalFilename);
		try {
			ServiceFromUpload service = serviceFromService.addUploadService(serviceFormUpload, multipartFile);
			if(service!=null) {
				redirectAttributes.addFlashAttribute("msg","Service Uploaded");
			}else {
				redirectAttributes.addFlashAttribute("msg","Failed To Upload");
			}
		}catch(Exception e) {
			redirectAttributes.addFlashAttribute("msg","Something Went Wrong");
		}
		return "redirect:/admin/addServices";	
	}
}
