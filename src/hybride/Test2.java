package hybride;

import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;
import org.junit.Test;
import exceptions.BadArgumentException;
import patricia.PatriciaTrie;

public class Test2 {

	final static String samplesDirName = "Shakespeare"; 

	public static List<File> getFileList() throws Exception {
		List<File> files = new ArrayList<File>();
		File samplesDir = new File(samplesDirName);
		for (File f : samplesDir.listFiles()) 
			files.add(f);
		return files;
	}

	public static void readFile(List<String>ls,File f){
		try {
			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(new FileReader(f));
			String ligne;
			while((ligne = reader.readLine()) != null)
				ls.add(ligne);


		} catch (Exception ex){
			System.err.println("Error. "+ex.getMessage());
		}
	}
	
	@Test
	public  void test() {

		try {
			List<String> listeMots=new ArrayList<>();
			List<File> listeFichiers=new ArrayList<File>();

			listeFichiers=getFileList();

			for(File f : listeFichiers)
				readFile(listeMots,f);
			//		for(String s : listeMots)
			//			System.out.println(s);
//			for(File f : listeFichiers)
//				System.out.println(f.getName());
//			System.out.println("taille listeFichiers: "+listeFichiers.size());
//			System.out.println("taille listeMots: "+listeMots.size());
			Hybride pt = new Hybride();
			for(String s : listeMots)
				pt.add(s);

			for(String s : listeMots)
				if(!pt.rechercher(s)) {
					fail(s);
				}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
//		String s1 = "bonjour";
//		String s2 = "bonsoir";
//		String s3 = "bonne";
//		String s4 = "bon";
//		PatriciaTrie pt = new PatriciaTrie();
//		try {
//			pt.addWord(s4);
//			pt.addWord(s3);
//			pt.addWord(s2);
//			pt.addWord(s1);
//			System.out.println(pt.tabFils["b".codePointAt(0)].prefix);
//			System.out.println(pt.tabFils["b".codePointAt(0)].tabFils["_".codePointAt(0)].prefix);
//			System.out.println(pt.tabFils["b".codePointAt(0)].tabFils["j".codePointAt(0)].prefix);
//			System.out.println(pt.tabFils["b".codePointAt(0)].tabFils["n".codePointAt(0)].prefix);
//			System.out.println(pt.tabFils["b".codePointAt(0)].tabFils["s".codePointAt(0)].prefix);
//			System.out.println(pt);
//			pt.deleteWord(s4);
//			System.out.println(pt);
//		} catch (BadArgumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		public class readFile {
//		    public static void main(String[] args) {
//		        readFile("/home/laurent/Bureau/myfile.txt");
//		    }
//		 
//		    public static void readFile(String pathToFile){
//		        try {
//		            BufferedReader reader = new BufferedReader(new FileReader(new File(pathToFile)));
//		            String ligne;
//		            while((ligne = reader.readLine()) != null){
//		                if(ligne.startsWith("Tel :")){
//		                    System.out.println(ligne);
//		                }
//		            }
//		        } catch (Exception ex){
//		            System.err.println("Error. "+ex.getMessage());
//		        }
//		    }
//		}

	}
	@Test
	public  void test2() {

		try {
			List<String> listeMots=new ArrayList<>();
			List<File> listeFichiers=new ArrayList<File>();

			listeFichiers=getFileList();

			for(File f : listeFichiers)
				readFile(listeMots,f);
			//		for(String s : listeMots)
			//			System.out.println(s);
//			for(File f : listeFichiers)
//				System.out.println(f.getName());
//			System.out.println("taille listeFichiers: "+listeFichiers.size());
//			System.out.println("taille listeMots: "+listeMots.size());
			Hybride hy = new Hybride();
			for(String s : listeMots)
				hy.add(s);

			for(String s : listeMots)
				if(!hy.rechercher(s)) {
					fail(s);
				}
			PatriciaTrie pt = hy.toPatriciaTrie();
			for(String s : listeMots)
				if(!pt.recherche(s)) {
					fail("pt "+s);
				}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}