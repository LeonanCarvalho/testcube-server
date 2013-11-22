package application
{
	import application.controller.AbstractController;
	import application.model.parameters.DBParameter;
	import application.model.parameters.SysParameter;
	import application.model.parameters.SysParametersList;
	import application.model.security.IUser;
	import application.model.users.User;
	
	import flash.net.URLRequest;
	import flash.xml.XMLNode;
	
	import mx.logging.ILogger;
	
	import presentation.events.LoadParametersEvent;
	import presentation.events.context.PmContextEvent;
	import presentation.model.context.PmContext;
	
	import utils.LogUtils;
	
	public class ApplicationContextController extends AbstractController {	
		private static const LOG:ILogger = LogUtils.getLogger(ApplicationContextController);
		
		private var parameters:SysParametersList;
		
		public function ApplicationContextController() {	
			
		}
		
		/********************************************************************************************************
		 *  INIT THE APPLICATION LOCALE
		 *******************************************************************************************************/
		[Init]
		public function initialize():void {
			getCurrentUser();
			loadSysParameters();
		} 
	
		/********************************************************************************************************
		 *  CALL TO A LIST OF PARAMETERS FROM SERVICE
		 *******************************************************************************************************/
		public function loadSysParameters():void{
			if (parameters == null){
				LOG.info("Sending System parameters request to server !!!");
				var paremeters:Object = new Object();
				paremeters["SYS_PARAMETERS"] = "[" + DBParameter.NUMBER_OF_ROWS + "," + SysParameter.DEFAULT_LOCALE + "," 
					+ DBParameter.DEFAULT_DATE_FORMAT + "]";
				
				getXMLContent(getServerURL() + API.GET_PARAMETERS, toModel, paremeters);
			}
		} 
		
		/*********************************************************************************************************
		 * After loading the application parameters we initialize the application and add the Main Panel to the app objects
		 ********************************************************************************************************/
		public function toModel(xml:XMLNode):void{
			parameters = deserlize(new XML(xml), SysParametersList) as SysParametersList;	
			dispatcher(new LoadParametersEvent(parameters));
		}	
		
		/*******************************************
		 * USERNAME FOR LOGOUT BUTTON  
		 *******************************************/
		public function getCurrentUser():void{
			getXMLContent(getServerURL() + API.USER_GET_CURRENT, toUserModel);
		}

		public function toUserModel(xmlUser:XMLNode):void{
			var currentUser:User = deserlize(new XML(xmlUser), User) as User;	
			dispatcher(new PmContextEvent(newPmContext(currentUser)));
		}
		
		public function getURL(relativeUrl:String):URLRequest{
			return new URLRequest(getServerURL() + relativeUrl);
		}
		
		private function newPmContext(user:IUser):PmContext{
			return new PmContext(user);
		}
	}
}