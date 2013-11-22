package application.model.products {	
	import application.model.XMLListRendered;
	
	import mx.collections.ArrayList;

	[XmlClass(alias="ENVIRONMENTS")]
	public class EnvironmentsList extends XMLListRendered {
		[XmlArray(alias="*", type="application.model.products.Environment")]
		public var environments:ArrayList;
		
		public function EnvironmentsList() {
		
		}
	}
}