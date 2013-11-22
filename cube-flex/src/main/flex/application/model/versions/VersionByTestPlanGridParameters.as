package application.model.versions {
	
	import application.model.grid.GridParameters;

	public class VersionByTestPlanGridParameters extends GridParameters {
		[XmlAttribute(alias="testPlanId")]
		public var testPlanId:String ;
	
		public function VersionByTestPlanGridParameters(limit:int, testPlanId:int) {
			super(limit);
			
			this.testPlanId = String(testPlanId);
		}
	}
	
}