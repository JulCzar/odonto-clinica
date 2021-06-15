package br.czar.odonto.converter;

import br.czar.odonto.model.ConsultationStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ConsultationStatusConverter implements AttributeConverter<ConsultationStatus, Integer> {
	@Override
	public Integer convertToDatabaseColumn(ConsultationStatus attribute) {
		return attribute == null? null : attribute.getId();
	}

	@Override
	public ConsultationStatus convertToEntityAttribute(Integer dbData) {
		return ConsultationStatus.valueOf(dbData);
	}
}
