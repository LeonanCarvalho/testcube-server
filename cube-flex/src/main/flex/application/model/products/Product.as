package application.model.products{
	import application.model.DomainObject;
	import application.model.NameDescriptionObject;
	
	[XmlClass(alias="PRODUCT")]
	public class Product extends NameDescriptionObject{	
		[Bindable]
		[XmlAttribute(alias="enabled")]
		public var enabled:Boolean;
		
		public function Product(){

		}
	}
}