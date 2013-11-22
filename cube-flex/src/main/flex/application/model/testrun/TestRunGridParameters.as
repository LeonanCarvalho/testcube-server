package application.model.testrun {
	
	import application.model.grid.GridKeywordParameters;

	public class TestRunGridParameters extends GridKeywordParameters { 
		[XmlAttribute(alias="productId")]
		public var productId:String = null;
		
		[XmlAttribute(alias="testPlanId")]
		public var testPlanId:String = null;
		
		public function TestRunGridParameters() {
			
		}
	}
}