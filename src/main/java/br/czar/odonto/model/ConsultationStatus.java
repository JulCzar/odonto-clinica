package br.czar.odonto.model;

public enum ConsultationStatus {
	ABERTO(1, "Aberto"),
	CONCLUIDO(2, "Concluído"),
	CANCELADO(3, "Cancelado");

	private Integer id;
	private String label;

	ConsultationStatus(Integer id, String label) {
		this.id = id;
		this.label = label;
	}

	public Integer getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public static ConsultationStatus valueOf(Integer id) {
		if (id == null)
			return null;

		for (ConsultationStatus cs : values())
			if (cs.getId().equals(id))
				return cs;

		return null;
	}
}
