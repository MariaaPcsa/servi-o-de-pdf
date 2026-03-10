package com.legado.servico_de_pdf.service;

import com.legado.servico_de_pdf.model.Boleto;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfService {

    // Gera PDF de um boleto individual
    public byte[] gerarPdf(Boleto b) {
        try {
            String html = gerarHtmlBoleto(b);
            return converterHtmlParaPdf(html);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar PDF do boleto", e);
        }
    }

    // Converte HTML para PDF
    private byte[] converterHtmlParaPdf(String html) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withHtmlContent(html, null);
            builder.toStream(outputStream);
            builder.run();
            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter HTML para PDF", e);
        }
    }

    // Gera HTML básico do boleto
    private String gerarHtmlBoleto(Boleto b) {
        return "<!DOCTYPE html><html lang='pt-BR'><head><meta charset='UTF-8'/>"
                + "<title>Boleto Bancário</title>"
                + "<style>"
                + "body { font-family: Arial; font-size:12px; }"
                + "table { width:100%; border-collapse: collapse; }"
                + "td { border:1px solid #000; padding:6px; vertical-align:top; }"
                + ".titulo { font-size:18px; font-weight:bold; text-align:center; }"
                + ".linha-digitavel { font-weight:bold; text-align:center; letter-spacing:2px; padding:8px; }"
                + ".valor-grande { font-size:16px; font-weight:bold; }"
                + ".codigo-barras { font-family:monospace; font-size:28px; text-align:center; margin-top:20px; }"
                + ".recibo { font-size:11px; margin-top:30px; }"
                + ".page-break { page-break-after: always; }"
                + "</style></head><body>"
                + "<table>"
                + "<tr><td width='20%'><strong>Banco:</strong> " + b.getBanco() + "</td>"
                + "<td width='50%' class='titulo'>BOLETO BANCÁRIO</td>"
                + "<td width='30%'>Vencimento:<br/><strong>" + b.getVencimento() + "</strong></td></tr>"
                + "<tr><td colspan='3' class='linha-digitavel'>" + b.getLinhaDigitavel() + "</td></tr>"
                + "<tr><td colspan='3'><strong>Beneficiário:</strong> " + b.getBeneficiario() + "</td></tr>"
                + "<tr><td colspan='3'><strong>Pagador:</strong> " + b.getPagador() + "</td></tr>"
                + "<tr><td colspan='3'><strong>CPF:</strong> " + b.getCpf() + "</td></tr>"
                + "<tr><td colspan='2'><strong>Competência:</strong> " + b.getCompetencia() + "</td>"
                + "<td align='right'><strong>Valor Documento:</strong> <span class='valor-grande'>R$ " + b.getValor() + "</span></td></tr>"
                + "</table>"
                + "<div class='codigo-barras'>" + b.getCodigoBarras() + "</div>"
                + "<hr style='margin-top:30px; border-top:1px dashed #000;'/>"
                + "<div class='recibo'>"
                + "<strong>Recibo do Pagador</strong><br/>"
                + "Pagador: " + b.getPagador() + "<br/>"
                + "Valor: R$ " + b.getValor()
                + "</div>"
                + "</body></html>";
    }
}