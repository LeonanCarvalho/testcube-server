package presentation.model.admin.testcase {
	import application.controller.admin.testcase.CaseStatusesController;
	import application.model.DomainObject;
	import application.model.testcase.CaseStatus;
	
	import presentation.model.panels.CrudPM;
	
	public class CrudCaseStatusPM  extends CrudPM {
			
		[Embed("/images/admin/casestatuses-48.png")]
		private static const CASE_STATUSES_LARGE:Class;
		
		public static const CASESTATUSES_SEARCH_REFRESH_EVENT:String = "CASESTATUSES_SEARCH_REFRESH_EVENT";
		
		[Inject]
		public var caseStatusesController:CaseStatusesController;
		
		public function CrudCaseStatusPM() {}
		
		[Init]
		override public function postConstructor():void {
			super.controller = caseStatusesController;
		}
		
		override public function get largeIcon():Class {
			return CASE_STATUSES_LARGE;
		}
		
		override public function get modelName():String {
			return getMessageLocale("admin", "admin.administrator.casestatuses.crud.object.name");
		}
		
		override public function getNewDomainObject():DomainObject {
			return new CaseStatus() as DomainObject;
		}
		
		override public function getRefreshEventType():String {
			return CASESTATUSES_SEARCH_REFRESH_EVENT;
		}
		
		[Bindable]
		public function get caseStatus():CaseStatus {
			return super.getDomainObject() as CaseStatus;
		}
		
		public function set caseStatus(caseStatus:CaseStatus):void {
			return super.setDomainObject(caseStatus as DomainObject);
		}
		
		override public function onSaveNewObject():void {
			this.caseStatus = getNewDomainObject() as CaseStatus;
		}
	}
}