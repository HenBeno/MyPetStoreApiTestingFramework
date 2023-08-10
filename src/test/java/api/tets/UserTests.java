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
    public void createNewUserTC(){
        Response res = createUser(userPayload);
        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(), 200);
    }

    @Test(testName = "TC2 - Get user by Username", priority = 1)
    public void getUserTC(){
        Response res = getUser(userPayload.getUsername());
        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(), 200);
    }

    @Test(testName = "TC3 - Login", priority = 2)
    public void loginTC(){
        Response res = login(userPayload.getUsername(), userPayload.getPassword());
        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(), 200);
    }

    @Test(testName = "TC4 - Logout", priority = 3)
    public void logoutTC(){
        Response res = logout();
        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(), 200);
    }

    @Test(testName = "TC5 - Update user info", priority = 4)
    public void updateUserInfoTC(){
        //Save the original username to sent in put req
        String originalUserName = userPayload.getUsername();

        //Change the data you want to update
        userPayload.setEmail(faker.internet().emailAddress());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setPhone(faker.phoneNumber().phoneNumber());

        Response res = putUpdateUser(originalUserName, userPayload);
        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(), 200);
    }

    @Test(testName = "TC6 - Delete user", priority = 5)
    public void deleteExistingUserTC(){
        Response res = deleteUser(userPayload.getUsername());
        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(), 200);
    }

    @Test(testName = "TC7 - Get user by Username that not exist - Negative test", priority = 6)
    public void getUserNegativeTC(){
        Response res = getUser(userPayload.getUsername());
        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(), 404);
    }
}
