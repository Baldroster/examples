import com.google.gson.Gson;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Sample {
    public static void main(String[] args) {

    }
    public static void ex1() throws IOException, ClassNotFoundException {
        Person person = new Person();
        person.name = "Ivan";
        person.cardNumber = "5555 5555 5555 5555";
        person.cardCVV2 = "123";
        //Сериализация
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream("person.ser"));
        objectOutputStream.writeObject(person);
        objectOutputStream.close();

        //Десериализация
        ObjectInputStream objectInputStream = new ObjectInputStream(
                new FileInputStream("person.ser"));
        Person person1 = (Person) objectInputStream.readObject();
        objectInputStream.close();
        System.out.println(person1.toString());

    }

    public static void ex2() throws IOException {
        Person person = new Person();
        person.name = "Ivan";
        person.cardNumber = "5555 5555 5555 5555";
        person.cardCVV2 = "123";
        File file = new File("Person.json");

        //Процесс сериализации
        Gson gson = new Gson();
        String jsonString = gson.toJson(person);
        Files.write(Paths.get("Person.json"), jsonString.getBytes());


        //десериализация
        jsonString = new String(Files.readAllBytes(Paths.get("Person.json")), StandardCharsets.UTF_8);
        Person person1 = gson.fromJson(jsonString, Person.class);
        System.out.println(person1.toString());
    }

    public static void ex3() throws JAXBException {
        Person person = new Person();
        person.name = "Ivan";
        person.cardNumber = "5555 5555 5555 5555";
        person.cardCVV2 = "143";

        //создание объекта Marshaller, который выполняет сериализацию
        JAXBContext context = JAXBContext.newInstance(Person.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        // сама сериализация
        File file = new File("Person.xml");
        marshaller.marshal(person, file);

        //десериализуем
        file = new File("Person.xml");
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Person person1 = (Person) unmarshaller.unmarshal(file);
        System.out.println(person1.toString());
    }


    public static void ex4() throws IOException, InterruptedException {

        Properties properties = new Properties();
        properties.load(new FileReader(new File("timeout.properties")));
        long timeout = Long.parseLong(properties.getProperty("timeout"));
        String catName = properties.getProperty("catName", "Murzik");
        Thread.sleep(timeout);
        System.out.println("timeout was " + timeout);
        System.out.println("cat name was " + catName);
    }
}
