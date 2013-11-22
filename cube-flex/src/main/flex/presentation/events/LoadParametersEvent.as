package presentation.events
{
	import application.model.parameters.DBParametersList;
	import application.model.parameters.SysParametersList;
	
	import flash.events.Event;

	public class LoadParametersEvent extends Event {		
		/** Event type for logout */
		public static const POST_LOAD_PARAMETERS:String = "POSTLOAD_PARAMETERS";
		
		private var _parameters:SysParametersList;
		
		public function LoadParametersEvent(parameters:SysParametersList){
			_parameters = parameters;
			super(POST_LOAD_PARAMETERS);
		}
		
		public function getSysParameters():SysParametersList {
			return _parameters;
		}
	}
}