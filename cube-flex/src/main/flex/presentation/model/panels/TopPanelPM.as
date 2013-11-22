package presentation.model.panels
{
	import application.API;
	import application.controller.panels.TopPanelController;
	
	import flash.net.navigateToURL;
	
	import mx.collections.ArrayList;
	
	import presentation.events.context.PmContextEvent;
	import presentation.model.ApplicationContextPM;
	import presentation.model.BasePM;
	import presentation.view.buttons.TopButton;
	import presentation.view.buttons.TopButtonClickEvent;
	import presentation.view.buttons.TopButtonNewEvent;

	public class TopPanelPM extends BasePM implements ITopPanel {
		private var controller:TopPanelController;
		private var applicationContext:ApplicationContextPM;
		private var buttons:ArrayList = new ArrayList();
		
		[Bindable]
		public var logoutText:String = null;
		
		public function TopPanelPM(){
		}
	
		[Inject]
		public function initialize(_controller:TopPanelController, _applicationContext:ApplicationContextPM):void{
			controller = _controller;
			applicationContext = _applicationContext;
		}
		
		public function onButtonClick(event:TopButtonClickEvent):void { 
			for(var index:int=0; index<buttons.length; index++){
				// Deselect all buttons beside the clicked one.
				if (event.target != buttons.getItemAt(index)){ 
					(buttons.getItemAt(index) as TopButton).unselect();
				}
			}
		}
		
		public function onButtonNew(event:TopButtonNewEvent):void { 
			buttons.addItem(event.getButton());
		}
		
		[MessageHandler]
		override public function contextChanged(event:PmContextEvent):void { 
			super.contextChanged(event);
			
			this.logoutText = getMessageLocale("toppanel", "toppanel.logout", [event.getContext().getUser().username]);
		}
		
		public function get logoutQuestion():String{
			return getMessageLocale("toppanel","toppanel.logout.question");
		}
		
		public function get logoutButtonTitle():String{
			return getMessageLocale("toppanel","toppanel.logout.title");
		}
		
		public function getDirection():String{
			return applicationContext.getDirection();
		}
		
		public function geTextAlignment():String{
			return applicationContext.geTextAlignment();
		}
		
		public function logout():void {
			navigateToURL(applicationContext.getURL(API.USER_LOGOUT), SELF);
		}
	}
}