package application.controller.admin.testplan {
	import application.controller.AbstractController;
	import application.model.grid.GridKeywordParameters;
	import application.model.testplan.PlanTypesList;
	
	import flash.xml.XMLNode;
	
	import presentation.events.panels.searchpanel.SearchCompletedEvent;

	public class PlanTypesController extends AbstractController {
		public static const PLANTYPES_SEARCH_COMPLETED_EVENT:String = "PLANTYPES_SEARCH_COMPLETED_EVENT";
		public static const MODEL_NAME:String = "plantype";
		
		private var plantypes_parameters:GridKeywordParameters = new GridKeywordParameters();
		
		public function PlanTypesController() {}
		
		override public function toGridModel(xml:XMLNode):void{
			var planTypes:PlanTypesList = getFlexXBEngineInstance().deserialize(new XML(xml), PlanTypesList) as PlanTypesList;
			
			dispatcher(new SearchCompletedEvent(PLANTYPES_SEARCH_COMPLETED_EVENT, planTypes.types, planTypes.hasNext));
		}
		
		override public function get gridParameters():GridKeywordParameters {
			return plantypes_parameters;
		}
		
		override protected function getModelName():String {
			return MODEL_NAME;
		}
	}
}