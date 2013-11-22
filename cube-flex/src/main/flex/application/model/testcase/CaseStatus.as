package application.model.testcase{
	import application.model.NameDescriptionObject;
	
	[XmlClass(alias="CASE_STATUS")]
	public class CaseStatus extends NameDescriptionObject {	
		[Bindable]
		[XmlAttribute(alias="order")]
		public var order:String;
		
		public function CaseStatus(){
			
		}
	}
}