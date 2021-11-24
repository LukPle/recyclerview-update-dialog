package com.example.update_dialog;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * The MainActivity creates a Shopping List using a ArrayList with a RecyclerView.
 * The RecyclerView in combination with an Adapter is the recommended way for creating lists in Android.
 * It is possible to edit an item of the shopping list by clicking on a specific view.
 *
 * The documentation focuses on the click on the item view and the dialog for editing an item.
 * For Javadoc information about the RecyclerView and Adapter check out the specific project.
 *
 * Layout File: activity_main.xml
 *
 * @author Lukas Plenk
 */
public class MainActivity extends AppCompatActivity implements EditDialog.EditDialogListener {

    private RecyclerView recyclerView;
    private ArrayList<Item> itemList;
    private Item selectedListItem;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemList = new ArrayList<>();
        fillItemList();

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new Adapter(itemList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(Item item) {

                selectedListItem = item;
                openDialog();
            }
        });
    }

    private void fillItemList() {

        itemList.add(new Item("Noodles", "1"));
        itemList.add(new Item("Cheese", "1"));
        itemList.add(new Item("Pepper", "2"));
        itemList.add(new Item("Onions", "4"));
        itemList.add(new Item("Carrots", "2"));
        itemList.add(new Item("Tomato", "1"));
        itemList.add(new Item("Fish", "4"));
        itemList.add(new Item("Grill Sauce", "1"));
        itemList.add(new Item("Baguette", "1"));
        itemList.add(new Item("Mushrooms", "6"));
        itemList.add(new Item("Cooking Oil", "1"));
    }

    /**
     * This method creates an instance of the EditDialog class.
     * That instance will be displayed on the screen via a FragmentManager.
     */
    public void openDialog() {

        EditDialog editDialog = new EditDialog();
        editDialog.show(getSupportFragmentManager(), "Edit Item");
    }

    /**
     * This method takes the data from the dialog and edits the element in the ArrayList.
     * @param item is the name of the item which the user typed in the dialog.
     * @param quantity is the quantity of the item which the user typed in the dialog.
     */
    @Override
    public void applyTexts(String item, String quantity) {

        selectedListItem.changeItem(item);
        selectedListItem.changeQuantity(quantity);
        adapter.notifyDataSetChanged();
    }
}