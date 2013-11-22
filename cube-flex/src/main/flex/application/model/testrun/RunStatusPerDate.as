package application.model.testrun
{
	[XmlClass(alias="RUNSTATUS_DATE")]
	public class RunStatusPerDate
	{
		[Bindable]
		[XmlAttribute(alias="idle")]
		public var idle:int;
		
		[Bindable]
		[XmlAttribute(alias="passed")]
		public var passed:int;
		
		[Bindable]
		[XmlAttribute(alias="failed")]
		public var failed:int;
		
		[Bindable]
		[XmlElement(alias="DATE")]
		public var date:String;
		
		public function RunStatusPerDate()
		{
		}
	}
}