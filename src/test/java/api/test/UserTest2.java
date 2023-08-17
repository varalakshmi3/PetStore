package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payloads.User;
import io.restassured.response.Response;

public class UserTest2{
	
	public Faker faker;
	public User userPayload;
	public Logger logger;

	@BeforeClass
	public void setupdata()
	{
	
		faker=new Faker();
		userPayload=new User();
		userPayload.setId(faker.number().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		//userPayload.setUserStatus(0);	
		
		//logs
		
		logger=LogManager.getLogger(this.getClass());

		
	}
	@Test(priority = 1)
	public void postUser()
	{
		logger.info("***********create user details***********");
	Response response=UserEndPoints2.createuser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*************user details are created*************");
	}
	
	@Test(priority =2)
	public void getUserByName()
	{
		logger.info("*************get the user details****************");
		Response response=UserEndPoints2.readuser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		response.then().log().all();
	}
	@Test(priority=3)
	public void updateUserByName()
	{
		logger.info("**********update user details************");
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		Response response=UserEndPoints2.updateuser(userPayload, this.userPayload.getUsername());
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//read values after update
		Response response1=UserEndPoints.readuser(this.userPayload.getUsername());
		response1.then().log().all();
		Assert.assertEquals(response1.getStatusCode(), 200);	
	}
	@Test(priority = 4)
	public void deleteUserByName()
	{
		logger.info("****************delete the user details******************");
		Response response=UserEndPoints2.deleteuser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}

