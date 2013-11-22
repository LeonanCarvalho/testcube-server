package presentation.model.admin.testrun
{
	import application.model.testrun.RunStatistics;
	import application.model.testrun.RunstatusEnum;
	
	import mx.charts.ChartItem;
	import mx.charts.HitData;
	import mx.charts.series.items.PieSeriesItem;
	import mx.collections.ArrayCollection;
	import mx.graphics.IFill;
	import mx.graphics.SolidColor;
	
	import utils.string.StringUtils;

	public class TestRunUtils
	{
		public static var IDLE_COLOR:SolidColor = new SolidColor(0x1896DE);
		public static var RUNNING_COLOR:SolidColor = new SolidColor(0xA5BE4A);
		public static var PAUSED_COLOR:SolidColor = new SolidColor(0xC6D3EF);
		public static var STOPPED_COLOR:SolidColor = new SolidColor(0xFF2421);
		public static var PASSED_COLOR:SolidColor = new SolidColor(0x109608);
		public static var FAILED_COLOR:SolidColor = new SolidColor(0x4A4542);
		public static var BLOCKED_COLOR:SolidColor = new SolidColor(0xD61008);
		public static var ERROR_COLOR:SolidColor = new SolidColor(0xFFEF18);
		
		public function TestRunUtils(){}
		
		public static function fillFunction(element:ChartItem, index:Number):IFill {
			var c:SolidColor = new SolidColor(0x00CC00);
			
			var item:PieSeriesItem = PieSeriesItem(element);
			var stat:RunStatistics = item.item as RunStatistics;       
			
			switch(stat.status.status)
			{
				case RunstatusEnum.IDLE.name:
				{
					c.color = IDLE_COLOR.color;
					break;
				}
				case RunstatusEnum.RUNNING.name:
				{
					c.color = RUNNING_COLOR.color;
					break;
				}
				case RunstatusEnum.PAUSED.name:
				{
					c.color = PAUSED_COLOR.color
					break;
				}
				case RunstatusEnum.STOPPED.name:
				{
					c.color = STOPPED_COLOR.color
					break;
				}
				case RunstatusEnum.PASSED.name:
				{
					c.color = PASSED_COLOR.color
					break;
				}
				case RunstatusEnum.FAILED.name:
				{
					c.color = FAILED_COLOR.color
					break;
				}
				case RunstatusEnum.BLOCKED.name:
				{
					c.color = BLOCKED_COLOR.color
					break;
				}
				case RunstatusEnum.ERROR.name:
				{
					c.color = ERROR_COLOR.color
					break;
				}
				default:
				{
					break;
				}
			}
			
			return c;
		}
		
		public static function displayText(statistics:ArrayCollection):String{
			var text:String = StringUtils.EMPTY; 
			
			for each (var stat:RunStatistics in statistics){
				text += stat.status.name + "(";
				text += stat.count + "), ";
			}
			
			// Remove last coma
			if (text.length > 0){
				text = text.substr(0, text.length -2);
			}
			
			return text;
		}
		
		public static function displayStatus(data:HitData):String {
			var psi:PieSeriesItem = data.chartItem as PieSeriesItem;
			var item:RunStatistics = data.item as RunStatistics;
			
			return "<b>" + item.status.name + "</b><br />" +
				item.count + " (<i>" +
				psi.percentValue.toFixed(2) + "%</i>)";
		}
	}
}