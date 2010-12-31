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
	
	import iil.flex.component.RelationLine;
	import iil.flex.config.Constant;
	
	import mx.core.FlexGlobals;
	
	public class ShowCircle extends Sprite{
		//定义私有属性
		private var names : String ;
		public var color : uint ;
		private var Circle:Sprite,FilterCircle:Sprite,maskTmp:Sprite;
		private var times:uint;
		private var rate:uint ;
		public 	var LineStage :Array=[];
		private var relationPoint:Array;
		
		public var vx:Number=0;//x轴速度
		public var vy:Number=0;//y轴速度
		public var radius:uint = 30;//半径
		public var needMove:Boolean = false;
		
		private var article:Array;
		
		private var line :Sprite ;
		private var CoreCricle:Ball;//中心圆形
		private var CoreCricleFra :Ball;//边框
		public var isArticleBoxExist:Boolean = false;
		//public var removeArticleBox:Boolean = true;
		public  var articleList:ArticleList ;
		
		public function ShowCircle( names :String , color : uint ){
			//初始化属性
			this.names = names;
			this.color = color;
			this.radius = Constant.RADIUS;
			initCircle();
		}
		private function initCircle():void{
			//外框大滤镜圆形
			
			//var radius:uint  = 30 ;
			FilterCircle = new Ball(radius,this.color);
			FilterCircle.filters=[new BlurFilter(radius-10,radius-10,BitmapFilterQuality.HIGH)];
			//生成核心
			CircleCore(radius);
			addChild(FilterCircle);
			addChild(Circle);
			
			AddEvent();
		}
		
		private function CircleCore( radius:uint ):void{
			//中心圆形
			CoreCricle = new Ball(radius-8,0x000000,0.4);
			//边框
			CoreCricleFra = new Ball(radius-10,this.color);
			//显示名字
			var txt :TextField  = new TextField();
			
			var txtformat :TextFormat =new TextFormat();
			txtformat.size =Constant.MAIN_NAME_SIZE;
			txtformat.font = Constant.FONT;
			txt.selectable = false ;
			txt.text =this.names;
			txt.setTextFormat(txtformat);
			txt.textColor = Constant.NAME_TEXT_COLOR;
			txt.x =(this.width-txt.textWidth  )/2-2;
			txt.y =(this.height - txt.textHeight )/2-3; 
			txt.width = txt.textWidth+5;
			
			Circle = new Sprite ();
			Circle.addChild(CoreCricleFra);	
			Circle.addChild(CoreCricle);	
			Circle.addChild(txt);
			
			//圆形透明覆盖响应mouse消息实现动态效果
			maskTmp= new Ball(radius-12,this.color,0);			
			Circle.addChild(maskTmp);
		}
		
		//添加事件
		private function AddEvent():void{
			//监听鼠标移入和移出事件
			this.times=6;
			this.rate=25;
			
			//实现点击查询
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
			FlexGlobals.topLevelApplication.getCoauthor(this.names);
			//Application.application.inquiry(this.names);
			
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
				//这里的DrawLine不是下面的那个函数，而是relationline里面的函数
				LineStage[i].DrawLine(point);
			}
  		}
		
		private function DrawLine(point:*, index:int, arr:Array):void{
			var tmpLine:RelationLine = new RelationLine(new Point(point.x,point.y),relationPoint[index].Article);
			addChild(tmpLine);
			this.setChildIndex(tmpLine,this.numChildren-1);
			//
			LineStage.push (tmpLine);
		}
		
		public function setArticleList(res:Array):void{
			articleList= new ArticleList(res);			
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