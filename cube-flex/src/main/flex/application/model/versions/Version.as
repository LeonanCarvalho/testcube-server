package application.model.versions
{
	import application.model.DomainObject;
	import application.model.NameDescriptionObject;
	import application.model.products.Product;
	
	[XmlClass(alias="VERSION")]
	public class Version extends NameDescriptionObject {	
		[Bindable]
		[XmlElement(alias="PRODUCT")]
		public var product:Product;
		
		[Bindable]
		[XmlAttribute(alias="order")]
		public var order:String;
		
		public function Version(){
			
		}
	}
}