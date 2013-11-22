package presentation.model.admin.products {
	import application.controller.admin.products.ComponentsController;
	import application.controller.admin.products.ProductsController;
	import application.model.DomainObject;
	import application.model.grid.GridKeywordParameters;
	import application.model.grid.GridParameters;
	import application.model.products.Component;
	import application.model.products.ProductsList;
	
	import flash.events.MouseEvent;
	import flash.xml.XMLNode;
	
	import mx.collections.ArrayList;
	
	import presentation.model.panels.CrudPM;
	
	import utils.ComboBoxUtils;

	public class CrudComponentPM extends CrudPM {
		[Embed("/images/admin/components-48.png")]
		private static const COMPONENTS_LARGE:Class;
		
		public static const COMPONENTS_SEARCH_REFRESH_EVENT:String = "COMPONENTS_SEARCH_REFRESH_EVENT";
		
		[Inject]
		public var componentsController:ComponentsController;
		[Inject]
		public var productsController:ProductsController;
		
		[Bindable]
		public var products:ArrayList;
		[Bindable]
		public var productSelectedIndex:int = ComboBoxUtils.NON_SELECTED_INDEX;
		
		public function CrudComponentPM() {}
		
		[Init]
		override public function postConstructor():void {
			super.controller = componentsController;
			
			loadProducts();
		}

		override public function get largeIcon():Class {
			return COMPONENTS_LARGE;
		}
		
		override public function get modelName():String {
			return getMessageLocale("admin", "admin.administrator.components.crud.object.name");
		}
		
		override public function getNewDomainObject():DomainObject{
			return new Component() as DomainObject;
		}
		
		override public function getRefreshEventType():String {
			return COMPONENTS_SEARCH_REFRESH_EVENT;
		}
		
		[Bindable]
		public function get component():Component {
			return super.getDomainObject() as Component;
		}
		
		public function set component(component:Component):void {
			return super.setDomainObject(component as DomainObject);
		}
		
		private function loadProducts():void {
			productsController.searchByParameters(toProductsModel, new GridParameters(ComboBoxUtils.COMBOBOX_LIMIT));
		}
		
		private function toProductsModel(xml:XMLNode):void{
			var products:ProductsList = getFlexXBEngineInstance().deserialize(new XML(xml), ProductsList) as ProductsList;
			this.products = products.products;
			
			productSelectedIndex = ComboBoxUtils.getSelected(this.products, component.product);
		}
		
		override public function onSaveNewObject():void {
			this.component = getNewDomainObject() as Component;
		}

	}
}