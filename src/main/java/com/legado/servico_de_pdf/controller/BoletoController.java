package com.legado.servico_de_pdf.controller;

import com.legado.servico_de_pdf.model.Boleto;
import com.legado.servico_de_pdf.service.PdfService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boletos")
public class BoletoController {

    private final PdfService pdfService;

    public BoletoController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @Operation(
            summary = "Gera boleto em PDF",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "PDF gerado",
                            content = @Content(mediaType = "application/octet-stream") // aqui
                    )
            }
    )
    @PostMapping("/gerar")
    public ResponseEntity<byte[]> gerarBoletoPdf(@RequestBody Boleto boleto) {
        byte[] pdfBytes = pdfService.gerarPdf(boleto);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "boleto.pdf"); // força download

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }
}