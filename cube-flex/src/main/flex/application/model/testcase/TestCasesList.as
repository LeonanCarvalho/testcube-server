package application.model.testcase {	
	import application.model.XMLListRendered;
	
	import mx.collections.ArrayList;
	
	[XmlClass(alias="TESTCASES")]
	public class TestCasesList extends XMLListRendered {
		[XmlArray(alias="*", type="application.model.testcase.TestCase")]
		public var cases:ArrayList;
		
		public function TestCasesList() {
			
		}
	}
}