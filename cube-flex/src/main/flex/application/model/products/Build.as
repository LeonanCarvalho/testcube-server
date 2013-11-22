package application.model.products
{
	import application.model.NameDescriptionObject;
	
	[XmlClass(alias="BUILD")]
	public class Build extends NameDescriptionObject {	
		[Bindable]
		[XmlElement(alias="PRODUCT")]
		public var product:Product;
		
		[Bindable]
		[XmlAttribute(alias="active")]
		public var active:Boolean;
		
		public function Build(){
			
		}
	}
}