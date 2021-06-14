package br.czar.odonto.controller;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serial;
import java.io.Serializable;

@Named
@ViewScoped
public class ProfileController implements Serializable {
	@Serial
	private static final long serialVersionUID = 2122937466427799647L;
	private boolean editing;

	public ProfileController() {
		setEditing(false);
	}

	public boolean isEditing() {
		return editing;
	}

	public void setEditing(boolean editing) {
		this.editing = editing;
	}

	public void enableEdit() {
		setEditing(true);
	}
	public void disableEdit() {
		setEditing(false);
	}
}
