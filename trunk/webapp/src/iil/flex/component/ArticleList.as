
package iil.flex.component{
	
	import flash.display.Sprite;
	import flash.events.EventPhase;
	import flash.events.MouseEvent;
	import flash.events.TimerEvent;
	import flash.filters.BitmapFilterQuality;
	import flash.filters.BlurFilter;
	import flash.geom.Point;
	import flash.text.TextField;
	import flash.text.TextFormat;
	import flash.utils.Timer;
	import flash.text.TextFieldAutoSize ;
	
	public class ArticleList extends Sprite{
		
		//private Articles
		private var txt:TextField = null ;
		private var txtformat :TextFormat = null ;
		
		public function ArticleList ( Article : Array ){
			
			this.Article=Article;
			
			if(Article){
				
				DrawUI(txt.textWidth+20,txt.textHeight +10);
				
			}
			
			
		}
		
		private function DrawUI(width:uint , height:uint ):void{
			
			this.graphics.beginFill(0xffffff,0.8);
			this.graphics.drawRoundRect(0,0,width,height,8,8);
			this.graphics.endFill();
			
		}
		
		private function set Article(res:Array):void{
			//显示名字
			txt   = new TextField();

			txt.textColor=0x000000;
			//txt.text = "合作文献\r";
				
			if(res){
				res.forEach(addList);
			}
			
			
			txt.x =5;
			txt.y =5; 
			
			txt.width = txt.textWidth+5;
			txt.autoSize =TextFieldAutoSize.LEFT;
			txt.multiline =true;
			addChild(txt);

		}
		
		private function addList(element:*, index:int, arr:Array):void{
			
			txt.text +="《"+element+"》"+"\r";

		}
	}
	
}