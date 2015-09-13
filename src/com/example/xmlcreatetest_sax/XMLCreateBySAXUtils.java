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
	 * SAX��ʽ����XML
	 * @param list
	 * @return
	 */
	public static String saxToXml(OutputStream output) {
		String xmlStr = null;
		try {
			// ��������XML�ļ�
			// ʵ�ִ˽ӿڵĶ����������ת��������������Ϣ
			Result resultXml = new StreamResult(output);
			// �����õ�XML�ַ�����ʽ
			// һ���ַ�������������������ַ����������е�����������ַ���
			StringWriter writerStr = new StringWriter();
			// ����ת��������������Ϣ��
			Result resultStr = new StreamResult(writerStr);
			// ����SAXת������
			SAXTransformerFactory sff = (SAXTransformerFactory) SAXTransformerFactory
					.newInstance();
			// ת�������������� SAX ContentHandler
			// �����¼�����������ת��Ϊ����� Result
			TransformerHandler th = sff.newTransformerHandler();
			// ��Դ��ת��Ϊ�����
			Transformer transformer = th.getTransformer();
			// �����ַ�����
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			// �Ƿ�����
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			// ����������ת���Ĵ� TransformerHandler ������ Result
			// ע��������th.setResult����ͬʱ����
			 th.setResult(resultXml);
//			 th.setResult(resultStr);
			//������Ԫ��<calsses>,������������Ϊ��
			th.startDocument();
			AttributesImpl attr = new AttributesImpl();
			th.startElement("", "classes", "classes", attr);
			//����һ����Ԫ��<group>,������������
			attr.clear();
			attr.addAttribute("","name", "name", "", "һ�꼶");
			attr.addAttribute("","num", "num", "", "10");
			th.startElement("", "", "group", attr);
			//����������Ԫ��<person>,������������
			attr.clear();
			attr.addAttribute("","name", "name", "", "С��");
			attr.addAttribute("","age", "age", "", "7");
			th.startElement("", "", "person", attr);
			//����������Ԫ��<chinese>,��������ֵ
			attr.clear();
			th.startElement("", "", "chinese", attr);
			th.characters("chinises 90".toCharArray(), 0, "chinese 90".length());
			th.endElement("", "", "chinese");
			//����������Ԫ��<english>,��������ֵ
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
		Log.e("TEST","���ɵ�"+xmlStr);
		return xmlStr;
	}
}
