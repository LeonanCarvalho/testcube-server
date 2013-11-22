package presentation.model.testrun
{
	import application.model.testcase.TestCase;
	
	import flash.events.Event;
	
	import presentation.events.Subscriber;

	public class TestResultSelectedEvent extends Event implements Subscriber{
		public static const TEST_RUN_SELECTED:String = "TEST_RUN_SELECTED";
		private var testCase:TestCase;
		private var selected:Boolean;
		
		// The subscriber object can be used to identify the original initiater,
		// in case several subscribers are registered to this event.
		private var subscriber:Object;
		
		public function TestResultSelectedEvent(subscriber:Object, testCase:TestCase, selected:Boolean=false){
			super(TEST_RUN_SELECTED, true, true);
		
			this.subscriber = subscriber;
			this.testCase = testCase
			this.selected = selected;
		}
	
		public function getTestCase():TestCase {
			return this.testCase;
		}
		
		public function getSubscriber():Object {
			return subscriber;
		}
		
		public function getSelected():Boolean{
			return selected;
		}
	}
}