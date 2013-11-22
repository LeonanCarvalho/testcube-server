package application.controller.testplan {
	
	import application.controller.AbstractController;
	import application.controller.testcase.TestCasesController;
	import application.model.grid.GridKeywordParameters;
	import application.model.testcase.TestCaseGridParameters;
	import application.model.testcase.TestCasesList;
	import application.model.testplan.TestPlanGridParameters;
	
	import flash.xml.XMLNode;
	
	import presentation.events.panels.searchpanel.SearchCompletedEvent;

	public class TestCasesTabController extends AbstractController {
		public static const TESTCASES_TAB_SEARCH_COMPLETED_EVENT:String = "TESTCASES_TAB_SEARCH_COMPLETED_EVENT";
		private static const MODEL_NAME:String = "testcase";
		
		private var testcases_parameters:TestCaseGridParameters = new TestCaseGridParameters();
		
		public function TestCasesTabController() {
			super();
		}
		
		override public function toGridModel(xml:XMLNode):void{
			var testCases:TestCasesList = getFlexXBEngineInstance().deserialize(new XML(xml), TestCasesList) as TestCasesList;
			
			dispatcher(new SearchCompletedEvent(TESTCASES_TAB_SEARCH_COMPLETED_EVENT, testCases.cases, testCases.hasNext, this));
		}
		
		override public function get gridParameters():GridKeywordParameters {
			return testcases_parameters;
		}
		
		override protected function getModelName():String {
			return MODEL_NAME;
		}
	}
	
}