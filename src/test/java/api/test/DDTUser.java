package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import api.utilities.*;
import io.restassured.response.Response;

public class DDTUser {

	@Test(priority=1,dataProvider = "data",dataProviderClass = Dataproviders.class)
	public void setupdata(String uID,String UName,String Fname,String Lname,String email,String pword,String phno)
	{
		Logger logger;
		User userpayload;
		userpayload=new User();
		userpayload.setId(Integer.parseInt(uID));
		userpayload.setUsername(UName);
		userpayload.setFirstName(Fname);
		userpayload.setLastName(Lname);
		userpayload.setEmail(email);
		userpayload.setPassword(pword);
		userpayload.setPassword(phno);
		
		Response response=UserEndPoints.createuser(userpayload);
		Assert.assertEquals(response.getStatusCode(), 200);
		response.then().log().all();
		
		//logs
		
		logger=LogManager.getLogger(this.getClass());
	}
	@Test(priority = 3,dataProvider="UserNames",dataProviderClass = Dataproviders.class)
	public void deleteUser(String UName)
	{
		Response response=UserEndPoints.deleteuser(UName);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
