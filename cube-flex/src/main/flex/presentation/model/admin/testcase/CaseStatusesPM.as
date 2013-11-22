package presentation.model.admin.testcase {


	import application.controller.admin.testcase.CaseStatusesController;
	import application.model.DomainObject;
	import application.model.testcase.CaseStatus;
	
	import mx.collections.ArrayList;
	import mx.collections.ListCollectionView;
	
	import presentation.events.panels.searchpanel.LinkBarItemClickEvent;
	import presentation.events.panels.searchpanel.SearchCompletedEvent;
	import presentation.events.panels.searchpanel.SearchRefreshEvent;
	import presentation.model.panels.SearchablePM;
	import presentation.view.admin.testcase.CrudCaseStatus;
	
	import utils.grid.GridUtils;
	
	public class CaseStatusesPM extends SearchablePM {
		private static const CASESTATUS_NEW_EVENT:String = "CASESTATUS_NEW_EVENT";
		private static const CASESTATUS_EDIT_EVENT:String = "CASESTATUS_EDIT_EVENT";
		private static const CASESTATUS_DELETE_EVENT:String = "CASESTATUS_DELETE_EVENT";
		
		[Inject]
		public var caseStatusesController:CaseStatusesController;
		
		[Embed("/images/admin/casestatuses-16.png")]
		private static const NEW_CASESTATUS:Class;
		
		public function CaseStatusesPM() {
			super();
			
			columns = new ArrayList();
			columns.addItem(GridUtils.getSortableColumn("id", getMessageLocale("admin", "admin.administrator.casestatuses.grid.id"), true, true, ID_DEFAULT_WIDTH));
			columns.addItem(GridUtils.getSortableColumn("name", getMessageLocale("admin", "admin.administrator.casestatuses.grid.name"), true, true));						
			columns.addItem(GridUtils.getSortableColumn("description", getMessageLocale("admin", "admin.administrator.casestatuses.grid.description"), true, true));
			columns.addItem(GridUtils.getSortableColumn("order", getMessageLocale("admin", "admin.administrator.casestatuses.grid.order"), true, true));
		}
		
		[Init]
		override public function postConstructor():void {
			super.controller = caseStatusesController;
		}
		
		[MessageHandler(selector=CaseStatusesController.CASESTATUSES_SEARCH_COMPLETED_EVENT)]
		override public function searchCompleted(event:SearchCompletedEvent):void {
			super.searchCompleted(event);
			
			data = new ListCollectionView(event.getResult());
		}
		
		[MessageHandler(selector=CASESTATUS_NEW_EVENT)]
		public function newStatus(event:LinkBarItemClickEvent):void {
			newCrud(new CrudCaseStatus);
		}
		
		[MessageHandler(selector=CASESTATUS_EDIT_EVENT)]
		public function editStatus(event:LinkBarItemClickEvent):void {
			editCrud(new CrudCaseStatus);
		}
		
		[MessageHandler(selector=CASESTATUS_DELETE_EVENT)]
		public function deleteStatus(event:LinkBarItemClickEvent):void {
			deleteCrud();
		}
		
		override public function getNewButton():Object {
			var newButton:Object = new Object();
			newButton.label = getMessageLocale("admin", "admin.administrator.casestatuses.linkbar.new");
			newButton.icon = NEW_CASESTATUS;
			newButton.eventType = CASESTATUS_NEW_EVENT;
			return newButton;
		}
		
		override public function getEditButton():Object {
			var editButton:Object = super.getEditButton();
			editButton.eventType = CASESTATUS_EDIT_EVENT;
			return editButton;
		}
		
		override public function getDeleteButton():Object {
			var deleteButton:Object = super.getDeleteButton();
			deleteButton.eventType = CASESTATUS_DELETE_EVENT;
			return deleteButton;
		}
		
		override public function getNewDomainObject():DomainObject{
			return new CaseStatus();
		}
		
		[MessageHandler(selector=CrudCaseStatusPM.CASESTATUSES_SEARCH_REFRESH_EVENT)]
		override public function onSearchRefreshEvent(event:SearchRefreshEvent):void {
			search(freeText);
		}
		
		override public function getModelName():String {
			return getMessageLocale("admin", "admin.administrator.casestatuses.crud.object.name");
		}
	}
}