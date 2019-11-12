package com.jayden.sqlSession;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;

/**
 * Author: Jayden
 * Date: 2019-11-11 17:07
 * Content:
 */
public class DocumentReader {

    /**
     *  创建一个document对象
     * @param inputStream
     * @return
     */
    public static Document createDocument(InputStream inputStream){
        Document document = null;
        try {
            SAXReader saxReader = new SAXReader();
            document = saxReader.read(inputStream);
            return document;
        }catch (DocumentException e){
            e.printStackTrace();
        }
        return null;
    }

}
