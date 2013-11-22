package presentation.model.admin.testplan {


	import application.controller.admin.testplan.PlanTypesController;
	import application.model.DomainObject;
	import application.model.testplan.PlanType;
	
	import mx.collections.ArrayList;
	import mx.collections.ListCollectionView;
	
	import presentation.events.panels.searchpanel.LinkBarItemClickEvent;
	import presentation.events.panels.searchpanel.SearchCompletedEvent;
	import presentation.events.panels.searchpanel.SearchRefreshEvent;
	import presentation.model.panels.SearchablePM;
	import presentation.view.admin.testplan.CrudPlanType;
	
	import utils.grid.GridUtils;
	
	public class PlanTypesPM extends SearchablePM {
		private static const PLANTYPE_NEW_EVENT:String = "PLANTYPE_NEW_EVENT";
		private static const PLANTYPE_EDIT_EVENT:String = "PLANTYPE_EDIT_EVENT";
		private static const PLANTYPE_DELETE_EVENT:String = "PLANTYPE_DELETE_EVENT";
		
		[Inject]
		public var planTypesController:PlanTypesController;
		
		[Embed("/images/admin/plantypes-16.png")]
		private static const NEW_PLANTYPE:Class;
		
		public function PlanTypesPM() {
			super();
			
			columns = new ArrayList();
			columns.addItem(GridUtils.getSortableColumn("id", getMessageLocale("admin", "admin.administrator.plantypes.grid.id"), true, true, ID_DEFAULT_WIDTH));
			columns.addItem(GridUtils.getSortableColumn("name", getMessageLocale("admin", "admin.administrator.plantypes.grid.name"), true, true));						
			columns.addItem(GridUtils.getSortableColumn("description", getMessageLocale("admin", "admin.administrator.plantypes.grid.description"), true, true));
		}
		
		[Init]
		override public function postConstructor():void {
			super.controller = planTypesController;
		}
		
		[MessageHandler(selector=PlanTypesController.PLANTYPES_SEARCH_COMPLETED_EVENT)]
		override public function searchCompleted(event:SearchCompletedEvent):void {
			super.searchCompleted(event);
			
			data = new ListCollectionView(event.getResult());
		}
		
		[MessageHandler(selector=PLANTYPE_NEW_EVENT)]
		public function newType(event:LinkBarItemClickEvent):void {
			newCrud(new CrudPlanType);
		}
		
		[MessageHandler(selector=PLANTYPE_EDIT_EVENT)]
		public function editType(event:LinkBarItemClickEvent):void {
			editCrud(new CrudPlanType);
		}
		
		[MessageHandler(selector=PLANTYPE_DELETE_EVENT)]
		public function deleteType(event:LinkBarItemClickEvent):void {
			deleteCrud();
		}
		
		override public function getNewButton():Object {
			var newButton:Object = new Object();
			newButton.label = getMessageLocale("admin", "admin.administrator.plantypes.linkbar.new");
			newButton.icon = NEW_PLANTYPE;
			newButton.eventType = PLANTYPE_NEW_EVENT;
			return newButton;
		}
		
		override public function getEditButton():Object {
			var editButton:Object = super.getEditButton();
			editButton.eventType = PLANTYPE_EDIT_EVENT;
			return editButton;
		}
		
		override public function getDeleteButton():Object {
			var deleteButton:Object = super.getDeleteButton();
			deleteButton.eventType = PLANTYPE_DELETE_EVENT;
			return deleteButton;
		}
		
		override public function getNewDomainObject():DomainObject{
			return new PlanType();
		}
		
		[MessageHandler(selector=CrudPlanTypePM.PLANTYPES_SEARCH_REFRESH_EVENT)]
		override public function onSearchRefreshEvent(event:SearchRefreshEvent):void {
			search(freeText);
		}
		
		override public function getModelName():String {
			return getMessageLocale("admin", "admin.administrator.plantypes.crud.object.name");
		}
	}
}