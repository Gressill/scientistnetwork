
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
	import iil.flex.config.Constant;
	
	public class RelationLine extends Sprite{
		
		private var times:uint ;
		private var rate:uint ;
		private var line :Sprite ;
		private var isArticleBoxExist:Boolean = false;
		//public var removeArticleBox:Boolean = true;
		public  var articleList:ArticleList ;
		public function RelationLine(point:Point,articleList:Array ):void{
			
			this.buttonMode = true;
			this.useHandCursor = true;
			this.Article = articleList;
			AddEvent();
			DrawLine(point);
		}
		
		private function AddEvent():void{
			//监听鼠标移入和移出事件
			this.times=4;
			this.rate=50
			this.addEventListener (MouseEvent.ROLL_OVER ,MouseLineOver );
			this.addEventListener (MouseEvent.ROLL_OUT ,MouseLineOut);
			//this.addEventListener(MouseEvent.CLICK,showArticleBox);
			
		}
		
		public function DrawLine( point:Point ):void{
			
			this.graphics.clear();
			
			this.graphics.lineStyle(1.5, 0xffffff, 1 , true, LineScaleMode.NONE,
                               CapsStyle.NONE, JointStyle.MITER, 10);
			this.alpha=0.3;
			
			//this.graphics.moveTo(0,0);
			point = this.globalToLocal(new Point(point.x,point.y));
			//this.graphics.lineTo(point.x,point.y);
			this.graphics.lineTo(caculateJointPoint(point).x,caculateJointPoint(point).y);
			
			//test
			
			//this.alpha =1;			
			//articles.x=mouseX;
			//articles.y=mouseY;
			//addChild(articles);
			
		}
		
		//计算中心圆和旁边的圆连线的连接点，用余弦计算
		private function caculateJointPoint(point:Point):Point{
			var jointPoint:Point = new Point();
			var dx:Number=point.x;//-Constant.CENTERX;
			var dy:Number=point.y;//-Constant.CENTERY;
			var dist:Number=Math.sqrt(dx*dx+dy*dy);
			var radius:uint = Constant.RADIUS-18;
//			var minDist:Number=Constant.RADIUS;
//			if (dist<minDist) {
//				var tx:Number=ball0.x + (dx/dist)*minDist;//ball0.x+Math.cos(angle)*minDist;
//				var ty:Number=ball0.y + (dy/dist)*minDist;//ball0.y+Math.sin(angle)*minDist;
//				var ax:Number = (tx - ball1.x) * spring;
//				var ay:Number = (ty - ball1.y) * spring;
//				ball0.vx-=ax;
//				ball0.vy-=ay;
//				ball1.vx+=ax;
//				ball1.vy+=ay;
//			}
			jointPoint.x = point.x-radius*(dx/dist);
			jointPoint.y = point.y-radius*(dy/dist);
			return jointPoint;
		}
		
		private function MouseLineOver(Mouse_Event:MouseEvent):void{
			//trace("i am in ..");
			this.alpha =1;	
			if(!isArticleBoxExist){
				this.alpha =1;			
				articleList.x=mouseX;
				articleList.y=mouseY;
				if(!this.contains(articleList)){
					addChild(articleList);
					this.setChildIndex(articleList,this.numChildren-1);
					
					//addChildAt(articles,0);
				}
				isArticleBoxExist = true;
				Constant.isArticleBoxExist = true;
			}
		}

		private function MouseLineOut(Mouse_Event:MouseEvent):void{
			//trace("i am out ..");
				this.alpha =0.3;
				removeChild(articleList);
				isArticleBoxExist = false;
//				flash.utils.setInterval(function():void{
//				   removeChild(articles);
//				},1000,null);
		}
		
		private function showArticleBox(Mouse_Event:MouseEvent):void{
			//removeArticleBox = false;
			MouseLineOver(Mouse_Event);
		}
		
		public function set Article(res:Array):void{
			articleList= new ArticleList(res);			
		}
	}
}