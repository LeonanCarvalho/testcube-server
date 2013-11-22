package application.model.products
{
	import application.model.DomainObject;
	import application.model.NameDescriptionObject;

	[XmlClass(alias="ENVIRONMENT")]
	public class Environment extends NameDescriptionObject {	
		[Bindable]
		[XmlElement(alias="PRODUCT")]
		public var product:Product;
		
		[Bindable]
		[XmlAttribute(alias="active")]
		public var active:Boolean;
		
		public function Environment(){
			
		}
	}
}