package presentation.model.admin.products {
	import application.controller.admin.products.ProductsController;
	import application.controller.admin.products.VersionsController;
	import application.model.DomainObject;
	import application.model.grid.GridParameters;
	import application.model.products.ProductsList;
	import application.model.versions.Version;
	
	import flash.events.MouseEvent;
	import flash.xml.XMLNode;
	
	import mx.collections.ArrayList;
	
	import presentation.model.panels.CrudPM;
	
	import utils.ComboBoxUtils;
	
	public class CrudVersionPM extends CrudPM {
		[Embed("/images/admin/versions-48.png")]
		private static const VERSIONS_LARGE:Class;
		
		public static const VERSIONS_SEARCH_REFRESH_EVENT:String = "VERSIONS_SEARCH_REFRESH_EVENT";
		
		[Inject]
		public var versionsController:VersionsController;
		[Inject]
		public var productsController:ProductsController;
		
		[Bindable]
		public var products:ArrayList;
		[Bindable]
		public var productSelectedIndex:int = ComboBoxUtils.NON_SELECTED_INDEX;
		
		public function CrudVersionPM() {}
		
		[Init]
		override public function postConstructor():void {
			super.controller = versionsController;
			
			loadProducts();
		}
		
		override public function get largeIcon():Class {
			return VERSIONS_LARGE;
		}
		
		override public function get modelName():String {
			return getMessageLocale("admin", "admin.administrator.versions.crud.object.name");
		}
		
		override public function getNewDomainObject():DomainObject{
			return new Version() as DomainObject;
		}
		
		override public function getRefreshEventType():String {
			return VERSIONS_SEARCH_REFRESH_EVENT;
		}
		
		[Bindable]
		public function get version():Version {
			return super.getDomainObject() as Version;
		}
		
		public function set version(version:Version):void {
			return super.setDomainObject(version as DomainObject);
		}
		
		private function loadProducts():void {
			productsController.searchByParameters(toProductsModel, new GridParameters(ComboBoxUtils.COMBOBOX_LIMIT));
		}
		
		private function toProductsModel(xml:XMLNode):void{
			var products:ProductsList = getFlexXBEngineInstance().deserialize(new XML(xml), ProductsList) as ProductsList;
			this.products = products.products;
			
			productSelectedIndex = ComboBoxUtils.getSelected(this.products, version.product);
		}
		
		override public function onSaveNewObject():void {
			this.version = getNewDomainObject() as Version;
		}
	}
}