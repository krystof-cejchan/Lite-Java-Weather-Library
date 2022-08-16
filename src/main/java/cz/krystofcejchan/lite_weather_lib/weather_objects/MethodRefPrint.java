package cz.krystofcejchan.lite_weather_lib.weather_objects;

import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

/**
 * prints object to the console, if possible object.toString() will be printed
 *
 * @param <T> Generics
 */
public class MethodRefPrint<T> {
    T t;

    public MethodRefPrint(@NotNull T t) {
        this.t = t;
    }

    /**
     * prints object to the console using Functional Interface
     */
    public void print() {
        Consumer<T> pRef = System.out::println;
        pRef.accept(t);
    }
}
