package application.model.products
{
	import application.model.grid.GridKeywordParameters;

	public class EnvironmentsGridParameters extends GridKeywordParameters{
		[XmlAttribute(alias="productId")]
		public var productId:String ;
		
		public function EnvironmentsGridParameters(limit:int, productId:int=0) {
			super(limit);
			
			this.productId = productId==0? null: String(productId);
		}
	
	}
}