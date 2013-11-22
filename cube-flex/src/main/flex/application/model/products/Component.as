package application.model.products{
	import application.model.DomainObject;
	import application.model.NameDescriptionObject;
	
	[XmlClass(alias="COMPONENT")]
	public class Component extends NameDescriptionObject {	
		[Bindable]
		[XmlElement(alias="PRODUCT")]
		public var product:Product;
		
		public function Component(){

		}
	}
}