package com.legado.servico_de_pdf.service;

import com.legado.servico_de_pdf.model.Boleto;
import com.legado.servico_de_pdf.repository.BoletoRepository;
import org.springframework.stereotype.Service;

@Service
public class BoletoService {

    private final LinhaDigitavelService linhaDigitavelService;
    private final PdfService pdfService;
    private final BoletoRepository boletoRepository;

    public BoletoService(LinhaDigitavelService linhaDigitavelService, PdfService pdfService, BoletoRepository boletoRepository) {
        this.linhaDigitavelService = linhaDigitavelService;
        this.pdfService = pdfService;
        this.boletoRepository = boletoRepository;
    }

    public Boleto prepararBoleto(Boleto boleto) {
        // Generate linha digitavel and codigo barras
        boleto.setLinhaDigitavel(linhaDigitavelService.gerarLinhaDigitavel(
            boleto.getBanco(), boleto.getValor(), boleto.getVencimento()));
        boleto.setCodigoBarras(linhaDigitavelService.gerarCodigoBarras(
            boleto.getBanco(), boleto.getValor(), boleto.getVencimento()));
        try {
            boleto.setBarcodeBase64(linhaDigitavelService.gerarBarcodeBase64(boleto.getCodigoBarras()));
        } catch (Exception e) {
            // Fallback to text
            boleto.setBarcodeBase64(null);
        }
        return boleto;
    }

    public Boleto salvarBoleto(Boleto boleto) {
        Boleto preparado = prepararBoleto(boleto);
        return boletoRepository.save(preparado);
    }

    public byte[] gerarPdfBoleto(Boleto boleto) {
        Boleto boletoPreparado = prepararBoleto(boleto);
        return pdfService.gerarPdf(boletoPreparado);
    }
}
