package iil.flex.component
{
	import flash.display.Sprite;
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.events.TimerEvent;
	import flash.text.TextField;
	import flash.text.TextFieldType;
	import flash.text.TextFormat;
	import flash.ui.Mouse;
	import flash.utils.Timer;
	
	import iil.flex.config.Constant;
	
	import org.osmf.events.TimeEvent;
	
	public class TagButton extends Sprite{
		
		private var butName:String="";
		private var background:Sprite=null;
		private var rate:uint,times:uint;
		//保存当前状态
		public var isNow:Boolean = false;
		//点击回调函数
		private var bkCallClick:Function;
		
		public function TagButton(butName:String,width:uint,height:uint){
			
			this.butName= butName;
			DrawUI(width,height);
			AddEvent();
		}
		
		public function DrawUI(width:uint,height:uint):void{
			
			this.buttonMode = true;
			this.useHandCursor = true ; 
			//显示名字
			var txt :TextField  = new TextField();
			
			var txtformat :TextFormat =new TextFormat();
			txtformat.size =14;
			txtformat.font = Constant.FONT;
			txt.selectable = false ;
			txt.text =this.butName;
			txt.setTextFormat(txtformat);
			txt.textColor  =Constant.NAME_TEXT_COLOR;
			txt.x =(width - txt.textWidth  )/2-2;
			txt.y =(height - txt.textHeight )/2-3; 
			txt.width = txt.textWidth+5;			

			background = new Sprite();
			background.graphics.beginFill(0xff0000);
			background.graphics.drawRoundRect(0,0,width,height,5,5);
			background.graphics.endFill();
			background.alpha=0;
			this.addChild(background);
			this.addChild(txt);	
		}
		
		public function AddEvent():void{
			//实现缩放动画效果
			//this.addEventListener ( MouseEvent.MOUSE_DOWN ,MouseDown);
			//this.addEventListener ( MouseEvent.MOUSE_UP ,MouseUp);
			this.addEventListener ( MouseEvent.CLICK ,onClick);
			
			this.rate = 20;
			this.times = 11 ;
			
			this.addEventListener(MouseEvent.MOUSE_OVER,MouseOver);
			this.addEventListener(MouseEvent.MOUSE_OUT,MouseOut);
			this.addEventListener(Event.ENTER_FRAME,setBk);
		}
		private function setBk(evt:Event):void{
			if(isNow){
				background.alpha=1;
			}
		}
		//设置透明背景
		public function setTranBackground():void{
			background.alpha =0;
		}
		
		//响应鼠标事件函数
		private function MouseDown(MouseDown:MouseEvent ):void{
			
		}
		
		private function MouseUp(MouseUp:MouseEvent ):void{
			
			//调用应用级的函数
			//
			
		}	
		private function MouseOut(Mouse_Event:MouseEvent):void{
			
			if(!isNow){
				
				Mouse.cursor ="auto";
				var TimeOut:Timer = new Timer(this.rate,this.times);
				TimeOut.start ();
				TimeOut.addEventListener(TimerEvent.TIMER,OutFlash);
			}
			
		}
		private function MouseOver(Mouse_Event:MouseEvent):void{
			
			if(!isNow){
				background.alpha=0;
				Mouse.cursor ="button";
				var TimeOut:Timer = new Timer(this.rate,this.times);
				TimeOut.start ();
				TimeOut.addEventListener(TimerEvent.TIMER,OverFlash);
			}
			
		}		
		//时间
		private function OverFlash(Te:TimerEvent):void{
			if(background.alpha<11)
				background.alpha  +=0.1;
		}
		
		private function OutFlash(Te:TimerEvent):void{
			background.alpha  -=0.1;
		}
		
		//设置回调函数
		public function set callBack(func:Function):void{
			bkCallClick=func;
		}
		
		public function onClick(evt:Event):void{
			
			if(!isNow){
				bkCallClick();				
			}
		}
		
	}
}