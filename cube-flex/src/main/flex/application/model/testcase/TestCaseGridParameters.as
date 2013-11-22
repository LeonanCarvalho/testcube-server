package application.model.testcase {
	
	import application.model.grid.GridKeywordParameters;

	public class TestCaseGridParameters extends GridKeywordParameters { 
		[XmlAttribute(alias="productId")]
		public var productId:String = null;
		
		[XmlAttribute(alias="testPlanId")]
		public var testPlanId:String = null;
		
		public function TestCaseGridParameters() {
			
		}
	}
}