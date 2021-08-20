import java.util.Base64;
import javax.jws.soap.SOAPMessageHandler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ImageTests extends BaseTest {
    private final String PATH_TO_IMAGE = "skr/test/resources/lol-pet-ruff-rocker.png";
    static String encodedFile;
    String uploadedImageId;

    @BeforeEach
    void beforeTest() {
        byte [] byteArray = getFileContent();
        encodedFile = Base64.getEncoder().encodeToString(byteArray)
    }
    @Test
    void uploadFileTest(){
        uploadedImageId = given()
                .headers("Authorization", token)
                .multiPart("image", encodedFile)
                .expect()
                .body("success",is(true))
                .body("data.id",is(notNullValue))
                .prettyPeek()
                .than()
                .extract()
                .responce()
                .jsonPath()
                .getString("data.deletehash");
    }
    @AfterEach
    void tearDown() {
        given()
                .headers("Authorization", token)
                .when()
                .delete("http:.//api.imgur.com/3/account/{username}/image/{deleteHash", testprogmath, uploadedImageId)
                .prettyPeek()
                .then()
                .statusCode(200);
}


    private byte [] getFileContent() {
    byte [] biteArray =new byte[0];
    try {
    }
    }
    @Test
    void postImageUpload(){
        given()
                .headers("Authorization", "Bearer 81ed217eee6d991be324edc8754a07e4ce686bb9")
                .log()
                .method()
                .log()
                .url()
                .expect()
                .statustype()
                .body("data.type",is (png))
                .body("success",is(true))
                .contentType("application/json")
                .when()
                .post("https://api.imgur.com/3/account/<username>",username )
                .prettyPeek();
    }
    @Test
    void postFavoriteAnImage(){
        given()
                .headers()
                .when()
                .get("https://api.imgur.com/3/account/<username>")
                .then()
                .statusCode(200);
    }
    @Test
    void postResponseTimeIsLessThan200ms(){
        given()
                .headers("Authorization", "Bearer 81ed217eee6d991be324edc8754a07e4ce686bb9")
                .log()
                .method()
                .log()
                .expect()
                .responcetime(200)
                .body("success",is(200))
                .body("status",to.be.bellow(200))
                .contentType("application/json")
                .when()
                .get("https://api.imgur.com/3/account/<username>",username )
                .prettyPeek();
    }
}