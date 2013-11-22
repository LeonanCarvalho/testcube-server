package presentation.model.admin.products {
	import application.controller.admin.products.ProductsController;
	import application.model.DomainObject;
	import application.model.products.Product;
	
	import flash.events.MouseEvent;
	
	import presentation.model.panels.CrudPM;

	public class CrudProductPM extends CrudPM {
		[Embed("/images/admin/products-48.png")]
		private static const PRODUCTS_LARGE:Class;
		
		public static const PRODUCTS_SEARCH_REFRESH_EVENT:String = "PRODUCTS_SEARCH_REFRESH_EVENT";
		
		[Inject]
		public var productsController:ProductsController;
		
		public function CrudProductPM() {
		}
		
		[Init]
		override public function postConstructor():void {
			super.controller = productsController;
		}
		
		override public function get largeIcon():Class {
			return PRODUCTS_LARGE;
		}
		
		override public function get modelName():String {
			return getMessageLocale("admin", "admin.administrator.products.crud.object.name");
		}
		
		override public function getNewDomainObject():DomainObject{
			return new Product() as DomainObject;
		}
		
		override public function getRefreshEventType():String {
			return PRODUCTS_SEARCH_REFRESH_EVENT;
		}
		
		[Bindable]
		public function get product():Product {
			return super.getDomainObject() as Product;
		}
		
		public function set product(product:Product):void {
			return super.setDomainObject(product as DomainObject);
		}
		
		override public function onSaveNewObject():void {
			this.product = getNewDomainObject() as Product;
		}
	}
}