package presentation.model.testrun {
	
	import application.controller.admin.products.BuildsController;
	import application.controller.admin.products.EnvironmentsController;
	import application.controller.admin.products.VersionsController;
	import application.controller.admin.users.UsersController;
	import application.controller.testrun.TestRunsController;
	import application.model.DomainObject;
	import application.model.grid.GridParameters;
	import application.model.products.BuildGridParameters;
	import application.model.products.BuildsList;
	import application.model.products.EnvironmentsGridParameters;
	import application.model.products.EnvironmentsList;
	import application.model.testcase.TestCase;
	import application.model.testrun.TestRun;
	import application.model.users.UsersList;
	import application.model.versions.VersionGridParameters;
	import application.model.versions.VersionsList;
	
	import flash.events.MouseEvent;
	import flash.xml.XMLNode;
	
	import mx.collections.ArrayList;
	
	import presentation.model.panels.CrudPM;
	
	import utils.ComboBoxUtils;
	
	public class CrudTestRunPM  extends CrudPM {
			
		[Embed("/images/testrun/testruns-48.png")]
		private static const TESTRUNS_LARGE:Class;
		
		public static const TESTRUNS_SEARCH_REFRESH_EVENT:String = "TESTRUNS_SEARCH_REFRESH_EVENT";
		
		[Inject]
		public var testRunsController:TestRunsController;
		[Inject]
		public var usersController:UsersController;
		[Inject]
		public var versionsController:VersionsController;
		[Inject]
		public var buildsController:BuildsController;
		[Inject]
		public var environmentsController:EnvironmentsController;
		
		[Bindable]
		public var users:ArrayList;
		[Bindable]
		public var versions:ArrayList;
		[Bindable]
		public var builds:ArrayList;
		[Bindable]
		public var environments:ArrayList;
		
		[Bindable]
		public var assigneeSelectedIndex:int = ComboBoxUtils.NON_SELECTED_INDEX;
		[Bindable]
		public var versionSelectedIndex:int = ComboBoxUtils.NON_SELECTED_INDEX;
		[Bindable]
		public var buildSelectedIndex:int = ComboBoxUtils.NON_SELECTED_INDEX;
		[Bindable]
		public var environmentSelectedIndex:int = ComboBoxUtils.NON_SELECTED_INDEX;
		
		public function CrudTestRunPM() {}
		
		[Init]
		override public function postConstructor():void {
			super.controller = testRunsController;
			
			this.loadUsers();
		}
		
		override public function get largeIcon():Class {
			return TESTRUNS_LARGE;
		}
		
		override public function get modelName():String {
			return getMessageLocale("testrun", "testruns.crud.object.name");
		}
		
		override public function getNewDomainObject():DomainObject {
			var newTestRun:TestRun =  new TestRun();
			
			// Copy testplan id from old testplan (Used for Save&New)
			if (this.testRun != null){
				newTestRun.testPlanId = this.testRun.testPlanId;
				newTestRun.productId = this.testRun.productId;
				newTestRun.status = this.testRun.status;
			}
			
			return newTestRun as DomainObject;
		}
		
		override public function getRefreshEventType():String {
			return TESTRUNS_SEARCH_REFRESH_EVENT;
		}
		
		[Bindable]
		public function get testRun():TestRun {
			return super.getDomainObject() as TestRun;
		}
		
		public function set testRun(testRun:TestRun):void {
			return super.setDomainObject(testRun as DomainObject);
		}
		
		private function loadUsers():void {
			usersController.searchByParameters(toUsersModel, new GridParameters(ComboBoxUtils.COMBOBOX_LIMIT));
		}
		
		private function toUsersModel(xml:XMLNode):void{
			var usersList:UsersList = getFlexXBEngineInstance().deserialize(new XML(xml), UsersList) as UsersList;
			this.users = usersList.users;
			
			assigneeSelectedIndex = ComboBoxUtils.getSelected(this.users, testRun.assignee);
		}
		
		public function loadVersions():void {
			if (this.testRun == null || this.testRun.testPlanId == ComboBoxUtils.NON_SELECTED_INDEX) 
				return;
			versionsController.searchByParameters(toVersionsModel, new VersionGridParameters(ComboBoxUtils.COMBOBOX_LIMIT, this.testRun.productId));
		}
		
		private function toVersionsModel(xml:XMLNode):void{
			var versions:VersionsList = getFlexXBEngineInstance().deserialize(new XML(xml), VersionsList) as VersionsList;
			this.versions = versions.versions;
			
			versionSelectedIndex = ComboBoxUtils.getSelected(this.versions, testRun.version);
		}
		
		public function loadBuilds():void {
			if (this.testRun == null || this.testRun.testPlanId == ComboBoxUtils.NON_SELECTED_INDEX) 
				return;
			buildsController.searchByParameters(toBuildsModel, new BuildGridParameters(ComboBoxUtils.COMBOBOX_LIMIT, this.testRun.productId));
		}
		
		private function toBuildsModel(xml:XMLNode):void{
			var builds:BuildsList = getFlexXBEngineInstance().deserialize(new XML(xml), BuildsList) as BuildsList;
			this.builds = builds.builds;
			
			buildSelectedIndex = ComboBoxUtils.getSelected(this.builds, testRun.build);
		}
		
		public function loadEnvironments():void {
			if (this.testRun == null || this.testRun.testPlanId == ComboBoxUtils.NON_SELECTED_INDEX) 
				return;
			environmentsController.searchByParameters(toEnvironmentsModel, new EnvironmentsGridParameters(ComboBoxUtils.COMBOBOX_LIMIT, this.testRun.productId));
		}
		
		private function toEnvironmentsModel(xml:XMLNode):void{
			var environments:EnvironmentsList = getFlexXBEngineInstance().deserialize(new XML(xml), EnvironmentsList) as EnvironmentsList;
			this.environments = environments.environments;
			
			environmentSelectedIndex = ComboBoxUtils.getSelected(this.environments, testRun.environment);
		}
				
		override public function onSaveNewObject():void {
			this.testRun = getNewDomainObject() as TestRun;
		}
	}
}