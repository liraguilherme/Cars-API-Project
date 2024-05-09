package br.com.kodcar.entity;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "Cars")
//@JsonPropertyOrder({"id", "brand", "model", "color", "year", "mileage", "fuel", "price" })
public class CarEntity extends RepresentationModel<CarEntity>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//@Column(name = "first_name", nullable = false, length = 80) - Caso queira modificar a coluna
	
	
	//@NotEmpty Não deixa o campo nulo, pelo menos um caractere
	//@NotNull Não deixa o campo nulo, string vazia permite
	//usamos o notblank para isso
	@NotBlank(message = "O email é obrigatorio!")
	private String email;

	@NotBlank(message = "O email é obrigatorio!")
	private String password;
	
	@NotBlank(message = "A marca é obrigatorio!")
	private String brand;

	@NotBlank(message = "O modelo é obrigatorio!")
	private String model;
	
	@NotBlank(message = "O ano é obrigatorio!")
	private String year;
	
	@NotBlank(message = "O preço é obrigatorio!")
	private String price;
	
	@NotBlank(message = "A quilometragem é obrigatorio!")
	private String mileage;
	
	@NotBlank(message = "A cor é obrigatorio!")
	private String color;
	
	@NotBlank(message = "O tipo de combustivel é obrigatorio!")
	private String fuel;
	
	public CarEntity() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getMileage() {
		return mileage;
	}

	public void setMileage(String mileage) {
		this.mileage = mileage;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getFuel() {
		return fuel;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	

	
	
	
	
	
	
	
	
	
}
