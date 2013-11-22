package application.model.users{
	import application.model.DomainObject;
	import application.model.security.IUser;
	
	[XmlClass(alias="USER")]
	public class User extends DomainObject implements IUser{		
		public static const FIELD_USERNAME:String = "username";
		
		[Bindable]
		[XmlElement(alias="FIRST_NAME")]
		public var firstName:String;

		[Bindable]
		[XmlElement(alias="LAST_NAME")]
		public var lastName:String;
		
		[Bindable]
		[XmlElement(alias="USER_NAME")]
		public var username:String;
		
		[Bindable]
		[XmlAttribute(alias="personalId")]
		public var personalid:String;
		
		[Bindable]
		[XmlAttribute(alias="administrator")]
		public var administrator:Boolean;
		
		// Always send true because there is no GUI access to this.
		[Bindable]
		[XmlAttribute(alias="enabled")]
		public var enabled:Boolean = true;   
		
		[Bindable]
		[XmlAttribute(alias="credentialsNonExpired")]
		public var credentialsNonExpired:Boolean = true;
		
		[Bindable]
		[XmlAttribute(alias="accountNonExpired")]
		public var accountNonExpired:Boolean = true;
		
		[Bindable]
		[XmlAttribute(alias="accountNonLocked")]
		public var accountNonLocked:Boolean = true;
		
		[Bindable]
		[XmlElement(alias="PASSWORD")]
		public var password:String=null;
		
		[Bindable]
		[XmlElement(alias="CONFIRMPASSWORD")]
		public var confirmpassword:String=null;
		
		public function User(){

		}
		
		[Bindable]
		public function get credentialsExpired():Boolean{
			return !credentialsNonExpired;	
		}
		
		public function set credentialsExpired(credentialsExpired:Boolean):void{
			this.credentialsNonExpired = !credentialsExpired;
		}
		
		[Bindable]
		public function get accountExpired():Boolean{
			return !accountNonExpired;	
		}
		
		public function set accountExpired(accountExpired:Boolean):void{
			this.accountNonExpired = !accountExpired;
		}
		
		[Bindable]
		public function get accountLocked():Boolean{
			return !accountNonLocked;	
		}
		
		public function set accountLocked(accountLocked:Boolean):void{
			this.accountNonLocked = !accountLocked;
		}
	}
}