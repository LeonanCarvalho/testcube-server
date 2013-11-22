package presentation.model.testplan {


	import application.controller.admin.products.ProductsController;
	import application.controller.testplan.TestPlansController;
	import application.model.DomainObject;
	import application.model.NameDescriptionObject;
	import application.model.grid.GridKeywordParameters;
	import application.model.grid.GridParameters;
	import application.model.products.Product;
	import application.model.products.ProductsList;
	import application.model.testplan.TestPlan;
	import application.model.testplan.TestPlanGridParameters;
	
	import flash.xml.XMLNode;
	
	import mx.collections.ArrayCollection;
	import mx.collections.ArrayList;
	import mx.collections.ListCollectionView;
	
	import presentation.events.panels.searchpanel.LinkBarItemClickEvent;
	import presentation.events.panels.searchpanel.SearchCompletedEvent;
	import presentation.events.panels.searchpanel.SearchRefreshEvent;
	import presentation.model.panels.CrudPM;
	import presentation.model.panels.SearchablePM;
	import presentation.view.testplan.CrudTestPlan;
	
	import utils.ComboBoxUtils;
	import utils.grid.GridUtils;
	import utils.list.ListUtils;
	
	public class TestPlansPM extends SearchablePM {
		public static const EVENT_TYPE:String = "TESTPLANS_ICON_LIST_ITEM_CLICK";
		
		private static const TESTPLAN_NEW_EVENT:String = "TESTPLAN_NEW_EVENT";
		private static const TESTPLAN_EDIT_EVENT:String = "TESTPLAN_EDIT_EVENT";
		private static const TESTPLAN_DELETE_EVENT:String = "TESTPLAN_DELETE_EVENT";
		
		[Inject]
		public var testPlansController:TestPlansController;
		[Inject]
		public var productsController:ProductsController;
		[Bindable]
		public var products:ArrayCollection;
		
		public var selectedProduct:Product; 
		
		[Embed("/images/testplan/testplans-16.png")] 
		private static const NEW_TESTPLAN:Class;
		[Embed("/images/admin/products-16.png")]
		private static const PRODUCT_ICON:Class;
		
		public function TestPlansPM() {
			super();
			
			columns = new ArrayList();
			columns.addItem(GridUtils.getSortableColumn("id", getMessageLocale("testplan", "testplans.grid.id"), true, true, ID_DEFAULT_WIDTH));
			columns.addItem(GridUtils.getSortableColumn("name", getMessageLocale("testplan", "testplans.grid.name"), true, true));						
			columns.addItem(GridUtils.getSortableColumn("description", getMessageLocale("testplan", "testplans.grid.description"), true, true));
			
			columns.addItem(GridUtils.getColumn("product.name", getMessageLocale("testplan", "testplans.grid.product"), true));
			columns.addItem(GridUtils.getColumn("version.name", getMessageLocale("testplan", "testplans.grid.version"), true));
			columns.addItem(GridUtils.getColumn("planType.name", getMessageLocale("testplan", "testplans.grid.planType"), true));
		}
		
		[Init]
		override public function postConstructor():void {
			super.controller = testPlansController;
			
			loadProducts();
		}
		
		[MessageHandler(selector=TestPlansController.TESTPLANS_SEARCH_COMPLETED_EVENT)]
		override public function searchCompleted(event:SearchCompletedEvent):void {
			super.searchCompleted(event);
			
			data = new ListCollectionView(event.getResult());
		}
		
		[MessageHandler(selector=TESTPLAN_NEW_EVENT)]
		public function newPlan(event:LinkBarItemClickEvent):void {
			newCrud(new CrudTestPlan);
		}
		
		[MessageHandler(selector=TESTPLAN_EDIT_EVENT)]
		public function editPlan(event:LinkBarItemClickEvent):void {
			editCrud(new CrudTestPlan);
		}
		
		[MessageHandler(selector=TESTPLAN_DELETE_EVENT)]
		public function deletePlan(event:LinkBarItemClickEvent):void {
			deleteCrud();
		}
		
		override public function getNewButton():Object {
			var newButton:Object = new Object();
			newButton.label = getMessageLocale("testplan", "testplans.linkbar.new");
			newButton.icon = NEW_TESTPLAN;
			newButton.eventType = TESTPLAN_NEW_EVENT;
			return newButton;
		}
		
		override public function getEditButton():Object {
			var editButton:Object = super.getEditButton();
			editButton.eventType = TESTPLAN_EDIT_EVENT;
			return editButton;
		}
		
		override public function getDeleteButton():Object {
			var deleteButton:Object = super.getDeleteButton();
			deleteButton.eventType = TESTPLAN_DELETE_EVENT;
			return deleteButton;
		}
		
		override public function getNewDomainObject():DomainObject{
			return new TestPlan();
		}
		
		[MessageHandler(selector=CrudTestPlanPM.TESTPLANS_SEARCH_REFRESH_EVENT)]
		override public function onSearchRefreshEvent(event:SearchRefreshEvent):void {
			search(freeText, event.getPagingEvent());
		}
		
		override protected function addGridParameters(gridParameters:GridParameters):void {
			super.addGridParameters(gridParameters);
			
			(gridParameters as TestPlanGridParameters).productId = this.selectedProduct!=null? String(this.selectedProduct.id): null;
		}
		
		override public function getModelName():String {
			return getMessageLocale("testplan", "testplans.crud.object.name");
		}
		
		public function loadProducts():void {
			productsController.searchByParameters(toProductsModel, new GridParameters(ComboBoxUtils.COMBOBOX_LIMIT, 0, NameDescriptionObject.FILED_NAME));
		}
		
		private function toProductsModel(xml:XMLNode):void{
			var products:ProductsList = getFlexXBEngineInstance().deserialize(new XML(xml), ProductsList) as ProductsList;
			
			this.products = ListUtils.toIconListItem(products.products, PRODUCT_ICON, "id", "name", "description");
		}
	}
}