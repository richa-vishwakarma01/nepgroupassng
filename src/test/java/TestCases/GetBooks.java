package TestCases;

import CommonUtils.Endpoints;
import ExecutionReports.CustomReporter;
import TestConfig.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Base64;

@Listeners(CustomReporter.class)

public class GetBooks extends BaseTest {

    @Test(enabled=true, priority = 2)
    public void verify_getUser_workflow() throws IOException {
        String auth = Base64.getEncoder().encodeToString((environment.getProperty("bookUser")+":"+environment.getProperty("bookpwd")).getBytes());

        Response res= restManger.setBaseUri("BaseUri", Endpoints.books).setHeaders("Authorization","Basic "+auth)
                .build().getRequest();
        setResponsemap("getBooks", res);
        Assert.assertEquals(getResponseMap("getBooks").getStatusCode(),200);
        //Assert.assertEquals(getResponseMap("createUserResponse").getBody().jsonPath().get("responseCode").toString(),"201");
    }
}