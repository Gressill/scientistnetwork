package iil.flex.component{
	
	//显示名字的圈圈
	import flash.display.CapsStyle;
	import flash.display.JointStyle;
	import flash.display.LineScaleMode;
	import flash.display.Sprite;
	import flash.events.Event;
	import flash.events.EventPhase;
	import flash.events.MouseEvent;
	import flash.events.TimerEvent;
	import flash.filters.BitmapFilterQuality;
	import flash.filters.BlurFilter;
	import flash.geom.Point;
	import flash.text.TextField;
	import flash.text.TextFormat;
	import flash.utils.Timer;

	import mx.core.Application;
	
	import iil.flex.component.RelationLine;
	
	public class ShowCircle extends Sprite{
		//定义私有属性
		private var names : String ;
		private var color : uint ;
		private var Circle,FilterCircle,maskTmp :Sprite;
		private var times:uint;
		private var rate:uint ;
		public 	var LineStage :Array=[];
		private var relationPoint:Array;
		
		private var article:Array;
		
		public function ShowCircle( names :String , color : uint ){
			//初始化属性
			this.names = names;
			this.color = color;
			
			initCircle();
		}
		private function initCircle():void{
			//外框大滤镜圆形

			var cW:uint  = 30 ;
			FilterCircle = new Sprite();
			FilterCircle.graphics.beginFill(this.color);
			FilterCircle.graphics.drawCircle(0,0,cW);
			FilterCircle.graphics.endFill();
			FilterCircle.filters=[new BlurFilter(cW-10,cW-10,BitmapFilterQuality.HIGH)];
			//生成核心
			CircleCore(cW);
			addChild(FilterCircle);
			addChild(Circle);
			
			AddEvent();

		}
		private function CircleCore( cW:uint ):void{
			//中心圆形
			var CoreCricle:Sprite = new Sprite();
			CoreCricle.graphics.beginFill(0x000000,0.4 );
			CoreCricle.graphics.drawCircle(0,0,cW-14);
			CoreCricle.graphics.endFill();
			//边框
			var CoreCricleFra :Sprite = new Sprite();
			CoreCricleFra.graphics.beginFill(this.color);
			CoreCricleFra.graphics.drawCircle(0,0,cW-12);
			CoreCricleFra.graphics.endFill();
			//显示名字
			var txt :TextField  = new TextField();
			
			var txtformat :TextFormat =new TextFormat();
			txtformat.size =16;
			txtformat.font = "微软雅黑";
			txt.selectable = false ;
			txt.text =this.names;
			txt.setTextFormat(txtformat);
			txt.textColor  =0xffffff;
			txt.x =(this.width-txt.textWidth  )/2-2;
			txt.y =(this.height - txt.textHeight )/2-3; 
			txt.width = txt.textWidth+5;
			
			Circle = new Sprite ();
			Circle.addChild(CoreCricleFra);	
			Circle.addChild(CoreCricle);	
			Circle.addChild(txt);
			
			//圆形透明覆盖响应mouse消息实现动态效果
			maskTmp= new Sprite();
			maskTmp.graphics.beginFill(this.color,0);
			maskTmp.graphics.drawCircle(0,0,cW-12);
			maskTmp.graphics.endFill();
			
			Circle.addChild(maskTmp);
		}
		//添加事件
		private function AddEvent():void{
			//监听鼠标移入和移出事件
			this.times=6;
			this.rate=25;
			
			//实现拖放
			addEventListener(MouseEvent.MOUSE_DOWN , MouseDown ,false);
			maskTmp.addEventListener(MouseEvent.MOUSE_UP , MouseUp ,false);
			//实现缩放动画效果
			maskTmp.addEventListener ( MouseEvent.MOUSE_OVER ,MouseOver);
			maskTmp.addEventListener ( MouseEvent.MOUSE_OUT ,MouseOut);

			
		}
		
		//响应鼠标事件函数
		private function MouseDown(MouseDown:MouseEvent ):void{
			this.startDrag();
			
		}
		
		private function MouseUp(MouseUp:MouseEvent ):void{

			//调用应用级的函数
			Application.application.inquiry(this.names);
			
		}
		
		private function MouseOver(Mouse_Event:MouseEvent):void{
			var TimeMover:Timer = new Timer(this.rate,this.times);
			TimeMover.start ();
			TimeMover.addEventListener(TimerEvent.TIMER,OverFlash);
		}

		private function MouseOut(Mouse_Event:MouseEvent):void{
			var TimeMout:Timer = new Timer(this.rate,this.times);
			TimeMout.start ();
			TimeMout.addEventListener(TimerEvent.TIMER,OutFlash);
		}

		//时间
		private function OverFlash(Te:TimerEvent):void{
			Circle.scaleX +=0.1;
			Circle.scaleY +=0.1;
		}

		private function OutFlash(Te:TimerEvent ):void{
			Circle.scaleX -=0.1;
			Circle.scaleY -=0.1;			
		}

		//处理关系线条
		public function LineRelation(point:Array):void{

			relationPoint = point;
			
			point.forEach(DrawLine);
			RePaint();

		}

		
  		private function RePaint():void{
			
			for(var i:uint = 0 ; i < LineStage.length ;i++){
				
				var point:Point  = new Point(relationPoint[i].x,relationPoint[i].y);
				
				LineStage[i].DrawLine(point);
			}
			
  		}
		
		private function DrawLine(point:*, index:int, arr:Array):void{
			
			var tmp:RelationLine = new  RelationLine(new Point(point.x,point.y),relationPoint[index].Article);
			addChild(tmp);
			this.setChildIndex(tmp,this.numChildren-1);
			LineStage.push (tmp);
		}
		
		//设置文章
		public function set Article(res:Array):void{
			article = res;
		} 

		public function get Article():Array{
			
			return article;
			
		}
		
	}
}