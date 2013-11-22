package application.controller.admin.products {
	import application.controller.AbstractController;
	import application.model.NameDescriptionObject;
	import application.model.grid.GridKeywordParameters;
	import application.model.grid.GridParameters;
	import application.model.products.ProductsList;
	
	import flash.xml.XMLNode;
	
	import presentation.events.panels.searchpanel.SearchCompletedEvent;

	public class ProductsController extends AbstractController {
		public static const PRODUCTS_SEARCH_COMPLETED_EVENT:String = "PRODUCTS_SEARCH_COMPLETED_EVENT";
		public static const MODEL_NAME:String = "product";
		
		private var product_parameters:GridKeywordParameters = new GridKeywordParameters(GridParameters.DEFAULT_PAGE_SIZE, GridParameters.DEFAULT_PAGE_START, NameDescriptionObject.FILED_NAME);
		
		public function ProductsController() {}
		
		override public function toGridModel(xml:XMLNode):void{
			var products:ProductsList = getFlexXBEngineInstance().deserialize(new XML(xml), ProductsList) as ProductsList;
			
			dispatcher(new SearchCompletedEvent(PRODUCTS_SEARCH_COMPLETED_EVENT, products.products, products.hasNext));
		}
		
		override public function get gridParameters():GridKeywordParameters {
			return product_parameters;
		}
		
		override protected function getModelName():String {
			return MODEL_NAME;
		}
	}
}