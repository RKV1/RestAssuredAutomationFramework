package api.endpoints;
import api.payload.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;


public class UserEndPoints2 {
	
	
		static ResourceBundle routes = ResourceBundle.getBundle("Routes");
		
	
	
	public static Response createUser(User payload) {
		
		Response response = given()
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			.body(payload)
			
		.when()
		.post(routes.getString("post_url"));
		
		return response;
	} // End of createUser
	
	public static Response getUser(String userName) {
		Response response = given()
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			.pathParam("username", userName)
			
		.when()
		.get(routes.getString("get_url"));
		
		return response;
	}// End of getUser
	
	public static Response putUser(User payload, String userName) {
		Response response = given()
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			.body(payload)
			.pathParam("username", userName)
			
		.when()
		.put(routes.getString("put_url"));
		
		return response;
	}// End of putUser
	
	public static Response deleteUser(String userName) {
		Response response = given()
			.accept(ContentType.JSON)
			.pathParam("username", userName)
			
		.when()
		.delete(routes.getString("del_url"));
		
		return response;
	}

}
