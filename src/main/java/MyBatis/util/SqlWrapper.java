package MyBatis.util;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.util.Arrays;
import java.util.List;

public class SqlWrapper {
	private String sql;
	private Object[] params;

	public SqlWrapper(BoundSql boundSql, Object parameterObject, Configuration configuration) {
		TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
		this.sql = boundSql.getSql();
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		this.params = new Object[parameterMappings.size()];
		for (int i=0;i<parameterMappings.size();i++) {
			ParameterMapping parameterMapping = parameterMappings.get(i);
			Object value;
			String propertyName = parameterMapping.getProperty();
			if (boundSql.hasAdditionalParameter(propertyName)) { // issue #448 ask first for additional params
				value = boundSql.getAdditionalParameter(propertyName);
			} else if (parameterObject == null) {
				value = null;
			} else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
				value = parameterObject;
			} else {
				MetaObject metaObject = configuration.newMetaObject(parameterObject);
				value = metaObject.getValue(propertyName);
			}
			params[i] = value;
		}
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

	@Override
	public String toString() {
		return this.sql + "\n" + Arrays.toString(this.params);
	}
}
