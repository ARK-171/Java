package org.example.model;

import java.time.LocalDate;

public class CurrencyExchange {
	private Integer id;
	private Double value;
	private Integer nominal;
	private String name;
	private String code;
	private LocalDate date;

	public CurrencyExchange() {
	}

	public CurrencyExchange(Integer id, Double value, Integer nominal, String currencyName, String currencyCode, LocalDate date) {
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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
}
