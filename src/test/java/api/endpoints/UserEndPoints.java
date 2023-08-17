package api.endpoints;
import static io.restassured.RestAssured.*;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
//create methods for create,retrieve,update,delete
public class UserEndPoints {
	
	public static Response createuser(User payload)
	{
		Response response =given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		
		.when()
		.post(Route.post_url);
		return response;
	}
	
	public static Response readuser(String userName)
	{
		Response response=given()
		.pathParam("username", userName)
		
		.when()
		.get(Route.get_url);
		return response;

	}
	public static Response updateuser(User payload,String userName)
	{
		Response response =given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.pathParam("username",userName)
		
		.when()
		.put(Route.update_url);
		return response;
	}
	
	public static Response deleteuser(String userName)
	{
		Response response =given()
		.pathParam("username", userName)
		
		.when()
		.delete(Route.delete_url);
		return response;
	}


	

}
