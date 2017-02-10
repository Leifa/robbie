package de.leifaktor.robbie.io;

import java.util.Iterator;
import java.util.Map.Entry;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter.OutputType;

import de.leifaktor.robbie.data.Episode;
import de.leifaktor.robbie.data.Room;
import de.leifaktor.robbie.data.RoomLayer;
import de.leifaktor.robbie.data.RoomManager;
import de.leifaktor.robbie.data.XYZPos;
import de.leifaktor.robbie.data.entities.Entity;
import de.leifaktor.robbie.data.tiles.Tile;

public class IO {
    
    public static void save(Episode e, FileHandle file) {        
        Json json = new Json();
        json.setTypeName("class");
        json.setUsePrototypes(false);
        json.setIgnoreUnknownFields(true);
        json.setOutputType(OutputType.json);
        json.setSerializer(RoomLayer.class, new RoomLayerSerializer());
        json.setSerializer(RoomManager.class, new RoomManagerSerializer());
        file.writeString(json.prettyPrint(e), false);
    }
    
    public static Episode load(FileHandle file) {
        Json json = new Json();
        json.setSerializer(RoomLayer.class, new RoomLayerSerializer());
        json.setSerializer(RoomManager.class, new RoomManagerSerializer());
        return json.fromJson(Episode.class, file);
    }
    
    static class RoomLayerSerializer implements Json.Serializer<RoomLayer> {        
        public void write (Json json, RoomLayer layer, Class knownType) {
           json.writeObjectStart();
           json.writeValue("width", layer.getWidth());
           json.writeValue("height", layer.getHeight());
           json.writeArrayStart("tiles");
           for (int i = 0; i < layer.getTiles().length; i++) {
               if (layer.getTiles()[i] != null) {
                   json.writeValue(layer.getTiles()[i].getID());
               } else { // if for some reason some tiles are null, write transparent tiles instead
                   json.writeValue(Tile.transparent.getID());
               }
           }
           json.writeArrayEnd();
           json.writeArrayStart("entities");
           for (int i = 0; i < layer.getEntities().size(); i++) {
               json.writeObjectStart();
               json.writeType(layer.getEntities().get(i).getClass());
               json.writeFields(layer.getEntities().get(i));
               json.writeObjectEnd();
           }
           json.writeArrayEnd();
           json.writeObjectEnd();
        }

        @Override
        public RoomLayer read(Json json, JsonValue jsonData, Class type) {
            // Read width and height
            jsonData = jsonData.child();
            int width = jsonData.asInt();
            jsonData = jsonData.next();
            int height = jsonData.asInt();
            RoomLayer layer = new RoomLayer(width, height);
            // Read tiles
            jsonData = jsonData.next();
            jsonData = jsonData.child();
            for (int i = 0; i < width*height; i++) {
                int id = jsonData.asInt();
                layer.getTiles()[i] = Tile.get(id);
                if (i < width*height - 1) jsonData = jsonData.next();
            }
            jsonData = jsonData.parent();
            // Entities
            jsonData = jsonData.next();
            if (jsonData.child() != null) { // Falls der Array nicht leer ist
                jsonData = jsonData.child(); // gehe in den Array
                Entity e = json.fromJson(Entity.class, jsonData.toString()); // mache eine Entity aus dem Objekt
                layer.getEntities().add(e); // und füge sie zum Layer hinzu
                while (jsonData.next() != null) { // solange es weitere Elemente im Array gibt
                    jsonData = jsonData.next(); // gehe zum nächsten
                    e = json.fromJson(Entity.class, jsonData.toString()); // mache eine Entity daraus
                    layer.getEntities().add(e); // und füge sie hinzu
                }
            }
            return layer;
        }
     };
     
     static class RoomManagerSerializer implements Json.Serializer<RoomManager> {

        @Override
        public void write(Json json, RoomManager rooms, Class knownType) {
            json.writeObjectStart();
            json.writeArrayStart("rooms");
            for (Iterator<Entry<XYZPos, Room>> iterator = rooms.getRoomIterator(); iterator.hasNext();) {
                Entry<XYZPos, Room> entry = iterator.next();                
                json.writeObjectStart();
                json.writeValue("x", entry.getKey().x);
                json.writeValue("y", entry.getKey().y);
                json.writeValue("z", entry.getKey().z);
                json.writeValue("room", entry.getValue());
                json.writeObjectEnd();
            }
            json.writeArrayEnd();
            json.writeObjectEnd();
        }

        @Override
        public RoomManager read(Json json, JsonValue jsonData, Class type) {
            RoomManager rooms = new RoomManager();
            jsonData = jsonData.child();
            if (jsonData.child() != null) { // Falls der Array nicht leer ist
                jsonData = jsonData.child(); // gehe in den Array
                jsonData = jsonData.child();
                int x = jsonData.asInt();
                jsonData = jsonData.next();
                int y = jsonData.asInt();
                jsonData = jsonData.next();
                int z = jsonData.asInt();
                jsonData = jsonData.next();
                Room room = json.readValue(Room.class, jsonData);
                rooms.setRoom(z, x, y, room);
                jsonData = jsonData.parent();
                while (jsonData.next() != null) {
                    jsonData = jsonData.next(); // gehe zum nächsten Eintrag
                    jsonData = jsonData.child();
                    x = jsonData.asInt();
                    jsonData = jsonData.next();
                    y = jsonData.asInt();
                    jsonData = jsonData.next();
                    z = jsonData.asInt();
                    jsonData = jsonData.next();
                    room = json.readValue(Room.class, jsonData);
                    rooms.setRoom(z, x, y, room);
                    jsonData = jsonData.parent();
                }
            }
            return rooms;
        }
         
     }

}
