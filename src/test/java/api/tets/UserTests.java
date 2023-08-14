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
    UserPayload userPayload2;
    UserPayload userPayload3;
    Faker faker;
    public Logger logger;

    @BeforeClass
    public void setup(){
        logger = LogManager.getLogger(this.getClass());

        logger.info("Create user payload");
        faker = new Faker();
        userPayload = new UserPayload(
                faker.random().nextInt(1000000),
                faker.name().username(),
                faker.name().firstName(),
                faker.name().lastName(),
                faker.internet().emailAddress(),
                faker.internet().password(),
                faker.phoneNumber().phoneNumber()
        );

        userPayload2 = new UserPayload(
                faker.random().nextInt(1000000),
                faker.name().username(),
                faker.name().firstName(),
                faker.name().lastName(),
                faker.internet().emailAddress(),
                faker.internet().password(),
                faker.phoneNumber().phoneNumber()
        );

        userPayload3 = new UserPayload(
                faker.random().nextInt(1000000),
                faker.name().username(),
                faker.name().firstName(),
                faker.name().lastName(),
                faker.internet().emailAddress(),
                faker.internet().password(),
                faker.phoneNumber().phoneNumber()
        );
        logger.debug("idNumber: " + userPayload.getId() + userPayload2.getId() + userPayload3.getId() +
                "\nusername: " + userPayload.getUsername() + userPayload2.getUsername() + userPayload3.getUsername() +
                "\nfirstName: " + userPayload.getFirstName() + userPayload2.getFirstName() + userPayload3.getFirstName() +
                "\nlastName: " + userPayload.getLastName() + userPayload2.getLastName() + userPayload3.getLastName() +
                "\nemailAddress: " + userPayload.getEmail() + userPayload2.getEmail() + userPayload3.getEmail() +
                "\nphoneNumber: " + userPayload.getPhone() + userPayload2.getPhone() + userPayload3.getPhone()
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
    public void findUserNegativeTC(){
        logger.info("Try to find user that not exits");
        Response res = getUser(userPayload.getUsername());
        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(), 404);
        logger.info("User not found - Negative tests");
    }

    @Test(testName = "TC8 - Create number of users by list payload", priority = 7)
    public void createUserByList(){

        logger.info("Create list os users");
        UserPayload[] userList = {userPayload2, userPayload3};
        Response res = postCreateUserByList(userList);
        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(), 200);

        for (UserPayload user : userList) {
            logger.info(String.format("Verify %s is create", user.getUsername()));
            Response res2 = getUser(user.getUsername());
            res2.then().log().all();
            Assert.assertEquals(res2.getStatusCode(), 200);
            logger.info(String.format("%s is created", user.getUsername()));
        }
        logger.info("All users created successfully");
    }
}
