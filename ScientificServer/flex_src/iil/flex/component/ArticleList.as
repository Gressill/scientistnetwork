
package iil.flex.component{
	
	import flash.display.Sprite;
	import flash.events.EventPhase;
	import flash.events.MouseEvent;
	import flash.events.TimerEvent;
	import flash.filters.BitmapFilterQuality;
	import flash.filters.BlurFilter;
	import flash.geom.Point;
	import flash.text.TextField;
	import flash.text.TextFieldAutoSize;
	import flash.text.TextFormat;
	import flash.utils.Timer;
	
	import iil.flex.config.Constant;
	
	import mx.core.FlexGlobals;
	
	import spark.components.TextArea;
	
	public class ArticleList extends Sprite{
		
		//private Articles
		private var txt:TextField= null ;
		//private var txta:TextArea= null ;
		private var txtformat :TextFormat = null ;
		//public var isArticleBoxExist:Boolean = true;
		
		public function ArticleList ( article : Array ,typeTag:String = "article"){
			//this.setArticle(article);// = article;
			this.Article = article;
			Constant.TYPE_TAG = typeTag;
			//trace("article.length is: "+Article.length);
			if(article){
				//addEvent();				
				DrawUI(txt.textWidth+20,txt.textHeight +10);
				//Constant.isArticleBoxExist = true;
			}			
		}		
		
		private function addEvent():void{
			//监听鼠标移入和移出事件
			
			//this.addEventListener (MouseEvent.ROLL_OVER ,mouseLineOver );
			//this.addEventListener (MouseEvent.ROLL_OUT ,mouseLineOut);
			//this.addEventListener(MouseEvent.CLICK,showArticleBox);
//			this.addEventListener(MouseEvent.MOUSE_DOWN,MouseDown);
//			this.addEventListener(MouseEvent.MOUSE_UP,MouseUp);
			
		}
		private function DrawUI(width:uint , height:uint ):void{
			
			this.graphics.beginFill(0xffffff,0.8);
			this.graphics.drawRoundRect(-1,-1,width,height,8,8);
			this.graphics.endFill();
			
		}
		
		
		private function mouseLineOver(Mouse_Event:MouseEvent):void{		
			//this.parent.removeChild(this);
			this.stage.focus = this;
			trace("son in")
		}
		
		private function mouseLineOut(Mouse_Event:MouseEvent):void{
			//this.parent.removeChild(this);
			//this.visible = true;
			trace("son out");
			//trace(FlexGlobals.topLevelApplication.getChildAt(0));
			//Constant.isArticleBoxExist = false;
		}
		private function set Article(articleList:Array):void{
			//显示名字
			txt = new TextField();
			txt.textColor=0x000000;
			//txt.text = "合作文献\r";				
			if(articleList){
				articleList.forEach(addList);
			}			
//			txt.x =5;
//			txt.y =5; 
			
			txt.width = txt.textWidth+5;
			txt.autoSize =TextFieldAutoSize.LEFT;
			txt.multiline =true;
			//txt.mouseEnabled = true;
			
			addChild(txt);
			this.setChildIndex(txt,this.numChildren-1);
//			var test:Sprite = new Sprite();
//			test.graphics.beginFill(0xffffff,0.8);
//			test.graphics.drawRoundRect(0,0,width,height,8,8);
//			test.graphics.endFill();
//			addChild(test);

		}
		
		private function addList(element:String, index:int, arr:Array):void{
			
			//if(element.length>FlexGlobals.topLevelApplication)
//			txt.appendText("《");
//			txt.appendText(element);
//			txt.appendText("》\r\n");
			//var ttt:String = "《"+element+"》"+"\r\n";
			//trace("ttt is : "+ttt);
			//txt.appendText(ttt);
			txt.multiline = true;
			txt.textColor = 0x000000;
			var temp:String = "";
			if(Constant.TYPE_TAG == "name"){
				temp = "<u><a target='_blank' href='http://arxiv.org/abs/1005.2652'>"+element+"<br>";
			}else{
				temp = "<u><a target='_blank' href='http://arxiv.org/abs/1005.2652'>"+"《"+element+"》<br>";
			}
			txt.htmlText += temp;
			//txt.text += "《"+element+"》"+"\r";
			//ttt.replace(/^\s*|\s*$/g,"").split(" ").join("")
		}	
		
		private function MouseDown(event:MouseEvent):void{
			
			this.startDrag();
			
		}
		
		private function MouseUp(event:MouseEvent):void{
			
			this.stopDrag();
			
		}
	}
	
}