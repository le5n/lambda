package lambda.part2.exercise;

import data.Person;
import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;

public class ArrowNotationExercise {

    @Test
    public void getAge() {
        // Person -> Integer
        final Function<Person, Integer> getAge = Person::getAge;

        assertEquals(Integer.valueOf(33), getAge.apply(new Person("", "", 33)));
    }

    @Test
    public void compareAges() {
        // TODO use BiPredicate
        // compareAges: (Person, Person) -> boolean
        final BiPredicate<Person, Person> compareAges = (p1, p2) -> p1.getAge() == p2.getAge();
        assertEquals(true, compareAges.test(new Person("a", "b", 22), new Person("c", "d", 22)));
    }

    // TODO
    // getFullName: Person -> String

    private static String getFullName(Person p) {
        return p.getFirstName() + " " + p.getLastName();
    }


    // TODO
    // ageOfPersonWithTheLongestFullName: (Person -> String) -> Person -> Person -> int
    //


    static private Function<Person, Function<Person, Integer>> ageOfPersonWithTheLongestFullName(Function<Person, String> getFullName) {
        return p1 -> (p2 -> {
            if (getFullName.apply(p1).length() > getFullName.apply(p2).length())
                return p1.getAge();
            else return p2.getAge();
        });
    }

    @Test
    public void getAgeOfPersonWithTheLongestFullName() throws Exception {
        // Person -> String
        final Function<Person, String> getFullName = ArrowNotationExercise::getFullName;

        // Person -> Person -> Integer
        // TODO use ageOfPersonWithTheLongestFullName(getFullName)
        Function<Person, Function<Person, Integer>> ageOfPersonWithTheLongestFullName = ageOfPersonWithTheLongestFullName(getFullName);

        assertEquals(
                Integer.valueOf(1),
                ageOfPersonWithTheLongestFullName.apply(
                        new Person("a", "b", 2))
                        .apply(new Person("aa", "b", 1)));
    }
}
