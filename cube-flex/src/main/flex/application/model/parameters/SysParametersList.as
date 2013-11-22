package application.model.parameters {
	import application.model.XMLListRendered;

	public class SysParametersList extends XMLListRendered {
		[XmlArray(alias="*", type="application.model.parameters.SysParameter")]
		public var parameters:Array;
		
		public function SysParametersList() {}
		
		public function getParameters():Array{
			return parameters;	
		}
		
		public function setParameters(parameters:Array):void{
			this.parameters = parameters;	
		}
	}
}