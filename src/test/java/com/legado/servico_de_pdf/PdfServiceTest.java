package com.legado.servico_de_pdf;

import com.legado.servico_de_pdf.model.Boleto;
import com.legado.servico_de_pdf.service.PdfService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PdfServiceTest {

    private PdfService pdfService;

    @BeforeEach
    void setUp() {
        // Inicializa o serviço de PDF
        pdfService = new PdfService();
    }

    @Test
    void gerarBoletoPdfTest() throws Exception {
        // Cria um boleto de exemplo
        Boleto boleto = criarBoletoExemplo();

        // Gera PDF do boleto
        byte[] pdfBytes = pdfService.gerarPdf(boleto);

        // Verifica se o PDF não é nulo e tem conteúdo
        assertNotNull(pdfBytes);
        assert(pdfBytes.length > 0);

        // Salva o PDF no disco para validação manual
        try (FileOutputStream fos = new FileOutputStream("boleto_individual.pdf")) {
            fos.write(pdfBytes);
        }

        System.out.println("PDF individual gerado com sucesso: boleto_individual.pdf");
    }

    // ==============================
    // Cria um boleto de exemplo
    // ==============================
    private Boleto criarBoletoExemplo() {
        Boleto boleto = new Boleto();
        boleto.setBanco("001 - Banco do Brasil");
        boleto.setBeneficiario("APRYASE Serviços Médicos LTDA");
        boleto.setPagador("Maria Silva Santos");
        boleto.setCpf("123.456.789-09");
        boleto.setNumeroPlano("PLN-45879");
        boleto.setCompetencia("02/2026");
        boleto.setVencimento(LocalDate.of(2026, 2, 28));
        boleto.setValor(BigDecimal.valueOf(289.90));
        boleto.setLinhaDigitavel("00190.00009 01234.567890 12345.678901 1 98760000028990");
        boleto.setCodigoBarras("00191987600000289900000012345678901234567890");
        return boleto;
    }
}