package application.model.testcase {	
	import application.model.XMLListRendered;
	
	import mx.collections.ArrayList;

	[XmlClass(alias="CASE_PRIORITIES")]
	public class CasePrioritiesList extends XMLListRendered {
		[XmlArray(alias="*", type="application.model.testcase.CasePriority")]
		public var priorities:ArrayList;
		
		public function CasePrioritiesList() {
		
		}
	}
}