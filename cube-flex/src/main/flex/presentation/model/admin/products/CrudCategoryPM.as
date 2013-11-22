package presentation.model.admin.products {
	import application.controller.admin.products.CategoriesController;
	import application.controller.admin.products.ProductsController;
	import application.model.DomainObject;
	import application.model.grid.GridParameters;
	import application.model.products.Category;
	import application.model.products.ProductsList;
	
	import flash.events.MouseEvent;
	import flash.xml.XMLNode;
	
	import mx.collections.ArrayList;
	
	import presentation.model.panels.CrudPM;
	
	import utils.ComboBoxUtils;

	public class CrudCategoryPM  extends CrudPM {
		[Embed("/images/admin/categories-48.png")]
		private static const CATEGORIES_LARGE:Class;
		
		public static const CATEGORIES_SEARCH_REFRESH_EVENT:String = "CATEGORIES_SEARCH_REFRESH_EVENT";
		
		[Inject]
		public var categoriesController:CategoriesController;
		[Inject]
		public var productsController:ProductsController;
		
		[Bindable]
		public var products:ArrayList;
		[Bindable]
		public var productSelectedIndex:int = ComboBoxUtils.NON_SELECTED_INDEX;
		
		public function CrudCategoryPM() {}
		
		[Init]
		override public function postConstructor():void {
			super.controller = categoriesController;
			
			loadProducts();
		}
		
		override public function get largeIcon():Class {
			return CATEGORIES_LARGE;
		}
		
		override public function get modelName():String {
			return getMessageLocale("admin", "admin.administrator.categories.crud.object.name");
		}
		
		override public function getNewDomainObject():DomainObject{
			return new Category() as DomainObject;
		}
		
		override public function getRefreshEventType():String {
			return CATEGORIES_SEARCH_REFRESH_EVENT;
		}
		
		[Bindable]
		public function get category():Category {
			return super.getDomainObject() as Category;
		}
		
		public function set category(category:Category):void {
			return super.setDomainObject(category as DomainObject);
		}
		
		private function loadProducts():void {
			productsController.searchByParameters(toProductsModel, new GridParameters(ComboBoxUtils.COMBOBOX_LIMIT));
		}
		
		private function toProductsModel(xml:XMLNode):void{
			var products:ProductsList = getFlexXBEngineInstance().deserialize(new XML(xml), ProductsList) as ProductsList;
			this.products = products.products;
			
			productSelectedIndex = ComboBoxUtils.getSelected(this.products, category.product);
		}
		
		override public function onSaveNewObject():void {
			this.category = getNewDomainObject() as Category;
		}
	}
}