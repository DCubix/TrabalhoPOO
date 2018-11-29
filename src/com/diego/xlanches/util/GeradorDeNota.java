package com.diego.xlanches.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GeradorDeNota {
	public static final int WIDTH = 40;
	private ArrayList<String> lines;
	
	public GeradorDeNota() {
		this.lines = new ArrayList<>();
	}
	
	public void println(String fmt, Object... args) {
		this.lines.add(String.format(fmt, args));
	}
	
	public void separador() {
		println(IntStream.range(0, GeradorDeNota.WIDTH).mapToObj(i -> "-").collect(Collectors.joining()));
	}
	
	public BufferedImage gerar() {	
		final Font font = new Font("Monospaced", Font.PLAIN, 16);
		
		BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = img.createGraphics();
		g.setFont(font);
		final int charWidth = g.getFontMetrics(font).charWidth('A');
		final int charHeight = g.getFontMetrics(font).getHeight();
		g.dispose();
		
		img = new BufferedImage(charWidth * WIDTH + 100, lines.size() * charHeight + 100, BufferedImage.TYPE_INT_RGB);
		g = img.createGraphics();
		
		g.setFont(font);
		g.setColor(new Color(0xf7eca8));
		g.fillRect(0, 0, img.getWidth(), img.getHeight());
		
		g.setColor(Color.BLACK);
		int y = 50;
		for (String line : lines) {
			g.drawString(line, 50, y);
			y += charHeight;
		}
		
		g.dispose();
			return img;
	}
	
	public static String centerString(int width, String s) {
		return String.format("%-" + width  + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
	}
}
