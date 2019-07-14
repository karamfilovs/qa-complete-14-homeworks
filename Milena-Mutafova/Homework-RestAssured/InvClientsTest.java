import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class InvClientsTest {
    private static final String USERNAME = "milena.mutafova@gmail.com";
    private static final String PASSWORD = "123456";
    private static final String CLIENTS_RESOURCE = "/client";
    private static final Gson GSON = new Gson();
    private static Response currentResponse;

    static {
        //Base configuration of RestAssured client
        RestAssured.baseURI = "https://milena-mutafova.inv.bg";
        RestAssured.basePath = "RESTapi";
        RestAssured.authentication = RestAssured
                .preemptive()
                .basic(USERNAME, PASSWORD);
    }

    public Response createClientAccount(ClientAccount clientAccount) {
        //Execute POST request to create client account
        Response response = RestAssured.given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(GSON.toJson(clientAccount))
                .when()
                .post(CLIENTS_RESOURCE);
        //Printing the current Response
        response.prettyPrint();
        return response;

    }

    public Response updateClientAccount(String id, ClientAccount clientAccount) {
        //Execute PUT request to update client account
        Response response = RestAssured.given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(GSON.toJson(clientAccount))
                .when()
                .put(CLIENTS_RESOURCE + "/" + id);
        //Printing the current Response
        response.prettyPrint();
        return response;
    }

    public Response deleteClientAccount(String id) {
        //Execute DELETE request to delete a client account
        Response response = RestAssured.given()
                .log().all()
                .when()
                .delete(CLIENTS_RESOURCE + "/" + id);
        //Printing the current Response
        response.prettyPrint();
        return response;
    }

    public Response getClients() {
        //Execute GET request to retrieve all clients
        Response response = RestAssured.given()
                .log().all()
                .when()
                .get(CLIENTS_RESOURCE + "s");
        //Printing the current Response
        response.prettyPrint();
        return response;
    }

    public Response getClient(String id){
        //Execute GET request to retrieve the updated client
        Response response = RestAssured.given()
                .log().all()
                .when()
                .get(CLIENTS_RESOURCE + "/" + id);
        //Printing the current Response
        response.prettyPrint();
        return response;
    }

    @Test
    @Tag("positive")
    @DisplayName("Can get all clients")
    public void canGetAllClients() {
        currentResponse = getClients();
        Assertions.assertEquals(200, currentResponse.getStatusCode());
    }

    @Test
    @Tag("positive")
    @DisplayName("Can create new client account with all mandatory fields")
    public void canCreateClientAccountWithMandatoryFields() {
        ClientAccount clientAccount = new ClientAccount();
        clientAccount.setFirm_name("Fairy");
        clientAccount.setFirm_addr("General Stoletov 185");
        clientAccount.setFirm_mol("Milena Mutafova");
        clientAccount.setFirm_town("Stara Zagora");
        clientAccount.setFirm_is_reg_vat(false);
        currentResponse = createClientAccount(clientAccount);
        Assertions.assertEquals(200, currentResponse.getStatusCode());
    }

    @Test
    @Tag("positive")
    @DisplayName("Can create and delete client account")
    public void canCreateAndDeleteClientAccount() {
        //Create client account
        ClientAccount clientAccount = new ClientAccount();
        clientAccount.setFirm_name("Fairy");
        clientAccount.setFirm_addr("General Stoletov 185");
        clientAccount.setFirm_mol("Milena Mutafova");
        clientAccount.setFirm_town("Stara Zagora");
        clientAccount.setFirm_is_reg_vat(false);
        currentResponse = createClientAccount(clientAccount);
        Assertions.assertEquals(200, currentResponse.getStatusCode());
        //Store client account
        String id = JsonPath
                .from(currentResponse.getBody().asString())
                .getString("success.id");
        //Update client account
        clientAccount.setFirm_name("This is updated name");
        currentResponse = updateClientAccount(id, clientAccount);
        //Assertions.assertEquals(200, currentResponse.getStatusCode());
        getClient(id);
        //Delete client account
        currentResponse = deleteClientAccount(id);
        Assertions.assertEquals(200, currentResponse.getStatusCode());
    }

}
