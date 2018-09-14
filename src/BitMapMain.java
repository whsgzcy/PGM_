import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BitMapMain {

	public static void main(String ds[]) {
		String[] a = null;
		try {
			String encoding = "UTF-8";
			File file = new File("/Users/super_yu/desktop/su.txt");
			// 判断文件是否存在
			if (file.isFile() && file.exists()) {
				// System.out.println("文件存在");
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = bufferedReader.readLine();
				// System.out.println(lineTxt);
				if (lineTxt != null && !lineTxt.equals("")) {
					a = lineTxt.split(",");
					read.close();
				}
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
		// 面板
		JPanel panel = new JPanel(new BorderLayout());
		BufferedImage image = createImage(panel, a);
		try {
			ImageIO.write(image, "bmp", new File("/Users/super_yu/desktop/map1.bmp"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static BufferedImage createImage(JPanel panel, String[] a) {
		// 1.在内存中创建图片
		int totalWidth = 800;
		int totalHeight = 800;
		BufferedImage panelImage = new BufferedImage(totalWidth, totalHeight, BufferedImage.TYPE_INT_RGB);
		// System.out.println(a.length);
		int n = 800;// 列
		int m = 800;// 行
		// 2.得到画笔
		Graphics2D g2D = (Graphics2D) panelImage.createGraphics();
		String[][] b = new String[m][n];
		int num = -1;
		for (int i = 0; i < 800; i++) {
			for (int j = 0; j < 800; j++) {
				num++;
				b[i][j] = a[num];
				if (Integer.parseInt(b[i][j]) == -1) {
					g2D.setColor(Color.BLUE);
					g2D.drawLine(i + 1, j + 1, i + 1, j + 1);
					panel.paint(g2D);
				} else if (Integer.parseInt(b[i][j]) == 100) {
					g2D.setColor(Color.red);
					g2D.drawLine(i + 1, j + 1, i + 1, j + 1);
					panel.paint(g2D);
				} else if (Integer.parseInt(b[i][j]) == 0) {
					g2D.setColor(Color.white);
					g2D.drawLine(i + 1, j + 1, i + 1, j + 1);
					panel.paint(g2D);
				}
			}
		}
		g2D.dispose();
		return panelImage;
	}

}
