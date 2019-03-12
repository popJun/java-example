/*
package com.popjun.word.demo.WordUtils;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class WordUtils {
    // word文档
    private static Dispatch doc;
    // word运行程序对象
    private  static ActiveXComponent word;
    // 所有word文档集合
    private static Dispatch documents;
    // 选定的范围或插入点
    private static Dispatch selection;
    private static boolean saveOnExit = true;


    */
/**
     * 创建目录
     *//*

    public void createCatalog() {
        Dispatch activeDocument = Dispatch.get(word, "ActiveDocument").toDispatch();

        Dispatch.call(selection, "HomeKey", new Variant(6)); // 将光标移到文件首的位置
        Dispatch.call(selection, "TypeParagraph");// 插入一个空行
        moveUp(1);

        Dispatch.put(selection, "Text", "目录");
        Dispatch style = Dispatch.call(activeDocument, "Styles", new Variant(-2)).toDispatch();;
        Dispatch.put(selection, "Style", style);
        Dispatch alignment = Dispatch.get(selection, "ParagraphFormat").toDispatch();// 段落格式
        Dispatch.put(alignment, "Alignment", "1"); // (1:置中 2:靠右 3:靠左)
        moveRight(1);
        Dispatch.call(selection, "TypeParagraph");// 插入一个空行

        Dispatch myRange = Dispatch.call(selection, "Range").toDispatch();

        */
/** 获取目录 *//*

        Dispatch tablesOfContents = Dispatch.get(activeDocument, "TablesOfContents").toDispatch();

        Dispatch add = Dispatch.call(tablesOfContents, "Add", myRange, new Variant(true),
                new Variant(1), new Variant(3), new Variant(true), new Variant(true), new Variant('T'),
                new Variant(true), new Variant(true), new Variant(1), new Variant(true)).toDispatch();

//        Dispatch.put(add, "RightAlignPageNumbers", new Variant(true));
//        Dispatch.put(add, "UseHeadingStyles", new Variant(true));
//        Dispatch.put(add, "UpperHeadingLevel", new Variant(1));
//        Dispatch.put(add, "LowerHeadingLevel", new Variant(3));
//        Dispatch.put(add, "IncludePageNumbers", new Variant(true));
//        Dispatch.put(add, "UseHyperlinks", new Variant(true));
//        Dispatch.put(add, "HidePageNumbersInWeb", new Variant(true));

//        Dispatch.call(add, "Add", myRange);

        //插入一个分页符
        Dispatch.call(selection, "InsertBreak", new Variant(7));
        Dispatch.call(selection, "TypeBackspace");
    }


    */
/**
     * 把选定的内容或者插入点向右移动
     *
     * @param pos
     *            移动的距离
     *//*

    public void moveRight(int pos) {
        if (selection == null)
            selection = Dispatch.get(word, "Selection").toDispatch();
        for (int i = 0; i < pos; i++)
            Dispatch.call(selection, "MoveRight");
    }
    */
/**
     * 把选定的内容或插入点向上移动
     *
     * @param pos
     *            移动的距离
     *//*

    public void moveUp(int pos) {
        if (selection == null)
            selection = Dispatch.get(word, "Selection").toDispatch();
        for (int i = 0; i < pos; i++)
            Dispatch.call(selection, "MoveUp");
    }

    */
/**
     * 关闭全部应用
     *//*

    public static void close() {
        closeDocument();
        if (word != null) {
            Dispatch.call(word, "Quit");
            word = null;
        }
        selection = null;
        documents = null;
    }
    */
/**
     * 关闭当前word文档
     *//*

    public static void closeDocument() {
        if (doc != null) {
            Dispatch.call(doc, "Save");
            Dispatch.call(doc, "Close", new Variant(saveOnExit));
            doc = null;
        }

    }
}
*/
