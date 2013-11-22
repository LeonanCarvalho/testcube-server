package application.model.parameters
{
	public class AbstractParameter {
		
		[XmlAttribute(alias="id")]
		public var id:String;
		
		[XmlElement(alias="VALUE")]	
		public var value:String;
		
		public function AbstractParameter(){
		}
		
		public function getId():String{
			return id;
		}
		
		public function setId(id:String):void{
			this.id = id;
		}
		
		public function getValue():String{
			return value;
		}
		
		public function setValue(value:String):void{
			this.value = value;
		}
	}
}