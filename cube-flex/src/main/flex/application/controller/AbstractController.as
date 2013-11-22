package application.controller
{
	import application.controller.http.HttpServiceWrapper;
	
	import flash.net.*;
	
	import mx.logging.ILogger;
	import mx.rpc.events.FaultEvent;
	
	import presentation.model.popup.ErrorMessage;
	import presentation.view.messages.Messages;
	
	import utils.LogUtils;

	public class AbstractController extends GridController {
		private static const LOG:ILogger = LogUtils.getLogger(AbstractController);
		public static const DEBUGIP:String = CONFIG::DEBUGIP;
		public static const CONTEXT_ROOT:String = "cube-web";
		
		public function AbstractController(){
			super(CONTEXT_ROOT, DEBUGIP);
		}	
		
		/********************************************************************************************************
		 *  MAIN ERROR CALL BACK, THROWS TO LOGIN IF CONNECTION IS BROKEN
		 *******************************************************************************************************/
		// TODO - extract this method to util class
		override protected function onErrorCallBack(event:FaultEvent):void {	
			var message:String = null;
			
			//log out if the message is for db down
			var ur:URLRequest;
			if(event.statusCode == HttpServiceWrapper.SC_SERVICE_UNAVAILABLE) {
				trace("Error: Server comunication error, redirecting to login page.");
				ur= new URLRequest(getServerURL());
				navigateToURL(ur, "_self");
				return;
			}
			
			if(event.statusCode == HttpServiceWrapper.SC_INTERNAL_SERVER_ERROR) {
				trace("Error: Server internal error !!!");
				message = getMessageLocale("admin","admin.error.internal");
				Messages.showError(new ErrorMessage(getMessageLocale("admin","admin.error.internal.title"), message), null);
				return;
			}
			
			// In case of redirect, httpService doesn't trace it but rather gets the HTML
			// trying to parse it as XML.
			if (event.message.body.toString().indexOf("j_spring_security_check") > -1){					
				trace("Error: Server comunication error, redirecting to login page.");
				ur= new URLRequest(getServerURL());
				navigateToURL(ur, "_self");
				return;	
			}
			
			message = getMessageLocale("admin","admin.error.connection");
			Messages.showError(new ErrorMessage(getMessageLocale("admin","admin.error.connection.title"), message), null);
		}
		
		override protected function getModelName():String {
			throw new Error("AbstractCrudController - getModelName must be override by derived class.");
		}
	}
}