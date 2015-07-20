package com.web.bean;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

public class ModelInterceptor extends EmptyInterceptor{
	
	private static final long serialVersionUID = 778111241939995288L;
	private static final String CREATE_DATE = "createDate";
	private static final String UPDATE_DATE = "modifyDate";
	
	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {

		Date date = new Date();
		for(int i = 0; i < propertyNames.length; i++){
			if(CREATE_DATE.equals(propertyNames[i]) || UPDATE_DATE.equals(propertyNames[i]))
				state[i] = date;
		}
		
		return true;
	}
	
	@Override
	public boolean onFlushDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		
		Date date = new Date();
		for(int i = 0; i < propertyNames.length; i++){
			if(UPDATE_DATE.equals(propertyNames[i]))
				currentState[i] = date;
		}
		
		return true;
	}
}
