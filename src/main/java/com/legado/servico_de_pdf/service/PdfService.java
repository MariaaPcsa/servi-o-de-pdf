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
        String barcodeHtml = b.getBarcodeBase64() != null ?
            "<img src='" + b.getBarcodeBase64() + "' alt='Código de Barras' style='width:300px; height:50px;' />" :
            "<div style='font-family:monospace; font-size:28px; text-align:center;'>" + b.getCodigoBarras() + "</div>";

        return "<!DOCTYPE html><html lang='pt-BR'><head><meta charset='UTF-8'/>"
                + "<title>Boleto Bancário</title>"
                + "<style>"
                + "body { font-family: Arial, sans-serif; font-size: 12px; margin: 20px; }"
                + "table { width:100%; border-collapse: collapse; margin-bottom: 20px; }"
                + "td { border: 1px solid #000; padding: 8px; vertical-align: top; }"
                + ".titulo { font-size: 18px; font-weight: bold; text-align: center; background-color: #f0f0f0; }"
                + ".linha-digitavel { font-weight: bold; text-align: center; letter-spacing: 2px; padding: 10px; background-color: #ffffcc; }"
                + ".valor-grande { font-size: 16px; font-weight: bold; text-align: right; }"
                + ".barcode { text-align: center; margin: 20px 0; }"
                + ".recibo { font-size: 11px; margin-top: 30px; border-top: 1px dashed #000; padding-top: 20px; }"
                + ".header { background-color: #e0e0e0; }"
                + "</style></head><body>"
                + "<table>"
                + "<tr class='header'><td width='20%'><strong>Banco:</strong><br/>" + b.getBanco() + "</td>"
                + "<td width='50%' class='titulo'>BOLETO BANCÁRIO</td>"
                + "<td width='30%'><strong>Vencimento:</strong><br/>" + b.getVencimento() + "</td></tr>"
                + "<tr><td colspan='3' class='linha-digitavel'>" + b.getLinhaDigitavel() + "</td></tr>"
                + "<tr><td colspan='3'><strong>Beneficiário:</strong> " + b.getBeneficiario() + "</td></tr>"
                + "<tr><td colspan='3'><strong>Pagador:</strong> " + b.getPagador() + "</td></tr>"
                + "<tr><td colspan='3'><strong>CPF:</strong> " + b.getCpf() + "</td></tr>"
                + "<tr><td width='50%'><strong>Competência:</strong> " + b.getCompetencia() + "</td>"
                + "<td width='50%' colspan='2' class='valor-grande'>Valor Documento: R$ " + String.format("%.2f", b.getValor()) + "</td></tr>"
                + "</table>"
                + "<div class='barcode'>" + barcodeHtml + "</div>"
                + "<div class='recibo'>"
                + "<strong>Recibo do Pagador</strong><br/>"
                + "Pagador: " + b.getPagador() + "<br/>"
                + "Valor: R$ " + String.format("%.2f", b.getValor())
                + "</div>"
                + "</body></html>";
    }
}