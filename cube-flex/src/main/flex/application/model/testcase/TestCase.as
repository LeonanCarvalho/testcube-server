package application.model.testcase
{
	import application.model.NameDescriptionObject;
	import application.model.products.Product;
	import application.model.users.User;
	import application.model.versions.Version;
	
	[XmlClass(alias="TESTCASE")]
	public class TestCase extends NameDescriptionObject {	
	
		[Bindable]
		[XmlAttribute(alias="testPlanId")]
		public var testPlanId:int;
		
		[Bindable]
		[XmlElement(alias="TEST_PLAN_NAME")]
		public var testPlanName:String;
				
		[Bindable]
		[XmlElement(alias="CREATED_DATE")]
		public var createdDate:String;
		
		[Bindable]
		[XmlElement(alias="CREATED_BY")]
		public var createdBy:String;
	
		[Bindable]
		[XmlElement(alias="ASSIGNEE")]
		public var assignee:User;
	
		[Bindable]
		[XmlAttribute(alias="priorityId")]
		public var priorityId:int;
		
		[Bindable]
		[XmlElement(alias="PRIORITY_NAME")]
		public var priorityName:String;
		
		[Bindable]
		[XmlAttribute(alias="categoryId")]
		public var categoryId:int;
		
		[Bindable]
		[XmlElement(alias="CATEGORY_NAME")]
		public var categoryName:String;
		
		[Bindable]
		[XmlElement(alias="ESTIMATED_TIME")]
		public var estimatedTime:String;
		
		[Bindable]
		[XmlElement(alias="BUGS")]
		public var bugs:String;
		
		[Bindable]
		[XmlAttribute(alias="statusId")]
		public var statusId:int;
		
		[Bindable]
		[XmlElement(alias="LABELS")]
		public var labels:String;
		
		[Bindable]
		[XmlElement(alias="REQUIREMENTS")]
		public var requirements:String;
		
		[Bindable]
		[XmlElement(alias="SETUP")]
		public var setup:String;
		
		[Bindable]
		[XmlElement(alias="ACTION")]
		public var action:String;
		
		[Bindable]
		[XmlElement(alias="EXPECTED")]
		public var expected:String;
		
		public function TestCase(){
			
		}
	}
}