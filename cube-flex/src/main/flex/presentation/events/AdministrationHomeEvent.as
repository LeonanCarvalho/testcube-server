package presentation.events {
	import flash.events.Event;

	public class AdministrationHomeEvent extends Event {		
		public static const ADMINISTRATION_HOME_EVENT:String = "ADMINISTRATION_HOME_EVENT";
	
		public function AdministrationHomeEvent() {
			super(ADMINISTRATION_HOME_EVENT, true, false);
		}
	}
}