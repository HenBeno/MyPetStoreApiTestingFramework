package api.tets;

import api.endpoints.UserEndPoints;
import api.payloads.UserPayload;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests extends UserEndPoints{
    UserPayload userPayload;
    Faker faker;
    public Logger logger;

    @BeforeClass
    public void setup(){
        logger = LogManager.getLogger(this.getClass());

        logger.info("Create user payload");
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
        logger.debug("idNumber: " + userPayload.getId() +
                "\nusername: " + userPayload.getUsername() +
                "\nfirstName: " + userPayload.getFirstName() +
                "\nlastName: " + userPayload.getLastName() +
                "\nemailAddress: " + userPayload.getEmail() +
                "\nphoneNumber: " + userPayload.getPhone()
        );
    }

    @Test(testName = "TC1 - Create new user", priority = 0)
    public void createNewUserTC(){
        logger.info("Create new user");
        Response res = createUser(userPayload);
        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(), 200);
        logger.info("New user created");
    }

    @Test(testName = "TC2 - Get user by Username", priority = 1)
    public void getUserTC(){
        logger.info("Get user by Username");
        Response res = getUser(userPayload.getUsername());
        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(), 200);
        logger.info("User found");
    }

    @Test(testName = "TC3 - Login", priority = 2)
    public void loginTC(){
        logger.info("Try to Login");
        Response res = login(userPayload.getUsername(), userPayload.getPassword());
        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(), 200);
        logger.info("Login successfully");
    }

    @Test(testName = "TC4 - Logout", priority = 3)
    public void logoutTC(){
        logger.info("Try to Logout");
        Response res = logout();
        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(), 200);
        logger.info("Logout successfully");
    }

    @Test(testName = "TC5 - Update user info", priority = 4)
    public void updateUserInfoTC(){
        logger.info("Create payload with new detail");
        //Save the original username to sent in put req
        String originalUserName = userPayload.getUsername();

        //Change the data you want to update
        userPayload.setEmail(faker.internet().emailAddress());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setPhone(faker.phoneNumber().phoneNumber());

        logger.info("Try to update user info");
        Response res = putUpdateUser(originalUserName, userPayload);
        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(), 200);
        logger.info("User info updated successfully");
    }

    @Test(testName = "TC6 - Delete user", priority = 5)
    public void deleteExistingUserTC(){
        logger.info("Try to delete user");
        Response res = deleteUser(userPayload.getUsername());
        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(), 200);
        logger.info("User name deleted successfully");
    }

    @Test(testName = "TC7 - Get user by Username that not exist - Negative test", priority = 6)
    public void getUserNegativeTC(){
        logger.info("Try to find user that not exits");
        Response res = getUser(userPayload.getUsername());
        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(), 404);
        logger.info("User not found - Negative tests");
    }
}
