package server.control;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MyControlPanel extends JPanel {
	BufferedImage imag;

	public MyControlPanel(String str) throws MalformedURLException {
		// add components

		try {
			imag = ImageIO.read(this.getClass().getResource(str));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public double getScaleFactor(int iMasterSize, int iTargetSize) {

		double dScale = 1;
		if (iMasterSize > iTargetSize) {

			dScale = (double) iTargetSize / (double) iMasterSize;

		} else {

			dScale = (double) iTargetSize / (double) iMasterSize;

		}

		return dScale;

	}

	public double getScaleFactorToFit(Dimension original, Dimension toFit) {
		double dScale = 1d;
		if (original != null && toFit != null) {

			double dScaleWidth = getScaleFactor(original.width, toFit.width);
			double dScaleHeight = getScaleFactor(original.height, toFit.height);

			dScale = Math.min(dScaleHeight, dScaleWidth);

		}

		return dScale;

	}

	public double getScaleFactorToFill(Dimension masterSize, Dimension targetSize) {

		double dScaleWidth = getScaleFactor(masterSize.width, targetSize.width);
		double dScaleHeight = getScaleFactor(masterSize.height, targetSize.height);

		double dScale = Math.max(dScaleHeight, dScaleWidth);

		return dScale;

	}

	/*
	 * @Override protected void paintComponent(Graphics g){ super.paintComponent(g);
	 * double scaleFactor = Math.min(1d, getScaleFactorToFill(new
	 * Dimension(imag.getWidth(null), imag.getHeight(null)), getSize()));
	 * 
	 * int scaleWidth = (int) Math.round(imag.getWidth(null) * scaleFactor); int
	 * scaleHeight = (int) Math.round(imag.getHeight(null) * scaleFactor);
	 * 
	 * Image scaled = imag.getScaledInstance(scaleWidth, scaleHeight,
	 * Image.SCALE_SMOOTH);
	 * 
	 * int width = getWidth()-1; int height = getHeight()-1;
	 * 
	 * int x = (width - scaled.getWidth(this)) / 2; int y = (height -
	 * scaled.getHeight(this)) / 2; g.drawImage(scaled,x,y,scaleWidth,scaleHeight,
	 * this); }
	 */
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g);

		double scaleFactor = Math.min(1d,
				getScaleFactorToFill(new Dimension(imag.getWidth(null), imag.getHeight(null)), getSize()));

		int scaleWidth = (int) Math.round(imag.getWidth(null) * scaleFactor);
		int scaleHeight = (int) Math.round(imag.getHeight(null) * scaleFactor);

		// Image scaled = imag.getScaledInstance(scaleWidth, scaleHeight,
		// Image.SCALE_SMOOTH);

		int width = getWidth() - 1;
		int height = getHeight() - 1;

		int x = (width - scaleWidth) / 2;
		int y = (height - scaleHeight) / 2;

		g2d.drawImage(imag, x, y, scaleWidth, scaleHeight, this);

	}

}
