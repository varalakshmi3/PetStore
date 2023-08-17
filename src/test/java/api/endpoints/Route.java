package api.endpoints;
/*
 * 
 create user(Post):https://petstore.swagger.io/v2/user
 update user(Post):https://petstore.swagger.io/v2/user/{username}
 get user(Get):https://petstore.swagger.io/v2/user/{username}
 delete usesr (Delete):https://petstore.swagger.io/v2/user/{username}



 */
public class Route {
	
	public static String base_url="https://petstore.swagger.io/v2/";
	
	//user model
	
	public static String post_url=base_url+"user";
	public static String get_url=base_url+"user/{username}";
	public static String update_url=base_url+"user/{username}";
	public static String delete_url=base_url+"user/{username}";
	
	

}
