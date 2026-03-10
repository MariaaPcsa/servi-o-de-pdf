package com.legado.servico_de_pdf.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.oned.Code128Writer;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

@Service
public class LinhaDigitavelService {

    public String gerarLinhaDigitavel(String banco, BigDecimal valor, LocalDate vencimento) {
        // Simplified linha digitavel generation
        // Real implementation would calculate check digits
        String bancoCode = banco.split(" - ")[0]; // Extract bank code
        String valorStr = valor.movePointRight(2).toString();
        String vencimentoStr = vencimento.format(DateTimeFormatter.ofPattern("yyMMdd"));

        return bancoCode + "9" + vencimentoStr + "00000" + String.format("%010d", valor.longValue()) + "00000000000000";
    }

    public String gerarCodigoBarras(String banco, BigDecimal valor, LocalDate vencimento) {
        // Simplified barcode generation
        String bancoCode = banco.split(" - ")[0];
        String valorStr = String.format("%010d", valor.movePointRight(2).longValue());
        String vencimentoStr = vencimento.format(DateTimeFormatter.ofPattern("yyMMdd"));

        return bancoCode + "9" + vencimentoStr + "00000" + valorStr + "00000000000000000000000000";
    }

    public String gerarBarcodeBase64(String codigoBarras) throws WriterException, IOException {
        Code128Writer barcodeWriter = new Code128Writer();
        var bitMatrix = barcodeWriter.encode(codigoBarras, BarcodeFormat.CODE_128, 300, 50);
        BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        javax.imageio.ImageIO.write(bufferedImage, "png", baos);
        byte[] imageBytes = baos.toByteArray();

        return "data:image/png;base64," + Base64.getEncoder().encodeToString(imageBytes);
    }
}