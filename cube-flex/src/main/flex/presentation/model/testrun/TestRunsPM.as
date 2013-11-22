package presentation.model.testrun {


	import application.controller.admin.products.ProductsController;
	import application.controller.testplan.TestPlansController;
	import application.controller.testrun.TestRunsController;
	import application.model.DomainObject;
	import application.model.NameDescriptionObject;
	import application.model.grid.GridParameters;
	import application.model.products.Product;
	import application.model.products.ProductsList;
	import application.model.testplan.TestPlan;
	import application.model.testplan.TestPlanGridParameters;
	import application.model.testplan.TestPlansList;
	import application.model.testrun.TestRun;
	import application.model.testrun.TestRunGridParameters;
	
	import flash.xml.XMLNode;
	
	import mx.collections.ArrayCollection;
	import mx.collections.ArrayList;
	import mx.collections.ListCollectionView;
	import mx.core.ClassFactory;
	import mx.core.IFactory;
	
	import presentation.events.panels.searchpanel.LinkBarItemClickEvent;
	import presentation.events.panels.searchpanel.SearchCompletedEvent;
	import presentation.events.panels.searchpanel.SearchRefreshEvent;
	import presentation.model.panels.CrudPM;
	import presentation.model.panels.SearchablePM;
	import presentation.model.popup.ErrorMessage;
	import presentation.view.testrun.CrudTestRun;
	import presentation.view.testrun.rendered.RunStatusGridRenderer;
	import presentation.view.testrun.rendered.TestRunStatisticsPieRendered;
	
	import spark.components.gridClasses.GridColumn;
	import spark.skins.spark.DefaultGridItemRenderer;
	
	import utils.ComboBoxUtils;
	import utils.grid.GridUtils;
	import utils.list.ListUtils;
	
	public class TestRunsPM extends SearchablePM {
		public static const EVENT_TYPE:String = "TESTRUNS_ICON_LIST_ITEM_CLICK";
		
		private static const TESTRUN_NEW_EVENT:String = "TESTRUN_NEW_EVENT";
		private static const TESTRUN_EDIT_EVENT:String = "TESTRUN_EDIT_EVENT";
		private static const TESTRUN_DELETE_EVENT:String = "TESTRUN_DELETE_EVENT";
		
		public static const TESTRUN_STOPPED_EVENT:String = "TESTRUN_STOPPED_EVENT";
		public static const TESTRUN_RUNNING_EVENT:String = "TESTRUN_RUNNING_EVENT";
		
		[Inject]
		public var testRunsController:TestRunsController;
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
		
		[Embed("/images/testrun/testruns-16.png")] 
		private static const NEW_TESTRUN:Class;
		[Embed("/images/admin/products-16.png")]
		private static const PRODUCT_ICON:Class;
		[Embed("/images/testplan/testplans-16.png")]
		private static const TESTPLAN_ICON:Class;
		
		public function TestRunsPM() {
			super();
			
			columns = new ArrayList();
			columns.addItem(GridUtils.getSortableColumn("id", getMessageLocale("testrun", "testruns.grid.id"), true, true, ID_DEFAULT_WIDTH));
			columns.addItem(GridUtils.getSortableColumn("summary", getMessageLocale("testrun", "testruns.grid.summary"), true, true));
			columns.addItem(GridUtils.getSortableImageColumn("status", getMessageLocale("testrun", "testruns.grid.status"), returnItemRenderer, false, false));
			columns.addItem(GridUtils.getSortableColumn("version.name", getMessageLocale("testrun", "testruns.grid.version"), true, true));
			columns.addItem(GridUtils.getSortableColumn("build.name", getMessageLocale("testrun", "testruns.grid.build"), true, true));
			columns.addItem(GridUtils.getSortableGraugeColumn("statistics", getMessageLocale("testrun", "testruns.grid.completed"), returnGieGraugeItemRenderer, false, false));
			
			columns.addItem(GridUtils.getColumn("assignee.username", getMessageLocale("testrun", "testruns.grid.assignee"), true));
			columns.addItem(GridUtils.getColumn("testPlanName", getMessageLocale("testrun", "testruns.grid.testplan"), true));
		}
		
		private function returnItemRenderer(item:Object, column:GridColumn):IFactory { 
			if (item){
				return new ClassFactory(RunStatusGridRenderer);    
			}else {
				return new ClassFactory(DefaultGridItemRenderer);
			}   
		}
		
		private function returnGieGraugeItemRenderer(item:Object, column:GridColumn):IFactory { 
			if (item){
				return new ClassFactory(TestRunStatisticsPieRendered);    
			}else {
				return new ClassFactory(DefaultGridItemRenderer);
			}   
		}
			
		[Init]
		override public function postConstructor():void {
			super.controller = testRunsController;
			
			loadProducts();
			
			loadTestPlans();
		}
		
		[MessageHandler(selector=TestRunsController.TESTRUNS_SEARCH_COMPLETED_EVENT)]
		override public function searchCompleted(event:SearchCompletedEvent):void {
			super.searchCompleted(event);
			
			data = new ListCollectionView(event.getResult());
		}
		
		[MessageHandler(selector=TESTRUN_NEW_EVENT)]
		public function newRun(event:LinkBarItemClickEvent):void {
			// Check that testplan was selected.
			if (this.selectedTestPlan==null){
				showError(new ErrorMessage(getMessageLocale("testrun", "testruns.grid.new.missing.testplan.title"), 
					getMessageLocale("testrun", "testruns.grid.new.missing.testplan.message")), null);
			}else {
				newCrud(new CrudTestRun);
			}
		}
		
		[MessageHandler(selector=TESTRUN_EDIT_EVENT)]
		public function editRun(event:LinkBarItemClickEvent):void {
			editCrud(new CrudTestRun);
		}
		
		[MessageHandler(selector=TESTRUN_DELETE_EVENT)]
		public function deleteRun(event:LinkBarItemClickEvent):void {
			deleteCrud();
		}
		
		[MessageHandler(selector=TESTRUN_RUNNING_EVENT)]
		public function startRun(event:LinkBarItemClickEvent):void {
			if (!isGridItemSelected()){
				return;
			}
			
			testRunsController.onStartClick(getSelectedItem().id, onStartStopReturnMessage);
		}
		
		[MessageHandler(selector=TESTRUN_STOPPED_EVENT)]
		public function stopRun(event:LinkBarItemClickEvent):void {
			if (!isGridItemSelected()){
				return;
			}
			
			testRunsController.onStopClick(getSelectedItem().id, onStartStopReturnMessage);
		}
		
		private function onStartStopReturnMessage(xml:XMLNode):void {
			if (displayReturnMessage(xml)){
				search(freeText);
			}
		}
		
		override public function getNewButton():Object {
			var newButton:Object = new Object();
			newButton.label = getMessageLocale("testrun", "testruns.linkbar.new");
			newButton.icon = NEW_TESTRUN;
			newButton.eventType = TESTRUN_NEW_EVENT;
			return newButton;
		}
		
		override public function getEditButton():Object {
			var editButton:Object = super.getEditButton();
			editButton.eventType = TESTRUN_EDIT_EVENT;
			return editButton;
		}
		
		override public function getDeleteButton():Object {
			var deleteButton:Object = super.getDeleteButton();
			deleteButton.eventType = TESTRUN_DELETE_EVENT;
			return deleteButton;
		}
		
		override public function getNewDomainObject():DomainObject{
			var testRun:TestRun = new TestRun();
			
			// Set selected plan id to new test run
			testRun.testPlanId = this.selectedTestPlan.id;
			testRun.productId = this.selectedTestPlan.product.id;
			
			return testRun;
		}
		
		[MessageHandler(selector=CrudTestRunPM.TESTRUNS_SEARCH_REFRESH_EVENT)]
		override public function onSearchRefreshEvent(event:SearchRefreshEvent):void {
			search(freeText, event.getPagingEvent());
		}
		
		override protected function addGridParameters(gridParameters:GridParameters):void {
			super.addGridParameters(gridParameters);
			
			(gridParameters as TestRunGridParameters).productId = this.selectedProduct!=null? String(this.selectedProduct.id): null;
			(gridParameters as TestRunGridParameters).testPlanId = this.selectedTestPlan!=null? String(this.selectedTestPlan.id): null;
		}
		
		override public function getModelName():String {
			return getMessageLocale("testrun", "testruns.crud.object.name");
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