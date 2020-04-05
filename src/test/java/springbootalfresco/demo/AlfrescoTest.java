package springbootalfresco.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import springbootalfresco.demo.model.reader.ReaderCreateDTO;

import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static springbootalfresco.demo.constants.ApplicationConstants.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AlfrescoTest {

    @Autowired
    private Environment environment;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testAlfrescoStream() {
        //GIVEN
        Response stringStreamResponse = null;
        ReaderCreateDTO readerCreateDTO = new ReaderCreateDTO(1, 25);
        //WHEN
        try {
            stringStreamResponse = getStringStream(readerCreateDTO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //THEN
        assertNotNull(stringStreamResponse);
        String string = stringStreamResponse.jsonPath().getString("object");
        assertNotNull(string);
        assertTrue(string.contains(ALFRESCO));
        assertTrue(string.contains(FIZZ));
        assertTrue(string.contains(BUZZ));
        assertTrue(string.contains(FIZZBUZZ));
    }

    private Response getStringStream(ReaderCreateDTO readerCreateDTO) throws JsonProcessingException {
        return given()
                .port(Integer.parseInt(Objects.requireNonNull(environment.getProperty("server.port"))))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(objectMapper.writeValueAsString(readerCreateDTO))
                .when()
                .post("/api/stream/toString");

    }
}
