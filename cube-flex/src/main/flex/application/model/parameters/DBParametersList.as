package application.model.parameters{
	import application.model.BaseList;
	import application.model.XMLListRendered;
	
	[XmlClass(alias="DB_PARAMETERS")]
	public class DBParametersList extends XMLListRendered {
		[XmlArray(alias="*", type="application.model.parameters.DBParameter")]
		public var parameters:Array;
	
		public function DBParametersList(){
		}
		
		public function getParameters():Array{
			return parameters;	
		}
		
		public function setParameters(parameters:Array):void{
			this.parameters = parameters;	
		}
	}
}