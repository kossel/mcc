package com.kossel.util;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.displaytag.export.BinaryExportView;
import org.displaytag.model.TableModel;

import javax.servlet.jsp.JspException;
import java.io.OutputStream;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.displaytag.Messages;
import org.displaytag.exception.BaseNestableJspTagException;
import org.displaytag.exception.SeverityEnum;
import org.displaytag.model.Column;
import org.displaytag.model.ColumnIterator;
import org.displaytag.model.HeaderCell;
import org.displaytag.model.Row;
import org.displaytag.model.RowIterator;
import org.displaytag.util.TagConstants;

/**
 * Created with IntelliJ IDEA.
 * User: Yichao
 * Date: 12/1/13
 * Time: 2:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class ContactPDFView implements BinaryExportView {

    /**
     * TableModel to render.
     */
    private TableModel model;

    /**
     * export full list?
     */
    private boolean exportFull;

    /**
     * include header in export?
     */
    private boolean header;

    /**
     * decorate export?
     */
    private boolean decorated;

    /**
     * This is the table, added as an Element to the PDF document. It contains all the data, needed to represent the
     * visible table into the PDF
     */
    private PdfPTable tablePDF;

    /**
     * The default font used in the document.
     */
    private Font smallFont;

    private Font dirFont;

    private String absolutePath;
    /**
     * @see org.displaytag.export.ExportView#setParameters(TableModel, boolean, boolean, boolean)
     */
    public void setParameters(TableModel tableModel, boolean exportFullList, boolean includeHeader,
                              boolean decorateValues)
    {
        this.model = tableModel;
        this.exportFull = exportFullList;
        this.header = includeHeader;
        this.decorated = decorateValues;
    }

    /**
     * Initialize the main info holder table.
     * @throws BadElementException for errors during table initialization
     */
    protected void initTable() throws Exception
    {
        tablePDF = new PdfPTable(this.model.getNumberOfColumns());
       // tablePDF.setDefaultVerticalAlignment(Element.ALIGN_TOP);
        tablePDF.setHeaderRows(11);
        tablePDF.setWidthPercentage(100);

      //  tablePDF.setSpacing(0);
        tablePDF.setSplitLate(false);
        tablePDF.setSplitRows(true);
        float[] columnWidths = new float[] {3f, 22f, 20f, 20f, 9f,19f,4f,9f};
        tablePDF.setWidths(columnWidths);
       // smallFont = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.NORMAL, new Color(0, 0, 0));
        dirFont= FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, new BaseColor(0, 0, 0));
        BaseFont bf = BaseFont.createFont("c:/windows/Fonts/msyh.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        smallFont = new Font(bf,8.5f);



    }

    /**
     * @see org.displaytag.export.BaseExportView#getMimeType()
     * @return "application/pdf"
     */
    public String getMimeType()
    {
        return "application/pdf"; //$NON-NLS-1$
    }

    /**
     * The overall PDF table generator.
     * @throws JspException for errors during value retrieving from the table model
     * @throws BadElementException IText exception
     */
    protected void generatePDFTable() throws JspException, BadElementException
    {
        if (this.header)
        {
            generateHeaders();
        }
       // tablePDF.endHeaders();
        tablePDF.setHeaderRows(1);
        generateRows();
    }

    /**
     * @see org.displaytag.export.BinaryExportView#doExport(OutputStream)
     */
    public void doExport(OutputStream out) throws JspException
    {
        try
        {
            // Initialize the table with the appropriate number of columns
            initTable();

            // Initialize the Document and register it with PdfWriter listener and the OutputStream
            Document document = new Document(PageSize.LETTER.rotate(), 30, 30, 20, 20);
            document.addCreationDate();

         //   HeaderFooter footer = new HeaderFooter(new Phrase(TagConstants.EMPTY_STRING, smallFont), true);


          //  footer.setBorder(Rectangle.NO_BORDER);
          //  footer.setAlignment(Element.ALIGN_CENTER);
            PdfWriter.getInstance(document, out);

            // Fill the virtual PDF table with the necessary data
            generatePDFTable();
            document.open();
            document=this.generateTitle(document);
           // document.setFooter(footer);
            document.add(this.tablePDF);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            Paragraph genDate = new Paragraph("Generates at: "+dateFormat.format(date),dirFont);
            genDate.setAlignment(Element.ALIGN_RIGHT);
            document.add(genDate);
            document.close();

        }
        catch (Exception e)
        {
            throw new PdfGenerationException(e);
        }
    }

    protected Document generateTitle(Document doc) throws Exception {
        String relativeWebPath = "src/main/webapp/images/cosllogo_small.jpg";
        // String relativeWebPath = "webapps/contacts/images/cosllogo_small.jpg";


        Image logo = Image.getInstance(relativeWebPath);

        logo.setAlignment(Image.ALIGN_LEFT);
        logo.scalePercent(50f);
        Paragraph title = new Paragraph(12);
        addEmptyLine(title,1);
        title.setSpacingBefore(-15);
        title.add(new Paragraph("Edificio de Oficinas TAKIN, Av. Isla de Tris # 28, Kilometro 5, Fraccionamiento San Miguel.", dirFont));

        title.add(new Paragraph("C.P. 24157, Cd. del Carmen, Campeche, México",dirFont));
        title.add(new Paragraph("Phone: +52 (938) 118 23 98  Fax:+52 (938) 131 4820",dirFont));
        title.add(new Paragraph("Chinese Speaker: expats@cosl.mx     Spanish Speaker: staffmx@cosl.mx", dirFont));
        doc.add(logo);
        doc.add(title);
        return doc;
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    /**
     * Generates the header cells, which persist on every page of the PDF document.
     * @throws BadElementException IText exception
     */
    protected void generateHeaders() throws BadElementException
    {
        Iterator iterator = this.model.getHeaderCellList().iterator();
         int colNum=0;
        while (iterator.hasNext())
        {
            HeaderCell headerCell = (HeaderCell) iterator.next();

            String columnHeader = headerCell.getTitle();

            if (columnHeader == null)
            {
                columnHeader = StringUtils.capitalize(headerCell.getBeanPropertyName());
            }
            PdfPCell hdrCell;
           // if(colNum==1){
            hdrCell = getCellCentered(columnHeader);
           // }
          //  hdrCell = getCell(columnHeader);
            hdrCell.setGrayFill(0.9f);
           // hdrCell.setHeader(true);
            tablePDF.addCell(hdrCell);
           colNum++;
        }
    }

    /**
     * Generates all the row cells.
     * @throws JspException for errors during value retrieving from the table model
     * @throws BadElementException errors while generating content
     */
    protected void generateRows() throws JspException, BadElementException
    {
        ArrayList<Integer> counts = this.countRowSpan();
        // get the correct iterator (full or partial list according to the exportFull field)
        RowIterator rowIterator = this.model.getRowIterator(this.exportFull);
        // iterator on rows
        String toMatch="";
        int rowspan=1;
        int rowBlock=1;
        int totalRowSpan=0;
        while (rowIterator.hasNext())
        {
            Row row = rowIterator.next();
               //8
            // iterator on columns
            ColumnIterator columnIterator = row.getColumnIterator(this.model.getHeaderCellList());

            int rowNum=0;
            while (columnIterator.hasNext())
            {

                Column column = columnIterator.nextColumn();
                Object value = column.getValue(this.decorated);
                String currentText = ObjectUtils.toString(value);
                PdfPCell cell;

                if(rowNum==1){
                    if(totalRowSpan<1){
                        totalRowSpan=counts.get(rowBlock-1);
                        rowBlock=rowBlock+1;
                        System.out.println(currentText);
                        String texts[]=currentText.split("<br/>");
                        System.out.println(texts[0]);
                        cell = getCellCentered(texts[0]+"\n"+texts[1]);
                        cell.setRowspan(totalRowSpan);

                        tablePDF.addCell(cell);

                    }

                }else{
                    if(rowNum==0||rowNum==4||rowNum==5||rowNum==6){
                        cell = getCellCentered(currentText);
                    }else{
                        cell = getCell(currentText);
                    }
                    tablePDF.addCell(cell);
                }

                rowNum++;
            }
            totalRowSpan=totalRowSpan-1;
        }

    }


    private ArrayList<Integer> countRowSpan()throws JspException, BadElementException{
        // get the correct iterator (full or partial list according to the exportFull field)
        RowIterator rowIterator = this.model.getRowIterator(this.exportFull);
        // iterator on rows
        String toMatch="";
        int rowspan=1;
        int curretBlock=0;
        ArrayList<Integer> counts = new ArrayList();
        while (rowIterator.hasNext())
        {
            Row row = rowIterator.next();
            ColumnIterator columnIterator = row.getColumnIterator(this.model.getHeaderCellList());
            int rowNum=0;
            while (columnIterator.hasNext())
            {
                Column column = columnIterator.nextColumn();
                Object value = column.getValue(this.decorated);
                String currentText = ObjectUtils.toString(value);
                PdfPCell cell=null;

                if(rowNum==1){
                    if(toMatch.compareToIgnoreCase("")==0){
                        toMatch=currentText;
                        counts.add(0,1);
                    }else{
                        if(currentText.compareToIgnoreCase(toMatch)==0){
                            if((counts.size()-1)<curretBlock) {
                                counts.add(curretBlock,1);
                            }
                            else{
                                counts.set(curretBlock,counts.get(curretBlock)+1);

                            }

                        }else{
                            curretBlock=curretBlock+1;
                            counts.add(curretBlock,1);
                            toMatch=currentText;

                        }
                    }
                }

                rowNum++;
            }
        }
        return counts;
    }


    /**
     * Returns a formatted cell for the given value.
     * @param value cell value
     * @return Cell
     * @throws BadElementException errors while generating content
     */
    private PdfPCell getCell(String value) throws BadElementException
    {
        PdfPCell cell = new PdfPCell(new Phrase(StringUtils.trimToEmpty(value), smallFont));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
       // cell.setLeading(8);
        cell.setPadding(2);
        return cell;
    }

    /**
     * Returns a formatted cell for the given value.
     * @param value cell value
     * @return Cell
     * @throws BadElementException errors while generating content
     */
    private PdfPCell getCellCentered(String value) throws BadElementException
    {
        PdfPCell cell = new PdfPCell(new Phrase(StringUtils.trimToEmpty(value), smallFont));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(2);
       // cell.setLeft(8);
        return cell;
    }


    /**
     * Wraps IText-generated exceptions.
     * @author Fabrizio Giustina
     * @version $Revision: 1081 $ ($Author: fgiust $)
     */
    static class PdfGenerationException extends BaseNestableJspTagException
    {

        /**
         * D1597A17A6.
         */
        private static final long serialVersionUID = 899149338534L;

        /**
         * Instantiate a new PdfGenerationException with a fixed message and the given cause.
         * @param cause Previous exception
         */
        public PdfGenerationException(Throwable cause)
        {
            super(ContactPDFView.class, Messages.getString("PdfView.errorexporting"), cause); //$NON-NLS-1$
        }

        /**
         * @see org.displaytag.exception.BaseNestableJspTagException#getSeverity()
         */
        public SeverityEnum getSeverity()
        {
            return SeverityEnum.ERROR;
        }
    }
}
