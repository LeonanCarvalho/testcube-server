package application.model.users  {	
	import application.model.XMLListRendered;
	
	import mx.collections.ArrayList;

	[XmlClass(alias="USERS")]
	public class UsersList extends XMLListRendered {
		[XmlArray(alias="*", type="application.model.users.User")]
		public var users:ArrayList;
		
		public function UsersList() {
		
		}
	}
}