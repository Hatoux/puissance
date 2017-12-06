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

			PatriciaTrie pt = hy.toPatriciaTrie();
			PatriciaTrie pt1 = new PatriciaTrie();
			for(String s : listeMots)
				pt1.addWord(s);
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