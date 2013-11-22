package application.model.testrun {
	
	public class RunstatusEnum {
		public static const IDLE:RunstatusEnum = new RunstatusEnum("IDLE", 1);
		public static const RUNNING:RunstatusEnum = new RunstatusEnum("RUNNING", 2);
		public static const PAUSED:RunstatusEnum = new RunstatusEnum("PAUSED", 3);
		public static const STOPPED:RunstatusEnum = new RunstatusEnum("STOPPED", 4);
		public static const PASSED:RunstatusEnum = new RunstatusEnum("PASSED", 5);
		public static const FAILED:RunstatusEnum = new RunstatusEnum("FAILED", 6);
		public static const BLOCKED:RunstatusEnum = new RunstatusEnum("BLOCKED", 7);
		public static const ERROR:RunstatusEnum = new RunstatusEnum("ERROR", 8);
		
		public function RunstatusEnum(name:String, id:int){
			this.name = name;
			this.id=id;
		}
		
		public var name:String;
		public var id:int;
	}
}