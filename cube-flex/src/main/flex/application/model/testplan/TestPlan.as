package application.model.testplan
{
	import application.model.NameDescriptionObject;
	import application.model.products.Product;
	import application.model.versions.Version;
	
	[XmlClass(alias="TESTPLAN")]
	public class TestPlan extends NameDescriptionObject {	
		[Bindable]
		[XmlElement(alias="CREATED_BY")]
		public var createdBy:String;
		
		[Bindable]
		[XmlElement(alias="PRODUCT")]
		public var product:Product;

		[Bindable]
		[XmlElement(alias="PLAN_TYPE")]
		public var planType:PlanType;
		
		[Bindable]
		[XmlElement(alias="PRODUCT_VERSION")]
		public var version:Version;
		
		[Bindable]
		[XmlElement(alias="DOCUMENT")]
		public var document:String;
		
		public function TestPlan(){
			
		}
	}
}