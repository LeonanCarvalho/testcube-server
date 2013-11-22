package application.model.products {
	import application.model.grid.GridParameters;

	/**
	 * Retrive Categories according to productId or testPlanId
	 **/
	public class CategoryGridParameters extends GridParameters {

 
		[XmlAttribute(alias="productId")]
		public var productId:String ;
		
		[XmlAttribute(alias="testPlanId")]
		public var testPlanId:String ;
		
		public function CategoryGridParameters(limit:int, productId:int=0, testPlanId:int=0) {
			super(limit);
			
			this.productId = productId==0? null: String(productId);
			this.testPlanId = testPlanId==0? null: String(testPlanId);
		}
	}
}