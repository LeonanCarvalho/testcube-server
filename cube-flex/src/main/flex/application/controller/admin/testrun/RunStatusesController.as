package application.controller.admin.testrun {
	
	import application.controller.AbstractController;
	import application.model.grid.GridKeywordParameters;
	import application.model.testrun.RunStatusesList;
	
	import flash.xml.XMLNode;
	
	import presentation.events.panels.searchpanel.SearchCompletedEvent;

	public class RunStatusesController extends AbstractController {
		public static const RUNSTATUSES_SEARCH_COMPLETED_EVENT:String = "RUNSTATUSES_SEARCH_COMPLETED_EVENT";
		public static const MODEL_NAME:String = "runstatus";
		
		private var casestatus_parameters:GridKeywordParameters = new GridKeywordParameters();
		
		public function RunStatusesController() {}
		
		override public function toGridModel(xml:XMLNode):void{
			var statuses:RunStatusesList = getFlexXBEngineInstance().deserialize(new XML(xml), RunStatusesList) as RunStatusesList;
			
			dispatcher(new SearchCompletedEvent(RUNSTATUSES_SEARCH_COMPLETED_EVENT, statuses.statuses, statuses.hasNext));
		}
		
		override public function get gridParameters():GridKeywordParameters {
			return casestatus_parameters;
		}
		
		override protected function getModelName():String {
			return MODEL_NAME;
		}
	}
}