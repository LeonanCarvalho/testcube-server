package application.model.products {	
	import application.model.XMLListRendered;
	
	import mx.collections.ArrayList;

	[XmlClass(alias="CATEGORIES")]
	public class CategoriesList extends XMLListRendered {
		[XmlArray(alias="*", type="application.model.products.Category")]
		public var categories:ArrayList;
		
		public function CategoriesList() {
		
		}
	}
}