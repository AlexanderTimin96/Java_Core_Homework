import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)]));
        }
        long countNotAdultPerson = persons.stream().filter(person -> person.getAge() < 18).count();
        System.out.println("Количество  несовершеннолетних: " + countNotAdultPerson);
        System.out.println();

        List<String> conscripts = persons.stream().filter(person -> {
            return person.getAge() > 18 && person.getAge() <= 27 && person.getSex() == Sex.MAN;
        }).map(Person::getFamily).collect(Collectors.toList());
        System.out.println(conscripts);
        System.out.println();

// TODO: переделать

        List<Person> personWithHighEducation = persons.stream().filter(person -> {
                    if (person.getSex() == Sex.WOMAN) {
                        return person.getEducation() == Education.HIGHER && person.getAge() < 60 && person.getAge() > 18;
                    } else {
                        return person.getEducation() == Education.HIGHER && person.getAge() < 65 && person.getAge() > 18;
                    }
                })
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println(personWithHighEducation);
    }
}