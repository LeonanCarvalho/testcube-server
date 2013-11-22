package presentation.model.admin.products {
	import application.controller.admin.products.ComponentsController;
	import application.model.DomainObject;
	import application.model.products.Component;
	
	import mx.collections.ArrayList;
	import mx.collections.ListCollectionView;
	
	import org.spicefactory.parsley.messaging.MessageHandler;
	
	import presentation.events.panels.searchpanel.LinkBarItemClickEvent;
	import presentation.events.panels.searchpanel.SearchCompletedEvent;
	import presentation.events.panels.searchpanel.SearchRefreshEvent;
	import presentation.model.panels.SearchablePM;
	import presentation.view.admin.CrudComponent;
	
	import utils.grid.GridUtils;
	
	public class ComponentsPM extends SearchablePM {
		private static const COMPONENTS_NEW_EVENT:String = "COMPONENTS_NEW_EVENT";
		private static const COMPONENTS_EDIT_EVENT:String = "COMPONENTS_EDIT_EVENT";
		private static const COMPONENTS_DELETE_EVENT:String = "COMPONENTS_DELETE_EVENT";
		
		[Inject]
		public var componentsController:ComponentsController;
		
		[Embed("/images/admin/components-16.png")]
		private static const NEW_COMPONENT:Class;
		
		public function ComponentsPM() {
			super();
			
			columns = new ArrayList();
			columns.addItem(GridUtils.getSortableColumn("id", getMessageLocale("admin", "admin.administrator.components.grid.id"), true, true, ID_DEFAULT_WIDTH));
			columns.addItem(GridUtils.getSortableColumn("name", getMessageLocale("admin", "admin.administrator.components.grid.name"), true, true));						
			columns.addItem(GridUtils.getSortableColumn("description", getMessageLocale("admin", "admin.administrator.components.grid.description"), true, true));
			columns.addItem(GridUtils.getColumn("product.name", getMessageLocale("admin", "admin.administrator.components.grid.product"), true));
		}
		
		[Init]
		override public function postConstructor():void {
			super.controller = componentsController;
		}
		
		[MessageHandler(selector=ComponentsController.COMPONENTS_SEARCH_COMPLETED_EVENT)]
		override public function searchCompleted(event:SearchCompletedEvent):void {
			super.searchCompleted(event);
			
			data = new ListCollectionView(event.getResult());
		}
		
		[MessageHandler(selector=COMPONENTS_NEW_EVENT)]
		public function newComponent(event:LinkBarItemClickEvent):void {
			newCrud(new CrudComponent);
		}
		
		[MessageHandler(selector=COMPONENTS_EDIT_EVENT)]
		public function editComponent(event:LinkBarItemClickEvent):void {
			editCrud(new CrudComponent);
		}
		
		[MessageHandler(selector=COMPONENTS_DELETE_EVENT)]
		public function deleteComponent(event:LinkBarItemClickEvent):void {
			deleteCrud();
		}
		
		override public function getNewButton():Object {
			var newButton:Object = new Object();
			newButton.label = getMessageLocale("admin", "admin.administrator.components.linkbar.new");
			newButton.icon = NEW_COMPONENT;
			newButton.eventType = COMPONENTS_NEW_EVENT;
			return newButton;
		}
		
		override public function getEditButton():Object {
			var editButton:Object = super.getEditButton();
			editButton.eventType = COMPONENTS_EDIT_EVENT;
			return editButton;
		}
		
		override public function getDeleteButton():Object {
			var deleteButton:Object = super.getDeleteButton();
			deleteButton.eventType = COMPONENTS_DELETE_EVENT;
			return deleteButton;
		}
		
		override public function getNewDomainObject():DomainObject{
			return new Component();
		}		
		
		[MessageHandler(selector=CrudComponentPM.COMPONENTS_SEARCH_REFRESH_EVENT)]
		override public function onSearchRefreshEvent(event:SearchRefreshEvent):void {
			search(freeText);
		}
		
		override public function getModelName():String {
			return getMessageLocale("admin", "admin.administrator.components.crud.object.name");
		}
	}
}