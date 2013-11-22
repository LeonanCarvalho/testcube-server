package presentation.model.admin.testplan {
	import application.controller.admin.testplan.PlanTypesController;
	import application.model.DomainObject;
	import application.model.testplan.PlanType;
	
	import flash.events.MouseEvent;
	
	import presentation.model.panels.CrudPM;
	
	public class CrudPlanTypePM  extends CrudPM {
			
		[Embed("/images/admin/plantypes-48.png")]
		private static const PLAN_TYPES_LARGE:Class;
		
		public static const PLANTYPES_SEARCH_REFRESH_EVENT:String = "PLANTYPES_SEARCH_REFRESH_EVENT";
		
		[Inject]
		public var planTypesController:PlanTypesController;
		
		public function CrudPlanTypePM() {}
		
		[Init]
		override public function postConstructor():void {
			super.controller = planTypesController;
		}
		
		override public function get largeIcon():Class {
			return PLAN_TYPES_LARGE;
		}
		
		override public function get modelName():String {
			return getMessageLocale("admin", "admin.administrator.plantypes.crud.object.name");
		}
		
		override public function getNewDomainObject():DomainObject {
			return new PlanType() as DomainObject;
		}
		
		override public function getRefreshEventType():String {
			return PLANTYPES_SEARCH_REFRESH_EVENT;
		}
		
		[Bindable]
		public function get planType():PlanType {
			return super.getDomainObject() as PlanType;
		}
		
		public function set planType(planType:PlanType):void {
			return super.setDomainObject(planType as DomainObject);
		}
		
		override public function onSaveNewObject():void {
			this.planType = getNewDomainObject() as PlanType;
		}
	}
}