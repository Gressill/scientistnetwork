package iil.flex.component
{
	import flash.display.Sprite;

	public class Arrow extends Sprite
	{
		public function Arrow()
		{
			
			var vertices:Vector.<Number> = new Vector.<Number>();
			vertices.push(10,10);
			vertices.push(18,15);
			vertices.push(10, 20);
			
			this.graphics.beginFill(0xffffff);
			this.graphics.drawTriangles(vertices);
			this.graphics.drawRect(4,12,6,6);
			this.graphics.endFill();
		}
	}
}