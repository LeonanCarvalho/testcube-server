package presentation.model.admin.testrun
{
	import application.model.testrun.RunstatusEnum;
	
	import spark.components.Image;

	public class RunStatusUtils
	{
		[Embed("/images/testrun/runstatus/idle-16.png")] 
		private static const STATUS_IDLE:Class;
		[Embed("/images/testrun/runstatus/running-16.png")] 
		private static const STATUS_RUNNING:Class;
		[Embed("/images/testrun/runstatus/paused-16.png")] 
		private static const STATUS_PAUSED:Class;
		[Embed("/images/testrun/runstatus/stopped-16.png")] 
		private static const STATUS_STOPPED:Class;
		[Embed("/images/testrun/runstatus/passed-16.png")] 
		private static const STATUS_PASSED:Class;
		[Embed("/images/testrun/runstatus/failed-16.png")] 
		private static const STATUS_FAILED:Class;
		[Embed("/images/testrun/runstatus/blocked-16.png")] 
		private static const STATUS_BLOCKED:Class;
		[Embed("/images/testrun/runstatus/error-16.png")] 
		private static const STATUS_ERROR:Class;
		
		[Embed("/images/testrun/runstatus/idle-22.png")] 
		private static const STATUS_IDLE_22:Class;
		[Embed("/images/testrun/runstatus/running-22.png")] 
		private static const STATUS_RUNNING_22:Class;
		[Embed("/images/testrun/runstatus/paused-22.png")] 
		private static const STATUS_PAUSED_22:Class;
		[Embed("/images/testrun/runstatus/stopped-22.png")] 
		private static const STATUS_STOPPED_22:Class;
		[Embed("/images/testrun/runstatus/passed-22.png")] 
		private static const STATUS_PASSED_22:Class;
		[Embed("/images/testrun/runstatus/failed-22.png")] 
		private static const STATUS_FAILED_22:Class;
		[Embed("/images/testrun/runstatus/blocked-22.png")] 
		private static const STATUS_BLOCKED_22:Class;
		[Embed("/images/testrun/runstatus/error-22.png")] 
		private static const STATUS_ERROR_22:Class;
		
		public function RunStatusUtils()
		{
		}
		
		public static function setImage(status:int, img:Image):void{
			if(status == RunstatusEnum.IDLE.id) {
				img.source = STATUS_IDLE;
			} else if(status == RunstatusEnum.RUNNING.id){
				img.source = STATUS_RUNNING;
			} else if(status == RunstatusEnum.PAUSED.id){
				img.source = STATUS_PAUSED;
			} else if(status == RunstatusEnum.STOPPED.id){
				img.source = STATUS_STOPPED;
			} else if(status == RunstatusEnum.PASSED.id){
				img.source = STATUS_PASSED;
			} else if(status == RunstatusEnum.FAILED.id){
				img.source = STATUS_FAILED;
			} else if(status == RunstatusEnum.BLOCKED.id){
				img.source = STATUS_BLOCKED;
			} else if(status == RunstatusEnum.ERROR.id){
				img.source = STATUS_ERROR;
			}
		}
		
		public static function setImage22(status:int, img:Image):void{
			if(status == RunstatusEnum.IDLE.id) {
				img.source = STATUS_IDLE_22;
			} else if(status == RunstatusEnum.RUNNING.id){
				img.source = STATUS_RUNNING_22;
			} else if(status == RunstatusEnum.PAUSED.id){
				img.source = STATUS_PAUSED_22;
			} else if(status == RunstatusEnum.STOPPED.id){
				img.source = STATUS_STOPPED_22;
			} else if(status == RunstatusEnum.PASSED.id){
				img.source = STATUS_PASSED_22;
			} else if(status == RunstatusEnum.FAILED.id){
				img.source = STATUS_FAILED_22;
			} else if(status == RunstatusEnum.BLOCKED.id){
				img.source = STATUS_BLOCKED_22;
			} else if(status == RunstatusEnum.ERROR.id){
				img.source = STATUS_ERROR_22;
			}
		}
		
		public static function getImage(status:int):Class{
			if(status == RunstatusEnum.IDLE.id) {
				return STATUS_IDLE;
			} else if(status == RunstatusEnum.RUNNING.id){
				return STATUS_RUNNING;
			} else if(status == RunstatusEnum.PAUSED.id){
				return STATUS_PAUSED;
			} else if(status == RunstatusEnum.STOPPED.id){
				return STATUS_STOPPED;
			} else if(status == RunstatusEnum.PASSED.id){
				return STATUS_PASSED;
			} else if(status == RunstatusEnum.FAILED.id){
				return STATUS_FAILED;
			} else if(status == RunstatusEnum.BLOCKED.id){
				return STATUS_BLOCKED;
			} else if(status == RunstatusEnum.ERROR.id){
				return STATUS_ERROR;
			}
			
			return STATUS_IDLE;
		}
	}
}