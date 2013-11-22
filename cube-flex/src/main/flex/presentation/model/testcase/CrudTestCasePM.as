package presentation.model.testcase {
	
	import application.controller.admin.products.CategoriesController;
	import application.controller.admin.testcase.CasePrioritiesController;
	import application.controller.admin.testcase.CaseStatusesController;
	import application.controller.admin.users.UsersController;
	import application.controller.testcase.TestCasesController;
	import application.controller.testplan.TestPlansController;
	import application.model.DomainObject;
	import application.model.grid.GridParameters;
	import application.model.products.CategoriesList;
	import application.model.products.CategoryGridParameters;
	import application.model.testcase.CasePrioritiesList;
	import application.model.testcase.CaseStatusesList;
	import application.model.testcase.TestCase;
	import application.model.testcase.TestCasesList;
	import application.model.testplan.TestPlansList;
	import application.model.users.UsersList;
	
	import flash.events.MouseEvent;
	import flash.xml.XMLNode;
	
	import mx.collections.ArrayList;
	
	import presentation.model.panels.CrudPM;
	
	import utils.ComboBoxUtils;
	
	public class CrudTestCasePM  extends CrudPM {
			
		[Embed("/images/testcase/testcases-48.png")]
		private static const TESTCASES_LARGE:Class;
		
		public static const TESTCASES_SEARCH_REFRESH_EVENT:String = "TESTCASES_SEARCH_REFRESH_EVENT";
		
		[Inject]
		public var testCasesController:TestCasesController;
		[Inject]
		public var usersController:UsersController;
		[Inject]
		public var prioritiesController:CasePrioritiesController;
		[Inject]
		public var categoriesController:CategoriesController;		
		[Inject]
		public var statusesController:CaseStatusesController;		
		
		
		[Bindable]
		public var plans:ArrayList;
		[Bindable]
		public var users:ArrayList;
		[Bindable]
		public var priorities:ArrayList;
		[Bindable]
		public var categories:ArrayList;
		[Bindable]
		public var statuses:ArrayList;
		
		[Bindable]
		public var assigneeSelectedIndex:int = ComboBoxUtils.NON_SELECTED_INDEX;
		[Bindable]
		public var prioritySelectedIndex:int = ComboBoxUtils.NON_SELECTED_INDEX;
		[Bindable]
		public var categorySelectedIndex:int = ComboBoxUtils.NON_SELECTED_INDEX;
		[Bindable]
		public var statusSelectedIndex:int = ComboBoxUtils.NON_SELECTED_INDEX;
		
		public function CrudTestCasePM() {}
		
		[Init]
		override public function postConstructor():void {
			super.controller = testCasesController;
			
			this.loadUsers();
			this.loadPriorities();
			this.loadstatuses();
		}
		
		override public function get largeIcon():Class {
			return TESTCASES_LARGE;
		}
		
		override public function get modelName():String {
			return getMessageLocale("testcase", "testcases.crud.object.name");
		}
		
		override public function getNewDomainObject():DomainObject {
			var newTestCase:TestCase = new TestCase();
			
			// Copy testplan id from old testplan (Used for Save&New)
			if (this.testCase !=null){
				newTestCase.testPlanId = this.testCase.testPlanId;
			}
				
			return newTestCase as DomainObject;
		}
		
		override public function getRefreshEventType():String {
			return TESTCASES_SEARCH_REFRESH_EVENT;
		}
		
		[Bindable]
		public function get testCase():TestCase {
			return super.getDomainObject() as TestCase;
		}
		
		public function set testCase(testCase:TestCase):void {
			return super.setDomainObject(testCase as DomainObject);
		}
		
		private function loadUsers():void {
			usersController.searchByParameters(toUsersModel, new GridParameters(ComboBoxUtils.COMBOBOX_LIMIT));
		}
		
		private function toUsersModel(xml:XMLNode):void{
			var usersList:UsersList = getFlexXBEngineInstance().deserialize(new XML(xml), UsersList) as UsersList;
			this.users = usersList.users;
			
			assigneeSelectedIndex = ComboBoxUtils.getSelected(this.users, testCase.assignee);
		}
		
		private function loadPriorities():void {
			prioritiesController.searchByParameters(toPrioritiesModel, new GridParameters(ComboBoxUtils.COMBOBOX_LIMIT));
		}
		
		private function toPrioritiesModel(xml:XMLNode):void{
			var prioritiesList:CasePrioritiesList = getFlexXBEngineInstance().deserialize(new XML(xml), CasePrioritiesList) as CasePrioritiesList;
			this.priorities = prioritiesList.priorities;
			
			prioritySelectedIndex = ComboBoxUtils.getSelectedIndex(this.priorities, testCase.priorityId);
		}
		
		// Should be called only after injectionComplete is triggerd.
		// testCase will be populated from CrudTestCase.mxml injectionComplete method.
		public function loadCategories():void {
			categoriesController.searchByParameters(toCategoriesModel, new CategoryGridParameters(ComboBoxUtils.COMBOBOX_LIMIT, 0, this.testCase!=null? this.testCase.testPlanId: null));
		}
		
		private function toCategoriesModel(xml:XMLNode):void{
			var categoriesList:CategoriesList = getFlexXBEngineInstance().deserialize(new XML(xml), CategoriesList) as CategoriesList;
			this.categories = categoriesList.categories;
			
			categorySelectedIndex = ComboBoxUtils.getSelectedIndex(this.categories, testCase.categoryId);
		}
		
		private function loadstatuses():void {
			statusesController.searchByParameters(toStatusesModel, new GridParameters(ComboBoxUtils.COMBOBOX_LIMIT));
		}
		
		private function toStatusesModel(xml:XMLNode):void{
			var statusesList:CaseStatusesList = getFlexXBEngineInstance().deserialize(new XML(xml), CaseStatusesList) as CaseStatusesList;
			this.statuses = statusesList.statuses;
			
			statusSelectedIndex = ComboBoxUtils.getSelectedIndex(this.statuses, testCase.statusId);
			
			// Set Default value to Proposed
			if (isNewState() && statusSelectedIndex == ComboBoxUtils.NON_SELECTED_INDEX && this.statuses != null && this.statuses.length > 0){
				statusSelectedIndex = 0;
				testCase.statusId = statusesList.statuses.getItemAt(0).id;
			}
		}
		
		override public function onSaveNewObject():void {
			this.testCase = getNewDomainObject() as TestCase;
		}
		
	}
}