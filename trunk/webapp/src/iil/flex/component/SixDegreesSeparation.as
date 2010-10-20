package iil.flex.component
{
	import flash.display.Sprite;
	import flash.text.TextField;
	import flash.text.TextFormat;
	
	import mx.collections.ArrayCollection;
	
	public class SixDegreesSeparation extends Sprite{
		
		private var obj:Array=[];
		private var data:ArrayCollection=null;
		private var mainName:String;
		public function SixDegreesSeparation(res:ArrayCollection , name:String){

			data=res;
			mainName=name;
			Init();
			
		} 
		
		public function Init():void{
			//var res:Array =["aaa","adsfa","adfaf"];
			
			var main:ShowCircle = new ShowCircle(mainName,0xffffff);
			main.scaleX=1.3;
			main.scaleY =1.3;			
			addChild(main);				
			obj.push(main);
			
			var article:Array=[];
			var num:uint=0;
			for(var i:uint =0 ; i<data.length ;i++){
				
				var Circle:ShowCircle=null;
				if(data[i].indexOf("author:")!=-1){	
					
					data[i] = data[i].replace("author:","");
					Circle = new ShowCircle(data[i],0xffffff);	
					Circle.scaleX=1.3;
					Circle.scaleY =1.3;
					Circle.x =160+160*num;					

					
					obj.push(Circle);
					obj[num].Article =article;
					Circle.LineRelation( new Array(obj[num]) );
					addChild(Circle);

					num++;
					article=[];
					
				}else{
					
					article.push(data[i]);

				}
			

			}


			//显示名字
			var txt :TextField  = new TextField();
			
			var txtformat :TextFormat =new TextFormat();
			txtformat.size =14;
			txtformat.font = "微软雅黑";
			txt.selectable = false ;
			txt.text ="Click Name Search it!";
			txt.setTextFormat(txtformat);
			txt.textColor  =0xffffff;
			txt.x =(this.width-txt.textWidth  )/2-50;
			txt.y =60; 
			txt.width = txt.textWidth+5;
			txt.alpha =0.6
			this.addChild(txt);	
					
		}
		
	}
	
}