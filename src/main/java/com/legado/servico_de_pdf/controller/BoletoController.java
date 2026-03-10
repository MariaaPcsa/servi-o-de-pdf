package com.legado.servico_de_pdf.controller;

import com.legado.servico_de_pdf.dto.BoletoRequest;
import com.legado.servico_de_pdf.model.Boleto;
import com.legado.servico_de_pdf.service.BoletoService;
import com.legado.servico_de_pdf.service.PdfService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/boletos")
public class BoletoController {

    private final BoletoService boletoService;
    private final PdfService pdfService;

    public BoletoController(BoletoService boletoService, PdfService pdfService) {
        this.boletoService = boletoService;
        this.pdfService = pdfService;
    }

    @Operation(
            summary = "Gera boleto em PDF",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "PDF gerado",
                            content = @Content(mediaType = "application/octet-stream")
                    )
            }
    )
    @PostMapping("/gerar")
    public ResponseEntity<byte[]> gerarBoletoPdf(@Valid @RequestBody BoletoRequest request) {
        Boleto boleto = mapToBoleto(request);
        Boleto salvo = boletoService.salvarBoleto(boleto);
        byte[] pdfBytes = boletoService.gerarPdfBoleto(salvo);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "boleto.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }

    private Boleto mapToBoleto(BoletoRequest request) {
        Boleto boleto = new Boleto();
        boleto.setBanco(request.getBanco());
        boleto.setBeneficiario(request.getBeneficiario());
        boleto.setPagador(request.getPagador());
        boleto.setValor(request.getValor());
        boleto.setVencimento(LocalDate.parse(request.getVencimento(), DateTimeFormatter.ISO_LOCAL_DATE));
        boleto.setCpf(request.getCpf());
        boleto.setNumeroPlano(request.getNumeroPlano());
        boleto.setCompetencia(request.getCompetencia());
        // linhaDigitavel and codigoBarras will be generated later
        return boleto;
    }
}