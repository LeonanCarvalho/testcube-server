package application.controller.admin.testcase {
	import application.controller.AbstractController;
	import application.model.grid.GridKeywordParameters;
	import application.model.testcase.CasePrioritiesList;
	
	import flash.xml.XMLNode;
	
	import presentation.events.panels.searchpanel.SearchCompletedEvent;

	public class CasePrioritiesController extends AbstractController {
		public static const CASEPRIORITIES_SEARCH_COMPLETED_EVENT:String = "CASEPRIORITIES_SEARCH_COMPLETED_EVENT";
		public static const MODEL_NAME:String = "casepriority";
		
		private var casePriority_parameters:GridKeywordParameters = new GridKeywordParameters();
		
		public function CasePrioritiesController() {}
		
		override public function toGridModel(xml:XMLNode):void{
			var priorities:CasePrioritiesList = getFlexXBEngineInstance().deserialize(new XML(xml), CasePrioritiesList) as CasePrioritiesList;
			
			dispatcher(new SearchCompletedEvent(CASEPRIORITIES_SEARCH_COMPLETED_EVENT, priorities.priorities, priorities.hasNext));
		}
		
		override public function get gridParameters():GridKeywordParameters {
			return casePriority_parameters;
		}
		
		override protected function getModelName():String {
			return MODEL_NAME;
		}
	}
}