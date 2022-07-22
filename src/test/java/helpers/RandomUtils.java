package helpers;

import com.github.javafaker.Faker;

public class RandomUtils {
    private static final Faker faker = new Faker();

    public static String getRandomBookshelfName() {
        return faker.book().publisher() + faker.bothify("(######??????).");
    }

    public static String getRandomBookshelfAnnotation() {
        return faker.book().publisher() + faker.bothify("(######??????).");
    }
}
