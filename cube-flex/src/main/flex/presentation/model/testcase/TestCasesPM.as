package presentation.model.testcase {


	import application.controller.admin.products.ProductsController;
	import application.controller.testcase.TestCasesController;
	import application.controller.testplan.TestPlansController;
	import application.model.DomainObject;
	import application.model.NameDescriptionObject;
	import application.model.grid.GridKeywordParameters;
	import application.model.grid.GridParameters;
	import application.model.products.Product;
	import application.model.products.ProductsList;
	import application.model.testcase.TestCase;
	import application.model.testcase.TestCaseGridParameters;
	import application.model.testplan.TestPlan;
	import application.model.testplan.TestPlanGridParameters;
	import application.model.testplan.TestPlansList;
	
	import flash.xml.XMLNode;
	
	import mx.collections.ArrayCollection;
	import mx.collections.ArrayList;
	import mx.collections.ListCollectionView;
	
	import presentation.events.panels.searchpanel.LinkBarItemClickEvent;
	import presentation.events.panels.searchpanel.SearchCompletedEvent;
	import presentation.events.panels.searchpanel.SearchRefreshEvent;
	import presentation.model.panels.CrudPM;
	import presentation.model.panels.SearchablePM;
	import presentation.model.popup.ErrorMessage;
	import presentation.view.testcase.CrudTestCase;
	import presentation.view.testplan.CrudTestPlan;
	
	import utils.ComboBoxUtils;
	import utils.grid.GridUtils;
	import utils.list.ListUtils;
	
	public class TestCasesPM extends SearchablePM {
		public static const EVENT_TYPE:String = "TESTCASES_ICON_LIST_ITEM_CLICK";
		
		private static const TESTCASE_NEW_EVENT:String = "TESTCASE_NEW_EVENT";
		private static const TESTCASE_EDIT_EVENT:String = "TESTCASE_EDIT_EVENT";
		private static const TESTCASE_DELETE_EVENT:String = "TESTCASE_DELETE_EVENT";
		
		[Inject]
		public var testCasesController:TestCasesController;
		[Inject]
		public var testPlansController:TestPlansController;
		[Inject]
		public var productsController:ProductsController;
		[Bindable]
		public var testplans:ArrayCollection;
		[Bindable]
		public var products:ArrayCollection;
		
		public var selectedProduct:Product; 
		public var selectedTestPlan:TestPlan;
		
		[Embed("/images/testcase/testcases-16.png")] 
		private static const NEW_TESTCASE:Class;
		[Embed("/images/admin/products-16.png")]
		private static const PRODUCT_ICON:Class;
		[Embed("/images/testplan/testplans-16.png")]
		private static const TESTPLAN_ICON:Class;
		
		public function TestCasesPM() {
			super();
			
			columns = new ArrayList();
			columns.addItem(GridUtils.getSortableColumn("id", getMessageLocale("testcase", "testcases.grid.id"), true, true, ID_DEFAULT_WIDTH));
			columns.addItem(GridUtils.getSortableColumn("name", getMessageLocale("testcase", "testcases.grid.name"), true, true));						
			columns.addItem(GridUtils.getSortableColumn("description", getMessageLocale("testcase", "testcases.grid.description"), true, true));
			
			columns.addItem(GridUtils.getSortableColumn("categoryName", getMessageLocale("testcase", "testcases.grid.categoryName"), true, true));
			columns.addItem(GridUtils.getColumn("createdBy", getMessageLocale("testcase", "testcases.grid.createdBy"), true));
			columns.addItem(GridUtils.getColumn("assignee.username", getMessageLocale("testcase", "testcases.grid.assignee"), true));
			columns.addItem(GridUtils.getColumn("testPlanName", getMessageLocale("testcase", "testcases.grid.testplan"), true));
		}
		
		[Init]
		override public function postConstructor():void {
			super.controller = testCasesController;
			
			loadProducts();
			
			loadTestPlans();
		}
		
		[MessageHandler(selector=TestCasesController.TESTCASES_SEARCH_COMPLETED_EVENT)]
		override public function searchCompleted(event:SearchCompletedEvent):void {
			super.searchCompleted(event);
			
			data = new ListCollectionView(event.getResult());
		}
		
		[MessageHandler(selector=TESTCASE_NEW_EVENT)]
		public function newCase(event:LinkBarItemClickEvent):void {
			// Check that testplan was selected.
			if (this.selectedTestPlan==null){
				showError(new ErrorMessage(getMessageLocale("testcase", "testcases.grid.new.missing.testplan.title"), 
					getMessageLocale("testcase", "testcases.grid.new.missing.testplan.message")), null);
			}else {
				newCrud(new CrudTestCase);
			}
		}
		
		[MessageHandler(selector=TESTCASE_EDIT_EVENT)]
		public function editCase(event:LinkBarItemClickEvent):void {
			editCrud(new CrudTestCase);
		}
		
		[MessageHandler(selector=TESTCASE_DELETE_EVENT)]
		public function deleteCase(event:LinkBarItemClickEvent):void {
			deleteCrud();
		}
		
		override public function getNewButton():Object {
			var newButton:Object = new Object();
			newButton.label = getMessageLocale("testcase", "testcases.linkbar.new");
			newButton.icon = NEW_TESTCASE;
			newButton.eventType = TESTCASE_NEW_EVENT;
			return newButton;
		}
		
		override public function getEditButton():Object {
			var editButton:Object = super.getEditButton();
			editButton.eventType = TESTCASE_EDIT_EVENT;
			return editButton;
		}
		
		override public function getDeleteButton():Object {
			var deleteButton:Object = super.getDeleteButton();
			deleteButton.eventType = TESTCASE_DELETE_EVENT;
			return deleteButton;
		}
		
		override public function getNewDomainObject():DomainObject{
			var testCase:TestCase = new TestCase();
			
			// Set selected plan id to new test cases
			testCase.testPlanId = this.selectedTestPlan.id;
			
			return testCase;
		}
		
		[MessageHandler(selector=CrudTestCasePM.TESTCASES_SEARCH_REFRESH_EVENT)]
		override public function onSearchRefreshEvent(event:SearchRefreshEvent):void {
			// Reset paging data on refresh search event.
			search(freeText, event.getPagingEvent());
		}
		
		override protected function addGridParameters(gridParameters:GridParameters):void {
			super.addGridParameters(gridParameters);
			(gridParameters as TestCaseGridParameters).productId = this.selectedProduct!=null? String(this.selectedProduct.id): null;
			(gridParameters as TestCaseGridParameters).testPlanId = this.selectedTestPlan!=null? String(this.selectedTestPlan.id): null;
		}
		
		override public function getModelName():String {
			return getMessageLocale("testcase", "testcases.crud.object.name");
		}
		
		public function loadProducts():void {
			productsController.searchByParameters(toProductsModel, new GridParameters(ComboBoxUtils.COMBOBOX_LIMIT, 0, NameDescriptionObject.FILED_NAME));
		}
		
		private function toProductsModel(xml:XMLNode):void{
			var products:ProductsList = getFlexXBEngineInstance().deserialize(new XML(xml), ProductsList) as ProductsList;
			
			this.products = ListUtils.toIconListItem(products.products, PRODUCT_ICON, "id", "name", "description");
		}
		
		public function loadTestPlans():void {	
			testPlansController.searchByParameters(toTestPlansModel, new TestPlanGridParameters(this.selectedProduct!=null? String(this.selectedProduct.id): null, ComboBoxUtils.COMBOBOX_LIMIT, GridParameters.DEFAULT_PAGE_START, NameDescriptionObject.FILED_NAME));
		}
		
		private function toTestPlansModel(xml:XMLNode):void{
			var plans:TestPlansList = getFlexXBEngineInstance().deserialize(new XML(xml), TestPlansList) as TestPlansList;
			
			this.testplans = ListUtils.toIconListItem(plans.plans, TESTPLAN_ICON, "id", "name", "description");
		}
	}
}