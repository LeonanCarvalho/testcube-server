package presentation.model.testplan {


	import application.controller.testplan.TestCasesTabController;
	import application.model.DomainObject;
	import application.model.grid.GridParameters;
	import application.model.testcase.TestCaseGridParameters;
	
	import mx.collections.ArrayList;
	import mx.collections.ListCollectionView;
	
	import presentation.events.panels.searchpanel.LinkBarItemClickEvent;
	import presentation.events.panels.searchpanel.SearchCompletedEvent;
	import presentation.events.panels.searchpanel.SearchRefreshEvent;
	import presentation.model.grid.PagingEvent;
	import presentation.model.panels.SearchablePM;
	import presentation.view.poup.PopUpManagerWrapper;
	import presentation.view.testplan.CopyTestCases;
	
	import utils.grid.GridUtils;
	
	public class TestCasesTabPM extends SearchablePM {
		private static const TESTCASES_COPY_EVENT:String = "TESTCASES_COPY_EVENT";
		
		[Embed("/images/testcase/testcases-16.png")] 
		private static const NEW_TESTCASE:Class;
		
		[Inject]
		public var testCasesTabController:TestCasesTabController;
		[Inject]
		public var crudTestPlanPM:CrudTestPlanPM;
		
		
		public function TestCasesTabPM() {
			super();
			
			columns = new ArrayList();
			columns.addItem(GridUtils.getSortableColumn("id", getMessageLocale("testcase", "testcases.grid.id"), true, true, ID_DEFAULT_WIDTH));
			columns.addItem(GridUtils.getSortableColumn("name", getMessageLocale("testcase", "testcases.grid.name"), true, true));						
			columns.addItem(GridUtils.getSortableColumn("description", getMessageLocale("testcase", "testcases.grid.description"), true, true));
			
			columns.addItem(GridUtils.getSortableColumn("categoryName", getMessageLocale("testcase", "testcases.grid.categoryName"), true, true));
			columns.addItem(GridUtils.getColumn("createdBy", getMessageLocale("testcase", "testcases.grid.createdBy"), true));
			columns.addItem(GridUtils.getColumn("assignee.username", getMessageLocale("testcase", "testcases.grid.assignee"), true));
			columns.addItem(GridUtils.getColumn("testPlanName", getMessageLocale("testcase", "testcases.grid.testplan"), true));
		}
		
		[Init]
		override public function postConstructor():void {
			super.controller = testCasesTabController;
		}
		
		[MessageHandler(selector=TESTCASES_TAB_SEARCH_COMPLETED_EVENT)]
		override public function searchCompleted(event:SearchCompletedEvent):void {
			// Handle events Subscribed using private controller.
			if (isSubscribed(event, testCasesTabController)){
				super.searchCompleted(event);
			
				data = new ListCollectionView(event.getResult());
			}
		}
		
		override public function getNewButton():Object {
			var newButton:Object = new Object();
			newButton.label = getMessageLocale("testplan", "testplans.crud.testcases.linkbar.new");
			newButton.icon = NEW_TESTCASE;
			newButton.eventType = TESTCASES_COPY_EVENT;
			newButton.subscriber = this;
			return newButton;
		}
		
		[MessageHandler(selector=TESTCASES_COPY_EVENT)]
		public function copyTestCases(event:LinkBarItemClickEvent):void {
			// Handle events only from this PM
			if (isSubscribed(event, this)){
				openPopup(new CopyTestCases());
			}
		}
		
		override public function getEditButton():Object {
			return null;
		}
		
		override public function getDeleteButton():Object {
			return null;
		}
		
		override public function getNewDomainObject():DomainObject{
			return null;
		}
		
		
		[MessageHandler(selector=TestCasesTabController.TESTCASES_TAB_SEARCH_COMPLETED_EVENT)]
		override public function onSearchRefreshEvent(event:SearchRefreshEvent):void {
			search(freeText);
		}
		
		// Overriding this method in order to cancel search event on NEW state
		public override function search(freeText:String, pagingEvent:PagingEvent=null):void {
			if (crudTestPlanPM.isUpdateState())
				super.search(freeText, pagingEvent);
		}
		
		
		override protected function addGridParameters(gridParam:GridParameters):void {
			super.addGridParameters(gridParam);
			
			var testCaseGridParameters:TestCaseGridParameters = gridParam as TestCaseGridParameters;
			if (this.getTestPlanId()!=0)
				testCaseGridParameters.testPlanId = this.getTestPlanId().toString();
		}
		
		override public function getModelName():String {
			return getMessageLocale("testcase", "testcases.crud.object.name");
		}
		
		public function getTestPlanId():int{
			return crudTestPlanPM!=null && crudTestPlanPM.getDomainObject()!=null ? crudTestPlanPM.getDomainObject().id : 0;
		}

	}
}