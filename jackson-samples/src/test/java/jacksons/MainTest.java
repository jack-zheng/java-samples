package jacksons;


import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

class MainTest {
    private ObjectMapper mapper = new ObjectMapper();
    @Test
    public void test_single_obj() throws IOException {
        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("user.json");
        User user = mapper.readValue(inputStream, User.class);
        System.out.println(user);

        System.out.println(mapper.writeValueAsString(user));
        //mapper.writeValue(new File("user_back.json"), user);
    }

    @Test
    public void test_collection() throws IOException {
        // read map from json
        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("map.json");
        Map<String, Integer> map = mapper.readValue(inputStream, Map.class);
        System.out.println(map.get("a"));
    }

    @Test
    public void test_collection_pojo() throws IOException {
        // read map from json
        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("list_pojo.json");
        List<User> list = mapper.readValue(inputStream, List.class);
        System.out.println(list.get(1));
    }

    @Test
    public void test_tree_model() throws IOException {
        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("stuff.json");
        JsonNode root = mapper.readTree(inputStream);
        System.out.println(root.get("name").asText());
        System.out.println(root.get("age").asInt());

        System.out.println("------- add property");
        root.withObject("/other").put("type", "student");
        System.out.println(mapper.writeValueAsString(root));
    }

    @Test
    public void test_JsonAnySetter() throws JsonProcessingException {
        String json = "{\n" +
                "    \"name\": \"Pear yPhone 72\",\n" +
                "    \"category\": \"cellphone\",\n" +
                "    \"displayAspectRatio\": \"97:3\",\n" +
                "    \"audioConnector\": \"none\"\n" +
                "}";

        Product prod = mapper.readValue(json, Product.class);
        System.out.println(prod.getName());

        System.out.println("------- details");
        prod.getDetails().forEach((k, v) -> System.out.println(k + " : " + v));

        System.out.println("------- serialize");
        System.out.println(mapper.writeValueAsString(prod));
    }

    @Test
    public void test_stream() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File resourceFolder = new File(classLoader.getResource("").getFile());

        // write json
        File jsonFile = new File(resourceFolder, "stream.json");
        JsonGenerator g = mapper.createGenerator(jsonFile, JsonEncoding.UTF8);
        g.writeStartObject();
        g.writeStringField("message", "Hello World!");
        g.writeEndObject();
        g.close();

        // parse json
        JsonParser p = mapper.createParser(jsonFile);
        JsonToken t = p.nextToken();
        System.out.println(t);
        t = p.nextToken();
        System.out.println(t + ", " + p.getCurrentName());
        t = p.nextToken();
        System.out.println(t + ", " + t.asString());
        String msg = p.getText();
        System.out.println("my message to u is: " + msg);

    }
}