import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintStream;

import javax.imageio.ImageIO;

public class DrawMain {

	public static void main(String[] args) throws Exception {
		int x = 0;
		DrawMain rc = new DrawMain();
		x = rc.getScreenPixel(800, 800);
		System.out.println(x + " - ");
		String content = rc.getImagePixel("/Users/super_yu/Desktop/map6.bmp");
		System.out.println("");
		rc.writeToFile(content);
	}

	public void writeToFile(String content) {
		try {
			// 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw
			File writename = new File("/Users/super_yu/Desktop/t.txt");
			writename.createNewFile(); // 创建新文件

			 PrintStream ps = new PrintStream(new
			 FileOutputStream(writename));
			 ps.write(content.getBytes());
//
//			BufferedWriter out = new BufferedWriter(new FileWriter(writename));
//			out.write(content);
//			out.flush(); // 把缓存区内容压入文件
//			out.close(); // 最后记得关闭文件
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 读取一张图片的RGB值
	 * 
	 * @throws Exception
	 */
	public String getImagePixel(String image) throws Exception {
		System.out.println("***********");
		int[] rgb = new int[3];
		File file = new File(image);
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int width = bi.getWidth();
		int height = bi.getHeight();
		int minx = bi.getMinX();
		int miny = bi.getMinY();
		System.out.println("width=" + width + ",height=" + height + ".");
		System.out.println("minx=" + minx + ",miniy=" + miny + ".");
		int m = 0;
		int n = 0;
		int l = 0;
		int a = 0;
		StringBuffer b = new StringBuffer();
		for (int i = 0; i < 800; i++) {
			for (int j = 0; j < 800; j++) {
				int pixel = bi.getRGB(i, j); // 下面三行代码将一个数字转换为RGB数字
				rgb[0] = (pixel & 0xff0000) >> 16;
				rgb[1] = (pixel & 0xff00) >> 8;
				rgb[2] = (pixel & 0xff);
				// System.out.println("i=" + i + ",j=" + j + ":(" + rgb[0] + ","
				// + rgb[1] + "," + rgb[2] + ")");
				// 蓝色 0 0 255 -1
				if (rgb[0] == 0 && rgb[1] == 0 && rgb[2] == 255) {
					b.append("-1,");
					System.out.println("m = " + ++m);
					continue;
				}
				// 红色 255 255 255 100
				if (rgb[0] == 255 && rgb[1] == 0 && rgb[2] == 0) {
					b.append("100,");
					System.out.println("m = " + ++n);
					continue;
				}
				// 白色 255 0 0 0
				if (rgb[0] == 255 && rgb[1] == 255 && rgb[2] == 255) {
					b.append("0,");
					System.out.println("m = " + ++l);
					continue;
				}
				b.append("-1,");
				System.out.println("m = " + ++a);
			}
		}
		System.out.println("m = " + m);
		System.out.println("n = " + n);
		System.out.println("l = " + l);	
		System.out.println("a = " + a);	
		return b.toString();
	}

	/**
	 * 返回屏幕色彩值
	 * 
	 * @param x
	 * @param y
	 * @return
	 * @throws AWTException
	 */
	public int getScreenPixel(int x, int y) throws AWTException { // 函数返回值为颜色的RGB值。
		Robot rb = null; // java.awt.image包中的类，可以用来抓取屏幕，即截屏。
		rb = new Robot();
		Toolkit tk = Toolkit.getDefaultToolkit(); // 获取缺省工具包
		Dimension di = tk.getScreenSize(); // 屏幕尺寸规格
		System.out.println(di.width);
		System.out.println(di.height);
		Rectangle rec = new Rectangle(0, 0, di.width, di.height);
		BufferedImage bi = rb.createScreenCapture(rec);
		int pixelColor = bi.getRGB(x, y);

		return 16777216 + pixelColor; // pixelColor的值为负，经过实践得出：加上颜色最大值就是实际颜色值。
	}
}