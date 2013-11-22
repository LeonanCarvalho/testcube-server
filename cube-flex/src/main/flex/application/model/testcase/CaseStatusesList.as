package application.model.testcase {	
	import application.model.XMLListRendered;
	
	import mx.collections.ArrayList;
	
	[XmlClass(alias="CASE_STATUSES")]
	public class CaseStatusesList extends XMLListRendered {
		[XmlArray(alias="*", type="application.model.testcase.CaseStatus")]
		public var statuses:ArrayList;
		
		public function CaseStatusesList() {
			
		}
	}
}