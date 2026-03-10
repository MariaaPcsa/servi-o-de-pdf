package com.legado.servico_de_pdf.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class LinhaDigitavelService {

    public String gerar(String banco, BigDecimal valor) {
        return banco + "00000.00000 00000.000000 00000.000000 0 "
                + valor.movePointRight(2).toPlainString();
    }
}