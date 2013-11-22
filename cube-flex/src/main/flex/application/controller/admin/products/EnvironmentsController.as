package application.controller.admin.products {
	import application.controller.AbstractController;
	import application.model.grid.GridKeywordParameters;
	import application.model.products.EnvironmentsList;
	
	import flash.xml.XMLNode;
	
	import presentation.events.panels.searchpanel.SearchCompletedEvent;

	public class EnvironmentsController extends AbstractController {
		public static const ENVIRONMENTS_SEARCH_COMPLETED_EVENT:String = "ENVIRONMENTS_SEARCH_COMPLETED_EVENT";
		public static const MODEL_NAME:String = "environment";
		
		private var environment_parameters:GridKeywordParameters = new GridKeywordParameters();
		
		public function EnvironmentsController() {}
		
		override public function toGridModel(xml:XMLNode):void{
			var environments:EnvironmentsList = getFlexXBEngineInstance().deserialize(new XML(xml), EnvironmentsList) as EnvironmentsList;
			
			dispatcher(new SearchCompletedEvent(ENVIRONMENTS_SEARCH_COMPLETED_EVENT, environments.environments, environments.hasNext));
		}
		
		override public function get gridParameters():GridKeywordParameters {
			return environment_parameters;
		}
		
		override protected function getModelName():String {
			return MODEL_NAME;
		}
	}
}