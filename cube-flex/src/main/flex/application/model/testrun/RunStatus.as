package application.model.testrun {
	
	import application.model.NameDescriptionObject;
	
	[XmlClass(alias="RUN_STATUS")]
	public class RunStatus extends NameDescriptionObject {		
		[Bindable]
		[XmlAttribute(alias="order")]
		public var order:String;
		[Bindable]
		[XmlAttribute(alias="status")]
		public var status:String;
		
		public function RunStatus(){
			
		}
	}
}