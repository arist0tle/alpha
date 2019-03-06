package util;

import lombok.Data;

import java.util.List;

/**
 * Created by tanghaiyang on 2019/3/6.
 */
@Data
public class Person {
    private String name;
    private int age;
    private String sex;
    private String city;
    private String birthday;
    private List<String> colors;
    private List<Person> child;
}
