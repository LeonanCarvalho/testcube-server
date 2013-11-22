package presentation.model.testplan {
	import application.controller.testcase.TestCasesController;
	import application.controller.testplan.CopyTestCasesController;
	import application.model.grid.GridParameters;
	
	import flash.xml.XMLNode;
	
	import mx.collections.ArrayList;
	import mx.collections.ListCollectionView;
	
	import presentation.events.panels.searchpanel.LinkBarItemClickEvent;
	import presentation.events.panels.searchpanel.SearchCompletedEvent;
	import presentation.events.panels.searchpanel.SearchRefreshEvent;
	import presentation.model.panels.SearchablePM;
	import presentation.model.popup.QuestionMessage;
	import presentation.model.testcase.CrudTestCasePM;
	import presentation.view.messages.Messages;
	
	import utils.grid.GridUtils;

	public class CopyTestCasesPM extends SearchablePM {	
		private static const TESTCASE_COPY_EVENT:String = "TESTCASE_COPY_EVENT";
		
		[Inject]
		public var copyTestCasesController:CopyTestCasesController;
		[Inject]
		public var crudTestPlanPM:CrudTestPlanPM;
		
		[Embed("/images/testplan/testcases/copy-16.png")] 
		private static const COPY_TESTCASE:Class;

		public function CopyTestCasesPM() {
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
			super.controller = copyTestCasesController;
		}
		
		[MessageHandler(selector=CopyTestCasesController.COPY_TESTCASES_SEARCH_COMPLETED_EVENT)]
		override public function searchCompleted(event:SearchCompletedEvent):void {
			super.searchCompleted(event);
			
			data = new ListCollectionView(event.getResult());
		}
		
		override public function getNewButton():Object {
			return null;
		}
		
		override public function getDeleteButton():Object {
			return null;
		}
		
		override public function getEditButton():Object {
			var newButton:Object = new Object();
			newButton.label = getMessageLocale("testplan", "testplans.crud.testcases.linkbar.copy");
			newButton.icon = COPY_TESTCASE;
			newButton.eventType = TESTCASE_COPY_EVENT;
			newButton.subscriber = this;
			return newButton;
		}
		
		[MessageHandler(selector=TESTCASE_COPY_EVENT)]
		public function editPlan(event:LinkBarItemClickEvent):void {
			// Handle events only from this PM
			if (isSubscribed(event, this)){
				if (!isGridItemSelected()){
					return;
				}
				
				Messages.showQuestion(
					new QuestionMessage(getMessageLocale("testplan", "testplans.crud.testcases.copy.title"),
						getMessageLocale("testplan", "testplans.crud.testcases.copy.confirm", [getModelName()])), null, onCopyClick);
			}
		}
		
		private function onCopyClick():void {
			copyTestCasesController.onCopyTestCase(getSelectedItem().id, getTestPlanId(), onCopyReturnMessage);
		}
		
		
		private function onCopyReturnMessage(xml:XMLNode):void {
			if (displayReturnMessage(xml)){
				search(freeText);
			}
		}
		
		override protected function addGridParameters(gridParameters:GridParameters):void {
			super.addGridParameters(gridParameters);
		}
		
		override public function getModelName():String {
			return getMessageLocale("testcase", "testcases.crud.object.name");
		}		
		
		public function getTestPlanId():int{
			return crudTestPlanPM!=null && crudTestPlanPM.getDomainObject()!=null ? crudTestPlanPM.getDomainObject().id : 0;
		}
	}
}