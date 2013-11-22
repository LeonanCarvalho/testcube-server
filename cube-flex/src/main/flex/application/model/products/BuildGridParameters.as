package application.model.products {
	import application.model.grid.GridKeywordParameters;

	/**
	 * Retrive Build according to productId or testPlanId
	 **/
	public class BuildGridParameters extends GridKeywordParameters {
 
		[XmlAttribute(alias="productId")]
		public var productId:String ;
				
		public function BuildGridParameters(limit:int, productId:int=0) {
			super(limit);
			
			this.productId = productId==0? null: String(productId);
		}
	}
}