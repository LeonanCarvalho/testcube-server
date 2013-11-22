package presentation.model.testplan {
	
	import application.controller.admin.products.ProductsController;
	import application.controller.admin.products.VersionsController;
	import application.controller.admin.testplan.PlanTypesController;
	import application.controller.testplan.TestPlansController;
	import application.model.DomainObject;
	import application.model.grid.GridParameters;
	import application.model.products.ProductsList;
	import application.model.testplan.PlanTypesList;
	import application.model.testplan.TestPlan;
	import application.model.versions.VersionGridParameters;
	import application.model.versions.VersionsList;
	
	import flash.xml.XMLNode;
	
	import mx.collections.ArrayList;
	
	import presentation.model.panels.CrudPM;
	
	import utils.ComboBoxUtils;
	
	public class CrudTestPlanPM  extends CrudPM {
			
		[Embed("/images/testplan/testplans-48.png")]
		private static const TESTPLANS_LARGE:Class;
		
		public static const TESTPLANS_SEARCH_REFRESH_EVENT:String = "TESTPLANS_SEARCH_REFRESH_EVENT";
		
		[Inject]
		public var testPlansController:TestPlansController;
		[Inject]
		public var productsController:ProductsController;
		[Inject]
		public var versionsController:VersionsController;
		[Inject]
		public var planTypesController:PlanTypesController;
		
		[Bindable]
		public var products:ArrayList;
		[Bindable]
		public var versions:ArrayList;
		[Bindable]
		public var planTypes:ArrayList;
		
		[Bindable]
		public var productSelectedIndex:int = ComboBoxUtils.NON_SELECTED_INDEX;
		[Bindable]
		public var versionSelectedIndex:int = ComboBoxUtils.NON_SELECTED_INDEX;
		[Bindable]
		public var planTypeSelectedIndex:int = ComboBoxUtils.NON_SELECTED_INDEX;
		
		public function CrudTestPlanPM() {}
		
		[Init]
		override public function postConstructor():void {
			super.controller = testPlansController;
			
			this.loadProducts();
			
			this.loadPlanTypes();
		}
		
		override public function get largeIcon():Class {
			return TESTPLANS_LARGE;
		}
		
		override public function get modelName():String {
			return getMessageLocale("testplan", "testplans.crud.object.name");
		}
		
		override public function getNewDomainObject():DomainObject {
			return new TestPlan() as DomainObject;
		}
		
		override public function getRefreshEventType():String {
			return TESTPLANS_SEARCH_REFRESH_EVENT;
		}
		
		[Bindable]
		public function get testPlan():TestPlan {
			return super.getDomainObject() as TestPlan;
		}
		
		public function set testPlan(testPlan:TestPlan):void {
			return super.setDomainObject(testPlan as DomainObject);
		}
		
		private function loadProducts():void {
			productsController.searchByParameters(toProductsModel, new GridParameters(ComboBoxUtils.COMBOBOX_LIMIT));
		}
		
		private function toProductsModel(xml:XMLNode):void{
			var products:ProductsList = getFlexXBEngineInstance().deserialize(new XML(xml), ProductsList) as ProductsList;
			this.products = products.products;
			
			productSelectedIndex = ComboBoxUtils.getSelected(this.products, testPlan.product);
			
			// In update mode load versions after product have been set.
			if (isUpdateState()){
				loadVersions();
			}
		}
		
		public function loadVersions():void {
			if (this.testPlan == null || this.testPlan.product == null) 
				return;
			versionsController.searchByParameters(toVersionsModel, new VersionGridParameters(ComboBoxUtils.COMBOBOX_LIMIT, this.testPlan.product.id));
		}
		
		private function toVersionsModel(xml:XMLNode):void{
			var versions:VersionsList = getFlexXBEngineInstance().deserialize(new XML(xml), VersionsList) as VersionsList;
			this.versions = versions.versions;
			
			versionSelectedIndex = ComboBoxUtils.getSelected(this.versions, testPlan.version);
		}
		
		private function loadPlanTypes():void {
			planTypesController.searchByParameters(toPlanTypesModel, new GridParameters(ComboBoxUtils.COMBOBOX_LIMIT));
		}
		
		private function toPlanTypesModel(xml:XMLNode):void{
			var planTypes:PlanTypesList = getFlexXBEngineInstance().deserialize(new XML(xml), PlanTypesList) as PlanTypesList;
			this.planTypes = planTypes.types;
			
			planTypeSelectedIndex = ComboBoxUtils.getSelected(this.planTypes, testPlan.planType);
		}
		
		override public function onSaveNewObject():void {
			this.testPlan = getNewDomainObject() as TestPlan;
		}
	}
}