package application.controller.admin.products {
	import application.controller.AbstractController;
	import application.model.grid.GridKeywordParameters;
	import application.model.products.ComponentsList;
	
	import flash.xml.XMLNode;
	
	import presentation.events.panels.searchpanel.SearchCompletedEvent;

	public class ComponentsController extends AbstractController {
		public static const COMPONENTS_SEARCH_COMPLETED_EVENT:String = "COMPONENTS_SEARCH_COMPLETED_EVENT";
		public static const MODEL_NAME:String = "component";
		
		private var component_parameters:GridKeywordParameters = new GridKeywordParameters();
		
		public function ComponentsController() {}
		
		override public function toGridModel(xml:XMLNode):void{
			var compoentns:ComponentsList = getFlexXBEngineInstance().deserialize(new XML(xml), ComponentsList) as ComponentsList;
			
			dispatcher(new SearchCompletedEvent(COMPONENTS_SEARCH_COMPLETED_EVENT, compoentns.components, compoentns.hasNext));
		}
		
		override public function get gridParameters():GridKeywordParameters {
			return component_parameters;
		}
		
		override protected function getModelName():String {
			return MODEL_NAME;
		}
	}
}