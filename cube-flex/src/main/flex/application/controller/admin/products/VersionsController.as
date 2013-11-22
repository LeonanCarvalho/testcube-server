package application.controller.admin.products {
	import application.controller.AbstractController;
	import application.model.grid.GridKeywordParameters;
	import application.model.products.BuildsList;
	import application.model.products.EnvironmentsList;
	import application.model.versions.VersionsList;
	
	import flash.xml.XMLNode;
	
	import presentation.events.panels.searchpanel.SearchCompletedEvent;

	public class VersionsController extends AbstractController {
		public static const VERSIONS_SEARCH_COMPLETED_EVENT:String = "VERSIONS_SEARCH_COMPLETED_EVENT";
		public static const MODEL_NAME:String = "version";
		
		private var version_parameters:GridKeywordParameters = new GridKeywordParameters();
		
		public function VersionsController() {}
		
		override public function toGridModel(xml:XMLNode):void{
			var versions:VersionsList = getFlexXBEngineInstance().deserialize(new XML(xml), VersionsList) as VersionsList;
			
			dispatcher(new SearchCompletedEvent(VERSIONS_SEARCH_COMPLETED_EVENT, versions.versions, versions.hasNext));
		}
		
		override public function get gridParameters():GridKeywordParameters {
			return version_parameters;
		}
		
		override protected function getModelName():String {
			return MODEL_NAME;
		}
	}
}