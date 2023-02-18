import employee.Employee;

import java.util.List;
import static parserToJson.ParserToJson.*;

public class Main {
    public static void main(String[] args) {

        //Task 1

        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        List<Employee> list = parseCSV(columnMapping, "data.csv");
        String json = listToJson(list);
        writeString(json, "data.json");

        //Task 2

        List<Employee> list2 = parseXML("data.xml");
        String json2 = listToJson(list2);
        writeString(json2, "data2.json");

        //Task3

        String json3 = readString("data.json");
        List<Employee> list3 = jsonToList(json3);
        System.out.println(list3);
    }
}
