package application.model.testcase
{
	import application.model.NameDescriptionObject;
	
	[XmlClass(alias="CASE_PRIORITY")]
	public class CasePriority extends NameDescriptionObject {	
		[Bindable]
		[XmlAttribute(alias="order")]
		public var order:String;
		
		public function CasePriority(){
			
		}
	}
}