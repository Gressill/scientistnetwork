
package iil.flex.component{
	
	// 自定义button 类
	
	import flash.display.CapsStyle;
	import flash.display.GradientType;
	import flash.display.InteractiveObject;
	import flash.display.JointStyle;
	import flash.display.LineScaleMode;
	import flash.display.SpreadMethod;
	import flash.display.Sprite;
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.events.TimerEvent;
	import flash.geom.Matrix;
	import flash.text.TextField;
	import flash.text.TextFieldType;
	import flash.text.TextFormat;
	import flash.ui.Mouse;
	import flash.utils.Timer;
	
	public class SpecialButton extends Sprite{
		
		private var times:uint ;
		private var rate:uint ;
		private var bgMouse:Sprite ;
		
		private var bkCallClick:Function;
		
		public function SpecialButton(width:uint , height:uint , name:String ,fontColor:uint = 0x000000,fontSize:uint =12 ){
			
			AddEvent();
			DrawUI(width , height , name ,fontColor ,fontSize);
			
			
 		}
		
		private function DrawUI(width:uint , height:uint , name:String ,fontColor:uint ,fontSize:uint ):void{
		
			var fillType:String = GradientType.LINEAR ;
  			var colors:Array = [0xff0000, 0xeb0000];
  			var alphas:Array = [1, 1];
  			var ratios:Array = [0x0, 0xff];
 			var matr:Matrix = new Matrix();
			matr.createGradientBox(width, height, (3/2)*Math.PI, 0, 0);
			var spreadMethod:String = SpreadMethod.PAD;
			
			this.graphics.lineStyle(0.6, 0x1E1E1E, 1 , true, LineScaleMode.VERTICAL,
                               CapsStyle.NONE, JointStyle.MITER, 10);
			this.graphics.beginGradientFill(fillType, colors, alphas, ratios, matr, spreadMethod);  
			this.graphics.drawRoundRect(0,0,width,height,6,6);
			
			//添加鼠标移入背景
			bgMouse = new Sprite();
			var colors1:Array = [ 0xeb0000,0xffcc00 ];
			bgMouse.graphics.beginGradientFill(fillType, colors1, alphas, ratios, matr, spreadMethod);  
			bgMouse.graphics.drawRoundRect(1,1,width-1,height-1,6,6);
			//先设置为0；
			bgMouse.alpha  = 0;
			
			
			this.buttonMode = true;
			this.useHandCursor = true ; 
			
			var txt :TextField  = new TextField();
			
			var txtformat :TextFormat =new TextFormat();
			txtformat.size =fontSize;
			txtformat.font = "微软雅黑";
			txt.selectable = false ;
			txt.text =name;
			txt.setTextFormat(txtformat);
			txt.textColor  =fontColor;
			txt.x =(width - txt.textWidth  )/2-2;
			txt.y =(height - txt.textHeight )/2-3; 
			txt.width = width-txt.x ;
			txt.height = height;
			
			addChild(bgMouse);
			addChild (txt);			
			
		}
		
		private function AddEvent():void{
			this.times = 10;
			this.rate=11;
			addEventListener ( MouseEvent.ROLL_OVER ,MouseOver);
			addEventListener ( MouseEvent.ROLL_OUT ,MouseOut);
			addEventListener ( MouseEvent.CLICK ,onClick);
		}
		
		//响应鼠标事件函数
		private function MouseOver(Mouse_Event:MouseEvent):void{
			
			Mouse.cursor ="button";
			var TimeMover:Timer = new Timer(this.rate,this.times);
			TimeMover.start ();
			TimeMover.addEventListener(TimerEvent.TIMER,OverFlash);
			
		}
	

		private function MouseOut(Mouse_Event:MouseEvent):void{
			Mouse.cursor ="auto";
			var TimeMout:Timer = new Timer(this.rate,this.times);
			TimeMout.start ();
			TimeMout.addEventListener(TimerEvent.TIMER,OutFlash);
		}

		//时间
		private function OverFlash(Te:TimerEvent):void{
			bgMouse.alpha  +=0.1;
		}

		private function OutFlash(Te:TimerEvent ):void{
			bgMouse.alpha  -=0.1;
		}
		
		public function set CallClick(fun:Function):void{
			
			bkCallClick = fun;
		}

		private function onClick(evt:Event):void{
			
			bkCallClick();
			
		}
	}

}