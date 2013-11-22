package application.model.parameters
{
	import application.model.DomainObject;

	[XmlClass(alias="DB_PARAMETER")]
	public class DBParameter extends AbstractParameter {
		public static const DEFAULT_DATE_FORMAT:String = "DEFAULT_DATE_FORMAT";
		public static const SHORT_DATE_FORMAT:String = "DEFAULT_DATE_FORMAT";
		public static const NUMBER_OF_ROWS:String = "NUMBER_OF_ROWS";
		
		[XmlElement(alias="DESCRIPTION")]	
		public var description:String;
		
		public function DBParameter (){
			super();
		}
		
		
	}
}