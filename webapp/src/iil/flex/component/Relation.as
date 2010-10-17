package iil.flex.component{
	
	import flash.display.CapsStyle;
	import flash.display.JointStyle;
	import flash.display.LineScaleMode;
	import flash.display.Sprite;
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.geom.Point;
	
	import iil.flex.component.Search;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.AsyncToken;
	
	import valueObjects.Relations;
	

	public class Relation extends Sprite{
		
		private var line:Sprite ;
		private var obj:Array =[];
		private var pointCircle:Array =[];

		
		public function Relation(...args){
			
			AddEvent();
			
			if(args.length == 0){		
				
			}else{
				
				if(args[0]){
					
					//设置中心任务
					var main :ShowCircle = new ShowCircle(args[1],0xff9800);
					main.Article = null;
					this.addChild(main);
					obj.push (main);
					
					//添加附加人物
					var sum:uint =(360*Math.random());
					
					var data:ArrayCollection = args[0];
					for(var i:uint=0; i<data.length; i++) {
						 
						var tempRelation:Relations = data[i];

						var color:uint = 0x0F3793;
						if(tempRelation.paperList){
							color += 0x1000*tempRelation.paperList.length;
							
						}
						var tmp :ShowCircle = new ShowCircle(tempRelation.arthorString,color);
						var x:uint = Math.random()*700+400;
						var y:uint = Math.random()*500;
						
						tmp.x=x;
						tmp.y=y;
						tmp.Article = tempRelation.paperList.toArray();
						
						obj.push (tmp);
						this.addChild(tmp);
						
					}
	
					
					obj[0].x=700;
					obj[0].y=300;
					obj[0].scaleX=1.8;
					obj[0].scaleY=1.8;
					obj[0].LineRelation(obj);
					
					this.setChildIndex(obj[0],this.numChildren-1);	
				}			
			}
			

		}
				
		private function WherePoint(point:*) :int {
			
			if((obj[0].x - point.x ) >0 && (obj[0].y - point.y) > 0){
				return 0;
			}
			if((obj[0].x - point.x )<0 && (obj[0].y - point.y) > 0){
				return 90;
			}
			if((obj[0].x - point.x )<0 && (obj[0].y - point.y) < 0){
				return 180;
			}

			if((obj[0].x - point.x )>0 && (obj[0].y - point.y) < 0){
				return 270;
			}
			
			return 0;
		}	
		private function AddEvent():void{
			
			this.addEventListener(MouseEvent.MOUSE_DOWN,MouseDown);
			this.addEventListener(MouseEvent.MOUSE_UP,MouseUp);


		}
		
		private function MouseDown(event:MouseEvent):void{
			
			this.startDrag();
			
		}
		
		private function MouseUp(event:MouseEvent):void{
			
			this.stopDrag();
			
		}

		
	}
}