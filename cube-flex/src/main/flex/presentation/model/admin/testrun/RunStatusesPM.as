package presentation.model.admin.testrun {


	import application.controller.admin.testrun.RunStatusesController;
	import application.model.DomainObject;
	import application.model.testrun.RunStatus;
	
	import mx.collections.ArrayList;
	import mx.collections.ListCollectionView;
	
	import presentation.events.panels.searchpanel.LinkBarItemClickEvent;
	import presentation.events.panels.searchpanel.SearchCompletedEvent;
	import presentation.events.panels.searchpanel.SearchRefreshEvent;
	import presentation.model.panels.SearchablePM;
	import presentation.view.admin.testrun.CrudRunStatus;
	
	import utils.grid.GridUtils;
	
	public class RunStatusesPM extends SearchablePM {
		private static const RUNSTATUS_EDIT_EVENT:String = "RUNSTATUS_EDIT_EVENT";
		
		[Inject]
		public var runStatusesController:RunStatusesController;
		
		public function RunStatusesPM() {
			super();
			
			columns = new ArrayList();
			columns.addItem(GridUtils.getSortableColumn("id", getMessageLocale("admin", "admin.administrator.runstatuses.grid.id"), true, true, ID_DEFAULT_WIDTH));
			columns.addItem(GridUtils.getSortableColumn("name", getMessageLocale("admin", "admin.administrator.runstatuses.grid.name"), true, true));						
			columns.addItem(GridUtils.getSortableColumn("description", getMessageLocale("admin", "admin.administrator.runstatuses.grid.description"), true, true));
			columns.addItem(GridUtils.getSortableColumn("order", getMessageLocale("admin", "admin.administrator.runstatuses.grid.order"), true, true));
		}
		
		[Init]
		override public function postConstructor():void {
			super.controller = runStatusesController;
		}
		
		[MessageHandler(selector=RunStatusesController.RUNSTATUSES_SEARCH_COMPLETED_EVENT)]
		override public function searchCompleted(event:SearchCompletedEvent):void {
			super.searchCompleted(event);
			
			data = new ListCollectionView(event.getResult());
		}
		
		[MessageHandler(selector=RUNSTATUS_NEW_EVENT)]
		public function newStatus(event:LinkBarItemClickEvent):void {
			newCrud(new CrudRunStatus);
		}
		
		[MessageHandler(selector=RUNSTATUS_EDIT_EVENT)]
		public function editStatus(event:LinkBarItemClickEvent):void {
			editCrud(new CrudRunStatus);
		}
		
		[MessageHandler(selector=RUNSTATUS_DELETE_EVENT)]
		public function deleteStatus(event:LinkBarItemClickEvent):void {
			deleteCrud();
		}
		
		override public function getNewButton():Object {
			return null; // return null will exlude this button from linkbar
		}
		
		override public function getEditButton():Object {
			var editButton:Object = super.getEditButton();
			editButton.label = getMessageLocale("admin", "admin.administrator.runstatuses.linkbar.edit");
			editButton.eventType = RUNSTATUS_EDIT_EVENT;
			return editButton;
		}
		
		override public function getDeleteButton():Object {
			return null; // return null will exlude this button from linkbar
		}
		
		override public function getNewDomainObject():DomainObject{
			return new RunStatus();
		}
		
		[MessageHandler(selector=CrudRunStatusPM.RUNSTATUSES_SEARCH_REFRESH_EVENT)]
		override public function onSearchRefreshEvent(event:SearchRefreshEvent):void {
			search(freeText);
		}
		
		override public function getModelName():String {
			return getMessageLocale("admin", "admin.administrator.runstatuses.crud.object.name");
		}
	}
}