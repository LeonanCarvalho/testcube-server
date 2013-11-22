package application.model.products {	
	import application.model.XMLListRendered;
	
	import mx.collections.ArrayList;

	[XmlClass(alias="COMPONENTS")]
	public class ComponentsList extends XMLListRendered{
		[XmlArray(alias="*", type="application.model.products.Component")]
		public var components:ArrayList;
		
		public function ComponentsList() {
		
		}
	}
}