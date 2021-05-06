package br.czar.odonto.aplication;

import java.io.Serial;

public class RepositoryException extends Exception {
	@Serial
	private static final long serialVersionUID = -2814700989842522205L;
	
	public RepositoryException(String message) {
		super(message);
	}
}
