package presentation.model.main
{
	import flash.display.DisplayObject;
	
	import mx.core.FlexGlobals;
	import mx.core.UIComponent;
	import mx.events.FlexEvent;
	import mx.managers.BrowserManager;
	import mx.styles.CSSStyleDeclaration;
	
	import presentation.events.LoadParametersEvent;
	import presentation.model.BasePM;
	import presentation.view.panels.MainPanel;
	
	import spark.components.Application;
	import spark.components.Group;
	import spark.skins.spark.SkinnableContainerSkin;

	public class CubePM extends BasePM {
		private static const MIN_WIDTH:Number = 1280;
		private static const MIN_HEIGHT:Number = 800;
		
		private var _application:Application;
		
		public function CubePM(){
		}
		
		/********************************************************************************************************
		 *  ADD THE MAIN CANVAS OF THE COMPLETE COMPONENTS TO THE APPLICATION FILE
		 *******************************************************************************************************/
		[MessageHandler]
		public function postInitialize(event:LoadParametersEvent):void {
			setGlobalFont();
			
			var mainpanel:MainPanel= new MainPanel();
			getMainContainer().addChild(mainpanel as Group);
		}
		
		public function setApplication(application:Application): void{
			_application = application;
		}
		
		public function setGlobalFont():void{
			var cssGlobal:CSSStyleDeclaration=getStyleManager().getStyleDeclaration("global");						
			cssGlobal.setStyle("fontFamily", getMessageLocale("application", "application.font.family"));
			getStyleManager().setStyleDeclaration("global",cssGlobal,true);
		}
		
		public function creationCompleteHandler(event:FlexEvent):void{
			BrowserManager.getInstance().setTitle(getMessageLocale('application', 'application.browser.title'));
		}
		
		public function scaleElements(application:Application):void{
			var newScaleX:Number = 1;
			var newScaleY:Number = 1;
			
			if (application.width < MIN_WIDTH)
				newScaleX = application.width / MIN_WIDTH;
			
			if (application.height < MIN_HEIGHT)
				newScaleY = application.height / MIN_HEIGHT;
			
			var newScale:Number = Math.min(newScaleX, newScaleY);
			
			for (var i:int = 0; i < application.numElements; i++){
				var element:DisplayObject = application.getElementAt(i) as DisplayObject;
				
				if (element)
				{
					element.scaleX = newScale;
					element.scaleY = newScale;
				}
			}
		}			

		
		// Main messages window Container
		public function getMessagesContainer():DisplayObject {
			FlexGlobals.topLevelApplication.messagesCanvas.visible = true;
			return FlexGlobals.topLevelApplication.messagesCanvas;
		}
		
		// Main window Container
		public function getMainContainer():UIComponent {
			FlexGlobals.topLevelApplication.mainCanvas.visible = true;
			return FlexGlobals.topLevelApplication.mainCanvas;
		}
		
		public function getParentDocument(uiComponent:UIComponent):Object{
			if(uiComponent.parentDocument is SkinnableContainerSkin)
				return uiComponent.parentDocument.parentDocument; 
			return uiComponent.parentDocument;
		}
		
	}
}