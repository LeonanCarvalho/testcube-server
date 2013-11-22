package presentation.model.admin.products
{
	import application.controller.admin.products.EnvironmentsController;
	import application.model.DomainObject;
	import application.model.products.Environment;
	
	import mx.collections.ArrayList;
	import mx.collections.ListCollectionView;
	
	import presentation.events.panels.searchpanel.LinkBarItemClickEvent;
	import presentation.events.panels.searchpanel.SearchCompletedEvent;
	import presentation.events.panels.searchpanel.SearchRefreshEvent;
	import presentation.model.panels.SearchablePM;
	import presentation.view.admin.CrudEnvironment;
	
	import utils.grid.GridUtils;

	public class EnvironmentsPM extends SearchablePM {
		private static const ENVIRONMENT_NEW_EVENT:String = "ENVIRONMENT_NEW_EVENT";
		private static const ENVIRONMENT_EDIT_EVENT:String = "ENVIRONMENT_EDIT_EVENT";
		private static const ENVIRONMENT_DELETE_EVENT:String = "ENVIRONMENT_DELETE_EVENT";
		
		[Inject]
		public var environmentsController:EnvironmentsController;
		
		[Embed("/images/admin/environments-16.png")]
		private static const NEW_ENVIRONMENT:Class;
		
		public function EnvironmentsPM() {
			super();
			
			columns = new ArrayList();
			columns.addItem(GridUtils.getSortableColumn("id", getMessageLocale("admin", "admin.administrator.environments.grid.id"), true, true, ID_DEFAULT_WIDTH));
			columns.addItem(GridUtils.getSortableColumn("name", getMessageLocale("admin", "admin.administrator.environments.grid.name"), true, true));						
			columns.addItem(GridUtils.getSortableColumn("description", getMessageLocale("admin", "admin.administrator.environments.grid.description"), true, true));
			columns.addItem(GridUtils.getColumn("product.name", getMessageLocale("admin", "admin.administrator.environments.grid.product"), true));
			columns.addItem(GridUtils.getSortableColumn("active", getMessageLocale("admin", "admin.administrator.environments.grid.active"), true, true));
		}
		
		[Init]
		override public function postConstructor():void {
			super.controller = environmentsController;
		}
		
		[MessageHandler(selector=EnvironmentsController.ENVIRONMENTS_SEARCH_COMPLETED_EVENT)]
		override public function searchCompleted(event:SearchCompletedEvent):void {
			super.searchCompleted(event);
			
			data = new ListCollectionView(event.getResult());
		}
		
		[MessageHandler(selector=ENVIRONMENT_NEW_EVENT)]
		public function newEnvironment(event:LinkBarItemClickEvent):void {
			newCrud(new CrudEnvironment);
		}
		
		[MessageHandler(selector=ENVIRONMENT_EDIT_EVENT)]
		public function editEnvironment(event:LinkBarItemClickEvent):void {
			editCrud(new CrudEnvironment);
		}
		
		[MessageHandler(selector=ENVIRONMENT_DELETE_EVENT)]
		public function deleteEnvironment(event:LinkBarItemClickEvent):void {
			deleteCrud();
		}
		
		override public function getNewButton():Object {
			var newButton:Object = new Object();
			newButton.label = getMessageLocale("admin", "admin.administrator.environments.linkbar.new");
			newButton.icon = NEW_ENVIRONMENT;
			newButton.eventType = ENVIRONMENT_NEW_EVENT;
			return newButton;
		}
		
		override public function getEditButton():Object {
			var editButton:Object = super.getEditButton();
			editButton.eventType = ENVIRONMENT_EDIT_EVENT;
			return editButton;
		}
		
		override public function getDeleteButton():Object {
			var deleteButton:Object = super.getDeleteButton();
			deleteButton.eventType = ENVIRONMENT_DELETE_EVENT;
			return deleteButton;
		}
		
		override public function getNewDomainObject():DomainObject{
			return new Environment();
		}		
		
		[MessageHandler(selector=CrudEnvironmentPM.ENVIRONMENTS_SEARCH_REFRESH_EVENT)]
		override public function onSearchRefreshEvent(event:SearchRefreshEvent):void {
			search(freeText);
		}
		
		override public function getModelName():String {
			return getMessageLocale("admin", "admin.administrator.environments.crud.object.name");
		}
	}
}