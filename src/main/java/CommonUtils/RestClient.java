package CommonUtils;

import TestConfig.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.stereotype.Component;

@Component
public class RestClient extends BaseTest {

    RequestSpecification request;

    public RestClient(RestManager restManager) {
        request = RestAssured.given();
        this.request.baseUri(restManager.getBaseuri())  ;
       if(restManager.getHeader()!=null) this.request.headers(restManager.getHeader())  ;
       if(restManager.getQuery()!=null) this.request.queryParams(restManager.getQuery())  ;
       if(restManager.getBody()!=null) this.request.body(restManager.getBody())  ;

    }

    public Response getRequest() {
        return this.request.get();

    }
    public Response postRequest() {
        return this.request.post();

    }

    public Response putRequest() {
        return this.request.put();

    }


    public Response patchRequest() {
        return this.request.patch();

    }

    public Response deleteRequest() {
        return this.request.delete();

    }
}
