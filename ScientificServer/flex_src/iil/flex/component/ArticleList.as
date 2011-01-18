
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
			if(element.length>0)
			{
				var paperUrl:Array = element.split("=");
			}
			if(Constant.TYPE_TAG == "name"){
				var tempAuthorSplit:Array = paperUrl[0].split(" ");
				var tempAuthor:String = "";
				for each(var k:String in tempAuthorSplit){
					tempAuthor += k;
					tempAuthor +="_";
				}
				tempAuthor = tempAuthor.substr(0,tempAuthor.length-1);
				//http://publish.aps.org/search/field/author/Stauffer_Dietrich
				temp = "<u><a target='_blank' href='http://publish.aps.org/search/field/author/"+tempAuthor+"'>"+paperUrl[0]+"<br>";
			}else{
				var tempUrl = "";
				if(paperUrl.length>1){
					if(paperUrl[1].indexOf("arXiv:")>=0){
						paperUrl[1] = paperUrl[1].substr(6,paperUrl[1].length-1);
						tempUrl = "http://arxiv.org/abs/"+paperUrl[1];
					}else{
						tempUrl = "http://link.aps.org/doi/"+paperUrl[1];
					}
				}
//				temp = "<u><a target='_blank' href='http://pre.aps.org/abstract/PRE/v52/i6/p6303_1'>"+"《"+element+"》<br>";
				temp = "<u><a target='_blank' href='"+tempUrl+"'>"+"《"+paperUrl[0]+"》<br>";
//				http://link.aps.org/doi/10.1103/PhysRevE.52.6303
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