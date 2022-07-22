package helpers;

import com.github.javafaker.Faker;

public class RandomUtils {
    private static final Faker faker = new Faker();

    public static String getRandomBookshelfName() {
        return faker.book().publisher() + faker.bothify("(######??????).");
    }

    public static String getRandomBookshelfAnnotation() {
        return faker.shakespeare().hamletQuote() + faker.bothify("(######??????).");
    }

    public static String getRandomBookshelfBookAnnotation() {
        return faker.shakespeare().kingRichardIIIQuote() + faker.bothify("(######??????).");
    }
}
