package cz.krystofcejchan.lite_weather_lib.weather_objects;

import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class MethodRefPrint<T> {
    T t;

    public MethodRefPrint(@NotNull T t) {
        this.t = t;
    }

    public void print() {
        Consumer<T> fPrint = System.out::println;
        fPrint.accept(this.t);
    }
}
