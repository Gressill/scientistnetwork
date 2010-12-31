
package iil.flex.component{
	
	import flash.display.Sprite;
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.external.ExternalInterface;
	import flash.geom.Point;
	
	import iil.flex.component.Relation;
	import iil.flex.component.SearchBox;
	import iil.flex.config.Constant;
	
	import mx.collections.ArrayCollection;
	
	public class MainStage extends Sprite{
		
//		private var relation:RelationWithoutLine = null;
		private var relation : Relation = null;
		private var search:SearchBox = null;
		private var sixDegreeSep:SixDegreesSeparation=null;
		private var tag:RelationTag=null;
		
		private var getWinSize:Object;
		
		public function MainStage(...args){
			
			if(args.length > 0){
				ShowMyRelation(args[0],null);
			}
			
			//添加搜索框
			search = new SearchBox(); 
			search.y=-15;
			search.x=args[1]/2 - search.width/2;
			addChild(search);
//			addChildAt(search,0);
			
			//切换标签
			tag = new RelationTag();
			tag.y = -15; 
			tag.x=args[1] - tag.width-10;
			addChild(tag);
//			addChildAt(tag,0);
			//SixDegrees(args[1]);
			
			AddEvent();

		}
		
		private function AddEvent():void{
			
			this.addEventListener( Event.ENTER_FRAME  ,resizeHandler);
			//this.addEventListener( MouseEvent.MOUSE_UP  ,resizeHandler);
			
		}
		
		private function resizeHandler(event:Event):void{
			
			getWinSize = ExternalInterface.call("get_win_size");
			
			search.x =getWinSize.width/2 - search.width/2;
			tag.x=getWinSize.width - tag.width-10;
//			if(sixDegreeSep)
//				sixDegreeSep.x=getWinSize.width/2 - sixDegreeSep.width/2+50;

		}
		/**
		 * 开始查询
		 */ 
		public function ShowMyRelation(relationDataFromServer:ArrayCollection,mainName:String):void{
			trace("mainName is : "+mainName);
			trace("relationDataFromServer is : "+relationDataFromServer);
			if(relation)
				RemoveRelation();
			
			relation = new Relation(relationDataFromServer,mainName);
//			relation = new RelationWithoutLine(obj,mainName);
			
			relation.y=Constant.RELATION_Y;
			addChild(relation);
//			addChildAt(relation,0);
		}
		//展示六度
		public function ShowSixDS(sixResultFromServer:ArrayCollection,mainName:String):void{
			
			if(sixDegreeSep)
				RemoveSixDegrees();
			if(relation)
				RemoveRelation();
			
			sixDegreeSep = new SixDegreesSeparation(sixResultFromServer,mainName);
			sixDegreeSep.x=getWinSize.width/2 - sixDegreeSep.width/2+70;
			sixDegreeSep.y=search.height+60;
//			trace("sixDegreeSep.x is :"+sixDegreeSep.x);
//			trace("sixDegreeSep.y is :"+sixDegreeSep.y);
			addChild(sixDegreeSep);
//			addChildAt(sixDegreeSep,0);
		}		

		//切换到六度
		public function SetSixDegrees():void{
			if(relation)
				RemoveRelation();
			//切换到六度搜索
			removeChild(search);
			search = new SearchBox(false);
			search.y=-15;
			addChild(search);
//			addChildAt(search,0);
		}
		
		//切换六度到关系
		public function SetRelationSearch():void{
			if(sixDegreeSep)
				RemoveSixDegrees();
			removeChild(search);
			search = new SearchBox();
			search.y=-15;
			addChild(search);
//			addChildAt(search,0);
		}
		
		//移除六度显示
		public function RemoveSixDegrees():void{
			this.removeChild(sixDegreeSep);
			sixDegreeSep =null;
			
		}
		
		//移除关系
		public function RemoveRelation():void{
			this.removeChild(relation);
			relation =null;
		}
	}
}