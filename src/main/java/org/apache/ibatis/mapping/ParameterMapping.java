/**
 * Copyright 2009-2016 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.ibatis.mapping;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.sql.ResultSet;

/**
 * 参数的映射
 *
 * @author Clinton Begin
 */
public class ParameterMapping {

    /**
     * 配置
     */
    private Configuration configuration;

    /**
     * 属性
     */
    private String property;

    /**
     * 参数的类型
     */
    private ParameterMode mode;

    /**
     * Java的类型
     */
    private Class<?> javaType = Object.class;

    /**
     * jdbc的类型
     */
    private JdbcType jdbcType;

    /**
     * 数字的精度
     */
    private Integer numericScale;

    /**
     * 类型处理器
     */
    private TypeHandler<?> typeHandler;

    /**
     * 结果集映射的ID
     */
    private String resultMapId;
    private String jdbcTypeName;
    private String expression;

    private ParameterMapping() {
    }

    public static class Builder {

        private ParameterMapping parameterMapping = new ParameterMapping();

        public Builder(Configuration configuration, String property, TypeHandler<?> typeHandler) {
            parameterMapping.configuration = configuration;
            parameterMapping.property = property;
            parameterMapping.typeHandler = typeHandler;
            parameterMapping.mode = ParameterMode.IN;
        }

        public Builder(Configuration configuration, String property, Class<?> javaType) {
            parameterMapping.configuration = configuration;
            parameterMapping.property = property;
            parameterMapping.javaType = javaType;
            parameterMapping.mode = ParameterMode.IN;
        }

        public Builder mode(ParameterMode mode) {
            parameterMapping.mode = mode;
            return this;
        }

        public Builder javaType(Class<?> javaType) {
            parameterMapping.javaType = javaType;
            return this;
        }

        public Builder jdbcType(JdbcType jdbcType) {
            parameterMapping.jdbcType = jdbcType;
            return this;
        }

        public Builder numericScale(Integer numericScale) {
            parameterMapping.numericScale = numericScale;
            return this;
        }

        public Builder resultMapId(String resultMapId) {
            parameterMapping.resultMapId = resultMapId;
            return this;
        }

        public Builder typeHandler(TypeHandler<?> typeHandler) {
            parameterMapping.typeHandler = typeHandler;
            return this;
        }

        public Builder jdbcTypeName(String jdbcTypeName) {
            parameterMapping.jdbcTypeName = jdbcTypeName;
            return this;
        }

        public Builder expression(String expression) {
            parameterMapping.expression = expression;
            return this;
        }

        public ParameterMapping build() {
            resolveTypeHandler();
            validate();
            return parameterMapping;
        }

        private void validate() {
            if (ResultSet.class.equals(parameterMapping.javaType)) {
                if (parameterMapping.resultMapId == null) {
                    throw new IllegalStateException("Missing resultmap in property '"
                            + parameterMapping.property + "'.  "
                            + "Parameters of type java.sql.ResultSet require a resultmap.");
                }
            } else {
                if (parameterMapping.typeHandler == null) {
                    throw new IllegalStateException("Type handler was null on parameter mapping for property '"
                            + parameterMapping.property + "'. It was either not specified and/or could not be found for the javaType ("
                            + parameterMapping.javaType.getName() + ") : jdbcType (" + parameterMapping.jdbcType + ") combination.");
                }
            }
        }

        private void resolveTypeHandler() {
            if (parameterMapping.typeHandler == null && parameterMapping.javaType != null) {
                final Configuration configuration = parameterMapping.configuration;
                final TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
                parameterMapping.typeHandler = typeHandlerRegistry.getTypeHandler(parameterMapping.javaType, parameterMapping.jdbcType);
            }
        }

    }

    public String getProperty() {
        return property;
    }

    /**
     * Used for handling output of callable statements
     *
     * @return
     */
    public ParameterMode getMode() {
        return mode;
    }

    /**
     * Used for handling output of callable statements
     *
     * @return
     */
    public Class<?> getJavaType() {
        return javaType;
    }

    /**
     * Used in the UnknownTypeHandler in case there is no handler for the property type
     *
     * @return
     */
    public JdbcType getJdbcType() {
        return jdbcType;
    }

    /**
     * Used for handling output of callable statements
     *
     * @return
     */
    public Integer getNumericScale() {
        return numericScale;
    }

    /**
     * Used when setting parameters to the PreparedStatement
     *
     * @return
     */
    public TypeHandler<?> getTypeHandler() {
        return typeHandler;
    }

    /**
     * Used for handling output of callable statements
     *
     * @return
     */
    public String getResultMapId() {
        return resultMapId;
    }

    /**
     * Used for handling output of callable statements
     *
     * @return
     */
    public String getJdbcTypeName() {
        return jdbcTypeName;
    }

    /**
     * Not used
     *
     * @return
     */
    public String getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ParameterMapping{");
        sb.append("property='").append(property).append('\'');
        sb.append(", mode=").append(mode);
        sb.append(", javaType=").append(javaType);
        sb.append(", jdbcType=").append(jdbcType);
        sb.append(", numericScale=").append(numericScale);
        sb.append(", resultMapId='").append(resultMapId).append('\'');
        sb.append(", jdbcTypeName='").append(jdbcTypeName).append('\'');
        sb.append(", expression='").append(expression).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
