package br.com.kodcar.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.kodcar.entity.CarEntity;
import br.com.kodcar.repository.CarRepository;
import br.com.kodcar.service.CarService;
import br.com.kodcar.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/car")
@Tag(name = "Cars", description = "Endpoints for Managing Cars")
public class CarController {
	
	@Autowired
     CarService carService;
	
	@Autowired
	CarRepository carRepository;
	
	@PostMapping(consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
			produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	
	//Configurando Swagger POST
	 @Operation(summary = "Adds a new Car", description = "Adds a new Car",
	 tags = {"Car"}, 
	 responses = {
			 @ApiResponse(description = "Success", responseCode = "200", 
					 content = @Content(schema = @Schema(implementation = CarEntity.class))),
			 
		     @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			 @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			 @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)

	 })
	
        public ResponseEntity<CarEntity> create(@Valid @RequestBody CarEntity carEntity) {
		 return ResponseEntity.status(201).body(carService.create(carEntity));
		 
		
		 
	} 
	
	@GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	
	//Configurando Swagger GetAll
	 @Operation(summary = "Finds all Cars", description = "Finds All Cars",
	 tags = {"Cars"}, 
	 responses = {
			 @ApiResponse(description = "Success", responseCode = "200", 
					 content = { @Content(
							 mediaType = "application/json",
							 array = @ArraySchema(schema = @Schema(implementation = CarEntity.class)))}),
			 
			 @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			 @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			 @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			 @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)

	 })
	
	public ResponseEntity <List<CarEntity>> GetAllCars(){
		
	
	 /*allCars.stream().map(carRepository -> 
	carRepository.add(linkTo(methodOn(CarController.class).findById(carRepository.getId())).withSelfRel())).toList();
	*/
	 return ResponseEntity.status(200).body(carService.GetAllCars());
	}
	
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	
	//Configurando Swagger FINDBYID
	 @Operation(summary = "Finds a Car", description = "Finds a Car",
	 tags = {"Car"}, 
	 responses = {
			 @ApiResponse(description = "Success", responseCode = "200", 
					 content = @Content(schema = @Schema(implementation = CarEntity.class))),
			 
			 @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
		     @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			 @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			 @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			 @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)

	 })
	
	public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
		
		CarEntity carEntityNovo = carService.findById(id)
				.add(linkTo(methodOn(CarController.class).GetAllCars()).withRel("Return to all clients"));
		
		 return ResponseEntity.status(200).body(carEntityNovo);
	}
	
	@PutMapping(consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
			produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	
	//Configurando Swagger UPDATE
	  @Operation(summary = "Updates a Car", description = "Updates a Car",
	  tags = {"Car"}, 
	  responses = {
	 		 @ApiResponse(description = "Updated", responseCode = "200", 
	 				 content = @Content(schema = @Schema(implementation = CarEntity.class))),
	 		 
	 	     @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
	 		 @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
	 		 @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
	 		 @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	  })
	
	public ResponseEntity<CarEntity> update(@Valid @RequestBody CarEntity carEntity) {
		 return ResponseEntity.status(200).body(carService.update(carEntity));
	}
	
	@DeleteMapping("{id}") 
	//Configurando Swagger DELETE
	  @Operation(summary = "Deletes a Car", description = "Deletes a Car",
	  tags = {"Car"}, 
	  responses = {
	 		 @ApiResponse(description = "No content", responseCode = "204", content = @Content),
	 		 @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
	 		 @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
	 		 @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
	 		 @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)

	  })
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		 carService.delete(id);
		 return ResponseEntity.status(204).build();
		 }
	
	//Criando endpoint de login para teste da senha 
	@PostMapping("/login")
	public ResponseEntity<CarEntity> validarSenha(@Valid @RequestBody CarEntity carEntity){
		Boolean valid = carService.validarSenha(carEntity);
		if (!valid) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}  return ResponseEntity.status(200).build();
		} 
	
	//Passar mensagem mais facil de ler na exception
	@ResponseStatus(HttpStatus.BAD_REQUEST) // intercepta quando ocorrer uma badRequest capturamos a exception abaixo
	@ExceptionHandler(MethodArgumentNotValidException.class) //Gera uma excessão para a interceptação
	public Map<String, String> handleValidationException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>(); //Cria uma lista
		
        //Pega os elementos e os campos e insere na lista
        ex.getBindingResult().getAllErrors().forEach((error) -> {
        	String fieldName = ((FieldError) error).getField();
        	String errorMessage = error.getDefaultMessage();
        	errors.put(fieldName, errorMessage);
        	
        });
        
	    return errors;
			
	}




}
	
	
	


