package patricia;

import hybride.Hybride;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import exceptions.BadArgumentException;
import static org.junit.Assert.*;
public class Test2 {

	final String samplesDirName = "Shakespeare"; 
	List<String> listeMots2;
	List<String> listeMots1;
	List<File> listeFichiers;

	public List<File> getFileList() throws Exception {
		List<File> files = new ArrayList<File>();
		File samplesDir = new File(samplesDirName);
		for (File f : samplesDir.listFiles()) 
			files.add(f);
		return files;
	}

	public void readFile(List<String>ls,File f){
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


	public void init1() throws Exception {
		listeMots1=new ArrayList<>();
		listeFichiers=getFileList();

		for(File f : listeFichiers)
			readFile(listeMots1,f);

	}

	public void init2() throws Exception {
		listeMots2=new ArrayList<>();
		listeMots1=new ArrayList<>();
		listeFichiers=getFileList();
		int nbFiles=listeFichiers.size();
		int i=0;
		for(File f : listeFichiers) {
			if(i>=nbFiles/2)
				readFile(listeMots1,f);
			else
				readFile(listeMots2,f);
			i++;
		}
	}

	@Test
	public  void test1() {

		try {
			init1();
			System.out.println(listeMots1.size());
			PatriciaTrie pt = new PatriciaTrie();
			Hybride hy = new Hybride();
			for(String s : listeMots1){
				pt.addWord(s);
				hy.add(s);
			}
			
			for(String s : listeMots1)
				if(!pt.recherche(s)) 
					fail("probleme 1: "+s);
			Set<String> s = new HashSet<>(listeMots1);
			System.out.println(s.size());
			System.out.println(pt.comptageMot());
			System.out.println(hy.comptageMot());
		
			
		} catch (BadArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
//
////	@Test
//	public  void test2() {
//		
//		try {
//			init2();
//			PatriciaTrie pt1 = new PatriciaTrie();
//			PatriciaTrie pt2 = new PatriciaTrie();
//			PatriciaTrie pt3;
//			for(String s : listeMots1)
//				pt1.addWord(s);
//
//			for(String s : listeMots1)
//				if(!pt1.recherche(s)) 
//					fail("probleme 1: "+s);
//			
//			for(String s : listeMots2)
//				pt2.addWord(s);
//
//			for(String s : listeMots2)
//				if(!pt2.recherche(s)) 
//					fail("probleme 2: "+s);
//			
//			pt3=pt1.clone();
//			
//			for(String s : listeMots1)
//				if(!pt3.recherche(s)) 
//					fail("probleme 1bis: "+s);
//			
//			pt3=pt2.clone();
//			
//			for(String s : listeMots2)
//				if(!pt3.recherche(s)) 
//					fail("probleme 2bis: "+s);
//			
//			pt3=pt1.fusion(pt2);
//			
//			for(String s : listeMots1)
//				if(!pt3.recherche(s)) 
//					fail("probleme 3: "+s);
//
//			for(String s : listeMots2)
//				if(!pt3.recherche(s)) 
//					fail("probleme 4: "+s);
//			
//			
//		} catch (BadArgumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
////	@Test
//	public  void test3() {
//
//		try {
//			init1();
//			PatriciaTrie pt = new PatriciaTrie();
//			for(String s : listeMots1)
//				pt.addWord(s);
//
//			pt.listeMot();
//					
//		} catch (BadArgumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//	@Test
//	public  void test4() {
//
//		try {
//			init1();
//			PatriciaTrie pt = new PatriciaTrie();
//			for(String s : listeMots1)
//				pt.addWord(s);
//
//			pt.listeMot2();
//					
//		} catch (BadArgumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//
	@Test
	public void test(){
		ArrayList<String> l = new ArrayList<String>();
		readFile(l,new File("test/exemple_de_base.txt"));
		Set<String> s = new HashSet<>(l);
		System.out.println(s.size());
		PatriciaTrie pt = new PatriciaTrie();
		for(String st : s)
			try {
				pt.addWord(st);
			} catch (BadArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		System.out.println(pt.comptageMot());
		
		
				
	}

}
