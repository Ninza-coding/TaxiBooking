package com.taxi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.taxi.Service.BookingFormService;
import com.taxi.Service.ContactFormService;
import com.taxi.Service.ServiceFromService;
import com.taxi.model.BookingForm;
import com.taxi.model.ContactFrom;
import com.taxi.model.ServiceFromUpload;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class MyController {
	
	private ContactFormService contactFormService;
	private BookingFormService bookingFormService;
	private ServiceFromService serviceFormService; 
	
	@Autowired
	public void setServiceFormService(ServiceFromService serviceFormService) {
		this.serviceFormService = serviceFormService;
	}

	@Autowired
	public void setBookingFormService(BookingFormService bookingFormService) {
		this.bookingFormService = bookingFormService;
	}

	@Autowired
	public void setContactFormService(ContactFormService contactFormService) {
		this.contactFormService = contactFormService;
	}

	@GetMapping(path = { "/", "home", "welcome", "index" })
	public String welcomeView(HttpServletRequest req, Model m) {
		String requestURI = req.getRequestURI();
		m.addAttribute("mycurrentpage", requestURI);
		m.addAttribute("bookingForm", new BookingForm());
		System.out.println("Hello This Is Taxi App");
		return "index";
	}
	
	@PostMapping("bookingform")
	public String bookingForm(@Valid @ModelAttribute BookingForm bookingForm, BindingResult bindingResult, Model m,
			 RedirectAttributes redirectAttributes){
		if(bindingResult.hasErrors()) {
			m.addAttribute("bindingResult", bindingResult);
			return "index";
		}else if(bookingForm.getAdult()+bookingForm.getChildren()>4){
			m.addAttribute("message","Total no of adult and children cannot exeed 4");
			return "index";
		}
		BookingForm saveBookingFormService = bookingFormService.saveBookingFormService(bookingForm);
		if(saveBookingFormService!=null) {
			redirectAttributes.addFlashAttribute("message", "Message sent Sucessfully");
		}else {
			redirectAttributes.addFlashAttribute("message", " Unbale to Message");
		}
		return "redirect:/index";
	}
	
	@GetMapping(path ="about")
	public String aboutView(HttpServletRequest req, Model m) {
		String requestURI = req.getRequestURI();
		m.addAttribute("mycurrentpage", requestURI);
		return "about";
	}

	@GetMapping(path = "cars")
	public String carsView(HttpServletRequest req, Model m) {
		String requestURI = req.getRequestURI();
		m.addAttribute("mycurrentpage", requestURI);
		return "cars";
	}

	@GetMapping(path = "contacts")
	public String contactsView(HttpServletRequest req, Model m) {
		String requestURI = req.getRequestURI();
		m.addAttribute("mycurrentpage", requestURI);
		m.addAttribute("contactFrom", new ContactFrom());
		return "contacts";
	}

	@PostMapping("contactfrom")
	public String contactForm(@Valid @ModelAttribute ContactFrom contactFrom, 
			BindingResult bindingResult, Model m, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()) {
			m.addAttribute("bindingResult", bindingResult);
			return "contacts";
		}
		ContactFrom saveContactFromService = contactFormService.saveContactFromService(contactFrom);
		if(saveContactFromService!=null) {
			redirectAttributes.addFlashAttribute("message", "Saved Successfully");
		}else {
			redirectAttributes.addFlashAttribute("message", "Something Went Wrong");
		}
		
		System.out.println(contactFrom);
		return "redirect:/contacts";
	}

	@GetMapping(path = "services")
	public String servicesView(HttpServletRequest req, Model m) {
		String requestURI = req.getRequestURI();
		m.addAttribute("mycurrentpage", requestURI);
		
		List<ServiceFromUpload> allService = serviceFormService.readAllService();
		m.addAttribute("allService", allService);
		return "services";
	}
	
	@GetMapping("/login")
	public String adminloginView(HttpServletRequest httpServletRequest, Model model) {
		ServletContext servletContext = httpServletRequest.getServletContext();
		Object attribute = servletContext.getAttribute("logout");
		if(attribute instanceof Boolean) {
			model.addAttribute("logout", attribute);
			servletContext.removeAttribute("logout");
		}
		return "adminlogin";
	}
}