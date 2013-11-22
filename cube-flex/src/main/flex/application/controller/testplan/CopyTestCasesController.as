package application.controller.testplan {
	
	import application.controller.AbstractController;
	import application.controller.BaseAPI;
	import application.controller.testcase.TestCasesController;
	import application.model.grid.GridKeywordParameters;
	import application.model.testcase.TestCaseGridParameters;
	import application.model.testcase.TestCasesList;
	import application.model.testplan.TestPlanGridParameters;
	
	import flash.xml.XMLNode;
	
	import presentation.events.panels.searchpanel.SearchCompletedEvent;

	public class CopyTestCasesController extends AbstractController {
		public static const COPY_TESTCASES_SEARCH_COMPLETED_EVENT:String = "COPY_TESTCASES_SEARCH_COMPLETED_EVENT";
		private static const MODEL_NAME:String = "testcase";
		private static const PARAMETER_TESTCASE_ID:String = "PARAMETER_TESTCASE_ID";
		private static const PARAMETER_TESTPLAN_ID:String = "PARAMETER_TESTPLAN_ID";
	
		
		private var testcases_parameters:TestCaseGridParameters= new TestCaseGridParameters();
		
		public function CopyTestCasesController() {
			super();
		}
		
		override public function toGridModel(xml:XMLNode):void{
			var testCases:TestCasesList = getFlexXBEngineInstance().deserialize(new XML(xml), TestCasesList) as TestCasesList;
			
			dispatcher(new SearchCompletedEvent(COPY_TESTCASES_SEARCH_COMPLETED_EVENT, testCases.cases, testCases.hasNext, this));
		}
		
		override public function get gridParameters():GridKeywordParameters {
			return testcases_parameters;
		}
		
		override protected function getModelName():String {
			return MODEL_NAME;
		}
		
		public function onCopyTestCase(testcaseId:int, testplanId:int, onCopyReturnMessage:Function = null): void{
			// Callback hook for adding additional search parameters to GridParameters. 
			
			var parameters:Object = new Object();
			parameters[PARAMETER_TESTCASE_ID] = testcaseId;
			parameters[PARAMETER_TESTPLAN_ID] = testplanId;
			
			getXMLContent(getServerURL() + getCopyURL(), onCopyReturnMessage, parameters);
		}
		
		private function getCopyURL():String {
			return BaseAPI.PROTECTED_SERVICE + "/" + getModelName() + BaseAPI.GRID_COPY;
		}
		
	}
}