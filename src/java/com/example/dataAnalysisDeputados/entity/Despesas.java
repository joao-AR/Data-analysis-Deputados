package com.example.dataAnalysisDeputados.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Despesas {
    public Despesas(int codDocumento, int id_deputado, int ano, int mes, String tipoDespesa,float valorLiquido ) {
        this.id_deputado = id_deputado;
        this.ano = ano;
        this.mes = mes;
        this.tipoDespesa = tipoDespesa;
        this.codDocumento = codDocumento;
        this.valorLiquido = valorLiquido;
    }

    private int id_deputado;

    public int getId_deputado() {
        return id_deputado;
    }

    public void setId_deputado(int id_deputado) {
        this.id_deputado = id_deputado;
    }

    private int ano;
    private int mes;
    private String tipoDespesa;
    private int codDocumento;
    @JsonIgnore
    private String tipoDocumento;
    @JsonIgnore
    private String codTipoDocumento;
    @JsonIgnore
    private String dataDocumento;
    @JsonIgnore
    private String numDocumento;
    @JsonIgnore
    private String valorDocumento;
    @JsonIgnore
    private String urlDocumento;
    @JsonIgnore
    private String nomeFornecedor;
    @JsonIgnore
    private String cnpjCpfFornecedor;

    private float valorLiquido;
    @JsonIgnore
    private String valorGlosa;
    @JsonIgnore
    private String numRessarcimento;
    @JsonIgnore
    private String codLote;
    @JsonIgnore
    private String parcela;

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public String getTipoDespesa() {
        return tipoDespesa;
    }

    public void setTipoDespesa(String tipoDespesa) {
        this.tipoDespesa = tipoDespesa;
    }

    public int getCodDocumento() {
        return codDocumento;
    }

    public void setCodDocumento(int codDocumento) {
        this.codDocumento = codDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getCodTipoDocumento() {
        return codTipoDocumento;
    }

    public void setCodTipoDocumento(String codTipoDocumento) {
        this.codTipoDocumento = codTipoDocumento;
    }

    public String getDataDocumento() {
        return dataDocumento;
    }

    public void setDataDocumento(String dataDocumento) {
        this.dataDocumento = dataDocumento;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String getValorDocumento() {
        return valorDocumento;
    }

    public void setValorDocumento(String valorDocumento) {
        this.valorDocumento = valorDocumento;
    }

    public String getUrlDocumento() {
        return urlDocumento;
    }

    public void setUrlDocumento(String urlDocumento) {
        this.urlDocumento = urlDocumento;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public String getCnpjCpfFornecedor() {
        return cnpjCpfFornecedor;
    }

    public void setCnpjCpfFornecedor(String cnpjCpfFornecedor) {
        this.cnpjCpfFornecedor = cnpjCpfFornecedor;
    }

    public float getValorLiquido() {
        return valorLiquido;
    }

    public void setValorLiquido(float valorLiquido) {
        this.valorLiquido = valorLiquido;
    }

    public String getValorGlosa() {
        return valorGlosa;
    }

    public void setValorGlosa(String valorGlosa) {
        this.valorGlosa = valorGlosa;
    }

    public String getNumRessarcimento() {
        return numRessarcimento;
    }

    public void setNumRessarcimento(String numRessarcimento) {
        this.numRessarcimento = numRessarcimento;
    }

    public String getCodLote() {
        return codLote;
    }

    public void setCodLote(String codLote) {
        this.codLote = codLote;
    }

    public String getParcela() {
        return parcela;
    }

    public void setParcela(String parcela) {
        this.parcela = parcela;
    }
}
