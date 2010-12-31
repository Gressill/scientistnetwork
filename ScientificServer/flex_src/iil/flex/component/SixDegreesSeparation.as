package iil.flex.component
{
	import flash.display.Sprite;
	import flash.events.MouseEvent;
	import flash.text.TextField;
	import flash.text.TextFormat;
	
	import iil.flex.config.Constant;
	
	import mx.collections.ArrayCollection;
	
	public class SixDegreesSeparation extends Sprite{
		
		private var middleAuthorArray:Array=[];
		private var sixDegree:ArrayCollection=null;
		private var mainName:String;
		public function SixDegreesSeparation(sixResultFromServer:ArrayCollection , name:String){

			sixDegree=sixResultFromServer;
			mainName=name;
			Init();
			
		} 
		
		private function AddEvent():void{
			
			this.addEventListener(MouseEvent.MOUSE_DOWN,MouseDown);
			this.addEventListener(MouseEvent.MOUSE_UP,MouseUp);
			//this.addEventListener(Event.ENTER_FRAME, onEnterFrame);
		}
		
		private function Init():void{
			//var res:Array =["aaa","adsfa","adfaf"];
			//AddEvent();
			
			var main:ShowCircle = new ShowCircle(mainName,Constant.SIX_DRGREE_CIRCLE_COLOR);
			main.scaleX=Constant.SCALE;
			main.scaleY =Constant.SCALE;			
			addChild(main);				
			middleAuthorArray.push(main);
			
			var articleArray:Array=[];
			var num:uint=0;
			for(var i:uint =0 ; i<sixDegree.length ;i++){
				
				var Circle:ShowCircle=null;
				if(sixDegree[i].indexOf("author:")!=-1){	
					//author
					sixDegree[i] = sixDegree[i].replace("author:","");
					Circle = new ShowCircle(sixDegree[i],Constant.SIX_DRGREE_CIRCLE_COLOR);	
					Circle.scaleX=Constant.SCALE;
					Circle.scaleY =Constant.SCALE;
					Circle.x =160+160*num;					
					
					middleAuthorArray.push(Circle);
					middleAuthorArray[num].Article =articleArray;
					//逆向连接，第二个点连向第一个点
					Circle.LineRelation( new Array(middleAuthorArray[num]) );
					addChild(Circle);

					num++;
					articleArray=[];
					
				}else{
					//artitlce
					articleArray.push(sixDegree[i]);
				}
			}


			//显示名字
			var txt :TextField  = new TextField();
			
			var txtformat :TextFormat =new TextFormat();
			txtformat.size =14;
			txtformat.font = Constant.FONT;
			txt.selectable = false ;
			txt.text = Constant.SEARCH_TEXT;
			txt.setTextFormat(txtformat);
			txt.textColor = Constant.NAME_TEXT_COLOR;
			txt.x =(this.width-txt.textWidth  )/2-50;
			txt.y =60; 
			txt.width = txt.textWidth+5;
			txt.alpha =0.6
			//this.addChild(txt);	
					
		}
		
		private function MouseDown(event:MouseEvent):void{
			//if()
			//onEnterFrame(event);
			this.startDrag();
			
		}
		
		private function MouseUp(event:MouseEvent):void{
			
			this.stopDrag();
			
		}	
		
	}
	
}