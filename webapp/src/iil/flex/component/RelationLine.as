
package  iil.flex.component{
	
	//展示人物关系 (包括文章显示)
	import flash.display.CapsStyle;
	import flash.display.JointStyle;
	import flash.display.LineScaleMode;
	import flash.display.Sprite;
	import flash.events.MouseEvent;
	import flash.events.TimerEvent;
	import flash.geom.Point;
	import flash.text.TextField;
	import flash.text.TextFieldType;
	import flash.text.TextFormat;
	import flash.utils.Timer;
	
	import iil.flex.component.ArticleList;
	
	public class RelationLine extends Sprite{
		
		private var times:uint ;
		private var rate:uint ;
		private var line :Sprite ;
		public  var articles:ArticleList ;
		
		public function RelationLine(point:Point,res:Array ):void{
			
			this.buttonMode = true;
			this.useHandCursor = true;
			this.Article = res;
			AddEvent();
			DrawLine(point);
		}
		
		private function AddEvent():void{
			//监听鼠标移入和移出事件
			this.times=4;
			this.rate=50
			this.addEventListener (MouseEvent.MOUSE_OVER ,MouseLineOver );
			this.addEventListener (MouseEvent.MOUSE_OUT ,MouseLineOut);
			
		}
		
		public function DrawLine( point:Point ):void{
			
			this.graphics.clear();
			
			this.graphics.lineStyle(1.5, 0xffffff, 1 , true, LineScaleMode.NONE,
                               CapsStyle.NONE, JointStyle.MITER, 10);
			this.alpha=0.3;
			
			this.graphics.moveTo(0,0);
			point = this.globalToLocal(new Point(point.x,point.y));
			this.graphics.lineTo(point.x,point.y);
			
		}
		
		private function MouseLineOver(Mouse_Event:MouseEvent):void{
			this.alpha =1;			
			articles.x=mouseX;
			articles.y=mouseY;
			addChild(articles);
		}

		private function MouseLineOut(Mouse_Event:MouseEvent):void{
			this.alpha =0.3;
			removeChild(articles);
		}
		
		public function set Article(res:Array):void{
			articles= new ArticleList(res);
			
		}



	}
}