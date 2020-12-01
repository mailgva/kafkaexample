package com.gorbatenko.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    String name;
    int age;
    String text;

    @Override
    public String toString() {
        return name + "(" + age + ") say: \"" + text + "\"";
    }
}
