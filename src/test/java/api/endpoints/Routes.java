package api.endpoints;

public class Routes {

    //Base URL
    private final String BaseURL = "https://petstore.swagger.io/v2/";

    //User Base PATH
    private final String userBasePATH = BaseURL + "user";

    //Store Base PATH
    private final String storeBasePATH = BaseURL + "store";;

    //Pet Base PATH
    private final String petBasePATH = BaseURL + "pet";;


    //POST User method
    protected String postCreateListOfUsersByArray = userBasePATH + "/createWithArray";
    protected String postCreateListOfUsersByList = userBasePATH + "/createWithList";

    //GET User method
    protected String getUserByUserName = userBasePATH + "/{username}";
    protected String getLoginIntoSystem = userBasePATH + "/login?username={myusername}&password={mypassword}";
    protected String getLogoutOfSystem = userBasePATH + "/logout";

    //PUT User method
    protected String postCreateUser = userBasePATH;

    //DELETE User method
    protected String deleteDeleteUser = userBasePATH + "/{username}";

}
