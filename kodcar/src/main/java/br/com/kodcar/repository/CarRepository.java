package br.com.kodcar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.kodcar.entity.CarEntity;

public interface CarRepository extends JpaRepository<CarEntity, Long>{

}
