package com.legado.servico_de_pdf.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Boleto {

    private String banco;
    private String beneficiario;
    private String pagador;
    private BigDecimal valor;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate vencimento;

    private String linhaDigitavel;

    // NOVOS CAMPOS
    private String cpf;
    private String numeroPlano;
    private String competencia;
    private String codigoBarras;

    public Boleto() {
    }

    // GETTERS E SETTERS

    public String getBanco() { return banco; }
    public void setBanco(String banco) { this.banco = banco; }

    public String getBeneficiario() { return beneficiario; }
    public void setBeneficiario(String beneficiario) { this.beneficiario = beneficiario; }

    public String getPagador() { return pagador; }
    public void setPagador(String pagador) { this.pagador = pagador; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public LocalDate getVencimento() { return vencimento; }
    public void setVencimento(LocalDate vencimento) { this.vencimento = vencimento; }

    public String getLinhaDigitavel() { return linhaDigitavel; }
    public void setLinhaDigitavel(String linhaDigitavel) { this.linhaDigitavel = linhaDigitavel; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getNumeroPlano() { return numeroPlano; }
    public void setNumeroPlano(String numeroPlano) { this.numeroPlano = numeroPlano; }

    public String getCompetencia() { return competencia; }
    public void setCompetencia(String competencia) { this.competencia = competencia; }

    public String getCodigoBarras() { return codigoBarras; }
    public void setCodigoBarras(String codigoBarras) { this.codigoBarras = codigoBarras; }
}