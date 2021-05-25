import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

class ItemWindowTest {
    @Test
    void getReceptures() {
        ArrayList<Recepture> arr = new ArrayList<>();
        Item[] items = new Item[10];

        ItemsArrayCreator(items);
        ReceptureArrayCreator(arr, items);

        ItemWindow window = new ItemWindow(arr, null);
        Assertions.assertEquals(arr, window.GetReceptures());
    }

    @Test
    void getNextItems() {
        ArrayList<Recepture> arr = new ArrayList<>();
        Item[] items = new Item[10];

        ItemsArrayCreator(items);
        ReceptureArrayCreator(arr, items);

        ArrayList<Item> nextItems = new ArrayList<>();
        nextItems.add(new Item(0, "stick", null, null));
        nextItems.add(new Item(1, "flint", null, null));
        nextItems.add(new Item(2, "cooked porkchop", null, null));

        ItemWindow window = new ItemWindow(arr, nextItems);
        Assertions.assertEquals(nextItems, window.GetNextItems());
    }

    @Test
    void getCurrentRecepture() {
        ArrayList<Recepture> arr = new ArrayList<>();
        Item[] items = new Item[10];

        ItemsArrayCreator(items);
        ReceptureArrayCreator(arr, items);
        ItemWindow window = new ItemWindow(arr, null);
        Recepture r1 = arr.get(0);
        Recepture r2 = window.GetCurrentRecepture();
        Assertions.assertEquals(arr.get(0), window.GetCurrentRecepture());
    }

    @Test
    void nextRecepture() {
        ArrayList<Recepture> arr = new ArrayList<>();
        Item[] items = new Item[10];

        ItemsArrayCreator(items);
        ReceptureArrayCreator(arr, items);
        ItemWindow window = new ItemWindow((ArrayList<Recepture>) arr.clone(), null);

        window.NextRecepture();
        Assertions.assertEquals(arr.get(1), window.GetCurrentRecepture());

        window.NextRecepture();
        Assertions.assertEquals(arr.get(2), window.GetCurrentRecepture());

        window.NextRecepture();
        window.NextRecepture();
        window.NextRecepture();
        Assertions.assertEquals(arr.get(0), window.GetCurrentRecepture());
    }

    @Test
    void prevRecepture() {
        ArrayList<Recepture> arr = new ArrayList<>();
        Item[] items = new Item[10];

        ItemsArrayCreator(items);
        ReceptureArrayCreator(arr, items);
        ItemWindow window = new ItemWindow((ArrayList<Recepture>) arr.clone(), null);

        window.PrevRecepture();
        Assertions.assertEquals(arr.get(4), window.GetCurrentRecepture());

        window.PrevRecepture();
        Assertions.assertEquals(arr.get(3), window.GetCurrentRecepture());

        window.PrevRecepture();
        window.PrevRecepture();
        window.PrevRecepture();
        Assertions.assertEquals(arr.get(0), window.GetCurrentRecepture());
    }

    private static void ReceptureArrayCreator(ArrayList<Recepture> arr, Item[] items) {
		ArrayList<Item> rec1 = new ArrayList<>(Arrays.asList(items[1], items[0], items[0],
			items[1], items[0], items[0],
			items[0], items[0], items[0]));
		ArrayList<Item> rec2 = new ArrayList<>(Arrays.asList(
			items[8], items[8], items[0],
			items[8], items[8], items[0],
			items[0], items[0], items[0]));
		ArrayList<Item> rec3 = new ArrayList<>(Arrays.asList(
			items[5], items[0], items[0],
			items[0], items[0], items[0],
			items[0], items[0], items[0]));
		ArrayList<Item> rec4 = new ArrayList<>(Arrays.asList(
			items[3], items[4], items[0],
			items[1], items[0], items[0],
			items[0], items[0], items[0]));
		ArrayList<Item> rec5 = new ArrayList<>(Arrays.asList(
			items[7], items[0], items[0],
			items[0], items[0], items[0],
			items[0], items[0], items[0]));

        arr.add(new Recepture(0, "crafting_table", rec1, items[0], 0));
        arr.add(new Recepture(0, "crafting_table", rec2, items[0], 0));
        arr.add(new Recepture(0, "furnance", rec3, items[0], 0));
        arr.add(new Recepture(0, "brewing_stand", rec4, items[0], 0));
        arr.add(new Recepture(0, "smithing_table", rec5, items[0], 0));
    }

    public static void ItemsArrayCreator(Item[] items) {
        items[0] = null;
        items[1] = new Item(0, "aa", null, null);
        items[2] = new Item(1, "aab", null, null);
        items[3] = new Item(2, "bab", null, null);
        items[4] = new Item(3, "c", null, null);
        items[5] = new Item(4, "g", null, null);
        items[6] = new Item(5, "z", null, null);
        items[7] = new Item(6, "k klet", null, null);
        items[8] = new Item(7, "k k", null, null);
        items[9] = new Item(8, "a", null, null);
    }
}