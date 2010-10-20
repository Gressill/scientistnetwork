package iil.flex.component
{
	import flash.display.Sprite;
	import mx.core.Application;
	
	public class RelationTag extends Sprite{

		
		//定义2个tag button
		public var index,sixIndex:TagButton;
		
		public function RelationTag(){
			DrawUI();
		}
		
		public function DrawUI():void{
			//渐变圆角背景
			var w:uint =150;
			var h:uint =50;
			this.graphics.beginFill(0x4b1e00);
			this.graphics.drawRoundRect(0,0,w,h,15,15);		
			this.graphics.endFill();
			this.alpha=0.9;
		
			//生成tag button
			index = new TagButton("Index",50,24);
			sixIndex = new TagButton("SixDS",50,24);
			
			//设置显示位置
			index.x=20;
			index.y=20;
			index.isNow=true;
			sixIndex.x=80;
			sixIndex.y=20;
			
			//添加button回调函数
			sixIndex.callBack = sixIndexFunction;
			index.callBack =indexFunction
			addChild(sixIndex);
			addChild(index);
		}
		
		//按钮回调函数
		private function indexFunction():void{
			sixIndex.isNow=false;
			sixIndex.setTranBackground();
			index.isNow=true;	
			
			Application.application.myRelation.SetRelationSearch();
			
		}
		
		private function sixIndexFunction():void{
			index.isNow=false;
			index.setTranBackground();
			sixIndex.isNow=true;
			
			Application.application.myRelation.SetSixDegrees();
			
			
		}	
	}
}