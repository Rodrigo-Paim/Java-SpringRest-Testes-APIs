package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.Cpf;

public interface CpfRepository extends JpaRepository<Cpf, String> {
	
	Cpf findByCpf(String cpf);

}
