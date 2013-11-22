package application.model.products
{
	import application.model.NameDescriptionObject;
	
	[XmlClass(alias="CATEGORY")]
	public class Category extends NameDescriptionObject {		
		[Bindable]
		[XmlElement(alias="PRODUCT")]
		public var product:Product;
		
		public function Category(){
			
		}
	}
}