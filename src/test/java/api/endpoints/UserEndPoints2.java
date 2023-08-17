package api.endpoints;
import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;
import java.util.*;
import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
//create methods for create,retrieve,update,delete
public class UserEndPoints2 {
	
	
	static ResourceBundle getURL()
	{
		ResourceBundle routes=ResourceBundle.getBundle("routes");
		return routes;
	}
	
	public static Response createuser(User payload)
	{
		String createuser=getURL().getString("create_url");
		Response response =given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		
		.when()
		.post(createuser);
		return response;
	}
	
	public static Response readuser(String userName)
	{
		String readuser=getURL().getString("get_url");
		Response response=given()
		.pathParam("username", userName)
		
		.when()
		.get(readuser);
		return response;

	}
	public static Response updateuser(User payload,String userName)
	{
		String update=getURL().getString("update_url");
		Response response =given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.pathParam("username",userName)
		
		.when()
		.put(update);
		return response;
	}
	
	public static Response deleteuser(String userName)
	{
		String delete=getURL().getString("delete_url");
		Response response =given()
		.pathParam("username", userName)
		
		.when()
		.delete(delete);
		return response;
	}


	

}
