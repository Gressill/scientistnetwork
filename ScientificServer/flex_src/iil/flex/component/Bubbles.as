package iil.flex.component
{
	import flash.display.Sprite;
	import flash.events.Event;
	public class Bubbles extends Sprite {
		private var balls:Array;
		private var numBalls:Number=10;
		private var centerBall:Ball;
		private var bounce:Number=-1;
		private var spring:Number=0.2;
		
		private var stageWidth:Number=1400;
		private var stageHeight:Number=900;
		
		public function Bubbles(...args) {
			if(args.length > 0){
				//this.stage = args[0];
				stageWidth = args[0];
				stageHeight = args[1];
			}
			init();
		}
		
		private function init():void {
			balls=new Array  ;
			centerBall=new Ball(100,0xcccccc);
			addChild(centerBall);
			centerBall.x=stageWidth/2;
			centerBall.y=stageHeight/2;
			for (var i:uint=0; i<numBalls; i++) {
				var ball:Ball=new Ball(Math.random()*40+5,Math.random()*0xffffff);
				ball.x=Math.random()*stageWidth;
				ball.y=Math.random()*stageHeight;
				ball.vx=(Math.random()*2-1)*10;
				ball.vy=(Math.random()*2-1)*10;
				addChild(ball);
				balls.push(ball);
			}
			addEventListener(Event.ENTER_FRAME,onEnterFrame);
		}
		private function onEnterFrame(event:Event):void {
			for (var i:uint=0; i<numBalls; i++) {
				var ball:Ball=balls[i];
				move(ball);
				var dx:Number=ball.x-centerBall.x;
				var dy:Number=ball.y-centerBall.y;
				var dist:Number=Math.sqrt(dx*dx+dy*dy);
				var minDist:Number=ball.radius+centerBall.radius;
				if (dist<minDist) {
					var angle:Number=Math.atan2(dy,dx);
					var tx:Number=centerBall.x+Math.cos(angle)*minDist;//弹性运动的目标点x坐标
					var ty:Number=centerBall.y+Math.sin(angle)*minDist;//弹性运动的目标点y坐标
					ball.vx+=(tx-ball.x)*spring;
					ball.vy+=(ty-ball.y)*spring;
				}
			}
		}
		private function move(ball:Ball):void {
			ball.x+=ball.vx;
			ball.y+=ball.vy;
			if (ball.x+ball.radius>stageWidth) {
				ball.x=stageWidth-ball.radius;
				ball.vx*=bounce;
			} else if (ball.x-ball.radius<0) {
				ball.x=ball.radius;
				ball.vx*=bounce;
			}
			if (ball.y+ball.radius>stageHeight) {
				ball.y=stageHeight-ball.radius;
				ball.vy*=bounce;
			} else if (ball.y-ball.radius<0) {
				ball.y=ball.radius;
				ball.vy*=bounce;
			}
		}
	}
}
