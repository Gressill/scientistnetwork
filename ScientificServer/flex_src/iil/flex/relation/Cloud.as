package iil.flex.relation
{
	import flash.display.Sprite;
	
	import iil.flex.config.Constant;
	
	import mx.collections.ArrayCollection;
	import mx.collections.ArrayList;
	
	public class Cloud extends Sprite
	{
		private var aPoint:ArrayCollection;
		//		private var aPointArray:ArrayCollection = new ArrayCollection();
		private var aPointArray:ArrayList;
		
		private var stageWidth:int=1440;
		private var stageHeight:int=700;
		private var centerX:int=720;
		private var centerY:int=350;
		
		private var degreeCircleRadius:int = 100;//度半径
		private var radius:int = 100;//球半径
		private var authorRadius:int = 4;
		
		private var userName:String = null;
		
		public function Cloud(width:Number,height:Number,authorArray:ArrayCollection,mainName:String)
		{
			if(width != 0){
				stageWidth = width;
//				trace("cloud stageWidth is "+stageWidth);
			}
			if(height != 0){
//				trace("cloud stageHeight is "+stageHeight);
				stageHeight = height;
			}
			//			trace(width+"asdas "+ heigth);
			centerX = stageWidth/2;
			centerY = stageHeight/2;
			userName = mainName;
			initCloud(authorArray);
		}
		
		public function initCloud(authorArray:ArrayCollection):void
		{
			this.degreeCircleRadius = 100;
			this.authorRadius = 4;
			var tempX:int;
			var tempY:int;
			var tempXShift:int = (stageWidth-degreeCircleRadius*2)/2;//计算位置的偏移量，永固测定每个圆弧圆心都处于屏幕中心
			var tempYShift:int = (stageHeight-degreeCircleRadius*2)/2;//计算位置的偏移量，永固测定每个圆弧圆心都处于屏幕中心
			var pointColor:int = 0xffffff;
			
			addCenterBall(userName,0xff9800);
			//			var authorList;
			var jumpIndex:int = 0;
			for each(var authorList in authorArray){
				//到最后一度的时候跳出，因为最后一度要布满全屏幕
//				trace(jumpIndex)
				for each(var name in authorList)
				{
					if(jumpIndex >2){
						tempX = Math.random()*stageWidth;
						tempY = Math.random()*stageHeight;
						pointColor = 0xffffff;
//						trace("asdasds");
						while(!inLastCircle(tempX,tempY,degreeCircleRadius-100)){
							tempX = Math.random()*stageWidth;
							tempY = Math.random()*stageHeight;
						}
					}else{
						tempX = Math.random()*degreeCircleRadius*2+tempXShift;
						tempY = Math.random()*degreeCircleRadius*2+tempYShift;
						while(!inCircle(tempX,tempY,degreeCircleRadius-100)){
							tempX = Math.random()*degreeCircleRadius*2+tempXShift;
							tempY = Math.random()*degreeCircleRadius*2+tempYShift;
						}
					}
					var point:AuthorPoint = new AuthorPoint(tempX,tempY,name,authorRadius,Constant.degreeColor[jumpIndex]);
					//aPointArray.addItem(point);
					addChild(point);
				}
				degreeCircleRadius+=100;
				pointColor = pointColor/100;
				authorRadius--;
				tempXShift = (stageWidth-degreeCircleRadius*2)/2;
				tempYShift = (stageHeight-degreeCircleRadius*2)/2;
				jumpIndex++;
			}
		}
		
		/**
		 * 让不同度的点分布在不同的圆环里面
		 */
		private function inCircle(x:int,y:int,innerRadius:int):Boolean {
			//			var hitOtherBall:Boolean = false;
			//trace(ball.x+" "+ball.y);
			var dx:int=x-centerX;
			var dy:int=y-centerY;
			var lastMinDist = innerRadius;
			var minDist:int=degreeCircleRadius;
			if(dx > minDist || dy > minDist || dx*(-1) > minDist || dy*(-1) > minDist ) {
				return false;
			}
			var dist:Number=Math.sqrt(dx*dx+dy*dy);
			//			trace("degreeCircleRadius : "+ degreeCircleRadius);
			//			trace("dist : "+ dist)
			if (dist<minDist && dist > lastMinDist) {
				return true;
			}
			return false;
		}
		
		/**
		 * 让不同度的点分布在不同的圆环里面
		 */
		private function inLastCircle(x:int,y:int,innerRadius:int):Boolean {
			//			var hitOtherBall:Boolean = false;
			//trace(ball.x+" "+ball.y);
			var dx:int=x-centerX;
			var dy:int=y-centerY;
			var lastMinDist = innerRadius;
//			var minDist:int=degreeCircleRadius;
			if(dx > lastMinDist || dy > lastMinDist || dx*(-1) > lastMinDist || dy*(-1) > lastMinDist ) {
//				trace(x+" "+y + " " + minDist);
				return true;
			}
			var dist:Number=Math.sqrt(dx*dx+dy*dy);
			//			trace("degreeCircleRadius : "+ degreeCircleRadius);
			//			trace("dist : "+ dist)
			if (dist > lastMinDist) {
				return true;
			}
			return false;
		}
		
		private function addCenterBall(name:String,color:Number):void {
//			var centerBall :Ball
//			stageWidth = Constant.SCREEN_WIDTH;
//			stageHeight = Constant.SCREEN_HEIGHT-Constant.RELATION_Y;
			//设置中心任务
//			var centerBall :ShowCircle = new ShowCircle(name,color);
//			
//			centerBall.x=Constant.CENTERX;
//			centerBall.y=Constant.CENTERY;
//			//中心圆放大倍数
//			centerBall.scaleX=Constant.SCALE;
//			centerBall.scaleY=Constant.SCALE;
//			centerBall.radius = Constant.RADIUS*Constant.SCALE;
//			centerBall.Article = null;
//			this.addChild(centerBall);
			
			trace("centerX is "+centerX +". centerY is "+centerY)
			var centerBall:AuthorPoint = new AuthorPoint(centerX,centerY,name,6,0xcbfd03);
//			centerBall.x=Constant.CENTERX;
//			centerBall.y=Constant.CENTERY;
			//中心圆放大倍数
//			centerBall.scaleX=Constant.SCALE;
//			centerBall.scaleY=Constant.SCALE;
//			centerBall.radius = Constant.RADIUS*Constant.SCALE;
			this.addChild(centerBall);
		}
	}
}