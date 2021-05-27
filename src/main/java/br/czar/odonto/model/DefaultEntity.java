package br.czar.odonto.model;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
public class DefaultEntity<T> implements Serializable {
	@Serial
	private static final long serialVersionUID = 1191286015134874793L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private LocalDateTime created;
	private LocalDateTime lastEdit;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getCreated() {
		return this.created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public LocalDateTime getLastEdit() {
		return this.lastEdit;
	}

	public void setLastEdit(LocalDateTime lastEdit) {
		this.lastEdit = lastEdit;
	}

	@PrePersist
	public void onBeforeInsert() {
		setCreated(LocalDateTime.now());
	}

	@PreUpdate
	public void preUpdate() {
		setLastEdit(LocalDateTime.now());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DefaultEntity other = (DefaultEntity) obj;
		if (id == null) return other.id == null;
		else return id.equals(other.id);
	}
}
