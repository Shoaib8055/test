package com.cg.services1.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.services1.entity.Services;
import com.cg.services1.exception.ServicesException;
import com.cg.services1.respository.ServicesRepository;

@Service
public class ServicesService {
	@Autowired
	private ServicesRepository repository;
	
	public Services saveServices(Services services) {
		
			if(services.getServicesName().isEmpty() || services.getServicesName().length()==0) {
				throw new ServicesException("601", "Please send proper service name");
			}
			try {
		     return repository.save(services);
		}catch(IllegalArgumentException e) {
			throw new ServicesException("602", "Given service is null" + e.getMessage());
		}catch(Exception e) {
			throw new ServicesException("603", "Something went wrong in service layer" + e.getMessage());
		}
	}
	
	/*public List<Services> saveServices(List<Services> servicess) {
		return repository.saveAll(servicess);
	}*/
	
	public List<Services> getServices(){
		List<Services> servicess = repository.findAll();
		//System.out.println("Getting data from DB : " + servicess);
		return servicess;
	}
	
	public Services findServicesByServicesId(Long servicesId) {
		return repository.findByServicesId(servicesId);
	}
	
	public List<Services> findServicesByCategory(String servicesCategory){
		try {
			return repository.findByServicesCategory(servicesCategory);
		}catch(IllegalArgumentException e) {
			throw new ServicesException("604", "Given service Category is null" + e.getMessage());
		}catch(Exception e) {
			throw new ServicesException("605", "Given service Category does not exist in DB" + e.getMessage());
		}			
		
	}
	
	public List<Services> findServicesByMerchantId(Long merchantId) {
		try {
			return repository.findByMerchantId(merchantId);
		}catch(IllegalArgumentException e) {
			throw new ServicesException("606", "Given merchant ID is null" + e.getMessage());
		}catch(Exception e) {
			throw new ServicesException("607", "Given merchant ID does not exist in DB" + e.getMessage());
		}
		
	}
	
	public String deleteServices(Long servicesId) {
		try {
		repository.deleteById(servicesId);
		return "service removed!!" + servicesId;
		}catch(IllegalArgumentException e) {
			throw new ServicesException("608", "Given service ID is null" + e.getMessage());
		}
	}

	public Services updateServices(Long servicesId, Services services) throws Exception {
		Services existingServices = repository.findByServicesId(servicesId);
		Services service = new Services();
		if(existingServices == null) {
			throw new Exception("Service Id not found");
		}
		else {
		existingServices.setServicesName(services.getServicesName());
		existingServices.setServicesCategory(services.getServicesCategory());
		existingServices.setServicesPrice(services.getServicesPrice());
		existingServices.setMerchantId(services.getMerchantId());
		return repository.save(existingServices);
	}
	}

	public List<String> getAllServicesByCategory() {
		List<Services> services = repository.findAll();
		List<String> category = new ArrayList<>();
		for(int index = 0; index < services.size(); index++) {
			category.add(index, services.get(index).getServicesCategory());
		}
		List<String> updatedCategory = new ArrayList<>();
		for(String servicesCategory : category) {
			if(!updatedCategory.contains(servicesCategory)) {
				updatedCategory.add(servicesCategory);
			}
		}
		return updatedCategory;
	}

	
	
}
