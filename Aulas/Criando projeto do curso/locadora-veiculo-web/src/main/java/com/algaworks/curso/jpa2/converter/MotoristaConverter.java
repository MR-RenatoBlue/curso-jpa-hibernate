package com.algaworks.curso.jpa2.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.algaworks.curso.jpa2.dao.MotoristaDAO;
import com.algaworks.curso.jpa2.modelo.ModeloCarro;
import com.algaworks.curso.jpa2.modelo.Motorista;
import com.algaworks.curso.jpa2.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Motorista.class)
public class MotoristaConverter implements Converter {

	private MotoristaDAO dao;

	public MotoristaConverter() {
		this.dao = CDIServiceLocator.getBean(MotoristaDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		if (value != null) {
			return (Motorista) this.dao.buscarPeloCodigo(new Long(value));
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value != null) {
			Long codigo = ((Motorista) value).getCodigo();
			return codigo == null ? null : codigo.toString();
		}

		return "";
	}

}
