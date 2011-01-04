package iil.flex.component{
	import flash.display.Sprite;
	import flash.events.MouseEvent;
	import flash.utils.Timer;
	
	import mx.core.FlexGlobals;
	
	//小球 类
	public class Ball extends Sprite {
		
		public var radius:uint;//半径
		public var color:uint;//颜色
		public var vx:Number=0;//x轴速度
		public var vy:Number=0;//y轴速度
		public var isOvered:Boolean=false;
		
		public function Ball(r:Number=50,c:uint=0xff0000,alpha:Number=1.0) {
			this.radius=r;
			this.color=c;
			init(alpha);
			//AddEvent();
		}
		
		public function init(alpha:Number=1.0):void {
			graphics.beginFill(color,alpha);
			graphics.drawCircle(0,0,radius);
			graphics.endFill();
		}
		
		//添加事件
		private function AddEvent():void{
			//监听鼠标移入和移出事件
			//实现拖放
			this.addEventListener(MouseEvent.MOUSE_UP , MouseUp ,false);
			//实现缩放动画效果
			this.addEventListener ( MouseEvent.ROLL_OVER ,MouseOver);
			this.addEventListener ( MouseEvent.ROLL_OUT ,MouseOut);			
		}
		
		//响应鼠标事件函数
		private function MouseDown(MouseDown:MouseEvent):void{
//			this.startDrag();
			
		}
		
		private function MouseUp(MouseUp:MouseEvent):void{
			
			//调用应用级的函数
			//FlexGlobals.topLevelApplication.getCoauthor(this.names);
			//Application.application.inquiry(this.names);
			
		}
		
		private function MouseOver(Mouse_Event:MouseEvent):void{
			//trace("i am in ..");
			//			this.alpha =1;	
			//			if(!isArticleBoxExist){
			//				this.alpha =1;			
			//				articleList.x=mouseX;
			//				articleList.y=mouseY;
			//				if(!this.contains(articleList)){
			//					addChild(articleList);
			//					this.setChildIndex(articleList,this.numChildren-1);
			//					//addChildAt(articles,0);
			//				}
			//				isArticleBoxExist = true;
			//				Constant.isArticleBoxExist = true;
			//			}
			//			
			//			var TimeMover:Timer = new Timer(this.rate,this.times);
			//			TimeMover.start ();
			//			TimeMover.addEventListener(TimerEvent.TIMER,OverFlash);
		}
		
		private function MouseOut(Mouse_Event:MouseEvent):void{
			//			this.alpha =0.3;
			//			removeChild(articleList);
			//			isArticleBoxExist = false;
			//			
			//			var TimeMout:Timer = new Timer(this.rate,this.times);
			//			TimeMout.start ();
			//			TimeMout.addEventListener(TimerEvent.TIMER,OutFlash);
		}
	}
}

