package com.lh.test.framework.configuration;

import com.google.inject.Injector;
import com.google.inject.TypeLiteral;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PageObjectModelTypeListener implements TypeListener {

    public <I> void hear(TypeLiteral<I> typeLiteral, TypeEncounter<I> typeEncounter) {
        if (isPageObject(typeLiteral.getRawType()))
            typeEncounter.register(new PageObjectModelInjectionListener(typeEncounter.getProvider(Injector.class)));
    }

    private boolean isPageObject(Class<?> rawType) {
        for (Field field : getAllFields(rawType)) {
            Class<?> type = field.getType();
            if(WebElement.class.isAssignableFrom(type)) return true;

            if(List.class.isAssignableFrom(type)) {
                Type genericType = field.getGenericType();
                if(genericType instanceof ParameterizedType) {
                    ParameterizedType parameterizedType = (ParameterizedType) genericType;
                    if(!(parameterizedType.getActualTypeArguments()[0] instanceof Class)) return false;
                    Class genericTypeArgument = (Class<?>)parameterizedType.getActualTypeArguments()[0];
                    if(WebElement.class.isAssignableFrom(genericTypeArgument)) return true;
                }
            }
        }

        return false;
    }

    private static List<Field> getAllFields(Object object) {
        return getAllClassFields(object.getClass());
    }

    private static List<Field> getAllClassFields(Class klass) {
        ArrayList<Field> fields = new ArrayList<Field>();
        while (klass != null) {
            Collections.addAll(fields, klass.getDeclaredFields());
            klass = klass.getSuperclass();
        }

        return fields;
    }
}
