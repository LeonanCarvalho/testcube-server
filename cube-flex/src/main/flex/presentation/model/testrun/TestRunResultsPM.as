package presentation.model.testrun {


	import application.controller.testrun.TestRunResultsController;
	import application.model.grid.GridParameters;
	import application.model.testrun.RunstatusEnum;
	import application.model.testrun.TestRun;
	import application.model.testrun.TestRunResult;
	import application.model.testrun.TestRunResultsGridParameters;
	
	import flash.events.MouseEvent;
	import flash.xml.XMLNode;
	
	import mx.collections.ArrayList;
	import mx.collections.ListCollectionView;
	import mx.core.ClassFactory;
	import mx.core.IFactory;
	
	import presentation.events.panels.searchpanel.LinkBarItemClickEvent;
	import presentation.events.panels.searchpanel.SearchCompletedEvent;
	import presentation.model.panels.CrudState;
	import presentation.model.panels.SearchablePM;
	import presentation.view.testrun.rendered.RunStatusGridRenderer;
	
	import spark.components.DataGrid;
	import spark.components.gridClasses.GridColumn;
	import spark.events.GridSelectionEvent;
	import spark.skins.spark.DefaultGridItemRenderer;
	
	import utils.grid.GridUtils;
	
	public class TestRunResultsPM extends SearchablePM {
		public static const TESTRUN_RESULT_IDLE_EVENT:String = "TESTRUN_RESULT_IDLE_EVENT";
		public static const TESTRUN_RESULT_RUNNING_EVENT:String = "TESTRUN_RESULT_RUNNING_EVENT";
		public static const TESTRUN_RESULT_PAUSED_EVENT:String = "TESTRUN_RESULT_PAUSED_EVENT";
		public static const TESTRUN_RESULT_STOP_EVENT:String = "TESTRUN_RESULT_STOP_EVENT";
		public static const TESTRUN_RESULT_PASSED_EVENT:String = "TESTRUN_RESULT_PASSED_EVENT";
		public static const TESTRUN_RESULT_FAILED_EVENT:String = "TESTRUN_RESULT_FAILED_EVENT";
		public static const TESTRUN_RESULT_BLOCKED_EVENT:String = "TESTRUN_RESULT_BLOCKED_EVENT";
		public static const TESTRUN_RESULT_ERROR_EVENT:String = "TESTRUN_RESULT_ERROR_EVENT";
		
		[Inject]
		public var testRunResultsController:TestRunResultsController;
	
		private var selectedRun:TestRun;
		private var state:CrudState = CrudState.NEWS; // Current state (NEW/UPDATE)
		
		public function TestRunResultsPM() {
			super();
			
			columns = new ArrayList();
			columns.addItem(GridUtils.getSortableColumn("id", getMessageLocale("testrun", "testruns.crud.results.grid.id"), true, true, ID_DEFAULT_WIDTH));
			columns.addItem(GridUtils.getSortableColumn("testCase.name", getMessageLocale("testrun", "testruns.crud.results.grid.name"), true, true));
			columns.addItem(GridUtils.getSortableColumn("testCase.assignee.username", getMessageLocale("testrun", "testruns.crud.results.grid.assignee"), true, true));
			columns.addItem(GridUtils.getSortableImageColumn("status", getMessageLocale("testrun", "testruns.crud.results.grid.status"), returnItemRenderer, false, false));
			
			columns.addItem(GridUtils.getSortableColumn("testCase.priorityName", getMessageLocale("testrun", "testruns.crud.results.grid.priority"), true, true));
			columns.addItem(GridUtils.getSortableColumn("testCase.categoryName", getMessageLocale("testrun", "testruns.crud.results.grid.category"), true, true));
			columns.addItem(GridUtils.getSortableColumn("testCase.bugs", getMessageLocale("testrun", "testruns.crud.results.grid.bugs"), true, true));
			
		}
		
		[Init]
		override public function postConstructor():void {
			super.controller = testRunResultsController;
		}
		
		private function returnItemRenderer(item:Object, column:GridColumn):IFactory { 
			if (item){
				return new ClassFactory(RunStatusGridRenderer);    
			}else {
				return new ClassFactory(DefaultGridItemRenderer);
			}   
		}
		
		[Bindable]
		public function get testRun():TestRun {
			return selectedRun;
		}
		
		public function set testRun(testRun:TestRun):void {
			selectedRun = testRun;
			
			refreshState();
		}
		
		public function refreshState():void {
			if (testRun == null || testRun.id == 0 ){
				this.state = CrudState.NEWS;
			}else{
				this.state = CrudState.UPDATE;
			}
		}
		
		public function isNewState():Boolean {
			return state==CrudState.NEWS;
		}
		
		public function isUpdateState():Boolean {
			return state==CrudState.UPDATE;
		}
		
		[MessageHandler(selector=TestRunResultsController.TESTRUN_RESULTS_SEARCH_COMPLETED_EVENT)]
		override public function searchCompleted(event:SearchCompletedEvent):void {
			super.searchCompleted(event);
			
			// Handle events which this object was the source initiater.
			if (isSubscribed(event, this)) {
				data = new ListCollectionView(event.getResult());
				
				// Clear selected testcase
				testCaseChnaged(null);
			}
		}
		
		
		[MessageHandler(selector=TESTRUN_RESULT_IDLE_EVENT)]
		public function setIdle(event:LinkBarItemClickEvent):void {
			if (!isSubscribedAndSelected(event) ){
				return;
			}
			
			testRunResultsController.onStatusChanged(getSelectedItem().id, RunstatusEnum.IDLE, onStatusChangedReturnMessage);
		}
		
		[MessageHandler(selector=TESTRUN_RESULT_RUNNING_EVENT)]
		public function setRunning(event:LinkBarItemClickEvent):void {
			if (!isSubscribedAndSelected(event)){
				return;
			}
			
			testRunResultsController.onStatusChanged(getSelectedItem().id, RunstatusEnum.RUNNING, onStatusChangedReturnMessage);
		}
		
		[MessageHandler(selector=TESTRUN_RESULT_PAUSED_EVENT)]
		public function setPaused(event:LinkBarItemClickEvent):void {
			if (!isSubscribedAndSelected(event)){
				return;
			}
			
			testRunResultsController.onStatusChanged(getSelectedItem().id, RunstatusEnum.PAUSED, onStatusChangedReturnMessage);
		}
		
		[MessageHandler(selector=TESTRUN_RESULT_STOP_EVENT)]
		public function setStopped(event:LinkBarItemClickEvent):void {
			if (!isSubscribedAndSelected(event)){
				return;
			}
			
			testRunResultsController.onStatusChanged(getSelectedItem().id, RunstatusEnum.STOPPED, onStatusChangedReturnMessage);
		}
		
		[MessageHandler(selector=TESTRUN_RESULT_PASSED_EVENT)]
		public function setPassed(event:LinkBarItemClickEvent):void {
			if (!isSubscribedAndSelected(event)){
				return;
			}
			
			testRunResultsController.onStatusChanged(getSelectedItem().id, RunstatusEnum.PASSED, onStatusChangedReturnMessage);
		}

		[MessageHandler(selector=TESTRUN_RESULT_FAILED_EVENT)]
		public function setFailed(event:LinkBarItemClickEvent):void {
			if (!isSubscribedAndSelected(event)){
				return;
			}
			
			testRunResultsController.onStatusChanged(getSelectedItem().id, RunstatusEnum.FAILED, onStatusChangedReturnMessage);
		}
		
		
		[MessageHandler(selector=TESTRUN_RESULT_BLOCKED_EVENT)]
		public function setBlocked(event:LinkBarItemClickEvent):void {
			if (!isSubscribedAndSelected(event)){
				return;
			}
			
			testRunResultsController.onStatusChanged(getSelectedItem().id, RunstatusEnum.BLOCKED, onStatusChangedReturnMessage);
		}
		
		[MessageHandler(selector=TESTRUN_RESULT_ERROR_EVENT)]
		public function setError(event:LinkBarItemClickEvent):void {
			if (!isSubscribedAndSelected(event)){
				return;
			}
			
			testRunResultsController.onStatusChanged(getSelectedItem().id, RunstatusEnum.ERROR, onStatusChangedReturnMessage);
		}
		
		protected function isSubscribedAndSelected(event:LinkBarItemClickEvent):Boolean {
			if (!isSubscribed(event, this)){
				return false;
			}
			
			return isGridItemSelected();
		}
		
		private function onStatusChangedReturnMessage(xml:XMLNode):void {
			if (displayReturnMessage(xml)){
				search(freeText);
			}
		}
		
		override public function getNewButton():Object {
			// Dummy unused object, just so the flex compiler will load this class definition.
			// DONOT remove this line if there are no other referances to TestRunResult class
			new TestRunResult();
			
			return null;
		}
		
		override public function getEditButton():Object {
			return null;
		}
		
		override public function getDeleteButton():Object {
			return null;
		}
		
		override protected function addGridParameters(gridParameters:GridParameters):void {
			super.addGridParameters(gridParameters);
			
			(gridParameters as TestRunResultsGridParameters).testRunId = (this.selectedRun != null && this.selectedRun.id != 0) ? String(this.selectedRun.id): null;
			(gridParameters as TestRunResultsGridParameters).testPlanId = this.selectedRun != null? String(this.selectedRun.testPlanId): null;
			(gridParameters as TestRunResultsGridParameters).subscriber = this;
		}
		
		override public function getModelName():String {
			return getMessageLocale("testrun", "testruns.crud.results.object.name");
		}
		
		override public function selectionChangeHandler(event:GridSelectionEvent):void { 
			super.selectionChangeHandler(event);
			
			// Notify selcted test case
			testCaseChnaged((getSelectedItem() as TestRunResult));
		}
		
		override public function doubleClick(event:MouseEvent, innerDataGrid:DataGrid):void {
			testCaseChnaged((getSelectedItem() as TestRunResult), true);
		}
		
		// Notify selcted test case
		private function testCaseChnaged(testRunResult:TestRunResult, selected:Boolean=false):void{
			if (testRunResult!=null && testRunResult.testCase!=null){
				// Retrive Full test case from server
				dispatcher(new TestResultSelectedEvent(this, testRunResult.testCase, selected));
			}else{
				// Clear selected testcase
				dispatcher(new TestResultSelectedEvent(this, null));
			}
		}
	}
}