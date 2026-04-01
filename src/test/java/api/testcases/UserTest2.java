package api.testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import api.utilities.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.*;
import io.restassured.response.Response;

public class UserTest2 {
	
	Faker faker;
	User userPayLoad;
	public static Logger logger;
	
	@BeforeClass
	public void generateTestData()
	{
		faker = new Faker();
		userPayLoad = new User();
		
		userPayLoad.setId(faker.idNumber().hashCode());
		userPayLoad.setUsername(faker.name().username());
		userPayLoad.setFirstname(faker.name().firstName());
		userPayLoad.setLastname(faker.name().lastName());
		userPayLoad.setEmail(faker.internet().emailAddress());
		userPayLoad.setPassword(faker.internet().password());
		userPayLoad.setPhone(faker.phoneNumber().cellPhone());
		
		//obtain logger
		logger = LogManager.getLogger("RestAssuredAutomationFramework");
		
	}
	
	
	@Test(priority=1)
	public void testCreateUser() 
	{
		System.out.println("===========Create user===============");
		Response response = UserEndPoints2.createUser(userPayLoad);
		//log the response
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		//response.body().jsonPath().get("message");
		System.out.println(">>>>>>>> " + response.body().jsonPath().get("message"));
		
		System.out.println("===========Create user===============");
		
		//log
		logger.info("Create User Executed");
	}
	
	@Test(priority=2)
	public void testGetUser()
	{
		System.out.println("===========Read user===============");
		Response response = UserEndPoints2.getUser(userPayLoad.getUsername());
		//log the response
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("===========Read user===============");
		
		logger.info("Get User Executed");
	}
	
	@Test(priority=3)
	public void testUpdateUser()
	{
		System.out.println("===========Update user===============");
		System.out.println("Email: ====== " + userPayLoad.getEmail());

		userPayLoad.setEmail(faker.internet().emailAddress());
		System.out.println("Email: ====== " + userPayLoad.getEmail());
		Response response = UserEndPoints2.putUser(userPayLoad, userPayLoad.getEmail());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("===========Update user===============");
		
		logger.info("Update User Executed");
	}
	
	@Test(priority=4)
	public void testDeleteUser()
	{
		System.out.println("===========Delete user===============");
		Response response = UserEndPoints2.deleteUser(userPayLoad.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		System.out.println("===========Delete user===============");
		logger.info("Delete User Executed");
	}
	

}
