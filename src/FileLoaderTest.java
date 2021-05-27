import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileLoaderTest {
    @Test
    void getPath() {
        FileLoader loader;
        loader = new FileLoader("C:/Users/Admin/");
        assertEquals("C:/Users/Admin/", loader.GetPath());

        loader = new FileLoader("C:\\Users\\Admin\\AppData\\Roaming\\.minecraft");
        assertEquals("C:\\Users\\Admin\\AppData\\Roaming\\.minecraft", loader.GetPath());

        loader = new FileLoader("");
        assertEquals("", loader.GetPath());
    }

    @Test
    void changePath() {
        FileLoader loader;
        loader = new FileLoader("C:/Users/Admin/");
        loader.ChangePath("C:/Users/Admin/");
        Assertions.assertEquals("C:/Users/Admin/", loader.GetPath());

        loader = new FileLoader("C:/Users/Admin/");
        loader.ChangePath("C:/Users/Admin/minecraft/");
        Assertions.assertEquals("C:/Users/Admin/minecraft/", loader.GetPath());

        loader = new FileLoader("C:/Users/Admin/");
        loader.ChangePath("C:\\Users\\Admin\\AppData\\Roaming\\.minecraft");
        Assertions.assertEquals("C:\\Users\\Admin\\AppData\\Roaming\\.minecraft", loader.GetPath());

        loader = new FileLoader("C:/Users/Admin/");
        loader.ChangePath("");
        Assertions.assertEquals("", loader.GetPath());
    }

    @Test
    void loadFiles() {
        FileLoader loader;
        LoadedFiles result;

        loader = new FileLoader("C:/");
        result = new LoadedFiles();
        result.success = false;
        result.items = null;
        result.receptures = null;
        assertEquals(result, loader.LoadFiles());

        File file = new File("./resources/example.jar");
        String path;
        try{
            path = file.getCanonicalPath();
        } catch (Exception e) {
            path = "";
        }

        loader = new FileLoader(path);
        result = new LoadedFiles();
        result.success = true;

        Item coal = new Item(0, "minecraft:coal", "..\\resources\\example_data\\assets\\minecraft\\textures\\item\\coal.png", null);
        Item charcoal = new Item(0, "minecraft:charcoal", "..\\resources\\example_data\\assets\\minecraft\\textures\\item\\charcoal.png", null);
        Item coals = new Item(0, "minecraft:coals", "", new ArrayList<Item>(Arrays.asList(coal, charcoal)));
        Item oakPlank = new Item(0, "minecraft:oak_planks", "..\\resources\\example_data\\assets\\minecraft\\textures\\block\\oak_planks.png", null);
        Item plank = new Item(0, "minecraft:planks", "", new ArrayList<Item>(Arrays.asList(oakPlank)));
        Item stick = new Item(0, "minecraft:stick", "..\\resources\\example_data\\assets\\minecraft\\textures\\item\\stick.png", null);
        Item torch = new Item(0, "minecraft:torch", "..\\resources\\example_data\\assets\\minecraft\\textures\\bloc\\torch.png", null);
        result.items = new ArrayList<>(Arrays.asList(coal, charcoal, coals, oakPlank, plank, stick, torch));

        Recepture stickRec = new Recepture(0, "minecraft:crafting_shaped", new ArrayList<>(Arrays.asList(oakPlank, null, null, oakPlank, null, null)),stick, 4);
        Recepture torchRec1 = new Recepture(0, "minecraft:crafting_shaped", new ArrayList<>(Arrays.asList(coal, null, null, stick, null, null)),torch, 4);
        Recepture torchRec2 = new Recepture(0, "minecraft:crafting_shaped", new ArrayList<>(Arrays.asList(charcoal, null, null, stick, null, null)),torch, 4);
        result.receptures = new ArrayList<>(Arrays.asList(stickRec, torchRec1, torchRec2));

        assertEquals(result, loader.LoadFiles());
    }
}