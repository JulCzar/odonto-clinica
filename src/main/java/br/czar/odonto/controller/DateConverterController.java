package br.czar.odonto.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Named
@RequestScoped
public class DateConverterController implements Serializable {
	public String formatDate(LocalDateTime date) {
		return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
	public String formatDateTime(LocalDateTime date) {
		return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm"));
	}
	public String formatDate(LocalDate date) {
		return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
	public String formatDateTime(LocalDate date) {
		return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm"));
	}
}
