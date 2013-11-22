package presentation.model.admin.testcase {
	import application.controller.admin.testcase.CasePrioritiesController;
	import application.model.DomainObject;
	import application.model.testcase.CasePriority;
	
	import flash.events.MouseEvent;
	
	import presentation.model.panels.CrudPM;
	
	public class CrudCasePriorityPM  extends CrudPM {
			
		[Embed("/images/admin/casepriorities-48.png")]
		private static const CASE_PRIORITIES_LARGE:Class;
		
		public static const CASEPRIORITIES_SEARCH_REFRESH_EVENT:String = "CASEPRIORITIES_SEARCH_REFRESH_EVENT";
		
		[Inject]
		public var casePrioritiesController:CasePrioritiesController;
		
		public function CrudCasePriorityPM() {}
		
		[Init]
		override public function postConstructor():void {
			super.controller = casePrioritiesController;
		}
		
		override public function get largeIcon():Class {
			return CASE_PRIORITIES_LARGE;
		}
		
		override public function get modelName():String {
			return getMessageLocale("admin", "admin.administrator.casepriorities.crud.object.name");
		}
		
		override public function getNewDomainObject():DomainObject {
			return new CasePriority() as DomainObject;
		}
		
		override public function getRefreshEventType():String {
			return CASEPRIORITIES_SEARCH_REFRESH_EVENT;
		}
		
		[Bindable]
		public function get casePriority():CasePriority {
			return super.getDomainObject() as CasePriority;
		}
		
		public function set casePriority(casePriority:CasePriority):void {
			return super.setDomainObject(casePriority as DomainObject);
		}
		
		override public function onSaveNewObject():void {
			this.casePriority = getNewDomainObject() as CasePriority;
		}
	}
}