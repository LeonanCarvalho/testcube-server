package presentation.model.admin.products
{
	import application.controller.admin.products.CategoriesController;
	import application.model.DomainObject;
	import application.model.products.Category;
	
	import mx.collections.ArrayList;
	import mx.collections.ListCollectionView;
	
	import presentation.events.panels.searchpanel.LinkBarItemClickEvent;
	import presentation.events.panels.searchpanel.SearchCompletedEvent;
	import presentation.events.panels.searchpanel.SearchRefreshEvent;
	import presentation.model.panels.SearchablePM;
	import presentation.view.admin.CrudCategory;
	
	import utils.grid.GridUtils;

	public class CategoriesPM extends SearchablePM {
		private static const CATEGORY_NEW_EVENT:String = "CATEGORY_NEW_EVENT";
		private static const CATEGORY_EDIT_EVENT:String = "CATEGORY_EDIT_EVENT";
		private static const CATEGORY_DELETE_EVENT:String = "CATEGORY_DELETE_EVENT";
		
		[Inject]
		public var categoriesController:CategoriesController;
		
		[Embed("/images/admin/categories-16.png")]
		private static const NEW_CATEGORY:Class;
		
		public function CategoriesPM() {
			super();
			
			columns = new ArrayList();
			columns.addItem(GridUtils.getSortableColumn("id", getMessageLocale("admin", "admin.administrator.categories.grid.id"), true, true, ID_DEFAULT_WIDTH));
			columns.addItem(GridUtils.getSortableColumn("name", getMessageLocale("admin", "admin.administrator.categories.grid.name"), true, true));						
			columns.addItem(GridUtils.getSortableColumn("description", getMessageLocale("admin", "admin.administrator.categories.grid.description"), true, true));
			columns.addItem(GridUtils.getColumn("product.name", getMessageLocale("admin", "admin.administrator.categories.grid.product"), true));
		}
		
		[Init]
		override public function postConstructor():void {
			super.controller = categoriesController;
		}
		
		[MessageHandler(selector=CategoriesController.CATEGORIES_SEARCH_COMPLETED_EVENT)]
		override public function searchCompleted(event:SearchCompletedEvent):void {
			super.searchCompleted(event);
			
			data = new ListCollectionView(event.getResult());
		}
		
		[MessageHandler(selector=CATEGORY_NEW_EVENT)]
		public function newCategory(event:LinkBarItemClickEvent):void {
			newCrud(new CrudCategory);
		}
		
		[MessageHandler(selector=CATEGORY_EDIT_EVENT)]
		public function editCategory(event:LinkBarItemClickEvent):void {
			editCrud(new CrudCategory);
		}
		
		[MessageHandler(selector=CATEGORY_DELETE_EVENT)]
		public function deleteCategory(event:LinkBarItemClickEvent):void {
			deleteCrud();
		}
		
		override public function getNewButton():Object {
			var newButton:Object = new Object();
			newButton.label = getMessageLocale("admin", "admin.administrator.categories.linkbar.new");
			newButton.icon = NEW_CATEGORY;
			newButton.eventType = CATEGORY_NEW_EVENT;
			return newButton;
		}
		
		override public function getEditButton():Object {
			var editButton:Object = super.getEditButton();
			editButton.eventType = CATEGORY_EDIT_EVENT;
			return editButton;
		}
		
		override public function getDeleteButton():Object {
			var deleteButton:Object = super.getDeleteButton();
			deleteButton.eventType = CATEGORY_DELETE_EVENT;
			return deleteButton;
		}
		
		override public function getNewDomainObject():DomainObject{
			return new Category();
		}		
		
		[MessageHandler(selector=CrudCategoryPM.CATEGORIES_SEARCH_REFRESH_EVENT)]
		override public function onSearchRefreshEvent(event:SearchRefreshEvent):void {
			search(freeText);
		}
		
		override public function getModelName():String {
			return getMessageLocale("admin", "admin.administrator.categories.crud.object.name");
		}
	}
}