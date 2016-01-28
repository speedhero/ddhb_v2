package com.huatek.hbwebsite.util;

import com.octo.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.component.image.color.RandomListColorGenerator;
import com.octo.captcha.component.image.deformation.ImageDeformationByFilters;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.DecoratedRandomTextPaster;
import com.octo.captcha.component.image.textpaster.textdecorator.TextDecorator;
import com.octo.captcha.component.image.wordtoimage.DeformedComposedWordToImage;
import com.octo.captcha.component.word.FileDictionary;
import com.octo.captcha.component.word.wordgenerator.ComposeDictionaryWordGenerator;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.ImageFilter;

public class GMailEngine extends ListImageCaptchaEngine {
	protected void buildInitialFactories() {
		byte minWordLength = 4;
		byte maxWordLength = 5;
		byte fontSize = 50;
		short imageWidth = 250;
		byte imageHeight = 100;
		ComposeDictionaryWordGenerator dictionnaryWords = new ComposeDictionaryWordGenerator(new FileDictionary("toddlist"));
		DecoratedRandomTextPaster randomPaster = new DecoratedRandomTextPaster(Integer.valueOf(minWordLength),
				Integer.valueOf(maxWordLength), new RandomListColorGenerator(new Color[] { new Color(23, 170, 27),
						new Color(220, 34, 11), new Color(23, 67, 172) }), new TextDecorator[0]);
		UniColorBackgroundGenerator background = new UniColorBackgroundGenerator(Integer.valueOf(imageWidth),
				Integer.valueOf(imageHeight), Color.white);
		RandomFontGenerator font = new RandomFontGenerator(Integer.valueOf(fontSize), Integer.valueOf(fontSize),
				new Font[] { new Font("nyala", 1, fontSize), new Font("Bell MT", 0, fontSize),
						new Font("Credit valley", 1, fontSize) });
		ImageDeformationByFilters postDef = new ImageDeformationByFilters(new ImageFilter[0]);
		ImageDeformationByFilters backDef = new ImageDeformationByFilters(new ImageFilter[0]);
		ImageDeformationByFilters textDef = new ImageDeformationByFilters(new ImageFilter[0]);
		DeformedComposedWordToImage word2image = new DeformedComposedWordToImage(font, background, randomPaster, backDef,
				textDef, postDef);
		this.addFactory(new GimpyFactory(dictionnaryWords, word2image));
	}
}
