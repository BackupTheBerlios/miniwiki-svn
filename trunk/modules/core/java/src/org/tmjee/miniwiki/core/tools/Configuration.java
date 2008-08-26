package org.tmjee.miniwiki.core.tools;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class Configuration {

    private int firstPage = -1;
    private int maxPage = -1;

    public int getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    public String print() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("firstPage="+firstPage);
        stringBuffer.append("\n");
        stringBuffer.append("maxPage="+maxPage);
        return stringBuffer.toString();
    }
}
