package TestCases;

import CommonUtils.Endpoints;
import CommonUtils.JSONUtils;
import Models.User;
import TestConfig.BaseTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.restassured.response.Response;
import org.springframework.core.env.Environment;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Base64;

public class createUser  extends BaseTest {

    @Test(enabled=true, priority = 0)
    public void verify_createUser_workflow( ) throws IOException {
        User body = User.builder().userName(environment.getProperty("bookUser")).password(environment.getProperty("bookpwd")).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonbody = mapper.writeValueAsString(body);

        String auth = Base64.getEncoder().encodeToString((environment.getProperty("bookUser")+":"+environment.getProperty("pwd")).getBytes());
        Response res= restManger.setBaseUri("BaseUri", Endpoints.user).setHeaders("Authorization","Basic "+auth).setHeaders("Content-Type","application/json")
                .setBody(jsonbody).
              build().postRequest();
        setResponsemap("createUserResponse", res);
        res.getBody().prettyPrint();
        Assert.assertEquals(getResponseMap("createUserResponse").getStatusCode(),201);

//        Assert.assertEquals(getResponseMap("createUserResponse").getBody().jsonPath().get("responseCode").toString(),"201");
    }
}
