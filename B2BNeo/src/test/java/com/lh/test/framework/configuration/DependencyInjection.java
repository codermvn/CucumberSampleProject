package com.lh.test.framework.configuration;

import com.google.inject.*;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Names;
import com.lh.test.framework.annotations.StaticInjection;

import org.openqa.selenium.WebDriver;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ConfigurationBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Properties;

public class DependencyInjection {
//
//    private Injector injector;
//
//    public Injector getInjector() {
//        if (this.injector == null) {
//            injector = Guice.createInjector(
//                    Stage.PRODUCTION,
//                    CucumberModules.SCENARIO,this
//            );
//        }
//
//        return injector;
//    }
//
//    public void injectMembers(Object object) {
//        getInjector().injectMembers(object);
//    }
//
//    public <T> T get(Class<T> klass) throws Error {
//        return getInjector().getInstance(klass);
//    }
//
//    // TODO: 10/19/2017 Set up the injection module for more bindings 
//    @Override
//    protected void configure() {
//        bind(WebDriver.class).toProvider(WebDriverFactory.class)
//                .in(ScenarioScoped.class);
//        bind(Configuration.class).toProvider(ConfigurationProvider.class)
//                .in(ScenarioScoped.class);
//        Names.bindProperties(binder(), getProperties());
//
//        ConfigurationBuilder reflectionsConfiguration = new ConfigurationBuilder()
//                .forPackages("com.lh.test")
//                .setScanners(new MethodAnnotationsScanner(),
//                        new SubTypesScanner(), new TypeAnnotationsScanner());
//
//        Reflections reflections = new Reflections(reflectionsConfiguration);
//
//        setAllTestClassesInScenarioScope(reflections);
//        setupStaticInjection(reflections);
//
//        // PageObjectModelTypeListener interrogates each type to see if it's a page object.
//        // It does this by looking at field types and annotations
//        bindListener(Matchers.any(), new PageObjectModelTypeListener());
//    }
//
//    private Properties getProperties() {
//        return Configuration.getConfiguration().getProperties();
//    }
//
//    private void setAllTestClassesInScenarioScope(Reflections reflections) {
//        ArrayList<Class<?>> scenarioScopedClasses = new ArrayList<Class<?>>();
//
//        // All classes containing methods annotated with Cucumber keywords
//        Class<? extends Annotation>[] cucumberKeywordClasses = new Class[] {
//                Given.class, When.class, Then.class, And.class, But.class,
//                Before.class, After.class };
//        for (Class<? extends Annotation> cucumberKeywordClass : cucumberKeywordClasses) {
//            for (Method method : reflections
//                    .getMethodsAnnotatedWith(cucumberKeywordClass)) {
//                if (!scenarioScopedClasses.contains(method.getDeclaringClass())) {
//                    scenarioScopedClasses.add(method.getDeclaringClass());
//                }
//            }
//        }
//
//        // Mark all of the above with ScenarioScoped
//        for (Class<?> klass : scenarioScopedClasses) {
//            bind(klass).in(ScenarioScoped.class);
//        }
//    }
//
//    private void setupStaticInjection(Reflections reflections) {
//        // Find all classes that require static injection based on the annotation
//        for (Class<?> aClass : reflections
//                .getTypesAnnotatedWith(StaticInjection.class)) {
//            requestStaticInjection(aClass);
//        }
//    }
}
