package presentation.model.admin {
	import presentation.model.ApplicationContextPM;
	import presentation.model.BasePM;
	
	public class AdministratorPM extends BasePM {
		public static const EVENT_TYPE:String = "ADMIN_ICON_LIST_ITEM_CLICK";
		
		private var applicationContext:ApplicationContextPM;
		
		public function AdministratorPM(){
		
		}
		
		[Inject]
		public function initialize(applicationContext:ApplicationContextPM):void{
			this.applicationContext = applicationContext;
		}
		
		public function getDirection():String{
			return applicationContext.getDirection();
		}
	}
}