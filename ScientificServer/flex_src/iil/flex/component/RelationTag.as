package iil.flex.component
{
	import flash.display.Sprite;
	
	import mx.core.FlexGlobals;
	
	public class RelationTag extends Sprite{

		
		//定义2个tag button
		public var index:TagButton,sixIndex:TagButton,cloudBtn:TagButton;
		
		public function RelationTag(){
			DrawUI();
		}
		
		public function DrawUI():void{
			//渐变圆角背景
			var w:uint =210;
			var h:uint =50;
			this.graphics.beginFill(0x4b1e00);
			this.graphics.drawRoundRect(0,0,w,h,15,15);		
			this.graphics.endFill();
			this.alpha=0.9;
		
			//生成tag button
			index = new TagButton("Index",50,24);
			sixIndex = new TagButton("SixDS",50,24);
			cloudBtn = new TagButton("Cloud",50,24);
			
			//设置显示位置
			index.x=20;
			index.y=20;
			index.isNow=true;
			sixIndex.x=80;
			sixIndex.y=20;
			cloudBtn.x=140;
			cloudBtn.y = 20;
			
			//添加button回调函数
			sixIndex.callBack = sixIndexFunction;
			index.callBack =indexFunction
			cloudBtn.callBack = cloudFunction;
			addChild(sixIndex);
			addChild(index);
			addChild(cloudBtn);
		}
		
		//按钮回调函数
		private function indexFunction():void{
			sixIndex.isNow=false;
			cloudBtn.isNow=false;
			sixIndex.setTranBackground();
			cloudBtn.setTranBackground();
			index.isNow=true;	
			
			FlexGlobals.topLevelApplication.myRelation.SetRelationSearch();
		}
		
		private function sixIndexFunction():void{
			index.isNow=false;
			cloudBtn.isNow=false;
			index.setTranBackground();
			cloudBtn.setTranBackground();
			sixIndex.isNow=true;
			
			FlexGlobals.topLevelApplication.myRelation.SetSixDegrees();
		}
		
		private function cloudFunction():void{
			index.isNow=false;
			sixIndex.isNow=false;
			index.setTranBackground();
			sixIndex.setTranBackground();
			cloudBtn.isNow=true;
			
			FlexGlobals.topLevelApplication.myRelation.SetCloudSearch();
		}	
	}
}