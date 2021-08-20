import org.junit.jupiter.api.Test;

public class AccountTests extends BaseTest {




    @Test
    void getAccountInfoTest() {
        given()
                .headers()
                .when()
                .get("https://api.imgur.com/3/account/<username>")
                .then()
                .statusCode(200);
    }
 @Test
 void uploadFileTest() {
     given()
             .headers("Authorization", token)
             .multiPart("image", encodedImage)
             .expect()
             .body("success", is(true))
             .body("data.id", is(notNullValue()))
             .when()
             .post("/image")
             .prettyPeek()
             .then()
             .statusCode(200)
             .extract()
             .response()
             .jsonPath()
             .getString("data.deletehash");
 }

    @AfterEach
    void tearDown() {
        given()
                .headers("Authorization", token)
                .when()
                .delete("account/{username}/image/{deleteHash}", username, uploadedImageHashCode)
                .prettyPeek()
                .then()
                .statusCode(200);
    }
    @Test
    void getAccountInfoWihtLogginTest() {
        given()
                .headers("Authorization", "Bearer 81ed217eee6d991be324edc8754a07e4ce686bb9")
                .log()
                .url()
                .log()
                .body()
                .when()
                .get("https://api.imgur.com/3/account/<username>",username )
                .prettyPeek()
                .than()
                .statusCode(200);
    }
    @Test
    void getAccountInfoWithAssertionsInGivenTest(){
        given()
                .headers("Authorization", "Bearer 81ed217eee6d991be324edc8754a07e4ce686bb9")
                .log()
                .method()
                .log()
                .url()
                .expect()
                .statusCode(200)
                .body("data.url",equalTo(username))
                .body("success",equalTo(true))
                .body("ststus",equalTo(200))
                .contentType("application/json")
                .when()
                .get("https://api.imgur.com/3/account/<username>",username )
                .prettyPeek();
    }

}
