﻿
package iil.flex.component{
	
	//搜索框
	
	import flash.display.CapsStyle;
	import flash.display.GradientType;
	import flash.display.JointStyle;
	import flash.display.LineScaleMode;
	import flash.display.SpreadMethod;
	import flash.display.Sprite;
	import flash.events.KeyboardEvent;
	import flash.geom.Matrix;
	import flash.text.TextField;
	import flash.text.TextFieldType;
	import flash.text.TextFormat;
	import flash.ui.Mouse;
	
	import iil.flex.component.Arrow;
	import iil.flex.component.SpecialButton;
	
	import mx.core.Application;
	import  mx.controls.Alert ;
	public class Search extends Sprite{
		
		public var input :TextField = null ;
		public var inputTo :TextField = null ;
		
		public var button :SpecialButton = null;
		private var style:Boolean =true;
		
		
		public function Search(style:Boolean =true){
			this.style=style;
			 DrawUI();
			 AddEvent();
		}
		
		private function DrawUI():void{
			//渐变圆角背景
			var fillType:String = GradientType.LINEAR ;
  			var colors:Array = [0xfb0000, 0x9b0000];
  			var alphas:Array = [1, 1];
  			var ratios:Array = [0x0, 0xff];
 			var matr:Matrix = new Matrix();
			var w:uint = 380 ;
			var h:uint = 60 ;
			
			matr.createGradientBox(w, h, (3/2)*Math.PI, 0, 0);
			var spreadMethod:String = SpreadMethod.PAD;
 			this.graphics.beginGradientFill(fillType, colors, alphas, ratios, matr, spreadMethod);  
			this.graphics.drawRoundRect(0,0,w,h,15,15);			
			
			
			if(this.style){
				//关系搜索
				RelationSearch();
			}else{
				//六度
				SixDegreesSeparation();
			}
		}
		private function AddEvent():void{
			
			this.addEventListener(KeyboardEvent.KEY_UP,Enter);
			
		}
		
		private function Enter(evt:KeyboardEvent):void{
			
			if(evt.keyCode == 13){
				ButtonClick();
			}
			
		}
		
		private function ButtonClick():void{
			
			if(this.style){
				
				if(input.text!="")
					Application.application.inquiry(input.text);
				else
					Alert.show("please input author name!","Warning");					
				
			}else{
				
				if(input.text!="" && inputTo.text!="")
					Application.application.sixDs(input.text,inputTo.text);
				else
					
					Alert.show("please input author name!","Warning");
			}
			
		}
		
		//关系是搜索样式
		public function RelationSearch():void{
			//搜索框
			input = CreateTextField (10,32,290,20,true);
			//input.text ="zi-ke zhang";
			addChild(input);
			
			//添加搜索按钮
			button = new SpecialButton(60,24,"Search",0xffffff);
			//设置button点击回调函数
			button.CallClick = ButtonClick;
			button.x = 310;
			button.y=30;
			addChild(button);
			
		}
		//六度搜索 
		public function SixDegreesSeparation ():void{
			/**/
			input = CreateTextField (10,32,130,20,true);
			inputTo = CreateTextField (170,32,120,20,true);
			addChild(input);
			addChild(inputTo);
			
			//箭头
			var arrow:Arrow = new Arrow();
			arrow.x=145;
			arrow.y=26;
			addChild(arrow);
			//添加搜索按钮
			button = new SpecialButton(60,24,"SixDS",0xffffff);
			//设置button点击回调函数
			button.CallClick = ButtonClick;
			button.x = 310;
			button.y=30;
			addChild(button);		
		}
		
        private function CreateTextField(x:Number, y:Number, width:Number, height:Number , input:Boolean = false ):TextField {
            
			var result:TextField = new TextField();
			
			var txtformat :TextFormat =new TextFormat();
			txtformat.size =50;
			txtformat.font = "微软雅黑";

			result.setTextFormat(txtformat);
			
			//判断是否可输入
			if(input){
				//圆角输入框
				result.type  = TextFieldType.INPUT;
				result.background  = true;
				this.graphics.beginFill(0xffffff);
				this.graphics.drawRoundRect(x-2,y-2,width+4,height+4,5,5);			
				this.graphics.endFill();
			}
			
			result.x = x; 
			result.y = y;
            result.width = width;
			result.height = height;	
            return result;
        }
		
		
	}
}