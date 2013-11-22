package application.controller.testrun {
	
	import application.controller.AbstractController;
	import application.controller.BaseAPI;
	import application.model.grid.GridKeywordParameters;
	import application.model.testrun.TestRunGridParameters;
	import application.model.testrun.TestRunsList;
	
	import flash.xml.XMLNode;
	
	import mx.logging.ILogger;
	
	import presentation.events.panels.searchpanel.SearchCompletedEvent;
	
	import utils.LogUtils;

	public class TestRunsController extends AbstractController {
		private static const LOG:ILogger = LogUtils.getLogger(TestRunsController);
		
		public static const TESTRUNS_SEARCH_COMPLETED_EVENT:String = "TESTRUNS_SEARCH_COMPLETED_EVENT";
		public static const MODEL_NAME:String = "testrun";
		
		private var testruns_parameters:TestRunGridParameters = new TestRunGridParameters();
		
		public function TestRunsController() {}
		
		override public function toGridModel(xml:XMLNode):void{
			var testRuns:TestRunsList = getFlexXBEngineInstance().deserialize(new XML(xml), TestRunsList) as TestRunsList;
			
			dispatcher(new SearchCompletedEvent(TESTRUNS_SEARCH_COMPLETED_EVENT, testRuns.runs, testRuns.hasNext));
		}
		
		override public function get gridParameters():GridKeywordParameters {
			return testruns_parameters;
		}
		
		override protected function getModelName():String {
			return MODEL_NAME;
		}
		
		public function onStartClick(id:int, returnFunction:Function):void {	
			LOG.info("Posting testrun Start request to server !");
			var paremeters:Object = new Object();
			
			paremeters[CRUD_PARAMETERS] = id; 
			
			getXMLContent(getServerURL() + getStartURL(), returnFunction, paremeters);	
		}
		
		public function onStopClick(id:int, returnFunction:Function):void {	
			LOG.info("Posting testrun Stop request to server !");
			var paremeters:Object = new Object();
			
			paremeters[CRUD_PARAMETERS] = id; 
			
			getXMLContent(getServerURL() + getStopURL(), returnFunction, paremeters);	
		}
		
		private function getStartURL():String {
			return BaseAPI.PROTECTED_SERVICE + "/" + getModelName() + BaseAPI.TESTRUN_START;
		}
		
		private function getStopURL():String {
			return BaseAPI.PROTECTED_SERVICE + "/" + getModelName() + BaseAPI.TESTRUN_STOP;
		}
	}
}