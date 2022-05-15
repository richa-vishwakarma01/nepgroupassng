package TestCases;

import CommonUtils.Endpoints;
import Models.Books;
import Models.CollectionOfIsbn;
import TestConfig.BaseTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;

public class Verify_Add_Books  extends BaseTest {


    @Test(enabled=true, priority = 3)
    public void add_book_to_user() throws IOException {
        String bookID = getResponseMap("getBooks").getBody().jsonPath().get("books[1].isbn");
        String userID =getResponseMap("createUserResponse").getBody().jsonPath().get("userId");
        String auth = Base64.getEncoder().encodeToString((environment.getProperty("bookUser")+":"+environment.getProperty("bookpwd")).getBytes());
        ArrayList<CollectionOfIsbn> collectionOfIsbns=new ArrayList<CollectionOfIsbn>();
        collectionOfIsbns.add( CollectionOfIsbn.builder().isbn(bookID).build());
        Books body = Books.builder().userId(userID).collectionOfIsbns(collectionOfIsbns).build();
        ObjectMapper mapper = new ObjectMapper();
        String bookPayload = mapper.writeValueAsString(body);

        Response res= restManger.setBaseUri("BaseUri", Endpoints.books).setHeaders("Authorization","Basic "+auth).setHeaders("Content-Type","application/json").
                setBody(bookPayload).build().postRequest();
        setResponsemap("addBook", res);
        res.getBody().prettyPrint();
        Assert.assertEquals(getResponseMap("addBook").getStatusCode(),201);


    }



}
