package br.com.sebrae.manter_produto.config;

import io.cucumber.cucumberexpressions.ParameterByTypeTransformer;

import java.lang.reflect.Type;
import java.math.BigDecimal;

public class CustomParameterTypes implements ParameterByTypeTransformer {

    @Override
    public Object transform(String fromValue, Type type) throws Throwable {
        if (type.equals(BigDecimal.class)) {
            return new BigDecimal(fromValue);
        }
        throw new IllegalArgumentException("Cannot transform " + fromValue + " to " + type);
    }
}
