package br.czar.odonto.converter;

import br.czar.odonto.aplication.RepositoryException;
import br.czar.odonto.model.Exam;
import br.czar.odonto.repository.ExamRepository;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Exam.class)
public class ExamConverter implements Converter<Exam> {
  @Override
  public Exam getAsObject(FacesContext context, UIComponent component, String value) {
    if (value == null || value.isEmpty()) return null;

		ExamRepository cr = new ExamRepository();
		Exam city = null;
    try {
      city = cr.find(Integer.valueOf(value.trim()));
    } catch (RepositoryException e) {
      e.printStackTrace();
    }
    return city;
  }

  @Override
  public String getAsString(FacesContext context, UIComponent component, Exam value) {
    if (value == null || value.getId() == null)
      return null;
    return value.getId().toString();
  }
}
