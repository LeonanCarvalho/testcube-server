package application.controller.panels
{
	import application.controller.AbstractController;
	
	import flash.utils.*;
	
	import mx.collections.ArrayList;

	public class TopPanelController extends AbstractController {
		[Bindable]
		public var eventList:ArrayList;		 
	
		public function TopPanelController() {
			super();			
		}
	}
}