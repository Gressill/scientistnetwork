package iil.flex.relation
{
	import flash.display.Sprite;
	import flash.events.Event;
	import iil.flex.component.Ball3D;

	public class RotateY extends Sprite {
		private var balls:Array;
		private var numBalls:uint=50;
		
		private var fl:Number=250;
		private var stageWidth:Number;
		private var stageHeight:Number;
		private var vpX:Number=stageWidth/2;
		private var vpY:Number=stageHeight/2;
		
		private var vpx:Number=stageWidth/2;
		private var vpy:Number=stageHeight/2;
		
		public function RotateY(width:Number,height:Number) {
			stageWidth = width;
			stageHeight = height;
			init();
		}
		
		private function init():void {
			balls=new Array(numBalls);
			for (var i:uint=0; i<numBalls; i++) {
				var ball:Ball3D=new Ball3D(10,Math.random()*0xffffff);
				balls[i]=ball;
				ball.xpos = (Math.random()*2-1)*100;
				ball.ypos = (Math.random()*2-1)*100;
				ball.zpos = (Math.random()*2-1)*100;
				addChild(ball);
			}
			addEventListener(Event.ENTER_FRAME,EnterFrameHandler);
		}
		
		private function EnterFrameHandler(e:Event):void {	
			var dx:Number = mouseX - vpx;
			var dy:Number = mouseY - vpy;	
			var angleY:Number =dx*0.0005;
			var angleX:Number =dy*0.0005;
			var angleZ:Number = Math.sqrt(dx*dx + dy*dy)*0.0005;
			
			if (dx>0){angleZ *=-1;}//以鼠标所在点的x坐标相对于消失点的位置为判断依据，左侧z轴正向旋转，右侧z轴反向旋转
			
			for (var i:uint; i<numBalls; i++) {
				var b:Ball3D=balls[i];
				rotateX(b,angleX);
				rotateY(b,angleY);
				rotateZ(b,angleZ);
				doPerspective(b);
			}
			sortZ();
		}
		
		//x轴的坐标旋转
		private function rotateX(ball:Ball3D, angleX:Number):void {
			var cosX:Number=Math.cos(angleX);
			var sinX:Number=Math.sin(angleX);
			var y1:Number=ball.ypos*cosX-ball.zpos*sinX;
			var z1:Number=ball.zpos*cosX+ball.ypos*sinX;
			ball.ypos=y1;
			ball.zpos=z1;
		}
		
		//y轴的坐标旋转
		private function rotateY(ball:Ball3D, angleY:Number):void {
			var cosY:Number=Math.cos(angleY);
			var sinY:Number=Math.sin(angleY);
			var x1:Number=ball.xpos*cosY-ball.zpos*sinY;
			var z1:Number=ball.zpos*cosY+ball.xpos*sinY;
			ball.xpos=x1;
			ball.zpos=z1;
		}
		
		//z轴的坐标旋转
		private function rotateZ(ball:Ball3D, angleZ:Number):void {
			var cosZ:Number=Math.cos(angleZ);
			var sinZ:Number=Math.sin(angleZ);
			var x1:Number=ball.xpos*cosZ-ball.ypos*sinZ;
			var y1:Number=ball.ypos*cosZ+ball.xpos*sinZ;
			ball.xpos=x1;
			ball.ypos=y1;
		}
		
		//3D透视处理
		private function doPerspective(ball:Ball3D):void {
			if (ball.zpos>-fl) {
				var scale:Number = fl / (fl + ball.zpos);
				ball.scaleX=ball.scaleY=scale;
				ball.x=vpx+ball.xpos*scale;
				ball.y=vpy+ball.ypos*scale;
				//ball.alpha = scale*0.65;
				ball.visible=true;
			} else {
				ball.visible=false;
			}
		}
		
		//z轴排序
		private function sortZ():void {
			balls.sortOn("zpos",Array.DESCENDING|Array.NUMERIC);
			for (var i:uint=0; i<numBalls; i++) {
				setChildIndex(balls[i],i);
			}
		}

		
//		public function RotateY() {
//			init();
//		}
//		
//		private function init():void {
//			balls = new Array();
//			for (var i:uint = 0; i < numBalls; i++) {
//				var ball:Ball3D=new Ball3D(10,Math.random()*0xffffff);
//				balls.push(ball);
//				ball.xpos=Math.random()*200-100;
//				ball.ypos=Math.random()*200-100;
//				ball.zpos=(Math.random()*2-1)*100;
//				addChild(ball);
//			}
//			addEventListener(Event.ENTER_FRAME, onEnterFrame);
//		}
//		private function onEnterFrame(event:Event):void {
//			var angleY:Number = (mouseX - vpX) * .0004;//旋转的角度与鼠标的水平位置关联
//			for (var i:uint = 0; i < numBalls; i++) {
//				var ball:Ball3D=balls[i];
//				rotateY(ball, angleY);
//			}
//			sortZ();
//		}
//		
//		//绕y轴旋转
//		private function rotateY(ball:Ball3D, angleY:Number):void {
//			var cosY:Number=Math.cos(angleY);
//			var sinY:Number=Math.sin(angleY);
//			var x1:Number=ball.xpos*cosY-ball.zpos*sinY;
//			var z1:Number=ball.zpos*cosY+ball.xpos*sinY;
//			ball.xpos=x1;
//			ball.zpos=z1;
//			if (ball.zpos>- fl) {
//				var scale:Number = fl / (fl + ball.zpos);
//				ball.scaleX=ball.scaleY=scale;
//				ball.x=vpX+ball.xpos*scale;
//				ball.y=vpY+ball.ypos*scale;
//				ball.alpha = scale*0.8;
//				ball.visible=true;
//			} else {
//				ball.visible=false;
//			}
//		}
//		private function sortZ():void {
//			balls.sortOn("zpos", Array.DESCENDING | Array.NUMERIC);
//			for (var i:uint = 0; i < numBalls; i++) {
//				var ball:Ball3D=balls[i];
//				setChildIndex(ball, i);
//			}
//		}
	}
}