package com.cg.services1.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.services1.entity.Services;

public interface ServicesRepository extends JpaRepository<Services, Long>{

	List<Services> findByServicesCategory(String servicesCategory);

	List<Services> findByMerchantId(Long merchantId);

	Services findByServicesId(Long servicesId);
	

}
