package com.jie.io.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Stream;

public class LamdbaTest {
    public static void main(String[] args) {
        Function<User, Integer> age = User::getAge;

        BiConsumer<User, Integer> setAge = User::setAge;
        User u1 = new User(1,"aa");
        User u2 = new User(2,"bb");
        User u3 = new User(2,"cc");
        User u4 = new User(4,"dd");
        ArrayList<User> user = new ArrayList<>();
        user.add(u1);
        user.add(u2);
        user.add(u3);
        user.add(u4);
        user.stream().map(User::getAge).forEach(System.out::println);

    }
}
