package presentation.model.admin.products {
	import application.controller.admin.products.BuildsController;
	import application.controller.admin.products.ProductsController;
	import application.model.DomainObject;
	import application.model.grid.GridParameters;
	import application.model.products.Build;
	import application.model.products.ProductsList;
	
	import flash.events.MouseEvent;
	import flash.xml.XMLNode;
	
	import mx.collections.ArrayList;
	
	import presentation.model.panels.CrudPM;
	
	import utils.ComboBoxUtils;
	
	public class CrudBuildPM extends CrudPM {
		[Embed("/images/admin/builds-48.png")]
		private static const BUILDS_LARGE:Class;
		
		public static const BUILDS_SEARCH_REFRESH_EVENT:String = "BUILDS_SEARCH_REFRESH_EVENT";
		
		[Inject]
		public var buildsController:BuildsController;
		[Inject]
		public var productsController:ProductsController;
		
		[Bindable]
		public var products:ArrayList;
		[Bindable]
		public var productSelectedIndex:int = ComboBoxUtils.NON_SELECTED_INDEX;
		
		public function CrudBuildPM() {}
		
		[Init]
		override public function postConstructor():void {
			super.controller = buildsController;
			
			loadProducts();
		}
		
		override public function get largeIcon():Class {
			return BUILDS_LARGE;
		}
		
		override public function get modelName():String {
			return getMessageLocale("admin", "admin.administrator.builds.crud.object.name");
		}
		
		override public function getNewDomainObject():DomainObject{
			return new Build() as DomainObject;
		}
		
		override public function getRefreshEventType():String {
			return BUILDS_SEARCH_REFRESH_EVENT;
		}
		
		[Bindable]
		public function get build():Build {
			return super.getDomainObject() as Build;
		}
		
		public function set build(build:Build):void {
			return super.setDomainObject(build as DomainObject);
		}
		
		private function loadProducts():void {
			productsController.searchByParameters(toProductsModel, new GridParameters(ComboBoxUtils.COMBOBOX_LIMIT));
		}
		
		private function toProductsModel(xml:XMLNode):void{
			var products:ProductsList = getFlexXBEngineInstance().deserialize(new XML(xml), ProductsList) as ProductsList;
			this.products = products.products;
			
			productSelectedIndex = ComboBoxUtils.getSelected(this.products, build.product);
		}
		
		override public function onSaveNewObject():void {
			this.build = getNewDomainObject() as Build;
		}
	}
}