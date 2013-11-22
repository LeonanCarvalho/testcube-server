package application.model.testrun {
	
	[XmlClass(alias="RUN_STATISTICS")]
	public class RunStatistics {
		
		[Bindable]
		[XmlElement(alias="STATUS")]
		public var status:RunStatus;
		[Bindable]
		[XmlAttribute(alias="count")]
		public var count:String;
		[Bindable]
		[XmlAttribute(alias="total")]
		public var total:String;
		
		public function RunStatistics() {
		}
	}
}