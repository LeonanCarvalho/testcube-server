package application.controller.admin.users {
	import application.API;
	import application.controller.AbstractController;
	import application.model.NameDescriptionObject;
	import application.model.grid.GridKeywordParameters;
	import application.model.grid.GridParameters;
	import application.model.users.User;
	import application.model.users.UsersList;
	
	import flash.xml.XMLNode;
	
	import presentation.events.panels.searchpanel.SearchCompletedEvent;

	public class UsersController extends AbstractController {
		public static const USERS_SEARCH_COMPLETED_EVENT:String = "USERS_SEARCH_COMPLETED_EVENT";
		public static const MODEL_NAME:String = "user";
		
		private var user_parameters:GridKeywordParameters = new GridKeywordParameters(GridParameters.DEFAULT_PAGE_SIZE, GridParameters.DEFAULT_PAGE_START, User.FIELD_USERNAME);

		public function UsersController() {}
		
		override public function toGridModel(xml:XMLNode):void{
			var users:UsersList = getFlexXBEngineInstance().deserialize(new XML(xml), UsersList) as UsersList;
			
			dispatcher(new SearchCompletedEvent(USERS_SEARCH_COMPLETED_EVENT, users.users, users.hasNext));
		}
		
		override public function get gridParameters():GridKeywordParameters {
			return user_parameters;
		}
		
		override protected function getModelName():String {
			return MODEL_NAME;
		}
	}
}