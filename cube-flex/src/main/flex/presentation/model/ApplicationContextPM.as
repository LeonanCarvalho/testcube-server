package presentation.model {
	
	import application.ApplicationContextController;
	import application.model.grid.GridParameters;
	import application.model.parameters.DBParameter;
	import application.model.parameters.SysParameter;
	
	import flash.net.URLRequest;
	import flash.utils.Dictionary;
	
	import mx.core.LayoutDirection;
	import mx.resources.ResourceManager;
	
	import presentation.events.LoadParametersEvent;
	import presentation.events.context.PmContextEvent;

	// Class to hold Global application properties
	public class ApplicationContextPM extends BasePM implements IApplicationContextPM {
		private static const DEFAULT_LOCALE:String="en_US";
		private static const localeArray:Array=["en_US"];
		
		private var parameters:Dictionary;
		private var direction:String = LayoutDirection.LTR;
		private var numberOfRows:int = GridParameters.DEFAULT_PAGE_SIZE;		
		
		private var controller:ApplicationContextController;
	
		public function ApplicationContextPM(){			
		}
		
		[Inject]
		public function initialize(_controller:ApplicationContextController):void {
			controller = _controller;
		}
		
		[MessageHandler]
		public function postInitialize(event:LoadParametersEvent):void { 										
			if (event.getSysParameters()!=null){
				parameters = new Dictionary();
				for each (var parameter:SysParameter in event.getSysParameters().getParameters()){
					parameters[parameter.getId()] = parameter;
				}
			}
			
			var locale:String = DEFAULT_LOCALE;
			var dbParam:SysParameter = getParameter(SysParameter.DEFAULT_LOCALE);
			if (dbParam!=null){
				locale = dbParam.getValue();
			}
			ResourceManager.getInstance().localeChain = [locale];	
		}
		
		public function getParameter(parameterId:String): SysParameter{
			if (parameters!=null){
				return parameters[parameterId];
			}
			
			return null;
		}
		
		public function getURL(relativeUrl:String):URLRequest{
			return controller.getURL(relativeUrl);
		}

		public function getDirection():String{
			return direction;
		}
		
		private function setDirection(direction:String):void{
			this.direction=direction;
		}
		
		public function getOpositeDirection():String{
			return getDirection()==LayoutDirection.RTL?LayoutDirection.LTR:LayoutDirection.RTL;
		}
		
		public function geTextAlignment():String{			
			return getDirection()==LayoutDirection.RTL ? "right" : "left";
		}
		
		public function geTextAlignmentOposite():String{			
			return getDirection()==LayoutDirection.RTL ? "left" : "right";
		}
		
		public function getLocale():String{
			return getParameter(SysParameter.DEFAULT_LOCALE).getValue();
		}
		
		public function getNumberOfRows():int{
			return getParameter(DBParameter.NUMBER_OF_ROWS).getValue() != null ? parseInt(getParameter(DBParameter.NUMBER_OF_ROWS).getValue()) : numberOfRows;
		}
				
		public function getDefaultDateFormat():String{
			return getParameter(DBParameter.DEFAULT_DATE_FORMAT).getValue().toUpperCase();			
		}
	}
}