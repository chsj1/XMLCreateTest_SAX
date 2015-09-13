package com.example.xmlcreatetest_sax;

import java.io.OutputStream;
import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

import android.util.Log;

public class XMLCreateBySAXUtils {
	/**
	 * SAX方式生成XML
	 * @param list
	 * @return
	 */
	public static String saxToXml(OutputStream output) {
		String xmlStr = null;
		try {
			// 用来生成XML文件
			// 实现此接口的对象包含构建转换结果树所需的信息
			Result resultXml = new StreamResult(output);
			// 用来得到XML字符串形式
			// 一个字符流，可以用其回收在字符串缓冲区中的输出来构造字符串
			StringWriter writerStr = new StringWriter();
			// 构建转换结果树所需的信息。
			Result resultStr = new StreamResult(writerStr);
			// 创建SAX转换工厂
			SAXTransformerFactory sff = (SAXTransformerFactory) SAXTransformerFactory
					.newInstance();
			// 转换处理器，侦听 SAX ContentHandler
			// 解析事件，并将它们转换为结果树 Result
			TransformerHandler th = sff.newTransformerHandler();
			// 将源树转换为结果树
			Transformer transformer = th.getTransformer();
			// 设置字符编码
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			// 是否缩进
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			// 设置与用于转换的此 TransformerHandler 关联的 Result
			// 注：这两个th.setResult不能同时启用
			 th.setResult(resultXml);
//			 th.setResult(resultStr);
			//创建根元素<calsses>,并设置其属性为空
			th.startDocument();
			AttributesImpl attr = new AttributesImpl();
			th.startElement("", "classes", "classes", attr);
			//创建一级子元素<group>,并设置其属性
			attr.clear();
			attr.addAttribute("","name", "name", "", "一年级");
			attr.addAttribute("","num", "num", "", "10");
			th.startElement("", "", "group", attr);
			//创建二级子元素<person>,并设置其属性
			attr.clear();
			attr.addAttribute("","name", "name", "", "小明");
			attr.addAttribute("","age", "age", "", "7");
			th.startElement("", "", "person", attr);
			//创建三级子元素<chinese>,并设置其值
			attr.clear();
			th.startElement("", "", "chinese", attr);
			th.characters("chinises 90".toCharArray(), 0, "chinese 90".length());
			th.endElement("", "", "chinese");
			//创建三级子元素<english>,并设置其值
			th.startElement("", "", "music", attr);
			th.characters("music 80".toCharArray(), 0, "music 80".length());
			th.endElement("", "", "music");
			th.endElement("", "", "person");
			th.endElement("", "", "group");
			th.endElement("", "classes", "classes");
			th.endDocument();
			xmlStr = writerStr.getBuffer().toString();
		} catch (TransformerConfigurationException e) {
			Log.e("TEST", ""+e.toString());
		} catch (SAXException e) {
			Log.e("TEST", ""+e.toString());
		} catch (Exception e) {
			Log.e("TEST", ""+e.toString());
		}
		Log.e("TEST","生成的"+xmlStr);
		return xmlStr;
	}
}
