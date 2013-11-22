package application.model.testplan {	
	import application.model.XMLListRendered;
	
	import mx.collections.ArrayList;
	
	[XmlClass(alias="TESTPLANS")]
	public class TestPlansList extends XMLListRendered {
		[XmlArray(alias="*", type="application.model.testplan.TestPlan")]
		public var plans:ArrayList;
		
		public function TestPlansList() {
			
		}
	}
}