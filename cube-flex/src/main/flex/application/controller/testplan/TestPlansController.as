package application.controller.testplan {
	
	import application.controller.AbstractController;
	import application.model.grid.GridKeywordParameters;
	import application.model.testplan.TestPlanGridParameters;
	import application.model.testplan.TestPlansList;
	
	import flash.xml.XMLNode;
	
	import presentation.events.panels.searchpanel.SearchCompletedEvent;

	public class TestPlansController extends AbstractController {
		public static const TESTPLANS_SEARCH_COMPLETED_EVENT:String = "TESTPLANS_SEARCH_COMPLETED_EVENT";
		private static const MODEL_NAME:String = "testplan";
		
		private var testplans_parameters:TestPlanGridParameters = new TestPlanGridParameters();
		
		public function TestPlansController() {}
		
		override public function toGridModel(xml:XMLNode):void{
			var testPlans:TestPlansList = getFlexXBEngineInstance().deserialize(new XML(xml), TestPlansList) as TestPlansList;
			
			dispatcher(new SearchCompletedEvent(TESTPLANS_SEARCH_COMPLETED_EVENT, testPlans.plans, testPlans.hasNext));
		}
		
		override public function get gridParameters():GridKeywordParameters {
			return testplans_parameters;
		}
		
		override protected function getModelName():String {
			return MODEL_NAME;
		}
	}
}