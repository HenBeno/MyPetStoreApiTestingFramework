package api.tets;

import api.endpoints.UserEndPoints;
import api.payloads.UserPayload;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserDDT extends UserEndPoints {


    @Test(testName = "TC1 - Create new users using DataProvider", priority = 0, dataProvider = "AllUserData", dataProviderClass = DataProviders.class)
    public void createNewUserDDT(String idNumber, String username, String firstName, String lastName, String email, String password, String phoneNumber){
        UserPayload userPayload = new UserPayload(
                Integer.parseInt(idNumber),
                username,
                firstName,
                lastName,
                email,
                password,
                phoneNumber);

        Response createNewUserResponse = createUser(userPayload);
        createNewUserResponse.then().log().all();
        Assert.assertEquals(createNewUserResponse.getStatusCode(), 200);

        Response getUserResponse = getUser(userPayload.getUsername());
        getUserResponse.then().log().all();
        Assert.assertEquals(getUserResponse.getStatusCode(), 200);

        Response loginResponse = login(userPayload.getUsername(), userPayload.getPassword());
        loginResponse.then().log().all();
        Assert.assertEquals(loginResponse.getStatusCode(), 200);

        Response logoutResponse = logout();
        logoutResponse.then().log().all();
        Assert.assertEquals(logoutResponse.getStatusCode(), 200);
    }

    @Test(testName = "TC2 - Delete all users using DataProvider", priority = 1, dataProvider = "AllUserNamesData", dataProviderClass = DataProviders.class)
    public void deleteUsersWithDataProvider(String username){

        Response deleteUserResponse = deleteUser(username);
        deleteUserResponse.then().log().all();
        Assert.assertEquals(deleteUserResponse.getStatusCode(), 200);

        Response getUserResponseNegative = getUser(username);
        getUserResponseNegative.then().log().all();
        Assert.assertEquals(getUserResponseNegative.getStatusCode(), 404);
    }

}
