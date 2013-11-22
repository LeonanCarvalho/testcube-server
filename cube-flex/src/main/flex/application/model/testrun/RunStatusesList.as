package application.model.testrun {	
	import application.model.XMLListRendered;
	
	import mx.collections.ArrayList;
	
	[XmlClass(alias="RUN_STATUSES")]
	public class RunStatusesList extends XMLListRendered {
		[XmlArray(alias="*", type="application.model.testrun.RunStatus")]
		public var statuses:ArrayList;
		
		public function RunStatusesList() {
			
		}
	}
}