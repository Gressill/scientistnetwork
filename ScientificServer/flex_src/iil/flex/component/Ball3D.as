package iil.flex.component
{
	import flash.display.Sprite;
	
	/**
	 *  3D坐标旋转：
	 *	其实跟2D坐标旋转几乎完全相同，只不过要指定绕哪个轴旋转
	 *	绕z轴旋转
	 *	x1 = cos(angleZ) * x - sin(angleZ) * y;
	 *	y1 = cos(angleZ) * y + sin(angleZ) * x;
	 *	绕y轴旋转
	 *  x1 = cos(angleY) * x - sin(angleY) * z;
	 *	z1 = cos(angleY) * z + sin(angleY) * x;
	 *	绕x轴旋转
	 *	y1 = cos(angleX) * y - sin(angleX) * z;
	 *	z1 = cos(angleX) * z + sin(angleX) * y;
	 */
	import flash.display.Sprite;
	public class Ball3D extends Sprite {
		public var radius:Number;
		private var color:uint;
		public var xpos:Number=0;
		public var ypos:Number=0;
		public var zpos:Number=0;
		public var vx:Number=0;
		public var vy:Number=0;
		public var vz:Number=0;
		public var mass:Number=1;
		
		public function Ball3D(radius:Number=40, color:uint=0xff0000) {
			this.radius=radius;
			this.color=color;
			init();
		}
		public function init():void {
			graphics.lineStyle(0);
			graphics.beginFill(color);
			graphics.drawCircle(0, 0, radius);
			graphics.endFill();
		}
	}
}

