package CommonUtils;

import TestConfig.BaseTest;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
@Component
@Getter
public class RestManager extends BaseTest {

  private String body;
  private  RestClient restClient ;
  private HashMap<String,String> header;
  private  HashMap<String,String> query;
  private String baseuri;


  public RestManager setBaseUri(String reqname, String endpoint) throws IOException {
    String domain= environment.getProperty(reqname);
    this.baseuri=domain+endpoint;
    return this;
  }
  public RestManager setHeaders(String headerName , String headerValue){
  if(this.header==null){
    this.header= new HashMap<>();
  }
    this.header.put(headerName, headerValue);
return this;
  }

  public RestManager setQueryparamers(String queryParaName , String queryParaValue){
    if(this.query==null){
      this.query= new HashMap<>();
    }
    this.query.put(queryParaName, queryParaValue);
return this;
  }
  public RestManager setBody(String resource ){
      this.body= resource;
    return this;
  }


  public RestClient build() throws IOException {
    restClient= new RestClient(this);
    return restClient;

  }

}
