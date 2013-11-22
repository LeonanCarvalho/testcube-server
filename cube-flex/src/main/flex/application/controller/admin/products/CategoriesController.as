package application.controller.admin.products {
	import application.controller.AbstractController;
	import application.model.grid.GridKeywordParameters;
	import application.model.products.CategoriesList;
	
	import flash.xml.XMLNode;
	
	import presentation.events.panels.searchpanel.SearchCompletedEvent;

	public class CategoriesController extends AbstractController {
		public static const CATEGORIES_SEARCH_COMPLETED_EVENT:String = "CATEGORIES_SEARCH_COMPLETED_EVENT";
		public static const MODEL_NAME:String = "category";
		
		private var category_parameters:GridKeywordParameters = new GridKeywordParameters();
		
		public function CategoriesController() {}
		
		override public function toGridModel(xml:XMLNode):void{
			var categories:CategoriesList = getFlexXBEngineInstance().deserialize(new XML(xml), CategoriesList) as CategoriesList;
			
			dispatcher(new SearchCompletedEvent(CATEGORIES_SEARCH_COMPLETED_EVENT, categories.categories, categories.hasNext));
		}
		
		override public function get gridParameters():GridKeywordParameters {
			return category_parameters;
		}
		
		override protected function getModelName():String {
			return MODEL_NAME;
		}
	}
}