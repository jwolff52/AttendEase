///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package attendease.util;
//
//import java.io.BufferedInputStream;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.Enumeration;
//import java.util.Hashtable;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipFile;
//import java.util.zip.ZipInputStream;
//
///**
// *
// * @author james.wolff
// */
//public final class JarResources {
//   public boolean debugOn=false;
//   private Hashtable htSizes=new Hashtable();  
//   private Hashtable htJarContents=new Hashtable();
//   private String jarFileName;
//   
//   public JarResources(String jarFileName) {
//      this.jarFileName=jarFileName;
//      init();
//   }
//   
//   private void init() {
//      try {
//          ZipFile zf=new ZipFile(jarFileName);
//          Enumeration e=zf.entries();
//          while (e.hasMoreElements()) {
//              ZipEntry ze=(ZipEntry)e.nextElement();
//              if (debugOn) {
//                 System.out.println(dumpZipEntry(ze));
//              }
//              htSizes.put(ze.getName(),new Integer((int)ze.getSize()));
//          }
//          zf.close();
//          FileInputStream fis=new FileInputStream(jarFileName);
//          BufferedInputStream bis=new BufferedInputStream(fis);
//          ZipInputStream zis=new ZipInputStream(bis);
//          ZipEntry ze=null;
//          while ((ze=zis.getNextEntry())!=null) {
//             if (ze.isDirectory()) {
//                continue;
//             }
//             if (debugOn) {
//                System.out.println(
//                   "ze.getName()="+ze.getName()+","+"getSize()="+ze.getSize()
//                   );
//             }
//             int size=(int)ze.getSize();
//             if (size==-1) {
//                size=((Integer)htSizes.get(ze.getName())).intValue();
//             }
//             byte[] b=new byte[(int)size];
//             int rb=0;
//             int chunk=0;
//             while (((int)size - rb) > 0) {
//                 chunk=zis.read(b,rb,(int)size - rb);
//                 if (chunk==-1) {
//                    break;
//                 }
//                 rb+=chunk;
//             }
//             htJarContents.put(ze.getName(),b);
//             if (debugOn) {
//                System.out.println(
//                   ze.getName()+"  rb="+rb+
//                   ",size="+size+
//                   ",csize="+ze.getCompressedSize()
//                   );
//             }
//          }
//       } catch (NullPointerException e) {
//          System.out.println("done.");
//       } catch (FileNotFoundException e) {
//          e.printStackTrace();
//       } catch (IOException e) {
//          e.printStackTrace();
//       }
//   }
//   
//   public static void main(String[] args) throws IOException {
//       if (args.length!=2) {
//          System.err.println(
//             "usage: java JarResources <jar file name> <resource name>"
//             );
//          System.exit(1);
//       }
//       JarResources jr=new JarResources(args[0]);
//       byte[] buff=jr.getResource(args[1]);
//       if (buff==null) {
//          System.out.println("Could not find "+args[1]+".");
//       } else {
//          System.out.println("Found "+args[1]+ " (length="+buff.length+").");
//       }
//   }
//}
