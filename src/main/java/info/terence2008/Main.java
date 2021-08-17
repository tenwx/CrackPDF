package info.terence2008;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class Main {
    public static void main(String[] args) throws Exception {
        if(args.length==0)
            throw new Exception("need file path to crack!");
        File file=new File(args[0]);
        if(!file.isFile())
            throw new Exception("file not found!");
        int i=file.getName().lastIndexOf('.');
        if(i<0)
            throw new Exception("file extension not found!");
        String extension=file.getName().substring(i+1);
        if(!extension.equalsIgnoreCase("pdf"))
            throw new Exception("file extension not pdf!");
        String fileName=file.getName().substring(0, i);
        PdfReader.unethicalreading = true;
        File newFile=new File(file.getParent(), fileName+"_cracked.pdf");
        MyReader reader = new MyReader(args[0]);
        FileOutputStream os = new FileOutputStream(newFile);
        PdfStamper s = new PdfStamper(reader, os);
        s.close();
        reader.close();
        System.out.println("crack success!");
    }
    
    static class MyReader extends PdfReader {
        public MyReader(String filename) throws IOException {
            super(filename);
        }
        public void decryptOnPurpose() {
            encrypted = false;
        }
    }
}