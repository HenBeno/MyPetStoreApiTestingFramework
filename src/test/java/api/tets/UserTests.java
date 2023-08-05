package api.tets;

import api.endpoints.UserEndPoints;
import api.payloads.UserPayload;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests extends UserEndPoints{
    UserPayload userPayload;
    Faker faker;

    @BeforeClass
    public void setup(){
        faker = new Faker();
        userPayload = new UserPayload(
                faker.idNumber().hashCode(),
                faker.name().username(),
                faker.name().firstName(),
                faker.name().lastName(),
                faker.internet().emailAddress(),
                faker.internet().password(),
                faker.phoneNumber().phoneNumber()
        );
    }

    @Test(testName = "TC1 - Create new user", priority = 0)
    public void createNewUser(){
        Response res = createUser(userPayload);
        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(), 200);
    }

    @Test(testName = "TC2 - Login", priority = 1)
    public void login(){
        Response res = login(userPayload.getUsername(), userPayload.getPassword());
        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(), 200);
    }


    @Test(testName = "TC3 - Delete user", priority = 2)
    public void deleteExistingUser(){
        Response res = deleteUser(userPayload.getUsername());
        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(), 200);
    }
}
