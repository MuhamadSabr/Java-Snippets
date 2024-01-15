package com.mmd.randomaccessfile;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BuildStudentData {
	public static void build(String datFileName, boolean separateIndex){//The prefix of the file name
		Path studentJsonPath = Path.of("students-backup.json");
		String dataFile = datFileName + ".dat";
		Map<Long, Long> indexedIds = new LinkedHashMap();//Again key is record id, n value is its position in the file.

		try{
			Files.deleteIfExists(Path.of(dataFile));
			String data = Files.readString(studentJsonPath);
			data = data.replaceFirst("^\\[", ""). replaceFirst("]$", "");
			String[] records = data.split("\n");
			System.out.println(records.length);

			long startingPosition = separateIndex ? 0 : 4 + (16L*records.length); //4 bytes int for the recordCounter. 16 bytes for 2 Long of recordId n startingPosition = index. * number of records u have
			Pattern idPattern = Pattern.compile("studentId\":([0-9]+)");

			try(RandomAccessFile raf = new RandomAccessFile(dataFile, "rw")){
				raf.seek(startingPosition);
				for(String record : records){
					Matcher m = idPattern.matcher(record);
					if(m.find()){
						Long id = Long.parseLong(m.group(1));
						indexedIds.put(id, raf.getFilePointer());
						raf.writeUTF(record);//Writes the arg passed to it n advances the file pointer automatically by the length of the arg u passed.
					}//Plus it includes the length of the data written, so that later readUTF can read it based on this length.
				}
				writeIndex( ( separateIndex? new RandomAccessFile(datFileName+".idx", "rw") :  raf),
						indexedIds);
			}
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	private static void writeIndex(RandomAccessFile raf, Map<Long, Long> indexedId) {//The RAF to be written to n the indexedId map as arguments.
		try {
			raf.seek(0);//Make sure u get back the pointer to 0, since there is a chance it's not at 0 n's been moved
			raf.writeInt(indexedId.size());
			for(var map : indexedId.entrySet()){
				raf.writeLong(map.getKey());
				raf.writeLong(map.getValue());
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}


	}

}
