package application.controller.testcase {
	
	import application.controller.AbstractController;
	import application.model.grid.GridKeywordParameters;
	import application.model.testcase.TestCase;
	import application.model.testcase.TestCaseGridParameters;
	import application.model.testcase.TestCasesList;
	
	import flash.xml.XMLNode;
	
	import presentation.events.panels.searchpanel.SearchCompletedEvent;

	public class TestCasesController extends AbstractController {
		public static const TESTCASES_SEARCH_COMPLETED_EVENT:String = "TESTCASES_SEARCH_COMPLETED_EVENT";
		public static const MODEL_NAME:String = "testcase";
		
		private var testcases_parameters:TestCaseGridParameters = new TestCaseGridParameters();
		
		public function TestCasesController() {}
		
		override public function toGridModel(xml:XMLNode):void{
			var testCases:TestCasesList = getFlexXBEngineInstance().deserialize(new XML(xml), TestCasesList) as TestCasesList;
			
			dispatcher(new SearchCompletedEvent(TESTCASES_SEARCH_COMPLETED_EVENT, testCases.cases, testCases.hasNext));
		}
		
		override public function get gridParameters():GridKeywordParameters {
			return testcases_parameters;
		}
		
		override protected function getModelName():String {
			return MODEL_NAME;
		}
	}
}