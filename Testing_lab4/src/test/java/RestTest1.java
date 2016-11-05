import com.jayway.restassured.RestAssured;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

/**
 * Created by DaryaKolyadko on 05.11.2016.
 */
public class RestTest1 extends RestAssured {
    @DataProvider
    public Object[][] dataMD5() {
        return new Object[][]{
                {"lol", DigestUtils.md5Hex("lol")},
                {"ahaha", DigestUtils.md5Hex("ahaha")},
                {"12345678", DigestUtils.md5Hex("12345678")}
        };
    }

    @Test(dataProvider = "dataMD5")
    public void testGETMD5JSON(String data, String expected) {
        get("http://md5.jsontest.com/?text=" + data).then().assertThat().body("md5", equalTo(expected));
    }

    @Test
    public void testGET404() {
        given().when().get("http://github.com/999/vjfiukvhy")
                .then().statusCode(404);
    }

    @Test
    public void testPOSTJsonPlaceholder() {
        JSONObject myJson = new JSONObject();
        myJson.put("title", "lalka");
        myJson.put("body", "ffffffff");
        myJson.put("userId", 7);
        given().body(myJson.toJSONString()).when().post("http://jsonplaceholder.typicode.com/posts")
                .then().statusCode(201);
    }

    @Test
    public void testDELETEJsonPlaceholder() {
        given().pathParam("id", 2)
                .when().delete("http://jsonplaceholder.typicode.com/posts/{id}")
                .then().statusCode(200);
    }

    @Test
    public void testPUTJsonPlaceholder() {
        JSONObject myJson = new JSONObject();
        myJson.put("title", "lalka");
        myJson.put("body", "ffffffff");
        myJson.put("userId", 7);
        given().pathParam("id", 2).basePath(myJson.toJSONString())
                .when().put("http://jsonplaceholder.typicode.com/posts/{id}")
                .then().statusCode(200);
    }


    @Test
    public void testPATCHJsonPlaceholder() {
        JSONObject myJson = new JSONObject();
        myJson.put("title", "lol");
        myJson.put("body", "alalala");
        myJson.put("userId", 8);
        given().pathParam("id", 1).basePath(myJson.toJSONString())
                .when().patch("http://jsonplaceholder.typicode.com/posts/{id}")
                .then().statusCode(200);
    }
}