package br.com.kodcar.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.kodcar.entity.CarEntity;
import br.com.kodcar.exceptions.ResourceNotFoundException;
import br.com.kodcar.repository.CarRepository;

@Service
public class CarService {

	private CarRepository carRepository;
	
	private final PasswordEncoder passwordEncoder;


    public CarService(CarRepository carRepository) {
    	this.carRepository = carRepository;
    	
    	//Realizar criptografia da senha
    	this.passwordEncoder = new BCryptPasswordEncoder();  
    }
	
	
	
	public CarEntity create(CarEntity carEntity) {
	String encoder = this.passwordEncoder.encode(carEntity.getPassword()); //encodifica a senha
	carEntity.setPassword(encoder); //criptografa a senha 
	CarEntity carEntityNovo = carRepository.save(carEntity);
	return carEntityNovo;
	}
	
	public List<CarEntity> GetAllCars() {
		List<CarEntity> list = carRepository.findAll();
		return list;
	}
	
	public CarEntity findById(Long id) {
		return carRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
	}
	
	public CarEntity update(CarEntity carEntity) {
		String encoder = this.passwordEncoder.encode(carEntity.getPassword()); //encodifica a senha
		carEntity.setPassword(encoder); //criptografa a senha 
		CarEntity carEntityNovo = carRepository.save(carEntity);
		return carEntityNovo; 
		 
	}
	
	public List<CarEntity> delete (Long id) {
		
		carRepository.deleteById(id);
		
		return carRepository.findAll();
		 
	}



	public Boolean validarSenha(CarEntity carEntity) {
		String senha = carRepository.getById(carEntity.getId()).getPassword();
		Boolean valid = passwordEncoder.matches(carEntity.getPassword(), senha); //comparando as duas senhas 
		return valid;
	}
	
	
	
}
