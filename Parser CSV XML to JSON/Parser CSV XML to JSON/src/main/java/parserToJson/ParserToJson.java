package parserToJson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import employee.Employee;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.*;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ParserToJson {

    public static List<Employee> parseCSV(String[] columnMapping, String fileName) {
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            ColumnPositionMappingStrategy<Employee> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(Employee.class);
            strategy.setColumnMapping(columnMapping);
            CsvToBean<Employee> csv = new CsvToBeanBuilder<Employee>(reader)
                    .withMappingStrategy(strategy)
                    .build();
            return csv.parse();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> String listToJson(List<T> list) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Type listType = new TypeToken<List<T>>() {
        }.getType();
        return gson.toJson(list, listType);
    }

    public static void writeString(String json, String fileName) {
        try (FileWriter file = new FileWriter(fileName)) {
            file.write(json);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Employee> parseXML(String fileName) {
        List<Employee> list = new ArrayList<>();
        Document document = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(new File(fileName));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

        Node root = document.getDocumentElement();
        NodeList rootChildes = root.getChildNodes();

        for (int i = 0; i < rootChildes.getLength(); i++) {
            if (rootChildes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Node employeeNode = rootChildes.item(i);
                NodeList attributeEmployee = employeeNode.getChildNodes();

                long id = 0;
                String firstName = "";
                String lastName = "";
                String country = "";
                int age = 0;

                for (int j = 0; j < attributeEmployee.getLength(); j++) {
                    switch (attributeEmployee.item(j).getNodeName()) {
                        case "id": {
                            id = Long.parseLong(attributeEmployee.item(j).getTextContent());
                            break;
                        }
                        case "firstName": {
                            firstName = attributeEmployee.item(j).getTextContent();
                            break;
                        }
                        case "lastName": {
                            lastName = attributeEmployee.item(j).getTextContent();
                            break;
                        }
                        case "country": {
                            country = attributeEmployee.item(j).getTextContent();
                            break;
                        }
                        case "age": {
                            age = Integer.parseInt(attributeEmployee.item(j).getTextContent());
                            break;
                        }
                    }
                }
                Employee employee = new Employee(id, firstName, lastName, country, age);
                list.add(employee);
            }
        }
        return list;
    }

    public static String readString(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader buffer = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = buffer.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static List<Employee> jsonToList(String s) {
        JSONParser parser = new JSONParser();
        List<Employee> list = new ArrayList<>();
        try {
            JSONArray jsonArray = (JSONArray) parser.parse(s);
            for (Object o : jsonArray) {
                JSONObject jsonObject = (JSONObject) o;
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                Employee employee = gson.fromJson(String.valueOf(jsonObject), Employee.class);
                list.add(employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}