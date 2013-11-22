package application.model.testrun
{
	import application.model.XMLListRendered;
	
	import mx.collections.ArrayList;

	[XmlClass(alias="TESTRUN_RESULTS")]
	public class TestRunResultsList extends XMLListRendered
	{
		[XmlArray(alias="*", type="application.model.testrun.TestRunResult", getRuntimeType=true, memberName="TESTRUN_RESULT")]
		public var results:ArrayList;
		
		public function TestRunResultsList()
		{
		}
	}
}