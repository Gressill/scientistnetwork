package iil.flex.relation
{
	import flash.display.Sprite;
	import flash.events.MouseEvent;
	import flash.events.TimerEvent;
	import flash.geom.Point;
	import flash.utils.Timer;
	
	import iil.flex.component.ArticleList;
	import iil.flex.component.Ball;
	import iil.flex.config.Constant;
	
	import mx.controls.ToolTip;
	import mx.managers.ToolTipManager;
	
	import org.osmf.events.TimeEvent;

	public class AuthorPoint extends Sprite
	{
		public var authorName:String;
		private var aPoint:Point;
		private var aBall:Ball;
		
		private var times:uint;
		private var rate:uint ;
		
		public  var articleList:ArticleList ;
		private var isArticleBoxExist:Boolean = false;
		
		public function AuthorPoint(x:Number,y:Number,name:String,radius:int,color:Number)
		{
			this.authorName = name;
//			this.aPoint = new Point(x,y);
			this.aBall = new Ball(radius,color,0.3);
			this.aBall.x = x;
			this.aBall.y = y;
			this.addChild(aBall);
			this.Article = name;
			addEvent();
		}
//		ToolTipManager.enabled=true;
		private function addEvent():void{
			//监听鼠标移入和移出事件
//			this.times=6;
//			this.rate=25;
			this.addEventListener(MouseEvent.ROLL_OVER,mouseOverHandle);
			this.addEventListener(MouseEvent.ROLL_OUT,mouseOutHandle);
		}
		
		private function mouseOverHandle(Mouse_Event:MouseEvent):void{
			this.alpha =1;	
			if(!isArticleBoxExist){
				this.alpha =1;			
				articleList.x=mouseX;
				articleList.y=mouseY;
				if(!this.contains(articleList)){
					addChild(articleList);
					this.setChildIndex(articleList,this.numChildren-1);
				}
				isArticleBoxExist = true;
//				Constant.isArticleBoxExist = true;
			}
		}
		
		private function mouseOutHandle(Mouse_Event:MouseEvent):void{
			this.alpha =0.3;
			removeChild(articleList);
			isArticleBoxExist = false;
		}
		
		public function set Article(name:String):void{
			var nameArray:Array = [name];
			articleList= new ArticleList(nameArray,"name");			
		}
		
//		//时间
//		private function OverFlash(Te:TimerEvent):void{
//			this.scaleX +=0.1;
//			this.scaleY +=0.1;
//		}
//		
//		private function OutFlash(Te:TimerEvent ):void{
//			this.scaleX -=0.1;
//			this.scaleY -=0.1;			
//		}
		
	}
}