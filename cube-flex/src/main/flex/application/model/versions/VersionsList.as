package application.model.versions {	
	import application.model.XMLListRendered;
	
	import mx.collections.ArrayList;

	[XmlClass(alias="VERSIONS")]
	public class VersionsList extends XMLListRendered {
		[XmlArray(alias="*", type="application.model.versions.Version")]
		public var versions:ArrayList;
		
		public function VersionsList() {
		
		}
	}
}