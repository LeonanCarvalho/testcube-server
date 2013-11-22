package application.model.testrun
{
	import application.model.DomainObject;
	import application.model.testcase.TestCase;
	import application.model.users.User;

	[XmlClass(alias="TESTRUN_RESULT")]
	public class TestRunResult extends DomainObject
	{
		public static const FIELD_UPDATED_DATE:String = "updatedDate DESC";
		
		[Bindable]
		[XmlElement(alias="TESTCASE")]
		public var testCase:TestCase;
		
		[Bindable]
		[XmlElement(alias="STATUS")]
		public var status:RunStatus;
		
		[Bindable]
		[XmlElement(alias="UPDATED_BY")]
		public var updatedBy:User;
		
		public function TestRunResult() {
		
		}
	}
}