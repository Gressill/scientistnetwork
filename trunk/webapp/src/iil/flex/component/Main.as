
package iil.flex.component{
	
	import flash.display.Sprite;
	import flash.events.Event;
	import flash.external.ExternalInterface;
	import flash.geom.Point;
	
	import iil.flex.component.Relation;
	import iil.flex.component.Search;
	
	import mx.collections.ArrayCollection;
	
	public class Main extends Sprite{
		
		private var relation : Relation = null;
		private var search:Search = null;
		
		public function Main(...args){
			
			if(args.length > 0){
				
				ShowMyRelation(args[0],null);
				
			}
			
			//添加搜索框
			search = new Search();
			search.y=-15;
			search.x=args[1]/2 - search.width/2;
			
			addChild(search);
			AddEvent();

		}
		
		private function AddEvent():void{
			
			this.addEventListener( Event.ENTER_FRAME  ,resizeHandler); 		
			
		}
		
		private function resizeHandler(event:Event):void{
			
			var exp:Object = ExternalInterface.call("get_win_size");
			
			search.x =exp.width/2 - search.width/2;
			
		}
		
		public function ShowMyRelation(obj:ArrayCollection,mainName:String):void{
			if(relation)
				RemoveRelation();
			
			relation = new Relation(obj,mainName); 
			addChild(relation);
		}
		
		public function RemoveRelation():void{
			
			this.removeChild(relation);
			
		}
		
		
	}
}