package application.model.testplan {	
	import application.model.XMLListRendered;
	
	import mx.collections.ArrayList;
	
	[XmlClass(alias="PLAN_TYPES")]
	public class PlanTypesList extends XMLListRendered {
		[XmlArray(alias="*", type="application.model.testplan.PlanType")]
		public var types:ArrayList;
		
		public function PlanTypesList() {
			
		}
	}
}