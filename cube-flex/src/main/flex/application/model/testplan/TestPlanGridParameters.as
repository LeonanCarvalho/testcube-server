package application.model.testplan {
	
	import application.model.grid.GridKeywordParameters;
	import application.model.grid.GridParameters;

	public class TestPlanGridParameters extends GridKeywordParameters { 
		[XmlAttribute(alias="productId")]
		public var productId:String = null;
		
		
		public function TestPlanGridParameters(productId:String = null, limit:int = GridParameters.DEFAULT_PAGE_SIZE, start:int = GridParameters.DEFAULT_PAGE_START, sorting:String = "id"){
			super(limit, start, sorting);
			
			this.productId = productId;
		}
	
	}
}