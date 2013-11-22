package application.controller.admin.testcase {
	import application.controller.AbstractController;
	import application.model.grid.GridKeywordParameters;
	import application.model.testcase.CaseStatusesList;
	
	import flash.xml.XMLNode;
	
	import presentation.events.panels.searchpanel.SearchCompletedEvent;

	public class CaseStatusesController extends AbstractController {
		public static const CASESTATUSES_SEARCH_COMPLETED_EVENT:String = "CASESTATUSES_SEARCH_COMPLETED_EVENT";
		public static const MODEL_NAME:String = "casestatus";
		
		private var casestatus_parameters:GridKeywordParameters = new GridKeywordParameters();
		
		public function CaseStatusesController() {}
		
		override public function toGridModel(xml:XMLNode):void{
			var statuses:CaseStatusesList = getFlexXBEngineInstance().deserialize(new XML(xml), CaseStatusesList) as CaseStatusesList;
			
			dispatcher(new SearchCompletedEvent(CASESTATUSES_SEARCH_COMPLETED_EVENT, statuses.statuses, statuses.hasNext));
		}
		
		override public function get gridParameters():GridKeywordParameters {
			return casestatus_parameters;
		}
		
		override protected function getModelName():String {
			return MODEL_NAME;
		}
	}
}