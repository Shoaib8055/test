package com.cg.services1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.services1.entity.Services;
import com.cg.services1.exception.ControllerException;
import com.cg.services1.exception.ServicesException;
import com.cg.services1.service.ServicesService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "http://localhost:3000")
@Api("Operations performed on services")
@RestController
@RequestMapping("/services")
public class ServicesController {

	@Autowired
	private ServicesService service;
	
	@ApiOperation(value="To add a service")
	@PostMapping("/addService")
	public ResponseEntity<?> saveServices(@RequestBody Services services) {
		try {
		Services servicesSaved = service.saveServices(services);
		return new ResponseEntity<Services>(servicesSaved, HttpStatus.CREATED);
	}catch(ServicesException e) {
		ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
		return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
	}catch(Exception e) {
		ControllerException ce = new ControllerException("609", "Something went wrong in controller");
		return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
	}
	}
	
	/*@ApiOperation("To add multiple services")
	@PostMapping("/addServices")
	public List<Services> saveServices(@RequestBody List<Services> servicess) {
		return service.saveServices(servicess);
	}*/
	
	@ApiOperation(value="To list all services")
	@GetMapping("/allservices")
	public List<Services> findAllServices(){
		return service.getServices();
	}
	
	@ApiOperation(value = "To list services by service ID")
	@GetMapping("/ById/{servicesId}")
	public Services findServicesByServicesId(@PathVariable("servicesId") Long servicesId) {
		return service.findServicesByServicesId(servicesId);
	}
	
	@ApiOperation(value = "To list services based on category")
	@GetMapping("/{servicesCategory}")
	public ResponseEntity<?> findServicesByCategory(@PathVariable("servicesCategory") String servicesCategory) {
		try {
		List<Services> servicesRetrievedByCategory = service.findServicesByCategory(servicesCategory);
		return new ResponseEntity<List<Services>>(servicesRetrievedByCategory, HttpStatus.OK);
		}catch(ServicesException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}catch(Exception e) {
			ControllerException ce = new ControllerException("610", "Something went wrong in controller");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "To list services based on merchantId")
	@GetMapping("/search/{merchantId}")
	public List<Services> findServicesByMerchantId(@PathVariable("merchantId") Long merchantId){
		return service.findServicesByMerchantId(merchantId);
	}
	
	@ApiOperation(value = "To delete a service based on serviceId")
	@DeleteMapping("/delete/{servicesId}")
	public String deleteServices(@PathVariable("servicesId") Long servicesId) {
		return service.deleteServices(servicesId);
	}
	
	/*@PutMapping("/{servicesId}")
	public Services updateServices(@PathVariable("serviceId") Long serviceId, @RequestBody Services services) {
		return service.updateServices(serviceId, services);
	}*/
	
	@ApiOperation(value = "To update a service based on serviceId")
	@PutMapping("/update")
	public Services updateServices(@RequestBody Services services) {
		return service.updateServices(services);
	}
}
