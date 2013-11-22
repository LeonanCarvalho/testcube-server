package application.model.testrun
{
	import application.model.DomainObject;
	import application.model.NameDescriptionObject;
	import application.model.products.Build;
	import application.model.products.Environment;
	import application.model.users.User;
	import application.model.versions.Version;
	
	import mx.collections.ArrayCollection;
	
	[XmlClass(alias="TESTRUN")]
	public class TestRun extends DomainObject {	
		public static const FIELD_CREATED_DATE:String = "createdDate DESC";
		[Bindable]
		[XmlAttribute(alias="testPlanId")]
		public var testPlanId:int;

		[Bindable]
		[XmlAttribute(alias="productId")]
		public var productId:int;
		
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
		[XmlElement(alias="VERSION")]
		public var version:Version;
		
		[Bindable]
		[XmlElement(alias="BUILD")]
		public var build:Build;
		
		[Bindable]
		[XmlElement(alias="ENVIRONMENT")]
		public var environment:Environment;
		
		[Bindable]
		[XmlElement(alias="SUMMARY")]
		public var summary:String;
		
		[Bindable]
		[XmlElement(alias="NOTES")]
		public var notes:String;
		
		[Bindable]
		[XmlElement(alias="STATUS")]
		public var status:RunStatus;
		
		[Bindable]
		[XmlArray(alias="STATISTICS", memberName = "RUN_STATISTICS", type ="application.model.testrun.RunStatistics", ignoreOn="serialize")]
		[ArrayElementType("application.model.testrun.RunStatistics")]
		public var statistics:ArrayCollection;
		
		public function TestRun(){
			
		}
	}
}