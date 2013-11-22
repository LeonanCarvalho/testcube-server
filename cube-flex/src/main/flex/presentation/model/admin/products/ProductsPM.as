package presentation.model.admin.products {
	import application.controller.admin.products.ProductsController;
	import application.model.DomainObject;
	import application.model.products.Product;
	
	import mx.collections.ArrayList;
	import mx.collections.ListCollectionView;
	
	import org.spicefactory.parsley.messaging.MessageHandler;
	
	import presentation.events.panels.searchpanel.LinkBarItemClickEvent;
	import presentation.events.panels.searchpanel.SearchCompletedEvent;
	import presentation.events.panels.searchpanel.SearchRefreshEvent;
	import presentation.model.panels.SearchablePM;
	import presentation.view.admin.CrudProduct;
	
	import utils.grid.GridUtils;
	
	public class ProductsPM extends SearchablePM {
		private static const PRODUCTS_NEW_EVENT:String = "PRODUCTS_NEW_EVENT";
		private static const PRODUCTS_EDIT_EVENT:String = "PRODUCTS_EDIT_EVENT";
		private static const PRODUCTS_DELETE_EVENT:String = "PRODUCTS_DELETE_EVENT";
		
		[Inject]
		public var productsController:ProductsController;
		
		[Embed("/images/admin/products-16.png")]
		private static const NEW_PRODUCT:Class;
		
		public function ProductsPM() {
			super();
			
			columns = new ArrayList();
			columns.addItem(GridUtils.getSortableColumn("id", getMessageLocale("admin", "admin.administrator.products.grid.id"), true, true, ID_DEFAULT_WIDTH));
			columns.addItem(GridUtils.getSortableColumn("name", getMessageLocale("admin", "admin.administrator.products.grid.name"), true, true));						
			columns.addItem(GridUtils.getSortableColumn("description", getMessageLocale("admin", "admin.administrator.products.grid.description"), true, true));
			columns.addItem(GridUtils.getSortableColumn("enabled", getMessageLocale("admin", "admin.administrator.products.grid.enabled"), true, true));
		}
		
		[Init]
		override public function postConstructor():void {
			super.controller = productsController;
		}
		
		[MessageHandler(selector=ProductsController.PRODUCTS_SEARCH_COMPLETED_EVENT)]
		override public function searchCompleted(event:SearchCompletedEvent):void {
			super.searchCompleted(event);
			
			data = new ListCollectionView(event.getResult());
		}
		
		[MessageHandler(selector=PRODUCTS_NEW_EVENT)]
		public function newProduct(event:LinkBarItemClickEvent):void {
			newCrud(new CrudProduct);
		}
		
		[MessageHandler(selector=PRODUCTS_EDIT_EVENT)]
		public function editProduct(event:LinkBarItemClickEvent):void {
			editCrud(new CrudProduct);
		}
		
		[MessageHandler(selector=PRODUCTS_DELETE_EVENT)]
		public function deleteProduct(event:LinkBarItemClickEvent):void {
			deleteCrud();
		}
		
		override public function getNewButton():Object {
			var newButton:Object = new Object();
			newButton.label = getMessageLocale("admin", "admin.administrator.products.linkbar.new");
			newButton.icon = NEW_PRODUCT;
			newButton.eventType = PRODUCTS_NEW_EVENT;
			return newButton;
		}
		
		override public function getEditButton():Object {
			var editButton:Object = super.getEditButton();
			editButton.eventType = PRODUCTS_EDIT_EVENT;
			return editButton;
		}
		
		override public function getDeleteButton():Object {
			var deleteButton:Object = super.getDeleteButton();
			deleteButton.eventType = PRODUCTS_DELETE_EVENT;
			return deleteButton;
		}
		
		override public function getNewDomainObject():DomainObject{
			return new Product();
		}		
		
		[MessageHandler(selector=CrudProductPM.PRODUCTS_SEARCH_REFRESH_EVENT)]
		override public function onSearchRefreshEvent(event:SearchRefreshEvent):void {
			search(freeText);
		}
		
		override public function getModelName():String {
			return getMessageLocale("admin", "admin.administrator.products.crud.object.name");
		}
	}
}