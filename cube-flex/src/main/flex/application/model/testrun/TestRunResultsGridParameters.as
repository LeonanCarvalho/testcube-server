package application.model.testrun {
	
	import application.model.grid.GridKeywordParameters;
	
	import presentation.model.testrun.TestRunResultsPM;

	public class TestRunResultsGridParameters extends GridKeywordParameters { 
		[XmlAttribute(alias="testRunId")]
		public var testRunId:String = null;
		
		[XmlAttribute(alias="testPlanId")]
		public var testPlanId:String = null;
		
		public var subscriber:TestRunResultsPM; 
		
		public function TestRunResultsGridParameters(limit:int = DEFAULT_PAGE_SIZE, start:int = DEFAULT_PAGE_START, sorting:String = "id"){
			super(limit, start, sorting);
		}
	}
}