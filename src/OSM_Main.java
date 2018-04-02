/*******************************************************************************
 *                                                                             *
 *                            OpenstreetMap Viewer                             *
 *                                                                             *
 *                              OSM_Main.java                                  *
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
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JApplet;
import javax.swing.JFrame;

import com.nav.osm.mapview.MapView;
import com.nav.osm.mapview.PngTile;
import com.nav.osm.model.OSMNode;
import com.nav.osm.model.Osm;
import com.nav.osm.parser.OsmParser;

public class OSM_Main
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{

		Osm osm = null;

		 PngTile pngTile = new PngTile("./images/map_small.png");

		try {
			//osm = OsmParser.parse("./data/map_test_small.osm");
			osm = OsmParser.parse("./data/berlin_small.osm");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error");
			e.printStackTrace();
		}
		
		System.out.println("GET WAY: " + osm.getWay("Magnus-Hirschfeld-Ufer"));
		
		System.out.println("Ok");
		System.out.println("DDD: "
				+ OSMNode.DMStoDEC(50.0, 03.0, 59.0,
						OSMNode.Suffix.N));
		System.out.println("DDD: "
				+ OSMNode.DMStoDEC(5.0, 42.0, 53.0,
						OSMNode.Suffix.E));
		System.out.println("45.3772 -> (45¡22'38) "
				+ OSMNode.DECtoDMS(45.3772));

		osm.printSettings();

		JFrame f = new JFrame("Map");
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
//		PngTile pngTile = new PngTile(17, 55.615679d, 12.980815d,
//				"tile.openstreetmap.org");

		JApplet applet = new MapView(pngTile);
		f.getContentPane().add("Center", applet);
		applet.init();
		f.pack();
		f.setSize(new Dimension(PngTile.imageWitdh, PngTile.imageWitdh));
		f.show();

	}

}
