package api.endpoints;

import api.payloads.UserPayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserEndPoints extends Routes{

    public Response createUser(UserPayload payLoad){
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .body(payLoad)
                        .when()
                        .post(postCreateUser);
        return response;
    }

    public Response deleteUser(String userName){
        Response response =
                given()
                        .accept(ContentType.JSON)
                        .pathParam("username", userName)
                        .when()
                        .delete(deleteDeleteUser);
        return response;
    }

    public Response login(String userName, String password){
        Response response =
                given()
                        .accept(ContentType.JSON)
                        .pathParam("myusername", userName)
                        .pathParam("mypassword", password)
                        .when()
                        .get(getLoginIntoSystem);
        return response;
    }

    public Response logout() {
        Response response =
                given()
                        .accept(ContentType.JSON)
                        .when()
                        .get(getLogoutOfSystem);
        return response;
    }

    public Response getUser(String userName) {
        Response response =
                given()
                        .accept(ContentType.JSON)
                        .pathParam("myusername", userName)
                        .when()
                        .get(getUserByUserName);
        return response;
    }

    public Response putUpdateUser(String userName, UserPayload payLoad) {
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .pathParam("myusername", userName)
                        .body(payLoad)
                        .when()
                        .put(postUpdateUser);
        return response;
    }
}
