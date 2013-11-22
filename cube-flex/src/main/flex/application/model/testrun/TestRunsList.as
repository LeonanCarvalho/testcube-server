package application.model.testrun {	
	import application.model.XMLListRendered;
	
	import mx.collections.ArrayList;
	
	[XmlClass(alias="TESTRUNS")]
	public class TestRunsList extends XMLListRendered {
		[XmlArray(alias="*", type="application.model.testrun.TestRun")]
		public var runs:ArrayList;
		
		public function TestRunsList() {
			
		}
	}
}