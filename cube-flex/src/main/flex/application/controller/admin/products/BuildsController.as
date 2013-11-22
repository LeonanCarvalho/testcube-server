package application.controller.admin.products {
	import application.controller.AbstractController;
	import application.model.grid.GridKeywordParameters;
	import application.model.products.BuildsList;
	import application.model.products.EnvironmentsList;
	
	import flash.xml.XMLNode;
	
	import presentation.events.panels.searchpanel.SearchCompletedEvent;

	public class BuildsController extends AbstractController {
		public static const BUILDS_SEARCH_COMPLETED_EVENT:String = "BUILDS_SEARCH_COMPLETED_EVENT";
		public static const MODEL_NAME:String = "build";
		
		private var build_parameters:GridKeywordParameters = new GridKeywordParameters();
		
		public function BuildsController() {}
		
		override public function toGridModel(xml:XMLNode):void{
			var builds:BuildsList = getFlexXBEngineInstance().deserialize(new XML(xml), BuildsList) as BuildsList;
			
			dispatcher(new SearchCompletedEvent(BUILDS_SEARCH_COMPLETED_EVENT, builds.builds, builds.hasNext));
		}
		
		override public function get gridParameters():GridKeywordParameters {
			return build_parameters;
		}
		
		override protected function getModelName():String {
			return MODEL_NAME;
		}
	}
}