package presentation.model.admin.testrun {
	import application.controller.admin.testrun.RunStatusesController;
	import application.model.DomainObject;
	import application.model.testrun.RunStatus;
	
	import flash.events.MouseEvent;
	
	import presentation.model.panels.CrudPM;
	
	public class CrudRunStatusPM  extends CrudPM {
			
		[Embed("/images/admin/runstatuses-48.png")]
		private static const CASE_STATUSES_LARGE:Class;
		
		public static const RUNSTATUSES_SEARCH_REFRESH_EVENT:String = "RUNSTATUSES_SEARCH_REFRESH_EVENT";
		
		[Inject]
		public var runStatusesController:RunStatusesController;
		
		public function CrudRunStatusPM() {}
		
		[Init]
		override public function postConstructor():void {
			super.controller = runStatusesController;
		}
		
		override public function get largeIcon():Class {
			return CASE_STATUSES_LARGE;
		}
		
		override public function get modelName():String {
			return getMessageLocale("admin", "admin.administrator.runstatuses.crud.object.name");
		}
		
		override public function getNewDomainObject():DomainObject {
			return new RunStatus() as DomainObject;
		}
		
		override public function getRefreshEventType():String {
			return RUNSTATUSES_SEARCH_REFRESH_EVENT;
		}
		
		[Bindable]
		public function get runStatus():RunStatus {
			return super.getDomainObject() as RunStatus;
		}
		
		public function set runStatus(runStatus:RunStatus):void {
			return super.setDomainObject(runStatus as DomainObject);
		}
		
		override public function onSaveNewObject():void {
			this.runStatus = getNewDomainObject() as RunStatus;
		}
	}
}