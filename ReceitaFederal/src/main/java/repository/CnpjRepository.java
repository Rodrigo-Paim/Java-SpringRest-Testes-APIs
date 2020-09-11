package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.Cnpj;

public interface CnpjRepository extends JpaRepository <Cnpj ,String>{
	
	Cnpj findByCnpj(String cnpj);

}
