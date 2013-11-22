package presentation.model.admin.products
{
	import application.controller.admin.products.VersionsController;
	import application.model.DomainObject;
	import application.model.versions.Version;
	
	import mx.collections.ArrayList;
	import mx.collections.ListCollectionView;
	
	import presentation.events.panels.searchpanel.LinkBarItemClickEvent;
	import presentation.events.panels.searchpanel.SearchCompletedEvent;
	import presentation.events.panels.searchpanel.SearchRefreshEvent;
	import presentation.model.panels.SearchablePM;
	import presentation.view.admin.CrudVersion;
	
	import utils.grid.GridUtils;

	public class VersionsPM extends SearchablePM {
		private static const VERSION_NEW_EVENT:String = "VERSION_NEW_EVENT";
		private static const VERSION_EDIT_EVENT:String = "VERSION_EDIT_EVENT";
		private static const VERSION_DELETE_EVENT:String = "VERSION_DELETE_EVENT";
		
		[Inject]
		public var versionsController:VersionsController;
		
		[Embed("/images/admin/versions-16.png")]
		private static const NEW_VERSION:Class;
		
		public function VersionsPM() {
			super();
			
			columns = new ArrayList();
			columns.addItem(GridUtils.getSortableColumn("id", getMessageLocale("admin", "admin.administrator.versions.grid.id"), true, true, ID_DEFAULT_WIDTH));
			columns.addItem(GridUtils.getSortableColumn("name", getMessageLocale("admin", "admin.administrator.versions.grid.name"), true, true));						
			columns.addItem(GridUtils.getSortableColumn("description", getMessageLocale("admin", "admin.administrator.versions.grid.description"), true, true));
			columns.addItem(GridUtils.getColumn("product.name", getMessageLocale("admin", "admin.administrator.versions.grid.product"), true));
			columns.addItem(GridUtils.getSortableColumn("order", getMessageLocale("admin", "admin.administrator.versions.grid.order"), true, true));
		}
		
		[Init]
		override public function postConstructor():void {
			super.controller = versionsController;
		}
		
		[MessageHandler(selector=VersionsController.VERSIONS_SEARCH_COMPLETED_EVENT)]
		override public function searchCompleted(event:SearchCompletedEvent):void {
			super.searchCompleted(event);
			
			data = new ListCollectionView(event.getResult());
		}
		
		[MessageHandler(selector=VERSION_NEW_EVENT)]
		public function newVersion(event:LinkBarItemClickEvent):void {
			newCrud(new CrudVersion);
		}
		
		[MessageHandler(selector=VERSION_EDIT_EVENT)]
		public function editVersion(event:LinkBarItemClickEvent):void {
			editCrud(new CrudVersion);
		}
		
		[MessageHandler(selector=VERSION_DELETE_EVENT)]
		public function deleteVersion(event:LinkBarItemClickEvent):void {
			deleteCrud();
		}
		
		override public function getNewButton():Object {
			var newButton:Object = new Object();
			newButton.label = getMessageLocale("admin", "admin.administrator.versions.linkbar.new");
			newButton.icon = NEW_VERSION;
			newButton.eventType = VERSION_NEW_EVENT;
			return newButton;
		}
		
		override public function getEditButton():Object {
			var editButton:Object = super.getEditButton();
			editButton.eventType = VERSION_EDIT_EVENT;
			return editButton;
		}
		
		override public function getDeleteButton():Object {
			var deleteButton:Object = super.getDeleteButton();
			deleteButton.eventType = VERSION_DELETE_EVENT;
			return deleteButton;
		}
		
		override public function getNewDomainObject():DomainObject{
			return new Version();
		}		
		
		[MessageHandler(selector=CrudVersionPM.VERSIONS_SEARCH_REFRESH_EVENT)]
		override public function onSearchRefreshEvent(event:SearchRefreshEvent):void {
			search(freeText);
		}
		
		override public function getModelName():String {
			return getMessageLocale("admin", "admin.administrator.versions.crud.object.name");
		}
	}
}