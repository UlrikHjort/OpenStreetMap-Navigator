/*******************************************************************************
 *                                                                             *
 *                            OpenstreetMap Viewer                             *
 *                                                                             *
 *                              MapView.java                                   *
 *                                                                             *
 *                                   Module                                    *
 *                                                                             *
 *                    Copyright (C) 2011 Ulrik H¿rlyk Hjort                    *
 *                                                                             *
 *   OpenstreetMap Viewer is free software;  you can  redistribute it          *
 *   and/or modify it under terms of the  GNU General Public License           *
 *   as published  by the Free Software  Foundation;  either version 2,        *
 *   or (at your option) any later version.                                    *
 *   OpenstreetMap Viewer is distributed in the hope that it will be           *
 *   useful, but WITHOUT ANY WARRANTY;  without even the  implied warranty     *
 *   of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                   *
 *   See the GNU General Public License for  more details.                     *
 *   You should have  received  a copy of the GNU General                      *
 *   Public License  distributed with Yolk.  If not, write  to  the  Free      *
 *   Software Foundation,  51  Franklin  Street,  Fifth  Floor, Boston,        *
 *   MA 02110 - 1301, USA.                                                     *
 *                                                                             *
 ******************************************************************************/

package com.nav.osm.mapview;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JApplet;

public class MapView extends JApplet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7053724806124461775L;
	private PngTile pngTile = null;
	
	

	public static int imageHeight = 0;
	public static int imageWitdh = 0;

	
	public MapView(PngTile pngTile) {
		this.pngTile = pngTile;
	}
	
	public void init()
	{
		setBackground(Color.white);
		setForeground(Color.white);
	}

	public void paint(Graphics g)
	{
		Graphics2D g_local = (Graphics2D) g;
		g_local.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g_local.setPaint(Color.gray);
		int x = 5;
		int y = 7;

		//PngTile pngTile = new PngTile();
		
		//pngTile.readImage("./images/map_small.png");
		
		g_local.drawImage(pngTile.image, 0, 0, null);
		g_local.draw(new Line2D.Double(x, y, 200, 200));
		g_local.drawString("MapView", x, 250);

	}
	/*
	 * public static void main(String s[]) { JFrame f = new
	 * JFrame("ShapesDemo2D"); f.addWindowListener(new WindowAdapter() {
	 * public void windowClosing(WindowEvent e) { System.exit(0); } });
	 * JApplet applet = new LineDemo2D(); f.getContentPane().add("Center",
	 * applet); applet.init(); f.pack(); f.setSize(new Dimension(300, 300));
	 * f.show(); }
	 */
}
