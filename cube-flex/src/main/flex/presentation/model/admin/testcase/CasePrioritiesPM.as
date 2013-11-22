package presentation.model.admin.testcase {


	import application.controller.admin.testcase.CasePrioritiesController;
	import application.model.DomainObject;
	import application.model.testcase.CasePriority;
	
	import mx.collections.ArrayList;
	import mx.collections.ListCollectionView;
	
	import presentation.events.panels.searchpanel.LinkBarItemClickEvent;
	import presentation.events.panels.searchpanel.SearchCompletedEvent;
	import presentation.events.panels.searchpanel.SearchRefreshEvent;
	import presentation.model.panels.SearchablePM;
	import presentation.view.admin.testcase.CrudCasePriority;
	
	import utils.grid.GridUtils;
	
	public class CasePrioritiesPM extends SearchablePM {
		private static const CASEPRIORITY_NEW_EVENT:String = "CASEPRIORITY_NEW_EVENT";
		private static const CASEPRIORITY_EDIT_EVENT:String = "CASEPRIORITY_EDIT_EVENT";
		private static const CASEPRIORITY_DELETE_EVENT:String = "CASEPRIORITY_DELETE_EVENT";
		
		[Inject]
		public var casePrioritiesController:CasePrioritiesController;
		
		[Embed("/images/admin/casepriorities-16.png")]
		private static const NEW_CASEPRIORITY:Class;
		
		public function CasePrioritiesPM() {
			super();
			
			columns = new ArrayList();
			columns.addItem(GridUtils.getSortableColumn("id", getMessageLocale("admin", "admin.administrator.casepriorities.grid.id"), true, true, ID_DEFAULT_WIDTH));
			columns.addItem(GridUtils.getSortableColumn("name", getMessageLocale("admin", "admin.administrator.casepriorities.grid.name"), true, true));						
			columns.addItem(GridUtils.getSortableColumn("description", getMessageLocale("admin", "admin.administrator.casepriorities.grid.description"), true, true));
			columns.addItem(GridUtils.getSortableColumn("order", getMessageLocale("admin", "admin.administrator.casepriorities.grid.order"), true, true));
		}
		
		[Init]
		override public function postConstructor():void {
			super.controller = casePrioritiesController;
		}
		
		[MessageHandler(selector=CasePrioritiesController.CASEPRIORITIES_SEARCH_COMPLETED_EVENT)]
		override public function searchCompleted(event:SearchCompletedEvent):void {
			super.searchCompleted(event);
			
			data = new ListCollectionView(event.getResult());
		}
		
		[MessageHandler(selector=CASEPRIORITY_NEW_EVENT)]
		public function newPriority(event:LinkBarItemClickEvent):void {
			newCrud(new CrudCasePriority);
		}
		
		[MessageHandler(selector=CASEPRIORITY_EDIT_EVENT)]
		public function editPriority(event:LinkBarItemClickEvent):void {
			editCrud(new CrudCasePriority);
		}
		
		[MessageHandler(selector=CASEPRIORITY_DELETE_EVENT)]
		public function deletePriority(event:LinkBarItemClickEvent):void {
			deleteCrud();
		}
		
		override public function getNewButton():Object {
			var newButton:Object = new Object();
			newButton.label = getMessageLocale("admin", "admin.administrator.casepriorities.linkbar.new");
			newButton.icon = NEW_CASEPRIORITY;
			newButton.eventType = CASEPRIORITY_NEW_EVENT;
			return newButton;
		}
		
		override public function getEditButton():Object {
			var editButton:Object = super.getEditButton();
			editButton.eventType = CASEPRIORITY_EDIT_EVENT;
			return editButton;
		}
		
		override public function getDeleteButton():Object {
			var deleteButton:Object = super.getDeleteButton();
			deleteButton.eventType = CASEPRIORITY_DELETE_EVENT;
			return deleteButton;
		}
		
		override public function getNewDomainObject():DomainObject{
			return new CasePriority();
		}
		
		[MessageHandler(selector=CrudCasePriorityPM.CASEPRIORITIES_SEARCH_REFRESH_EVENT)]
		override public function onSearchRefreshEvent(event:SearchRefreshEvent):void {
			search(freeText);
		}
		
		override public function getModelName():String {
			return getMessageLocale("admin", "admin.administrator.casepriorities.crud.object.name");
		}
	}
}