package presentation.model.admin.products
{
	import application.controller.admin.products.BuildsController;
	import application.model.DomainObject;
	import application.model.products.Build;
	
	import mx.collections.ArrayList;
	import mx.collections.ListCollectionView;
	
	import presentation.events.panels.searchpanel.LinkBarItemClickEvent;
	import presentation.events.panels.searchpanel.SearchCompletedEvent;
	import presentation.events.panels.searchpanel.SearchRefreshEvent;
	import presentation.model.panels.SearchablePM;
	import presentation.view.admin.CrudBuild;
	
	import utils.grid.GridUtils;

	public class BuildsPM extends SearchablePM {
		private static const BUILD_NEW_EVENT:String = "BUILD_NEW_EVENT";
		private static const BUILD_EDIT_EVENT:String = "BUILD_EDIT_EVENT";
		private static const BUILD_DELETE_EVENT:String = "BUILD_DELETE_EVENT";
		
		[Inject]
		public var buildsController:BuildsController;
		
		[Embed("/images/admin/builds-16.png")]
		private static const NEW_BUILD:Class;
		
		public function BuildsPM() {
			super();
			
			columns = new ArrayList();
			columns.addItem(GridUtils.getSortableColumn("id", getMessageLocale("admin", "admin.administrator.builds.grid.id"), true, true, ID_DEFAULT_WIDTH));
			columns.addItem(GridUtils.getSortableColumn("name", getMessageLocale("admin", "admin.administrator.builds.grid.name"), true, true));						
			columns.addItem(GridUtils.getSortableColumn("description", getMessageLocale("admin", "admin.administrator.builds.grid.description"), true, true));
			columns.addItem(GridUtils.getColumn("product.name", getMessageLocale("admin", "admin.administrator.builds.grid.product"), true));
			columns.addItem(GridUtils.getSortableColumn("active", getMessageLocale("admin", "admin.administrator.builds.grid.active"), true, true));
		}
		
		[Init]
		override public function postConstructor():void {
			super.controller = buildsController;
		}
		
		[MessageHandler(selector=BuildsController.BUILDS_SEARCH_COMPLETED_EVENT)]
		override public function searchCompleted(event:SearchCompletedEvent):void {
			super.searchCompleted(event);
			
			data = new ListCollectionView(event.getResult());
		}
		
		[MessageHandler(selector=BUILD_NEW_EVENT)]
		public function newBuild(event:LinkBarItemClickEvent):void {
			newCrud(new CrudBuild);
		}
		
		[MessageHandler(selector=BUILD_EDIT_EVENT)]
		public function editBuild(event:LinkBarItemClickEvent):void {
			editCrud(new CrudBuild);
		}
		
		[MessageHandler(selector=BUILD_DELETE_EVENT)]
		public function deleteBuild(event:LinkBarItemClickEvent):void {
			deleteCrud();
		}
		
		override public function getNewButton():Object {
			var newButton:Object = new Object();
			newButton.label = getMessageLocale("admin", "admin.administrator.builds.linkbar.new");
			newButton.icon = NEW_BUILD;
			newButton.eventType = BUILD_NEW_EVENT;
			return newButton;
		}
		
		override public function getEditButton():Object {
			var editButton:Object = super.getEditButton();
			editButton.eventType = BUILD_EDIT_EVENT;
			return editButton;
		}
		
		override public function getDeleteButton():Object {
			var deleteButton:Object = super.getDeleteButton();
			deleteButton.eventType = BUILD_DELETE_EVENT;
			return deleteButton;
		}
		
		override public function getNewDomainObject():DomainObject{
			return new Build();
		}		
		
		[MessageHandler(selector=CrudBuildPM.BUILDS_SEARCH_REFRESH_EVENT)]
		override public function onSearchRefreshEvent(event:SearchRefreshEvent):void {
			search(freeText);
		}
		
		override public function getModelName():String {
			return getMessageLocale("admin", "admin.administrator.builds.crud.object.name");
		}
	}
}