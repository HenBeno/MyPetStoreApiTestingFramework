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


    ///////////////////////////////////////////////////////////
    /////////////////////// User method ///////////////////////
    ///////////////////////////////////////////////////////////

    //POST User method
    protected String postCreateListOfUsersByArray = userBasePATH + "/createWithArray";
    protected String postCreateListOfUsersByList = userBasePATH + "/createWithList";
    protected String postCreateUser = userBasePATH;

    //GET User method
    protected String getUserByUserName = userBasePATH + "/{myusername}";
    protected String getLoginIntoSystem = userBasePATH + "/login?username={myusername}&password={mypassword}";
    protected String getLogoutOfSystem = userBasePATH + "/logout";

    //PUT User method
    protected String postUpdateUser = userBasePATH + "/{myusername}";

    //DELETE User method
    protected String deleteDeleteUser = userBasePATH + "/{username}";


    ///////////////////////////////////////////////////////////
    /////////////////////// Store method ///////////////////////
    ///////////////////////////////////////////////////////////
    //POST Store method
    protected String postPlaceAnOrder = storeBasePATH + "/order";
    //GET Store method
    protected String getFindPurchasedOrderById = storeBasePATH + "/order/{orderId}";
    protected String getInventoryByStatus = storeBasePATH + "/order";
    //DELETE Store method
    protected String deleteRemoveAnOrderById = storeBasePATH + "/order/{orderId}";


    ///////////////////////////////////////////////////////////
    /////////////////////// Pet method ///////////////////////
    ///////////////////////////////////////////////////////////
    //POST Store method
    protected String postUploadImage = petBasePATH + "/{petId}/uploadImage";
    protected String postAddNewPet = petBasePATH;
    protected String postUpdatePetWithFormData = petBasePATH + "/{petId}";

    //GET Store method
    protected String getFindPetByStatus = petBasePATH + "/findByStatus";
    protected String getFindPetById = petBasePATH + "/{petId}";

    //PUT Store method
    protected String putUpdateExistingPet = petBasePATH;

    //DELETE Store method
    protected String deletePet = petBasePATH + "/{petId}";
}
