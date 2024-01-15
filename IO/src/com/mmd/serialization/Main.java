package com.mmd.serialization;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

class Player implements Serializable{//If u omit implements Serializable, U will face NotSerializableException
	private static final int version = 2;
	@Serial //U can use @Serial to annotate the serialVersionUID is what it is.
	private static final long serialVersionUID = 1L;
	private String name;
	private long bigScore;
	private long topScore;//Even though u made it transient, it does not matter since this is not default serialization
	private List<String> collectedWeapons;			//But instead u wrote ur own writeObject n readObject
	private final transient long accountId;

	public Player(long accountId, String name, int topScore, List<String> collectedWeapons) {
		this.name = name;
		this.topScore = topScore;
		this.collectedWeapons = new ArrayList<>(collectedWeapons);
		this.accountId = accountId;
	}

	@Serial
	private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
//		stream.defaultReadObject();//This is calling the default serialization reading mechanism.
//		bigScore = (bigScore==0 ? 1_000_000_000L : bigScore);

		int serializedVersion = stream.readInt();
		collectedWeapons = (List<String>) stream.readObject();
		name = stream.readUTF();
		topScore = (serializedVersion==1) ? stream.readInt() : stream.readLong();
	}

	@Serial//When u create ur own writeObject, u can't simply delegate the readObject to stream.defaultReadObject. Instead, again u
	private void writeObject(ObjectOutputStream stream) throws IOException{//have to read it in the same order as u wrote them
//		stream.defaultWriteObject();//This calls the default serialization writing mechanism.
		System.out.println("--> Customized writing");
		stream.writeInt(version);
		stream.writeObject(collectedWeapons);
		stream.writeUTF(name);
		stream.writeLong(topScore);
	}

	@Override
	public String toString() {
		return "Player{" +
				"name='" + name + '\'' +
				", topScore=" + topScore +
				", collectedWeapons=" + collectedWeapons +
				", accountId=" + accountId +
				'}';
	}
}

public class Main {
	public static void main(String[] args) {

//		Path dataOutInStreamPath = Path.of("dataOutInStream.dat");
//		try {
//			readData(dataOutInStreamPath);
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}

		Player mohammed = new Player(333,"Mohammed", 1_000_100, List.of("Knife", "Pistol", "Machete") );
		Path mohammedObjPath = Path.of("mohammedObjState.dat");
//		writeObject(mohammedObjPath, mohammed);
		try {
			Player deserializedMohammedObjState = readObject(mohammedObjPath);
			System.out.println(mohammed);
			System.out.println(deserializedMohammedObjState);
		} catch (IOException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}System.out.println("-".repeat(30));


		Player ahmed = new Player(555, "Ahmed", 75, List.of("knife, rifle"));
		Path ahmedObjStatePath = Path.of("ahmedObjState.dat");
		writeObject(ahmedObjStatePath, ahmed);
		try {
			Player deserializedAhmedObjState = readObject(ahmedObjStatePath);
			System.out.println(ahmed);
			System.out.println(deserializedAhmedObjState);
		} catch (IOException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

	}

	private static void writeData(Path dataFile) throws IOException{//U don't need to wrap the FileOutPutStream in BufferedOutputStream
		try (DataOutputStream dataOutStream = new DataOutputStream(//Accepts an OutPutStream implementation  //But buffered is beeter
				new BufferedOutputStream(new FileOutputStream(dataFile.toFile())))){						//than not buffered.

			int myInt = 17;
			long myLong = 100_000_000_000_000L;
			boolean myBoolean = true;
			char myChar = 'Z';
			float myFloat = 77.7f;
			double myDouble = 98.6;
			String myString = "Hello World";

			int position = 0;
			dataOutStream.writeInt(myInt);
			System.out.println("writeInt writes: " + (dataOutStream.size() -position) );
			position = dataOutStream.size();

			dataOutStream.writeLong(myLong);
			System.out.println("writeLong writes: " + (dataOutStream.size() -position) );
			position = dataOutStream.size();

			dataOutStream.writeBoolean(myBoolean);
			System.out.println("writeBoolean writes: " + (dataOutStream.size() -position) );
			position = dataOutStream.size();

			dataOutStream.writeChar(myChar);
			System.out.println("writeChar writes: " + (dataOutStream.size() -position) );
			position = dataOutStream.size();

			dataOutStream.writeFloat(myFloat);
			System.out.println("writeFloat writes: " + (dataOutStream.size() -position) );
			position = dataOutStream.size();

			dataOutStream.writeDouble(myDouble);
			System.out.println("writeDouble writes: " + (dataOutStream.size() -position) );
			position = dataOutStream.size();

			dataOutStream.writeUTF(myString);//Variable length. Writes the length of the string first then the characters.
			System.out.println("writeUTF writes: " + (dataOutStream.size() -position) );//So the field is like this BBcharacters
			position = dataOutStream.size();//The first two bytes are used to save the length of the characters written.
		}
	}

	private static void readData(Path dataFile) throws IOException{
		try(DataInputStream dataInStream = new DataInputStream(Files.newInputStream(dataFile))){
			System.out.println("MyInt: " + dataInStream.readInt());
			System.out.println("MyLong: " + dataInStream.readLong());
			System.out.println("MyBoolean: " + dataInStream.readBoolean());
			System.out.println("MyChar: " + dataInStream.readChar());
			System.out.println("MyFloat: " + dataInStream.readFloat());
			System.out.println("MyDouble: " + dataInStream.readDouble());
			System.out.println("readUTF: " + dataInStream.readUTF());
		}
	}

	private static void writeObject(Path objData, Player player){
		try(ObjectOutputStream objOutStream = new ObjectOutputStream(Files.newOutputStream(objData))){

			objOutStream.writeObject(player);//Writing an object is this easy.

		}catch (IOException e){
			e.printStackTrace();
		}
	}

	private static Player readObject(Path objData) throws IOException, ClassNotFoundException {
//		Player player = null;
		try(ObjectInputStream objInStream = new ObjectInputStream(Files.newInputStream(objData))){

			return  (Player) objInStream.readObject();

		}
//		return player; //If u throw it again then u don't need to have a return after the catch. But if u catch it then u need one after the catch.
	}

}
