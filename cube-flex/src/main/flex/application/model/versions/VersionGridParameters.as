package application.model.versions {
	
	import application.model.grid.GridParameters;

	public class VersionGridParameters extends GridParameters {
		[XmlAttribute(alias="productId")]
		public var productId:String ;
		
		public function VersionGridParameters(limit:int, productId:int) {
			super(limit);
			
			this.productId = String(productId);
		}
	}
	
}