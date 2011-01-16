package iil.flex.component
{
	/*
	We import the built-in AS3 classes that our class needs and
	then begin our class. The class extends the Sprite class.
	Hence, it will inherit all properties and methods
	of the Sprite class.
	
	This class uses the same basic 3D rendering functions that we introduced
	in the tutorial 'Simple 3D Drawing in Flash CS3' (http://www.flashandmath.com/advanced/simple3d/).
	These functions are: setVertices, setFaces, renderView. In this particular class,
	the vertices and faces correspond to a hexagonal cylinder.
	*/
	
	import flash.display.Sprite;
	
	import flash.display.Shape;
	
	public class HexCylinder extends Sprite {
		
		/*
		The two public properties, curTheta and curPhi, represent the current
		angles of displacement for the observer. curTheta corresponds to the horizontal
		displacement, curPhi to the vertical displacement. 
		When the two values for the angles, in degrees, are passed
		to the renderView method, the method renders the view of the cylinder
		corresponding to those view angles.
		*/
		
		public var curTheta:Number;
		
		public var curPhi:Number;
		
		protected var fLen:Number;
		
		protected var vertsArray:Array;
		
		protected var facesArray:Array;
		
		protected var numVertices:int;
		
		protected var numFaces:int;
		
		protected var spHolder:Sprite;
		
		protected var cylinRad:Number;
		
		protected var cylinHeight:Number;
		
		protected var shSolid:Shape;
		
		protected var facesColors:Array;
		
		protected var opacity:Number;
		
		protected var bordColor:Number;
		
		protected var bordThick:Number;
		
		/*
		The constructor takes two parameters: the radius and the height of the cylinder.
		The cylinder will be drawn in such a way that the registration point of each instance
		of HexCylinder will be placed in the upper left corner.
		*/
		
		public function HexCylinder(r:Number,h:Number){
			
			this.cylinRad=r;
			
			this.cylinHeight=h;
			
			this.fLen=400;
			
			this.vertsArray=[];
			
			this.facesArray=[];
			
			this.numVertices=12;
			
			this.numFaces=8;
			
			this.spHolder=new Sprite();
			
			this.addChild(spHolder);
			
			this.shSolid=new Shape();
			
			spHolder.addChild(shSolid);
			
			shSolid.x=cylinRad;
			
			shSolid.y=cylinHeight/2;
			
			this.curTheta=30;
			
			this.curPhi=90;
			
			this.facesColors=[0xFF89B9,0x33FFFF,0xFFFFCC,0xFF9966,0x3585FD,0x89FEB7,0x9A7DDF,0xFFCCFF];
			
			this.opacity=0.8;
			
			this.bordColor=0xCC0000;
			
			this.bordThick=1;
			
			setVertices();
			
			setFaces();
			
			renderView(curTheta,curPhi);
			
		}
		
		/*
		Public methods for customizing the appearance of the cylinder,
		and the renderView method for rotating the cylinder.
		*/
		
		public function setBorderColorAndThick(c:Number,t:Number):void {
			
			bordColor=c;
			
			bordThick=t;
			
			renderView(curTheta,curPhi);
			
		}
		
		public function setOpacity(o:Number):void {
			
			opacity=o;
			
			renderView(curTheta,curPhi);
			
		}
		
		public function setFacesColors(a:Array):void {
			
			var i:int;
			
			for(i=0;i<numFaces;i++){
				
				facesColors[i]=a[i];
				
			}
			
			renderView(curTheta,curPhi);
			
		}
		
		public function renderView(t:Number,p:Number):void {
			
			var i:int;
			
			var j:int;
			
			var distArray=[];
			
			var dispArray=[];
			
			var vertsNewArray=[];
			
			var midPoint:Array=[];
			
			var dist:Number;
			
			var curFace:int;
			
			var curFaceLen:int;
			
			t=t*Math.PI/180;
			
			p=p*Math.PI/180;
			
			shSolid.graphics.clear();
			
			for(i=0;i<numVertices;i++){
				
				vertsNewArray[i]=pointNewView(vertsArray[i],t,p); 
				
			}
			
			for(i=0;i<numFaces;i++){
				
				curFaceLen=facesArray[i].length;
				
				midPoint[0]=0;
				
				midPoint[1]=0;
				
				midPoint[2]=0;
				
				for(j=0;j<curFaceLen;j++){
					
					midPoint[0]+=vertsNewArray[facesArray[i][j]][0];
					
					midPoint[1]+=vertsNewArray[facesArray[i][j]][1];
					
					midPoint[2]+=vertsNewArray[facesArray[i][j]][2];
					
				}
				
				midPoint[0]/=curFaceLen;
				
				midPoint[1]/=curFaceLen;
				
				midPoint[2]/=curFaceLen;
				
				dist=Math.sqrt(Math.pow(fLen-midPoint[0],2)+Math.pow(midPoint[1],2)+Math.pow(midPoint[2],2));
				
				distArray[i]=[dist,i];
				
			}
			
			distArray.sort(byDist);
			
			for(i=0;i<numVertices;i++){
				
				dispArray[i]=[fLen/(fLen-vertsNewArray[i][0])*vertsNewArray[i][1],-fLen/(fLen-vertsNewArray[i][0])*vertsNewArray[i][2]];
				
			}
			
			for(i=0;i<numFaces;i++){
				
				shSolid.graphics.lineStyle(bordThick,bordColor);
				
				curFace=distArray[i][1]; 
				
				curFaceLen=facesArray[curFace].length;
				
				shSolid.graphics.beginFill(facesColors[curFace],opacity);
				
				shSolid.graphics.moveTo(dispArray[facesArray[curFace][0]][0],dispArray[facesArray[curFace][0]][1]);
				
				for(j=1;j<curFaceLen;j++){
					
					shSolid.graphics.lineTo(dispArray[facesArray[curFace][j]][0],dispArray[facesArray[curFace][j]][1]);
					
				}
				
				shSolid.graphics.lineTo(dispArray[facesArray[curFace][0]][0],dispArray[facesArray[curFace][0]][1]);
				
				shSolid.graphics.endFill();
				
			}
			
		}
		
		//Protected methods.
		
		protected function byDist(v:Array,w:Array):Number {
			
			if (v[0]>w[0]){
				
				return -1;
				
			} else if (v[0]<w[0]){
				
				return 1;
				
			} else {
				
				return 0;
			}
			
		}
		
		
		protected function pointNewView(v:Array,theta:Number,phi:Number):Array {
			
			var newCoords:Array=[];
			
			newCoords[0]=v[0]*Math.cos(theta)*Math.sin(phi)+v[1]*Math.sin(theta)*Math.sin(phi)+v[2]*Math.cos(phi);
			
			newCoords[1]=-v[0]*Math.sin(theta)+v[1]*Math.cos(theta);
			
			newCoords[2]=-v[0]*Math.cos(theta)*Math.cos(phi)-v[1]*Math.sin(theta)*Math.cos(phi)+v[2]*Math.sin(phi);
			
			return newCoords;
			
			
		}
		
		
		protected function setVertices():void {
			
			vertsArray[0]=[cylinRad,0,cylinHeight/2];
			
			vertsArray[1]=[cylinRad*0.5,cylinRad*0.866,cylinHeight/2];
			
			vertsArray[2]=[-cylinRad*0.5,cylinRad*0.866,cylinHeight/2];
			
			vertsArray[3]=[-cylinRad,0,cylinHeight/2];
			
			vertsArray[4]=[-cylinRad*0.5,-cylinRad*0.866,cylinHeight/2];
			
			vertsArray[5]=[cylinRad*0.5,-cylinRad*0.866,cylinHeight/2];
			
			vertsArray[6]=[cylinRad,0,-cylinHeight/2];
			
			vertsArray[7]=[cylinRad*0.5,cylinRad*0.866,-cylinHeight/2];
			
			vertsArray[8]=[-cylinRad*0.5,cylinRad*0.866,-cylinHeight/2];
			
			vertsArray[9]=[-cylinRad,0,-cylinHeight/2];
			
			vertsArray[10]=[-cylinRad*0.5,-cylinRad*0.866,-cylinHeight/2];
			
			vertsArray[11]=[cylinRad*0.5,-cylinRad*0.866,-cylinHeight/2];
			
		}
		
		protected function setFaces():void {
			
			facesArray[0]=[0,1,2,3,4,5];
			
			facesArray[1]=[6,7,8,9,10,11];
			
			facesArray[2]=[0,1,7,6];
			
			facesArray[3]=[1,2,8,7];
			
			facesArray[4]=[2,3,9,8];
			
			facesArray[5]=[3,4,10,9];
			
			facesArray[6]=[4,5,11,10];
			
			facesArray[7]=[5,0,6,11];
			
		}
	}
}