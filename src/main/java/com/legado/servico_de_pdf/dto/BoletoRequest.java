package com.legado.servico_de_pdf.dto;



import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados para geração de boleto")
public class BoletoRequest {

    @Schema(description = "Código do banco", example = "001")
    private String banco;

    @Schema(description = "Nome do beneficiário", example = "Empresa XYZ")
    private String beneficiario;

    @Schema(description = "Nome do pagador", example = "Maria Correia")
    private String pagador;

    @Schema(description = "Valor do boleto", example = "150.75")
    private Double valor;

    @Schema(description = "Data de vencimento", example = "04/03/2026")
    private String vencimento;

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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getVencimento() {
        return vencimento;
    }

    public void setVencimento(String vencimento) {
        this.vencimento = vencimento;
    }
}
