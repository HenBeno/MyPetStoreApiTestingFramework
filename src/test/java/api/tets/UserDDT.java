package api.tets;

import api.endpoints.UserEndPoints;
import api.payloads.UserPayload;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserDDT extends UserEndPoints {

    public Logger logger;

    @BeforeClass
    public void setup(){
        logger = LogManager.getLogger(this.getClass());
    }
    @Test(testName = "TC1 - Create new users using DataProvider", priority = 0, dataProvider = "AllUserData", dataProviderClass = DataProviders.class)
    public void createNewUserDDT(String idNumber, String username, String firstName, String lastName, String email, String password, String phoneNumber){
        logger.info("Create user payload using DDT");
        UserPayload userPayload = new UserPayload(
                Integer.parseInt(idNumber),
                username,
                firstName,
                lastName,
                email,
                password,
                phoneNumber);

        logger.info("Try to Create user [DDT]");
        //Create new user
        Response createNewUserResponse = createUser(userPayload);
        createNewUserResponse.then().log().all();
        Assert.assertEquals(createNewUserResponse.getStatusCode(), 200);
        logger.info("User created successfully [DDT]");

        logger.info("Try to find user using username [DDT]");
        //Try to find the created user
        Response getUserResponse = getUser(userPayload.getUsername());
        getUserResponse.then().log().all();
        Assert.assertEquals(getUserResponse.getStatusCode(), 200);
        logger.info("User found successfully [DDT]");

        logger.info("Try to Login [DDT]");
        //Login to the created user
        Response loginResponse = login(userPayload.getUsername(), userPayload.getPassword());
        loginResponse.then().log().all();
        Assert.assertEquals(loginResponse.getStatusCode(), 200);
        logger.info("Login successfully [DDT]");

        logger.info("Try to Logout [DDT]");
        //Logout from the created user
        Response logoutResponse = logout();
        logoutResponse.then().log().all();
        Assert.assertEquals(logoutResponse.getStatusCode(), 200);
        logger.info("Logout successfully [DDT]");
    }

    @Test(testName = "TC2 - Delete all users using DataProvider", priority = 1, dataProvider = "AllUserNamesData", dataProviderClass = DataProviders.class)
    public void deleteUsersWithDataProvider(String username){
        logger.info("Try to Delete user [DDT]");
        //Delete user by user name
        Response deleteUserResponse = deleteUser(username);
        deleteUserResponse.then().log().all();
        Assert.assertEquals(deleteUserResponse.getStatusCode(), 200);
        logger.info("User deleted successfully [DDT]");

        logger.info("Try to find not exits user [DDT]");
        //Try to find the deleted username (Expected results status code 404)
        Response getUserResponseNegative = getUser(username);
        getUserResponseNegative.then().log().all();
        Assert.assertEquals(getUserResponseNegative.getStatusCode(), 404);
        logger.info("User not found - Negative test [DDT]");
    }

}
