package com.lh.test.framework.configuration;

import com.google.inject.Provider;

/**
 * Extends the provider class
 * Used in dependency injection and WebDriverFactory for multiple operations
 * @param <T>
 */
public interface DependencyFactory<T> extends Provider<T> {
    T get();
}
