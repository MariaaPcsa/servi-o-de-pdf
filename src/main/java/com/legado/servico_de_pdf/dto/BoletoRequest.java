package com.legado.servico_de_pdf.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Schema(description = "Dados para geração de boleto")
public class BoletoRequest {

    @Schema(description = "Código do banco", example = "001 - Banco do nosso dinheiro")
    @NotBlank(message = "Banco é obrigatório")
    private String banco;

    @Schema(description = "Nome do beneficiário", example = "Serviços Médicos LTDA")
    @NotBlank(message = "Beneficiário é obrigatório")
    private String beneficiario;

    @Schema(description = "Nome do pagador", example = "Maria Silva")
    @NotBlank(message = "Pagador é obrigatório")
    private String pagador;

    @Schema(description = "Valor do boleto", example = "289.90")
    @NotNull(message = "Valor é obrigatório")
    @Positive(message = "Valor deve ser positivo")
    private BigDecimal valor;

    @Schema(description = "Data de vencimento (yyyy-MM-dd)", example = "2026-02-28")
    @NotBlank(message = "Vencimento é obrigatório")
    private String vencimento;

    @Schema(description = "CPF do pagador", example = "123.456.789-09")
    @NotBlank(message = "CPF é obrigatório")
    private String cpf;

    @Schema(description = "Número do plano", example = "PLN-45879")
    @NotBlank(message = "Número do plano é obrigatório")
    private String numeroPlano;

    @Schema(description = "Competência", example = "02/2026")
    @NotBlank(message = "Competência é obrigatória")
    private String competencia;

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(String beneficiario) {
        this.beneficiario = beneficiario;
    }

    public String getPagador() {
        return pagador;
    }

    public void setPagador(String pagador) {
        this.pagador = pagador;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getVencimento() {
        return vencimento;
    }

    public void setVencimento(String vencimento) {
        this.vencimento = vencimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNumeroPlano() {
        return numeroPlano;
    }

    public void setNumeroPlano(String numeroPlano) {
        this.numeroPlano = numeroPlano;
    }

    public String getCompetencia() {
        return competencia;
    }

    public void setCompetencia(String competencia) {
        this.competencia = competencia;
    }
}
