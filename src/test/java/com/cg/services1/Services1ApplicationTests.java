package com.cg.services1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.services1.entity.Services;
import com.cg.services1.respository.ServicesRepository;
import com.cg.services1.service.ServicesService;

@RunWith(SpringRunner.class)
@SpringBootTest
class Services1ApplicationTests {

	@Autowired
	private ServicesService service;
	
	@MockBean
	private ServicesRepository repository;
	
	@Test
	public void getServicesTest() {
		when(repository.findAll()).thenReturn(Stream.of(new Services(2l, "BrandName", "Shirt", 1200.0, 1212l), new Services(3l, "BrandName2", "Shoe", 1110.0, 11l)).collect(Collectors.toList()));
		assertEquals(2, service.getServices().size());
	}
	
	@Test
	public void getUsersbyCategoryTest() {
		String servicesCategory = "Shirt";
		when(repository.findByServicesCategory(servicesCategory)).thenReturn(Stream.of(new Services(2l, "BrandName", "Shirt", 1200.0, 1212l)).collect(Collectors.toList()));
		assertEquals(1, service.findServicesByCategory(servicesCategory).size());
	}
	
	@Test
	public void getUsersbyMerchantIdTest() {
		Long MerchantId = 1111l;
		when(repository.findByMerchantId(MerchantId)).thenReturn(Stream.of(new Services(3l, "BrandName2", "Shoe", 1110.0, 11l)).collect(Collectors.toList()));
		assertEquals(1, service.findServicesByMerchantId(MerchantId).size());
	}
	
	@Test
	public void saveServicesTest() {
		Services services = new Services(5l, "Adidas", "Trousers", 1234.0, 3432l);
		when(repository.save(services)).thenReturn(services);
		assertEquals(services, service.saveServices(services));
	}
	
	@Test
	public void deleteServicesTest() {
		Long servicesId = 119l;
		Services services = new Services(5l, "Adidas", "Trousers", 1234.0, 3432l);
		service.deleteServices(servicesId);
		verify(repository, times(1)).deleteById(servicesId);
	}
	
	/*@Test
	public void updateServicesTest() {
	Services services = new Services(5l, "Adidas", "Trousers", 1234.0, 3432l);
	when(repository.save(services)).thenReturn(services);
	assertEquals(services, service.updateServices(services));
	}*/
	
	/*@Test
	public void updateServicesTest() {
		Services services = new Services();
		repository.findById(services.getServicesId()).get();
		services.setServicesCategory("Shoe");
		Services servicesUpdated = repository.save(services);
		Assertions.assertThat(servicesUpdated.getServicesCategory()).isEqualTo("Shoe");
	}*/
	
	/*@Test
	public void updateServicesTest() {
		String servicesCategory = "Shoe";
		Services services = new Services(5l, "Adidas", "Trousers", 1234.0, 3432l);
		services.setServicesId(5l);
		repository.save(services);
		Services updatedServices = repository.findByServicesId(5l);
		assertThat(updatedServices.getServicesCategory()).isEqualTo(servicesCategory);
	}*/
	
	/*@Test
	public void updateServicesTest() {
		String servicesCategory = "Shirt";
		Services s = repository.findByServicesCategory("Trousers").get(0);
		s.setServicesCategory("Shoe");
		repository.save(s);
		assertNotEquals("Trousers", repository.findByServicesCategory("Trousers").get(0).getServicesCategory());
	}*/
	
	/*@Test
	public void updateServicesTest() {
		Services services = new Services(5l, "Adidas", "Trousers", 1234.0, 3432l);
		repository.findByServicesId(5l);
		services.setServicesName("Nike");
		repository.save(services);
		
		Services updatedServices = repository.save(services);
		
		assertThat((updatedServices).getServicesName()).isEqualTo("Nike");
	}*/
	

	void contextLoads() {
	}

}
