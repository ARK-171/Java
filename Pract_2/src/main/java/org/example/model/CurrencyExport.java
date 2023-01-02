package org.example.model;

public class CurrencyExport {
    private Integer id;
    private Double value;
    private Integer nominal;
    private String name;
    private String code;
    private String date;

    public CurrencyExport() {
    }

    public CurrencyExport(Integer id, Double value, Integer nominal, String currencyName, String currencyCode, String date) {
        this.id = id;
        this.value = value;
        this.nominal = nominal;
        this.name = currencyName;
        this.code = currencyCode;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Integer getNominal() {
        return nominal;
    }

    public void setNominal(Integer nominal) {
        this.nominal = nominal;
    }

    public String getCurrencyName() {
        return name;
    }

    public void setCurrencyName(String currencyName) {
        this.name = currencyName;
    }

    public String getCurrencyCode() {
        return code;
    }

    public void setCurrencyCode(String currencyCode) {
        this.code = currencyCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
