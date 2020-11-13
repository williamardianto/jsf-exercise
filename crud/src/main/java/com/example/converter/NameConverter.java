package com.example.converter;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.example.project.model.Name;
import com.example.view.Crud;

@FacesConverter(value = "nameConverter")
public class NameConverter implements Converter {
	@Override
	public Object getAsObject(FacesContext ctx, UIComponent uiComponent, String nameId) {
		ValueExpression vex = ctx.getApplication().getExpressionFactory().createValueExpression(ctx.getELContext(),
				"#{crud}", Crud.class);

		Crud crud = (Crud) vex.getValue(ctx.getELContext());
		return crud.getName(Integer.valueOf(nameId));
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object name) {
		return ((Name) name).getId().toString();
	}
}
