package application.controller.testrun {
	
	import application.controller.AbstractController;
	import application.controller.BaseAPI;
	import application.model.grid.GridKeywordParameters;
	import application.model.testrun.RunstatusEnum;
	import application.model.testrun.TestRunResultsGridParameters;
	import application.model.testrun.TestRunResultsList;
	
	import flash.xml.XMLNode;
	
	import mx.logging.ILogger;
	
	import presentation.events.panels.searchpanel.SearchCompletedEvent;
	
	import utils.ComboBoxUtils;
	import utils.LogUtils;

	public class TestRunResultsController extends AbstractController {
		private static const LOG:ILogger = LogUtils.getLogger(TestRunsController);
		
		public static const TESTRUN_RESULTS_SEARCH_COMPLETED_EVENT:String = "TESTRUN_RESULTS_SEARCH_COMPLETED_EVENT";
		private static const RUN_STATUS:String = "RUN_STATUS";
		public static const MODEL_NAME:String = "runresult";
		
		private var runresults_parameters:TestRunResultsGridParameters = new TestRunResultsGridParameters(ComboBoxUtils.COMBOBOX_LIMIT);
		
		public function TestRunResultsController() {}
		
		override public function toGridModel(xml:XMLNode):void{
			var results:TestRunResultsList = getFlexXBEngineInstance().deserialize(new XML(xml), TestRunResultsList) as TestRunResultsList;
			
			dispatcher(new SearchCompletedEvent(TESTRUN_RESULTS_SEARCH_COMPLETED_EVENT, results.results, results.hasNext, runresults_parameters.subscriber));
		}
		
		override public function get gridParameters():GridKeywordParameters {
			return runresults_parameters;
		}
		
		override protected function getModelName():String {
			return MODEL_NAME;
		}
		
		public function onStatusChanged(id:int, runStatusEnum:RunstatusEnum, returnFunction:Function):void {	
			LOG.info("Posting testrun-result change status request to server !");
			var paremeters:Object = new Object();
			
			paremeters[CRUD_PARAMETERS] = id; 
			paremeters[RUN_STATUS] = runStatusEnum.name;
			
			getXMLContent(getServerURL() + getStatusURL(), returnFunction, paremeters);	
		}
		
		public function getStatusByDate(returnFunction:Function):void {
			var paremeters:Object = new Object();
			
			getXMLContent(getServerURL() + getStatusByDateURL(), returnFunction, paremeters);
		}
		
		private function getStatusURL():String {
			return BaseAPI.PROTECTED_SERVICE + "/" + getModelName() + BaseAPI.TESTRUN_RESULT_STATUS;
		}
		
		private function getStatusByDateURL():String {
			return BaseAPI.PROTECTED_SERVICE + "/" + getModelName() + BaseAPI.TESTRUN_STATUS_BYDATE;
		}
		
	}
}