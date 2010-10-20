
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
		private var sds:SixDegreesSeparation=null;
		private var tag:RelationTag=null;
		
		private var exp:Object;
		
		public function Main(...args){
			
			if(args.length > 0){
				
				ShowMyRelation(args[0],null);
				
			}
			
			//添加搜索框
			search = new Search(); 
			search.y=-15;
			search.x=args[1]/2 - search.width/2;
			addChild(search);
			
			//切换标签
			tag = new RelationTag();
			tag.y = -15; 
			tag.x=args[1] - tag.width-10;
			addChild(tag);
			

			//SixDegrees(args[1]);
			
			AddEvent();

		}
		
		private function AddEvent():void{
			
			this.addEventListener( Event.ENTER_FRAME  ,resizeHandler); 		
			
		}
		
		private function resizeHandler(event:Event):void{
			
			exp = ExternalInterface.call("get_win_size");
			
			search.x =exp.width/2 - search.width/2;
			tag.x=exp.width - tag.width-10;
			if(sds)
				sds.x=exp.width/2 - sds.width/2+50;

		}
		
		public function ShowMyRelation(obj:ArrayCollection,mainName:String):void{
			if(relation)
				RemoveRelation();
			
			relation = new Relation(obj,mainName); 
			relation.y=200;
			addChild(relation);
		}
		//展示六度
		public function ShowSixDS(obj:ArrayCollection,mainName:String):void{
			
			if(sds)
				RemoveSixDegrees();
			if(relation)
				RemoveRelation();
			
			sds = new SixDegreesSeparation(obj,mainName);
			sds.x=exp.width/2 - sds.width/2+70;
			sds.y=search.height+60;
			addChild(sds);
		}		

		//切换到六度
		public function SetSixDegrees():void{
			
			if(relation)
				RemoveRelation();
			//切换到六度搜索
			removeChild(search);
			search = new Search(false);
			search.y=-15;
			addChild(search);
		}
		
		//切换六度到关系
		public function SetRelationSearch():void{
			if(sds)
				RemoveSixDegrees();
			removeChild(search);
			search = new Search();
			search.y=-15;
			addChild(search);
		}
		
		//六度显示
		public function RemoveSixDegrees():void{
			
			this.removeChild(sds);
			sds =null;
			
		}
		
		//移除关系
		public function RemoveRelation():void{
			
			this.removeChild(relation);
			relation =null;
			
		}
		
		
	}
}