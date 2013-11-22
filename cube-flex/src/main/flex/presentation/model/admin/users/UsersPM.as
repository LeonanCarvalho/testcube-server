package presentation.model.admin.users {
	import application.controller.admin.users.UsersController;
	import application.model.DomainObject;
	import application.model.users.User;
	
	import infrastructure.UserConfig;
	
	import mx.collections.ArrayList;
	import mx.collections.ListCollectionView;
	
	import org.spicefactory.parsley.messaging.MessageHandler;
	
	import presentation.events.panels.searchpanel.LinkBarItemClickEvent;
	import presentation.events.panels.searchpanel.SearchCompletedEvent;
	import presentation.events.panels.searchpanel.SearchRefreshEvent;
	import presentation.model.panels.SearchablePM;
	import presentation.view.admin.CrudUser;
	
	import utils.grid.GridUtils;
	
	public class UsersPM extends SearchablePM {
		private static const USERS_NEW_EVENT:String = "USERS_NEW_EVENT";
		private static const USERS_EDIT_EVENT:String = "USERS_EDIT_EVENT";
		private static const USERS_DELETE_EVENT:String = "USERS_DELETE_EVENT";
		
		[Inject]
		public var usersController:UsersController;
		
		[Embed("/images/admin/users-16.png")]
		private static const NEW_USER:Class;
		
		public function UsersPM() {
			super();
			
			columns = new ArrayList();
			columns.addItem(GridUtils.getSortableColumn("id", getMessageLocale("admin", "admin.administrator.users.grid.id"), true, true, ID_DEFAULT_WIDTH));
			columns.addItem(GridUtils.getSortableColumn("username", getMessageLocale("admin", "admin.administrator.users.grid.username"), true, true));						
			columns.addItem(GridUtils.getSortableColumn("firstName", getMessageLocale("admin", "admin.administrator.users.grid.firstName"), true, true));
			columns.addItem(GridUtils.getSortableColumn("lastName", getMessageLocale("admin", "admin.administrator.users.grid.lastName"), true, true));
			
			columns.addItem(GridUtils.getColumn("administrator", getMessageLocale("admin", "admin.administrator.users.grid.administrator"), true));
			columns.addItem(GridUtils.getColumn("credentialsExpired", getMessageLocale("admin", "admin.administrator.users.grid.credentialsExpired"), true));
			columns.addItem(GridUtils.getColumn("accountExpired", getMessageLocale("admin", "admin.administrator.users.grid.accountExpired"), true));
			columns.addItem(GridUtils.getColumn("accountLocked", getMessageLocale("admin", "admin.administrator.users.grid.accountLocked"), true));
		}
		
		[Init]
		override public function postConstructor():void {
			super.controller = usersController;
		}
		
		[MessageHandler(selector=UsersController.USERS_SEARCH_COMPLETED_EVENT)]
		override public function searchCompleted(event:SearchCompletedEvent):void {
			super.searchCompleted(event);
			
			data = new ListCollectionView(event.getResult());
		}
		
		[MessageHandler(selector=USERS_NEW_EVENT)]
		public function newUser(event:LinkBarItemClickEvent):void {
			newCrud(new CrudUser);
		}
		
		[MessageHandler(selector=USERS_EDIT_EVENT)]
		public function editUser(event:LinkBarItemClickEvent):void {
			editCrud(new CrudUser);
		}
		
		[MessageHandler(selector=USERS_DELETE_EVENT)]
		public function deleteUser(event:LinkBarItemClickEvent):void {
			deleteCrud();
		}
		
		override public function getNewButton():Object {
			var newButton:Object = new Object();
			newButton.label = getMessageLocale("admin", "admin.administrator.users.linkbar.new");
			newButton.icon = NEW_USER;
			newButton.eventType = USERS_NEW_EVENT;
			return newButton;
		}
		
		override public function getEditButton():Object {
			var editButton:Object = super.getEditButton();
			editButton.eventType = USERS_EDIT_EVENT;
			return editButton;
		}
		
		override public function getDeleteButton():Object {
			var deleteButton:Object = super.getDeleteButton();
			deleteButton.eventType = USERS_DELETE_EVENT;
			return deleteButton;
		}
		
		override public function getNewDomainObject():DomainObject{
			return new User();
		}		
		
		[MessageHandler(selector=CrudUserPM.USERS_SEARCH_REFRESH_EVENT)]
		override public function onSearchRefreshEvent(event:SearchRefreshEvent):void {
			search(freeText);
		}
		
		override public function getModelName():String {
			return getMessageLocale("admin", "admin.administrator.users.crud.object.name");
		}
	}
}