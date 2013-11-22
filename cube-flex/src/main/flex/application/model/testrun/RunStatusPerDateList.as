package application.model.testrun
{
	import mx.collections.ArrayCollection;
	import mx.collections.ArrayList;

	[XmlClass(alias="RUNSTATUS_DATE_LIST")]
	public class RunStatusPerDateList
	{
		[XmlArray(alias="*", type="application.model.testrun.RunStatusPerDate")]
		[ArrayElementType("application.model.testrun.RunStatusPerDate")]
		public var statuses:ArrayList;
		
		public function RunStatusPerDateList()
		{
		}
	}
}