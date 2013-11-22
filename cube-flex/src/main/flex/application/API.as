package application {
	import application.controller.BaseAPI;
	
	public class API extends BaseAPI{
		
		//Top panel
		public static const USER_LOGOUT:String = PROTECTED_SERVICE + "/user/logout";	
		public static const USER_GET_CURRENT:String = PROTECTED_SERVICE + "/user/get";
		
		public static const GET_PARAMETERS:String = PROTECTED_SERVICE + "/parameters/get";
	}
}