package api.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import api.testcases.*;

public class UserTestDD {
	
	@Test(priority=1, dataProvider="AllData", dataProviderClass = DataProviders.class)
	public void testCreateUser(String userId, String userName, String firstName, String lastName, String email, String password, String phone) 
	{
		
		User userPayLoad = new User();
		userPayLoad.setId(Integer.parseInt(userId));
		userPayLoad.setUsername(userName);
		userPayLoad.setFirstname(firstName);
		userPayLoad.setLastname(lastName);
		userPayLoad.setEmail(email);
		userPayLoad.setPassword(password);
		userPayLoad.setPhone(phone);
		
		Response response = UserEndPoints.createUser(userPayLoad);
		//log the response
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		
		
	}// End of TestCreateUser

	@Test(priority = 2, dataProvider = "UserNameData", dataProviderClass = DataProviders.class)
	public void testDeleteUser(String userName)
	{
		System.out.println("===========Delete user===============");
		Response response = UserEndPoints.deleteUser(userName);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		System.out.println("===========Delete user===============");
	}
	
	
	
}
