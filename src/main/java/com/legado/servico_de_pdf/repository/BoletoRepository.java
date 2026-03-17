package com.legado.servico_de_pdf.repository;

import com.legado.servico_de_pdf.model.Boleto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoletoRepository extends JpaRepository<Boleto, Long> {
}
