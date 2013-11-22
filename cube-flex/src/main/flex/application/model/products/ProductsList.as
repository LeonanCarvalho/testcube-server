package application.model.products {	
	import application.model.XMLListRendered;
	
	import mx.collections.ArrayList;

	[XmlClass(alias="PRODUCTS")]
	public class ProductsList extends XMLListRendered {
		[XmlArray(alias="*", type="application.model.products.Product")]
		public var products:ArrayList;
		
		public function ProductsList() {
		
		}
	}
}