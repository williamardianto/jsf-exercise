package com.example.converter;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.example.model.Name;
import com.example.view.CounterBean;

@FacesConverter(value = "nameConverter")
public class NameConverter implements Converter {
	@Override
	public Object getAsObject(FacesContext ctx, UIComponent uiComponent, String beerId) {
		ValueExpression vex = ctx.getApplication().getExpressionFactory().createValueExpression(ctx.getELContext(),
				"#{counterBean}", CounterBean.class);

		CounterBean beers = (CounterBean) vex.getValue(ctx.getELContext());
		return beers.getName(Integer.valueOf(beerId));
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object name) {
		return ((Name) name).getId().toString();
	}
}
