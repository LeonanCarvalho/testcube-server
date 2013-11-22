package application.model.products {	
	import application.model.XMLListRendered;
	
	import mx.collections.ArrayList;

	[XmlClass(alias="BUILDS")]
	public class BuildsList extends XMLListRendered{
		[XmlArray(alias="*", type="application.model.products.Build")]
		public var builds:ArrayList;
		
		public function BuildsList() {
		
		}
	}
}