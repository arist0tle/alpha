import com.alibaba.fastjson.JSONObject;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

/**
 * Created by tanghaiyang on 2019/3/18.
 */
public class AssuredTest {

    public static void main(String[] args) {


    }


    @Test
    public void getHttpTest() {
        JSONObject vertex = new JSONObject();
        vertex.put("objectKey", "c3");
        vertex.put("schema", "company");

//        Response response = given().get("http://www.jianshu.com/users/recommended?seen_ids=&count=5&only_unfollowed=true");
        Response response = given()
                .param("data_str", vertex.toJSONString())
                .get("http://192.168.1.101:9000/query/exist");

        System.out.println("===================");
//        System.out.println(response.toString());

//        ResponseBody responseBody = response.prettyPeek();
        response.prettyPrint();
    }

    @Test
    public void getHttpTest2() {
//        Response response = given().get("http://www.jianshu.com/users/recommended?seen_ids=&count=5&only_unfollowed=true");
        Response response = given()
                .param("objectKey", "c3")
                .param("schema", "company")
                .get("http://192.168.1.101:9000/query/exist");

        System.out.println("|||||||||||||||");
//        System.out.println(response.toString());

//        ResponseBody responseBody = response.prettyPeek();
        response.prettyPrint();
    }
}
