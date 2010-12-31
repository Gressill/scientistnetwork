package iil.flex.component{
	
	import flash.display.CapsStyle;
	import flash.display.JointStyle;
	import flash.display.LineScaleMode;
	import flash.display.Sprite;
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.geom.Point;
	
	import iil.flex.component.SearchBox;
	import iil.flex.config.Constant;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.AsyncToken;
	
	import valueObjects.Relations;
	

	public class RelationWithoutLine extends Sprite{
		
		private var line:Sprite ;
		private var relationBalls:Array =[];
		private var pointCircle:Array =[];

		
		private var balls:Array;
		private var numBalls:Number=50;
		private var bounce:Number=-0.9;
		private var spring:Number=0.2;
		private var gravity:Number=1;
		
		private var stageWidth:Number=1400;
		private var stageHeight:Number=600;
		
		public function RelationWithoutLine(...args){
			
			AddEvent();
			
			if(args.length == 0){		
				
			}else{				
				if(args[0]){
					//					
					//init();
					stageWidth = Constant.SCREEN_WIDTH;
					stageHeight = Constant.SCREEN_HEIGHT-Constant.RELATION_Y;
					//设置中心任务
					var centerBall :ShowCircle = new ShowCircle(args[1],0xff9800);
					
					centerBall.x=Constant.CENTERX;
					centerBall.y=Constant.CENTERY;
					//中心圆放大倍数
					centerBall.scaleX=Constant.SCALE;
					centerBall.scaleY=Constant.SCALE;
					centerBall.radius = Constant.RADIUS*Constant.SCALE;
					centerBall.Article = null;
					this.addChild(centerBall);
					relationBalls.push (centerBall);					
					//添加附加人物
					var sum:uint =(360*Math.random());
					
					var authorPaperdata:ArrayCollection = args[0];
					for(var i:uint=0; i<authorPaperdata.length; i++) {
						 
						var tempRelation:Relations = authorPaperdata[i];

						var color:uint = 0x0F3793;
						if(tempRelation.paperList){
							color += 0x1000*tempRelation.paperList.length;							
						}
						var tmpBall :ShowCircle = new ShowCircle(tempRelation.arthorString,color);
						var x:uint = Math.random()*700+400;
						var y:uint = Math.random()*300;
						
						tmpBall.x=x;
						tmpBall.y=y;
						numBalls = this.numChildren;
						//trace("numBalls is: "+numBalls);
//						while(ifHitOtherBalls(tmpBall)){
//							move(tmpBall,false);
//						}
						tmpBall.Article = tempRelation.paperList.toArray();
						
						relationBalls.push (tmpBall);
						this.addChild(tmpBall);						
					}	
					//relationBalls[0].LineRelation(relationBalls);
					//让线在圆圈的外层，这样才好点击线
					this.setChildIndex(relationBalls[0],this.numChildren-1);
//					numBalls = this.numChildren;
					this.addEventListener(Event.ENTER_FRAME, onEnterFrame);
				}		
			}
		}
		
		private function ifHitOtherBalls(ball:ShowCircle):Boolean {
			var hitOtherBall:Boolean = false;
			//trace(ball.x+" "+ball.y);
			for (var i:uint = 0; i < numBalls - 1; i++) {
				var tempBall:ShowCircle=relationBalls[i];
				var dx:Number=tempBall.x-ball.x;
				var dy:Number=tempBall.y-ball.y;
				var dist:Number=Math.sqrt(dx*dx+dy*dy);
				var minDist:Number=ball.radius+tempBall.radius;
				if (dist<minDist) {
					hitOtherBall = true;
					//trace("i is: "+i);
					//trace(tempBall.x+" "+tempBall.y);
					/*
					var angle:Number=Math.atan2(dy,dx);
					*/						
					var tx:Number=ball.x + (dx/dist)*minDist;//ball0.x+Math.cos(angle)*minDist;
					var ty:Number=ball.y + (dy/dist)*minDist;//ball0.y+Math.sin(angle)*minDist;
//					var ax:Number = (tx - tempBall.x) * spring;
//					var ay:Number = (ty - tempBall.y) * spring;
					//这两句的目的是为了防止死循环，比如一个球在两个球之前来回弹
					var ax:Number = (tx - tempBall.x) * Math.random();
					var ay:Number = (ty - tempBall.y) * Math.random();
					ball.vx-=ax;
					ball.vy-=ay;
					break;
					//move1(ball);
				}
			}
			return hitOtherBall;
			//relationBalls[0].LineRelation(relationBalls);
		}
		
		private function onEnterFrame(event:Event):void {
			var needMove:Boolean = false;
			for (var i:uint = 0; i < numBalls - 1; i++) {
				//var ball0:Ball=balls[i];
				//trace("relationBalls"+relationBalls[i].x);
				var ball0:ShowCircle=relationBalls[i];
				for (var j:uint = i + 1; j < numBalls; j++) {
					//var ball1:Ball=balls[j];
					//trace("j is: "+relationBalls[j].x);
					var ball1:ShowCircle=relationBalls[j];
					var dx:Number=ball1.x-ball0.x;
					var dy:Number=ball1.y-ball0.y;
					var dist:Number=Math.sqrt(dx*dx+dy*dy);
					var minDist:Number=ball0.radius+ball1.radius;
					if (dist<minDist) {
						needMove = true;
						/*
						var angle:Number=Math.atan2(dy,dx);
						var tx:Number=ball0.x+Math.cos(angle)*minDist;
						var ty:Number=ball0.y+Math.sin(angle)*minDist;
						*/						
						var tx:Number=ball0.x + (dx/dist)*minDist;//ball0.x+Math.cos(angle)*minDist;
						var ty:Number=ball0.y + (dy/dist)*minDist;//ball0.y+Math.sin(angle)*minDist;
						var ax:Number = (tx - ball1.x) * spring;
						var ay:Number = (ty - ball1.y) * spring;
						ball0.vx-=ax;
						ball0.vy-=ay;
						ball1.vx+=ax;
						ball1.vy+=ay;
					}
				}
			}
			if(needMove == true){
				for (i = 1; i < numBalls; i++) {
					//var ball:Ball=balls[i];
					var ball:ShowCircle=relationBalls[i];
					move(ball,false);
				}	
			}					
			//relationBalls[0].LineRelation(relationBalls);
		}
		/**
		 * 小球移动函数
		 * @ball ： 球
		 * @hasGravity ：是否有重力效果，true的话就有
		 */
		private function move(ball:ShowCircle,hasGravity:Boolean):void {
			if(hasGravity == true){
				ball.vy+=gravity;//重力代码
			}
			ball.x+=ball.vx;
			ball.y+=ball.vy;
			//下面是装到屏幕弹回的代码
			if (ball.x+ball.radius>stageWidth) {
				ball.x=stageWidth-ball.radius;
				ball.vx*=bounce;
			} else if (ball.x - ball.radius < 0) {
				ball.x=ball.radius;
				ball.vx*=bounce;
			}
			if (ball.y+ball.radius>stageHeight) {
				ball.y=stageHeight-ball.radius;
				ball.vy*=bounce;
			} else if (ball.y - ball.radius < 0) {
				ball.y=ball.radius;
				ball.vy*=bounce;
			}			
		}
		
		private function init():void {
			balls = new Array();
			for (var i:uint = 0; i < numBalls; i++) {
				//var ball:Ball=new Ball(Math.random()*30+20,Math.random()*0xffffff);
				var ball:ShowCircle=new ShowCircle((Math.random()*30+20).toString(),Math.random()*0xffffff);
				
				ball.x=Math.random()*stageWidth;
				ball.y=Math.random()*stageHeight;
				ball.vx=Math.random()*6-3;
				ball.vy=Math.random()*6-3;
				addChild(ball);
				balls.push(ball);
			}
			addEventListener(Event.ENTER_FRAME, onEnterFrame);
		}
		
		private function AddEvent():void{
			
			this.addEventListener(MouseEvent.MOUSE_DOWN,MouseDown);
			this.addEventListener(MouseEvent.MOUSE_UP,MouseUp);
//			this.addEventListener(Event.ENTER_FRAME, onEnterFrame);
		}
		
		private function MouseDown(event:MouseEvent):void{
			//if()
			//onEnterFrame(event);
			this.startDrag();
			
		}
		
		private function MouseUp(event:MouseEvent):void{
			
			this.stopDrag();
			
		}		
	}
}