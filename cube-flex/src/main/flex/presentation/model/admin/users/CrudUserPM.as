package presentation.model.admin.users {
	import application.controller.admin.users.UsersController;
	import application.model.DomainObject;
	import application.model.users.User;
	
	import flash.events.MouseEvent;
	
	import presentation.model.panels.CrudPM;

	public class CrudUserPM extends CrudPM {
		[Embed("/images/admin/users-48.png")]
		private static const USER_LARGE:Class;
		public static const USERS_SEARCH_REFRESH_EVENT:String = "USERS_SEARCH_REFRESH_EVENT";
		
		[Inject]
		public var usersController:UsersController;
		
		public function CrudUserPM() {}
		
		[Init]
		override public function postConstructor():void {
			super.controller = usersController;
		}
		
		override public function get largeIcon():Class {
			return USER_LARGE;
		}
		
		override public function get modelName():String {
			return getMessageLocale("admin", "admin.administrator.users.crud.object.name");
		}
		
		override public function getNewDomainObject():DomainObject{
			return new User() as DomainObject;
		}
		
		override public function getRefreshEventType():String {
			return USERS_SEARCH_REFRESH_EVENT;
		}
		
		[Bindable]
		public function get user():User {
			return super.getDomainObject() as User;
		}
		
		public function set user(user:User):void {
			return super.setDomainObject(user as DomainObject);
		}
		
		override public function onSaveNewObject():void {	
			this.user = getNewDomainObject() as User;
		}
	}
}